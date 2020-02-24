var tabla;
var alumnos = [];
var botonResporte = document.querySelector('.uk-container button.reporte');
var tablaAlumnos = document.getElementById('formulario-tabla-alumno:tabla-alumno');

document.addEventListener('DOMContentLoaded', function (evento) { 
    tabla = document.querySelector('.uk-container table');
    //formularioNuevoAlumno.querySelector('.uk-modal-body div:nth-child(5) input').addEventListener('input', entradaFecha);
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


function limpiaFormulario(evento) {
    document.querySelectorAll('#formulario-modal-nuevo-profesor input[type=text], input[type=hidden]').forEach(function (entrada) {
        entrada.value = null;
    });
    document.querySelectorAll('#formulario-modal-nuevo-profesor span.uk-alert-danger').forEach(function (error) {
        error.remove();
    })
}

function generarReporte(evento) {
    // console.log(evento);
    // tabla = document.getElementById('formulario-tabla-alumno:tabla-alumno');
    // csv = Array.from(tabla.tHead.rows[0].cells).map(th => th.textContent).join(',').concat('\n');
    // console.log(csv);
    // for (i = 0; i < tabla.tBodies[0].rows.length; i++) {
    //     csv += Array.from(tabla.tBodies[0].rows[i].cells).map(td => td.querySelector('span').textContent.trim() === '' ? 'N/A' : td.querySelector('span').textContent).join(',').concat('\n');
    // }
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var grupo = urlParams.get('grupo');
    var hiddenElement = document.createElement('a');
    console.log(grupo);
    hiddenElement.href = "http://reportes.e-squadron.com.mx/reporte-grupo?grupo=".concat(grupo);
    // hiddenElement.target = '_blank';
    // hiddenElement.download = 'activityreport_group' + evento.target.dataset.grado + evento.target.dataset.grupo + '.pdf';
    hiddenElement.click();
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