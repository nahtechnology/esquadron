function puntaje() {

    var barras = document.querySelectorAll('.padding-contenedor progress');
    var cajas = document.querySelectorAll('.califica');

    cajas.forEach(calcula);

    function calcula(puntaje, indice, putajesLocal) {
        let valor = puntaje.innerText.split(':')[1];

        if(0 <= parseInt(valor) && parseInt(valor) <= 25){
            barras[indice].classList.add("rojo");
        }
        else if(26 <= parseInt(valor) && parseInt(valor) <= 85){
            barras[indice].classList.add("amarillo");
        }
        else if(86 <= parseInt(valor) && parseInt(valor) <= 100){
            barras[indice].classList.add("verde");
        }

    }

    clearTimeout(tiempo);
}

document.addEventListener('DOMContentLoaded',function () {
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

    s3.getObject(parametro, function(err, data) {
        if (err) {
            console.log(err, err.stack);

        } else {
            console.log(data);
            video.src = URL.createObjectURL(new Blob([data.Body], {type: data.ContentType}));
            document.querySelector('.video-alumno > div').style.display="none";
        }

    });


});
