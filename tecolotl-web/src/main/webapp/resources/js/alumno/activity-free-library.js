var contenedorRespuesta , parrafo,respuestas = [], copyRespuesta = [],botonCalificar;
var contenedorPalabras = document.querySelector('.contenedor-respuesta');

document.addEventListener('DOMContentLoaded', function (evt) {
     contenedorRespuesta = document.querySelector('.trancript-contenedor');
        parrafos(contenedorRespuesta);
        botonCalificar = document.querySelector('.uk-flex-center button');
        botonCalificar.addEventListener('click',calificar)

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
     copyRespuesta = respuestas.slice();
     revolver(copyRespuesta).forEach(function (texto) {
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

function calificar() {
    var score=document.createElement('div');
    var cajaRespuestaAlumno = document.querySelectorAll('.trancript-contenedor .respuesta-drag');
    var respuestaAlumno = [],contador=0,puntaje;
    cajaRespuestaAlumno.forEach(function (respuesta,index) {
        respuestaAlumno.push(respuesta.innerHTML);
    });

    if(respuestaAlumno.length !== respuestas.length){
        UIkit.modal.alert('Necesitas llenar todas las respuestas.');
        return false;
    }

    //verificar que esten contestadas


    for (var inicio = 0; inicio < respuestas.length; inicio++){
        console.log(respuestaAlumno[inicio] + '=' + respuestas[inicio]);

        if(respuestaAlumno[inicio] === respuestas[inicio]){
            contador++;
            cajaRespuestaAlumno[inicio].style.backgroundColor="#2ECC71";
        }else {
            cajaRespuestaAlumno[inicio].style.backgroundColor="#E74C3C";
        }
    }
    puntaje = (contador * 100) / respuestas.length;
    score.innerHTML= 'Score: ' + Math.round(puntaje);
    score.classList.add('puntaje');
    document.querySelector('.contenedor-respuesta').appendChild(score);
}