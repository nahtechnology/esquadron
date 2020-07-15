var progreso, barra = [];

document.addEventListener('DOMContentLoaded',function (ev) {
   progreso = document.getElementById("formulario-tabla-actividades:tabla-actividades");
   barrasValor();
   var botonReporte = document.querySelector('#reporte');
    var idProfesor = document.querySelector('#idProfesor').innerText;
    botonReporte.addEventListener('click',()=>{
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        var alumno = urlParams.get('idAlumno');
        var hiddenElement = document.createElement('a');
        console.log(alumno);
        hiddenElement.href = "http://reportes.e-squadron.com.mx/reporte-calificaciones?grupo=".concat(idProfesor,"&alumno=",alumno);
        console.log("http://reportes.e-squadron.com.mx/reporte-calificaciones?grupo=".concat(idProfesor,"&alumno=",alumno));
        hiddenElement.click();
    });
});

function barrasValor() {
    let filas = progreso.querySelectorAll('tbody tr > td');
    filas.forEach(function (fila) {
       if(fila.querySelector('progress')){
           barra.push(fila);
       }
    });
    barra.forEach(function (progres) {
        let valor = document.createElement('span');
       valor.innerHTML = progres.querySelector('progress').value + '%';
       progres.insertBefore(valor,progres.querySelector('progress'));
    });
}