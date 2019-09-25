<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<c:forEach items="${list}" var="list">
        <input type="hidden" name="ino" value="${list.ino}">
         <li class= "imgLi" data-value ="${list.filename}"  onclick="editMove(${list.ino})">
         <img class="img"  style="width: 300px; height: 300px;" alt="${list.fileName}" src="/upload/showAll?bno=${vo.bno}&fileName=${list.filename}">
         <a href="/upload/imgDelete?bno=${vo.bno}&fileName=${list.filename}" style="z-index:1;">X</a> 
         </li>
</c:forEach>



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




$(document).ready(function(){
	
	$("#upBtn").on("click",function(){
		obj.attr("action","updatePage").attr("method","get").submit();
	});
	
	$("#delBtn").on("click",function(){
		obj.attr("action","delete").attr("method","POST").submit();
	});
	
	$("#listBtn").on("click",function(){
		obj.attr("action","/board/listPage?page=1").attr("method","get").submit();
	
	});


});




</script>

</body>
</html>