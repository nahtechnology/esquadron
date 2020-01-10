var alumno = document.querySelector('.alumno');
var profesor = document.querySelector('.profesor');
var esloginProfesor = false;

document.addEventListener('DOMContentLoaded', function (evt) {
    loginImagen(alumno.querySelectorAll('img'));
    profesor.querySelector('button').addEventListener('click', loginProfesor);
});

function loginImagen(imagenes) {
    imagenes.forEach(function (imagen) {
        imagen.addEventListener("click", function (evento) {
            if(imagen.classList.toggle('seleccionado') === false){
                var etiqueta = document.querySelector('ul.login-menu li');
                etiqueta.click();
                console.log('entro');
            }
        });
    });
}

function cargaContrasenia(evento) {
    let seleccionados = [],contra=[];
    let imagenes = alumno.querySelectorAll('img');
    for (let i = 0; i < imagenes.length; i++) {
        if (imagenes[i].classList.contains('seleccionado')) {
            // x = i % 7;
            // y = Math.floor(i / 7);
            // seleccionados.push(x.toString().concat(':').concat(y.toString()));
            seleccionados.push(i);
        }
    }
    if (seleccionados.length === 0 && !esloginProfesor) {
        UIkit.modal.alert(mensaje);
        return false;
    } else {
        alumno.querySelector('input[type=hidden]').value = seleccionados.join(',');
        console.log(prueba);
        return true;
    }
}

function loginProfesor(evento) {
    esloginProfesor = true;
    let usuario = profesor.querySelector('input[type=text]').value;
    let contrasenia = profesor.querySelector('input[type=password]').value;
    alumno.querySelector('input[type=text]').value = usuario;
    alumno.querySelector('input[type=hidden]').value = contrasenia;
    alumno.querySelector('input[type=submit]').click();
}