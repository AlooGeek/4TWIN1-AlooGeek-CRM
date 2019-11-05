package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import tn.esprit.crm.dao.IAnswerDao;
import tn.esprit.crm.dao.impl.AnswerDaoImpl;
import tn.esprit.crm.entities.Answer;
import tn.esprit.crm.entities.GradeAnswer;
import tn.esprit.crm.entities.Question;
import tn.esprit.crm.services.IAnswerService;
import tn.esprit.crm.services.IGradeAnswerService;
import tn.esprit.crm.services.IQuestionService;

@Stateless
public class AnswerServiceImpl implements IAnswerService {
	@PersistenceContext(unitName="crm-ejb")
	@EJB
	private IAnswerDao answerDao;
	
	@EJB
	private IQuestionService questionService;
	
	@EJB
	private IGradeAnswerService gradeAnswerService;


	@Override
	public Answer save(Answer Answer) {
		return answerDao.save(Answer);
	}

	@Override
	public Answer update(Answer Answer) {
		return answerDao.update(Answer);
	}

	@Override
	public List<Answer> selectAll() {
		return answerDao.selectAll();
	}

	@Override
	public List<Answer> selectBy(String param, String value) {
		return answerDao.selectBy(param, value);
	}

	@Override
	public List<Answer> selectAll(String sortField, String sort) {
		return answerDao.selectAll(sortField, sort);
	}

	@Override
	public Answer getById(Long id) {
		return answerDao.getById(id);
	}

	@Override
	public void remove(Long id) {
		answerDao.remove(id);
		
	}

	@Override
	public Answer findOne(String paramName, Object paramValue) {
		return answerDao.findOne(paramName, paramValue);
	}

	@Override
	public Answer findOne(String[] paramNames, Object[] paramValues) {
		return answerDao.findOne(paramNames, paramValues);	
	}
	
	@Override
	public Answer addAnswer(Answer answer) {
		Question question=questionService.getById(answer.getQuestion().getId());
			questionService.update(question);
			this.save(answer);
			return answer;

	}
	
	@Override
	public void deleteAnswer(Long id) {
		List<GradeAnswer> listGradeAnswer=gradeAnswerService.selectBy("fk_answer", Long.toString(id));
		listGradeAnswer.forEach(e->{
			gradeAnswerService.remove(e.getId());
		});
		answerDao.remove(id);
	}
	
	@Override
	public void updateScore(Answer answer,Boolean gradeAnswerType) {
		if(gradeAnswerType) {
			answer.setScore(answer.getScore()+1);
		}
		else {
			answer.setScore(answer.getScore()-1);
		}
		update(answer);
	}
	
}