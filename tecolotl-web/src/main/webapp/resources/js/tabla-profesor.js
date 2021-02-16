document.addEventListener("DOMContentLoaded", function (ev) {

    iniciarCanvasPaginado();
    colocarImagenesPassword();

});
function inicializacionPaginacion(tabla,botones,pagina) {
    botones[0].disabled = true;
    botones[0].classList.add('uk-button-default');
    tabla.paginacion(0);
    if(tabla.filas.length <= pagina){
        botones[1].disabled = true;
        botones[1].classList.add('uk-button-default');
    } else{
        botones[1].disabled = false;
        botones[1].classList.add('uk-button-secondary');
    }
}

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

function colocarImagenesPassword() {
    console.log('entro colocar imagenes');
    let contenedorpassword = document.querySelectorAll('.tablero-licencia #imagen-password-tablero');
    let tablaProfesor = document.querySelectorAll('[id$=table-prfesor] #login-tabla-profesor');

    for (let i = 0; i < tablaProfesor.length; i++) {
        let contrasena1 = contenedorpassword[i].querySelector('span').innerText.split(',');
        let contrasena2 = tablaProfesor[i].querySelector('span').innerText.split(',');
        contenedorpassword[i].innerHTML = "";
        tablaProfesor[i].innerHTML = "";
        contrasena1.forEach(contra1 => {
            let img = document.createElement('img');
            img.src = `../resources/img/iconos-login/${parseInt(contra1) + 1}.svg`;
            img.style.width = "40px";
            img.style.marginRight = "5px";
            contenedorpassword[i].appendChild(img);
        });
        contrasena2.forEach(contra2 => {
            let img = document.createElement('img');
            img.src = `../resources/img/iconos-login/${parseInt(contra2) + 1}.svg`;
            img.style.width = "40px";
            img.style.marginRight = "5px";
            tablaProfesor[i].appendChild(img);
        });
    }
}

function generatePdf() {
    let element = document.querySelector('#modal-password .uk-modal-body');
    let opt = {
        margin:       1,
        filename:     'Passwords-Teachers.pdf',
        image:        { type: 'jpeg', quality: 0.98 },
        html2canvas:  { scale: 2 },
        jsPDF:        {  format: 'legal', orientation: 'landscape' },
        pagebreak: {mode:'avoid-all'}
    };
    html2pdf().set(opt).from(element).save();
}
function iniciarCanvasPaginado() {
    let pagina = 5;
    let tablaMaestra = document.querySelector('.tabla');
    let botones = tablaMaestra.querySelectorAll('#botones-navegacion button');

    let tabla = new Tabla(tablaMaestra.querySelector('.tabla-paginada'), pagina);
    // var etiqueta = tablaMaestra.querySelector('#botones-navegacion > div');   <------para mostrar la cantidad de paginas
    inicializacionPaginacion(tabla,botones,pagina);

    let paginacion = tablaMaestra.querySelector('#botones-navegacion select');
    paginacion.addEventListener('change',function (evento) {
        pagina = parseInt(evento.target.value);
        tabla.pagina = pagina;
        tabla.actual = 0;
        inicializacionPaginacion(tabla,botones,pagina);
    });

    botones[1].addEventListener('click', function (evento) {
        tabla.paginacion(1);
        if (tabla.actual * tabla.pagina + tabla.pagina >= tabla.filas.length) {
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
}