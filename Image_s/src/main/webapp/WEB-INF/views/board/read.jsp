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



	<form id="form" method="get"enctype="multipart/form-data">
		<input type="hidden" name="bno" id="bno" value="${vo.bno}">
		<input type="hidden" id="listSize" value="${listSize}">
		<ul>
			<c:forEach items="${list}" var="list" >
				<li><img class="img" style="width: 300px; height: 300px;"
					alt="${list.filename}" src="/upload/file?bno=${vo.bno}&filename=${list.filename}">
					
				<button type="button" class="deleteImg" value="${vo.bno}" data-value="${list.filename}">X</button>
				</li>
				
			</c:forEach>

		</ul>




		<div id="divForm">
			<input type="file" id="file" class="file" name="file">
		</div>

		<button type="button" id="addBtn">추가</button>

		<button type="button" id="removeBtn">제거</button>

		<button type="submit" id="insertBtn">등록</button>

	</form>





	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".img").on("click", function() {
				var src = $(this).attr("src");
                window.location.href = src;
			});
		
			var form = $("#form");
			var numCheck = 0;
             
			
			$(".deleteImg").on("click",function(){	
				var bno =$(this).attr("value");
				var filename = $(this).attr("data-value");
				var count =  $("#listSize").val();
				if(count == 1 ){
					alert("사진이 1개이상 있어야 합니다.");
				}else{
					form.attr("action","/upload/imgDelete?bno="+bno+"&filename="+filename).attr("method","post").submit(); 	
				}
			});
			
			$("#insertBtn").on("click", function() {
				//이미지의 값 중 빈값이 있으면 등록 되지 않도록 구현한다.
				var fileVal = $('[name="file"]').val();
				if (fileVal == "" || fileVal == null) {
					alert("이미지를 1개 이상 등록해 주세요.");
				} else {
					form.attr("action", "/upload/insertImage");
					form.attr("method", "POST");
					form.submit();
				}

			});

			$("#addBtn")
					.on(
							"click",
							function() {
								numCheck = numCheck + 1;
								var txt = '<input type="file" id="file" class="file'+numCheck+'" name="file"> '
								$("#divForm").append(txt);
							});

			$("#removeBtn").on("click", function() {
				/*전체 제거  */
				/* $("#divForm *").remove(); */
				$(".file" + numCheck).remove();
				numCheck = numCheck - 1;
			});
		
		
		});
	</script>

</body>
</html>