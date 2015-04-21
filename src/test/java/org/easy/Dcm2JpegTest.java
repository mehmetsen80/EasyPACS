package org.easy;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.dcm4che3.tool.dcm2jpg.Dcm2Jpg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@WebAppConfiguration
public class Dcm2JpegTest extends TestCase{

	private static final Logger LOG = LoggerFactory.getLogger(Dcm2JpegTest.class);
	
	@Test
	public void test2Jpeg() throws IOException{
	
		try{
			
			File src = new File("C:/Users/mehmet/Documents/workspace-sts-3.6.0.RELEASE/EasyPACS/tmp/dcm/1.3.12.2.1107.5.1.4.39115.30000014103112592382800014368.dcm");
			File dest = new File("C:/Temp/1.3.12.2.1107.5.1.4.39115.30000014103112592382800014368.jpg");
			
			Dcm2Jpg dcm2jpg = new Dcm2Jpg();
			dcm2jpg.initImageWriter("JPEG", null, null, null, null);
			dcm2jpg.convert(src, dest);
			assertTrue(dest.exists());
			
		}catch(IOException e){
			LOG.error(e.getMessage());
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
	}
}
