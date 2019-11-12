var alumno = document.querySelector('.alumno');
var profesor = document.querySelector('.profesor');

document.addEventListener('DOMContentLoaded', function (evt) {
    loginImagen(alumno.querySelectorAll('img'));
});

function loginImagen(imagenes) {
    imagenes.forEach(function (imagen) {
        imagen.addEventListener("click", function (evento) {
            imagen.classList.toggle('seleccionado');
        });
    });
}

function cargaContrasenia(imagenes) {
    seleccionados = [];
    for (i = 0; i < imagenes.length; i++) {
        if (imagenes[i].classList.contains('seleccionado')) {
            x = Math.floor(i / 7);
            y = i % 7;
            seleccionados.push(x.toString().concat(':').concat(y.toString()));
        }
    }
}