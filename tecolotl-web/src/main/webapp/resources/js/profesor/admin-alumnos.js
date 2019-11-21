var tabla;
var alumnos = [];
var formularioNuevoAlumno = document.getElementById('formulario-modal-nuevo-profesor');
var botonResporte = document.querySelector('.uk-container button.reporte');
var tablaAlumnos = document.getElementById('formulario-tabla-alumno:tabla-alumno');

document.addEventListener('DOMContentLoaded', function (evento) { 
    tabla = document.querySelector('.uk-container table');
    seleccionContrasenia();
    formularioNuevoAlumno.querySelector('.uk-modal-body div:nth-child(5) input').addEventListener('input', entradaFecha);
    botonResporte.addEventListener('click', generarReporte);
    if(document.querySelector('#tabla-vacia') === null){
        desabilitar();
        botonResporte.disabled = false;
        botonResporte.classList.remove('boton-disabilitado');

    }else{
        botonResporte.disabled = true;
        botonResporte.classList.add('boton-disabilitado');
    }

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

function generarReporte(evento) {
    console.log(evento);
    tabla = document.getElementById('formulario-tabla-alumno:tabla-alumno');
    csv = Array.from(tabla.tHead.rows[0].cells).map(th => th.textContent).join(',').concat('\n');
    console.log(csv);
    for (i = 0; i < tabla.tBodies[0].rows.length; i++) {
        csv += Array.from(tabla.tBodies[0].rows[i].cells).map(td => td.querySelector('span').textContent.trim() === '' ? 'N/A' : td.querySelector('span').textContent).join(',').concat('\n');
    }
    var hiddenElement = document.createElement('a');
    hiddenElement.href = 'data:text/csv;charset=utf-8,' + encodeURI(csv);
    hiddenElement.target = '_blank';
    hiddenElement.download = 'activityreport_group' + evento.target.dataset.grado + evento.target.dataset.grupo + '.csv';
    hiddenElement.click();
}

function desabilitar() {
    var filas = tablaAlumnos.querySelectorAll('tbody tr');
    filas.forEach(function (row) {
        var celdas = row.querySelectorAll('td');
        if(celdas[1].innerText === "" && celdas[2].innerText === ""  && celdas[3].innerText === ""  && celdas[4].innerText === "" && celdas[5].innerText === "" && celdas[6].innerText === ""){
             celdas[7].querySelector('button').disabled = true;
        }
        // console.log(celdas);
    });

}
