<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="permissionCheck.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/form_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form action="SetContestParam" method="post" class="white-pink">
	<h1>设置比赛参数
		<span>请将表格信息准确填写</span>
	</h1>
	
	<label>
		<span>比赛名:</span>
		<input id="contestName" type="text" name="contestName" placeholder="输入比赛名称"/>
	</label>
	
	<label>
		<span>开始时间:</span>
		<input id="startTime" type="text" name="startTime" placeholder="格式yyyy_MM_dd HH:mm:ss"/>
	</label>
	
	<label>
		<span>比赛时长:</span>
		<input id="duration" type="text" name="duration" placeholder="格式yyyy_MM_dd HH:mm:ss"/>
	</label>
	<label>
		<span>题目数量:</span>
		<input id="questionNums" type="text" name="questionNums" placeholder="题目个数"/>
	</label>

	<label>
		<span>&nbsp;</span>
		<input type="submit" class="button" value="确定" />
	</label>
	<label>
		<span>&nbsp;</span>
		<input type="reset" class="button" value="重置" />
	</label>
	</form>

</body>
</html>