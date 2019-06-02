<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Documents Detail</title>
</head>
<body>
<div>
	<h1>Document Detail</h1>
	<table border="1" width="500">
		<tr>
			<th width="50">No.</th>
			<td><c:out value="${document.id }"></c:out></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><c:out value="${document.title }"></c:out></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" rows="8" cols="50" readonly="readonly">${document.content }</textarea></td>
		</tr>
	</table>
	<button onclick="window.location.href='/documents'">목록</button>
	<button onclick="window.location.href='/documents/${document.id}/edit'">수정</button>
	<form action="/documents/${document.id }" method="post">
		<input type="hidden" name="_method" value="DELETE">
		<input type="submit" value="삭제">
	</form>
</div>
</body>
</html>