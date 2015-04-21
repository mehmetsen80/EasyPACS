package org.easy.service;

import org.easy.entity.Equipment;
import org.easy.entity.Instance;
import org.easy.entity.Patient;
import org.easy.entity.Series;
import org.easy.entity.Study;
import org.easy.server.DicomReader;


public interface DBService {

	public void buildEntities(DicomReader reader);
	Patient buildPatient(DicomReader reader);
	Study buildStudy(DicomReader reader,Patient patient);
	Series buildSeries(DicomReader reader, Study study);
	Equipment buildEquipment(DicomReader reader, Series series);
	Instance buildInstance(DicomReader reader, Series series);
}
