package com.mindtree.spring.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mindtree.spring.model.Employee;
import com.mindtree.spring.model.Project;
import com.mindtree.spring.model.TaskAllocation;

@Repository
public class TaskDAOImpl implements TaskDAO {

	private static final Logger logger = LoggerFactory.getLogger(TaskDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<String> getProjectName() {
		logger.debug("in getProjectName method");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Project.class)
				.setProjection(Projections.projectionList().add(Projections.property("projectName"), "projectName"))
				.setResultTransformer(Transformers.aliasToBean(Project.class));
		@SuppressWarnings("unchecked")
		List<Project> projectList = criteria.list();
		List<String> project = new ArrayList<String>();
		for (Project p : projectList) {
			logger.info("Person List::" + p);
			System.out.println(p.getProjectName());
			project.add(p.getProjectName());
		}
		logger.debug("successfully got the project name"+project);
		return project;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEmployeeByProjectName(String projectName) {
		logger.debug("in getEmployeeByProjectName method");
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.Query q = session.createQuery("select name from Employee where projectName=:projectName");
		q.setString("projectName", projectName);
		List<String> list = q.list();
		System.out.println("employee list" + list);
		return list;
	}

	@Override
	public void addTask(TaskAllocation t) {
		logger.debug("in addTask method");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery(
				"insert into TaskAllocation(taskId,taskDescription,startDate,endDate,employeeName,projectName) values(?,?,?,?,?,?)");
		System.out.println("task id" + t.getTaskId());
		sqlQuery.setParameter(0, t.getTaskId());
		sqlQuery.setParameter(1, t.getTaskDescription());
		sqlQuery.setParameter(2, t.getStartDate());
		sqlQuery.setParameter(3, t.getEndDate());
		sqlQuery.setParameter(4, t.getEmployeeName());
		sqlQuery.setParameter(5, t.getProjectName());
		sqlQuery.executeUpdate();
		session.getTransaction().commit();
		logger.debug("Task allocated and updated in database");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskAllocation> getTask(String projectName) {
		logger.debug("in getTask method");
		TaskAllocation taskALlocation = new TaskAllocation();
		List<TaskAllocation> taskList = new ArrayList<TaskAllocation>();
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.Query q = session.createQuery(
				"select taskDescription,startDate,endDate,employeeName,projectName from TaskAllocation where projectName=:projectName");
		q.setString("projectName", projectName);
		List<Object[]> list = (List<Object[]>) q.list();
		for (Object[] taskAllocationList : list) {
			taskALlocation.setTaskDescription((String) taskAllocationList[0]);
			taskALlocation.setStartDate((Date) taskAllocationList[1]);
			taskALlocation.setEndDate((Date) taskAllocationList[2]);
			taskALlocation.setEmployeeName((String) taskAllocationList[3]);
			taskALlocation.setProjectName((String) taskAllocationList[4]);
			taskList.add(taskALlocation);
		}
		logger.debug("return the Task from db");
		return taskList;
	}
	
	

}
