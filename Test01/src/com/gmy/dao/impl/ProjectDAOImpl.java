package com.gmy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gmy.dao.IProjectDAO;
import com.gmy.vo.Project;

public class ProjectDAOImpl implements IProjectDAO{

	private Connection conn;
	private PreparedStatement pstmt;
	
	public ProjectDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Project vo) throws Exception {
		String sql = "INSERT INTO project(name,frName,tel,address) VALUES(?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getName());
		this.pstmt.setString(2, vo.getFrName());
		this.pstmt.setInt(3, vo.getTel());
		this.pstmt.setString(4, vo.getAddress());
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Project vo) throws Exception {
		String sql = "UPDATE project SET name=?,frName=?,tel=?,address=? WHERE id=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getName());
		this.pstmt.setString(2, vo.getFrName());
		this.pstmt.setInt(3, vo.getTel());
		this.pstmt.setString(4, vo.getAddress());
		this.pstmt.setInt(5, vo.getId());
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		if(ids.size() == 0) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM project WHERE id IN (");
		Iterator<Integer> iter = ids.iterator();
		while (iter.hasNext()) {
			sql.append(iter.next()).append(",");
		}
		sql.delete(sql.length() - 1 , sql.length()).append(")");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		return this.pstmt.executeUpdate() == ids.size();
	}

	@Override
	public List<Project> findAll(Integer currentPage, Integer lineSize)
			throws Exception {
		List<Project> all = new ArrayList<Project>();
		String sql = "SELECT id,name,frName,tel,address FROM project LIMIT ?,?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, (currentPage - 1) * lineSize);
		this.pstmt.setInt(2, lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()) {
			Project vo = new Project();
			vo.setId(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setFrName (rs.getString(3));
			vo.setTel(rs.getInt(4));
			vo.setAddress(rs.getString(5));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCount() throws Exception {
		String sql = "SELECT COUNT(id) FROM project";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

}
