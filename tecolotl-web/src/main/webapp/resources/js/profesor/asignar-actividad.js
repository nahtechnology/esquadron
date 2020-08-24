var ejercicios;
var niveles,temarioBtn,cuerpo;
var misTemas = new Set();
var mapas;
let btnConteoDescagas;
document.addEventListener('DOMContentLoaded', function (evento) {
    var btnClose = document.querySelector('#temario > div > div > span');
    var nivelesFiltro = document.querySelector('ul.nav-default-profesor');
    btnConteoDescagas = document.getElementById('formulario-descargable:boton-incrementar');
    ejercicios = document.querySelector('.js-filter');
    niveles = document.querySelectorAll('.uk-subnav-alumno li:not(:first-child)');
    agregaListener(ejercicios.querySelectorAll('li .uk-card-footer'));
    temariosActividad();
    temarioBtn = document.querySelector('#temario + div > span');
    temarioBtn.addEventListener('click',function () {
        var temas = document.querySelector('#temario > div');
        temas.classList.toggle('temario-open');
    });
    btnClose.addEventListener('click',function () {
        var temas = document.querySelector('#temario > div');
        temas.classList.toggle('temario-open');
    });
    ordenarNiveles(nivelesFiltro);
    ejercicios.querySelectorAll('input[type=radio]').forEach(radio => radio.addEventListener('change', activaChechbox));
    ejercicios.querySelectorAll('input[type=radio] + input[type=checkbox]').forEach(checkbox => checkbox.disabled = true);

});

function buscaActividadSeleccionada(evento) {
    salida = [];
    seleccionados = ejercicios.querySelectorAll('input[type=radio]:checked');
    verRespuestas = Array.from(seleccionados).map(selec => selec.nextElementSibling.checked);
    if (seleccionados === null || seleccionados.length === 0) {
        UIkit.modal.alert(letrero);
    } else {
        evento.preventDefault();
        seleccionados.forEach(function (seleccionado) {
            salida.push(seleccionado.value);
        });
        if (salida.length !== niveles.length) {
            UIkit.modal.confirm(confirmacion).then(function () {
                document.getElementById('formulario-asignar-tarea:input-actividad').value = salida.join(',');
                document.getElementById('formulario-asignar-tarea:input-tarea').value = verRespuestas.join(',');
                document.getElementById('formulario-asignar-tarea:boton-enviar').click();
            }, function () {
                console.log('Cancelacion de envio');
            });
        } else {
            document.getElementById('formulario-asignar-tarea:input-actividad').value = salida.join(',');
            document.getElementById('formulario-asignar-tarea:input-tarea').value = verRespuestas.join(',');
            document.getElementById('formulario-asignar-tarea:boton-enviar').click();
        }

    }
    let botones = document.querySelectorAll('.uk-modal-footer > button');
    console.log(botones);
    botones.forEach(boton => {
        boton.classList.remove('uk-button','uk-button-default','uk-button-primary');
        boton.classList.add('boton-escuadron-base');
    });
}

var s3 = new AWS.S3({
    endpoint: new AWS.Endpoint('nyc3.digitaloceanspaces.com'),
    accessKeyId: 'GEXURWPHJX37JPR2VGWY',
    secretAccessKey: 'EOvb5YkVHMViTG12aEvSfsRz5clDgpXGJdlq2UrpHHs'
});

