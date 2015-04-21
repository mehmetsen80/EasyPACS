package org.easy;

import java.util.Calendar;
import java.util.List;


import javax.transaction.Transactional;
import junit.framework.TestCase;

import org.easy.dao.StudyDao;
import org.easy.entity.Patient;
import org.easy.entity.Study;
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
public class StudyTest extends TestCase{

	private static final Logger LOG = LoggerFactory.getLogger(StudyTest.class);
	
	@Autowired
	StudyDao studyDao;	
	
	@Ignore
	@Test	
	public void testList(){		
		
		List<Study> studies = studyDao.findAll(1,10);
		
		for(Study study : studies){
			Patient patient = study.getPatient();
			LOG.info("Patient: {}", patient.toString());
			LOG.info("Study: {}", study.toString());
			LOG.info("-----------------------------------------------------------------------------------------------------------------");
		}		
	}
	
	@Test	
	@Ignore
	@Rollback(false)
	public void testInsert(){
		
		Study study = new Study();
		study.setReferringPhysicianName("john doe");
		study.setStudyID("Zra12344");
		study.setStudyDateTime(Calendar.getInstance().getTime());

		Patient patient = new Patient();
		patient.setPatientID("RA299111");
		patient.setPatientName("Albert Doe");
		patient.setPatientAge("26M");
		patient.setPatientSex("M");
		patient.setPatientBirthday(Calendar.getInstance().getTime());

		study.setPatient(patient);

		//IDs start as null
		assertEquals((Long) null, study.getPkTBLStudyID());
		assertEquals((Long) null, patient.getPkTBLPatientID());		
		
		studyDao.save(study);
		
		testList();
	}
	
	
	@Test	
	@Ignore
	public void testFindByID(){
		//test a null study object that does not exist
		Study study = studyDao.findById(3);		
		LOG.info(study.toString());
		assertNotNull(study);
	}	
	
	@Ignore
	@Test
	public void testFindByStudyInstanceUID(){		
		Study study = studyDao.findByStudyInstanceUID("2.16.840.1.114151.4.887.42082.8558.2202495");
		assertNotNull(study);
		LOG.info(study.toString());
	}
	
	@Test
	@Ignore
	public void testFindByPatientID(){
		List<Study> studies = studyDao.findByPkTBLPatientID(1L);
		for(Study study : studies){
			Patient patient = study.getPatient();
			LOG.info("Patient: {}", patient.toString());
			LOG.info("Study: {}", study.toString());
			LOG.info("-----------------------------------------------------------------------------------------------------------------");
		}
	}
}
