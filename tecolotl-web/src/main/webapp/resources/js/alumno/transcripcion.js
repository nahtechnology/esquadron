/*
Funcionaes para la transcripcion
 */
var answer;
var menuPrincipal;
var orderWords;

document.addEventListener('DOMContentLoaded', function (evt) {
    answer = document.querySelector('.answer');
    menuPrincipal = document.querySelector('.uk-container .uk-subnav');
    orderWords = document.querySelector('.order-words');
    agregaRespuestas(answer.querySelector('.contenedor-respuesta'));
    menuPrincipal.querySelector('li:nth-child(4)').addEventListener('click', eventoClickTransciptcion);
    if (menuPrincipal.querySelector('li:first-child').dataset.respondiendo === 'true') {
        menuPrincipal.querySelector('li:nth-child(4)').click();
        menuPrincipal.querySelector('li:first-child').removeAttribute('uk-filter-control');
    }
});

function agregaRespuestas(elemento) {
    console.log('agregando elemento');
    respuestas = [];
    answer.querySelectorAll('.remplazar .remover').forEach(function (palabra,index) {
        respuestas.push(palabra.textContent);
        palabra.setAttribute("uk-sortable","group: respuesta");
        palabra.setAttribute('class','uk-sortable respuesta-transcript');
        palabra.setAttribute('data-indice',index);
        palabra.innerHTML=" ";
    });
    totalRespuestasTranscripcion = respuestas.length;
    revolver(respuestas).forEach(function (resp,indice) {
        respuesta = document.createElement('span');
        respuesta.classList.add('respuesta-drag');
        respuesta.setAttribute('data-index', indice);
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

function transcripcionEnvidad(data) {
    if (data.status === 'success') {
        UIkit.notification(mensajeEnviado, {pos: 'top-right'});
    }
}

function validaContenido() {
    if (((answer.querySelectorAll('.contenedor-respuesta span').length * 100) / totalRespuestasTranscripcion) < 15.0)  {
        resp = '';
        answer.querySelectorAll('.remplazar p').forEach(function (respRem) {
            resp = resp.concat(respRem.outerHTML);
        });
        document.getElementById('formulario-transcripcion:respuesta').value = resp;
        return true;
    }
    UIkit.modal.alert(mensajeError);
    return false;
}

function eventoClickTransciptcion() {
    var video = menuPrincipal.querySelector('li:first-child');
    var respuestaTranscript = menuPrincipal.querySelector('li:nth-child(4)');
    if (video.dataset.respondiendo === 'false' && video.dataset.respuesta === 'vacio') {
        UIkit.modal.confirm(mensajeTrnascripcion).then(function() {
            video.removeAttribute('uk-filter-control');
            menuPrincipal.querySelector('form input[type=hidden]:nth-child(2)').value = true;
            menuPrincipal.querySelector('form a').click();
        }, function () {
            video.click();
        });
    }
}