package tn.esprit.crm.resources;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import tn.esprit.crm.entities.Pack;
import tn.esprit.crm.entities.Pack_Product;
import tn.esprit.crm.entities.StoreProduct;
import tn.esprit.crm.services.IPackProductService;
import tn.esprit.crm.services.IPackService;
import tn.esprit.crm.services.IProductService;

@Path("packproducts")
public class PackProductRessource {

	@EJB
	IPackProductService packproductservice;
	
	@EJB
	IProductService productserv;
	
	@EJB
	IPackService packserv;
	
	@POST
	@Path("{idpack}/{idproduct}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response AddPack_Product(@PathParam(value="idpack") Long idpack,@PathParam(value="idproduct") Long idproduct,Pack_Product pp) throws ParseException {
		//pp=new Pack_Product();
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd ");
		String inputString1 =myFormat.format(packserv.getById(idpack).getPackEndDate());
		String inputString2 =myFormat.format(packserv.getById(idpack).getPackStartDate());
		 Date date1 =myFormat.parse(inputString1);
		    Date date2 =  myFormat.parse(inputString2);
		    int diff = (int) (date1.getDate() - date2.getDate());
		    int days=(int) TimeUnit.DAYS.convert(diff, TimeUnit.DAYS);
		/*int period=0;
		period=packserv.getById(idpack).getPackEndDate().getDate()-packserv.getById(idpack).getPackStartDate().getDate();*/
		pp.setPacks(packserv.getById(idpack));
		pp.setProductss(productserv.getById(idproduct));
		pp.setPrix(productserv.getById(idproduct).getUnitPrice()-((productserv.getById(idproduct).getUnitPrice()*packserv.getById(idpack).getReduction_amount())/100));
		pp.setPeriod(days);
		packproductservice.save(pp);
		return  Response.status(Status.OK).entity("votre pack produit a ete ajouté").build();
	}

	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response DisplayPackProductList() {
		return Response.status(Status.OK).entity(packproductservice.selectAll()).build();
		

		}
	

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
public Response UpdatePP(@PathParam(value="id") Long id,Pack_Product p) {

		if (id!=null) {
			p.setPacks(packproductservice.getById(id).getPacks());
			p.setProductss(packproductservice.getById(id).getProductss());
			packproductservice.update(p);
		return Response.status(Status.ACCEPTED).entity("Pack Product "+id+" Updated").build();
		}
	
		return Response.status(Status.NOT_MODIFIED).entity("Pack Product Not Updated").build();

	}


	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response deletePackProduct(@PathParam(value="id") Long id) {
		
		if (packproductservice.remove(id)) {
		return Response.status(Status.GONE).entity("Pack product deleted"+id).build();
		}
		return Response.status(Status.NOT_FOUND).entity("Not found Pack product").build();
		
	}
	
	@Path("packprice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response DisplayPrice() {
		return Response.status(Status.OK).entity(packproductservice.getPriceOfPack()).build();

		}
	
	
	@Path("liste")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response DisplayList() {
		System.out.println("aaaaaa"+packproductservice.Select());
		return Response.status(Status.OK).entity(packproductservice.Select()).build();
		}
	
	
}
