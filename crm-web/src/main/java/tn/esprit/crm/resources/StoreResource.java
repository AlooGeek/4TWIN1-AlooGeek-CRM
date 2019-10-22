package tn.esprit.crm.resources;


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

import tn.esprit.crm.entities.Store;
import tn.esprit.crm.services.IStoreService;

@Path("store")
public class StoreResource {
	
	@EJB
	IStoreService storeserv;

	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response AddStore() {
		Store s=new Store();
		s.setAddress("Taniour");
		s.setEmail("Ahmed@esprit.tn");
		s.setName("ooredoo");
		s.setPhoneNumber(23193908);
		storeserv.save(s);
		return  Response.status(Status.OK).entity("Store Added : "+s).build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayStoreList() {
		return Response.status(Status.OK).entity(storeserv.selectAll()).build();
		

		}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
public Response UpdateDiscount(@PathParam(value="id") Long id) {
	
		if (id!=0) {
				Store s1=new Store();
				s1=storeserv.getById(id);
				s1.setAddress("sidiselem");
				s1.setEmail("Email");
				s1.setPhoneNumber(25364856);
				s1.setName("telecom");
		
				storeserv.update(s1);
				return Response.status(Status.ACCEPTED).entity("Store "+id+" Updated").build();

		}
		
	
		
		return Response.status(Status.NOT_MODIFIED).entity("Store Not Updated").build();

	}

	
	
	
	
	
	@DELETE
	@Path("{id_store}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStore(@PathParam(value="id_store") Long id_store) {
		
		if (storeserv.remove(id_store)) {
		return Response.status(Status.GONE).entity("Store "+id_store+" Deleted").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Store Not Found").build();
		
	}
}
