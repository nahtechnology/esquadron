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
    } else {
        evento.preventDefault();
        seleccionados.forEach(function (seleccionado) {
            salida.push(seleccionado.value);
        });
        if (salida.length !== niveles.length) {
            UIkit.modal.confirm('Al parecer hay alumnos sin asginar actvidad, desea asignar la(s) actvidad(es) seleccionada(s)').then(function () {
                document.getElementById('formulario-asignar-tarea:input-actividad').value = salida.join(',');
                document.getElementById('formulario-asignar-tarea:boton-enviar').click();
            }, function () {
                console.log('Cancelacion de envio');
            });
        } else {
            document.getElementById('formulario-asignar-tarea:input-actividad').value = salida.join(',');
            document.getElementById('formulario-asignar-tarea:boton-enviar').click();
        }
    }
}