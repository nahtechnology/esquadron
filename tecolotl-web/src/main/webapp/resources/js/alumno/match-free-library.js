var contenedorPalabras;
var words,letra=[];
document.addEventListener('DOMContentLoaded', function (evt) {
    contenedorPalabras = document.querySelector('.respuesta-imagen');
    palabras(contenedorPalabras);
});

function palabras(contenedor) {
    words = contenedor.querySelectorAll('.palabra-imagen');
    words.forEach(function (palabra) {
        letra.push(palabra.innerText)
    });
    cajas(letra);

}

function cajas(texto) {

    revolver(texto).forEach(function (palabra,indice) {
        words[indice].innerHTML = palabra;
    })
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