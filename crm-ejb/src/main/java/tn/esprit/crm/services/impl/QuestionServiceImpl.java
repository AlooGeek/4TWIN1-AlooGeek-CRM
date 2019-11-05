package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import tn.esprit.crm.dao.IQuestionDao;
import tn.esprit.crm.entities.Answer;
import tn.esprit.crm.entities.GradeQuestion;
import tn.esprit.crm.entities.Question;
import tn.esprit.crm.services.IAnswerService;
import tn.esprit.crm.services.IGradeQuestionService;
import tn.esprit.crm.services.IQuestionService;
import tn.esprit.crm.services.IUserService;

@Stateless
public class QuestionServiceImpl implements IQuestionService {
	
	
	@PersistenceContext(unitName="crm-ejb")
	@EJB
	private IQuestionDao QuestionDao;
	
	
	
	@EJB
	private IAnswerService answerService;
	
	@EJB
	private IGradeQuestionService gradeQuestionService;

	@Override
	public Question save(Question question) {
		//question.setFk_user(UserService.getById(question.getFk_user().getId()));
		
		return QuestionDao.save(question);
	}

	@Override
	public Question update(Question question) {
		return QuestionDao.update(question);
	}

	@Override
	public List<Question> selectAll() {
		return QuestionDao.selectAll();
	}

	@Override
	public List<Question> selectBy(String param, String value) {
		return QuestionDao.selectBy(param, value);
	}

	@Override
	public List<Question> selectAll(String sortField, String sort) {
		return QuestionDao.selectAll(sortField, sort);
	}

	@Override
	public Question getById(Long id) {
		return QuestionDao.getById(id);
	}

	@Override
	public void remove(Long id) {
		QuestionDao.remove(id);
		
	}

	@Override
	public Question findOne(String paramName, Object paramValue) {
		return QuestionDao.findOne(paramName, paramValue);
	}

	@Override
	public Question findOne(String[] paramNames, Object[] paramValues) {
		return QuestionDao.findOne(paramNames, paramValues);	
	}
	
	@Override
	public Question addQuestion(Question question) {
		if(this.findOne("title", question.getTitle())==null) {
			this.save(question);
			return  question;
		}
		else {
			return findOne("title", question.getTitle());
		}
	}
	
	@Override
	public void updateScore(Question question,Boolean gradeQuestionType) {
		if(gradeQuestionType) {
			question.setScore(question.getScore()+1);
		}
		else {
			question.setScore(question.getScore()-1);
		}
		update(question);
	}
	
	@Override
	public void validateQuestion(Question question) {
		question.setState("valid");
		QuestionDao.update(question);
	}

	
	@Override
	public void deleteQuestion(Long id) {
		//delete gradeQuestions
		List<GradeQuestion> listGradeQuestion=gradeQuestionService.selectBy("fk_question", Long.toString(id));
		listGradeQuestion.forEach(e->{
			gradeQuestionService.deleteGradeQuestion(e);
		});
		
		//delete Answers
		List<Answer> listAnswers= answerService.selectBy( "fk_question", Long.toString(id));
		listAnswers.forEach(e->{
			answerService.deleteAnswer(e.getId());
		});
		
		QuestionDao.remove(id);
	}


	

}