let modalCicloEscolar;
let modalActivar;

document.addEventListener('DOMContentLoaded', inicio);

function inicio(evento) {
    modalCicloEscolar = document.getElementById('modal-escuela');
    modalActivar = document.getElementById('modal-activar');
}

function cierraModal(data, modal) {
    if (data.status === 'success') {
        switch (modal) {
            case 'modalCicloEscolar':
                if (modalCicloEscolar.querySelector('.uk-alert-danger') === null) {
                    UIkit.modal(modalCicloEscolar).hide();
                }
                break;
            case 'modalActivar':
                if (modalActivar.querySelector('.uk-alert-danger') === null) {
                    UIkit.modal(modalActivar).hide();
                }
                break;
            default:
                console.error('Opcion un no programada:' + modal);
        }
    }
}

function abreModal(modal) {
    switch (modal) {
        case 'modalActivar':
            modalActivar.querySelector('.uk-modal-header').click();
            break;
        case 'modalCicloEscolar':
            modalCicloEscolar.querySelector('.uk-modal-header').click();
            break;
        default:
            console.log('Opcion aun no programada' + modal);
    }
}