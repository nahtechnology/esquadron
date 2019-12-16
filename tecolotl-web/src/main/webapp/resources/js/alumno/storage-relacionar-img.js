var finalPoint = new AWS.Endpoint('nyc3.digitaloceanspaces.com');


// var contenedorimagen = document.querySelector('.contenedor-img');

var ruta = "imagenes_glosario/";
var imagen = document.createElement('img');


document.addEventListener('DOMContentLoaded', function (evt) {
   cargarImagen();
});

function cargarImagen() {
    console.log("entro funcion imagen");
    var imagenDocumento = document.querySelectorAll(".imagen-relacionar img");
    var imagenes = [];
    var parametros = [];
    imagenDocumento.forEach(function (imagen) {
        var contexto = imagen.dataset.llave;
        imagenes.push(contexto);
    });
    var s3 = new AWS.S3({
        endpoint: finalPoint,
        accessKeyId: 'GEXURWPHJX37JPR2VGWY',
        secretAccessKey: 'EOvb5YkVHMViTG12aEvSfsRz5clDgpXGJdlq2UrpHHs'
        //params: {Bucket: "tecolotl-multimedia"}
    });

    imagenes.forEach(function (imagen,index) {
        parametros[index] = {
            Bucket: "tecolotl-multimedia",
            Key: imagen
        };
        s3.getObject(parametros[index], function(err, data) {
            if (err) {
                console.log(err, err.stack);
            }
            else {
                console.log(data);
                var direccion = new Blob([data.Body], {type: data.ContentType});
                console.log(direccion);
                imagenDocumento[index].src = URL.createObjectURL(new Blob([data.Body], {type: data.ContentType}));
            }
        });
    });

}