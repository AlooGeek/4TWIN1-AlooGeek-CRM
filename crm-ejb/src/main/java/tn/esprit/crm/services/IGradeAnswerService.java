package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.GradeAnswer;

@Local
public interface IGradeAnswerService {
	
public GradeAnswer save(GradeAnswer gradeAnswer);
	
	public GradeAnswer update(GradeAnswer gradeAnswer);
	
	public List<GradeAnswer> selectAll();
	
	public List<GradeAnswer> selectBy(String param, String value);
	
	public List<GradeAnswer> selectAll(String sortField, String sort);
	
	public GradeAnswer getById(Long id);
	
	public void remove(Long id);
	
	public GradeAnswer findOne(String paramName, Object paramValue);
	
	public GradeAnswer findOne(String[] paramNames, Object[] paramValues);
	
	public Boolean addGradeAnswer(GradeAnswer gradeAnswer);
	
	public void deleteGradeAnswer(GradeAnswer gradeAnswer);
	
	public GradeAnswer changeGradeAnswer(GradeAnswer gradeAnswer);

}