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
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddOffer(Offer o) {
		
		
		OffService.save(o);
		return  Response.status(Status.OK).entity("Your offer has been added").build();
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayOfferList() {
		 
		return Response.status(Status.OK).entity(OffService.selectAll()).build();

		}
	
		
		@PUT
		@Path("{OffCode}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateOffer(@PathParam(value="OffCode") String OffCode,Offer o) {
			
				if(OffCode!=null) {
					
					OffService.update(o);
					return Response.status(Status.ACCEPTED).entity("Offer Updated").build();

				
			
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
