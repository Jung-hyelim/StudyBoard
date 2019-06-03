<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Documents Edit</title>
</head>
<body>
	<div align="center">
		<h1>Document Edit</h1>
		<div>
			<form action="/${document.id }" method="post">
				<input type="hidden" name="_method" value="put" />
				<table border="1" width="500">
					<tr>
						<th width="50">No.</th>
						<td><input type="text" name="id" readonly="readonly" value="${document.id }"></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" value="${document.title }"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" rows="8" cols="65">${document.content }</textarea></td>
					</tr>
				</table>
				<input type="submit" value="저장">
			</form>
		</div>
		<div>
			<a href="/"><button>목록</button></a>
		</div>
	</div>
</body>
</html>