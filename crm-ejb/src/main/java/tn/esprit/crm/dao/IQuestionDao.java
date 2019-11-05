package tn.esprit.crm.dao;

import javax.ejb.Local;

import tn.esprit.crm.entities.Question;

@Local
public interface IQuestionDao extends IGenericDao<Question> {

}
