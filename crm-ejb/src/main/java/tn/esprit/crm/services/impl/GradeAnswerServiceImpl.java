package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import lombok.Data;
import tn.esprit.crm.dao.IGradeAnswerDao;
import tn.esprit.crm.dao.impl.GradeAnswerDaoImpl;
import tn.esprit.crm.entities.GradeAnswer;
import tn.esprit.crm.entities.GradeQuestion;
import tn.esprit.crm.entities.Question;
import tn.esprit.crm.entities.Answer;
import tn.esprit.crm.services.IAnswerService;
import tn.esprit.crm.services.IGradeAnswerService;
import tn.esprit.crm.services.IUserService;
import tn.esprit.crm.dao.impl.GradeAnswerDaoImpl;

@Stateless
public class GradeAnswerServiceImpl implements IGradeAnswerService {
	@PersistenceContext(unitName="crm-ejb")
	@EJB
	private IGradeAnswerDao GradeAnswerDao;
	@EJB
	private IAnswerService answerService;
	@EJB
	private IUserService userService;

	@Override
	public GradeAnswer save(GradeAnswer gradeAnswer) {
		return GradeAnswerDao.save(gradeAnswer);
	}

	@Override
	public GradeAnswer update(GradeAnswer gradeAnswer) {
		return GradeAnswerDao.update(gradeAnswer);
	}

	@Override
	public List<GradeAnswer> selectAll() {
		return GradeAnswerDao.selectAll();
	}

	@Override
	public List<GradeAnswer> selectBy(String param, String value) {
		return GradeAnswerDao.selectBy(param, value);
	}

	@Override
	public List<GradeAnswer> selectAll(String sortField, String sort) {
		return GradeAnswerDao.selectAll(sortField, sort);
	}

	@Override
	public GradeAnswer getById(Long id) {
		return GradeAnswerDao.getById(id);
	}

	@Override
	public void remove(Long id) {
		GradeAnswerDao.remove(id);
		
	}

	@Override
	public GradeAnswer findOne(String paramName, Object paramValue) {
		return GradeAnswerDao.findOne(paramName, paramValue);
	}

	@Override
	public GradeAnswer findOne(String[] paramNames, Object[] paramValues) {
		return GradeAnswerDao.findOne(paramNames, paramValues);	
	}
	
	@Override
	public Boolean addGradeAnswer(GradeAnswer gradeAnswer) {
		Answer answer=answerService.getById(gradeAnswer.getAnswer().getId());
		if((userService.getById(gradeAnswer.getUser().getId())!=null)&&(answer!=null)) {
			return false;
		}
		else {
			this.save(gradeAnswer);
			answerService.updateScore(answer, gradeAnswer.isType());
			return true;
		}
	}
	
	@Override
	public GradeAnswer changeGradeAnswer(GradeAnswer gradeAnswer){
		Answer answer=answerService.getById(gradeAnswer.getAnswer().getId());
		this.update(gradeAnswer);
		answerService.updateScore(answer, gradeAnswer.isType());
		answerService.updateScore(answer, gradeAnswer.isType());
		return gradeAnswer;
	}
	
	@Override
	public void deleteGradeAnswer(GradeAnswer gradeAnswer){
		Answer answer=answerService.getById(gradeAnswer.getAnswer().getId());
		answerService.updateScore(answer, !gradeAnswer.isType());
		this.remove(gradeAnswer.getId());
	}
	
	
	
	
	
}