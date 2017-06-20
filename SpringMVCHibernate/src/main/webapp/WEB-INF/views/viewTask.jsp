<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Task</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

$("#projectName").change(function(){
alert("test");
var projectName=$("#projectName").val();
var taskList="";
	$.ajax({
			type : "POST",
			url:"getTask",
	     	data:{projectName:projectName},
			success : function(response) {
				$.each(response, function(index, itemdata) {
					taskList=taskList+"<table> <tr>";
					taskList = taskList +"<td><label>Project Name: </label></td>";
					taskList = taskList + "<td><b>" + itemdata.projectName + "</b></td></tr>";
					tasklist=taskList+"<td><label> Task Description: </label></td>";
					taskList = taskList + "<td>" + itemdata.taskDescription + "</td></tr>";
					tasklist="<tr>"+taskList+"<tr><td><label> Start Date </label></td>";
					taskList = taskList + "<td>" + itemdata.startDate + "</td></tr>";
					tasklist="<tr>"+taskList+"<tr><td><label> End Date </label></td>";
					taskList = taskList + "<tr>" + itemdata.endDate + "</td></tr>";
					tasklist="<tr>"+taskList+"<tr><td><label> Employee Name </label></td>";
					taskList = taskList + "<td>" + itemdata.employeeName + "</td></tr></table>";
					
					//alert(employees);
				});
				$("#taskList").html(taskList);
			}
		});
});


		var project = "";
		$.ajax({
			type : "POST",
			url : "getProject",
			success : function(response) {
				$.each(response, function(index, itemdata) {
					//alert(response);
					project = project + "<option>" + itemdata + "</option>";
					//alert(employees);
				});
				$("#projectName").html(project);
			}
		});
	});
	 /* $(document).ready(function(){
	  $('#projectName').change(function(){
	  	alert("getting employee data");
	  	var task="";
	  	var projectName=$("select#projectName").val();;
	  	$.ajax({
	  	 type : "POST",
	      url:"getTasks",
	      data:{projectName:projectName},
	      success:  function(response) {
	       $.each(response,function(index,itemdata){
	       //alert(response);
	       task=itemdata;
	       });
	       $("#task").html(task);
	      }
	    
	  });
	  });
	 }); */
	
</script>
</head>
<body>
<form:form commandName="task" method="POST" action="getTask" modelAttribute="selectedProject"> 
	<table align="center">
		<tr>
			<td><label> Filter By</label></td>
			<td><select id="projectName" name="projectName">
					<option>Project Name</option>
			</select></td>
		</tr>
		
		<%-- <tr>
			<c:forEach var="taskName" items="${taskDescription}" >
				<td><label> Task Description </label></td>
			</c:forEach>
		</tr>  --%>
		
		
		</table>
		<div align="center" id="taskList"></div>
		</form:form>
</body>
</html>