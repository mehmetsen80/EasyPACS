package org.easy;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import junit.framework.TestCase;

import org.easy.dao.EquipmentDao;
import org.easy.dao.InstanceDao;
import org.easy.server.DicomReader;
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

@Ignore
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@WebAppConfiguration
public class DBServiceTest extends TestCase{

	private static final Logger LOG = LoggerFactory.getLogger(DBServiceTest.class);
	
	@Autowired
	InstanceDao instanceDao;		
	
	@Autowired
	EquipmentDao equipmentDao;
	
	@Autowired
	DBService dbService;
	
	@Ignore
	@Test
	@Rollback(false)
	public void testSingleInstanceDicomObject(){
		
		//File file = new File("C:/Temp/1.3.12.2.1107.5.1.4.65710.30000014053020082123400002087");
		File file = new File("C:/Temp/159.150.226.197_1.3.12.2.1107.5.1.4.55292.30000015032113073778100003743");
		
		
		DicomReader dicomReader = null;
		try {
				dicomReader = new DicomReader(file);
				LOG.info(dicomReader.toString());
				dbService.buildEntities(dicomReader);
				
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}
	
	
	@Ignore
	@Test
	@Rollback(false)
	public void testBuildEntities(){		
		
		File folder = new File("C:/Temp/MARTIN^ZANE^_WM00227587_03-21-2015-20-39-01_2.16.840.1.114151.4.887.42082.8558.2202495/");
		//File folder = new File("C:/Temp/test2/");
		
		try{
			if(folder.isDirectory()){
				Integer row = 0;			
				for(File file : folder.listFiles()){	
					DicomReader dicomReader = new DicomReader(file);
					dbService.buildEntities(dicomReader);
					LOG.info("{}- {} saved successfully!", ++row, file.getName());
				}
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}
}
