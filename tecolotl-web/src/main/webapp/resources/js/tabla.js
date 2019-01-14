var paginacion = 5;
var filas;
var paginacion;

document.addEventListener('DOMContentLoaded', function (evt) {
	var datos = document.querySelector('#tabla-motivo-bloqueo').rows;
	filas = datos.slice(0);
	paginacion = document.querySelector('#paginacion-tabla-motivo-bloqueo');
	for (var i = paginacion; i <= datos.length; i++) {
		datos[i].remove();
	}
	var totalPaginas = Math.floor(filas / paginacion);
	if (totalPaginas > 0) {
		for (var pagina = 1; pagina <= totalPaginas; pagina++) {
			var li = document.createElement('li');
			li.appendChild(document.createTextNode(String(pagina)));
			if (pagina == 1) {
				continue;
			}

		}
	} else {

	}
});