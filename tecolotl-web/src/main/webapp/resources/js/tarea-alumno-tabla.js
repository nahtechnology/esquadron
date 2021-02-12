let nivelesSet = new Set();
let gruposSet = new Set();
let textosSet = new Set();
let nivelActual = document.querySelector('.elimina .uk-card-header > div > div:last-child > p:last-child').innerText.split(':')[1].trim();
let gradoActual = document.querySelector('.elimina .uk-card-body > p:nth-of-type(1)').textContent.split(':')[1].trim();
let grupoActual = document.querySelector('.elimina .uk-card-body > p:nth-of-type(2)').textContent.split(':')[1].trim();
// let nivelSelect = `hover-${nivelActual}`;
let nivelSelect = `hover-todos`;
let grupoSelect = (grupoActual.concat(gradoActual)).replace(/ /g, "");
document.addEventListener('DOMContentLoaded',()=>{
    let seleccionFiltro = document.querySelector('#filtro-nivel');
    let filtroGrupo = document.getElementById('filtro-grupo');
    let contenedorOpcionesFiltro = document.createDocumentFragment();
    let nivelesTabla = document.querySelectorAll('.tabla-alumnos td > img');
    let indiceOpcion;
    let conteoOpcion = 0, piboteOpcion = 0,conteoFiltroOpcion = 0,piboteFiltroOpcion = 0;

    barrasValor();
    switch (nivelActual) {
        case "A1":
            indiceOpcion = "1";
            break;
        case "A2":
            indiceOpcion = "2";
            break;
        case "B1":
            indiceOpcion = "3";
            break;
        case "B2":
            indiceOpcion = "4";
            break;
        case "C1":
            indiceOpcion = "5";
            break;
    }
    crearNiveles(nivelesTabla);
    // console.log(conteoOpcion);
    if(!nivelesSet.has(nivelActual)){
        nivelesSet.add(nivelActual);
    }
    nivelesSet.forEach((opcion) =>{
        let etiqueta = document.createElement('option');
        etiqueta.value=opcion;
        etiqueta.innerText = `level ${opcion}`;
        seleccionFiltro.appendChild(etiqueta);
        if(nivelActual === opcion){
            piboteOpcion = conteoOpcion + 1;
        }
        conteoOpcion++;
    });
    let conteo = 0
    gruposSet.forEach((opcion) => {
        let elemento = document.createElement('option');
        elemento.value = opcion;
        elemento.textContent = Array.from(textosSet)[conteo];
        contenedorOpcionesFiltro.appendChild(elemento);
        if(grupoSelect === opcion){
            piboteFiltroOpcion = conteoFiltroOpcion + 1;
        }
        conteo++;
    });

    filtroGrupo.appendChild(contenedorOpcionesFiltro);

    seleccionFiltro.addEventListener('change',(evt)=>{
        nivelSelect = `hover-${evt.target.value}`;
        verFilas(nivelSelect,grupoSelect);
    });
    filtroGrupo.addEventListener('change',evt => {
        grupoSelect = evt.target.value;
        console.log(grupoSelect);
        verFilas(nivelSelect,grupoSelect);
    });
    //selecciona el nivel y grupo alctual
    /*
    seleccionFiltro.selectedIndex = `${piboteOpcion}`;
    filtroGrupo.selectedIndex = `${piboteFiltroOpcion}`;*/
    seleccionFiltro.selectedIndex = "0";
    filtroGrupo.selectedIndex = `${piboteFiltroOpcion}`;
    puntajeGlobal();

});
function crearNiveles(nivelesTabla) {
    nivelesTabla.forEach(nivel => {
        let nivelActividad = nivel.dataset.nivel;
        nivelesSet.add(nivelActividad);
        nivel.parentElement.parentElement.classList.add(nivel.dataset.grupo.replace(/ /g, "").concat(nivel.dataset.grado));
        gruposSet.add(nivel.dataset.grupo.replace(/ /g, "").concat(nivel.dataset.grado));
        textosSet.add(nivel.dataset.grado.concat(" ",nivel.dataset.grupo));
        switch (nivelActividad) {
            case "A1":
                nivel.parentElement.parentElement.classList.add('hover-A1');
                break;
            case "A2":
                nivel.parentElement.parentElement.classList.add('hover-A2');
                break;
            case "B1":
                nivel.parentElement.parentElement.classList.add('hover-B1');
                break;
            case "B2":
                nivel.parentElement.parentElement.classList.add('hover-B2');
                break;
            case "C1":
                nivel.parentElement.parentElement.classList.add('hover-C1');
                break;
            case "C2":
                nivel.parentElement.parentElement.classList.add('hover-A1');
                break;
        }
    });
}
function verFilas(nivel,grupo) {
    // console.log(nivel,grupo);
    let cuerpoTabla = document.querySelector('.tabla-alumnos');
    let filas = document.querySelectorAll('.tabla-alumnos tbody > tr');
    if (cuerpoTabla.querySelector('tbody .fila-vacia') !== null ){
        let numeroFila = cuerpoTabla.querySelector('tbody .fila-vacia').rowIndex;
        cuerpoTabla.deleteRow(numeroFila);
    }
    cuerpoTabla.classList.add('visibilidad-fila');
    setTimeout(animacionFilas,500,filas,nivel,cuerpoTabla,grupo);
}

