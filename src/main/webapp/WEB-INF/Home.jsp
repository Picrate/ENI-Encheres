<%-- Imports --%>
<%@ page import="java.util.List" %>
<%@ page import="fr.eni.javaee.eni_encheres.bo.Article"%>

<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
			
				<%
	        	List<Article> listeArticles = (List<Article>)request.getAttribute("listeArticle");
	        	%>
	        	
	        	<%-- <c:if test="${! empty listeArticles}"> --%>
					<%= listeArticles %>
		        	<c:forEach var="article" items="${listeArticles}">
		        	<li class="card">
			        	<img src="" alt="">
						<div class="detail">
							<h3><a href="<%=request.getContextPath()%>/objet?articleID=${article.noArticle}">${article.nomArticle}</a></h3>
							<p class="price">Prix ${article.prixVente} points</p>
							<p class="end_date">${article.dateFinEncheres}</p>
							<p class="seller">Pseudo</p>
						</div>
		            </li>
		        	</c:forEach>
		        	
		        <%-- </c:if> --%>
				
			</ul>
			
		</main>
		
		 <!-- FOOTER -->
	    <jsp:include page="/WEB-INF/template-parts/footer.jsp">
	    	<jsp:param value="<%= pageAuthor %>" name="pageAuthor"/>
	    </jsp:include>
    

	</body>

</html>
