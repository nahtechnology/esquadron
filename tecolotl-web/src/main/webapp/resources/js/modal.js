var botonModal = document.querySelectorAll('[nh-modal]');
var modales = document.querySelectorAll('.modal-contenedor');
var nombresModales = [];
var nahModal = {};

document.addEventListener("DOMContentLoaded",() => {
    if (modales.length > 0) {
        modales.forEach(nombre =>{
            let nombreModal = nombre.id;
            nahModal[nombreModal] = new Modal(nombre.id);

        });

        botonModal.forEach(boton =>{
            boton.addEventListener('click',() =>{
                let identificadorModal = boton.getAttribute('nh-modal');
                let btncerrar = document.querySelector(`#${identificadorModal} .cerrar-modal`);
                nahModal[identificadorModal].abrirModal();


                btncerrar.addEventListener('click',()=>{
                    nahModal[identificadorModal].cerrarModal();
                });

            });
        });

    }
});




function Modal(nombreModal){
    this.nombreModal = nombreModal;
}

Modal.prototype.abrirModal = function () {
    let modal = document.querySelector(`#${this.nombreModal}`);
    modal.style.display = "flex";
    modal.click();
    document.body.style.overflow = 'hidden';
    setTimeout(animacionAbrir,500,modal);

};

Modal.prototype.cerrarModal = function (){
    let modal = document.querySelector(`#${this.nombreModal}`);
    modal.classList.remove('modal-abrir');
    setTimeout(animacionCerrar,500,modal);
    document.body.style.overflow = 'auto';

};

function animacionAbrir(modal){
    modal.classList.add('modal-abrir');
}

function animacionCerrar(modal){
    modal.style.display="none";

}