<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>수정</title>
	</head>
	<body>
		<h1>전화번호부</h1>
		<h2>연락처 수정폼</h2>
		<form action="/phonebook3/update" method="post">
			<input type="hidden" id="personId" name="personId" value=${personVo.personId }><br>
			<label for="name">이름(name)</label>
			<input type="text" id="name" name="name" value=${personVo.name }><br>
			<label for="hp">휴대전화(hp)</label>
			<input type="text" id="hp" name="hp" value=${personVo.hp }><br>
			<label for="company">회사번호(company):</label>
			<input type="text" id="company" name="company" value=${personVo.company }><br>
			<button type="submit">저장</button>
		</form>
	</body>
</html>