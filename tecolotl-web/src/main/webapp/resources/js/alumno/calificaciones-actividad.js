var palabrasOrdenarContenedor = document.querySelector('.order-words');
document.addEventListener("DOMContentLoaded",() => {
    //calificacion de order-words
    palabrasOrdenarContenedor.querySelectorAll('#contenedor-words > li').forEach(nodoVerificacion => {
       if (nodoVerificacion.dataset.activado === "true"){
           calificarOrdenarOracion(nodoVerificacion);
       }
    });

});

function calificarOrdenarOracion(nodo) {
    let oracionCorrecta = nodo.querySelector('span.visibilidad').innerText;
    let contenedorRespuestas = nodo.querySelector('.contenedor-respuesta-correcta');
    let oracionesAlumno = nodo.querySelectorAll('.estilo-contenedor > div  .palabra');
    let palabras = oracionCorrecta.split(" ");
    oracionesAlumno.forEach((oracion,index) => {
        let respuesta = oracion.innerText.trim().toLowerCase() === palabras[index].trim().toLowerCase() ? "correcto":"incorrecto";
        oracion.classList.add(respuesta);
    });
    switch (parseInt(nodo.querySelector('.score span').innerText)) {
        case 1 :
            nodo.querySelector('.score span').innerText = "Score: 25%";
            contenedorRespuestas.classList.remove('visibilidad');
            break;
        case 2:
            nodo.querySelector('.score span').innerText = "Score: 50%";
            contenedorRespuestas.classList.remove('visibilidad');
            break;
        case 3:
            nodo.querySelector('.score span').innerText = "Score: 75%";
            contenedorRespuestas.classList.remove('visibilidad');
            break;
        case 4:
            nodo.querySelector('.score span').innerText = "Score: 100%";
            break;
    }

}