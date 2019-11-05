package tn.esprit.crm.resources;


import java.sql.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.inject.Inject;
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
import tn.esprit.crm.entities.Question;
import tn.esprit.crm.security.AuthenticationService;
import tn.esprit.crm.services.impl.QuestionServiceImpl;

@Path("/question")
public class QuestionResource {

	@EJB
	QuestionServiceImpl questionService;
	
	@Inject
	private AuthenticationService authService;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public Question addQuestion(Question question) {
		question.setUser(authService.getAuthenticated());
		return questionService.addQuestion(question);
	}
	
	@GET
	@Path("/display-all")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Question> displayAllQuestions() { 
		return questionService.selectAll();	
	}
    
	@GET
	@Path("/display-by_title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Question> DisplayQuestionByTitle(@PathParam("title")String title) {
		return questionService.selectBy("title", title);
		
	}
	
	@GET
	@Path("/display-by-user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Question> DisplayQuestionByUser(@PathParam("id")long id) {
		return questionService.selectBy("fk_user", Long.toString(id));
	}
	
	@GET
	@Path("/display-by-question/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Question> DisplayQuestionByQuestion(@PathParam("id")long id) {
		return questionService.selectBy("fk_question", Long.toString(id));
		
	}
		
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Question Update(Question question) {
		return questionService.update(question);
	
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public void delete(@PathParam("id")long id) {
		questionService.deleteQuestion(id);	
	}
		
		
}