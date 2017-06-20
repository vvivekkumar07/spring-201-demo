package com.mindtree.spring.dao;

import java.util.List;

import com.mindtree.spring.model.TaskAllocation;

public interface TaskDAO {
	
	public List<String> getProjectName() throws Exception;
	public List<String> getEmployeeByProjectName(String projectName) throws Exception;
	public void addTask(TaskAllocation t) throws Exception;
	public List<TaskAllocation> getTask(String projectName) throws Exception;
}
