const s3 = new AWS.S3({
    endpoint: new AWS.Endpoint('nyc3.digitaloceanspaces.com'),
    accessKeyId: 'GEXURWPHJX37JPR2VGWY',
    secretAccessKey: 'EOvb5YkVHMViTG12aEvSfsRz5clDgpXGJdlq2UrpHHs'
});
function generateLinks(){
    let activities = document.querySelectorAll('.uk-card-footer');
    activities.forEach(activity =>{
        activity.addEventListener('click', evt => {
            let element = evt.target;
            if(element.dataset.link  ){
                UIkit.notification("please wait .....",{timeout:7000});
                descargaDocumento(element.dataset.link, { Bucket: "tecolotl-multimedia", Key: element.dataset.sourceText.concat(activity.dataset.actividad) });

            }else if (element.nodeName === "LI") {
                UIkit.notification("please wait .....",{timeout:7000});
                descargaDocumento(element.textContent.trim().concat(".pdf"), { Bucket: "tecolotl-multimedia", Key: "plantilla/".concat(element.dataset.mapLink) });
            }
        })
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
       let mapsName = themesElement.querySelector('.uk-card-footer .uk-flex > div:last-child');
       let arrayMaps = mapsName.dataset.maps.split(',');
       let contentMaps = document.createDocumentFragment();
       arrayMaps.forEach((map,index) => {
           let elementList = document.createElement('li');
           elementList.textContent = `Think-Share And Develop_${map}`;
           elementList.dataset.mapLink = map;
           contentMaps.appendChild(elementList);
       });
       mapsName.querySelector('ul').appendChild(contentMaps);
    }
    themesActivity.add("Show all activities")
    themesActivity.forEach(theme =>{
        let elementTagSpan = document.createElement('span');
        elementTagSpan.textContent = theme;
        contentFragment.appendChild(elementTagSpan);
    });
    contentThemes.innerHTML = "";
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
        if (element.nodeName === "SPAN" && element.textContent.trim() !== "Show all activities"){
            contentActivities.classList.add('hidden-activities');
            let filterActivities = Array.from(themes).filter(theme => theme.dataset.tema.trim() === element.textContent.trim());
            for (const filterElement of filterActivities) {
                filterElement.parentElement.classList.add('uk-display-block');
            }
        }else if (element.textContent.trim() === "Show all activities"){
            console.log('entro');
            contentActivities.classList.remove('hidden-activities');
        }
    });
    contentActivities.addEventListener('change',evt =>{
        console.log(evt.target.type);
        if (evt.target.type === "radio" && evt.target.checked === true) {
            evt.target.nextElementSibling.checked = true;
        }
        contentActivities.querySelectorAll('.uk-radio').forEach(radio => {
            if (radio.checked === false){
                radio.nextElementSibling.checked = false;
            }
        })
    })

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
