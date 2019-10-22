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

import tn.esprit.crm.dao.IPackDao;
import tn.esprit.crm.entities.Pack;
import tn.esprit.crm.services.IPackService;

@Path("pack")
public class PackRessource {

	@EJB
	IPackService packService;
	

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response AddPack() {
		Pack p=new Pack();
		p.setPackName("Postman pack");
		p.setPackDescription("ntestiw fel ajout pack bel postman");
		p.setPackStartDate(new Date(2001-1900,01,15));
		p.setPackEndDate(new Date(2009-1900,01,19));
		packService.save(p);
		return  Response.status(Status.OK).entity("votre pack a ete ajouté").build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response DisplayPackList() {
		 
		return Response.status(Status.OK).entity(packService.selectAll()).build();

		}
	
		
		@PUT
		@Path("{id}")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.APPLICATION_JSON)
	public Response UpdatePack(@PathParam(value="id") Long id) {
			if(id!=0) {
					Pack p=new Pack();
					p.setId(id);
					p.setPackName("nyech");
					p.setPackDescription("ntestiw fel update bel postman");
					p.setPackStartDate(new Date(01,01,2019));
					p.setPackEndDate(new Date(01,01,2040));
					packService.update(p);
					return Response.status(Status.ACCEPTED).entity("Pack Updated").build();
		}
			
			return Response.status(Status.NOT_MODIFIED).entity("Pack n est pas modifie").build();

		}
	
		@DELETE
		@Path("{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deletePack(@PathParam(value="id") Long id) {
			
			if (packService.remove(id)) {
			return Response.status(Status.GONE).entity("Pack deleted"+id).build();
			}
			return Response.status(Status.NOT_FOUND).entity("Not found Pack").build();
			
		}
		
		
}
