addEventListener('load',()=>{
    let datosActividades = document.getElementById('datos-conteo-actividades');
    let contenedorOption = document.createDocumentFragment();
    let filtroVideosCaidos = document.getElementById('actividades-canceladas');
    let btndesactivar = document.getElementById('formulario-actividad:desactivar');
    let posicionX = btndesactivar.getBoundingClientRect().left;
    let posicionY = btndesactivar.getBoundingClientRect().top;
    let contenedorActividades = document.querySelector('.contenido-actividades');
    let inputBuscar = document.querySelector('form.uk-search .uk-search-input');
    let btnBuscar = inputBuscar.parentElement.nextElementSibling;
    let idActividades = document.querySelectorAll(".card-actividades-bloqueadas h6");
    let imgAvtividades = document.querySelectorAll('.card-actividades-bloqueadas img');


    for (const imgActividad of imgAvtividades) {
        if (imgActividad.naturalWidth === 120){
            let option = document.createElement('option');
            option.value = imgActividad.nextElementSibling.textContent;
            option.textContent = imgActividad.nextElementSibling.textContent;
            contenedorOption.appendChild(option);
        }
    }
    filtroVideosCaidos.appendChild(contenedorOption);
    btndesactivar.style.setProperty("top",`${posicionY}px`);
    btndesactivar.style.setProperty("left",`${posicionX}px`);
   addEventListener("scroll",evt=>{
       if (posicionY < document.documentElement.scrollTop  ){
           btndesactivar.classList.add('posicion-fila-btn-desactivar');
       }else  if (document.documentElement.scrollTop === 0){
           btndesactivar.classList.remove('posicion-fila-btn-desactivar');
       }
    });
   inputBuscar.addEventListener('input',(evt)=>{
       if (evt.target.value === "" ){
           resear(contenedorActividades);
       }
   });
   btnBuscar.addEventListener('click',() => {
        buscar(idActividades,inputBuscar.value);
   });
   filtroVideosCaidos.addEventListener('change',(evt)=>{
       if (evt.target.value === "all"){
           resear(contenedorActividades);
       }else {
           resear(contenedorActividades);
           buscar(idActividades,evt.target.value);
       }
   })
    textosActividades(contenedorActividades,datosActividades);

});

function seleccionActividad(evebto) {
    let seleccion = document.querySelector('input[name="actividad"]:checked');
    if (seleccion === null) {
        UIkit.modal.alert('Seleccione una actividad');
        return false;
    }
    document.getElementById('formulario-actividad:entrada-actividad').value = seleccion.id;
    return true;
}
function buscar(actividades,palabra){
    for (const actividade of actividades) {
        if (actividade.textContent === palabra ){
            actividade.parentElement.classList.add('visibilidad-actividad');
            actividade.parentElement.parentElement.classList.add('visibilidad-actividades');

        }
    }
}

function resear(contenedorActividades){
    contenedorActividades.classList.remove('visibilidad-actividades');
    if (document.querySelector('.visibilidad-actividad')){
        document.querySelector('.visibilidad-actividad').classList.remove('visibilidad-actividad');
    }
}

function textosActividades(contenedorActividades,contenedorTexto){
    let totalActividades = contenedorActividades.querySelectorAll('.card-actividades-bloqueadas').length;
    let totalActividadesBloqueadas = contenedorActividades.querySelectorAll('.card-adtividad-desactivado').length;
    contenedorTexto.querySelector('span:first-child span').textContent = totalActividades;
    contenedorTexto.querySelector('span:last-child span').textContent = totalActividadesBloqueadas;
}