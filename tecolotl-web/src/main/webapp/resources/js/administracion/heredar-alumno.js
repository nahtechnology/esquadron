document.addEventListener("DOMContentLoaded", () => {
    limpiaSeleccion();
});

function limpiaSeleccion() {
    document.querySelectorAll('select').forEach(sel => sel.removeAttribute('size'));
}