package tn.esprit.crm.security;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tn.esprit.crm.entities.User;
import tn.esprit.crm.services.IUserService;

public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Inject
	HttpServletRequest request;
	
	@EJB
	IUserService userService;

	@Override
	public User getAuthenticated() {
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		return userService.findOne("username", username);		
		
	}

}
