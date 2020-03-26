var tabla;
var alumnos = [];

var tablaAlumnos = document.getElementById('formulario-tabla-alumno:tabla-alumno');

document.addEventListener('DOMContentLoaded', function (evento) { 
    tabla = document.querySelector('.uk-container table');
    //formularioNuevoAlumno.querySelector('.uk-modal-body div:nth-child(5) input').addEventListener('input', entradaFecha);
    if(document.querySelector('#tabla-vacia') === null){
        desabilitar();
        botonResporte.disabled = false;
        botonResporte.classList.remove('boton-disabilitado');

    }else{
        botonResporte.disabled = true;
        botonResporte.classList.add('boton-disabilitado');
    }

});


function limpiaFormulario(evento) {
    document.querySelectorAll('#formulario-modal-nuevo-profesor input[type=text], input[type=hidden]').forEach(function (entrada) {
        entrada.value = null;
    });
    document.querySelectorAll('#formulario-modal-nuevo-profesor span.uk-alert-danger').forEach(function (error) {
        error.remove();
    })
}



function desabilitar() {
    var filas = tablaAlumnos.querySelectorAll('tbody tr');
    filas.forEach(function (row) {
        var celdas = row.querySelectorAll('td');
        if(celdas[1].innerText === "" && celdas[2].innerText === ""  && celdas[3].innerText === ""  && celdas[4].innerText === "" && celdas[5].innerText === "" && celdas[6].innerText === ""){
             celdas[7].querySelector('button').disabled = true;
        }
    });

}

function error(data) {
    console.error(data);
}