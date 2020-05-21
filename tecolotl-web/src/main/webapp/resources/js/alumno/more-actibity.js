var relacionarImagen = document.querySelector('.uk-container .relacion-imagen');
var ordenarOracion = document.querySelector('.uk-container .ordenar');
var menu = document.querySelector('.uk-container ul.uk-subnav');
var completarOracion = document.querySelector('.uk-container .complete-sentences');
var answer;



document.addEventListener('DOMContentLoaded', function (evt) {
    revuelve(relacionarImagen.querySelectorAll('.palabras div'));
    if (ordenarOracion.querySelector('.contenedor-oraciones').dataset.activado === "false"){
        revuelve(ordenarOracion.querySelectorAll('.contenedor-oraciones > div'));
    }else{
        calificacionOracinesOrdenar();


    }

    var listaMenu = menu.querySelectorAll('li');
    for (indice = 0; indice < listaMenu.length; indice++) {
        if (listaMenu[indice].style.display != 'none') {
            listaMenu[indice].click();
            break;
        }
    }
    completarOracionRemueve();
    if (relacionarImagen.querySelector('.contenedor-imagen').dataset.activo === "true"){
        calificaRelacionarImagen();
    }

   verRespuestasCompletarOracion();
});

function completarOracionRemueve() {
    let nodosPalabras = [];
    let respuestasPalabras = [];
    let palabrasOraciones = [];

     palabras = completarOracion.querySelector('.contedor-oraciones');
    while (palabras.hasChildNodes()) {
        palabras.removeChild(palabras.firstChild);
    }
    // var texto = revolver(wordsPalabras);
    completarOracion.querySelectorAll('div ul:nth-child(2) > li').forEach(function (parrafo,indice) {
      let otro = parrafo.querySelector('span[id*=oraciones]');
       nodosPalabras.push(otro);
       palabrasOraciones.push(otro.querySelector('span.remover').innerText);
     if (otro.dataset.resuelto === 'true'){
         respuestasPalabras.push(otro.dataset.respuesta)
     }
    });
    console.log(respuestasPalabras);
    respuestasPalabras.forEach(word => {
        let busqueda = palabrasOraciones.indexOf(word);
        if (busqueda > -1){
            palabrasOraciones.splice(busqueda,1);
        }
    });
    console.log(palabrasOraciones);
    palabrasOraciones.forEach(word =>{

        for (let i = 0; i < nodosPalabras.length ; i++) {
            if (nodosPalabras[i].querySelector('span.remover').innerText === word){
                var palabra = document.createElement('span');
                palabra.innerText = nodosPalabras[i].querySelector('span.remover').innerText;
                palabra.dataset.cardinalidad = nodosPalabras[i].parentNode.dataset.cardinalidad;
                palabra.id="identificador"+i;
                palabra.dataset.index = i;
                palabras.appendChild(palabra);
                i = nodosPalabras.length;
            }
        }
    });

    answer = document.querySelector('.complete-sentences');
    agregaRespuestas(answer.querySelector('.remplazar'));
    agregaRespuesta();
    revolverNodos();
}

function validaRespuestaImagen(evento) {
    if (relacionarImagen.querySelectorAll('.respuesta-imagen div').length !== 0) {
        UIkit.modal.alert(mensajeNoEnvio);
        return false;
    } else {
        relacionarImagen.querySelectorAll('.respuesta-imagen-contenedor > :nth-child(3)').forEach(function (respuesta) {
            respuesta.nextElementSibling.value = respuesta.firstChild.textContent.trim();
        });
        return true;
    }
}

function revuelve(palabras) {
    for (var indice = 0; indice < palabras.length; indice++) {
        palabras[indice].after(palabras[Math.floor(Math.random() * palabras.length)]);
    }
}

