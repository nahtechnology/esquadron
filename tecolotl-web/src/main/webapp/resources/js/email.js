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
            Host : "in-v3.mailjet.com",
            Username : "f8fb25bef858388aea9dedeb6042676d",
            Password : "64705e76a3d268cd48ba0784fdb235b4",
            To : 'cu.es.guillermo@gmail.com',
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