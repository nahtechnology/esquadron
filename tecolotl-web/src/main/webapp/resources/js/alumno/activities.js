
var actividades = document.querySelectorAll('.uk-card');

actividades.forEach(actividad =>{
    if(actividad.classList.contains('desactivado')){
        actividad.querySelector('.boton').value = "check";
    }
});
function muestraPregunta(elemento) {
    console.log(elemento);
    return false;
}