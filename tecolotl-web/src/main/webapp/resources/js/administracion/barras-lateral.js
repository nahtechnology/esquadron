document.addEventListener('DOMContentLoaded',()=>{
   const contenedorBarras = document.getElementById('alumnos-extra');
    const tituloBarras = contenedorBarras.previousElementSibling;
    if (!contenedorBarras.hasChildNodes()){
        tituloBarras.classList.add('visibilidad');
    }else tituloBarras.classList.remove('visibilidad');
});