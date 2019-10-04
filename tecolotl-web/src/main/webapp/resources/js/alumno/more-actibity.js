var relacionarImagen = document.querySelector('.uk-container .relacion-imagen');
var ordenarOracion = document.querySelector('.uk-container .ordenar');
var menu = document.querySelector('.uk-container ul.uk-subnav');
var completarOracion = document.querySelector('.uk-container .complete-sentences');

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
    completarOracion.querySelectorAll('ul:nth-child(2) > li').forEach(function (parrafo) {
        var palabra = document.createElement('div');
        palabra.textContent = parrafo.querySelector('span').textContent;
        palabras.appendChild(palabra);
    });
});

function revuelve(palabras) {
    for (var indice = 0; indice < palabras.length; indice++) {
        palabras[indice].after(palabras[Math.floor(Math.random() * palabras.length)]);
    }
}

function enviarRespuesta() {
    relacionarImagen.querySelectorAll('table tbody tr').forEach(function (respuesta) {
        respuesta.querySelector('input[type=hidden]').value = respuesta.querySelector('td:nth-child(2) div').dataset.codigo;
    });
}

function respuestaEnvidad(data) {
    if (data.status === 'success') {
        relacionarImagen.querySelectorAll('table tbody tr').forEach(function (ordenable) {
            ordenable.querySelector('td:nth-child(2)').removeAttribute('uk-sortable');
        });
        data.source.disabled = true;
        UIkit.notification("Respuesta enviada correctamente", {pos: 'top-right'});
        var respondido = document.createElement('span');
        respondido.classList.add('realizado', 'movimiento1');
        respondido.textContent = 'Respondido';
        ordenarOracion.querySelector('form').appendChild(respondido);
    }
}
