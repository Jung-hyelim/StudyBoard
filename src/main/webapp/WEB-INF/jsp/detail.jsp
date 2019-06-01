<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div>
	<h1>Document Detail</h1>
	<table border="1">
		<tr>
			<th>No.</th>
			<td>{document.id }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>{document.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>{document.content }</td>
		</tr>
	</table>
</div>
</body>
</html>