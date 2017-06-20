<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Task Page</title>
<!-- <style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
		$(document).ready(function(){
				var project="";
				$.ajax({
				type : "POST",
				url:"getProject",
				success : function(response) {
				$.each(response,function(index,itemdata){
					alert(response);
					project=project+"<option>"+itemdata+"</option>";
				});
				$("#projectName").html(project);
				}
			});
	});
	  $(document).ready(function(){
	  $('#projectName').change(function(){
	  	var employee="";
	  	var projectName=$("select#projectName").val();;
	  	$.ajax({
	  	 type : "POST",
	      url:"getEmployees",
	      data:{projectName:projectName},
	      success:  function(response) {
	       $.each(response,function(index,itemdata){
	       employee=employee+"<option>"+itemdata+"</option>";
	       });
	       $("#employeename").html(employee);
	      }
	    
	  });
	  });
	 });
</script>
</head>
<body>
	<h1>Assign a Task</h1>



	<form:form action="addTask" commandName="task" method="POST">

		<table>
			<tr>
				<td><label> Project Name </label></td>
				<td><select id="projectName" name="projectName">
				<option>Select a Project</option></select></td>
			</tr>
			<tr>
				<td><label> Description </label>
				<td><input type="text" name="taskDescription" id="description" required/></td>
			</tr>
			<tr>
				<td><label> Start Date </label></td>
				<td><input type="text" name="startDate" id="startDate" required/></td>
			</tr>
			<tr>
				<td><label> End Date </label>
				<td><input type="text" name="endDate" id="endDate" required/></td>
			</tr>
			<tr>
				<td><label> Who should do this?</label></td>
				<td> <select id ="employeename" name="employeeName"  multiple>
                        <option>Select Employee</option>
                    </select> </td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message text="Add Task"/>" /> <input type="submit"
					value="<spring:message text="Back"/>" /></td>
			</tr>
		</table>
	</form:form>
	<br>

</body>
</html>
