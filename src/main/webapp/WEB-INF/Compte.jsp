<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Config de la page --%>
<%@ page 
language="java" 
contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"
%>

<%-- Variable page --%>
<c:set var="pageTitle" value="Compte" />
<c:set var="pageAuthor" value="POV - ENI" />

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
				<jsp:param value="" name="errorMessage"/>
			</jsp:include>
			 
			<!-- COMPTE-->
			<div id="connexion" class="row">
				<form method="post" action='<c:choose><c:when test="${connecte}">modifier</c:when><c:otherwise>creer</c:otherwise></c:choose>'>
					<div class="col_2">
						<fieldset>
							<label for="pseudo">Pseudo :</label>
							<input type="text" name="pseudo" value="${utilisateurConnecte.pseudo}" required="required" pattern="[a-zA-Z0-9]{3,30}" maxlength="30" title="3 Car. min, 30 Car. Max: majuscules, minuscules & chiffres uniquement">
						</fieldset>
						
						<fieldset>
							<label for="nom">Nom : </label>
							<input type="text" name="nom" value="${utilisateurConnecte.nom}" required="required" maxlength="30">
						</fieldset>
				
						<fieldset>
							<label for="prenom">Prénom :</label>
							<input type="text" name="prenom" value="${utilisateurConnecte.prenom}" required="required" maxlength="30">
						</fieldset>

						<fieldset>
							<label for="email">Email :</label>
							<input type="email" name="email" value="${utilisateurConnecte.email}" required="required" maxlength="50">
						</fieldset>

						<fieldset>
							<label for="telephone">Téléphone :</label>
							<input type="tel" name="telephone" value="${utilisateurConnecte.telephone}" placeholder="01.02.03.04.05" maxlength="14" pattern="[0-9]{2}\.[0-9]{2}\.[0-9]{2}\.[0-9]{2}\.[0-9]{2}$" title="01.02.03.04.05">
						</fieldset>

						<fieldset>
							<label for="rue">Rue :</label>
							<input type="text" name="rue" value="${utilisateurConnecte.adresse.rue}" required="required"  maxlength="30" placeholder="1 Allée des Cerisiers">
						</fieldset>

						<fieldset>
							<label for="codePostal">Code postal :</label>
							<input type="text" name="codePostal" value="${utilisateurConnecte.adresse.codePostal}" required="required" maxlength="6" pattern="[0-9]{5,6}" placeholder="17450">
						</fieldset>

						<fieldset>
							<label for="ville">Ville :</label>
							<input type="text" name="ville" value="${utilisateurConnecte.adresse.ville}" required="required" placeholder="FOURAS" maxlength="30">
						</fieldset>
						
						<fieldset>
							<label for="password">Mot de passe :</label>
							<input type="password" name="password" <c:if test="${ empty connecte }">required="required"</c:if> maxlength="30">
						</fieldset>

						<fieldset>
							<label for="password">Confirmation :</label>
							<input type="password" name="cpassword" <c:if test="${ empty connecte }">required="required"</c:if> maxlength="30">
						</fieldset>
					</div>

					<input type="submit" value="<c:choose><c:when test="${connecte}">Mettre à jour</c:when><c:otherwise>Créer</c:otherwise></c:choose>" class="btn">
					<c:if test="${connecte}"><a class="btn outline" href="<c:url value="/compte/supprimer"/>">Supprimer mon compte</a></c:if>
					<a class="btn outline" href="<c:url value="/"/>">Annuler</a>
				</form>
			</div>
			
		</main>
		
		 <!-- FOOTER -->
	    <jsp:include page="/WEB-INF/template-parts/footer.jsp">
	    	<jsp:param value="${pageAuthor}" name="pageAuthor"/>
	    </jsp:include>
    

	</body>

</html>
