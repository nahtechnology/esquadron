document.addEventListener('DOMContentLoaded',()=>{
   let diasLicencias = document.querySelector('#licencia-fecha').dataset.licencia;
   let permitir = sessionStorage.getItem('mensaje');
   if (parseInt(diasLicencias) <= 15 && permitir === "true"){
        let nodo = document.createElement('div');
        let nodoreferencia = document.querySelector('h1');
        let nodoPadre = nodoreferencia.parentElement;
        let icon = document.createElement('span');
        nodo.innerText = `En ${parseInt(diasLicencias)} dias se termina tu licencia`;
        nodo.classList.add('mensaje-licencias');
        icon.setAttribute('uk-icon','close');
        icon.setAttribute('uk-tooltip','No mostrar mensaje')
        icon.classList.add('cerrar-mensaje');
        nodo.appendChild(icon);
        nodoPadre.insertBefore(nodo,nodoreferencia);
        icon.addEventListener('click',()=>{
            nodo.remove();
            sessionStorage.setItem('mensaje','false');
        })
   }

});

function limpiarSession() {
    sessionStorage.removeItem('mensaje');
}