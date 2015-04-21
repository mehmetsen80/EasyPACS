package org.easy.dao;

import java.util.List;

import org.easy.entity.Equipment;

public interface EquipmentDao {

	void save(Equipment equipment);
	Equipment update(Equipment equipment);
	List<Equipment> findAll(int firstResult, int maxResults);
	Long count();
	Equipment findById(long pkTBLEquipmentID);
	Equipment findByPkTBLSeriesID(Long pkTBLSeriesID);
}
