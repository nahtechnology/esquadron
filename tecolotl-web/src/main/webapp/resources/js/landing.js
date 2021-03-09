let contenedor = document.querySelectorAll('#inicio > div  >  div:first-of-type > div');
let boton2,caja2;
let tag = document.createElement('script');
let videoPlayer = document.querySelector('.video-alum');
tag.src = "https://www.youtube.com/iframe_api";
let firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
let player;

let btnPlay = document.querySelector('#botonply');
btnPlay.addEventListener('click',reproducir);

function onYouTubeIframeAPIReady() {
    player = new YT.Player( videoPlayer, {
        height: '100%',
        width: '100%',
        events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange
        }
    });
}

function onPlayerReady(event) {
    console.log(event, 'Evento Listo!!');
}

let done = false;
function onPlayerStateChange(event) {
    // console.log(event.target.a.id);

    switch (event.data) {
        case 0: {
            document.querySelector("#anti-video").classList.remove('prueba');
            document.querySelector("#anti-video").classList.add('finalVideo');
            document.querySelector("#botonply").classList.remove('visibilidad');
            break;
        }
        case 1: {
            // console.log("reproduciendo");

            break;
        }
        case 2: {
            document.querySelector("#anti-video").classList.add('prueba');
            // console.log("pausado");
            break;
        }

    }

}
document.addEventListener("DOMContentLoaded", function (evt) {
    setInterval(slider,5000);

    let menuMovil = document.querySelector('#mobil-aside');
    let botonMovil = document.querySelector('#hamburguesa');
    botonMovil.addEventListener('click',function () {
        menuMovil.classList.toggle('aparece-menu');
    });


});

function slider() {
    // console.log('hola');
    contenedor.forEach(function (slid) {
        slid.classList.toggle('aparece');
    });
}

function escalada() {
    caja2.forEach(function (cajas, index) {
        switch (index) {
            case 0: {
                cajas.classList.add('animacion-scalada');
            }
                break;
            case 1: {
                setTimeout(function () {
                    cajas.classList.add('animacion-scalada');
                    caja2[0].classList.remove('animacion-scalada');
                }, 900);
            }
                break;
            // case 2: {
            //     setTimeout(function () {
            //         // cajas.classList.add('animacion-scalada');
            //         caja2[1].classList.remove('animacion-scalada');
            //     }, 1800);
            // }
            //     break;
        }

    });

    setTimeout(function () {
        caja2[1].classList.remove('animacion-scalada');
    }, 1800);
}

function reproducir() {
    document.querySelector("#anti-video").classList.remove('finalVideo');
    document.querySelector("#botonply").classList.add('visibilidad');
    player.playVideo();
}
function responseJson(url,method){
    return new Promise((resolve,reject)=>{
        const objResponse = new XMLHttpRequest();
        objResponse.open(method,url,true);
        objResponse.addEventListener('load',evt => {
            if (evt.target.status === 200){
                resolve(JSON.parse(evt.target.responseText))
            }
        });
        objResponse.send();
    });
}
(async()=>{
    let logosContent = document.getElementById('eventos-logos');
    let contentImg = document.createDocumentFragment();
    let containerImg = document.createElement('div');
    let response = await responseJson("https://tecolotl-multimedia.nyc3.digitaloceanspaces.com/Tecolotl/resources/json/ferias-img.json","GET");
    response.forEach((image,index) => {
        let img = document.createElement('img');
        img.src = `https://tecolotl-multimedia.nyc3.digitaloceanspaces.com/Tecolotl/resources/img/feria_Libro/${image.nombre}.${image.extension}`;
        containerImg.appendChild(img);
        if ((index+1) % 3 === 0){
            contentImg.appendChild(containerImg);
            containerImg = document.createElement('div');
        }
    });
    if (containerImg.hasChildNodes()){
        contentImg.appendChild(containerImg);
    }
    logosContent.insertBefore(contentImg,logosContent.querySelector('h2:last-of-type'));
})();