<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Variables --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}/" />

<div id="filter" class="row">
	
	<form id="search_filter" method="post" action="${contextPath}">
		
		<input type="text" name="searchPattern" value="${! empty searchPattern ? searchPattern : ''}" placeholder="Rechercher" list="products_list" >
		
		<datalist id="products_list">
			<option value="Casquette">
			<option value="V�lo">
			<option value="Pantalon">
		</datalist>
		
		<select id="categories" name="selectedCategorie">
			<option value="0" ${selectedCategorieId == "" ? "selected" : ""}>Toutes les cat�gories</option>
			<c:if test="${! empty listeCategories}">
				<c:forEach var="categorie" items="${listeCategories}">
					<option value="${categorie.noCategorie}" ${selectedCategorieId == categorie.noCategorie ? "selected" : ""}>${categorie.libelle}</option>
				</c:forEach>
			</c:if>
		</select>
		
		<button type="submit" class="btn">Rechercher</button>
		
	</form>
	
	<c:if test="${connecte}">
	<form action="${contextPath}" method="post" id="user_filters">
	
		<div>
			<h4>Mes ench�res</h4>
			<fieldset>
				<input type="radio" name="userFilter" value="openEncheres" class="categorie_checkboxes" ${userFilterAttribute == "openEncheres" ? "checked" : ""} onclick="document.getElementById('user_filters').submit();" >
				<label for="touteEnch�res">Toutes les ench�res ouvertes</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="userFilter" value="currentEncheres" class="categorie_checkboxes" ${userFilterAttribute == "currentEncheres" ? "checked" : ""} onclick="document.getElementById('user_filters').submit();">
				<label for="mesEnch�resEnCours">Mes ench�res en cours</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="userFilter" value="winEncheres" class="categorie_checkboxes" ${userFilterAttribute == "winEncheres" ? "checked" : ""} onclick="document.getElementById('user_filters').submit();">
				<label for="mesEnch�resRemportees">Mes ench�res remport�es</label>
			</fieldset>
		</div>
		
		<div>
			<h4>Mes ventes</h4>
			<fieldset>
				<input type="radio" name="userFilter" value="currentSells" class="categorie_checkboxes" ${userFilterAttribute == "currentSells" ? "checked" : ""} onclick="document.getElementById('user_filters').submit();" >
				<label for="ventesEnCours">Mes ventes en cours</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="userFilter" value="notStartSells" class="categorie_checkboxes" ${userFilterAttribute == "notStartSells" ? "checked" : ""} onclick="document.getElementById('user_filters').submit();">
				<label for="mesEnch�resEnCours">Ventes non d�but�es</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="userFilter" value="endingSells" class="categorie_checkboxes" ${userFilterAttribute == "endingSells" ? "checked" : ""} onclick="document.getElementById('user_filters').submit();">
				<label for="ventesTerminees">Ventes termin�es</label>
			</fieldset>
		</div>
		
	</form>
	</c:if>
	
</div>
