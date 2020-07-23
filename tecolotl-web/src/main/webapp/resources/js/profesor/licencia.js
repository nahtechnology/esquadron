document.addEventListener('DOMContentLoaded',()=>{
   let diasLicencias = document.querySelector('#licencia-fecha').dataset.licencia;
   let permitir = sessionStorage.getItem('mensaje');
   if (parseInt(diasLicencias) <= 30 && permitir === "true"){
        let nodo = document.createElement('div');
         let titulo = document.createElement('span');
        let letrero = document.createElement('span');
        let nodoreferencia = document.querySelector('h1');
        let nodoPadre = nodoreferencia.parentElement;
        let icon = document.createElement('span');

        letrero.innerText = parseInt(diasLicencias) <= 0 ?'Tu licencia a caducado': `En ${parseInt(diasLicencias)} dias se termina tu licencias`;
        nodo.classList.add(parseInt(diasLicencias) <= 0 ? '--mensaje-licencia-caducada':'--mensaje-licencia-restante','mensaje-licencias');
        icon.setAttribute('uk-icon','close');
        icon.setAttribute('uk-tooltip','No mostrar mensaje')
        icon.classList.add('cerrar-mensaje');
        nodo.appendChild(icon);
       titulo.innerText ="IMPORTANTE";
       titulo.style.marginBottom = "8px";
       nodo.appendChild(titulo);
       nodo.appendChild(letrero);
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