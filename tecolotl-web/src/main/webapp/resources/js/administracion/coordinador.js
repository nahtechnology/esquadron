
function cargaContrasenia() {
    console.log('entre');
    var imagenes = document.querySelectorAll('#formulario-modal-coordinador .uk-modal-body .login > img');
    var seleccionados = [];
    for (i = 0; i < imagenes.length; i++) {
        if (imagenes[i].classList.contains('seleccionado')) {
            seleccionados.push(i);
        }
    }
    document.getElementById('formulario-modal-coordinador:input-secret-password').value = seleccionados.length === 0 ? null : seleccionados.join(',');
    console.log(document.getElementById('formulario-modal-coordinador:input-secret-password').value);
    setTimeout(efectosImagenes,500);
}

function efectosImagenes() {
    console.log('entro');
    let imaginLogin = document.querySelectorAll('#formulario-modal-coordinador .uk-modal-body .login-coordinador > img');
    imaginLogin.forEach(img => {
        img.addEventListener('click',()=>{
           img.classList.toggle('seleccionado')
        });
    });
}