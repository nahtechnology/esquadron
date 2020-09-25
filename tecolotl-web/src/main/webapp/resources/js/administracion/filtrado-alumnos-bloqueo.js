document.addEventListener('DOMContentLoaded',()=>{
    let alums = document.querySelectorAll('tbody tr > td:first-child');
    let contentTable = document.getElementById('content-groups');
    let elementSearch = document.getElementById('busqueda-alumnos');
    let btnSearch = elementSearch.nextElementSibling;
    let btnShowTables = btnSearch.nextElementSibling;
    btnSearch.addEventListener('click',()=>{
        let filter = Array.from(alums).filter(cell => cell.textContent.trim().includes(elementSearch.value.trim()));
        contentTable.classList.add('ocultar-tablas');
        for (const alum of filter) {
            alum.parentElement.parentElement.parentElement.parentElement.parentElement.classList.add('uk-display-block');
            alum.classList.add('uk-text-danger');
        }
    });
    btnShowTables.addEventListener("click",()=>{
       contentTable.classList.remove('ocultar-tablas');
       let filterClass = contentTable.querySelectorAll('.uk-display-block');
       filterClass.forEach(filter =>{
          filter.classList.remove('uk-display-block');
          filter.querySelectorAll('.uk-text-danger').forEach(text =>{
              text.classList.remove('uk-text-danger');
          })
       });
        elementSearch.value = "";
    });


})