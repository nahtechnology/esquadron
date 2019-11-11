var contenedorOraciones;
var oraciones;
var palabras = [],copyPalabras = [];
var contenedorOracion;
var botonScores;
document.addEventListener('DOMContentLoaded', function (evt) {
    contenedorOraciones = document.querySelector('.menu-ordenar + ul');
    botonScores = document.querySelectorAll('.scroll-drag button.boton-enviar');
    sentences(contenedorOraciones);
    botonScores.forEach(function (boton,index) {
        boton.addEventListener('click',calificar);
    })
});
function sentences(contenedor) {
    oraciones = contenedor.querySelectorAll('li .estilo-contenedor span');
    contenedorOracion = contenedor.querySelectorAll('li .estilo-contenedor');
    oraciones.forEach(function (palabra) {
       var  oracion = new PalabrasOracion(palabra.innerHTML);
       var  copyOracion = new PalabrasOracion(palabra.innerHTML);
       palabras.push(oracion);
       copyPalabras.push(copyOracion);
    });
    separarPalabras();
}

function PalabrasOracion(texto) {
    this.texto = texto.split(" ");
}

function separarPalabras() {
    palabras.forEach(function (words,indice) {
        while (contenedorOracion[indice].hasChildNodes()){
            contenedorOracion[indice].removeChild(contenedorOracion[indice].firstChild);
        }
        var cadena = words.texto;
        var contenedorCaja = document.createElement('div');
        contenedorCaja.classList.add('uk-grid','uk-child-width-1-5@m','uk-padding-small');
        contenedorCaja.setAttribute('uk-grid','');
        contenedorCaja.setAttribute('uk-sortable','');
        // console.log(cadena);
        revolver(cadena).forEach(function (letras) {
            var caja = document.createElement('div');
            var cajaTexto = document.createElement('div');
            cajaTexto.innerHTML= letras;
            cajaTexto.classList.add('palabra');
            cajaTexto.classList.add('uk-text-center');
            caja.appendChild(cajaTexto);
            contenedorCaja.appendChild(caja);
        });
        contenedorOracion[indice].appendChild(contenedorCaja);
    });
}
function calificar() {
    var puntos = document.createElement('span');
    var cajaAnswer = document.querySelector('li.uk-active .estilo-contenedor');
    var cajaPuntaje = document.querySelector('li.uk-active #calificacion-respuesta');
    var boton = document.querySelector('li.uk-active .estilo-contenedor + div + div > button');
    var answer = cajaAnswer.querySelectorAll('.palabra');
    var indice = parseInt(boton.dataset.indice),contador=0,puntaje;
    console.log(answer);
    while (cajaPuntaje.hasChildNodes()){
        cajaPuntaje.removeChild(cajaPuntaje.firstChild);
    }
  answer.forEach(function (res,index) {
     if(copyPalabras[indice].texto[index] === res.innerHTML){
        res.style.backgroundColor="#2ECC71";
        contador++;
     }else{
         res.style.backgroundColor="#E74C3C";
     }
  });
    puntaje = Math.round((contador * 100) / answer.length);
    puntos.innerHTML = 'Score:' + puntaje+'%';
    cajaPuntaje.appendChild(puntos);
    cajaPuntaje.classList.remove('uk-hidden')

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