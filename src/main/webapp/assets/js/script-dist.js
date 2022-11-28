"use strict";

/*
* Filters scripts
*/
var checkboxesArray = document.querySelectorAll('.categorie_checkboxes');
var form = document.getElementById('user_filters');

// create click listener on radio filters
checkboxesArray.forEach(function (element) {
  element.addEventListener("click", function (evt) {
    form.submit();
  });
});

// remove filter
var removeFilterButton = document.getElementById('remove_filter');
removeFilterButton.addEventListener("click", function (evt) {
  checkboxesArray.forEach(function (element) {
    element.removeAttribute('checked');
  });
});