document.addEventListener('DOMContentLoaded', function (evt) {
    var imagenes = document.querySelectorAll('.contenedor img');
    imagenes.forEach(function (imagen) {
        imagen.addEventListener("click", function (evento) {
            imagen.classList.contains('seleccionado')  ? imagen.classList.remove('seleccionado') :imagen.classList.add('seleccionado');
        });
    });
});