"use strict";

console.log('test');
var checkboxesArray = document.querySelectorAll('.categorie_checkboxes');
console.log(checkboxesArray.lenght);
var form = document.getElementById('categorie_filter');
checkboxesArray.forEach(function (element) {
  element.addEventListener("click", function (evt) {
    alert("form submit");
    form.submit();
    console.log('test');
  });
});