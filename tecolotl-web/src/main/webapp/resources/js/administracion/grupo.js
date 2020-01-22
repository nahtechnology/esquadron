var modalGrupo;
var modalBorrarGrupo;

document.addEventListener('DOMContentLoaded', inicio);

function inicio(evento) {
    modalGrupo = document.getElementById('modal-grupo');
    modalBorrarGrupo = document.getElementById('modal-borrar-grupo');
    abreModal();
    seleccion();
}

function seleccion() {
    let selecciona = document.querySelector('#formulario- > input + select > option');
    selecciona.selected = true;
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