package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.crm.entities.Store;


@Remote
public interface IStoreService {

public Store save(Store store);
	
	public Store update(Store store);
	
	public List<Store> selectAll();
	
	public List<Store> selectBy(String param, String value);
	
	public List<Store> selectAll(String sortField, String sort);
	
	public Store getById(Long id);

	public boolean remove(Long id);
	
	
}
