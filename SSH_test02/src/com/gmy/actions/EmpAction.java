package com.gmy.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmy.service.IEmpService;
import com.gmy.util.DateJsonValueProcessor;
import com.gmy.vo.Emp;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("struts-default")
@SuppressWarnings("serial")
@Namespace("/EmpAction")
public class EmpAction extends ActionSupport{

	@Autowired
	private IEmpService empService;
	
	public void setEmpService(IEmpService empService) {
		this.empService = empService;
	}
	public IEmpService getEmpService() {
		return empService;
	}
	
	@Action(value = "list")
	public String list() throws Exception {
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		int currentPage = 1;
		int lineSize = 5;
		
		try {
			Map<String,Object> map = this.empService.list(currentPage, lineSize);
			JSONObject obj = new JSONObject();
			obj.put("allRecorders", map.get("empCount"));
			@SuppressWarnings("unchecked")
			List<Emp> all = (List<Emp>) map.get("allEmps");
			JsonConfig jsonConfig = new JsonConfig();
		    jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
			JSONArray array = JSONArray.fromObject(all, jsonConfig);
			obj.put("allEmps", array);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(obj);
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Action(value = "insert", results = { @Result(name = "success",location = "/pages/emp/emp_insert.jsp")})
	public String insert() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Emp vo = new Emp();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setName(request.getParameter("name"));
		vo.setAge(Integer.parseInt(request.getParameter("age")));
		Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
		vo.setBirthday(birthday);
		vo.setSal(Integer.parseInt(request.getParameter("sal")));
		this.empService.insert(vo);                                                                             
		return SUCCESS;
	}
	
	@Action(value = "delete")
	public void delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String ids = request.getParameter("ids");
		try {
			if (ids != null) {
				String result[] = ids.split("\\|");
				Set<Integer> set = new HashSet<Integer>();
				for (int i = 0; i < result.length; i++) {
					set.add(Integer.parseInt(result[i]));
				}
				response.getWriter().print(this.empService.delete(set));
			} else {
				response.getWriter().print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "update")
	public void update() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Emp vo = new Emp();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setName(request.getParameter("name"));
		vo.setAge(Integer.parseInt(request.getParameter("age")));
		Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
		vo.setBirthday(birthday);
		vo.setSal(Integer.parseInt(request.getParameter("sal")));
		try {
			response.getWriter().print(this.empService.update(vo));
		} catch(Exception e) {
			e.printStackTrace();
		}
		}
	
}
