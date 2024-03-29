<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Config de la page --%>
<%@ page 
language="java" 
contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"
%>

<%-- Variable page --%>
<c:set var="pageTitle" value="Connexion" />
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
				<jsp:param value="${listeErreurs}" name="listeErreurs"/>
			</jsp:include>
			
			<!-- CONNEXION FORM -->
			<div id="connexion" class="row">
				<form method="post" action="connexion">
					<fieldset>
						<label for="identifiant">Identifiant :</label>
						<input type="text" name="identifiant" id="" required="required">
					</fieldset>
					
					<fieldset>
						<label for="password">Mot de passe : </label>
						<input type="password" name="password" id="" required="required">
					</fieldset>
	
					<fieldset id="checkbox_wrapper">
						<input type="checkbox" name="remember" id="">
						<label for="remember">Se souvenir de moi</label>
					</fieldset>
	
					<input type="submit" value="Connexion" class="btn">
				</form>
			</div>
			
		</main>
		
		 <!-- FOOTER -->
	    <jsp:include page="/WEB-INF/template-parts/footer.jsp">
	    	<jsp:param value="${pageAuthor}" name="pageAuthor"/>
	    </jsp:include>
    

	</body>

</html>
