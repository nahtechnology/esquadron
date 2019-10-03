var menu = document.querySelector('.uk-container .uk-subnav');
var video = document.querySelector('.uk-container .js-filter');
var relacionar = document.querySelector('.uk-container .match');

document.addEventListener('DOMContentLoaded', function (evt) {
    video.querySelectorAll('#scrollTranscript p').forEach(function (parrafo) {
        parrafo.querySelectorAll('span').forEach(function (frase) {
            frase.classList.remove('remover');
        });
    });
    if (answer.querySelector('.trancript-contenedor').dataset.respuesta === "false") {
        answer.querySelectorAll('.trancript-contenedor span').forEach(function (frase) {
            frase.removeAttribute('uk-sortable');
        });
    }
});