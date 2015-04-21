package org.easy.dao;


import java.util.List;

import org.easy.entity.Study;


public interface StudyDao{

	void save(Study study);
	Study update(Study study);
	List<Study> findAll(int firstResult, int maxResults);
	Long count();
	Study findById(long pkTBLStudyID);
	List<Study> findByPkTBLPatientID(Long pkTBLPatientID);
	Study findByStudyInstanceUID(String studyInstanceUID);
}
