package tn.esprit.crm.resources;

import java.sql.Date;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
import tn.esprit.crm.entities.Offer;
import tn.esprit.crm.entities.User;
import tn.esprit.crm.services.IOfferService;
import tn.esprit.crm.services.IUserService;

@Path("offer")
public class OfferRessource {

	@EJB
	IOfferService OffService; 
	@EJB
	IUserService userService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response AddOffer(Offer o) {
		
		
		OffService.save(o);
		return  Response.status(Status.OK).entity("Your offer has been added").build();
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response DisplayOfferList() {
		 
		return Response.status(Status.OK).entity(OffService.selectAll()).build();

		}
	
		
		@PUT
		@Path("{OffCode}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("admin")
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
		@RolesAllowed("admin")
		public Response deleteOffer(@PathParam(value="OffCode") String OffCode) {
			//set offer code in user already affected null
			OffService.setUsersNull();
			if (OffService.Delete(OffCode)) {
			return Response.status(Status.GONE).entity("Offer deleted"+OffCode).build();
			}
			return Response.status(Status.NOT_FOUND).entity("Not found Offer").build();
			
		}
		
		@Path("bestusers")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("admin")
		public Response DisplayScoredUsers() {
			return Response.status(Status.OK).entity(OffService.getBestUsers()).build();

			}
		
		@Path("bestuserswithoffer")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("admin")
		public Response DisplayOfferedUsers() {
			return Response.status(Status.OK).entity(OffService.getBestUsersWithOffer()).build();

			}
		
		
		
		@PUT
		@Path("/affected/{OffCode}")
		@Consumes(MediaType.APPLICATION_JSON)
		@RolesAllowed("admin")
		public Response AddOfferToUser(@PathParam(value="OffCode") String OffCode) {
			if(OffCode!=null) {
				
				OffService.AddOfferToUser(OffCode);
					return Response.status(Status.ACCEPTED).entity("Offer affected").build();
		}
			
			return Response.status(Status.NOT_MODIFIED).entity("Offer not affected").build();

		}
		
		@PUT
		@Path("/UpdateScore/{idUser}")
		@RolesAllowed("admin")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response UpdateScore(@PathParam(value="idUser") Long idUser) {
			if(idUser!=0) {
				
				OffService.AddScoreToUser(idUser);
					return Response.status(Status.ACCEPTED).entity("User Score Updated").build();
		}
			
			return Response.status(Status.NOT_MODIFIED).entity("Score not updated").build();

		}
		
	
}
