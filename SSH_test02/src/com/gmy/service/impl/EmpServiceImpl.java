package com.gmy.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmy.dao.IEmpDAO;
import com.gmy.service.IEmpService;
import com.gmy.vo.Emp;

@Service("mepService")
public class EmpServiceImpl implements IEmpService {
	
	@Autowired
	private IEmpDAO empDAO;
	
	public void setEmpDAO(IEmpDAO empDAO) {
		this.empDAO = empDAO;
	}
	
	public IEmpDAO getEmpDAO() {
		return empDAO;
	}
	
	@Override
	public boolean insert(Emp vo) throws Exception {
		return this.empDAO.doCreate(vo);
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		return this.empDAO.doRemoveBatch(ids);
	}

	@Override
	public boolean update(Emp vo) throws Exception {
		return this.empDAO.doUpdate(vo);
	}

	@Override
	public Map<String, Object> list(int currentPage, int lineSize)
			throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("allEmps", this.empDAO.findAll(currentPage, lineSize));
		map.put("empCount", this.empDAO.getAllCount());
		return map;
	}

}
