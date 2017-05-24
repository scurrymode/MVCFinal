<%@page contentType="text/html;charset=utf-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="orange">
<%
	RuntimeException ex = (RuntimeException)request.getAttribute("registEx");
	String error=ex.getMessage();
	out.print(error);
%>
</body>
</html>