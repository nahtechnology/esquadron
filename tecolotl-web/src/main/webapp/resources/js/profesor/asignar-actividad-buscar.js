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

function createContentTheme(){
    let themes = document.querySelectorAll('div[class^=card-activity]');
    let btnActivity = document.getElementById('formulario-asignartarea:boton-asignar');
    let contentActivities = document.getElementById('tareas');
    let contentThemes = document.getElementById('activity-theme');
    let contentFragment = document.createDocumentFragment();
    let themesActivity = new Set();
    for (const themesElement of themes) {
       themesActivity.add(themesElement.dataset.tema);
    }
    themesActivity.forEach(theme =>{
        let elementTagSpan = document.createElement('span');
        elementTagSpan.textContent = theme;
        contentFragment.appendChild(elementTagSpan);
    });
    contentThemes.appendChild(contentFragment);
    contentThemes.parentElement.parentElement.parentElement.classList.remove('uk-hidden');
    btnActivity.classList.remove('uk-hidden');
    contentThemes.addEventListener('click',evt =>{
        let themesFilter = contentActivities.querySelectorAll('.uk-display-block');
        if (themesFilter.length > 0){
            themesFilter.forEach(theme =>{
                theme.classList.remove('uk-display-block');
            });
        }
        let element = evt.target;
        if (element.nodeName === "SPAN"){
            contentActivities.classList.add('hidden-activities');
            let filterActivities = Array.from(themes).filter(theme => theme.dataset.tema.trim() === element.textContent.trim());
            for (const filterElement of filterActivities) {
                filterElement.parentElement.classList.add('uk-display-block');
            }
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