function animacionFilas(filas,nivel,tabla,grupo) {
    let cuenta = 0;
    let cuerpo = tabla.querySelector('tbody');
    let tablaAlumnosCuerpo = document.querySelector('.tabla-alumnos > tbody');
    let nodoAsignadas = document.querySelector('#tareas-asignadas > span');
    let nodoPromediadas = document.querySelector('#tareas-promediadas > span');
    let nodoPromedioGeneral = document.querySelector('#promedio-general > span');
    let datosVista = new PromedioGeneralActividad(tablaAlumnosCuerpo,nodoAsignadas,nodoPromediadas,nodoPromedioGeneral,nivel,grupo);
    // console.log(nivel);
    filas.forEach(fila => {
        if (nivel === "hover-todos" && grupo === "todos"){
            cuenta = 1;
            fila.style.display="table-row"
        }else if (!(verdaderoFalsoNivel(fila,nivel) && verdaderoFalsoGrupo(fila,grupo))){
            fila.style.display="none";
        }else {
            cuenta = 1;
            fila.style.display="table-row";
        }
    });
    if (cuenta === 0){
        let row = cuerpo.insertRow(0);
        let cel1 = row.insertCell(0);
        cel1.colSpan = 9;
        let img = document.createElement('img');
        img.src ="https://tecolotl-multimedia.nyc3.digitaloceanspaces.com/Tecolotl/resources/img/vacio.svg";
        cel1.appendChild(img);
        row.classList.add('fila-vacia');
    }
    tabla.classList.remove('visibilidad-fila');
    datosVista.promedio();
}
function barrasValor() {
    let progreso = document.getElementById("formulario-tabla-actividades:tabla-actividades");
    let filas = progreso.querySelectorAll('tbody tr > td');
    let barra = [];
    filas.forEach(function (fila) {
        if(fila.querySelector('progress')){
            barra.push(fila);
        }
    });
    barra.forEach(function (progres) {
        let valor = document.createElement('span');
        valor.innerHTML = progres.querySelector('progress').value + '%';
        progres.insertBefore(valor,progres.querySelector('progress'));
    });
}
function renderizarBorrado(data) {
    // let seleccionFiltro = document.querySelector('#filtro-nivel');
    if (data.status === "success"){
        let nivelesTabla = document.querySelectorAll('.tabla-alumnos td > img');
        // let nivel = `hover-${seleccionFiltro.value}`;
        barrasValor();
        crearNiveles(nivelesTabla);
        verFilas(nivelSelect,grupoSelect);
    }
}

function verdaderoFalsoNivel(fila,nivel){
    return fila.classList.contains(nivel) || nivel === 'hover-todos';
}
function verdaderoFalsoGrupo(fila,grupo){
    return fila.classList.contains(grupo) || grupo === 'todos';
}

