var ejercicios;
var niveles;

document.addEventListener('DOMContentLoaded', function (evento) {
    ejercicios = document.querySelector('.js-filter');
    niveles = document.querySelectorAll('.uk-subnav-alumno li:not(:first-child)');
});


function buscaActividadSeleccionada(evento) {
    salida = [];
    seleccionados = ejercicios.querySelectorAll('input[type=radio]:checked');
    if (seleccionados === null || seleccionados.length === 0) {
        UIkit.modal.alert('Es necesario seleccionar una actvidad');
        return false;
    } else {
        seleccionados.forEach(function (seleccionado) {
            salida.push(seleccionado.value);
        });
        document.getElementById('formulario-asignar-tarea:input-actividad').value = salida.join(',');
        return true;
    }
}