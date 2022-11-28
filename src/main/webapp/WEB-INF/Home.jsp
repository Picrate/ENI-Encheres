<%-- Imports --%>
<%@ page import="java.util.List" %>
<%@ page import="fr.eni.javaee.eni_encheres.bo.Article"%>

<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- Config de la page --%>
<%@ page 
language="java" 
contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"
%>

<%-- Variables page --%>
<c:set var="pageTitle" value="Les enchères" />
<c:set var="pageAuthor" value="POV - ENI" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>

	<head>
		
		<!-- HEAD -->
		<jsp:include page="/WEB-INF/template-parts/head.jsp">
			<jsp:param value="${pageTitle}" name="pageTitle"/>
			<jsp:param value="${pageAuthor}" name="pageAuthor"/>
		</jsp:include>
		
		<!-- CSS -->
		<link href="${contextPath}/assets/css/style.css" rel="stylesheet">
		
		

	</head>

	<body>
	
		<!-- HEADER -->
		<jsp:include page="/WEB-INF/template-parts/header.jsp"/>
	
		<main>
			<!-- HEADING -->
			<jsp:include page="/WEB-INF/template-parts/heading.jsp">
				<jsp:param value="${pageTitle}" name="title"/>
			</jsp:include>
			
			<!-- FILTERS -->
			<jsp:include page="/WEB-INF/template-parts/filters.jsp">
				<jsp:param value="${userConnected}" name="userConnected"/>
				<jsp:param value="${listeCategories}" name="categoriesList"/>
				<jsp:param value="${selectedCategorieId}" name="selectedCategorieId"/>
			</jsp:include>
			
			<!-- ERROR -->
	        <jsp:include page="/WEB-INF/template-parts/error.jsp">
				<jsp:param value="" name="errorMessage"/>
			</jsp:include>
			
			<c:choose> 
			
				<c:when test="${! empty listeArticles}"> 
					<!-- LISTE OBJETS -->
					<ul id="index" class="row">
						<c:forEach var="keyValue" items="${listeArticles}">
			        	<li class="card">
				        	<img src="" alt="">
							<div class="detail">
								<h3>
									<a class="article_link" href="${contextPath}/objet?articleID=${keyValue.key.noArticle}">
									${keyValue.key.nomArticle}
									</a>
								</h3>
								<p class="price">${keyValue.key.miseAPrix} points</p>
								<fmt:parseDate value="${keyValue.key.dateDebutEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDateTime" type="both" />
								<p class="date end_date">Début de l'enchère :<br><span><fmt:formatDate pattern="EEEEE dd MMMM 'à' HH'h'mm" value="${parsedStartDateTime}" type="both"/></span></p>
								<fmt:parseDate value="${keyValue.key.dateFinEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
								<p class="date end_date">Fin de l'enchère :<br><span><fmt:formatDate pattern="EEEEE dd MMMM 'à' HH'h'mm" value="${parsedDateTime}" type="both"/></span></p>
								<p class="seller">Mis en vente par : <a href="${contextPath}/profil-utilisateur?userId=${keyValue.value.no_utilisateur}"><strong>${keyValue.value.pseudo}</strong></a></p>
							</div>
			            </li>
			        	</c:forEach>
			        </ul>
        		</c:when> 
				
	        	<c:otherwise> 
					<p class="row no_content">Aucun objet ne correspond à la recherche.</p>
				</c:otherwise> 
	        	
	        </c:choose>
	        			
		</main>
		
		 <!-- FOOTER -->
	    <jsp:include page="/WEB-INF/template-parts/footer.jsp">
	    	<jsp:param value="${pageAuthor}" name="pageAuthor"/>
	    </jsp:include>
    

	</body>

</html>
