var sourceContainer = document.getElementById('formulario-relacionar:tablaRespuestas');

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev){
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev, el) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    el.appendChild(document.getElementById(data));
    console.log(ev.target.childElementCount);
    var retorno;
    if (el.childElementCount > 1) {
        sourceContainer.appendChild(ev.target.firstChild);
        return;
    }
    if (el.childElementCount === 0) {
        console.log()
    }

}



