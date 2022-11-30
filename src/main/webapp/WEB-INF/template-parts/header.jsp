<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<header>
	<div>
		<h1><a href="<%=request.getContextPath()%>">ENI - Enchères</a></h1>


		<nav id="disconneted_nav" class="main_nav">
			<ul>				
				<c:choose>
						<c:when test="${connecte}">
							<li><a href="${contextPath}/ajouter-un-article">Vendre un article</a></li>
							
							<!--<li><a href="<%=request.getContextPath()%>/profil-utilisateur?userId=${utilisateurConnecte.no_utilisateur}">Mon Profil</a></li>  -->
							<li><a href="
							<c:url value="/profil-utilisateur">
								<c:param name="userId" value="${utilisateurConnecte.no_utilisateur}"></c:param>
							</c:url>">Mon Profil</a></li>
							
							<li><a href="<c:url value="/deconnexion"/>">Se déconnecter</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="connexion">Connexion</a></li>
							<li><a href="<c:url value="/compte"/>">Créer un compte</a><li>
						</c:otherwise>
					</c:choose>				
			</ul>
		</nav>

		<!-- <nav id="connected_nav" class="main_nav">
			<ul>
				<li><a href="vendre-article.html">Vendre un article</a></li>
				<li><a href="mon-profil.html">Créer un compte</a></li>
				<li><a href="connexion.html">Deconnexion</a></li>
				
			</ul>
		</nav>
		-->
	</div>
</header>