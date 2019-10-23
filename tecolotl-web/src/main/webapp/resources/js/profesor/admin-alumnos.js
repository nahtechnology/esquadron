var tabla;
var alumnos = [];
var formularioNuevoAlumno = document.getElementById('formulario-modal-nuevo-profesor');

document.addEventListener('DOMContentLoaded', function (evento) { 
    tabla = document.querySelector('.uk-container table');
    cargaFilas(tabla.querySelector('tbody'));
    seleccionContrasenia();
    formularioNuevoAlumno.querySelector('.uk-modal-body div:nth-child(5) input').addEventListener('input', entradaFecha);
});


function seleccionContrasenia(data) {
    if (typeof data === 'undefined') {
        formularioNuevoAlumno.querySelectorAll('.uk-modal-body div:nth-child(7) img').forEach(function (imagen) {
            imagen.addEventListener("click", function (evento) {
                imagen.classList.toggle('seleccionado');
            });
        });
        formularioNuevoAlumno.querySelector('.uk-modal-body div:nth-child(5) input').addEventListener('input', entradaFecha);
    } else {
        if (data.status === 'success') {
            formularioNuevoAlumno.querySelectorAll('.uk-modal-body div:nth-child(7) img').forEach(function (imagen) {
                imagen.addEventListener("click", function (evento) {
                    imagen.classList.toggle('seleccionado');
                });
            });
            formularioNuevoAlumno.querySelector('.uk-modal-body div:nth-child(5) input').addEventListener('input', entradaFecha);
        }
    }
}

function cargaContrasenia() {
    var imagenes = formularioNuevoAlumno.querySelectorAll('.uk-modal-body div:nth-child(7) img');
    var seleccionados = [];
    for (i = 0; i < imagenes.length; i++) {
        if (imagenes[i].classList.contains('seleccionado')) {
            x = Math.floor(i / 7);
            y = i % 7;
            seleccionados.push(x.toString().concat(':').concat(y.toString()));
        }
    }
    document.getElementById('formulario-modal-nuevo-profesor:input-secret-password').value = seleccionados.length === 0 ? null : seleccionados.join(',');
}

function limpiaFormulario(data) {
    if (data.status === 'success') {
        formularioNuevoAlumno.querySelectorAll('input[type=text], input[type=hidden]').forEach(function (entrada) {
            entrada.value = null;
        });
    }
}