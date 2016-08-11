<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="permissionCheck.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Control Page</title>
</head>
<body>
	<h1>队员信息</h1>
	<form action="UpdateData">
		姓名:
		<select name="sName">
			<c:forEach items="${contestants}" var="contestant">
		      <option value="${contestant.id }">${contestant.name }</option>
			</c:forEach>
		</select>
		题号:
		<select name="sQuestionNum">
		  <c:forEach items="${contest.alphas }" var="alpha" >
			<option value="${alpha }">${alpha }</option>		  
		  </c:forEach>
		</select>
		是否通过:
		<select name="sIsAccept">
			<option value="ac">是</option>
			<option value="noac">否</option>
		</select>
		<input type="submit" value="确认"/>	
	</form>
	
	<hr>
	<table>
		<tr>
			<th>排名</th>
			<th>姓名</th>
			<th>班级</th>
			<th>已解决题数</th>
			<th></th>
		</tr>
		
		<c:forEach items="${contestants}" var="contestant">
		
		<tr>
			<td>${contestant.rank}</td>
			<td><a href="">${contestant.name}</a></td>
			<td>${contestant.className }</td>
			<td>${contestant.solveCount }</td>
			<td><a href="">修改</a></td>
			<th><a href="ContestantServlet?action=del&id=${contestant.id }">删除</a></th>
		</tr>
		
		</c:forEach>
	</table>
	
	<hr>
	<a href="add.jsp">添加成员</a>	
</body>
</html>