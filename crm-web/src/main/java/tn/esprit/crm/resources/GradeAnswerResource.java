package tn.esprit.crm.resources;


import java.sql.Date;
import java.util.List;

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
import tn.esprit.crm.entities.GradeAnswer;
import tn.esprit.crm.services.impl.GradeAnswerServiceImpl;

@Path("/gradeAnswer")
public class GradeAnswerResource {

	@EJB
	GradeAnswerServiceImpl gradeAnswerService;
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public Boolean addGradeAnswer(GradeAnswer gradeAnswer) {
		return gradeAnswerService.addGradeAnswer(gradeAnswer);
	}
	
	@GET
	@Path("/display-all")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<GradeAnswer> displayAllGradeAnswers() { 
		return gradeAnswerService.selectAll();	
	}
    

	
	@GET
	@Path("/display-by-user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<GradeAnswer> DisplayGradeAnswerByUser(@PathParam("id")long id) {
		return gradeAnswerService.selectBy("fk_user", Long.toString(id));
	}
	
	@GET
	@Path("/display-by-answer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<GradeAnswer> DisplayGradeAnswerByAnswer(@PathParam("id")long id) {
		return gradeAnswerService.selectBy("fk_answer", Long.toString(id));
		
	}
		
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public GradeAnswer Update(GradeAnswer gradeAnswer) {
		return gradeAnswerService.changeGradeAnswer(gradeAnswer);
	
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public void delete(GradeAnswer gradeAnswer) {
		gradeAnswerService.deleteGradeAnswer(gradeAnswer);	
	}
		
		
}
