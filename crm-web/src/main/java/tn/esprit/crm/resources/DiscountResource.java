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
import tn.esprit.crm.services.IDiscountService;

@Path("discount")
public class DiscountResource {

	@EJB
	IDiscountService discloc;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	
	public Response AddDiscount(Discount d) {
		
		discloc.save(d);
		return  Response.status(Status.OK).entity("votre discount a ete ajouté").build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response DisplayDiscountList() {
		 
		return Response.status(Status.OK).entity(discloc.selectAll()).build();

		}
	
		
		@PUT
		@Path("{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("admin")
	public Response UpdateDiscount(@PathParam(value="id") Long id,Discount d) {
			if(id!=0) {
				discloc.update(d);
					return Response.status(Status.ACCEPTED).entity("Discount Updated").build();
		}
			
			return Response.status(Status.NOT_MODIFIED).entity("Discount n est pas modifie").build();

		}
	
		@DELETE
		@Path("{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("admin")
		public Response deleteDiscount(@PathParam(value="id") Long id) {
			
			if (discloc.remove(id)) {
			return Response.status(Status.GONE).entity("Discount deleted"+id).build();
			}
			return Response.status(Status.NOT_FOUND).entity("Not found Discount").build();
			
		}
		
		@DELETE
		@Path("deleteExpired")
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("admin")
	public Response deleteExpiredDiscount() {
			
			if (discloc.DeleteExpiredDiscount()) {
			return Response.status(Status.GONE).entity("Expired discount deleted").build();
			}
			return Response.status(Status.NOT_FOUND).entity("Not found Discount").build();
			
		}
		
		@GET
		@Path("statistiqueDiscount")
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("admin")
		public Response DisplayStatList() {
			 
			return Response.status(Status.OK).entity(discloc.StatistiqueDiscount()).build();

			}
		
		
		
}
