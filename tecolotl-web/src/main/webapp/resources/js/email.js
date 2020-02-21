document.addEventListener("DOMContentLoaded",function () {
    var labels = ['Nombre:','Colegio:','Cuidad:','Correo:','Telefono:'];
    var botonEmail = document.querySelector('#seccion3 > div > div > div:last-child > div');
     mensaje="";
    botonEmail.addEventListener('click',function () {
        var contactos = document.querySelectorAll('#seccion3 div > input');
        var cuenta = 0;
        contactos.forEach(function (texto,index) {
            if(texto.value !== ""){
                mensaje += labels[index].concat(texto.value.concat(" "));
                cuenta+=1;
                texto.classList.remove('inputError');
            }else {
                texto.classList.add('inputError');
            }
        });

        if(cuenta === 5){
            sendEmail();
        }else {cuenta = 0;
                mensaje = "";
                }
    });

    function sendEmail() {
        var contactos = document.querySelectorAll('#seccion3 div > input');
        Email.send({
            SecureToken: "96f1d275-0ac3-45ea-a6bb-0f84511e6843",
            To : 'e-squadron@e-squadron.com.mx',
            From : "maugg76@gmail.com",
            Subject : "Contacto Escuela",
            Body : mensaje
        }).then(
            console.log('envio')
        );
        contactos.forEach(function (texto) {
            texto.value = "";
        });
        mensaje = "";
    }
});