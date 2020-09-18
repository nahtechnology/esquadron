
document.addEventListener("DOMContentLoaded", () => {
   eventoCheckbox();
    limpiaSeleccion();
});

function limpiaSeleccion() {
    document.querySelectorAll('select').forEach(sel => sel.removeAttribute('size'));
}
function eventoCheckbox(){
    console.log('entro funcion');
    let checkboxGeneral = document.querySelector('.switch > input[type=checkbox]');
    let checkboxs = document.querySelectorAll('.tabla-checkbox input[type=checkbox]');
    checkboxGeneral.addEventListener('change',(evt)=>{
        if (evt.target.checked === true){
            checkboxs.forEach(check =>{
                check.checked = true;
            });
        }else {
            checkboxs.forEach(check =>{
                check.checked = false;
            });
        }

    });
}
function tablaCargada(){
    let tablaChekbox;
    tablaChekbox = document.querySelector('.tabla-checkbox');
    tablaChekbox.onload = ()=>{
        console.log('se termino de cargar');
    }
}
function visibilidadSpinner(opcion){
    switch (opcion) {
        case 1:
            let spinnerGrupo = document.getElementById('spinner-grupo');
            spinnerGrupo.classList.toggle('ver-svg');
            break;
        case 2:
            let spinnerAlumnos = document.getElementById('spinner-alumnos');
            spinnerAlumnos.classList.toggle('ver-svg');

    }
}
function cargando(){
    let selectgrupo= document.getElementById('formulario-grupo:seleccion-grupo');
    let btnGrupo = document.getElementById('formulario-grupo:btn-grupo');
    btnGrupo.disabled = selectgrupo.options.length <= 1;
    selectgrupo.disabled = selectgrupo.options.length <= 1;
    console.log('entro cargando');
}
