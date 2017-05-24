<%@page import="com.mvc.model.student.Physical"%>
<%@page import="com.mvc.model.student.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=utf-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function regist(){
	location.href="/student/registForm.jsp";	
}
</script>
</head>
<body>
<%
	List<Student> list=(List)request.getAttribute("list");
%>

<table border="1px">
<tr>
<td>회원번호</td><td>아이디</td><td>비번</td><td>이름</td><td>혈액형</td><td>몸무게</td>
</tr>
<% for(int i=0; i<list.size();i++){ %>
<% Student student = list.get(i); %>
<% Physical physical = student.getPhysical(); %>
<tr>
<td><%=student.getStudent_id() %></td>
<td><%=student.getId() %></td>
<td><%=student.getPassword() %></td>
<td><%=student.getName() %></td>

<td><%=physical.getBlood() %></td>
<td><%=physical.getWeight() %></td>
</tr>
<% } %>
</table>
<input type="button" value="등록하러 가기" onClick="regist()">
</body>
</html>