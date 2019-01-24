document.addEventListener("DOMContentLoaded", function (evt) {
    console.log('antes de evento');
    limpiaInput();
    console.log('despues del evento');
});

function limpiaInput() {
    console.log('antes de la limpieza');
    document.querySelectorAll('.uk-select').forEach(function (value) { value.removeAttribute('size') });
    console.log('despues de la limpieza');
}