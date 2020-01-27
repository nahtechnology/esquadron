let modalAlumno;

document.addEventListener('DOMContentLoaded', inicio);

function inicio(evento) {
    modalAlumno = document.getElementById('modal-nuevo-alumno');
}

function cierraModal(data) {
    if (data.status === 'success') {
        if (modalAlumno.querySelector('.uk-alert-danger') === null) {
            modalAlumno.querySelectorAll('input[type=text]').forEach(entrada => entrada.value= '');
            document.getElementById('formulario-modal-nuevo-profesor:input-secret-password').value = '';
            modalAlumno.querySelectorAll('select').forEach(seleccion => seleccion.selectedIndex = 0);
            UIkit.modal(modalAlumno).hide();
        }
    }
}