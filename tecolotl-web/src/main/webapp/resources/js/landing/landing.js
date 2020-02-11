var contenedor = document.querySelectorAll('#inicio > div  >  div:first-of-type > div');
var boton2,caja2;
document.addEventListener("DOMContentLoaded", function (evt) {
setInterval(slider,5000);
    var spacesEndpoint = new AWS.Endpoint('nyc3.digitaloceanspaces.com');
    var parametro = {
        Bucket: "tecolotl-multimedia",
        Key: "Video/Welcome_to_E-squadron.mp4"
    };
    var video = document.querySelector('video');
    var s3 = new AWS.S3({
        endpoint: spacesEndpoint,
        accessKeyId: 'GEXURWPHJX37JPR2VGWY',
        secretAccessKey: 'EOvb5YkVHMViTG12aEvSfsRz5clDgpXGJdlq2UrpHHs'

    });

    // boton2 = document.querySelector('aside > ul > li:nth-child(2)').addEventListener('click',function (evt) {
    //      caja2 = document.querySelectorAll('#seccion2 > div');
    //     setTimeout(escalada,800);
    // });
    var menuMovil = document.querySelector('#mobil-aside');
    var botonMovil = document.querySelector('#hamburguesa');
    botonMovil.addEventListener('click',function () {
        menuMovil.classList.toggle('aparece-menu');
    });

    s3.getObject(parametro, function(err, data) {
        if (err) {
            console.log(err, err.stack);

        } else {
            console.log(data);
            video.src = URL.createObjectURL(new Blob([data.Body], {type: data.ContentType}));

        }

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
