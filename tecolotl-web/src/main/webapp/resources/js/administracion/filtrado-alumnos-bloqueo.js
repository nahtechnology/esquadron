document.addEventListener('DOMContentLoaded',()=>{
    let alums = document.querySelectorAll('tbody tr > td:first-child');
    let contentTable = document.getElementById('content-groups');
    let elementSearch = document.getElementById('busqueda-alumnos');
    let btnSearch = elementSearch.nextElementSibling;
    let btnShowTables = btnSearch.nextElementSibling;
    document.getElementById('all-students-disabled').querySelector('span').textContent =  document.querySelectorAll("table .uk-button-danger").length.toString();
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
    drawCount();
    updateCount();
})
function updateCount(){
    let containerStudents = document.querySelectorAll('table');

    if(containerStudents.length > 0){
        containerStudents.forEach(content =>{
            content.addEventListener('click',evt=>{
                let element = evt.target;
                if (element.nodeName === "INPUT"){
                    setTimeout(drawCount,500);
                }
            })
        })
    }

}
function drawCount(){
    document.querySelectorAll('.uk-card').forEach(elem =>{
        let active = elem.querySelectorAll('.uk-card-body .uk-button-primary');
        let cardHeaderText = elem.querySelector('.uk-card-header > span:last-child > span');
        cardHeaderText.textContent = `Activados: ${active.length.toString()} / Desactivados: ${elem.querySelectorAll('.uk-card-body .uk-button-danger').length.toString()} /`
    });
    let allActives = document.querySelectorAll('table .uk-button-primary');
    document.getElementById('all-students-disabled').querySelector('span:first-child').textContent = allActives.length.toString();
    document.getElementById('all-students-disabled').querySelector('span:last-child').textContent = document.querySelectorAll("table .uk-button-danger").length.toString();
}

