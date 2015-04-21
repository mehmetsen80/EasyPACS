package org.easy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.easy.dao.PatientDao;
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

import junit.framework.TestCase;

@Ignore
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@WebAppConfiguration
public class PatientTest extends TestCase{

	private static final Logger LOG = LoggerFactory.getLogger(PatientTest.class);
	
	@Autowired
	PatientDao patientDao;
	
	
	@Ignore
	@Test	
	public void testList(){
		
		List<Patient> patients = patientDao.findAll(1,10);
		
		LOG.info("-----------------------------------------------------------------------------------------------------------------");
		
		for(Patient p : patients){			
			LOG.info(p.toString());			
		}
		
		LOG.info("-----------------------------------------------------------------------------------------------------------------");
		
	}
	
	@Ignore
	@Test
	@Rollback(false)
	public void testPatientObject(){
		
		Patient p = new Patient();
		p.setPatientID("TA147216");
		p.setPatientName("Adam^Clay");
		p.setPatientAge("49Y");
		p.setPatientSex("M");
		p.setPatientBirthday(Calendar.getInstance().getTime());
		
		Study study = new Study();
		study.setReferringPhysicianName("Holy Brown");
		study.setStudyID("A114297");
		study.setStudyDateTime(Calendar.getInstance().getTime());
		
		List<Study> studies = new ArrayList<Study>();
		studies.add(study);		
		p.setStudy(studies);//this won't save because Study is only a collection mapped by Study not Patient object!		
		patientDao.save(p);
		LOG.info(p.toString());		
		study = ((List<Study>) p.getStudy()).get(0);
		assertEquals((Long) null, study.getPkTBLStudyID());
		
		testList();
	}
	
	@Test
	public void testfindByPatientID(){
		
		Patient patient = patientDao.findByPatientID("WM002275878");
		assertNotNull(patient);
		LOG.info(patient.toString());
	}
}
