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
import tn.esprit.crm.entities.Answer;
import tn.esprit.crm.security.AuthenticationService;
import tn.esprit.crm.services.impl.AnswerServiceImpl;

@Path("/answer")
public class AnswerResource {

	@EJB
	AnswerServiceImpl answerService;
	
	@Inject
	private AuthenticationService authService;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public Answer addAnswer(Answer answer) {
		answer.setUser(authService.getAuthenticated());
		return answerService.addAnswer(answer);
	}
	
	@GET
	@Path("/display-all")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Answer> displayAllAnswers() { 
		return answerService.selectAll();	
	}
    
	@GET
	@Path("/display-by_title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Answer> DisplayAnswerByTitle(@PathParam("title")String title) {
		return answerService.selectBy("title", title);
		
	}
	
	@GET
	@Path("/display-by-user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Answer> DisplayAnswerByUser(@PathParam("id")long id) {
		return answerService.selectBy("fk_user", Long.toString(id));
	}
	
	@GET
	@Path("/display-by-question/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Answer> DisplayAnswerByQuestion(@PathParam("id")long id) {
		return answerService.selectBy("fk_question", Long.toString(id));
		
	}
		
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Answer Update(Answer answer) {
		return answerService.update(answer);
	
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public void delete(@PathParam("id")long id) {
		answerService.deleteAnswer(id);	
	}
		
		
}
