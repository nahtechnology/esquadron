document.addEventListener("DOMContentLoaded",function () {
    
    let labels = ['Nombre:','Whatsapp:','Email:','Nivel:','Colegio:','Webside:','Direccion:','Medio Social:'];
    let mensaje="";
    let form = document.getElementById('form');
    form.addEventListener('submit',(evt)=>{
        let datosEnviar = evt.target;
        let contenedores = datosEnviar.parentElement.querySelectorAll('form > div');
        contenedores.forEach((content,index) => {
            let cadena = "";
            content.querySelectorAll('[name]').forEach(input => {
                if (input.type === "checkbox"){
                    if(input.checked){
                        cadena = cadena.concat(input.value.concat(" "));
                    }
                }else{
                    cadena = cadena.concat(input.value.concat(" "));
                }

            });
            mensaje = mensaje.concat(labels[index].concat(cadena));

        });
        console.log(mensaje);
        sendEmail();
        form.reset();
    })
    // var botonEmail = document.querySelector('#seccion3 > div > div > div:last-child > div');
    // botonEmail.addEventListener('click',function () {
    //     var contactos = document.querySelectorAll('#seccion3 div > input');
    //     var cuenta = 0;
    //     contactos.forEach(function (texto,index) {
    //         if(texto.value !== ""){
    //             mensaje += labels[index].concat(texto.value.concat(" "));
    //             cuenta+=1;
    //             texto.classList.remove('inputError');
    //         }else {
    //             texto.classList.add('inputError');
    //         }
    //     });
    //
    //     if(cuenta === 5){
    //         sendEmail();
    //     }else {cuenta = 0;
    //             mensaje = "";
    //             }
    // });
    
    function sendEmail() {
        Email.send({
            SecureToken :'96c7e030-d64a-44b2-b925-85eb7b23d35c',
            To : 'e-squadron@e-squadron.com.mx',
            From : "maugg76@gmail.com",
            Subject : "Contacto Escuela",
            Body : mensaje
        }).then(
            console.log('envio')
        );
        mensaje = "";
    }
});