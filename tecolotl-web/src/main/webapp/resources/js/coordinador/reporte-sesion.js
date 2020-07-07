document.addEventListener("DOMContentLoaded",()=>{
    var tabla = document.querySelector('#contenedor-tabla > table');
    acomodarTabla(tabla);
});

function acomodarTabla(tabla) {
    let encabezado = tabla.createTHead();
    let titulos = ["fecha","inicio","fecha","cierre"];
    let nodos = tabla.parentElement.querySelectorAll('table ~ *');
    for (let i = 0; i < 4 ; i++) {
        let titulo = document.createElement("th");
        titulo.innerText = titulos[i];
        encabezado.appendChild(titulo);
    }
    let filas = tabla.querySelectorAll('tbody > tr');
    let cuerpoTabla = tabla.querySelector('tbody');
    let celdasFila = [];
    while (tabla.querySelector('tbody').hasChildNodes()){
        tabla.querySelector('tbody').removeChild(tabla.querySelector('tbody').firstChild);
    }
    if (nodos.length !== 0){
        nodos.forEach(nodo =>{
           nodo.remove();
        });
    }
    filas.forEach((fila,index) => {
        if((index+1) % 2 === 0){
            fila.querySelectorAll('td').forEach(celda =>{
                celdasFila.push(celda);
            });
            let row = cuerpoTabla.insertRow(-1);
            celdasFila.forEach(celda => {
               row.appendChild(celda);
            });
            celdasFila = [];
        }else {
            fila.querySelectorAll('td').forEach(celda =>{
               celdasFila.push(celda);
            });
        }
    });

}

function validarFecha() {
    let input = document.querySelector('#rango-fecha > input:last-of-type');
    let fechaActual = new Date();
    let diaActual = fechaActual.getDate();
    let mesActual = fechaActual.getMonth()+1;
    let anioActual = fechaActual.getFullYear();
    let diaSeleccion = input.value.split('/')[0];
    let mesSeleccion = input.value.split('/')[1];
    let anioSeleccion = input.value.split('/')[2];
    if ( parseInt(mesSeleccion) < mesActual && parseInt(anioSeleccion) <= anioActual ){
        console.log('entro 1');
            return true
    }else if (parseInt(mesSeleccion) === mesActual && parseInt(diaSeleccion)<= diaActual)  {
        console.log('entro 2');
         return true;
    }else {
        UIkit.modal.alert(`No se puede seleccionar una fecha superior a la actual: ${diaActual}/${mesActual}/${anioActual}`);
        return false
    }

}