function puntajeGlobal(){
    let tablaAlumnosCuerpo = document.querySelector('.tabla-alumnos > tbody');
    let nodoAsignadas = document.querySelector('#tareas-asignadas > span');
    let nodoPromediadas = document.querySelector('#tareas-promediadas > span');
    let nodoPromedioGeneral = document.querySelector('#promedio-general > span');
    let datosVista = new PromedioGeneralActividad(tablaAlumnosCuerpo,nodoAsignadas,nodoPromediadas,nodoPromedioGeneral,'hover-todos','todos');
    datosVista.promedio();
    let totalTareas=document.querySelector('#tareas-asignadas > span').textContent;
    let tareasCalificadas = document.querySelector('#tareas-promediadas > span').textContent;
    let promedio = document.querySelector('#promedio-general > span').textContent;
    document.querySelector('#puntaje-reporte span:first-child').textContent = totalTareas;
    document.querySelector('#puntaje-reporte span:nth-child(2)').textContent = tareasCalificadas;
    document.querySelector('#puntaje-reporte span:last-child').textContent = promedio;
    verFilas(nivelSelect,grupoSelect);
}

class PromedioTarea {
    constructor(transcrip,gramatica,mapaMental,relacionarImagen,completarOracion,ordenarOraciones,relacionarOracion) {
        this.transcrip = parseInt(transcrip);
        this.gramatica = parseInt(gramatica);
        this.mapaMental = parseInt(mapaMental);
        this.relacionarImagen = parseInt(relacionarImagen);
        this.completarOracion = parseInt(completarOracion);
        this.ordenarOraciones = parseInt(ordenarOraciones);
        this.relacionarOracion = parseInt(relacionarOracion);
    }

    promedio(){
        let permitido = this.sinPromedio(this.transcrip) && this.sinPromedio(this.gramatica) && this.sinPromedio(this.mapaMental) && this.sinPromedio(this.relacionarImagen) && this.sinPromedio(this.completarOracion) && this.sinPromedio(this.ordenarOraciones) && this.sinPromedio(this.relacionarOracion);
        let divisor = 0;
        let dividendo = 0;
        if (permitido){
            let datos = [this.transcrip,this.gramatica,this.mapaMental,this.relacionarImagen,this.completarOracion,this.ordenarOraciones,this.relacionarOracion];
            datos.forEach(dato =>{
                divisor += (dato === -2 ? 0 : 1);
                dividendo += (dato === -2 ? 0 : dato);
            });
            return Math.round(dividendo/divisor);
        }else{ return permitido;}
    }
    sinPromedio(tarea){
        return !(tarea === -1 || tarea === 0);
    }

}

class PromedioActividad {
    constructor(transcrip,gramatica,mapaMental,relacionarImagen,completarOracion,ordenarOraciones,relacionarOracion) {
        this.transcrip = parseInt(transcrip);
        this.gramatica = parseInt(gramatica);
        this.mapaMental = parseInt(mapaMental);
        this.relacionarImagen = parseInt(relacionarImagen);
        this.completarOracion = parseInt(completarOracion);
        this.ordenarOraciones = parseInt(ordenarOraciones);
        this.relacionarOracion = parseInt(relacionarOracion);
    }
    promedio(){
        let permitido = this.sinPromedio(this.transcrip) && this.sinPromedio(this.gramatica) && this.sinPromedio(this.mapaMental) && this.sinPromedio(this.relacionarImagen) && this.sinPromedio(this.completarOracion) && this.sinPromedio(this.ordenarOraciones) && this.sinPromedio(this.relacionarOracion);
        if (permitido){
            return [this.transcrip, this.gramatica, this.mapaMental, this.relacionarImagen, this.completarOracion, this.ordenarOraciones, this.relacionarOracion];
        }else{ return permitido;}
    }
    sinPromedio(tarea){
        return !(tarea === -3 || tarea === 4);
    }
}

