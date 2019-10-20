package tn.esprit.crm.resources;


import java.sql.Date;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.services.IDiscountService;

@Path("discount")
public class DiscountResource {

	@EJB
	IDiscountService discloc;
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response AddDiscount() {
		Discount d=new Discount();
		d.setName("Postman Reduction");
		d.setReduction_amount(30);
		d.setDescription("ntestiw fel ajout bel postman");
		d.setStartdate(new Date(01,01,2001));
		d.setEnddate(new Date(01,01,2009));
		discloc.save(d);
		return  Response.status(Status.OK).entity("votre discount a ete ajouté").build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//(D) DisplayEmployeeList
	public Response DisplayDiscountList() {
		 System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA@@@@@@@@@");
	     System.out.println(Discount.class.getName());
		return Response.status(Status.OK).entity(discloc.selectAll()).build();
		

		}
	
	
	
}
