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

import tn.esprit.crm.entities.Categorie;
import tn.esprit.crm.services.ICategoryService;

@Path("category")
public class CategoryResource {
	
@EJB 
ICategoryService servcat;


@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response AddCategory (Categorie c) {
	
	servcat.save(c);
	return  Response.status(Status.OK).entity("Category Added : "+c).build();
}


@GET
@Produces(MediaType.APPLICATION_JSON)
public Response DisplayCategoryList() {
	return Response.status(Status.OK).entity(servcat.selectAll()).build();
	

	}

@GET
@Path("{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response AfficheCategoryParId(@PathParam(value="id") Long id) {
	return Response.status(Status.OK).entity(servcat.getById(id)==null?"Category not found":servcat.getById(id)).build();
	

	}

@GET
@Path("{param}/{value}")
@Produces(MediaType.APPLICATION_JSON)
public Response AfficheCategoryParamValue(@PathParam(value="param") String param,@PathParam(value="value") String value) {
	return Response.status(Status.OK).entity(servcat.selectBy(param, value)).build();
	

	}

@PUT
@Path("{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response UpdateCategory(@PathParam(value="id") Long id,Categorie c) {

	if (id!=0) {					
		servcat.update(c);
	return Response.status(Status.ACCEPTED).entity("Category "+id+" Updated").build();
	}

	return Response.status(Status.NOT_MODIFIED).entity("Category Not Updated").build();

}






@DELETE
@Path("{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response deleteStore(@PathParam(value="id") Long id) {
	
	if (servcat.remove(id)) {
	return Response.status(Status.GONE).entity("Category "+id+" Deleted").build();
	}
	return Response.status(Status.NOT_FOUND).entity("Category Not Found").build();
	
}


}
