let formulario;
let usuario;
let galaxia;
let validacion;
let contentEmpty;

document.addEventListener('DOMContentLoaded', function (evt) {
    formulario = document.querySelector('form');
    usuario = formulario.querySelector('input[type=text]');
    galaxia = usuario.nextElementSibling.nextElementSibling;
    validacion = formulario.querySelector('.mns-input');
    loginImagen(formulario.querySelectorAll('img'));
    contentEmpty = document.getElementById('content-empty');
});

function loginImagen(imagenes) {
    imagenes.forEach(function (imagen) {
        imagen.addEventListener("click", function (evento) {
            imagen.classList.toggle('seleccionado')
        });
    });
}

function cargaContrasenia() {
    let seleccionados = [];
    let imagenes = formulario.querySelectorAll('img');
    validacion.textContent = '';
    for (let i = 0; i < imagenes.length; i++) {
        if (imagenes[i].classList.contains('seleccionado')) {
            seleccionados.push(i);
        }
    }
    if (seleccionados.length === 0) {
        UIkit.modal.alert(mensaje);
        return false;
    }
    if (usuario.value.length === 0) {
        validacion.classList.add('mensaje-error');
        validacion.textContent = 'Es necesario introducir un apodo';
        return false;
    }else {  validacion.classList.remove('mensaje-error');}
    if (galaxia.value.length === 0) {
        validacion.classList.add('mensaje-error');
        validacion.textContent = 'Es necesario introducir una galaxia';
        return false;
    }else {
        validacion.classList.remove('mensaje-error');
    }
    formulario.querySelector('input[name=j_password]').value = seleccionados.join(',');
    formulario.querySelector('input[name=j_username]').value = usuario.value.concat(',').concat(galaxia.value);
    return true;
}
