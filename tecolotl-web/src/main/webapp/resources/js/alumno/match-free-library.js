var contenedorPalabras;
var contenedorRespuestas;
var btnFinish = document.querySelector('form + div + div > input + input');
var words,letra=[],copyLetras,btnRespuesta;
document.addEventListener('DOMContentLoaded', function (evt) {
    contenedorPalabras = document.querySelector('.respuesta-imagen');
    btnRespuesta = document.querySelector('form + div > button');
    palabras(contenedorPalabras);
    btnRespuesta.addEventListener('click',calificar);
    btnFinish.disabled = true;
});

function palabras(contenedor) {
    words = contenedor.querySelectorAll('.palabra-imagen');
    words.forEach(function (palabra) {
        letra.push(palabra.innerText)
    });
    copyLetras = letra.slice();
    cajas(letra);

}

function cajas(texto) {

    revolver(texto).forEach(function (palabra,indice) {
        words[indice].innerHTML = palabra;
    })
}

function calificar() {
    var imgRespuesta = document.createElement('div');
    var resContent = document.createElement('span');
    var contPuntaje = document.querySelector('.respuesta-imagen');
    var answer = [],conta = 0,puntaje;
    var estiloContenedor = document.querySelectorAll('.contenedor-imagen .oraciones-relacion-img');
    contenedorRespuestas = document.querySelectorAll('.contenedor-imagen .oraciones-relacion-img .palabra-imagen');
    contenedorRespuestas.forEach(function (texto) {
        answer.push(texto.innerHTML);
    });
    if(answer.length !== letra.length){
        UIkit.modal.alert('Necesitas llenar todas las respuestas.');
        return false;
    }
    for (var indice = 0; indice < letra.length;indice++){
       if (answer[indice] === copyLetras[indice] ) {
           conta++;
           contenedorRespuestas[indice].style.backgroundColor="transparent";
           estiloContenedor[indice].style.backgroundColor="#2ECC71";
       }else {
           contenedorRespuestas[indice].style.backgroundColor="transparent";
           estiloContenedor[indice].style.backgroundColor="#E74C3C";
       }
    }

    puntaje = Math.round((conta*100)/letra.length);
    resContent.innerHTML= 'Score:' + puntaje + '%';
    imgRespuesta.appendChild(resContent);
    imgRespuesta.classList.add('puntaje');
    imgRespuesta.style.color="#2ECC71";
    contPuntaje.appendChild(imgRespuesta);
    btnFinish.disabled = false;
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