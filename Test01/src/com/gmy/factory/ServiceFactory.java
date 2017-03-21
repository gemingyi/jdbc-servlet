package com.gmy.factory;

import com.gmy.service.IProjectService;
import com.gmy.service.impl.ProjectServiceImpl;

public class ServiceFactory {
	public static IProjectService getIProjectServiceInstance() {
		return new ProjectServiceImpl();
	}
}
