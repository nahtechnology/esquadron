var contenedorRespuesta , parrafo,respuestas = [];
var contenedorPalabras = document.querySelector('.contenedor-respuesta')

document.addEventListener('DOMContentLoaded', function (evt) {
     contenedorRespuesta = document.querySelector('.trancript-contenedor');
        parrafos(contenedorRespuesta);
});

function parrafos(texto) {

     parrafo = texto.querySelectorAll('p .remover');
     parrafo.forEach(function (resp,indice) {
         respuestas.push(resp.innerHTML);
         resp.classList.remove('remover');
         resp.classList.add('uk-sortable');
         resp.classList.add('respuesta-transcript');
         resp.setAttribute('uk-sortable','group: respuesta');
         resp.innerHTML=" ";
     });
     revolver(respuestas).forEach(function (texto) {
         console.log(texto);
         var palabra = document.createElement('span');
         palabra.innerHTML=texto;
         palabra.classList.add('respuesta-drag');
         contenedorPalabras.appendChild(palabra);
     });
}

function revolver(arreglo) {
    var indiceActual = arreglo.length, temporal, indiceAleatorio;
    while (0 !== indiceActual) {
        indiceAleatorio = Math.floor(Math.random() * indiceActual);
        indiceActual -= 1;
        temporal = arreglo[indiceActual];
        arreglo[indiceActual] = arreglo[indiceAleatorio];
        arreglo[indiceAleatorio] = temporal;
    }
    return arreglo;
}