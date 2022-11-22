<div id="filter" class="row">
	<form id="search_filter" method="post" action="">
		<input type="text" placeholder="Rechercher" list="products_list">
		<datalist id="products_list">
			<option value="Aspirateur">
			<option value="Vélo">
			<option value="Appareil photo">
		</datalist>
		<select id="categories">
			<option value="informatique">Informatique</option>
			<option value="ameublement">Ameublement</option>
			<option value="vetement">Vêtement</option>
			<option value="sport_loisirs">Sports & Loisirs</option>
		</select>
		<button type="submit" class="btn">Rechercher</button>
	</form>

	<form action="index.html" method="post" id="categorie_filter">
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
	
</div>
