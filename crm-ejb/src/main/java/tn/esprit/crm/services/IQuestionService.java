package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.Question;

@Local
public interface IQuestionService {
	
	public Question save(Question question);
	
	public Question update(Question question);
	
	public List<Question> selectAll();
	
	public List<Question> selectBy(String param, String value);
	
	public List<Question> selectAll(String sortField, String sort);
	
	public Question getById(Long id);
	
	public void remove(Long id);
	
	public Question findOne(String paramName, Object paramValue);
	
	public Question findOne(String[] paramNames, Object[] paramValues);
	
	public void deleteQuestion(Long id);
	
	public void updateScore(Question question,Boolean gradeQuestionType);
	
	public void validateQuestion(Question question);
	
	public Question addQuestion(Question question);

	//Question updateQuestion(Question question);
	
	

}