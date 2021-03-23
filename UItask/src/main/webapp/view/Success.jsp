<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form>
<h1>Successfully registered.....</h1>
<span>Full name:</span><span>${user1.username}</span><br/>
<span>Password:</span><span>${user1.password}</span><br/>
<span>Image:</span><span><img src="data:image/png;base64,${user1.encodedImage}" alt="Image" width="500" height="600"></span><br/>

</form>

</body>
</html>