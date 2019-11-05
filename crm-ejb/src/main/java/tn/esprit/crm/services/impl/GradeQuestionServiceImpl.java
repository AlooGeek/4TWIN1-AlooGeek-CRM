package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import lombok.Data;
import tn.esprit.crm.dao.IGradeQuestionDao;
import tn.esprit.crm.dao.impl.GradeQuestionDaoImpl;
import tn.esprit.crm.entities.GradeQuestion;
import tn.esprit.crm.entities.Question;
import tn.esprit.crm.services.IGradeQuestionService;
import tn.esprit.crm.services.IQuestionService;
import tn.esprit.crm.services.IUserService;
import tn.esprit.crm.dao.impl.GradeQuestionDaoImpl;

@Stateless
public class GradeQuestionServiceImpl implements IGradeQuestionService {
	@PersistenceContext(unitName="crm-ejb")
	@EJB
	 IGradeQuestionDao GradeQuestionDao;
	@EJB
	 IQuestionService questionService;
	@EJB
	 IUserService userService;

	@Override
	public GradeQuestion save(GradeQuestion gradeQuestion) {
		return GradeQuestionDao.save(gradeQuestion);
	}

	@Override
	public GradeQuestion update(GradeQuestion gradeQuestion) {
		return GradeQuestionDao.update(gradeQuestion);
	}

	@Override
	public List<GradeQuestion> selectAll() {
		return GradeQuestionDao.selectAll();
	}

	@Override
	public List<GradeQuestion> selectBy(String param, String value) {
		return GradeQuestionDao.selectBy(param, value);
	}

	@Override
	public List<GradeQuestion> selectAll(String sortField, String sort) {
		return GradeQuestionDao.selectAll(sortField, sort);
	}

	@Override
	public GradeQuestion getById(Long id) {
		return GradeQuestionDao.getById(id);
	}

	@Override
	public void remove(Long id) {
		GradeQuestionDao.remove(id);
		
	}

	@Override
	public GradeQuestion findOne(String paramName, Object paramValue) {
		return GradeQuestionDao.findOne(paramName, paramValue);
	}

	@Override
	public GradeQuestion findOne(String[] paramNames, Object[] paramValues) {
		return GradeQuestionDao.findOne(paramNames, paramValues);	
	}
	
	@Override
	public Boolean addGradeQuestion(GradeQuestion gradeQuestion) {
		Question question=questionService.getById(gradeQuestion.getQuestion().getId());
		if((userService.getById(gradeQuestion.getUser().getId())!=null)&&(question!=null)) {
			return false;
		}
		else {
			this.save(gradeQuestion);
			questionService.updateScore(question, gradeQuestion.isType());
			return true;
		}
	}
	
	@Override
	public GradeQuestion changeGradeQuestion(GradeQuestion gradeQuestion){
		Question question=questionService.getById(gradeQuestion.getQuestion().getId());
		this.update(gradeQuestion);
		questionService.updateScore(question, gradeQuestion.isType());
		questionService.updateScore(question, gradeQuestion.isType());
		return gradeQuestion;
	}
	
	@Override
	public void deleteGradeQuestion(GradeQuestion gradeQuestion){
		Question question=questionService.getById(gradeQuestion.getQuestion().getId());
		questionService.updateScore(question, !gradeQuestion.isType());
		this.remove(gradeQuestion.getId());
	}
	
	
	
}