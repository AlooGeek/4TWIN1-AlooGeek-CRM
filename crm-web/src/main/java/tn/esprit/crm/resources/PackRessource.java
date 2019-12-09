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

import tn.esprit.crm.dao.IPackDao;
import tn.esprit.crm.entities.Pack;
import tn.esprit.crm.services.IPackService;

@Path("pack")
public class PackRessource {

	private final String statusstart="{\"statusres\":\"";
	private final String statusEnd="\"}";

	@EJB
	IPackService packService;
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response AddPack(Pack p) {
		packService.save(p);
		return  Response.status(Status.OK).entity("votre pack a ete ajouté").build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	
	public Response DisplayPackList() {
		 
		return Response.status(Status.OK).entity(packService.selectAll()).build();

		}
	
		
	@PUT
	@Path("{id}")
	@RolesAllowed("admin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdatePack(@PathParam(value="id") Long id,Pack p) {
			if(id!=0) {
				
					packService.update(p);
					return Response.status(Status.OK).entity(statusstart+"Pack Updated"+statusEnd).build();
		}
			
			return Response.status(Status.NOT_MODIFIED).entity("Pack n est pas modifie").build();

		}
	
		@DELETE
		@Path("{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@RolesAllowed("admin")
		public Response deletePack(@PathParam(value="id") Long id) {
			
			if (packService.remove(id)) {
			return Response.status(Status.GONE).entity("Pack deleted"+id).build();
			}
			return Response.status(Status.NOT_FOUND).entity("Not found Pack").build();
			
		}
		
		
}
