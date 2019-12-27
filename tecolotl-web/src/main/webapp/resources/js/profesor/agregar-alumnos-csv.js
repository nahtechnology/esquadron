function Alumno(apodo, nombre, apellidopaterno, apellidomaterno, fechanacimiento, nivellenguaje, genero) {

    this.apodo = apodo;
    this.nombre = nombre;
    this.apellidopaterno = apellidopaterno;
    this.apellidomaterno = apellidomaterno;
    this.fechanacimento = fechanacimiento;
    this.nivellenguaje = nivellenguaje;
    this.genero = genero;
}

var ExpresionRegularFecha = new  RegExp("^([1-9]|(0)[1-9]|[1-2][0-9]|(3)[0-1])(\\/)([1-9]|((0)[1-9])|((1)[0-2]))(\\/)\\d{4}$","i");
var ExpresionRegularNivelLenguaje = new RegExp("^[a-c][1-2]$", "i");
var patronCSV = /(?:,|\n|^)("(?:(?:"")*[^"]*)*"|[^",\n]*|(?:\n|$))/i;
var alumnos = [];
var pibote = 0;
var indice = [];
var filasRechazadas;
var listApodos,apodos = [];


document.addEventListener('DOMContentLoaded', function (evento) {
    document.querySelector('input[type=file]').addEventListener('change', cargaArchivo);
    tablaBuena = document.querySelector('#tabla-aceptados');
    tablaMala = document.querySelector('#tabla-rechazados');
    document.querySelector('#tabla-aceptados + div > button').addEventListener('click', insertaAlumno);
    listApodos = document.querySelector('ul[style="display: none"]').querySelectorAll('li');
    listApodos.forEach(function (apodo) {
        apodos.push(apodo.innerHTML);
    });
});


Alumno.prototype.validaApodo = function(){

    return this.apodo.length >= 4 && this.apodo.length <= 50 && apodos.includes(this.apodo) === false; /* mayor igual 4 y menor igual a 50*/


};
Alumno.prototype.validaNombre = function(){
    return this.nombre.length >= 3 && this.nombre.length <= 40;
};

Alumno.prototype.validaApellidopaterno = function(){
    return this.apellidopaterno.length >= 4 && this.apellidopaterno.length <= 50;
};

Alumno.prototype.validaApellidomaterno = function(){
    return this.apellidomaterno.length >= 4 && this.apellidomaterno.length <= 50;
};

Alumno.prototype.validaFecha = function(){
    if(ExpresionRegularFecha.test(this.fechanacimento)){
        var fecha = this.fechanacimento.split('/');
        fechalocal = new Date(fecha[2],fecha[1] - 1,fecha[0]); /*construir un objeto de tipo date*/
        if (fechalocal.getDate() === parseInt(fecha[0])){
            return true;
        }
        return  false;
    }
    return false;
};

Alumno.prototype.validaNivellenguaje = function(){
    return(ExpresionRegularNivelLenguaje.test(this.nivellenguaje))
};

Alumno.prototype.validaGenero = function(){
    return this.genero.trim() === 'F' || this.genero.trim() === 'M';
};

