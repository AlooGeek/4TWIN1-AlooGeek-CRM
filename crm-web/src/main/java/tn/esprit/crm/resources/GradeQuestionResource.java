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
import tn.esprit.crm.entities.GradeQuestion;
import tn.esprit.crm.services.impl.GradeQuestionServiceImpl;

@Path("/gradeQuestion")
public class GradeQuestionResource {

	@EJB
	GradeQuestionServiceImpl gradeQuestionService;
	
	
	@POST
	@Path("/add")
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean addGradeQuestion(GradeQuestion gradeQuestion) {
		return gradeQuestionService.addGradeQuestion(gradeQuestion);
	}
	
	@GET
	@Path("/display-all")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<GradeQuestion> displayAllGradeQuestions() { 
		return gradeQuestionService.selectAll();	
	}
    

	
	@GET
	@Path("/display-by-user/{id}")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<GradeQuestion> DisplayGradeQuestionByUser(@PathParam("id")long id) {
		return gradeQuestionService.selectBy("fk_user", Long.toString(id));
	}
	
	@GET
	@Path("/display-by-question/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<GradeQuestion> DisplayGradeQuestionByQuestion(@PathParam("id")long id) {
		return gradeQuestionService.selectBy("fk_question", Long.toString(id));
		
	}
		
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public GradeQuestion Update(GradeQuestion gradeQuestion) {
		return gradeQuestionService.changeGradeQuestion(gradeQuestion);
	
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public void delete(GradeQuestion gradeQuestion) {
		gradeQuestionService.deleteGradeQuestion(gradeQuestion);	
	}
		
		
}