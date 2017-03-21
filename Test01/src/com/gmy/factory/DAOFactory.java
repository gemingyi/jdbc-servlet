package com.gmy.factory;

import java.sql.Connection;

import com.gmy.dao.IProjectDAO;
import com.gmy.dao.impl.ProjectDAOImpl;

public class DAOFactory {
	public static IProjectDAO getIProjectDAOInstance(Connection conn) {
		return new ProjectDAOImpl(conn);
	}
}
