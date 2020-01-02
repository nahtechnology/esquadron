

document.addEventListener('DOMContentLoaded', function (evento) {
var contenedorPassword = document.querySelector('#modal-password .uk-modal-body');
var passwords = contenedorPassword.querySelectorAll('p > span');

passwords.forEach(function (contra,indice) {
    var img = 0;

    var cadena = contra.innerHTML.split(',');
    contra.innerHTML = "";
    cadena.forEach(function (cuenta) {
        let cadenita;
        cadenita = cuenta.split(':');
        console.log(cadenita);
        cadenita.forEach(function (corde,index) {
            var imagen = document.createElement('img');
            if(index === 0){
                switch (parseInt(corde)) {
                    case 0:
                        img = 1;
                        break;
                    case 1:
                        img = 8;
                        break;
                    case 2:
                        img = 15;
                        break;
                    case 3:
                        img = 22;
                        break;
                }
            }else {
                img+=parseInt(corde);
                console.log(img);
                imagen.src = "../resources/img/alumno/iconos-login/" + img + ".svg";
                // imagen.style.marginLeft = "5px";
                contra.appendChild(imagen);
            }
        });
    })
})

});