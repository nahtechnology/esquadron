var ejercicios;
var niveles;

document.addEventListener('DOMContentLoaded', function (evento) {
    ejercicios = document.querySelector('.js-filter');
    niveles = document.querySelectorAll('.uk-subnav-alumno li:not(:first-child)');
});


function buscaActividadSeleccionada(evento) {
    niveles.forEach(function (nivel) {
        seleccionados = ejercicios.querySelector('input[type=radio][name='+nivel.textContent+']:checked');
    });
    if (seleccionados.length === 0) {
        UIkit.modal.alert('Es necesario seleccionar una actvidad');
    }
    return false;
}