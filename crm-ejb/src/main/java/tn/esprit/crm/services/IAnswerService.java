package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.Answer;

@Local
public interface IAnswerService {
	
public Answer save(Answer answer);
	
	public Answer update(Answer answer);
	
	public List<Answer> selectAll();
	
	public List<Answer> selectBy(String param, String value);
	
	public List<Answer> selectAll(String sortField, String sort);
	
	public Answer getById(Long id);
	
	public void remove(Long id);
	
	public Answer findOne(String paramName, Object paramValue);
	
	public Answer findOne(String[] paramNames, Object[] paramValues);
	
	public void updateScore(Answer answer,Boolean gradeAnswerType);
	
	public void deleteAnswer(Long id);
	
	public Answer addAnswer(Answer answer);

}