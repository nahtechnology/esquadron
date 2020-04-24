document.addEventListener("DOMContentLoaded",() => {
    var botonModal = document.querySelectorAll('[nh-modal]');
    var modales = document.querySelectorAll('.modal-contenedor');
    botonModal.forEach(boton => {
        boton.addEventListener('click',() => {
            let incideModal;
          for (let index = 0; index < modales.length; index++) {
              if(modales[index].id === boton.getAttribute('nh-modal') ){
                  modales[index].style.display = "flex";
                  setTimeout(animacionAbrir,500,modales[index]);
                  incideModal = index;
                  index = modales.length;
              }
          }
          
          
        let botonBorrar = modales[incideModal].querySelector('.cerrar-modal');
        botonBorrar.addEventListener('click',() =>{
            modales[incideModal].classList.remove('modal-abrir'); 
            setTimeout(animacionCerrar,500,modales[incideModal]);    
        }); 
          
        });   
    });

     function animacionAbrir(modal){
         modal.classList.add('modal-abrir');
     }
    
     function animacionCerrar(modal){
        modal.style.display="none";
        
     }


    
    

});
    

