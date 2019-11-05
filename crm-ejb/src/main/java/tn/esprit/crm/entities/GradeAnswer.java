package tn.esprit.crm.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity

public class GradeAnswer  {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private boolean type ;
	
	@ManyToOne
	@JoinColumn(name = "fk_user")
	public User user;
	
	@ManyToOne
	@JoinColumn(name = "fk_answer")
	public Answer answer;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}


}
