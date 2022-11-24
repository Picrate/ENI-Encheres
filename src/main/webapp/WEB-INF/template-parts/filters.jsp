<%-- Librairies --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Variables --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div id="filter" class="row">
	
	
	<form id="search_filter" method="post" action="${contextPath}">
		
		<input type="text" placeholder="Rechercher" list="products_list">
		
		<datalist id="products_list">
			<option value="Casquette">
			<option value="Vélo">
			<option value="Pantalon">
		</datalist>
		
		<select id="categories" name="selectedCategorie">
			<c:if test="${! empty listeCategories}">
				<c:forEach var="categorie" items="${listeCategories}">
					<option value="${categorie.noCategorie}">${categorie.libelle}</option>
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
				<input type="checkbox" name="touteEncheres" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="touteEnchères">enchères ouvertes</label>
			</fieldset>

			<fieldset>
				<input type="checkbox" name="mesEncheresEnCours" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="mesEnchèresEnCours">mes enchères en cours</label>
			</fieldset>

			<fieldset>
				<input type="checkbox" name="mesEncheresRemportees" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="mesEnchèresRemportees">mes enchères remportées</label>
			</fieldset>
		</div>

		<div>
			<h4>Mes ventes</h4>
			<fieldset>
				<input type="checkbox" name="ventesEnCours" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="ventesEnCours">mes ventes en cours</label>
			</fieldset>

			<fieldset>
				<input type="checkbox" name="ventesNonDebutees" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="mesEnchèresEnCours">ventes non débutées</label>
			</fieldset>

			<fieldset>
				<input type="checkbox" name="ventesTerminees" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="ventesTerminees">ventes terminées</label>
			</fieldset>
		</div>
		
	</form>
	</c:if>
	
</div>
