package org.easy.handler;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.easy.component.ActiveDicoms;
import org.easy.event.NewFileEvent;
import org.easy.server.DicomReader;
import org.easy.service.DBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;



public class IncomingFileHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(IncomingFileHandler.class);
		
	@Autowired(required = true)
	private EventBus eventBus;
	
	@Autowired
	private DBService dbService;
	
	@Autowired
	private ActiveDicoms activeDicoms;
		
	@Transactional
	@Subscribe
    @AllowConcurrentEvents
    public void handleIncomingFileEvent(NewFileEvent newFileEvent) {
    	//IMPORTANT! Write everything inside try catch, the guava breaks if an exception occurs
		
		try{
			File file = newFileEvent.getFile();//get the file from event handler
			DicomReader reader = new DicomReader(file);			
			
			//LOG.info("Active Dicoms:{} Received Patient Name:{} ID:{} Age:{} Sex:{} ", activeDicoms.toString(), reader.getPatientName(), reader.getPatientID(), reader.getPatientAge(), reader.getPatientSex());
			synchronized(dbService){
				dbService.buildEntities(reader);//lets build our dicom database entities
			}
			
			
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
	}
	
	@PostConstruct
    public void postConstruct(){
        eventBus.register(this);       
    }

    @PreDestroy
    public void preDestroy(){
        eventBus.unregister(this);
    }
	
	public void printStats(String status) {
		//String str = Thread.currentThread().getName().split("@@")[0];
		//Thread.currentThread().setName(String.valueOf(Thread.currentThread().getId()));		
		LOG.info("{} {} {} [Active Threads: {}] ",Thread.currentThread().getId(), Thread.currentThread().getName(), status, Thread.activeCount());		
		
	}
}
