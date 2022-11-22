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
String pageTitle = "Compte";
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
			 
			<!-- COMPTE-->
			<div id="connexion" class="row">
				<form>
					<div class="col_2">
						<fieldset>
							<label for="pseudo">Pseudo :</label>
							<input type="text" name="pseudo">
						</fieldset>
						
						<fieldset>
							<label for="nom">Nom : </label>
							<input type="text" name="nom">
						</fieldset>
				
						<fieldset>
							<label for="prenom">Prénom :</label>
							<input type="text" name="prenom">
						</fieldset>

						<fieldset>
							<label for="email">Email :</label>
							<input type="email" name="email">
						</fieldset>

						<fieldset>
							<label for="telephone">Téléphone :</label>
							<input type="tel" name="telephone">
						</fieldset>

						<fieldset>
							<label for="rue">Rue :</label>
							<input type="text" name="rue">
						</fieldset>

						<fieldset>
							<label for="codePostal">Code postal :</label>
							<input type="text" name="codePostal">
						</fieldset>

						<fieldset>
							<label for="ville">Ville :</label>
							<input type="text" name="ville">
						</fieldset>
						
						<fieldset>
							<label for="password">Mot de passe :</label>
							<input type="password" name="password">
						</fieldset>

						<fieldset>
							<label for="password">Confirmation :</label>
							<input type="password" name="password">
						</fieldset>
					</div>

					<!-- <input type="submit" value="Créer" class="btn"> -->
					<a class="btn" href="<%=request.getContextPath()%>/profil-utilisateur">Créer</a>
					<a class="btn outline" href="<%=request.getContextPath()%>">Annuler</a>
				</form>
			</div>
			
		</main>
		
		 <!-- FOOTER -->
	    <jsp:include page="/WEB-INF/template-parts/footer.jsp">
	    	<jsp:param value="<%= pageAuthor %>" name="pageAuthor"/>
	    </jsp:include>
    

	</body>

</html>
