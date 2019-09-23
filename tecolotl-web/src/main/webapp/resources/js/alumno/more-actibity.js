var relacionarImagen = document.querySelector('.uk-container .relacion-imagen');

document.addEventListener('DOMContentLoaded', function (evt) {
    revuelveRelacionarImagen(relacionarImagen.querySelectorAll('.palabras div'));
});

function revuelveRelacionarImagen(palabras) {

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
    }
    data.source.disabled = true;
}