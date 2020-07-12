var pibotes = [];
class PaginacionTabla {
    constructor(tabla,elementos,indice){
        this.tabla = tabla;
        this.elementos = parseInt(elementos);
        this.cuerpo = tabla.querySelector('tbody');
        this.contenedor = tabla.parentElement;
        this.btnAtras = document.createElement('button');
        this.btnSiguiente = document.createElement('button');
        this.seleccionPagina = document.createElement('select');
        this.seleccionElementos = document.createElement('select');
        this.indice = indice;
    }


    inicio(){
        this.cuerpo.classList.add('cuerpo-principal');
        let filas = this.tabla.querySelectorAll(".cuerpo-principal > tr");
        for (let i = 0; i < filas.length; i++) {
            filas[i].classList.add('visibilidad');
        }
        for (let i = 0; i < this.elementos; i++) {
            if (filas[i] === undefined) {
                i= this.elementos;
            } else{filas[i].classList.remove('visibilidad');}
        }

        this.btnAtras.disabled = true;
        this.btnSiguiente.disabled = this.paginacion() <= 0;
        this.seleccionPagina.disabled = this.paginacion() <= 0;
        this.seleccionElementos.disabled = this.paginacion() <= 0;
    }
    paginacion(){
        let filas = this.tabla.querySelectorAll(".cuerpo-principal > tr");
        return Math.ceil(filas.length/this.elementos) - 1;

    }
    nuevaPaginacion(){
        while (this.seleccionPagina.hasChildNodes()){
            this.seleccionPagina.removeChild(this.seleccionPagina.firstChild);
        }
        for (let i = 0; i <= this.paginacion() ; i++) {
            let opcion = document.createElement('option');
            opcion.value = `${i+1}`;
            opcion.innerText = `${i+1}`;
            this.seleccionPagina.appendChild(opcion);
        }
    }

    opcionesPaginas(){
        this.seleccionElementos.classList.add('uk-select','uk-form-width-small');
        this.btnSiguiente.classList.add('uk-button','uk-button-primary','uk-margin-small-right');
        this.btnAtras.classList.add('uk-button','uk-button-primary','uk-margin-small-right');
        this.seleccionPagina.classList.add('uk-select','uk-form-width-small','uk-margin-small-right');
        this.btnSiguiente.innerText = "next";
        this.btnAtras.innerText = "back";
        for (let i = 1; i<= 5; i++){
            let opcion = document.createElement('option');
            opcion.value = `${i*5}`;
            opcion.innerText = `${i*5}`;
            this.seleccionElementos.appendChild(opcion);
        }

        for (let i = 0; i <= this.paginacion() ; i++) {
            let opcion = document.createElement('option');
            opcion.value = `${i+1}`;
            opcion.innerText = `${i+1}`;
            this.seleccionPagina.appendChild(opcion);
        }

        this.contenedor.appendChild(this.btnAtras);
        this.contenedor.appendChild(this.btnSiguiente);
        this.contenedor.appendChild(this.seleccionPagina);
        this.contenedor.appendChild(this.seleccionElementos);
    }

    siguiente(numPagina){
        let filas = this.tabla.querySelectorAll(".cuerpo-principal > tr");
        if (numPagina <= this.paginacion()) {
            this.btnAtras.disabled = false;
            let inicio = this.elementos * numPagina;
            let fin = inicio + this.elementos;
            for (let i = 0; i < filas.length; i++) {
                filas[i].classList.add('visibilidad');
            }
            for (let i = inicio; i < fin; i++) {
                if (filas[i] === undefined) {
                    i= fin;
                } else{
                    filas[i].classList.remove('visibilidad');
                }
            }
        }
        if(numPagina >= this.paginacion()){
            this.btnSiguiente.disabled = true;
        }
        this.seleccionPagina.selectedIndex = `${numPagina}`;

    }

    atras(numPagina){
        let filas = this.tabla.querySelectorAll(".cuerpo-principal > tr");
        if (numPagina >= 0) {
            this.btnSiguiente.disabled = false;
            let inicio = this.elementos * numPagina;
            let fin = inicio + this.elementos;
            for (let i = 0; i < filas.length; i++) {
                filas[i].classList.add('visibilidad');
            }
            for (let i = inicio; i < fin; i++) {
                if (filas[i] === undefined) {
                    i= this.fin;
                }else{
                    filas[i].classList.remove('visibilidad');
                }
            }
        }
        if(numPagina <= 0){
            console.log('entro deabilitar botn');

            this.btnAtras.disabled = true;
        }
        this.seleccionPagina.selectedIndex = `${numPagina}`;
    }
    animacion(){
        this.tabla.classList.remove('animacion-tabla');
    }
    mostrar(numPagina){
        let filas = this.tabla.querySelectorAll(".cuerpo-principal > tr");
        let inicio = this.elementos * numPagina;
        let fin = inicio + this.elementos;
        for (let i = 0; i < filas.length; i++) {
            filas[i].classList.add('visibilidad');
        }
        for (let i = inicio; i < fin; i++) {
            if (filas[i] === undefined) {
                i= this.fin;
            }else{
                filas[i].classList.remove('visibilidad');
            }
        }

        if(numPagina <= 0){
            this.btnAtras.disabled = true;
            this.btnSiguiente.disabled = false;
        }else if(numPagina >= this.paginacion()){
            this.btnAtras.disabled = false;
            this.btnSiguiente.disabled = true;
        } else {
            this.btnAtras.disabled = false;
            this.btnSiguiente.disabled = false;
        }
        pibotes[this.indice] = numPagina;
    }


}
// funciones para animar tabla
function animacionSiguiente(obj) {
    obj.animacion();
    obj.siguiente(++pibotes[obj.indice]);
}

function animacionAtras(obj) {
    obj.animacion();
    obj.atras(--pibotes[obj.indice]);
}
function animacionpaginas(obj,evento) {
    obj.animacion();
    obj.mostrar(evento.target.selectedIndex);
}
function animacionSelectElementos(obj,evento) {
    obj.animacion();
    obj.elementos = evento.target.value;
    console.log(evento.target.value);
    obj.inicio();
    obj.nuevaPaginacion();
    pibotes[obj.indice] = 0;
}

/*Funcion para crear paginacion de una tabla*/
function crearPaginacionTabla(element,tabla,indice) {
    let objeto = new PaginacionTabla(tabla,element,indice);
    pibotes[indice] = 0;
    objeto.inicio();
    objeto.opcionesPaginas();
    return objeto;

}