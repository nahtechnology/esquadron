var modalProfresor;
var modalEliminar;

document.addEventListener('DOMContentLoaded', ejecucionInicio);

function ejecucionInicio(evento) {
    modalProfresor = document.getElementById('modal-profesor');
    modalEliminar = document.getElementById('modal-eliminar');
    modalProfresor.querySelector('.uk-modal-footer button').addEventListener("click", cierra);
    abrirModal();
    elimina();
}

function cierra(evento) {
    cerrarModal(null, "modalProfresor");
    cerrarModal(null, "modalGrupo");
}

function elimina() {
    modalProfresor.querySelector('.uk-form-controls select').removeAttribute("size");
}

function cerrarModal(data , modal) {
    if (data === null) {
        switch (modal) {
            case 'modalProfresor' :
                if (modalProfresor.querySelector('.uk-text-danger') === null) {
                    UIkit.modal(modalProfresor).hide();
                }
                break;
            case 'modal-eliminar':
                UIkit.modal(modalEliminar).hide();
                break;
            default :
                console.error('Aun no se programa este modal:' + modal);
        }
    } else {
        if (data.status === "success") {
            switch (modal) {
                case 'modalProfresor' :
                    if (modalProfresor.querySelector('.uk-text-danger') === null) {
                        UIkit.modal(modalProfresor).hide();
                    }
                    break;
                case 'modal-eliminar':
                    UIkit.modal(modalEliminar).hide();
                    break;
                default :
                    console.error('Aun no se programa este modal:' + modal);
            }
        }
    }
}


function abrirModal() {
    UIkit.util.on('#modal-profesor', 'shown', function () {
        modalProfresor.querySelector('.uk-modal-header').click();
        modalProfresor.querySelector('.uk-form-controls select').removeAttribute("size");
    });
    UIkit.util.on('#modal-eliminar', 'show', function () {
        modalEliminar.querySelector('.uk-modal-header').click();
    });
}