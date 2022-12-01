<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Variables --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}/" />

<!-- FILTERS -->
<div id="filter" class="row">
	
	<!-- SEARCH -->
	<form id="search_filter" method="post" action="${contextPath}">
		
		<input type="text" name="searchPattern" value="${! empty searchPattern ? searchPattern : ''}" placeholder="Rechercher" list="products_list" >
		
		<datalist id="products_list">
			<option value="Casquette">
			<option value="Vélo">
			<option value="Pantalon">
		</datalist>
		
		<select id="categories" name="selectedCategorie">
			<option value="0" ${selectedCategorieId == "" ? "selected" : ""}>Toutes les catégories</option>
			<c:if test="${! empty listeCategories}">
				<c:forEach var="categorie" items="${listeCategories}">
					<option value="${categorie.noCategorie}" ${selectedCategorieId == categorie.noCategorie ? "selected" : ""}>${categorie.libelle}</option>
				</c:forEach>
			</c:if>
		</select>
		
		<button type="submit" class="btn">Rechercher</button>
		
	</form>
	
	<c:if test="${connecte}">
	<!-- USER FILTERS -->
	<form action="${contextPath}" method="post" id="user_filters">
	
		<div>
			<h4>Enchères</h4>
			<fieldset>
				<input type="radio" name="userFilter" value="openEncheres" class="categorie_checkboxes" ${userFilterAttribute == "openEncheres" ? "checked" : ""}>
				<label for="touteEnchères">Toutes les enchères ouvertes</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="userFilter" value="currentEncheres" class="categorie_checkboxes" ${userFilterAttribute == "currentEncheres" ? "checked" : ""}>
				<label for="mesEnchèresEnCours">Mes enchères en cours</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="userFilter" value="winEncheres" class="categorie_checkboxes" ${userFilterAttribute == "winEncheres" ? "checked" : ""}>
				<label for="mesEnchèresRemportees">Mes enchères remportées</label>
			</fieldset>
		</div>
		
		<div>
			<h4>Ventes</h4>
			<fieldset>
				<input type="radio" name="userFilter" value="notStartSells" class="categorie_checkboxes" ${userFilterAttribute == "notStartSells" ? "checked" : ""}>
				<label for="mesEnchèresEnCours">Mes ventes non débutées</label>
			</fieldset>
			
			<fieldset>
				<input type="radio" name="userFilter" value="currentSells" class="categorie_checkboxes" ${userFilterAttribute == "currentSells" ? "checked" : ""}>
				<label for="ventesEnCours">Mes ventes en cours</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="userFilter" value="endingSells" class="categorie_checkboxes" ${userFilterAttribute == "endingSells" ? "checked" : ""}>
				<label for="ventesTerminees">Mes ventes terminées</label>
			</fieldset>
		</div>
		
		<span id="remove_filter">Retirer les filtres</span>
		
	</form>
	</c:if>
	
</div>

<!-- JS -->
<script src="${contextPath}/assets/js/script-dist.js"></script>
