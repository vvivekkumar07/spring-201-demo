package com.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mindtree.spring.model.TaskAllocation;
import com.mindtree.spring.service.TaskService;
import com.mindtree.spring.service.TaskServiceImpl;

public class TestTaskService {

	@Test
	public void test() {
		TaskServiceImpl taskService=new TaskServiceImpl();
		assertEquals("shivam",taskService.getEmployeeByProjectName("samsung ui"));
	}
}
