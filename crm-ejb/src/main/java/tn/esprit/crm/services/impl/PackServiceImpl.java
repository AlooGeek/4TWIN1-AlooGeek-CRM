package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.crm.dao.IPackDao;
import tn.esprit.crm.entities.Pack;
import tn.esprit.crm.services.IPackService;

@Stateless
public class PackServiceImpl implements IPackService{

	
	@PersistenceContext(unitName="crm-ejb") //l esm men persistance.xml
	//private IDiscountDao discountDao;
	EntityManager em;
	
	@EJB
	IPackDao packDao;
	@Override
	public Pack save(Pack pack) {
		return packDao.save(pack);
	}

	@Override
	public List<Pack> selectAll() {
		return packDao.selectAll();
	}

	@Override
	public Pack update(Pack pack) {
		return packDao.update(pack);
	}

	@Override
	public boolean remove(Long id) {
		if(id!=0) {
			 packDao.remove(id);
			 return true;
		}
		return false;
	}
	

}
