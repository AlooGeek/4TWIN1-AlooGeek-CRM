package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.entities.User;

@Local
public interface IDiscountService {
	
	public Discount save (Discount discount);
	public List<Discount> selectAll();
	public Discount update(Discount discount);
	public boolean remove(Long id);
	public Discount getById(Long id);
	public boolean DeleteExpiredDiscount ();
}
