var modalGrupo;

document.addEventListener('DOMContentLoaded', inicio);

function inicio(evento) {
    modalGrupo = document.getElementById('modal-grupo');
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
        }, 5000);
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