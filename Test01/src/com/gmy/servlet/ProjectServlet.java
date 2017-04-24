package com.gmy.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gmy.factory.ServiceFactory;
import com.gmy.util.ExcelUtil;
import com.gmy.util.ResponseUtil;
import com.gmy.vo.Project;

@WebServlet(name = "ProjectServlet", urlPatterns = "/pages/project/ProjectServlet/*")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getPathInfo().substring(1);
		try {
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Project vo = new Project();
		vo.setName(request.getParameter("name"));
		vo.setFrName(request.getParameter("frName"));
		vo.setTel(Integer.parseInt(request.getParameter("tel")));
		vo.setAddress(request.getParameter("address"));
		String msg = "数据添加失败！";
		String path = request.getContextPath() + "/pages/project/project_insert.jsp";
		try {
			if (ServiceFactory.getIProjectServiceInstance().insert(vo)) {
				msg = "数据添加成功！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("msg", msg);
		request.setAttribute("path", path);
		request.getRequestDispatcher("/pages/forward.jsp").forward(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Project vo = new Project();
		vo.setName(request.getParameter("name"));
		vo.setFrName(request.getParameter("frName"));
		vo.setTel(Integer.parseInt(request.getParameter("id")));
		vo.setAddress(request.getParameter("address"));
		vo.setId(Integer.parseInt(request.getParameter("id")));
		try {
			response.getWriter().print(ServiceFactory.getIProjectServiceInstance().update(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("ids");
		try {
			if (ids != null) {
				String result[] = ids.split("\\|");
				Set<Integer> set = new HashSet<Integer>();
				for (int i = 0; i < result.length; i++) {
					set.add(Integer.parseInt(result[i]));
				}
				response.getWriter().print(ServiceFactory.getIProjectServiceInstance().delete(set));
			} else {
				response.getWriter().print(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int currentPage = 1;
		int lineSize = 5;
		try {
			currentPage = Integer.parseInt(request.getParameter("cp"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			lineSize = Integer.parseInt(request.getParameter("ls"));
		} catch (Exception e) {
		}
		try {
			Map<String, Object> map = ServiceFactory.getIProjectServiceInstance().list(currentPage, lineSize);
			JSONObject obj = new JSONObject();
			JSONArray array = new JSONArray();
			obj.put("allRecorders", map.get("projectCount"));
			@SuppressWarnings("unchecked")
			List<Project> all = (List<Project>) map.get("allProjects");
			Iterator<Project> iter = all.iterator();
			while (iter.hasNext()) {
				JSONObject temp = new JSONObject();
				Project vo = iter.next();
				temp.put("id", vo.getId());
				temp.put("name", vo.getName());
				temp.put("frName", vo.getFrName());
				temp.put("tel", vo.getTel());

				temp.put("address", vo.getAddress());
				array.add(temp);
			}
			obj.put("allProjects", array);
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Workbook wb = new HSSFWorkbook();
		String[] headers = {"工程名称", "法人名称", "法人电话", "工程地址"};
		ResultSet rs = ServiceFactory.getIProjectServiceInstance().exportExcel();
		ExcelUtil.exportExcelData(rs, wb, headers);
		ResponseUtil.export(response, wb, "导出excel.xls");
	}

	public void importExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getServletContext().getRealPath("/") + "upload/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> iter = items.iterator();
		while(iter.hasNext()) {
			FileItem item = iter.next();
			String ext =  item.getName().split("\\.")[1];
			InputStream input = item.getInputStream();
			String fileName = request.getServletContext().getRealPath("/") +"upload/" + UUID.randomUUID() + "." + ext;
			File file = new File(fileName);
			OutputStream out = new FileOutputStream(file);
			byte buff[] = new byte[1024];
			int length = 0;
			while((length =  input.read(buff)) != -1) {
				out.write(buff, 0, length);
			}
			out.close();
			input.close();
			
			POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb=new HSSFWorkbook(fs);
			HSSFSheet hssfSheet=wb.getSheetAt(0);  // 获取第一个Sheet页
			if(hssfSheet != null) {
				for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++){
					HSSFRow hssfRow=hssfSheet.getRow(rowNum);
					if(hssfRow==null){
						continue;
					}
					Project vo = new Project();
					vo.setName(ExcelUtil.formatCell(hssfRow.getCell(0)));
					vo.setFrName(ExcelUtil.formatCell(hssfRow.getCell(1)));
					System.out.println(ExcelUtil.formatCell(hssfRow.getCell(2)));
					vo.setTel(Integer.parseInt(ExcelUtil.formatCell(hssfRow.getCell(2))));
					vo.setAddress(ExcelUtil.formatCell(hssfRow.getCell(3)));
					ServiceFactory.getIProjectServiceInstance().insert(vo);
				}
			}
		}
		
		String msg = "数据添加成功！";
		String path = request.getContextPath() + "/pages/project/project_list.jsp";
		request.setAttribute("msg", msg);
		request.setAttribute("path", path);
		request.getRequestDispatcher("/pages/forward.jsp").forward(request, response);
	}
}
