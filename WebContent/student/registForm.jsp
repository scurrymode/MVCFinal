<%@page contentType="text/html;charset=utf-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
function regist(){
	form1.action="/student/regist.do";
	form1.submit();
}
</script>
</head>
<body>

<form name="form1" method="post">
<pre>
<input type="text" name="id" placeholder="아이디">
<input type="text" name="password" placeholder="비밀번호">
<input type="text" name="name" placeholder="이름">
<input type="text" name="blood" placeholder="혈액형">
<input type="text" name="weight" placeholder="몸무게" value="68">
<input type="button" value="등록" onClick="regist()">
</pre>
</form>
</body>
</html>