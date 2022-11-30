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
<c:set var="pageTitle" value="Détail vente" />
<c:set var="pageAuthor" value="POV - ENI" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="sellerId" value="${seller.no_utilisateur}" />
<c:set var="currentUderId" value="${utilisateurConnecte.no_utilisateur}" />

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
			
			
			<!-- ERROR -->
	        <jsp:include page="/WEB-INF/template-parts/error.jsp">
				<jsp:param value="${listeErreurs}" name="errorMessage"/>
			</jsp:include>
			
			
			<!-- OBJET -->
			<div id="objet" class="row">
			
				<div class="col_left">
					<img src="" alt="">
				</div>
				
				<div class="col_right">
				
					<!-- TITRE -->
					<h1><c:out value="${selectedArticle.nomArticle}" default="Aucun nom"/></h1>
					
					<!-- DESCRIPTION -->
					<p><strong>Description :</strong><br><c:out value="${selectedArticle.description}" default="Aucune description"/></p>
					
					<!-- CATEGORIE -->
					<p><strong>Catégorie :</strong><br><c:out value="${selectedCategorie.libelle}" default="Aucune catégorie"/></p>

					<!-- MISE À PRIX -->
					<p><strong>Mise à prix :</strong><br><c:out value="${selectedArticle.miseAPrix}" default="Aucune catégorie"/> points</p>
					
					<!-- MEILLEUR OFFRES -->
					<p><strong>Meilleure offre :</strong><br><c:out value="${bestOffer} points par ${buyerPseudo}" default="Aucune offre"/></p>
					
					<!-- FIN ENCHERE -->
					<fmt:parseDate value="${selectedArticle.dateFinEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDateTime" type="both" />
					<p><strong>Fin de l'enchère :</strong><br><fmt:formatDate pattern="EEEEE dd MMMM 'à' HH'h'mm" value="${parsedEndDateTime}" type="both"/></p>
					
					<!-- VILLE -->
					<p>
						<strong>Retrait :</strong><br/>
						<c:out value="${seller.adresse.getRue()}" default="Aucune rue"/><br/>
						<c:out value="${seller.adresse.getCodePostal()}" default="Aucun code postal"/>
						<c:out value="${seller.adresse.getVille()}" default="Aucune ville"/>
					</p>

					<p><strong>Vendeur :</strong><br><c:out value="${seller.pseudo}" default="Aucun utilisateur"/></p>
										
					<c:choose> 
			
						<c:when test="${currentUderId == sellerId}"> 
							<a class="btn" href="${contextPath}/ajouter-un-article?articleId=${selectedArticle.noArticle}">Modifier</a>
						</c:when> 
				
			        	<c:otherwise> 
							<form action="" method="post">
								<strong>Proposition :</strong><br>
								<fieldset>
									<input name="enchere" type="number" value="${! empty bestOffer ? bestOffer + 1 : selectedArticle.miseAPrix + 1}">
									<button type="submit" class="btn">Enchérir</button>
								</fieldset>
							</form>
						</c:otherwise> 
			        	
			        </c:choose>

				</div>
			</div>
	        			
		</main>
		
		 <!-- FOOTER -->
	    <jsp:include page="/WEB-INF/template-parts/footer.jsp">
	    	<jsp:param value="${pageAuthor}" name="pageAuthor"/>
	    </jsp:include>
    

	</body>

</html>
