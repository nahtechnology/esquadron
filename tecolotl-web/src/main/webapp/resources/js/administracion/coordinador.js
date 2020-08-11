
function cargaContrasenia() {
    console.log('entre');
    var imagenes = document.querySelectorAll('#formulario-modal-coordinador .uk-modal-body .login-coordinador > img');
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

function colocarImagenesPassword() {
    let contenedorpassword = document.querySelectorAll('.tablero-licencia #imagen-password-tablero');
    let tablaCoordinador= document.querySelectorAll('[id$=tabla-coordinador] #login-tabla-coordinador');

    for (let i = 0; i < contenedorpassword.length; i++) {
        let contrasena1 = contenedorpassword[i].querySelector('span').innerText.split(',');
        let contrasena2 = tablaCoordinador[i].querySelector('span').innerText.split(',');
        contenedorpassword[i].innerHTML = "";
        tablaCoordinador[i].innerHTML = "";
        contrasena1.forEach(contra1 => {
            let img = document.createElement('img');
            img.src = `../resources/img/alumno/iconos-login/${parseInt(contra1) + 1}.svg`;
            img.style.width = "40px";
            img.style.marginRight = "5px";
            contenedorpassword[i].appendChild(img);
        });
        contrasena2.forEach(contra2 => {
            let img = document.createElement('img');
            img.src = `../resources/img/alumno/iconos-login/${parseInt(contra2) + 1}.svg`;
            img.style.width = "40px";
            img.style.marginRight = "5px";
            tablaCoordinador[i].appendChild(img);
        });
    }
}

function generatePdf() {
    let element = document.querySelector('#modal-password .uk-modal-body');
    let opt = {
        margin:       1,
        filename:     'Passwords-coordinador.pdf',
        image:        { type: 'jpeg', quality: 0.98 },
        html2canvas:  { scale: 2 },
        jsPDF:        {  format: 'legal', orientation: 'landscape' },
        pagebreak: {mode:'avoid-all'}
    };
    html2pdf().set(opt).from(element).save();
}