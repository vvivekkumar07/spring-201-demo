package com.mindtree.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mindtree.spring.model.Project;
import com.mindtree.spring.model.TaskAllocation;
import com.mindtree.spring.service.TaskService;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping(value = "/getProject", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getProjectName() {
		// System.out.println("get project"+test);
		List<String> projectList = null;
		try {
			projectList = this.taskService.getProject();
		} catch (Exception e) {
			logger.debug("exception occured while getting the project name from db"+e.toString());
			
		}
		for (int i = 0; i < projectList.size(); i++) {
			logger.debug(projectList.get(i));
		}
		System.out.println("project name" + projectList);
		return projectList;
	}

	@RequestMapping(value = "/getEmployees", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getEmployeeName(@RequestParam("projectName") String projectName) {
		// System.out.println("get project"+test);
		List<String> employeeList = null;
		try {
			employeeList = this.taskService.getEmployeeByProjectName(projectName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("exception occured while getting the employee from db"+e.toString());
		}
		for (int i = 0; i < employeeList.size(); i++) {
			System.out.println("The project list value are " + employeeList.get(i));
		}
		System.out.println("project name" + employeeList);
		return employeeList;
	}

	@RequestMapping(value = "/assignTask", method = RequestMethod.GET)
	public String assignTask(Model model) {
		model.addAttribute("task", new TaskAllocation());
		return "assignTask";
	}

	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	public ModelAndView addTask(@ModelAttribute("task") TaskAllocation t, RedirectAttributes redir,BindingResult result) {
		System.out.println("in add task");
		System.out.println(t.toString());
		if(result.hasErrors()){
			logger.debug("binding result error"+ result);
			return new ModelAndView("addTask");
		}
		try {
			taskService.addTask(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("exception occured while adding the task to db"+e.toString());
		}
		redir.addFlashAttribute("savemsg", "successfully saved");
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/viewTask", method = RequestMethod.GET)
	public ModelAndView viewTask(){
		System.out.println("in view task");
		ModelAndView modelAndView = new ModelAndView("viewTask");
		List<String> test = new ArrayList<String>();
		test.add("value1");
		modelAndView.addObject("taskDescription", test);
		return modelAndView;
	}

	@RequestMapping(value = "/getTask", method = RequestMethod.POST)
	@ResponseBody
	public List<TaskAllocation> getTask(@RequestParam("projectName") String projectName){
		System.out.println("in get task" + projectName);
		List<TaskAllocation> taskList = null;
		try {
			taskList = taskService.getTask(projectName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("exception occured while getting the task"+e.toString());
		}
		return taskList;
	}
}
