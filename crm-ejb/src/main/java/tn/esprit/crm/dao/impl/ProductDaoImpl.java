package tn.esprit.crm.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import tn.esprit.crm.dao.IProductDao;
import tn.esprit.crm.entities.Product;

@Stateless
public class ProductDaoImpl extends GenericDaoImpl<Product> implements IProductDao {

	@Override
	public int updateQte(Long id,int qte) {
		
		Query query = em.createQuery("update Product p set p.qte=p.qte-:qte where p.id=:id");
		query.setParameter("qte", qte);
		query.setParameter("id", id);
		return query.executeUpdate();
		
		
	}

}
