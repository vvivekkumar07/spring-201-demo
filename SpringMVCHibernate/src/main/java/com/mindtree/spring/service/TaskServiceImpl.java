package com.mindtree.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.spring.dao.TaskDAO;
import com.mindtree.spring.dao.TaskDAOImpl;
import com.mindtree.spring.model.TaskAllocation;

@Service
public class TaskServiceImpl implements TaskService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	@Autowired
	private TaskDAO taskDAO;

	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<String> getProject() {
		List<String> projectList = null;
		try {
			projectList = this.taskDAO.getProjectName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("exception occured in getproject"+e);
		}
		return projectList;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<String> getEmployeeByProjectName(String projectName) throws Exception {
		 
		return taskDAO.getEmployeeByProjectName(projectName);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public void addTask(TaskAllocation t) {
		try {
			taskDAO.addTask(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("exception occured in addTask"+e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<TaskAllocation> getTask(String projectName) {
		List<TaskAllocation> taskAllocation=null;
		try {
				taskAllocation= taskDAO.getTask(projectName);
		} catch (Exception e) {
			logger.debug("exception occured in getTask"+e);
		}
		return taskAllocation;
	}

}
