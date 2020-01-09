var ejercicios;
var niveles;

document.addEventListener('DOMContentLoaded', function (evento) {
    ejercicios = document.querySelector('.js-filter');
    niveles = document.querySelectorAll('.uk-subnav-alumno li:not(:first-child)');
    agregaListener(ejercicios.querySelectorAll('li .uk-card-footer'));
});


function buscaActividadSeleccionada(evento) {
    salida = [];
    seleccionados = ejercicios.querySelectorAll('input[type=radio]:checked');
    if (seleccionados === null || seleccionados.length === 0) {
        UIkit.modal.alert('Es necesario seleccionar una actvidad');
    } else {
        evento.preventDefault();
        seleccionados.forEach(function (seleccionado) {
            salida.push(seleccionado.value);
        });
        if (salida.length !== niveles.length) {
            UIkit.modal.confirm('Al parecer hay alumnos sin asginar actvidad, desea asignar la(s) actvidad(es) seleccionada(s)').then(function () {
                document.getElementById('formulario-asignar-tarea:input-actividad').value = salida.join(',');
                document.getElementById('formulario-asignar-tarea:boton-enviar').click();
            }, function () {
                console.log('Cancelacion de envio');
            });
        } else {
            document.getElementById('formulario-asignar-tarea:input-actividad').value = salida.join(',');
            document.getElementById('formulario-asignar-tarea:boton-enviar').click();
        }
    }
}

var s3 = new AWS.S3({
    endpoint: new AWS.Endpoint('nyc3.digitaloceanspaces.com'),
    accessKeyId: 'GEXURWPHJX37JPR2VGWY',
    secretAccessKey: 'EOvb5YkVHMViTG12aEvSfsRz5clDgpXGJdlq2UrpHHs'
});

function agregaListener(ejercicios) {
    ejercicios.forEach(function (ejercicio) {
        actividad = ejercicio.dataset.actividad;
        iconos = ejercicio.querySelectorAll('svg');
        iconos[0].addEventListener('click', function (evento) {
            descargaDocumento('grammar.pdf', { Bucket: "tecolotl-multimedia", Key: "descargables/".concat(ejercicio.dataset.actividad) });
        });
        iconos[1].addEventListener('click', function (evento) {
            descargaDocumento('didactic_guide.pdf', { Bucket: "tecolotl-multimedia", Key: "guia/".concat(ejercicio.dataset.actividad) });
        });
        iconos[2].addEventListener('click', function (evento) {
            descargaDocumento('cards.pdf', { Bucket: 'tecolotl-multimedia', Key: 'cartas/'.concat(ejercicio.dataset.actividad) });
        });
        iconos[3].addEventListener('click', function (evento) {
            descargaDocumento('discussion.pdf', { Bucket: 'tecolotl-multimedia', Key: 'plantilla/discussion' })
        });
    });
}

function descargaDocumento(nombreArchivo, llave) {
    s3.getObject(llave, function(err, data) {
        if (err) {
            UIkit.modal.alert('Estimado usuario, por el momento no está disponible el documento.');
        }
        else {
            console.log(data);
            let hiperVinculo = document.createElement('a');
            hiperVinculo.setAttribute("href", URL.createObjectURL(new Blob([data.Body], {type: data.ContentType})));
            hiperVinculo.setAttribute("download", nombreArchivo);
            hiperVinculo.style.visibility = 'hidden';
            document.body.appendChild(hiperVinculo);
            hiperVinculo.click();
            document.body.removeChild(hiperVinculo);
        }
    });
}