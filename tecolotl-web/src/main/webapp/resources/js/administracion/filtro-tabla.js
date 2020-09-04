let datos = [];
class FiltradoTabla {
    constructor(tabla) {
        this.tabla = tabla;
        this.busquedad = document.createDocumentFragment();
        this.incidesNodos = [];
        this.inputs;
    }
    inicio() {
        let btnReseteo = document.createElement('button');
        let pibote = document.createElement('input')
        let btnGlobal = document.createElement('button');
        pibote.setAttribute("type", "checkbox");
        let filtros = this.tabla.getAttribute("nah-filtrado").split(',');
        let cabeceras = this.tabla.querySelectorAll('th');
        let conteo = 0;

        btnReseteo.setAttribute('type','button');
        btnGlobal.setAttribute('type','button');
        btnReseteo.textContent = "RESET";
        btnGlobal.textContent = "Buscar"
        btnReseteo.id = "reseteo"
        btnGlobal.id="busqueda-global"
        btnGlobal.style.display="none";
        btnReseteo.classList.add('uk-button','uk-button-primary','uk-button-small','uk-margin-small-top','uk-margin-small-right');
        btnGlobal.classList.add('uk-button','uk-button-primary','uk-button-small','uk-margin-small-top','uk-margin-small-right');
        pibote.classList.add('uk-checkbox');
        for (let index = 0; index < cabeceras.length; index++) {
            if (conteo === filtros.length) {
                break;
            }
            if (cabeceras[index].textContent === filtros[conteo]) {
                this.busquedad.appendChild(this.crearElementoBusqueda(filtros[conteo]));
                conteo++;
                this.incidesNodos.push(index);
                index = -1;
            }         
        }
        this.busquedad.appendChild(btnGlobal);
        this.busquedad.appendChild(btnReseteo);
        this.busquedad.appendChild(pibote);
    }
    agregarEvento() {
        this.inicio();
        let listaNodos = this.buscarIndices();
        this.inputs = this.busquedad.querySelectorAll('input[type=text]');
        let botones = this.busquedad.querySelectorAll('div button');
        let botonBusqueda = this.busquedad.getElementById('busqueda-global');
        let btnReset = this.busquedad.getElementById('reseteo');
        let caja = this.busquedad.querySelector('input[type=checkbox]');
        let filtradoGlobal = this.buscarIndicesGlogal();

        botones.forEach((elemento, index) => {
            elemento.addEventListener('click', evt => {
                this.limpiarTabla();
                this.tabla.classList.add('ver-tabla');
                const filtrado = Array.from(listaNodos[index]).filter(celda => celda.textContent.trim() === evt.target.previousElementSibling.value.trim());
                for (const celda of filtrado) {
                    celda.parentElement.classList.add('ver-fila');
                }

            });
        });
        caja.addEventListener('change', evt => {
            if(evt.target.checked){
                for (const boton of botones) {
                    boton.style.display = "none"
                }
                botonBusqueda.style.display = "initial"
            }else{
                for (const boton of botones) {
                    boton.style.display = "initial"
                }
                botonBusqueda.style.display = "none";
            }
        });

        botonBusqueda.addEventListener('click',evt =>{
            let cadenaFiltrada = "",datosFiltrados;
            this.limpiarTabla();
            this.tabla.classList.add('ver-tabla');
            for (const input of this.inputs) {
                cadenaFiltrada = cadenaFiltrada.concat(input.value);
            }    
            datosFiltrados = filtradoGlobal.filter(fila => fila.getCadenaCeldas() === cadenaFiltrada.trim());
            datosFiltrados[0].getPadre().classList.add('ver-fila');
    
        });

        btnReset.addEventListener('click',()=>{
            this.researTabla();    
        });
        this.tabla.parentElement.parentElement.insertBefore(this.busquedad, this.tabla.parentElement);
    }

    crearElementoBusqueda(texto) {
        let ContenedorBusqueda = document.createElement('div');
        ContenedorBusqueda.style.display = "inline-block";
        ContenedorBusqueda.style.padding = "5px";
        let comentario = document.createElement('label');
        comentario.textContent = texto.concat(':');
        let inputBusqueda = document.createElement('input');
        inputBusqueda.setAttribute("type","text");
        let btnBusqueda = document.createElement('button');
        btnBusqueda.textContent = "buscar";
        inputBusqueda.classList.add('uk-input','uk-form-small','uk-margin-small-bottom');
        btnBusqueda.classList.add('uk-button','uk-button-primary','uk-button-small');
        ContenedorBusqueda.classList.add('tamano-cotenedor-input');
        comentario.classList.add('uk-text-capitalize','uk-text-bold');
        btnBusqueda.setAttribute('type','button');

        ContenedorBusqueda.appendChild(comentario);
        ContenedorBusqueda.appendChild(inputBusqueda);
        ContenedorBusqueda.appendChild(btnBusqueda);
        return ContenedorBusqueda;
    }

    buscarIndices() {
        let busquedaNodos = [];
        for (const indice of this.incidesNodos) {
            busquedaNodos.push(this.tabla.querySelectorAll(`tbody td:nth-child(${indice + 1})`));
        }
        return busquedaNodos;
    }
    buscarIndicesGlogal() {
        let busquedaNodosGlobal = [];
        let filas = this.tabla.querySelectorAll('tbody tr')
        for (const row of filas) {
            let celdas = [];
            for (const indices of this.incidesNodos) {
                celdas.push(row.querySelector(`td:nth-child(${indices + 1})`));
            }
            busquedaNodosGlobal.push(new Fila(celdas, celdas[0].parentElement));
        }
        return busquedaNodosGlobal;
    }

    limpiarTabla(){
        this.tabla.classList.remove('ver-tabla');
        if (this.tabla.querySelectorAll('tbody tr.ver-fila').length > 0) {
            for (const fila of this.tabla.querySelectorAll('tr.ver-fila')) {
                fila.classList.remove('ver-fila');
            }
        }
    }

    researTabla(){
        this.limpiarTabla();
        for (const input of this.inputs) {
            input.value="";
        }

    }

}
class Fila {
    constructor(celdas, elementoPadre) {
        this.celdas = celdas;
        this.elementoPadre = elementoPadre;
    }
    getCadenaCeldas() {
        let cadena="";
        for (const celda of this.celdas) {
           cadena = cadena.concat(celda.textContent.trim());
        }
        return cadena;
    }

    getPadre() {
        return this.elementoPadre;
    }
}


function inicioTabla(){
    let elementoFiltrar = document.querySelectorAll("table[nah-filtrado]");

    for (const elemento of elementoFiltrar) {
        datos.push(new FiltradoTabla(elemento));
    }
    for (const dato of datos) {
        dato.agregarEvento();
    }
}