class PromedioGeneral {
    constructor(tabla,tareasAsignadas,tareasPromediadas,promedioGeneral,nivel) {
        this.tabla = tabla;
        this.tareasAsignadas = tareasAsignadas;
        this.tareasPromediadas = tareasPromediadas;
        this.promedioGeneral = promedioGeneral;
        this.nivel = nivel;
    }
    promedio2(){
        let filas;
        let promedios = [];
        let dividendo = 0;
        if (this.nivel === "hover-todos"){
            filas = this.tabla.querySelectorAll("tr");
        }else { filas = this.tabla.querySelectorAll(`tr.${this.nivel}`)}
        filas.forEach(fila =>{
            let columnas = fila.querySelectorAll('td');
            let promedioFila = new PromedioTarea(columnas[2].dataset.transcrip,columnas[3].dataset.gramatica,columnas[4].dataset.mapaMental,columnas[5].dataset.relacionarImagen,columnas[6].dataset.completarOracion,columnas[7].dataset.ordenarOraciones,columnas[8].dataset.relacionarOraciones);
            // console.log(promedioFila);
            if (promedioFila.promedio() !== false){
                promedios.push(promedioFila.promedio());
                console.log(promedioFila.promedio());
            }
        });
        if (promedios.length > 0){
            promedios.forEach(total => {
                dividendo += total;
            });
        }
        this.tareasAsignadas.innerText = filas.length;
        this.tareasPromediadas.innerText = promedios.length;
        this.promedioGeneral.innerText = promedios.length > 0 ? Math.round(dividendo/promedios.length) : "Nothing answered yet!";
    }

}

class PromedioGeneralActividad {
    constructor(tabla,tareasAsignadas,tareasPromediadas,promedioGeneral,nivel,grupo) {
        this.tabla = tabla;
        this.tareasAsignadas = tareasAsignadas;
        this.tareasPromediadas = tareasPromediadas;
        this.promedioGeneral = promedioGeneral;
        this.nivel = nivel;
        this.grupo = grupo;
    }
    promedio(){
        let filas;
        let actividades = [];
        let puntajes = [0,0,0,0,0,0,0];
        let dividendos = [0,0,0,0,0,0,0];
        let promedios = [],promedio = 0;
        if (this.nivel === "hover-todos" && this.grupo === "todos"){
            filas = this.tabla.querySelectorAll("tr");
        }else if( this.tabla.querySelectorAll(`tr.${this.nivel}.${this.grupo}`).length > 0) {
            filas = this.tabla.querySelectorAll(`tr.${this.nivel}.${this.grupo}`)
        }else {
            if (this.nivel === "hover-todos" && this.tabla.querySelectorAll(`tr.${this.grupo}`).length > 0 ){
                filas = this.tabla.querySelectorAll(`tr.${this.grupo}`);
            }
            if (this.grupo === "todos" && this.tabla.querySelectorAll(`tr.${this.nivel}`).length > 0 ){
                filas = this.tabla.querySelectorAll(`tr.${this.nivel}`);
            }
        }
        if (filas !== undefined){
            filas.forEach(fila =>{
                let columnas = fila.querySelectorAll('td');
                let promedioFila = new PromedioActividad(columnas[2].dataset.transcrip,columnas[3].dataset.gramatica,columnas[4].dataset.mapaMental,columnas[5].dataset.relacionarImagen,columnas[6].dataset.completarOracion,columnas[7].dataset.ordenarOraciones,columnas[8].dataset.relacionarOraciones);
                // console.log(promedioFila);
                if (promedioFila.promedio() !== false){
                    actividades.push(promedioFila.promedio());
                }
            });
            if (actividades.length > 0){
                actividades.forEach(actividad => {
                    for (let i = 0;i<7;i++){
                        dividendos[i] += (actividad[i] === -2 ? 0 : 1);
                        puntajes[i] += ((actividad[i] === -2 || actividad[i] === -1) ? 0 : actividad[i]);
                    }
                });
                for (let i = 0; i < 7; i++){
                    if (dividendos[i] > 0){
                        let dato = Math.round(puntajes[i]/dividendos[i]);
                        promedios.push(dato)
                    }
                }
                promedios.forEach(pr =>{
                    promedio += pr;
                })
            }
            // console.log(dividendos);
            // console.log(puntajes);
            this.tareasAsignadas.innerText = filas.length;
            this.tareasPromediadas.innerText = actividades.length;
            this.promedioGeneral.innerText = actividades.length > 0 ? Math.round(promedio/promedios.length) : "Nothing answered yet!";
            // console.log(promedios);
        }else {
            this.tareasAsignadas.innerText = 0;
            this.tareasPromediadas.innerText = 0;
            this.promedioGeneral.innerText = "Nothing answered yet!";
        }



    }

}