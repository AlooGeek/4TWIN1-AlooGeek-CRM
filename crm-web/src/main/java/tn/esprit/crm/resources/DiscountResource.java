package tn.esprit.crm.resources;


import java.sql.Date;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

	public Response DisplayDiscountList() {
		 
		return Response.status(Status.OK).entity(discloc.selectAll()).build();

		}
	
		
		@PUT
		@Path("{id}")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateDiscount(@PathParam(value="id") Long id) {
			if(id!=0) {
					Discount d=new Discount();
					d.setId(id);
					d.setName("nyech");
					d.setReduction_amount(50);
					d.setDescription("ntestiw fel update bel postman");
					d.setStartdate(new Date(01,01,2019));
					d.setEnddate(new Date(01,01,2040));
					discloc.update(d);
					return Response.status(Status.ACCEPTED).entity("Discount Updated").build();
		}
			
			return Response.status(Status.NOT_MODIFIED).entity("Discount n est pas modifie").build();

		}
	
		@DELETE
		@Path("{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteDiscount(@PathParam(value="id") Long id) {
			
			if (discloc.remove(id)) {
			return Response.status(Status.GONE).entity("Discount deleted"+id).build();
			}
			return Response.status(Status.NOT_FOUND).entity("Not found Discount").build();
			
		}
		
		
		
		
}
