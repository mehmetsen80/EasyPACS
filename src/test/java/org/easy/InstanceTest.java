package org.easy;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import junit.framework.TestCase;

import org.easy.dao.EquipmentDao;
import org.easy.dao.InstanceDao;
import org.easy.entity.Equipment;
import org.easy.entity.Instance;
import org.easy.entity.Patient;
import org.easy.entity.Series;
import org.easy.entity.Study;
import org.easy.service.DBService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@WebAppConfiguration
public class InstanceTest extends TestCase{
	
	private static final Logger LOG = LoggerFactory.getLogger(InstanceTest.class);
	
	@Autowired
	InstanceDao instanceDao;		
	
	@Autowired
	EquipmentDao equipmentDao;
	
	@Autowired
	DBService dbService;
	
	@Ignore
	@Test
	public void testList()
	{
		List<Instance> instances = instanceDao.findAll(1,10);
		
		if(instances.size() <= 0){
			LOG.info("no instances found!");
		}else{			
			for(Instance instance : instances){
				//print instance
				LOG.info("instance: {}", instance);				
				//get series
				Series series = instance.getSeries();				
				LOG.info("series: {}", series.toString());			
				//get study
				Study study = series.getStudy();				
				LOG.info("study: {}", study.toString());
				//get patient
				Patient patient = study.getPatient();
				LOG.info("patient: {}", patient.toString());
			}
		}		
	}
	
	@Ignore
	@Test
	public void testfindBySopInstanceUID(){
		
		Instance instance = instanceDao.findBySopInstanceUID("1.3.12.2.1107.5.1.4.55292.30000015032113073778100003751");
		assertNotNull(instance);
		LOG.info(instance.toString());
		instance = instanceDao.findBySopInstanceUID("1.3.12.2.1107.5.1.4.55292.3000001503211307377810000375188888888");
		assertNull(instance);
	}
	
	@Test
	@Ignore
	public void testFindByPKTBLSeriesID(){
		
		List<Instance> instances = instanceDao.findByPkTBLSeriesID(1L);
		
		for(Instance instance : instances){
			//print instance
			LOG.info("instance: {}", instance);				
			//get series
			Series series = instance.getSeries();				
			LOG.info("series: {}", series.toString());			
			//get study
			Study study = series.getStudy();				
			LOG.info("study: {}", study.toString());
			//get patient
			Patient patient = study.getPatient();
			LOG.info("patient: {}", patient.toString());
			
			LOG.info("-----------------------------------------------------------------------------------------------------------------");		
		}
	}
	
	@Ignore
	@Test
	@Rollback(false)
	public void testInstanceObject(){
		
		Instance instance = new Instance();
		instance.setKvp("8");
		instance.setPixelSpacing(2.56f);
		instance.setInstanceNumber(1);
		instance.setImageType("CT Image");
		
		Series series = new Series();		
		series.setOperatorsName("Elizabeth Uranus");
		series.setSeriesNumber(1);
		series.setSeriesDescription("CT Image with over 100 images");		
			
		
		Study study = new Study();
		study.setReferringPhysicianName("Anderson Yola");
		study.setStudyID("4593DB");
		study.setStudyDateTime(Calendar.getInstance().getTime());
		
		
		Patient patient = new Patient();
		patient.setPatientID("451123");
		patient.setPatientName("Nomine^Alerta");
		patient.setPatientAge("45F");
		patient.setPatientSex("F");
		patient.setPatientBirthday(Calendar.getInstance().getTime());

		study.setPatient(patient);
		series.setStudy(study);	
		instance.setSeries(series);
				
		//IDs start as null
		assertEquals((Long) null, instance.getPkTBLInstanceID());
		assertEquals((Long) null, series.getPkTBLSeriesID());
		assertEquals((Long) null, study.getPkTBLStudyID());
		assertEquals((Long) null, patient.getPkTBLPatientID());	
		
		
		testList();		
		instanceDao.save(instance);		
		
		assertNotNull(instance.getPkTBLInstanceID());
		assertNotNull(series.getPkTBLSeriesID());
		assertNotNull(study.getPkTBLStudyID());
		assertNotNull(patient.getPkTBLPatientID());
		
		//you need to test Equipment separately because it is one-To-One
		Equipment equipment = new Equipment();
		equipment.setInstitutionName("St. Vincent");
		equipment.setDeviceSerialNumber("897423587");
		equipment.setInstitutionalDepartmentName("Radiology Clinic");	
		equipment.setModality("CT");
		equipment.setSeries(series);//set the Series to Equipment because we now have the pkTBLSeriesID
		
		//ID starts as null
		assertEquals((Long) null, equipment.getPkTBLEquipmentID());		
		equipmentDao.save(equipment);//save Equipment here after you get the pkTBLSeriesID
		assertNotNull(equipment.getPkTBLEquipmentID());
		
		testList();
	}	
	
	@Ignore
	@Test
	public void testAllInstancesByPatientID(){
		
		List<Instance> instances = instanceDao.findAllByPkTBLPatientID(2L);
		assertEquals(0, instances.size());
		
		instances = instanceDao.findAllByPkTBLPatientID(1L);
		assertTrue(instances.size() > 0);
		
		int i=0;
		for(Instance instance : instances){
			//print instance
			LOG.info(++i + "- instance: {}", instance);				
			//get series
			Series series = instance.getSeries();				
			LOG.info("series: {}", series.toString());			
			//get study
			Study study = series.getStudy();				
			LOG.info("study: {}", study.toString());
			//get patient
			Patient patient = study.getPatient();
			LOG.info("patient: {}", patient.toString());
		}
		
		if(i==0)
			LOG.info("No instance found!");
	}
	
}
