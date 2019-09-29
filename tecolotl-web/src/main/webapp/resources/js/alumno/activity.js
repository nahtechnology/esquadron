var menu = document.querySelector('.uk-container .uk-subnav');
var video = document.querySelector('.uk-container .js-filter');

document.addEventListener('DOMContentLoaded', function (evt) {
    video.querySelectorAll('#scrollTranscript p').forEach(function (parrafo) {
        parrafo.querySelectorAll('span').forEach(function (frase) {
            frase.classList.remove('remover');
        });
    });

    answer
});