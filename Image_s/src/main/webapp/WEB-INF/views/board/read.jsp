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
					alt="${list.filename}"
					src="/upload/showAll?bno=${vo.bno}&filename=${list.filename}">
					<a class="btn" href="/upload/showAll?bno=${vo.bno}&filename=${list.filename}" download>PDF 다운로드</a>

				</li>
			
			</c:forEach>

		</ul>











	</form>





	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".img").on("click", function() {
				var src = $(this).attr("src");
				alert("src : " + src);
				downloadUrl = src;
				var downloadFrame = document.createElement("iframe"); 
				downloadFrame.setAttribute('src',downloadUrl);
				downloadFrame.setAttribute('class',"screenReaderText"); 
				document.body.appendChild(downloadFrame); 

			});

		});
	</script>

</body>
</html>