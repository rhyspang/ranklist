<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<script language="JavaScript">
function myrefresh(){
   window.location.reload();
}
setTimeout('myrefresh()',5000); //指定1秒刷新一次
</script>
<head>
<link href="css/base.css" rel="stylesheet" type="text/css" />
<title>Rank List</title>
</head>
<body>

<center><h1>${contest.contestName }</h1></center>
<hr>
<center>
<table id="customers">
<tr>
<th>Rank</th>
<th>ID</th>
<th>Solve</th>
<th>Penalty</th>
<c:forEach items="${contest.alphas }" var="alpha" >
	<th>${alpha }</th>
</c:forEach>
</tr>

<c:forEach items="${contestants}" var="contestant">
<tr>
<td class="info">${contestant.rank }</td>
<td class="info">${contestant.name }</td>
<td class="info">${contestant.solveCount }</td>
<td class="info">${contestant.penalty }</td>
  <c:forEach items="${contestant.questions }" var="question">
  	<c:if test="${question.submitTimes == 0 }">
  		<td class="question"></td> 
  	</c:if>
  	<c:if test="${question.accept == false and question.submitTimes != 0 }">
	  <td class="noac">&nbsp;&nbsp;&nbsp;(-${question.wrongTimes })&nbsp;&nbsp;</td>
  	</c:if>
  	<c:if test="${question.accept == true and question.wrongTimes == 0 and question.isFB == false }">
	  <td class="nofb">${question.acceptTimeStr }</td>
  	</c:if>
  	<c:if test="${question.accept == true and question.wrongTimes != 0 and question.isFB == false }">
	  <td class="nofb">${question.acceptTimeStr }<br>(-${question.wrongTimes })</td>
  	</c:if>
  	
  	<c:if test="${question.isFB == true and question.wrongTimes == 0 }">
	  <td class="fb">${question.acceptTimeStr }</td>
  	</c:if>
  	<c:if test="${question.isFB == true and question.wrongTimes != 0 }">
	  <td class="fb">${question.acceptTimeStr }<br>(-${question.wrongTimes })</td>
  	</c:if>
  </c:forEach>
</tr>
</c:forEach>
</table>
</center>
</body>
</html>