Alumno.prototype.insertaDatos = function (tabla) {
    let entrada = document.createElement('input');
    entrada.setAttribute('type','text');
    let fila = tabla.tBodies[0].insertRow();
    let conteo = 0;
    let celdaApodo = fila.insertCell();

    let celdaNombre = fila.insertCell(-1);

    let celdaApellidoPaterno = fila.insertCell(-1);

    let celdaApellidoMaterno = fila.insertCell(-1);

    let celdaFechaNacimento = fila.insertCell(-1);

    let celdaNivelLenguaje = fila.insertCell(-1);

    let celdaGenero = fila.insertCell(-1);


    if(!this.validaApodo()){
        celdaApodo.appendChild(entrada).value = this.apodo;
        conteo += 1;
    }else {
            celdaApodo.innerHTML = this.apodo;
            if (apodos.includes(this.apodo) === false){
                apodos.push(this.apodo);
            }
    }
    if(!this.validaNombre()){
        celdaNombre.appendChild(entrada).value = this.nombre;
        conteo += 1;
    }else {
        celdaNombre.innerHTML = this.nombre;
    }
    if(!this.validaApellidomaterno()){
        celdaApellidoMaterno.appendChild(entrada).value = this.apellidomaterno;
        conteo += 1;
    }else{
        celdaApellidoMaterno.innerHTML = this.apellidomaterno;
    }
    if(!this.validaApellidopaterno()){
        celdaApellidoPaterno.appendChild(entrada).value = this.apellidopaterno;
        conteo += 1;
    }else {
        celdaApellidoPaterno.innerHTML = this.apellidopaterno;
    }
    if(!this.validaFecha()){
        celdaFechaNacimento.appendChild(entrada).value = this.fechanacimento;
        conteo += 1;
    }else{
        celdaFechaNacimento.innerHTML = this.fechanacimento;
    }
    if(!this.validaNivellenguaje()){
        celdaNivelLenguaje.appendChild(entrada).value = this.nivellenguaje;
        conteo += 1;
    }else{
        celdaNivelLenguaje.innerHTML = this.nivellenguaje;
    }
    if(!this.validaGenero()){
        celdaGenero.appendChild(entrada).value = this.genero;
        conteo += 1;
    }else {
        celdaGenero.innerHTML = this.genero;
    }

    if (conteo>0){
        let  celdaValida = fila.insertCell(-1);
        let boton = document.createElement("span");
        boton.classList.add('uk-icon-button','uk-margin-small-right');
        boton.setAttribute('uk-icon','check');
        celdaValida.appendChild(boton);
    }else{
        let  celdaValida = fila.insertCell(-1);
        let boton = document.createElement("span");
        boton.classList.add('uk-icon-button','uk-margin-small-right');
        boton.setAttribute('uk-icon','check');
        celdaValida.appendChild(boton);
    }
};
function cargaArchivo(evento) {
    var fileReader = new FileReader();
    var archivo = evento.target.files[0];
    fileReader.onload = lee;
    fileReader.onloadstart = cargaIniciada;
    fileReader.onloadend = cargaFinalizada;
    fileReader.onabort = lecturaTerminada;
    fileReader.onprogress = progreso;
    fileReader.readAsText(archivo);
}

function lee(evento) {
    var datosAlumno = [];
    var filas = evento.target.result.split('\n');
    filas.forEach(function (fila) {
        fila.split(patronCSV).forEach(function (palabra,index) {
            if (!(palabra.trim().length === 0)) {
                datosAlumno.push(palabra);
                if(datosAlumno.length % 7 === 0){
                    alumnos[pibote] = new Alumno(datosAlumno[0],datosAlumno[1],datosAlumno[2],datosAlumno[3],datosAlumno[4],datosAlumno[5],datosAlumno[6]);
                    validarDatos(alumnos[pibote]);
                    pibote += 1;
                    // console.log(datosAlumno);
                    datosAlumno = [];
                }
            }
        });
    });
}

function cargaIniciada(evento) {
    console.log('archivo inicializado');
}

function cargaFinalizada(evento) {
    console.log('archivo finalizado');
    tablaBuena.tBodies[0].deleteRow(0);
    tablaMala.tBodies[0].deleteRow(0);
    filasRechazadas = document.querySelectorAll('#tabla-rechazados tbody tr');
    botonesTabla();
    var botonFile = document.querySelector('.botones input[type=file]');
    var textLabel= botonFile.value.split("\\");
    document.querySelector('.botones label').innerHTML = textLabel[textLabel.length - 1];
}
function botonesTabla() {
    var botonesValidar;
    botonesValidar = document.querySelectorAll('#tabla-rechazados tbody > tr > td:last-child');
    console.log(botonesValidar);
    console.log(botonesValidar.length);

    botonesValidar.forEach(function (boton,index) {
        boton.addEventListener('click',function () {
            validarFila(index);
        });
    });
}


