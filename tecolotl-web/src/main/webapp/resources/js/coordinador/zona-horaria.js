let zona = new Intl.DateTimeFormat().resolvedOptions().timeZone;

function cambiarEnlaceBoton(){
    const botones = document.querySelectorAll("table[id$=tabla-alumno] .botones-pagina > input.uk-button-primary");

    botones.forEach(boton =>{
       let cadenaFuncion = boton.getAttribute('onclick');
       let cadenaFuncionRemplazo = cadenaFuncion.replace('tiempo',zona);
       boton.setAttribute('onclick',cadenaFuncionRemplazo);
    });
}