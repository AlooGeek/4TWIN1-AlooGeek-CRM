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

	private final String statusstart="{\"statusres\":\"";
	private final String statusEnd="\"}";

	@EJB
	IOfferService OffService; 
	@EJB
	IUserService userService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("ROLE_ADMIN")
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
		@RolesAllowed("ROLE_ADMIN")
	public Response UpdateOffer(@PathParam(value="OffCode") String OffCode,Offer o) {
			
				if(OffCode!=null) {
					
					OffService.update(o);
					return Response.status(Status.ACCEPTED).entity(statusstart+"Offer Updated"+statusEnd).build();

				
			
		}
			
			return Response.status(Status.NOT_MODIFIED).entity("Offer n est pas modifie").build();

		}
	
		@DELETE
		@Path("{OffCode}")
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("ROLE_ADMIN")
		public Response deleteOffer(@PathParam(value="OffCode") String OffCode) {
			//set offer code in user already affected null
			OffService.setUsersNull();
			if (OffService.Delete(OffCode)) {
			return Response.status(Status.OK).entity(statusstart+"Offer deleted"+OffCode+statusEnd).build();
			}
			return Response.status(Status.NOT_FOUND).entity("Not found Offer").build();
			
		}
		
		@Path("bestusers")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("ROLE_ADMIN")
		public Response DisplayScoredUsers() {
			return Response.status(Status.OK).entity(OffService.getBestUsers()).build();

			}
		
		@Path("bestuserswithoffer")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("ROLE_ADMIN")
		public Response DisplayOfferedUsers() {
			return Response.status(Status.OK).entity(OffService.getBestUsersWithOffer()).build();

			}
		
		
		
		@PUT
		@Path("/affected/{OffCode}")
		@Consumes(MediaType.APPLICATION_JSON)
		@RolesAllowed("ROLE_ADMIN")
		public Response AddOfferToUser(@PathParam(value="OffCode") String OffCode) {
			if(OffCode!=null) {
				
				OffService.AddOfferToUser(OffCode);
					return Response.status(Status.ACCEPTED).entity(statusstart+"Offer affected"+statusEnd).build();
		}
			
			return Response.status(Status.NOT_MODIFIED).entity("Offer not affected").build();

		}
		
		@PUT
		@Path("/UpdateScore/{username}")
		@PermitAll
		@Consumes(MediaType.APPLICATION_JSON)
		public Response UpdateScore(@PathParam(value="username") String username) {
			if(username!=null) {
				
				OffService.AddScoreToUser(username);
					return Response.status(Status.ACCEPTED).entity(statusstart+"User Score Updated"+statusEnd).build();
		}
			
			return Response.status(Status.NOT_MODIFIED).entity("Score not updated").build();

		}
		
		
		
		
		@Path("checkOffer/{OffCode}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@PermitAll
		public Response CheckOffer(@PathParam(value="OffCode") String code) {
			try {
			return Response.status(Status.OK).entity(OffService.VerifyCoupon(code)).build();
				
			}
			catch (Exception e) {
				return Response.status(Status.OK).entity(statusstart+"Not Found"+statusEnd).build();

			}
			}
		
	
}
