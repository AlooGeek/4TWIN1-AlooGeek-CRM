package tn.esprit.crm.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;



@Entity	
@Data
public class Role extends BaseEntity {
	private Long Id;
	private String roleName;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
	private List<User> usreList = new ArrayList<>();

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public void setUsreList(List<User> usreList) {
		this.usreList = usreList;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	
	
}
