var id = [],datosTabla = [];
var actividad = [] , nombreAlum = [];
var tabla = document.querySelector('.tabla-general');
var tablaDetalle = document.querySelector('#tabla-detalle');
var filas, alumnos = [],columnas;
document.addEventListener('DOMContentLoaded',function (ev) {
    filas = tabla.querySelector('tbody');
    cargarFilas(filas);
});

function cargarFilas(cuerpoFila) {
    var fila = cuerpoFila.querySelectorAll('tr');
    var tablaDetallerow = document.createElement('tr'),tablaDetallecolum = document.createElement('td'), tablaDetalletitle = document.createElement('th');
    fila.forEach(function (columna) {
        var celda = columna.querySelectorAll("td");

        var alumno = new Alumno(celda[0].innerText,celda[1].innerText,celda[2].innerText,celda[3].innerText,celda[4].innerText,celda[5].innerText);
        alumnos.push(alumno);
    });
    alumnos.forEach(function (persona) {
            if(!id.includes(persona.idAlumo)){
                id.push(persona.idAlumo);
            }
            if(!actividad.includes(persona.actividad)){
                actividad.push(persona.actividad);
            }
            if(!nombreAlum.includes(persona.nombre)){
                nombreAlum.push(persona.nombre);
            }
    });
    nombreAlum.forEach(function (nombreActividad,conteo) {
        total2 = [];
        alumnos.filter(alumno => alumno.idAlumo.localeCompare(id[conteo]) === 0).forEach(function (datos,inicio) {
            total2[inicio] = datos;
        });
        for (var init = 0; init < actividad.length; init++){
         var tareas = total2.filter(alumno => alumno.actividad.localeCompare(actividad[init]) === 0).reduce(function (acumilador, valorActual) {return acumilador + valorActual.totalTareas;},0);
        var respuestas = total2.filter(alumno => alumno.actividad.localeCompare(actividad[init]) === 0).reduce(function (acumilador, valorActual) {return acumilador + valorActual.totalRespuesta;},0);
        var detalletarea = total2.filter(alumno => alumno.actividad.localeCompare(actividad[init]) === 0);
        var contenidoTabla = new ActividadAlumno(nombreActividad,actividad[init],tareas,respuestas,detalletarea);
        datosTabla.push(contenidoTabla);
        }
    });
    crearTabla(datosTabla);
}

function Alumno(idTarea, actividad, idAlumno,nombre,totalTareas,totalRespuesta ) {
    this.idTarea = idTarea;
    this.actividad = actividad;
    this.idAlumo = idAlumno;
    this.nombre = nombre;
    this.totalTareas = parseInt(totalTareas);
    this.totalRespuesta = parseInt(totalRespuesta);
}

function ActividadAlumno(alumnoNombre,nombreActividad,actividadTareas,actividadRespuesta,actividadDetalle){
    this.alumnoNombre=alumnoNombre;
    this.nombreActividad = nombreActividad;
    this.actividadTarreas = actividadTareas;
    this.actividadRespuesta = actividadRespuesta;
    this.actividadDetalle = actividadDetalle;
}

function crearTabla(contenido) {
    var cuerpoTabla = tablaDetalle.querySelector('tbody');

    for(var i = 0 ; i<nombreAlum.length;i++){
        var row = cuerpoTabla.insertRow(0),totalTareas = 0, totalRespuestas = 0;
        var celda = [];
        celda[0] = row.insertCell(0),celda[1] = row.insertCell(1),celda[2] = row.insertCell(2),celda[3] = row.insertCell(3),celda[4] = row.insertCell(4),celda[5] = row.insertCell(5),celda[6] = row.insertCell(6);
        var filaTabla = [];
        var celdas = document.createElement("td");
        var progreso = document.createElement('progress');
            contenido.filter(dato => dato.alumnoNombre.localeCompare(nombreAlum[i]) === 0).forEach(function (alumno,index){
            if(index === 0) {
                filaTabla.push(alumno.alumnoNombre);
                filaTabla.push(alumno.actividadRespuesta  + " / " + alumno.actividadTarreas);
                totalTareas=alumno.actividadTarreas + totalTareas;
                totalRespuestas = alumno.actividadRespuesta +totalRespuestas;
            }
            if(index === 1) {
                filaTabla.push(alumno.actividadRespuesta  + " / " + alumno.actividadTarreas);
                totalTareas=alumno.actividadTarreas + totalTareas;
                totalRespuestas = alumno.actividadRespuesta +totalRespuestas;
            }
            if (index === 2) {
                filaTabla.push(alumno.actividadRespuesta  + " / " + alumno.actividadTarreas);
                totalTareas=alumno.actividadTarreas + totalTareas;
                totalRespuestas = alumno.actividadRespuesta +totalRespuestas;
            }
            if(index === 3) {
                filaTabla.push(alumno.actividadRespuesta  + " / " + alumno.actividadTarreas);
                totalTareas=alumno.actividadTarreas + totalTareas;
                totalRespuestas = alumno.actividadRespuesta +totalRespuestas;
            }
            if( index === 4) {
                filaTabla.push(alumno.actividadRespuesta  + " / " + alumno.actividadTarreas);
                totalTareas=alumno.actividadTarreas + totalTareas;
                totalRespuestas = alumno.actividadRespuesta +totalRespuestas;
            }
            if( index === 5) {
                filaTabla.push(alumno.actividadRespuesta  + " / " + alumno.actividadTarreas);
                totalTareas=alumno.actividadTarreas + totalTareas;
                totalRespuestas = alumno.actividadRespuesta +totalRespuestas;
            }
        });
        progreso.setAttribute("value", totalRespuestas);
        progreso.setAttribute("max",totalTareas);

        celda[0].innerHTML = filaTabla[0];
        celda[1].innerHTML = filaTabla[1];
        celda[2].innerHTML = filaTabla[2];
        celda[3].innerHTML = filaTabla[3];
        celda[4].innerHTML = filaTabla[4];
        celda[5].innerHTML = filaTabla[5];
        celda[6].innerHTML = filaTabla[6];

        cuerpoTabla.querySelector("tr").appendChild(celdas);
        celdas.appendChild(progreso);

    }

    tabla.remove();

}



