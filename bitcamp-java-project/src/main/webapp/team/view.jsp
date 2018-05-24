<%@page import="bitcamp.java106.pms.domain.Member"%>
<%@page import="bitcamp.java106.pms.domain.Team"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>팀 보기(MVC)</title>
</head>
<body>
<div id='header'>
<a href='/bitcamp-java-project/auth/login'>로그인</a></div>
<h1>팀 보기(MVC)</h1>
<%
Team team = (Team)request.getAttribute("team");
String name = (String)request.getAttribute("name");
%>
<form action='update' method='post'>
<table border='1'>
<tr>
    <th>팀명</th><td><input type="text" name="name" value='<%=name%>' readonly></td>
</tr>
<tr>
    <th>설명</th><td><textarea name="description" 
        rows="6" cols="60"><%=team.getDescription()%></textarea></td>
</tr>
<tr>
    <th>최대인원</th><td><input type="number" name="maxQty" value='<%=team.getMaxQty()%>'></td>
</tr>
<tr>
    <th>시작일</th><td><input type="date" name="startDate" value='<%=team.getStartDate()%>'></td>
</tr>
<tr>
    <th>종료일</th><td><input type="date" name="endDate" value='<%=team.getEndDate()%>'></td>
</tr>
</table>
<p>
<a href='list'>목록</a>
<button>변경</button>
<a href='delete?name=<%=name%>'>삭제</a>
<a href='../task/list?teamName=<%=name%>'>작업목록</a>
</p>
</form>
</body>
</html>