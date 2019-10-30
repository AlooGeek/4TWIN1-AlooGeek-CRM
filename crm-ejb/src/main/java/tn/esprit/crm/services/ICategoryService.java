package tn.esprit.crm.services;

import java.util.List;
import java.util.Locale.Category;

import javax.ejb.Local;

import tn.esprit.crm.entities.Categorie;


@Local
public interface ICategoryService {

	public Categorie save(Categorie category);
	
	public Categorie update(Categorie category);
	
	public List<Categorie> selectAll();
	
	public List<Categorie> selectBy(String param, String value);
	
	public List<Categorie> selectAll(String sortField, String sort);
	
	public Categorie getById(Long id);

	public boolean remove(Long id);


}
