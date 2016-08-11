<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="permissionCheck.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Contestant</title>
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
<link href="css/addpage.css" rel="stylesheet" type="text/css" />
<link href="css/gradientbutton.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div id="container">
	<div id="content">
	
	<form action="AddServlet" method="post" class="white-pink">
	<h1>添加参赛队员
		<span>请将表格信息准确填写</span>
	</h1>
	<label>
		<span>姓名:</span>
		<input id="name" type="text" name="name" placeholder="输入姓名"/>
	</label>
	
	<label>
		<span>班级:</span>
		<input id="classname" type="text" name="classname" placeholder="输入班级"/>
	</label>
	
	<label>
		<span>性别:</span>
		<select name="selection">
			<option value="male">男</option>
			<option value="female">女</option>
		</select>
	</label>
	
	<label>
		<span>备注:</span>
		<textarea id="message" name="message" placeholder="备注信息"></textarea>
	</label>
	<label>
		<span>&nbsp;</span>
		<input type="submit" class="button" value="继续添加" />
	</label>
	<label>
		<span>&nbsp;</span>
		<a href="WriteDate" class="button" >添加完成</a>
	</label>
	</form>
	
	</div>
	
	<div id="side">
		<c:forEach items="${contestant_list}" var="contestant">
		<a href="#" class="button orange">${contestant.name }</a>		
		</c:forEach>
		
	</div>
	
		
	</div>
</body>
</html>