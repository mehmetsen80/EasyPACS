package org.easy.dao.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.easy.dao.AbstractJpaDao;
import org.easy.dao.EquipmentDao;
import org.easy.entity.Equipment;

import org.springframework.stereotype.Repository;

@Repository
public class EquipmentDaoImpl extends AbstractJpaDao<Equipment> implements EquipmentDao {
	
	@PersistenceContext(unitName = "dbdicom")
	private EntityManager entityManager;
	
	public EquipmentDaoImpl(){
		super();
		setClazz(Equipment.class);
	}
	
	/*@Transactional
	@Override
	public void save(Equipment equipment) {
		entityManager.persist(equipment);
		entityManager.flush();
	}

	@Override
	public List<Equipment> findAll() {
		try{			
			TypedQuery<Equipment> query = entityManager.createQuery("select e FROM Equipment e", Equipment.class);			 
			return query.getResultList();
			
		}catch(Exception e){			
			return null;		
		}
	}

	@Override
	public Equipment findByID(long pkTBLEquipmentID) {
		try{			
			return entityManager.find(Equipment.class, pkTBLEquipmentID);
			
		}catch(Exception e){			
			return null;
		}
	}*/
	
	@Override 
	public Equipment findByPkTBLSeriesID(Long pkTBLSeriesID){
		
		try{
			return entityManager.createQuery("select e from Equipment e  where e.series.pkTBLSeriesID LIKE :pkTBLSeriesID", Equipment.class)
			.setParameter("pkTBLSeriesID", pkTBLSeriesID)			
			.getSingleResult();
		}catch(Exception e){			
			return null;		
		}
	}

}
