package tn.esprit.crm.resources;


import javax.annotation.security.PermitAll;
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
import tn.esprit.crm.entities.StoreProduct;
import tn.esprit.crm.services.IProductService;
import tn.esprit.crm.services.IStoreProductService;
import tn.esprit.crm.services.IStoreService;


@Path("storeproduct")
public class StoreProductRessource {
	
	@EJB
	IStoreProductService servsp;
	@EJB
	IStoreService servstore;
	@EJB
	IProductService servpro;
	
	
	@POST
	@Path("{id_store}/{id_product}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response AddStoreProduct(@PathParam(value="id_store") Long id_store,@PathParam(value="id_product") Long id_product,StoreProduct sp) {
		
		sp.setStores(servstore.getById(id_store));
		sp.setProducts(servpro.getById(id_product));
		
		if (servpro.getById(id_product).getQte()>=sp.getQte()) {
			
			servpro.updateQte(id_product,sp.getQte());
			servsp.save(sp);
			return  Response.status(Status.OK).entity("Store Product Added").build();
			
		}
	
		return  Response.status(Status.NOT_ACCEPTABLE).entity("la quantité saisie n'existe pas en stock").build();
		
	}
	

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayStoreProdList() {
		return Response.status(Status.OK).entity(servsp.selectAll()).build();
		

		}
	
	// Statistique 
	@GET
	@Path("statistic")
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayStatisticsp() {
		return Response.status(Status.OK).entity(servsp.StatisticsStore()).build();
		

		}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AfficheStoreProdParId(@PathParam(value="id") Long id) {
		return Response.status(Status.OK).entity(servsp.getById(id)).build();
		

		}
	
	@GET
	@Path("{param}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AfficheStoreProdParamValue(@PathParam(value="param") String param,@PathParam(value="value") String value) {
		return Response.status(Status.OK).entity(servsp.selectBy(param, value)).build();
		

		}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
public Response UpdateStoreProd(@PathParam(value="id") Long id,StoreProduct sp) {

		if (id!=null) {
			
			servsp.update(sp);
		return Response.status(Status.ACCEPTED).entity("Store Product "+id+" Updated").build();
		}
	
		return Response.status(Status.NOT_MODIFIED).entity("Store Product Not Updated").build();

	}

	
	
	
	
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStoreProd(@PathParam(value="id") Long id) {
		
		if (servsp.remove(id)) {
		return Response.status(Status.GONE).entity("Store Product "+id+" Deleted").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Store Product Not Found").build();
		
	}
}
