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
		<link href="<c:url value="/assets/css/style.css"/>" rel="stylesheet">
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
							<input type="text" name="nomArticle" value="<c:out value='${selectedArticle.nomArticle}' default=''/>" required>
						</fieldset>
					
						<!-- DESCRIPTION -->
						<fieldset>
							<label>Description :</label><br>
							<textarea name="description" required><c:out value='${selectedArticle.description}' default=''/></textarea>
						</fieldset>
						
						<!-- CATEGORIE -->
						<fieldset>
							<label>Catégorie :</label><br>
							<select id="categories" name="selectedCategorie" required>
								<option disable ${empty selectedArticle.noCategorie ? "selected" : ""}>Toutes les catégories</option>
								<c:if test="${! empty listeCategories}">
									<c:forEach var="categorie" items="${listeCategories}">
										<option value="<c:out value='${categorie.noCategorie}' default=''/>" ${selectedArticle.noCategorie == categorie.noCategorie ? "selected" : ""}>${categorie.libelle}</option>
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
						<c:choose>
							<c:when test="${empty selectedArticle.miseAPrix}">
								<fieldset>
									<label>Mise à prix :</label><br>
									<input type="number" name="miseAPrix" value="<c:out value="" default=""/>" required>
								</fieldset>
							</c:when>
							<c:otherwise>
								<p><strong>Mise à prix :</strong><br><c:out value="${selectedArticle.miseAPrix}" default="Aucune catégorie"/> points</p>
								<input type="hidden" value="${selectedArticle.miseAPrix}" />
							</c:otherwise>
						</c:choose>	
						
						<!-- DEBUT ENCHERE -->	
						<fmt:parseDate value="${selectedArticle.dateDebutEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDateTime" type="both" />
						<c:choose>
							<c:when test="${empty selectedArticle.miseAPrix}">
								<fieldset>
									<label>Début de l'enchère :</label><br>
									<input type="datetime-local" name="dateDebutEnchere" value="<fmt:formatDate pattern="dd/MM/YYYY HH:mm" value="${parsedStartDateTime}" type="both"/>" required>
								</fieldset>
							</c:when>
							<c:otherwise>
								<p><strong>Début de l'enchère :</strong><br><fmt:formatDate pattern="EEEEE dd MMMM 'à' HH'h'mm" value="${parsedStartDateTime}" type="both"/></p>
								<input type="hidden" name="dateDebutEnchere" value="${parsedEndDateTime}" />
							</c:otherwise>
						</c:choose>	
						
						<!-- FIN ENCHERE -->
						<fmt:parseDate value="${selectedArticle.dateFinEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDateTime" type="both" />
						<c:choose>
							<c:when test="${empty selectedArticle.miseAPrix}">
								<fieldset>
									<label>Fin de l'enchère :</label><br>
									<input type="datetime-local" name="dateFinEnchere" value="<fmt:formatDate pattern="dd/MM/YYYY HH:mm" value="${parsedEndDateTime}" type="both"/>" required>
								</fieldset>
							</c:when>
							<c:otherwise>
								<p><strong>Fin de l'enchère :</strong><br><fmt:formatDate pattern="EEEEE dd MMMM 'à' HH'h'mm" value="${parsedEndDateTime}" type="both"/></p>
								<input name="dateFinEnchere" type="hidden" value="${parsedEndDateTime}" />
							</c:otherwise>
						</c:choose>	
						
						
						<!-- ADRESSE -->
						<div class="field_group">
							<fieldset>
								<label>Rue :</label><br>
								<input type="text" value="<c:out value='${utilisateurConnecte.adresse.getRue()}' default=''/>" required>
							</fieldset>
							<fieldset>
								<label>Code postal :</label><br>
								<input type="text" value="<c:out value='${utilisateurConnecte.adresse.getCodePostal()}' default=''/>" required>
							</fieldset>
							<fieldset>
								<label>Ville :</label><br>
								<input type="text" value="<c:out value='${utilisateurConnecte.adresse.getVille()}' default=''/>" required>
							</fieldset>
						</div>
						
						<!-- SUBMIT -->
						<input type="submit" value="Enregistrer" class="btn">
						<a href="${contextPath}" class="btn outline">Annuler</a>
						<a href="${contextPath}/ajouter-un-article?deleteId=${selectedArticle.noArticle}" class="btn outline">Supprimer la vente</a>
						
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
