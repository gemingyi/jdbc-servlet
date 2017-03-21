package com.gmy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gmy.dbc.DatabaseConnection;
import com.gmy.factory.DAOFactory;
import com.gmy.service.IProjectService;
import com.gmy.vo.Project;

public class ProjectServiceImpl implements IProjectService{
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public boolean insert(Project vo) throws Exception {
		try {
			return DAOFactory.getIProjectDAOInstance(this.dbc.getConnection()).doCreate(vo);
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Project vo) throws Exception {
		try {
			return DAOFactory.getIProjectDAOInstance(this.dbc.getConnection()).doUpdate(vo);
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		try {
			return DAOFactory.getIProjectDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> list(int currentPage, int lineSize)
			throws Exception {
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("allProjects", DAOFactory.getIProjectDAOInstance(this.dbc.getConnection()).findAll(currentPage, lineSize));
			map.put("projectCount", DAOFactory.getIProjectDAOInstance(this.dbc.getConnection()).getAllCount());
			return map;
		} catch(Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}
}
