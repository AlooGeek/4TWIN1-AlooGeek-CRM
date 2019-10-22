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

import tn.esprit.crm.entities.Product;

import tn.esprit.crm.services.IProductService;

@Path("product")
public class ProductRessource {

	@EJB
	IProductService servprod;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response AddProduct() {
		Product p=new Product();
		p.setLabel("A50");
		p.setQte(20);
		p.setTva(18);
		p.setUnitPrice(950);
		servprod.save(p);
		return  Response.status(Status.OK).entity("Product Added : "+p).build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayProductList() {
		return Response.status(Status.OK).entity(servprod.selectAll()).build();
		

		}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
public Response UpdateProduct(@PathParam(value="id") Long id) {

		if (id!=null) {
			Product p=new Product();			
			p=servprod.getById(id);
			p.setLabel("hala");
			p.setQte(50);
			p.setTva(19);
			p.setUnitPrice(500);
		servprod.update(p);
		return Response.status(Status.ACCEPTED).entity("Product "+id+" Updated").build();
		}
	
		return Response.status(Status.NOT_MODIFIED).entity("Product Not Updated").build();

	}

	
	
	
	
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStore(@PathParam(value="id") Long id) {
		
		if (servprod.remove(id)) {
		return Response.status(Status.GONE).entity("Product "+id+" Deleted").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Product Not Found").build();
		
	}
	
}
