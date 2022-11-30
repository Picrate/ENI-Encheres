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
String pageTitle = "Profil";
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
				<jsp:param value="<%= pageTitle %>" name="title"/>
			</jsp:include>
			
			<!-- ERROR -->
	        <jsp:include page="/WEB-INF/template-parts/error.jsp">
				<jsp:param value="" name="errorMessage"/>
			</jsp:include>
			
			<!-- PROFIL-->
			<div id="user" class="row">
				<div class="col_2">

					<p>Pseudo :</p>
					<p>${utilisateur.pseudo}</p>

					<p>Nom :</p>
					<p>${utilisateur.nom}</p>

					<p>Prénom :</p>
					<p>${utilisateur.prenom}</p>

					<p>Email :</p>
					<p>${utilisateur.email}</p>

					<p>Téléphone :</p>
					<p>${utilisateur.telephone}</p>

					<p>Rue :</p>
					<p><c:out value="${utilisateur.adresse.rue}" default="Inconnu" /></p>

					<p>Code postal :</p>
					<p><c:out value="${utilisateur.adresse.codePostal}" default="Inconnu" /></p>

					<p>Ville :</p>
					<p><c:out value="${utilisateur.adresse.ville}" default="Inconnu" /></p>

				</div>
				<c:if test="${utilisateurConnecte.no_utilisateur == userId}"><a class="btn" href="compte/modifier"/>Modifier</a></c:if>
				
				
			</div>
			
		</main>
		
		 <!-- FOOTER -->
	    <jsp:include page="/WEB-INF/template-parts/footer.jsp">
	    	<jsp:param value="<%= pageAuthor %>" name="pageAuthor"/>
	    </jsp:include>
    

	</body>

</html>
