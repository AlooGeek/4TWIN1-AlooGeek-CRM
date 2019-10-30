package tn.esprit.crm.services.impl;

import java.util.List;
import java.util.Locale.Category;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import tn.esprit.crm.dao.ICategoryDao;
import tn.esprit.crm.entities.Categorie;
import tn.esprit.crm.services.ICategoryService;

@Stateless
public class CategoryServiceImpl implements ICategoryService{
	
	@PersistenceContext(unitName="crm-ejb")
	
	@EJB
	ICategoryDao categoryDao;

	@Override
	public Categorie save(Categorie category) {
		
		return categoryDao.save(category);
	}

	@Override
	public Categorie update(Categorie category) {
		return categoryDao.update(category);
	}

	@Override
	public List<Categorie> selectAll() {
		return categoryDao.selectAll();
	}

	@Override
	public List<Categorie> selectBy(String param, String value) {
		return categoryDao.selectBy(param, value);
	}

	@Override
	public List<Categorie> selectAll(String sortField, String sort) {
		return categoryDao.selectAll(sortField, sort);
	}

	@Override
	public Categorie getById(Long id) {
		return categoryDao.getById(id);
	}

	@Override
	public boolean remove(Long id) {
		if (id!=0) {
			categoryDao.remove(id);
			return true;
		}
		return false;
	}


}