function respuestaEnvidad(data) {
    if (data.status === 'success') {
        relacionarImagen.querySelectorAll('table tbody tr').forEach(function (ordenable) {
            ordenable.querySelector('td:nth-child(2)').removeAttribute('uk-sortable');
        });
        data.source.disabled = true;
        UIkit.notification(mensajeEnviado, {pos: 'top-right'});
        var respondido = document.createElement('span');
        respondido.classList.add('fin-ordenar', 'fin-movimiento');
        ordenarOracion.querySelector('form').appendChild(respondido);
        calificacionOracinesOrdenar();
    }
}

function agregaRespuestas(elemento) {
    //let respuestas = [];
    answer.querySelectorAll('span[id*=oraciones] .remover').forEach(function (palabra,index) {
        // respuestas.push(palabra.textContent);
        palabra.setAttribute('class','respuesta-ordenar-oracion');
        // palabra.setAttribute("ondrop","drop(event, this)");
        // palabra.setAttribute("ondragover","allowDrop(event)");
        palabra.setAttribute('uk-sortable','group: completar');
        palabra.innerHTML=" ";
        palabra.dataset.indice = index;
    });
   // let totalRespuestasTranscripcion = respuestas.length;
    // while (elemento.hasChildNodes()) {
    //     elemento.removeChild(elemento.firstChild);
    // }
    // respuestas.forEach(function (resp) {
    //     let respuesta = document.createElement('span');
    //     respuesta.classList.add('respuesta-drag');
    //     respuesta.textContent = resp;
    //     elemento.appendChild(respuesta);
    // });

}

function agregaRespuesta() {
    completarOracion.querySelectorAll('div ul:nth-child(2) > li > span[id*=oraciones] ').forEach(function (elemento) {
        if (elemento.dataset.resuelto === 'true') {
            elemento.querySelector('span').innerText = elemento.dataset.respuesta;
        }
    });
}

function verRespuestasCompletarOracion() {
    completarOracion.querySelectorAll('[id*=respuesta-correcta]').forEach(palabra =>{
        calificarCompletarOracion(palabra);

    });
    let totalRespondidas = completarOracion.querySelectorAll('.menu-ordenar > li.boton-listo').length;
    let totalPreguntas = completarOracion.querySelectorAll('.menu-ordenar > li').length;
    if(totalPreguntas === totalRespondidas){
        let puntaje = completarOracion.querySelectorAll('span.color-correcto').length;
        puntaje = Math.round(puntaje*100/totalPreguntas) >= 25 ? Math.round(puntaje*100/totalPreguntas) : 25;
        completarOracion.querySelectorAll('.terminado').forEach(score=>{
            score.classList.remove('terminado');
            score.classList.add('score','uk-position-absolute');
            score.querySelector('span').innerText = "Score "+puntaje+"%";
        });
        completarOracion.querySelectorAll('[id*=respuesta-correcta]').forEach(nodo =>{

            let palabraCorrecta = nodo.parentElement.querySelector('span[id*=oraciones] span.respuesta-ordenar-oracion');
            palabraCorrecta.setAttribute('uk-tooltip',`title: ${nodo.querySelector('.remover').innerText};pos: bottom`);
            palabraCorrecta.addEventListener('mouseover',()=>{
                setTimeout(pintar,50);
                function pintar() {
                    console.log('pinto');
                    document.querySelector('.uk-tooltip').classList.add('respuestas-correctas');
                    document.querySelector('.uk-tooltip .uk-tooltip-inner').style.color="black";
                }
            });

        });
    }
}

function validaRespuestaCompletarOracion(evento) {
    var formulario = evento.target.parentNode;
    var respuesta = formulario.querySelector('.respuesta-ordenar-oracion span');
    if (respuesta) {
        formulario.querySelector('input[type=hidden]').value = respuesta.dataset.cardinalidad;
        return true;
    } else {
        UIkit.modal.alert(mensajeNoEnvio);
        return false;
    }
}