function agregaListener(ejercicios) {
    ejercicios.forEach(function (ejercicio) {
        let cadenaMapas = ejercicio.dataset.mapas.split(',');
        let contenedorListaMapa= ejercicio.querySelector('div > div > ul');
        // console.log(cadenaMapas[0]);
        actividad = ejercicio.dataset.actividad;
        mapas = ejercicio.dataset.mapas;

        cadenaMapas.forEach(function (numeroMapa) {
           let linkMapa = document.createElement('li');
           linkMapa.innerHTML =descargableMapa.concat("_",numeroMapa) ;
           contenedorListaMapa.appendChild(linkMapa);
        });
        iconos = ejercicio.querySelectorAll('.uk-inline > div > span');
        iconos[0].addEventListener('click', function (evento) {
            descargaDocumento('Grammar.pdf', { Bucket: "tecolotl-multimedia", Key: "descargables/".concat(ejercicio.dataset.actividad) });
        });
        iconos[1].addEventListener('click', function (evento) {
            descargaDocumento('Lesson_Plan.pdf', { Bucket: "tecolotl-multimedia", Key: "guia/".concat(ejercicio.dataset.actividad) });
        });
        iconos[2].addEventListener('click', function (evento) {
            descargaDocumento('Disussion_cards.pdf', { Bucket: 'tecolotl-multimedia', Key: 'cartas/'.concat(ejercicio.dataset.actividad) });
        });
        contenedorListaMapa.querySelectorAll('li').forEach(function (linkMap,index) {
          linkMap.addEventListener('click',function (evento) {
              descargaDocumento(descargableMapa.concat(cadenaMapas[index]).concat('.pdf'), { Bucket: 'tecolotl-multimedia', Key: 'plantilla/'.concat(cadenaMapas[index])});
          })
        })

        // iconos[3].addEventListener('click', function (evento) {
        //     descargaDocumento('mental_map.pdf', { Bucket: 'tecolotl-multimedia', Key: 'plantilla/'.concat(ejercicio.dataset.mapas) });
        // });
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
            btnConteoDescagas.click();
            hiperVinculo.setAttribute("href", URL.createObjectURL(new Blob([data.Body], {type: data.ContentType})));
            hiperVinculo.setAttribute("download", nombreArchivo);
            hiperVinculo.style.visibility = 'hidden';
            document.body.appendChild(hiperVinculo);
            hiperVinculo.click();
            document.body.removeChild(hiperVinculo);
        }
    });
}

function temariosActividad() {
    var temas =document.querySelectorAll('.uk-card .uk-card-header > div > div:first-child > :nth-child(3)');
    var contenedorTemas = document.querySelector('#temario > div > div + div');
    temas.forEach(function (textoTemas) {
        misTemas.add(textoTemas.innerText);
    });
    misTemas.forEach(function (temas) {
        let texto = document.createElement('span');
        texto.addEventListener('click',holaFiltro);
        texto.innerText = temas;
        contenedorTemas.appendChild(texto);
    });
    /*
    misTemas.forEach(function (temas) {
        let lista = document.createElement('li');
        let texto = document.createElement('span');
        texto.addEventListener('click',holaFiltro);
        texto.innerText = temas;
        lista.appendChild(texto);
        contenedorTemas.appendChild(lista);
    });*/
}

function ordenarNiveles(nodo) {
    let lenguageLevels = ['A1','A2','B1','B2','C1','C2'];
    let levels = nodo.querySelectorAll('li');
    let prueba = [],pibote = true,numero = 0;
    console.log(levels);
    prueba.push(levels[0]);
    while (pibote){
       levels.forEach(nivel => {
          if(nivel.className === lenguageLevels[numero]){
              prueba.push(nivel);
          }
       });
       numero++;
       if(numero > 5){
           pibote = false;
       }
    }
      while(nodo.hasChildNodes()){
         nodo.removeChild(nodo.firstChild);
      }
      for (let i = 0; i < levels.length; i++){
          nodo.appendChild(prueba[i]);
      }
}

let personitas = document.querySelectorAll(".uk-card-header [uk-icon *= users]");

personitas.forEach(function (persona) {
    persona.addEventListener("mouseover", function (evt) {
        let padre = evt.target.parentElement.parentElement;
        let nivel = padre.querySelector(".cat-txt").innerText.trim();
        let color ;
        switch (nivel) {
            case "A1":
                color = "#E80CA2";
                break;
            case "A2":
                color = "#D35400";
                break;
            case "B1":
                color = "#E89E0C";
                break;
            case "B2":
                color = "#650CE8";
                break;
            case "C1":
                color = "#00a8ec";
                break;
            case "C2":
                color = "#f0506e";
                break;
        }
        setTimeout(pintarTooltip,60);
        function pintarTooltip(){
            let msjTooltip = document.querySelector(".uk-tooltip");
            if(msjTooltip != null){
                msjTooltip.style.background = color;
            }
        }
    });
});

function activaChechbox(event){
    let radio = event.target;
    document.querySelectorAll('input[type=radio][name=' + radio.name + ']').forEach(chec => {
        chec.nextElementSibling.checked = false;
        chec.nextElementSibling.disabled = true;
    });
    radio.nextElementSibling.checked = true;
    radio.nextElementSibling.disabled = false;
}
