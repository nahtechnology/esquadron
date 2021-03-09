document.addEventListener('DOMContentLoaded', function (evento) {
    var contenedorPassword = document.querySelector('#modal-password .uk-modal-body');
    var passwords = contenedorPassword.querySelectorAll('p > span');

    passwords.forEach(function (contra,indice) {


        var cadena = contra.innerHTML.split(',');
        contra.innerHTML = "";
        cadena.forEach(function (cuenta) {
            var img = parseInt(cuenta) + 1;
            var imagen = document.createElement('img');
            imagen.src = "https://tecolotl-multimedia.nyc3.digitaloceanspaces.com/Tecolotl/resources/img/alumno/iconos-login/" + img + ".svg";
            imagen.style.marginLeft = "5px";
            contra.appendChild(imagen);

        });

    });



});