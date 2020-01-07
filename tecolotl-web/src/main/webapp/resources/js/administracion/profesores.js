var modalProfresor;

document.addEventListener('DOMContentLoaded', ejecucionInicio);

function ejecucionInicio(evento) {
    modalProfresor = document.getElementById('modal-profesor');
    modalProfresor.querySelector('.uk-modal-footer button').addEventListener("click", cierra);
    elimina();
}

function cierra(evento) {
    cerrarModal(null, "modalProfresor");
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
            default :
                console.error('Aun no se programa este modal:' + modal);
        }
    } else {
        if (data.status === "success") {
            switch (modal) {
                case 'modalProfresor' :
                    if (modalProfresor.querySelector('.uk-text-danger') === null) {
                        UIkit.modal(modalProfresor).hide();
                        document.getElementById('formulario-profesor').reset();
                    }
                    break;
                default :
                    console.error('Aun no se programa este modal:' + modal);
            }
        }
    }
}