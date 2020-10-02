const s3 = new AWS.S3({
    endpoint: new AWS.Endpoint('nyc3.digitaloceanspaces.com'),
    accessKeyId: 'GEXURWPHJX37JPR2VGWY',
    secretAccessKey: 'EOvb5YkVHMViTG12aEvSfsRz5clDgpXGJdlq2UrpHHs'
});
function generateLinks(){
    let activities = document.querySelectorAll('.uk-card-footer');
    activities.forEach(activity =>{
        let  icons = activity.querySelectorAll('.uk-inline > div > span');
        icons[0].addEventListener('click', function (evento) {
            UIkit.notification("please wait .....",{timeout:7000});
            descargaDocumento('Grammar.pdf', { Bucket: "tecolotl-multimedia", Key: "descargables/".concat(activity.dataset.actividad) });
        });
        icons[1].addEventListener('click', function (evento) {
            UIkit.notification("please wait .....",{timeout:7000});
            descargaDocumento('Lesson_Plan.pdf', { Bucket: "tecolotl-multimedia", Key: "guia/".concat(activity.dataset.actividad) });
        });
        icons[2].addEventListener('click', function (evento) {
            UIkit.notification("please wait .....",{timeout:7000});
            descargaDocumento('Disussion_cards.pdf', { Bucket: 'tecolotl-multimedia', Key: 'cartas/'.concat(activity.dataset.actividad) });
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

function buscaSeleccion() {
    let actividad = document.querySelector('input[type=radio]:checked');
    if (actividad == null) {
        UIkit.modal.alert('Selecciona una tarea');
        return false;
    }
    document.getElementById('formulario-asignartarea:actvidad').value = actividad.value;
    document.getElementById('formulario-asignartarea:ver-tarea').value = actividad.nextElementSibling.checked;
    return true;
}