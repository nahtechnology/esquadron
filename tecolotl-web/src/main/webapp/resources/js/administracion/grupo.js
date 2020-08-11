var modalGrupo;
var modalBorrarGrupo;
var modalReasignarGrupo;
var modalReasignarAlumno;
var modalAlumno;

document.addEventListener('DOMContentLoaded', inicio);

function inicio(evento) {
    modalGrupo = document.getElementById('modal-grupo');
    modalBorrarGrupo = document.getElementById('modal-borrar-grupo');
    modalReasignarGrupo = document.getElementById('modal-reasigna-grupo');
    modalReasignarAlumno = document.getElementById('modal-reasigna-alumno');
    modalAlumno = document.getElementById('modal-alumno');
    abreModal();
    seleccion();
    elimina();
}

function seleccion() {
    let selecciona = document.querySelector('#formulario- > input + select > option');
    selecciona.selected = true;
}

function elimina() {
    let eliminacion = document.querySelector('#formulario- > input + select');
    eliminacion.removeAttribute("size");
    modalReasignarGrupo.querySelectorAll('select').forEach(sel => sel.removeAttribute("size"));
    modalReasignarAlumno.querySelectorAll('select').forEach(sel => sel.removeAttribute("size"));
    modalAlumno.querySelectorAll('select').forEach(sel => sel.removeAttribute('size'));
}

function mayuscula(evento) {
    if(evento.inputType === 'insertText'){
        evento.target.value = evento.target.value.toUpperCase();
    }
}

function abreModal() {
    //beforeshow
    UIkit.util.on('#modal-borrar-grupo', 'beforeshow', () => modalBorrarGrupo.querySelector('.uk-modal-header').click());
    UIkit.util.on('#modal-grupo', 'beforeshow', () => modalBorrarGrupo.querySelector('.uk-modal-header').click());
    UIkit.util.on('#modal-reasigna-grupo','beforeshow',() => modalReasignarGrupo.querySelector('.uk-modal-header').click());
    UIkit.util.on('#modal-reasigna-alumno', 'beforeshow', () => modalReasignarAlumno.querySelector('.uk-modal-header').click());
    UIkit.util.on('#modal-alumno', 'beforeshow', () => modalAlumno.querySelector('.uk-modal-header').click())
}

function cerrarModal(data, modal) {
    if (data && data.status === 'success') {
        setTimeout(function () {
            switch (modal) {
                case 'modalGrupo':
                    if (!modalGrupo.querySelector('.uk-text-danger')) {
                        UIkit.modal(modalGrupo).hide();
                    }
                    break;
                case 'modalReasignarGrupo':
                    UIkit.modal(modalReasignarGrupo).hide();
                    break;
                case 'modalReasignarAlumno':
                    UIkit.modal(modalReasignarAlumno).hide();
                default:
                    console.error('Aun no existe modal:' + modal);
            }
        }, 100);
    } else {
        switch (modal) {
            case 'modalGrupo':
                setTimeout(function () {
                    switch (modal) {
                        case 'modalGrupo':
                            if (!modalGrupo.querySelector('.uk-text-danger')) {
                                UIkit.modal(modalGrupo).hide();
                            }
                            break;
                        default:
                            console.error('Aun no existe modal:' + modal);
                    }
                }, 100);
                break;
            default:
                console.error('Aun no existe modal:' + modal);
        }
    }
}