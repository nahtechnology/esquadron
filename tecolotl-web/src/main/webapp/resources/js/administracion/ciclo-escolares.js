let modalCicloEscolar;

document.addEventListener('DOMContentLoaded', inicio);

function inicio(evento) {
    modalCicloEscolar = document.getElementById('modal-escuela');
}


function cierraModal(data, modal) {
    if (data.status === 'success') {
        switch (modal) {
            case 'modalCicloEscolar':
                if (modalCicloEscolar.querySelector('.uk-alert-danger') === null) {
                    UIkit.modal(modalCicloEscolar).hide();
                }
                break;
            default:
                console.error('Opcion un no programada:' + modal);
        }
    }
}