function lecturaTerminada(evento) {
    console.log('carga de archivo cancelado');
}

function progreso(evento) {
    console.log('progeso');
}

function validarDatos(objeto) {
    if (objeto.validaApodo() && objeto.validaNombre() && objeto.validaApellidopaterno() && objeto.validaApellidomaterno() && objeto.validaFecha() && objeto.validaNivellenguaje() && objeto.validaGenero() )
    {
        objeto.insertaDatos(tablaBuena);
    }else {
        objeto.insertaDatos(tablaMala);
    }
}

function validarFila(num) {
    let alumno = [];
    let usuario;
  filasRechazadas[num].querySelectorAll("td").forEach(function (celda,indice) {
      if(celda.querySelector('input[type=text]')){
          alumno.push(celda.querySelector('input[type=text]').value);
      }else{
          alumno.push(celda.innerHTML.trim());
      }
  });
  usuario = new Alumno(alumno[0],alumno[1],alumno[2],alumno[3],alumno[4],alumno[5],alumno[6]);

    if (usuario.validaApodo() && usuario.validaNombre() && usuario.validaApellidopaterno() && usuario.validaApellidomaterno() && usuario.validaFecha() && usuario.validaNivellenguaje() && usuario.validaGenero() ) {
        usuario.insertaDatos(tablaBuena);
        // tablaMala.tBodies[0].deleteRow(num);
        filasRechazadas[num].style.display = 'none';

    } else {
        console.log('no aceptada');
    }

}

function cargaVista() {
   var  salida;
    var contrasenias = [];
    while (contrasenias.length < 2) {
        contrasenias.push(new Contrasenia());
        contrasenias = removeDups(contrasenias);
    }
    salida = contrasenias.sort().join(',');
    return salida;
}

function Contrasenia() {
    this.x = Math.floor(Math.random() * 7);
    this.y = Math.floor(Math.random() * 7);
}

Contrasenia.prototype.equals = function (contrasenia) {
    return this.x === contrasenia.x && this.y === contrasenia.y;
};

Contrasenia.prototype.toString = function () {
    return this.x.toString().concat(':').concat(this.y.toString());
};

function removeDups(names) {
    let unique = {};
    names.forEach(function(i) {
        if(!unique[i]) {
            unique[i] = true;
        }
    });
    return Object.keys(unique);
}


function insertaAlumno(evento) {
    let apodo = document.getElementById('formulario-alumno:apodo');
    let nombre = document.getElementById('formulario-alumno:nombre');
    let apelldioPaterno = document.getElementById('formulario-alumno:apellido-paterno');
    let apelldioMaterno = document.getElementById('formulario-alumno:apellido-materno');
    let fechaNacimiento = document.getElementById('formulario-alumno:fecha-nacimiento');
    let sexo = document.getElementById('formulario-alumno:sexo');
    let nivelLenguaje = document.getElementById('formulario-alumno:nivel-lenguaje');
    let contrasena = document.getElementById('formulario-alumno:contrasenia');
    let botonEnviar = document.getElementById('formulario-alumno:enviar');
    for (let indice = 0; indice < tablaBuena.tBodies[0].rows.length; indice++) {
        let celdas = tablaBuena.tBodies[0].rows[indice].querySelectorAll('td');
        apodo.value = celdas[0].textContent;
        nombre.value = celdas[1].textContent;
        apelldioPaterno.value = celdas[2].textContent;
        apelldioMaterno.value = celdas[3].textContent;
        fechaNacimiento.value = celdas[4].textContent;
        sexo.value = celdas[5].textContent.trim();
        nivelLenguaje.value = celdas[6].textContent;
        contrasena.value = cargaVista();
        botonEnviar.click();
    }
    document.getElementById('formulario-alumno:insertar').click();
}