

document.addEventListener('DOMContentLoaded', function (evento) {
var contenedorPassword = document.querySelector('#modal-password .uk-modal-body');
var passwords = contenedorPassword.querySelectorAll('p > span');

passwords.forEach(function (contra,indice) {


    var cadena = contra.innerHTML.split(',');
    contra.innerHTML = "";
    cadena.forEach(function (cuenta) {
        var img = 0;
        let cadenita;
        cadenita = cuenta.split(':');
        cadenita.forEach(function (corde,index) {
            var imagen = document.createElement('img');
            if(index === 0){
                img+=parseInt(corde);
            }else {
                switch (parseInt(corde)) {
                    case 0:
                        img += 1;
                        break;
                    case 1:
                        img += 8;
                        break;
                    case 2:
                        img += 15;
                        break;
                    case 3:
                        img += 22;
                        break;
                }
                imagen.src = "../resources/img/alumno/iconos-login/" + img + ".svg";
                imagen.style.marginLeft = "5px";
                contra.appendChild(imagen);
            }
        });
    });

});



});