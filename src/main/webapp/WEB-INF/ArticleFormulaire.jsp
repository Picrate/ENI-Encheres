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
				
				<div class="col_right form">
				
					<form action="" method="post">
						<!-- TITRE -->
						<fieldset>
							<label>Titre :</label><br>
							<input type="text" name="nomArticle" value="<c:out value='${selectedArticle.nomArticle}' default=''/>">
						</fieldset>
					
						<!-- DESCRIPTION -->
						<fieldset>
							<label>Description :</label><br>
							<textarea name="description"><c:out value='${selectedArticle.description}' default=''/></textarea>
						</fieldset>
						
						<!-- CATEGORIE -->
						<fieldset>
							<label>Catégorie :</label><br>
							<select id="categories" name="selectedCategorie">
								<option disable ${selectedArticle.noCategorie == "" ? "selected" : ""}>Toutes les catégories</option>
								<c:if test="${! empty listeCategories}">
									<c:forEach var="categorie" items="${listeCategories}">
										<option value="<c:out value='${categorie.noCategorie}' default=''/>" ${selectedCategorieId == categorie.noCategorie ? "selected" : ""}>${categorie.libelle}</option>
									</c:forEach>
								</c:if>
							</select>
						</fieldset>

						<!-- PHOTO -->
						<fieldset>
							<label>Photo :</label><br>
							<input type="file" name="fileToUpload" id="fileToUpload" class="btn">
						</fieldset>
						
						<!-- PRICE -->
						<fieldset>
							<label>Mise à prix :</label><br>
							<input type="number" name="miseAPrix" value="<c:out value="${selectedArticle.miseAPrix}" default=""/>">
						</fieldset>
						
						<!-- DEBUT ENCHERE -->
						<fieldset>
							<fmt:parseDate value="${selectedArticle.dateDebutEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDateTime" type="both" />
							<label>Début de l'enchère :</label><br>
							<input type="datetime-local" name="dateDebutEnchere" value="<fmt:formatDate pattern="dd/MM/YYYY HH:mm" value="${parsedStartDateTime}" type="both"/>">
						</fieldset>
						
						<!-- FIN ENCHERE -->
						<fieldset>
							<fmt:parseDate value="${selectedArticle.dateFinEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDateTime" type="both" />
							<label>Fin de l'enchère :</label><br>
							<input type="datetime-local" name="dateFinEnchere" value="<fmt:formatDate pattern="dd/MM/YYYY HH:mm" value="${parsedEndDateTime}" type="both"/>">
						</fieldset>
						
						<!-- ADRESSE -->
						<div class="field_group">
							<fieldset>
								<label>Rue :</label><br>
								<input type="text" value="<c:out value='${utilisateurConnecte.adresse.getRue()}' default=''/>">
							</fieldset>
							<fieldset>
								<label>Code postal :</label><br>
								<input type="text" value="<c:out value='${utilisateurConnecte.adresse.getCodePostal()}' default=''/>">
							</fieldset>
							<fieldset>
								<label>Ville :</label><br>
								<input type="text" value="<c:out value='${utilisateurConnecte.adresse.getVille()}' default=''/>">
							</fieldset>
						</div>
						
						<!-- SUBMIT -->
						<input type="submit" value="Enregistrer" class="btn">
						
					</form>

				</div>
			</div>
	        			
		</main>
		
		 <!-- FOOTER -->
	    <jsp:include page="/WEB-INF/template-parts/footer.jsp">
	    	<jsp:param value="${pageAuthor}" name="pageAuthor"/>
	    </jsp:include>
    

	</body>

</html>
