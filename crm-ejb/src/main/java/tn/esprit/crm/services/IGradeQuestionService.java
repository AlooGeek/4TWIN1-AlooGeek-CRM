package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.GradeAnswer;
import tn.esprit.crm.entities.GradeQuestion;

@Local
public interface IGradeQuestionService {
	
public GradeQuestion save(GradeQuestion gradeQuestion);
	
	public GradeQuestion update(GradeQuestion gradeQuestion);
	
	public List<GradeQuestion> selectAll();
	
	public List<GradeQuestion> selectBy(String param, String value);
	
	public List<GradeQuestion> selectAll(String sortField, String sort);
	
	public GradeQuestion getById(Long id);
	
	public void remove(Long id);
	
	public GradeQuestion findOne(String paramName, Object paramValue);
	
	public GradeQuestion findOne(String[] paramNames, Object[] paramValues);
	
	public Boolean addGradeQuestion(GradeQuestion gradeQuestion);
	
	public GradeQuestion changeGradeQuestion(GradeQuestion gradeQuestion);
	
	public void deleteGradeQuestion(GradeQuestion gradeQuestion);
	

	
	

}