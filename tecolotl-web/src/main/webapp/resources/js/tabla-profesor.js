document.addEventListener("DOMContentLoaded", function (ev) {
    const pagina = 5;
    var tablaMaestra = document.querySelector('.tabla');
    var botones = tablaMaestra.querySelectorAll('#botones-navegacion button');
    botones[0].disabled = true;
    botones[0].classList.add('uk-button-default');
    var tabla = new Tabla(tablaMaestra.querySelector('.tabla-paginada'), pagina);
    var etiqueta = tablaMaestra.querySelector('#botones-navegacion > div');
    tabla.paginacion(0);
    tablaMaestra.querySelector('input[type=text]').addEventListener('input',function (evento) {
        tabla.busca(this.value);
        if(this.value === ""){
            tabla.paginacion(0);
        }
    });
   if(tabla.filas.length <= pagina){
       botones[1].disabled = true;
       botones[1].classList.add('uk-button-default');
   } else{
       botones[1].classList.add('uk-button-secondary');
   }
    for (var indice = 0; indice < Math.ceil(tabla.filas.length / tabla.pagina); indice++) {
        var numeral = document.createElement('span');
        numeral.textContent = indice + 1;
        etiqueta.appendChild(numeral);
    }
    botones[1].addEventListener('click', function (evento) {
        tabla.paginacion(1);
        if (tabla.actual * tabla.pagina + tabla.pagina > tabla.filas.length) {
            botones[1].disabled = true;
            botones[1].classList.add('uk-button-default');
            botones[1].classList.remove('uk-button-secondary');
        }else {
            botones[1].classList.remove('uk-button-default');
            botones[1].classList.add('uk-button-secondary');
        }
        botones[0].disabled = false;
        botones[0].classList.remove('uk-button-default');
        botones[0].classList.add('uk-button-secondary');

    });

    botones[0].addEventListener('click', function (evento) {
        tabla.paginacion(-1);
        if (tabla.actual === 0) {
            botones[0].disabled = true;
            botones[0].classList.add('uk-button-default');
            botones[0].classList.remove('uk-button-secondary');
        }else{
            botones[0].classList.remove('uk-button-default');
            botones[0].classList.add('uk-button-secondary');
        }
        botones[1].disabled = false;
        botones[1].classList.remove('uk-button-default');
        botones[1].classList.add('uk-button-secondary');
    });

});

function Tabla(tabla, pagina) {
    this.tabla = tabla;
    this.filas = this.tabla.querySelectorAll('tbody tr');
    this.pagina = pagina;
    this.actual = 0;
}

Tabla.prototype.busca = function(palabra) {

    for (var indice = 0; indice < this.filas.length; indice++){
        var columnas = this.filas[indice].querySelectorAll('td');
        var coincide = false;
        columnas.forEach(columna => {
            if (columna) {
                var textoEvaluar = columna.textContent || columna.innerText;
                coincide |= textoEvaluar.indexOf(palabra) > -1;
            }
        });
        this.filas[indice].style.display = coincide ? 'table-row' : 'none';
        if (coincide) {
            this.filas[indice].dataset.filtrado = '';
        }
    }
};

Tabla.prototype.paginacion = function (siguiente) {
    this.actual += siguiente;
    var posicion = this.actual * this.pagina;
    var indice;
    for (indice = 0; indice < posicion; indice++) {
        this.filas[indice].style.display = 'none';
    }
    for (indice = posicion; indice < (this.filas.length < posicion + this.pagina ? this.filas.length : posicion + this.pagina); indice++) {
        this.filas[indice].style.display = "table-row";
    }
    for (indice = posicion + this.pagina; indice < this.filas.length; indice++) {
        this.filas[indice].style.display = "none";
    }
};