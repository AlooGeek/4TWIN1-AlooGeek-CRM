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

import tn.esprit.crm.entities.Product;
import tn.esprit.crm.services.ICategoryService;
import tn.esprit.crm.services.IDiscountService;
import tn.esprit.crm.services.IProductService;

@Path("product")
public class ProductRessource {

	@EJB
	IProductService servprod;
	@EJB
	IDiscountService servdisc;
	@EJB
	ICategoryService servcat;
	
	@PermitAll
	@POST
	@Path("{IdCategorie}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddProduct(@PathParam(value="IdCategorie") Long IdCategorie,Product p) {
		
		p.setCategory(servcat.getById(IdCategorie));
		
		if (servcat.getById(IdCategorie)!=null) {
		servprod.save(p);
		return  Response.status(Status.OK).entity("Product Added : "+p).build();
		}
		return Response.status(Status.NOT_FOUND).entity("Category Not Found!").build();
	}

	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response DisplayProductList() {
		return Response.status(Status.OK).entity(servprod.selectAll()).build();
		

		}
	@PermitAll
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AfficheProductParId(@PathParam(value="id") Long id) {
		return Response.status(Status.OK).entity(servprod.getById(id)).build();
		

		}
	@PermitAll
	@GET
	@Path("{param}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response AfficheProductParamValue(@PathParam(value="param") String param,@PathParam(value="value") String value) {
		return Response.status(Status.OK).entity(servprod.selectBy(param, value)).build();
		

		}
	
	
	
	@PermitAll
	@PUT
	@Path("{id}/{id_discount}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
public Response UpdateProduct(@PathParam(value="id") Long id,@PathParam(value="id_discount") Long id_discount,Product p) {

		if (id!=0) {					
			p.setDiscount(servdisc.getById(id_discount));
			p.setCategory(servprod.getById(id).getCategory());
			p.setNewprice(p.getUnitPrice()-((( p.getUnitPrice()*servdisc.getById(id_discount).getReduction_amount())/100)));

			servprod.update(p);
			
			if (servprod.getById(p.getId()).getQte()!=0) {
				servprod.ActivateDispo(p.getId());
			}else {
				servprod.DesactivateDispo(p.getId());
			}
		return Response.status(Status.ACCEPTED).entity("Product "+id+" Updated").build();
		}
	
		return Response.status(Status.NOT_MODIFIED).entity("Product Not Updated").build();

	}

	
	
	
	
	@PermitAll
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStore(@PathParam(value="id") Long id) {
		
		try {
		if (servprod.remove(id)) {
		return Response.status(Status.GONE).entity("Product "+id+" Deleted").build();
		}
		}catch(Exception e) {
			return Response.status(Status.NOT_FOUND).entity("The Product that you try to delete is afected to a store ").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Product Not Found").build();
		
	}
	
}
