package org.easy.server;



import com.google.common.eventbus.EventBus;


import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomInputStream.IncludeBulkData;
import org.dcm4che3.io.DicomOutputStream;
import org.dcm4che3.net.*;
import org.dcm4che3.net.pdu.AAssociateAC;
import org.dcm4che3.net.pdu.AAssociateRQ;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.pdu.UserIdentityAC;
import org.dcm4che3.net.service.BasicCEchoSCP;
import org.dcm4che3.net.service.BasicCStoreSCP;
import org.dcm4che3.net.service.DicomServiceRegistry;
import org.dcm4che3.util.SafeClose;
import org.easy.event.NewFileEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class DicomServer {
    private static final Logger LOG = LoggerFactory.getLogger(DicomServer.class);

    private static final String DCM_EXT = ".dcm";

    private final Device device = new Device("storescp");
    private final ApplicationEntity ae = new ApplicationEntity("*");
    private final Connection conn = new Connection();
    private File storageDir;
   
    private int status;

    public EventBus eventBus;

    private final class CStoreSCPImpl extends BasicCStoreSCP {

        CStoreSCPImpl() {
            super("*");
        }

        @Override
        protected void store(Association as, PresentationContext pc,
                             Attributes rq, PDVInputStream data, Attributes rsp)
                throws IOException {
            rsp.setInt(Tag.Status, VR.US, status);
            if (storageDir == null)
                return;

            String ipAddress  = as.getSocket().getInetAddress().getHostAddress(); //ip address
            String associationName = as.toString();
            String cuid = rq.getString(Tag.AffectedSOPClassUID);
            String iuid = rq.getString(Tag.AffectedSOPInstanceUID);
            String tsuid = pc.getTransferSyntax();
            


            //File file = new File(storageDir, ipAddress + "_" + iuid + DCM_EXT);
            File file = new File(storageDir, iuid + DCM_EXT);
            try {
                LOG.info("as: {}", as);
                storeTo(as, as.createFileMetaInformation(iuid, cuid, tsuid),
                        data, file);
                
                if(!file.exists()){
                	LOG.error("File {} does not exists! Connection Details--> ipAddress: {}  associationName: {}  sopclassuid: {}  sopinstanceuid: {} transfersyntax: {}", file.getAbsolutePath(), ipAddress, associationName, cuid, iuid, tsuid);
                	return;
                }
                
                eventBus.post(new NewFileEvent(file));
                
                //let's parse the files
                /*Attributes attrs = parse(file);
                if(attrs != null){                	
                	String studyiuid = attrs.getString(Tag.StudyInstanceUID);
	         		String patientID = attrs.getString(Tag.PatientID);
	         		patientID = (patientID == null || patientID.length() == 0) ? "<UNKNOWN>" : patientID;
	         		Long projectID = -1L;
	         		String patientName = attrs.getString(Tag.PatientName);
	         		String institutionName = attrs.getString(Tag.InstitutionName);
	         		String uniqueID = file.getName();
	         		Date studyDate =  attrs.getDate(Tag.StudyDate);
	         		Date studyTime =  attrs.getDate(Tag.StudyTime);         		
	         		
	         		String studyDateTime = (studyDate != null && studyTime != null)?new SimpleDateFormat("MM-dd-yyyy").format(studyDate)+" "+new SimpleDateFormat("HH-mm-ss").format(studyTime):"01-01-1901 01-01-01";
	         	
	         		
	         		
                	//eventBus.post(new NewLogEvent(as.toString(), "IMAGE_RECEIVED", ipAddress, studyDateTime, projectID, patientID, patientName, null, studyiuid, institutionName, uniqueID));
                	//eventBus.post(new NewFileEvent(file, ipAddress, studyiuid, iuid, cuid, associationName));
                	
                }else{
                	LOG.error("File Name {} could not be parsed!",file.getName());
                }*/

            }catch(EOFException e){
            	//deleteFile(as, file); //broken file, just remove...     
            	LOG.error("Dicom Store EOFException: " + e.getMessage());               
            }
            catch (Exception e) {              
            	deleteFile(as, file); //broken file, just remove...     
                LOG.error("Dicom Store Exception: " + e.getMessage());        
            }
            
        }
    };

    public DicomServer() throws IOException {
        device.setDimseRQHandler(createServiceRegistry());
        device.addConnection(conn);
        device.addApplicationEntity(ae);
        device.setAssociationHandler(associationHandler);
        ae.setAssociationAcceptor(true);
        ae.addConnection(conn);
    }

    private void storeTo(Association as, Attributes fmi,
                         PDVInputStream data, File file) throws IOException  {
        LOG.info("{}: M-WRITE {}", as, file);
        file.getParentFile().mkdirs();
        DicomOutputStream out = new DicomOutputStream(file);
        try {
            out.writeFileMetaInformation(fmi);
            data.copyTo(out);
        } finally {
            SafeClose.close(out);
        }
    }

    private static Attributes parse(File file) throws IOException {
        DicomInputStream in = new DicomInputStream(file);
        try {
            in.setIncludeBulkData(IncludeBulkData.NO);
            return in.readDataset(-1, Tag.PixelData);
        } finally {
            SafeClose.close(in);
        }
    }

    private static void deleteFile(Association as, File file) {
        if (file.delete())
            LOG.info("{}: M-DELETE {}", as, file);
        else
            LOG.warn("{}: M-DELETE {} failed!", as, file);
    }

    private DicomServiceRegistry createServiceRegistry() {
        DicomServiceRegistry serviceRegistry = new DicomServiceRegistry();
        serviceRegistry.addDicomService(new BasicCEchoSCP());
        serviceRegistry.addDicomService(new CStoreSCPImpl());
        return serviceRegistry;
    }

    public void setStorageDirectory(File storageDir) {
        if (storageDir != null)
            storageDir.mkdirs();
        this.storageDir = storageDir;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static void configureConn(Connection conn){
        conn.setReceivePDULength(Connection.DEF_MAX_PDU_LENGTH);
        conn.setSendPDULength(Connection.DEF_MAX_PDU_LENGTH);
      
        conn.setMaxOpsInvoked(0);
        conn.setMaxOpsPerformed(0);
    }

    public static DicomServer init(String aeHost, int aePort, String aeTitle, String storageDirectory, EventBus eventBus) {
        LOG.info("Bind to: " + aeTitle + "@" + aeHost + ":" + aePort + "; storage: " + storageDirectory);

        DicomServer ds = null;
        try {
            ds = new DicomServer();

            ds.eventBus = eventBus;
            if(aeHost != null) {
                ds.conn.setHostname(aeHost);
            }
            ds.conn.setPort(aePort);
            ds.ae.setAETitle(aeTitle);

            //default conn parameters
            configureConn(ds.conn);

            //accept-unknown
            ds.ae.addTransferCapability(
                    new TransferCapability(null,
                            "*",
                            TransferCapability.Role.SCP,
                            "*"));

            ds.setStorageDirectory(new File(storageDirectory));

            ExecutorService executorService = Executors.newCachedThreadPool();
            ScheduledExecutorService scheduledExecutorService =
                    Executors.newSingleThreadScheduledExecutor();
            ds.device.setScheduledExecutor(scheduledExecutorService);
            ds.device.setExecutor(executorService);
            ds.device.bindConnections();

        }catch (Exception e) {
            LOG.error("dicomserver: {}", e.getMessage());
            e.printStackTrace();
        }

        return ds;
    }
    
    
    private AssociationHandler associationHandler = new AssociationHandler(){
		
		@Override
		protected AAssociateAC makeAAssociateAC(Association as,
				AAssociateRQ rq, UserIdentityAC arg2) throws IOException {
			
			State st = as.getState();
			
			if(as != null)
			{				
				LOG.info("makeAAssociateAC: {}  Associate State: {}  Associate State Name: {}", as.toString(), st, st.name());
				try {					
					 //eventBus.post(new NewLogEvent(as.toString(),st.name(),as.getSocket().getInetAddress().getHostAddress(), null, null,null,null,null,null,null,null));
				}catch (Exception e) {
					LOG.error(e.getMessage());
				}
			}
			
			if(rq != null)
				LOG.info("Max OpsInvoked: {}  Max OpsPerformed: {}  Max PDU Length: {}  Number of Pres. Contexts: {}",rq.getMaxOpsInvoked(), rq.getMaxOpsPerformed(), rq.getMaxPDULength(), rq.getNumberOfPresentationContexts());
			
			if(arg2 != null)
				LOG.info("UserIdentityAC Length:{}",arg2.length());
			
			return super.makeAAssociateAC(as, rq, arg2);
		}
		
		@Override
		protected AAssociateAC negotiate(Association as, AAssociateRQ rq)
				throws IOException {
			
			if(as != null)
				LOG.info("AAssociateAC negotiate:{}",as.toString());
			
			return super.negotiate(as, rq);
		}
		
		@Override
		protected void onClose(Association as) {
			
			State st = as.getState();
			
			if(as != null && st == State.Sta13){
				LOG.info("Assocation Released and Closed: {} Name: {}", as.getState(), as.toString());			
					
				try {					
					//eventBus.post(new NewLogEvent(as.toString(),st.name(),as.getSocket().getInetAddress().getHostAddress(), null, null, null, null,null,null,null,null));
				}  catch (Exception e) {					
					LOG.error(e.getMessage());					
				} 
			}
			else
			{
				LOG.info("Association Closed");
			}
			
			super.onClose(as);
		}
	};
}
