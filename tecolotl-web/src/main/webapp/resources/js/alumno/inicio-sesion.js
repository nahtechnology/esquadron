var alumno = document.querySelector('.alumno');
var profesor = document.querySelector('.profesor');

document.addEventListener('DOMContentLoaded', function (evt) {
    loginImagen(alumno.querySelectorAll('img'));
});

function loginImagen(imagenes) {
    imagenes.forEach(function (imagen) {
        imagen.addEventListener("click", function (evento) {
            imagen.classList.toggle('seleccionado');
        });
    });
}

function cargaContrasenia(evento) {
    let seleccionados = [];
    let imagenes = alumno.querySelectorAll('img');
    for (let i = 0; i < imagenes.length; i++) {
        if (imagenes[i].classList.contains('seleccionado')) {
            x = Math.floor(i / 7);
            y = i % 7;
            seleccionados.push(x.toString().concat(':').concat(y.toString()));
        }
    }
    if (seleccionados.length === 0) {
        UIkit.modal.alert('Estimado usuario, de favor seleccionar una contraseña');
        return false;
    } else {
        alumno.querySelector('input[type=hidden]').value = seleccionados.join(',');
        return true;
    }
}