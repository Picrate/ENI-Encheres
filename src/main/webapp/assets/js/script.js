/*
* Filters scripts
*/
var checkboxesArray = document.querySelectorAll('.categorie_checkboxes');
var form = document.getElementById('user_filters');

// create click listener on radio filters
checkboxesArray.forEach(element => {
	element.addEventListener("click", evt => {
		form.submit();
	});
});

// remove filter
var removeFilterButton = document.getElementById('remove_filter');
removeFilterButton.addEventListener("click", evt => {
	checkboxesArray.forEach(element => {
		element.removeAttribute('checked');
	});
	form.submit();
});