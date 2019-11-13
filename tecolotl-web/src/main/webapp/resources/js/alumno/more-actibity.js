var relacionarImagen = document.querySelector('.uk-container .relacion-imagen');
var ordenarOracion = document.querySelector('.uk-container .ordenar');
var menu = document.querySelector('.uk-container ul.uk-subnav');
var completarOracion = document.querySelector('.uk-container .complete-sentences');
var answer;


document.addEventListener('DOMContentLoaded', function (evt) {
    revuelve(relacionarImagen.querySelectorAll('.palabras div'));
    revuelve(ordenarOracion.querySelectorAll('.contenedor-oraciones > div'));
    var listaMenu = menu.querySelectorAll('li');
    for (indice = 0; indice < listaMenu.length; indice++) {
        if (listaMenu[indice].style.display != 'none') {
            listaMenu[indice].click();
            break;
        }
    }
    palabras = completarOracion.querySelector('.contedor-oraciones');
    completarOracion.querySelectorAll('div ul:nth-child(2) > li').forEach(function (parrafo,indice) {
        var palabra = document.createElement('span');
        palabra.textContent = parrafo.querySelector('span').textContent;
        palabra.dataset.cardinalidad = parrafo.dataset.cardinalidad;
        palabra.draggable=true;
        palabra.id="identificador"+indice;
        palabra.setAttribute('ondragstart','drag(event)');
        palabra.setAttribute('ondrop','return true');
        palabra.setAttribute('ondragover','return false');
        palabras.appendChild(palabra);
    });
    answer = document.querySelector('.complete-sentences');
    agregaRespuestas(answer.querySelector('.remplazar'));

});

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
        respondido.classList.add('realizado', 'movimiento1');
        respondido.textContent = 'Respondido';
        ordenarOracion.querySelector('form').appendChild(respondido);
    }
}

function agregaRespuestas(elemento) {
    console.log('agregando elemento');
    respuestas = [];
    answer.querySelectorAll('.remover').forEach(function (palabra) {
        respuestas.push(palabra.textContent);
        palabra.setAttribute('class','respuesta-ordenar-oracion');
        palabra.setAttribute("ondrop","drop(event, this)");
        palabra.setAttribute("ondragover","allowDrop(event)");
        palabra.innerHTML=" ";
    });
    totalRespuestasTranscripcion = respuestas.length;
    revolver(respuestas).forEach(function (resp) {
        respuesta = document.createElement('span');
        respuesta.classList.add('respuesta-drag');
        respuesta.textContent = resp;
        elemento.appendChild(respuesta);
    });

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