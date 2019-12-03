package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.crm.dao.IPackDao;
import tn.esprit.crm.entities.Pack;
import tn.esprit.crm.services.IPackService;

@Stateless
public class PackServiceImpl implements IPackService{

	
	@PersistenceContext(unitName="crm-ejb") //l esm men persistance.xml
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
			Query q = em.createQuery("delete Pack_Product p where p.packs.id=:id"); //verifier si la discount est affecter a un produit
			q.setParameter("id", id);
			q.executeUpdate();
			 packDao.remove(id);
			 return true;
		}
		return false;
	}

	@Override
	public Pack getById(Long id) {
		return packDao.getById(id);
	}
	

}
