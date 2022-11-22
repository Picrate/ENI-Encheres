<%-- Imports --%>
<%-- <%@ page import="java.util.List" %> --%>

<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Config de la page --%>
<%@ page 
language="java" 
contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"
%>

<%-- Variable page --%>
<%! 
String pageTitle = "Les enchères";
String pageAuthor = "POV - ENI";
%>

<!DOCTYPE html>
<html>

	<head>
		
		<!-- HEAD -->
		<jsp:include page="/WEB-INF/template-parts/head.jsp">
			<jsp:param value="<%= pageTitle %>" name="pageTitle"/>
			<jsp:param value="<%= pageAuthor %>" name="pageAuthor"/>
		</jsp:include>
		
		<!-- CSS -->
		<link href="<%=request.getContextPath()%>/assets/css/style.css" rel="stylesheet">
	
		<!-- JS -->
		<!-- <script src="../js/script-dist.js"></script> -->

	</head>

	<body>
	
		<!-- HEADER -->
		<jsp:include page="/WEB-INF/template-parts/header.jsp"/>

		<main>
		
			<!-- HEADING -->
			<jsp:include page="/WEB-INF/template-parts/heading.jsp">
				<jsp:param value="Les enchères" name="title"/>
			</jsp:include>
			
			<!-- FILTERS -->
			<jsp:include page="/WEB-INF/template-parts/filters.jsp"/>

			
			<!-- ERROR -->
	        <jsp:include page="/WEB-INF/template-parts/error.jsp">
				<jsp:param value="" name="errorMessage"/>
			</jsp:include>
			
			<!-- LISTE OBJETS -->
			<ul id="index" class="row">
				<li class="card">
					<img src="" alt="">
					<div class="detail">
						<h3><a href="objet.html">Objet 1</a></h3>
						<p class="price">Prix 200 points</p>
						<p class="end_date">21/11/2022</p>
						<p class="seller">Pseudo</p>
					</div>
				</li>

				<li class="card">
					<img src="" alt="">
					<div class="detail">
						<h3><a href="objet.html">Objet 2</a></h3>
						<p class="price">Prix 200 points</p>
						<p class="end_date">21/11/2022</p>
						<p class="seller">Pseudo</p>
					</div>
				</li>

				<li class="card">
					<img src="" alt="">
					<div class="detail">
						<h3><a href="objet.html">Objet 3</a></h3>
						<p class="price">Prix 200 points</p>
						<p class="end_date">21/11/2022</p>
						<p class="seller">Pseudo</p>
					</div>
				</li>
			</ul>
			
		</main>
		
		 <!-- FOOTER -->
	    <jsp:include page="/WEB-INF/template-parts/footer.jsp">
	    	<jsp:param value="<%= pageAuthor %>" name="pageAuthor"/>
	    </jsp:include>
    

	</body>

</html>
