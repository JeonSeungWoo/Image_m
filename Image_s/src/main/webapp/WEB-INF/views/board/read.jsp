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
		<ul>
			<c:forEach items="${list}" var="list">
				<li><img class="img" style="width: 300px; height: 300px;"
					alt="${list.filename}" src="/upload/file?bno=${vo.bno}&filename=${list.filename}">
					
				</li>
			
			</c:forEach>

		</ul>











	</form>





	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".img").on("click", function() {
				var src = $(this).attr("src");
                window.location.href = src;
			});
			
			$(".fileBtn").on("click",function(){
				var path = $(this).attr("value");
				var name = $(this).attr("data-value");
				alert(path + name);
				
			});

		});
	</script>

</body>
</html>