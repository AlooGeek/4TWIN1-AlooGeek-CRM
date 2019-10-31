package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.crm.entities.Product;



@Remote
public interface IProductService {
	
	public Product save(Product product);

	public Product update(Product product);
	
	public List<Product> selectAll();
	
	public List<Product> selectBy(String param, String value);
	
	public List<Product> selectAll(String sortField, String sort);
	
	public Product getById(Long id);
	
	public boolean remove(Long id);
	
	public int updateQte(Long id,int qte);
}
