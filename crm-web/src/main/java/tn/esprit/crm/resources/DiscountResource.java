package tn.esprit.crm.resources;


import java.sql.Date;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.services.IDiscountService;

@Path("discount")
public class DiscountResource {

	@EJB
	IDiscountService discloc;
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Discount add () {
		Discount d=new Discount();
		d.setStartdate(new Date(01,01,2001));
		d.setEnddate(new Date(01,01,2001));
		discloc.save(d);
		return d;
	}
	
	
}
