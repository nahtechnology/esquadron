var contenedorOraciones;
var oraciones;
var palabras = [];
var contenedorOracion;

document.addEventListener('DOMContentLoaded', function (evt) {
    contenedorOraciones = document.querySelector('.menu-ordenar + ul');
    sentences(contenedorOraciones);
});
function sentences(contenedor) {
    oraciones = contenedor.querySelectorAll('li .estilo-contenedor span');
    contenedorOracion = contenedor.querySelectorAll('li .estilo-contenedor');
    oraciones.forEach(function (palabra) {
       var  oracion = new PalabrasOracion(palabra.innerHTML);
       palabras.push(oracion);
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