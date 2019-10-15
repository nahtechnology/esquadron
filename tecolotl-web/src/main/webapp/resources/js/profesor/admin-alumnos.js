var tabla;
var alumnos = [];

document.addEventListener('DOMContentLoaded', function (evento) { 
    tabla = document.querySelector('.uk-container table');
    cargaFilas(tabla.querySelector('tbody'));
});


function cargaFilas(tbody) {
    console.log(tbody);
    var alumno = new Alumno(1);
    alumnos.push(alumno)
}

function Alumno(id, nombre, totalTarea, totalRespuesta, nombreActividad) {
    this.id = id;
    this.nombre = nombre;
    this.totalTarea = totalTarea;
    this.totalRespuesta = totalRespuesta;
    this.nombreActividad = nombreActividad
}