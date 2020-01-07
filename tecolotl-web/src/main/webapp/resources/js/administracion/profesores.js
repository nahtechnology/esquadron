var modalProfresor;

document.addEventListener('DOMContentLoaded', ejecucionInicio);

function ejecucionInicio(evento) {
    modalProfresor = document.getElementById('modal-profesor');
    modalProfresor.querySelector('.uk-modal-footer button').addEventListener("click",cerrarModal);
}

function cerrarModal() {
    UIkit.modal(modalProfresor).hide();
}


