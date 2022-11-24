<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Variables --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}/" />

<div id="filter" class="row">
	
	<form id="search_filter" method="post" action="${contextPath}">
		
		<input type="text" placeholder="Rechercher" list="products_list" name="searchPattern">
		
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
	
	<c:if test="${userConnected}">
	<form action="${contextPath}" method="post" id="categorie_filter">
	
		<div>
			<h4>Achats</h4>
			<fieldset>
				<input type="radio" name="achatsFilter" value="openEncheres" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="touteEnch�res">ench�res ouvertes</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="achatsFilter" value="currentEncheres" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="mesEnch�resEnCours">mes ench�res en cours</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="achatsFilter" value="winEncheres" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="mesEnch�resRemportees">mes ench�res remport�es</label>
			</fieldset>
		</div>
		
		<div>
			<h4>Mes ventes</h4>
			<fieldset>
				<input type="radio" name="venteFilter" value="currentSells" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="ventesEnCours">mes ventes en cours</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="venteFilter" value="notStartSells" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="mesEnch�resEnCours">ventes non d�but�es</label>
			</fieldset>

			<fieldset>
				<input type="radio" name="venteFilter" value="endingSells" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="ventesTerminees">ventes termin�es</label>
			</fieldset>
		</div>
		
	</form>
	</c:if>
	
</div>
