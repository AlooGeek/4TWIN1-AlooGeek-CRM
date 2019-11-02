package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.crm.dao.IPackProductDao;
import tn.esprit.crm.entities.Pack_Product;
import tn.esprit.crm.entities.User;
import tn.esprit.crm.services.IPackProductService;

@Stateless
public class PackProductServiceImpl  implements IPackProductService{

	@PersistenceContext(unitName="crm-ejb") //l esm men persistance.xml
	
	EntityManager em;
	
	@EJB
	IPackProductDao ppdao;
	
	@Override
	public Pack_Product save(Pack_Product pp) {
		return ppdao.save(pp);
	}

	@Override
	public List<Pack_Product> selectAll() {
		return ppdao.selectAll();
	}

	@Override
	public Pack_Product update(Pack_Product pp) {
		return ppdao.update(pp);
	}

	@Override
	public boolean remove(Long id) {
		if(id!=0) {
			 ppdao.remove(id);
			 return true;
		}
		return false;
	}

	@Override
	public Pack_Product getById(Long id) {
		return ppdao.getById(id);
	}

	@Override
	public List<Double> getPriceOfPack() {
		List<Double> packs=em.createQuery("SELECT SUM(p.prix) FROM Pack_Product p group by p.packs ",Double.class).getResultList();
		return packs;
	}

}
