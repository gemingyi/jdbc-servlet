package com.gmy.dao;

import java.util.List;
import java.util.Set;

import com.gmy.vo.Emp;

public interface IEmpDAO {

	public boolean doCreate(Emp vo) throws Exception;
	
	public boolean doUpdate(Emp vo) throws Exception;
	
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception;
	
	public List<Emp> findAll(Integer currentPage,Integer lineSize) throws Exception;
	
	public Integer getAllCount() throws Exception;
}
