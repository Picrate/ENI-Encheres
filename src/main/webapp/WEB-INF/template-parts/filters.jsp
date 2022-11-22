<div id="filter" class="row">
	<form id="search_filter" method="post" action="">
		<input type="text" placeholder="Rechercher" list="products_list">
		<datalist id="products_list">
			<option value="Aspirateur">
			<option value="V�lo">
			<option value="Appareil photo">
		</datalist>
		<select id="categories">
			<option value="informatique">Informatique</option>
			<option value="ameublement">Ameublement</option>
			<option value="vetement">V�tement</option>
			<option value="sport_loisirs">Sports & Loisirs</option>
		</select>
		<button type="submit" class="btn">Rechercher</button>
	</form>

	<form action="index.html" method="post" id="categorie_filter">
		<div>
			<h4>Achats</h4>
			<fieldset>
				<input type="checkbox" name="touteEncheres" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="touteEnch�res">ench�res ouvertes</label>
			</fieldset>

			<fieldset>
				<input type="checkbox" name="mesEncheresEnCours" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="mesEnch�resEnCours">mes ench�res en cours</label>
			</fieldset>

			<fieldset>
				<input type="checkbox" name="mesEncheresRemportees" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="mesEnch�resRemportees">mes ench�res remport�es</label>
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
				<label for="mesEnch�resEnCours">ventes non d�but�es</label>
			</fieldset>

			<fieldset>
				<input type="checkbox" name="ventesTerminees" class="categorie_checkboxes" onclick="document.getElementById('categorie_filter').submit();">
				<label for="ventesTerminees">ventes termin�es</label>
			</fieldset>
		</div>
	</form>
	
</div>
