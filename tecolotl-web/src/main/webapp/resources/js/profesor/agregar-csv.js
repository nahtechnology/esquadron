var patronCSV = /(?:,|\n|^)("(?:(?:"")*[^"]*)*"|[^",\n]*|(?:\n|$))/i;
var patronFecha = /(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d/i;
var alumnos;
var tablas;

document.addEventListener('DOMContentLoaded', function (evento) {
    document.querySelector('input[type=file]').addEventListener('change', cargaArchivo);
    tablas = document.querySelectorAll('table');
});

function cargaArchivo(evento) {
    var fileReader = new FileReader();
    fileReader.onload = lee;
    fileReader.readAsText(evento.target.files[0]);
}

function lee(evento) {
    var filas = evento.target.result.split('\n');
    filas.forEach(fila => alumnos.push(new Alumno(fila)));
    fi
}

function Alumno(cadena) {
    palabras = cadena.split(patronCSV);
    this.apodo = palabras[0];
    this.nombre = palabras[1];
    this.apellidoPaterno = palabras[2];
    this.apellidoMaterno = palabras[3];
    this.fechaNacimiento = palabras[4];
    this.nivelLenguaje = palabras[5];
    this.genero = palabras[6];
}


Alumno.prototype.valido = function () {
    var validado = true;
    validado &= this.apodo.length >= 5 && this.apodo.length <= 50;
    validado &= this.nombre.length >= 3 && this.apodo.length <= 40;
    validado &= this.apellidoPaterno.length >= 4 && this.apellidoPaterno.length <= 50;
    validado &= this.apellidoMaterno.length >= 4 && this.apellidoMaterno.length <= 50;
    validado &= patronFecha.test(this.fechaNacimiento);
    validado &= this.nivelLenguaje.length === 2;
    return validado;
};

Alumno.prototype.generaComoFila = function (tabla) {
    var fila = tabla.insertRow();
    var celdaApodo = fila.insertCell(0);
    celdaApodo.textContent = this.apodo;
    var celdaNombre = fila.insertCell(1);
    celdaNombre.textContent = this.nombre;
    var celdaApellidoPaterno = fila.insertCell(2);
    celdaApellidoPaterno.textContent = this.apellidoPaterno;
    var celdaApellidoMaterno = fila.insertCell(3);
    celdaApellidoMaterno.textContent = this.apellidoMaterno;
    var celdaFechaNacimiento = fila.insertCell(4);
    celdaFechaNacimiento.textContent = this.fechaNacimiento;
    var celdaNicelLenguaje =  fila.insertCell(5);
    celdaNicelLenguaje.textContent = this.nivelLenguaje;
};