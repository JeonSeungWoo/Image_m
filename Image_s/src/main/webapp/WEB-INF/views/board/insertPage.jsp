<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록</title>
</head>
<body>
<form id="form" action="/board/insert" method="get" enctype="multipart/form-data">

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

<div id="divForm">
<input type="file" id="file" class="file" name="file"> 
</div>

      <button type="button" id="addBtn">추가</button>
	
	  <button type="button" id="removeBtn">제거</button>

<button type="submit" id="insertBtn">등록</button>
</form>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
var form = $("#form");

var numCheck = 0;

$("#insertBtn").on("click", function() {
	//이미지의 값 중 빈값이 있으면 등록 되지 않도록 구현한다.
	var fileVal = $('[name="file"]').val();
	if (fileVal == "" || fileVal == null) {
		alert("이미지를 1개 이상 등록해 주세요.");
	} else {
	    form.attr("action", "insert");
		form.attr("method", "POST");
		form.submit(); 
	}
	
});

$("#addBtn").on("click",function() {
	numCheck = numCheck+1;
	 var txt = '<input type="file" id="file" class="file'+numCheck+'" name="file"> '
	 $("#divForm").append(txt);
});

$("#removeBtn").on("click", function() {
	/*전체 제거  */
	/* $("#divForm *").remove(); */
	$(".file"+numCheck).remove(); 
	numCheck = numCheck-1;
});

</script>


</body>
</html>