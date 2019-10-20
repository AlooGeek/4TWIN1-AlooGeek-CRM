package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;

import tn.esprit.crm.dao.IRoleDao;
import tn.esprit.crm.entities.Role;
import tn.esprit.crm.services.IRoleService;

public class RoleServiceImpl implements IRoleService {

	@EJB
	private IRoleDao roleDao;

	@Override
	public Role save(Role role) {
		return roleDao.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleDao.update(role);
	}

	@Override
	public List<Role> selectAll() {
		return roleDao.selectAll();
	}

	@Override
	public List<Role> selectBy(String param, String value) {
		return roleDao.selectBy(param, value);
	}

	@Override
	public List<Role> selectAll(String sortField, String sort) {
		return roleDao.selectAll(sortField, sort);
	}

	@Override
	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	@Override
	public void remove(Long id) {
		roleDao.remove(id);
		
	}

	@Override
	public Role findOne(String paramName, Object paramValue) {
		return roleDao.findOne(paramName, paramValue);
	}

	@Override
	public Role findOne(String[] paramNames, Object[] paramValues) {
		return roleDao.findOne(paramNames, paramValues);	
	}

}
