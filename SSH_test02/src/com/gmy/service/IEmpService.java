package com.gmy.service;

import java.util.Map;
import java.util.Set;

import com.gmy.vo.Emp;

public interface IEmpService {

	public boolean insert(Emp vo) throws Exception;
	
	public boolean delete(Set<Integer> ids) throws Exception;
	
	public boolean update(Emp vo) throws Exception;
	
	public Map<String,Object> list(int currentPage, int lineSize) throws Exception;
}
