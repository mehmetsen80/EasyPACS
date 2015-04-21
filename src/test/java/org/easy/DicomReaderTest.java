package org.easy;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.easy.server.DicomReader;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@WebAppConfiguration
public class DicomReaderTest extends TestCase{

	private static final Logger LOG = LoggerFactory.getLogger(DicomReaderTest.class);
	

	@Ignore
	@Test
	public void testReader(){
		
		File file = new File("C:/Temp/159.150.226.197_1.3.12.2.1107.5.1.4.55292.30000015032113073778100003743");
		
		DicomReader dicomReader = null;
		try {
				dicomReader = new DicomReader(file);
				LOG.info(dicomReader.toString());
				
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void testStudyFolderForDifferentSeries(){
		
		File folder = new File("C:/Temp/MARTIN^ZANE^_WM00227587_03-21-2015-20-39-01_2.16.840.1.114151.4.887.42082.8558.2202495/");
		
		DicomReader dicomReader = null;
		
		try{
			if(folder.isDirectory()){
				Integer row = 0;
				for(File file : folder.listFiles()){					
					dicomReader = new DicomReader(file);
					LOG.info("{}- MS SOP InstanceUID: {} Study Instance UID: {}  Series Instance UID: {}   Series Number: {}", ++row, dicomReader.getMediaStorageSopInstanceUID(), dicomReader.getSeriesInstanceUID(), dicomReader.getSeriesInstanceUID(), dicomReader.getSeriesNumber());					
				}
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		
	}
}
