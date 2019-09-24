<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록</title>
</head>
<body>
<form action="/board/insert" id="form" method="post">

<table border="1">
<tr>
<th>제목 </th>
<th>내용</th>

</tr>

<tr>
<td><input type="text" name="title" id="title"></td>
<td><input type="text" name="content" id="content"></td>

</tr>


</table>
<button type="submit">등록</button>
</form>

</body>
</html>