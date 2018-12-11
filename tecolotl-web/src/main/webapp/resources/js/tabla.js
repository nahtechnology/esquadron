var paginacion = 5;

document.addEventListener('DOMContentLoaded', function (evt) {
	var datos = document.querySelector('.uk-table').rows;
	for (var i = paginacion; i <= datos.length; i++) {
		console.log(i)
		datos[i].remove();
	}
});