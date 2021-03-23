<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="register" method="POST" modelAttribute="user1" enctype="multipart/form-data">
		<form:label path="username">UserName :</form:label>
		<form:input path="username" />
		<br />	
		<form:label path="password">Password :</form:label>
		<form:input path="password" />
		<br />	
			<label for="file">Select a file:</label> <input type="file"
				id="file" name="file">
		<button>submit</button>
	</form:form>
</body>
</html>