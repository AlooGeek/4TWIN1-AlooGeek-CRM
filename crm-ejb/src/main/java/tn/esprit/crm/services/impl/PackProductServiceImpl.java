package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
			Query q = em.createQuery("update Pack_Product p set p.packs=NULL,p.productss=NULL where p.id=:id");
			q.setParameter("id", id);
			q.executeUpdate();
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
	public List<Pack_Product> getPriceOfPack() {
		Query q=em.createQuery("SELECT SUM(p.prix),p.packs.id FROM Pack_Product p group by (p.packs.id)");
		
		return q.getResultList();	
	}

	@Override
	public List<Pack_Product> Select() {
		 List<Pack_Product>packproducts=em.createQuery("SELECT p FROM Pack_Product p group by (p.packs)",Pack_Product.class).getResultList();
		 
		// Map<Integer,List<Pack_Product>> result = packproducts.stream().collect(groupingBy(String::length)); 
		return packproducts;
	}
	

}
