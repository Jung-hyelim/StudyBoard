<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Documents List</title>
</head>
<body>
	<div align="center">
		<h1>Documents</h1>
		<div>
			<table border="1" width="500">
				<thead>
					<tr>
						<th width="50">No.</th>
						<th>제목</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="d" items="${page.content }">
						<tr>
							<td>${d.id }</td>
							<td><a href="/${d.id }">${d.title }</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div>
			<c:forEach var="i" begin="1" end="${page.totalPages }">
				<span><a href="/?page=${i - 1}">${i }</a></span>
			</c:forEach>
		</div>
		<div>
			<a href="/new"><button>글쓰기</button></a>
		</div>
	</div>
</body>
</html>