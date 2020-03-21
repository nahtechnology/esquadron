var contenedor = document.querySelectorAll('#inicio > div  >  div:first-of-type > div');
var boton2,caja2;
var tag = document.createElement('script');
var videoPlayer = document.querySelector('.video-alum');
tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
var player;

var btnPlay = document.querySelector('#botonply');
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

var done = false;
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
    // var spacesEndpoint = new AWS.Endpoint('nyc3.digitaloceanspaces.com');
    // var parametro = {
    //     Bucket: "tecolotl-multimedia",
    //     Key: "Video/Welcome_to_E-squadron.mp4"
    // };
    // var video = document.querySelector('video');
    // var s3 = new AWS.S3({
    //     endpoint: spacesEndpoint,
    //     accessKeyId: 'GEXURWPHJX37JPR2VGWY',
    //     secretAccessKey: 'EOvb5YkVHMViTG12aEvSfsRz5clDgpXGJdlq2UrpHHs'
    //
    // });

    // boton2 = document.querySelector('aside > ul > li:nth-child(2)').addEventListener('click',function (evt) {
    //      caja2 = document.querySelectorAll('#seccion2 > div');
    //     setTimeout(escalada,800);
    // });
    var menuMovil = document.querySelector('#mobil-aside');
    var botonMovil = document.querySelector('#hamburguesa');
    botonMovil.addEventListener('click',function () {
        menuMovil.classList.toggle('aparece-menu');
    });

    // s3.getObject(parametro, function(err, data) {
    //     if (err) {
    //         console.log(err, err.stack);
    //
    //     } else {
    //         console.log(data);
    //         video.src = URL.createObjectURL(new Blob([data.Body], {type: data.ContentType}));
    //         document.querySelector('.video-escuadron > div').classList.add('aparece');
    //     }
    //
    // });

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
