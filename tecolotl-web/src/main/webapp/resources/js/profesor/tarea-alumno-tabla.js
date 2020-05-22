document.addEventListener('DOMContentLoaded',()=>{
    let seleccionFiltro = document.querySelector('#filtro-nivel');
    let nivelesTabla = document.querySelectorAll('.tabla-alumnos td > img');
    nivelesTabla.forEach(nivel => {
        let nivelActividad = nivel.dataset.nivel;
        switch (nivelActividad) {
            case "A1":
                nivel.parentElement.parentElement.classList.add('hover-A1');
                break;
            case "A2":
                nivel.parentElement.parentElement.classList.add('hover-A2');
                break;
            case "B1":
                nivel.parentElement.parentElement.classList.add('hover-B1');
                break;
            case "B2":
                nivel.parentElement.parentElement.classList.add('hover-B2');
                break;
            case "C1":
                nivel.parentElement.parentElement.classList.add('hover-C1');
                break;
            case "C2":
                nivel.parentElement.parentElement.classList.add('hover-A1');
                break;
        }
    });

    seleccionFiltro.addEventListener('change',(evt)=>{
        let cuerpoTabla = document.querySelector('.tabla-alumnos');
        let filas = document.querySelectorAll('.tabla-alumnos tbody > tr');
        let nivel = `hover-${evt.target.value}`;
        if (cuerpoTabla.querySelector('tbody .fila-vacia') !== null ){
            let numeroFila = cuerpoTabla.querySelector('tbody .fila-vacia').rowIndex;
            cuerpoTabla.deleteRow(numeroFila);
        }
        cuerpoTabla.classList.add('visibilidad-fila');
        setTimeout(animacionFilas,500,filas,nivel,cuerpoTabla);
    });

});

function animacionFilas(filas,nivel,tabla) {
    let cuenta = 0;
    let cuerpo = tabla.querySelector('tbody');
    console.log(nivel);
    filas.forEach(fila => {
        if (nivel === "hover-todos"){
            cuenta = 1;
            fila.style.display="table-row"
        }else if (!fila.classList.contains(nivel)){
            fila.style.display="none";
         }else {
            cuenta = 1;
            fila.style.display="table-row";
         }
    });
    if (cuenta === 0){
        let row = cuerpo.insertRow(0);
        let cel1 = row.insertCell(0);
        cel1.colSpan = 9;
        let img = document.createElement('img');
        img.src ="https://tecolotl-multimedia.nyc3.digitaloceanspaces.com/Tecolotl/resources/img/vacio.svg";
        cel1.appendChild(img);
        row.classList.add('fila-vacia');
    }
    tabla.classList.remove('visibilidad-fila');
}