function revolver(arreglo) {
    var indiceActual = arreglo.length, temporal, indiceAleatorio;
    while (0 !== indiceActual) {
        indiceAleatorio = Math.floor(Math.random() * indiceActual);
        indiceActual -= 1;
        temporal = arreglo[indiceActual];
        arreglo[indiceActual] = arreglo[indiceAleatorio];
        arreglo[indiceAleatorio] = temporal;
    }
    return arreglo;
}

function revolverNodos() {
    var arregloNodo = [];
    var cotenidoRespuesta = document.querySelector('.complete-sentences .contedor-oraciones');
    var nodos = document.querySelectorAll('.complete-sentences .contedor-oraciones span');
    nodos.forEach(function (span) {
       arregloNodo.push(span);
    });
    var nodosBuenos = revolver(arregloNodo);
    console.log(nodosBuenos);
    while (cotenidoRespuesta.hasChildNodes()){
        cotenidoRespuesta.removeChild(palabras.firstChild);
    }
    nodosBuenos.forEach(function (nodoBueno) {
        cotenidoRespuesta.appendChild(nodoBueno);
    });
}

function calificaRelacionarImagen() {
    let calificacion = 0;
    let contenedores = relacionarImagen.querySelectorAll('.contenedor-imagen:first-child .respuesta-imagen-contenedor');
contenedores.forEach(palabras => {
    let respuestasCorrectas, respuestasAlumno;
    respuestasCorrectas = palabras.querySelector('input[type=hidden]').value.trim().toLocaleLowerCase();
    respuestasAlumno = palabras.querySelector('.oraciones-relacion-img').innerText.trim().toLocaleLowerCase();
    if(respuestasCorrectas === respuestasAlumno){
        palabras.querySelector('.oraciones-relacion-img').classList.add('correcto');
        calificacion++;
    }else {
        palabras.querySelector('.oraciones-relacion-img').classList.add('incorrecto');
        palabras.querySelector('.oraciones-relacion-img').setAttribute('uk-tooltip',respuestasCorrectas);
        palabras.addEventListener('mouseover',() =>{
            setTimeout(pintar,50);
            function pintar() {
                console.log('pinto');
                document.querySelector('.uk-tooltip').classList.add('respuestas-correctas');
                document.querySelector('.uk-tooltip .uk-tooltip-inner').style.color="black";
                document.querySelector('.uk-tooltip .uk-tooltip-inner').style.fontsize="30px";
            }
        })


    }

});

    calificacion = Math.round(calificacion * 100/contenedores.length) >= 25 ? Math.round(calificacion * 100/contenedores.length) : 25;
    relacionarImagen.querySelector('.respuesta-imagen .score > span').innerText = "Score: "+calificacion + "%";
    relacionarImagen.querySelector('.contenedor-imagen:last-child .palabras').style.background = "none";
    
}

function calificarCompletarOracion(palabra) {
let respuestaCorrecta = palabra.querySelector('.remover').innerText;
let respuestaAlumno = palabra.parentElement.querySelector('span[id*=oraciones] span.respuesta-ordenar-oracion').innerText;
let clase =  respuestaAlumno.trim().toLowerCase() === respuestaCorrecta.trim().toLowerCase() ? 'color-correcto':'color-incorrecto';
    palabra.parentElement.querySelector('span[id*=oraciones] span.respuesta-ordenar-oracion').classList.add(clase);
}

function calificacionOracinesOrdenar() {
    let respuestas = ordenarOracion.querySelectorAll('.imagen-oracion');
    let puntaje = 0;
    respuestas.forEach(respuesta =>{
        if(respuesta.classList.contains('correcto')){
            puntaje++;
        }
    });
    console.log(puntaje);
    puntaje = Math.round(puntaje * 100/respuestas.length) >= 25 ? Math.round(puntaje * 100/respuestas.length) : 25;
    ordenarOracion.querySelector('.score > span').innerText ="Score: " + puntaje + "%";
}