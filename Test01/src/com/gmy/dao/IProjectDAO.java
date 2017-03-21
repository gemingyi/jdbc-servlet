package com.gmy.dao;

import java.util.List;
import java.util.Set;

import com.gmy.vo.Project;

public interface IProjectDAO {

	public boolean doCreate(Project vo) throws Exception;
	
	public boolean doUpdate(Project vo) throws Exception;
	
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception;
	
	public List<Project> findAll(Integer currentPage,Integer lineSize) throws Exception;
	
	public Integer getAllCount() throws Exception;
}
