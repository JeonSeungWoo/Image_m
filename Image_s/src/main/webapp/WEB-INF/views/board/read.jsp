<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="form">
<input type="hidden" name="bno" id="bno" value="${vo.bno}">
<table>
<tr>
<th>제목</th>
<th>내용</th>
</tr>

<tr>
<td><input type="text" name="title" id="title" value="${vo.title}"></td>
<td><input type="text" name="content" id="content" value="${vo.content}"></td>
</tr>


</table>
<button type="button" id="updateBtn">수정</button>
<button type="button" id="deleteBtn">삭제</button>
</form>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
var form = $("#form");

$("#updateBtn").on("click",function(){
	form.attr("action","update").attr("method","POST").submit();
});


$("#deleteBtn").on("click",function(){
	form.attr("action","delete").attr("method","POST").submit();
});

</script>

</body>
</html>