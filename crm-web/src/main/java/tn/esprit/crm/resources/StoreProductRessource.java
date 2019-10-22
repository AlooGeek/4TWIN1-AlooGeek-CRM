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
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.entities.Store;
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
	
	
	@GET
	@Path("{id_store}/{id_product}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddStoreProduct(@PathParam(value="id_store") Long id_store,@PathParam(value="id_product") Long id_product) {
		StoreProduct sp=new StoreProduct();
		sp.setStores(servstore.getById(id_store));
		sp.setProducts(servpro.getById(id_product));
		sp.setQte(50);
		sp.setDateEntry(new Date(1996-1900,5,25));
		servsp.save(sp);
		return  Response.status(Status.OK).entity("Store Product Added : "+sp).build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayProductList() {
		return Response.status(Status.OK).entity(servsp.selectAll()).build();
		

		}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
public Response UpdateProduct(@PathParam(value="id") Long id) {

		if (id!=null) {
			StoreProduct p=new StoreProduct();			
			p=servsp.getById(id);
			p.setQte(250);
			p.setDateEntry(new Date(25,02,2018));
			servsp.update(p);
		return Response.status(Status.ACCEPTED).entity("Store Product "+id+" Updated").build();
		}
	
		return Response.status(Status.NOT_MODIFIED).entity("Store Product Not Updated").build();

	}

	
	
	
	
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStore(@PathParam(value="id") Long id) {
		
		if (servsp.remove(id)) {
		return Response.status(Status.GONE).entity("Store Product "+id+" Deleted").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Store Product Not Found").build();
		
	}
}
