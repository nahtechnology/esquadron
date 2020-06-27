
var rangos = [];
var pibote = 0;
document.addEventListener("DOMContentLoaded",()=>{
    actualiza();
});


class RangoFecha{
    constructor(fechaInicio,fechaFin){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    };

    tiempoConectado(){
        return this.fechaFin - this.fechaInicio;
    }

}

class PaginacionTabla {
    constructor(tabla,elementos){
        this.tabla = tabla;
        this.elementos = parseInt(elementos);
        this.cuerpo = tabla.querySelector('tbody');
        this.contenedor = tabla.parentElement;
        this.btnAtras = document.createElement('button');
        this.btnSiguiente = document.createElement('button');
        this.seleccionPagina = document.createElement('select');
        this.seleccionElementos = document.createElement('select');
    }


    inicio(){
        let filas = this.cuerpo.querySelectorAll("tr");
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
        let cuerpotabla = this.tabla;
        let filas = cuerpotabla.querySelectorAll('tbody > tr');
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
        let filas = this.cuerpo.querySelectorAll("tr");
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
        let filas = this.cuerpo.querySelectorAll("tr");
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
        let filas = this.cuerpo.querySelectorAll("tr");
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
        pibote = numPagina;
    }
}


function crearFechas(tabla){
    let expresion = new RegExp("/|:");
    let sesion = tabla.querySelectorAll('tbody > tr ');
    let datos = [];
    sesion.forEach((fila,index) => {
        let dato = [];
        fila.querySelectorAll('td').forEach(celda => {
            dato = dato.concat(separar(celda.innerText)); 
        });
         datos.push(new Date(dato[2],dato[1]-1,dato[0],dato[3],dato[4],dato[5]));
        datos.push(new Date(dato[8],dato[7]-1,dato[6],dato[9],dato[10],dato[11]));
        rangos.push(new RangoFecha(datos[0],datos[1]));
         datos = [];
    });

    function separar(cadena){
        return cadena.split(expresion);
    }

}

function timeConversion(millisec) {

    var seconds = (millisec / 1000).toFixed(1);

    var minutes = (millisec / (1000 * 60)).toFixed(1);

    var hours = (millisec / (1000 * 60 * 60)).toFixed(2);

    var days = (millisec / (1000 * 60 * 60 * 24)).toFixed(1);

    if (seconds < 60) {
        return seconds + " Sec";
    } else if (minutes < 60) {
        return minutes + " Min";
    } else if (hours < 24) {
        return hours + " Hrs";
    } else {
        return days + " Days"
    }
}

// funciones para animar tabla
function animacionSiguiente(obj) {
    obj.animacion();
    obj.siguiente(++pibote);
}

function animacionAtras(obj) {
    obj.animacion();
    obj.atras(--pibote);
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
    pibote = 0;
}
//funcion jsf
function actualiza() {
    rangos = [];
    pibote = 0;
    let tabla = document.querySelector('table');
    let filas = tabla.querySelectorAll('tbody > tr');
    let cabeceraTabla = document.createElement("th");
    cabeceraTabla.innerText = "Tiempo";
    tabla.querySelector('thead').appendChild(cabeceraTabla);
    let tiempoAcumulado = 0;
    objDate = crearFechas(tabla);

    rangos.forEach((rango,index) => {
        let celda = filas[index].insertCell(-1);
        celda.innerText = timeConversion(rango.tiempoConectado());
        tiempoAcumulado += rango.tiempoConectado();
    });
    document.querySelector('#conexion').value = timeConversion(tiempoAcumulado);
    document.querySelector('#totalHoras').value = (tiempoAcumulado / (1000 * 60 * 60)).toFixed(2);
    let objeto = muestraDatosTabla(5,tabla);
    objeto.btnSiguiente.addEventListener('click',()=>{
        objeto.tabla.classList.add('animacion-tabla');
        setTimeout(animacionSiguiente,500,objeto);
    });
    objeto.btnAtras.addEventListener('click',()=>{
        objeto.tabla.classList.add('animacion-tabla');
        setTimeout(animacionAtras,500,objeto);
    });
    objeto.seleccionPagina.addEventListener('change',(evt)=>{
        objeto.tabla.classList.add('animacion-tabla');
        setTimeout(animacionpaginas,500,objeto,evt);
    });
    objeto.seleccionElementos.addEventListener('change',(evt)=>{
        objeto.tabla.classList.add('animacion-tabla');
        setTimeout(animacionSelectElementos,500,objeto,evt);

    })
}

function muestraDatosTabla(element,tabla) {
    let objeto = new PaginacionTabla(tabla,element);
    objeto.inicio();
    objeto.opcionesPaginas();
    return objeto;

}

