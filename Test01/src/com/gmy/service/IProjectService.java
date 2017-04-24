package com.gmy.service;

import java.sql.ResultSet;
import java.util.Map;
import java.util.Set;

import com.gmy.vo.Project;

public interface IProjectService {

	public boolean insert(Project vo) throws Exception;
	
	public boolean update(Project vo) throws Exception;
	
	public boolean delete(Set<Integer> ids) throws Exception;
	
	public Map<String,Object> list(int currentPage,int lineSize) throws Exception;
	
	public ResultSet exportExcel() throws Exception;
	
}
