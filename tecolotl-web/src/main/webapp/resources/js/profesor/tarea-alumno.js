var progreso, barra = [];

document.addEventListener('DOMContentLoaded',function (ev) {
   progreso = document.getElementById("formulario-tabla-actividades:tabla-actividades");
   barrasValor();
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
       valor.innerHTML = '%'+ progres.querySelector('progress').value;
       progres.insertBefore(valor,progres.querySelector('progress'));
    });

}