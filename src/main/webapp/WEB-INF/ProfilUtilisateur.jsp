<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Config de la page --%>
<%@ page 
language="java" 
contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"
%>

<%-- Variable page --%>
<c:set var="pageTitle" value="Profil" />
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
			
			<!-- PROFIL-->
			<div id="user" class="row">
				<div class="col_2">

					<p>Pseudo :</p>
					<p><c:out value="${utilisateur.pseudo}" default="Pseudo non renseigné" /></p>

					<p>Nom :</p>
					<p><c:out value="${utilisateur.nom}" default="Nom non renseigné" /></p>

					<p>Prénom :</p>
					<p><c:out value="${utilisateur.prenom}" default="Prénom non renseigné" /></p>

					<p>Email :</p>
					<p><c:out value="${utilisateur.email}" default="Email non renseigné" /></p>

					<p>Téléphone :</p>
					<p><c:out value="${utilisateur.telephone}" default="Téléphone non renseigné" /></p>

					<p>Rue :</p>
					<p><c:out value="${utilisateur.adresse.rue}" default="Rue non renseignée" /></p>

					<p>Code postal :</p>
					<p><c:out value="${utilisateur.adresse.codePostal}" default="Code postal non renseigné" /></p>

					<p>Ville :</p>
					<p><c:out value="${utilisateur.adresse.ville}" default="Ville non renseignée" /></p>
					
					<c:if test="${utilisateurConnecte.no_utilisateur == userId}">
						<p>Crédits :</p><p><c:out value="${utilisateurConnecte.credit}" default="Crédits non renseignés" /></p>
					</c:if>

				</div>
				
				<c:if test="${utilisateurConnecte.no_utilisateur == userId}">
					<a class="btn" href="compte/modifier"/>Modifier</a>
				</c:if>
				
			</div>
			
		</main>
		
		 <!-- FOOTER -->
	    <jsp:include page="/WEB-INF/template-parts/footer.jsp">
	    	<jsp:param value="${pageAuthor}" name="pageAuthor"/>
	    </jsp:include>
    

	</body>

</html>
