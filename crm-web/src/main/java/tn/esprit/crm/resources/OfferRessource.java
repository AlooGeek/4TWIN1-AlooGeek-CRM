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

import tn.esprit.crm.entities.Offer;
import tn.esprit.crm.services.IOfferService;

@Path("offer")
public class OfferRessource {

	@EJB
	IOfferService OffService; 
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response AddOffer() {
		Offer d=new Offer();
		d.setOffCode("Q5z4a1");
		d.setOffDescription("offre cadeau 100points");
		d.setOffStartDate(new Date(01,05,2019));
		d.setOffEndDate(new Date(01,01,2020));
		d.setOffName("offre cadeau");
		d.setOffReduction_amount(30);
		
		OffService.save(d);
		return  Response.status(Status.OK).entity("Your offer has been added").build();
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayOfferList() {
		 
		return Response.status(Status.OK).entity(OffService.selectAll()).build();

		}
	
		
		@PUT
		@Path("{OffCode}")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateOffer(@PathParam(value="OffCode") String OffCode) {
			for(int i=0 ; i<OffService.selectAll().size();i++) {
				if(OffService.selectAll().get(i).getOffCode().equals(OffCode)) {
					Offer d=new Offer();
					d.setOffCode((OffService.selectAll().get(i).getOffCode()));
					d.setOffDescription("Offer modification");
					d.setOffDescription("ntestiw fel update bel postman");
					d.setOffStartDate(new Date(01,01,2019));
					d.setOffEndDate(new Date(01,01,2040));
					d.setOffReduction_amount(50);
					OffService.update(d);
					return Response.status(Status.ACCEPTED).entity("Offer Updated").build();

				}
			
		}
			
			return Response.status(Status.NOT_MODIFIED).entity("Offer n est pas modifie").build();

		}
	
		@DELETE
		@Path("{OffCode}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteOffer(@PathParam(value="OffCode") String OffCode) {
			
			if (OffService.Delete(OffCode)) {
			return Response.status(Status.GONE).entity("Offer deleted"+OffCode).build();
			}
			return Response.status(Status.NOT_FOUND).entity("Not found Offer").build();
			
		}
	
	
}
