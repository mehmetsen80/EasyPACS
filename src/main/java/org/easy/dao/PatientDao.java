package org.easy.dao;

import java.util.List;

import org.easy.entity.Patient;

public interface PatientDao {

	void save(Patient patient);
	Patient update(Patient patient);
	List<Patient> findAll(int firstResult, int maxResults);
	Long count();
	Patient findById(long pkTBLPatientID);
	Patient findByPatientID(String patientID);
}
