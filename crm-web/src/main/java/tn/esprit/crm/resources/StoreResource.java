package tn.esprit.crm.resources;


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

import tn.esprit.crm.entities.Store;
import tn.esprit.crm.services.IStoreService;

@Path("store")
public class StoreResource {
	
	@EJB
	IStoreService storeserv;

	@RolesAllowed("ROLE_ADMIN")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddStore(Store s) {
		storeserv.save(s);
		return  Response.status(Status.OK).entity(storeserv.selectAll()).build();
	}

	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayStoreList() {
		return Response.status(Status.OK).entity(storeserv.selectAll()).build();
		}
	@PermitAll
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AfficheStoreParId(@PathParam(value="id") Long id) {
		return Response.status(Status.OK).entity(storeserv.getById(id)).build();
		

		}
	@PermitAll
	@GET
	@Path("{param}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AfficheStoreParamValue(@PathParam(value="param") String param,@PathParam(value="value") String value) {
		return Response.status(Status.OK).entity(storeserv.selectBy(param, value)).build();
		

		}
	
	
	@RolesAllowed("ROLE_ADMIN")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
public Response UpdateStore(Store s) {
	
				storeserv.update(s);
				return Response.status(Status.ACCEPTED).entity(storeserv.selectAll()).build();

	}

	
	
	
	
	@RolesAllowed("ROLE_ADMIN")
	@DELETE
	@Path("{id_store}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStore(@PathParam(value="id_store") Long id_store) {
		
		try {
		if (storeserv.remove(id_store)) {
		return Response.status(Status.OK).entity(storeserv.selectAll()).build();
		}
		}catch(Exception e) {
			return Response.status(Status.NOT_FOUND).entity("The store that you try to delete have products").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Store Not Found").build();
		
	}
}
