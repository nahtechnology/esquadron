let cuentaContrasena = 0;
function Alumno(apodo, nombre, apellidopaterno, apellidomaterno, fechanacimiento, nivellenguaje, genero) {

    this.apodo = apodo;
    this.nombre = nombre;
    this.apellidopaterno = apellidopaterno;
    this.apellidomaterno = apellidomaterno;
    this.fechanacimento = fechanacimiento;
    this.nivellenguaje = nivellenguaje;
    this.genero = genero;
}

var formularioNuevoAlumno = document.getElementById('formulario-modal-nuevo-profesor');

var ExpresionRegularFecha = new  RegExp("^([1-9]|(0)[1-9]|[1-2][0-9]|(3)[0-1])(\\/)([1-9]|((0)[1-9])|((1)[0-2]))(\\/)\\d{4}$","i");
var ExpresionRegularNivelLenguaje = new RegExp("^[a-c][1-2]$", "i");
var patronCSV = /(?:,|\n|^)("(?:(?:"")*[^"]*)*"|[^",\n]*|(?:\n|$))/i;
var alumnos = [];
var pibote = 0;
var indice = [];
var filasRechazadas;
var apodos = [];
var cupo,margenCupo ;


document.addEventListener('DOMContentLoaded', function (evento) {
    document.querySelector('input[type=file]').addEventListener('change', cargaArchivo);
    tablaBuena = document.querySelector('#tabla-aceptados');
    tablaMala = document.querySelector('#tabla-rechazados');
    let totalCupo = document.querySelector('#cupo-alumnos > span').innerText.split('/')[0];
    // document.querySelector('#tabla-aceptados + div > button').addEventListener('click', insertaAlumno);
    agregarApodos();
    cupo = document.querySelector('#cupo-alumnos > span').innerText.split('/')[1];
    margenCupo = parseInt(cupo) * 0.10;
    if(parseInt(totalCupo) >= (parseInt(cupo) + Math.round(margenCupo)) ){
        desactivarDescarga();
    }
    if(parseInt(totalCupo) >= parseInt(cupo)){
        let mensaje = document.querySelector('#cupo-alumnos > span');
        mensaje.style.color = "red";
    }

    // botonBorrar = document.querySelector('.botones input[type=file]');
    // botonBorrar.addEventListener('click',limpiar);
});


Alumno.prototype.validaApodo = function(){

    return this.apodo.length >= 4 && this.apodo.length <= 50 && apodos.includes(this.apodo) === false; /* mayor igual 4 y menor igual a 50*/


};
Alumno.prototype.validaNombre = function(){
    return this.nombre.length >= 3 && this.nombre.length <= 40;
};

Alumno.prototype.validaApellidopaterno = function(){
    return this.apellidopaterno.length >= 3 && this.apellidopaterno.length <= 50;
};

Alumno.prototype.validaApellidomaterno = function(){
    return this.apellidomaterno.length >= 3 && this.apellidomaterno.length <= 50;
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
        let inputApodo = document.createElement('input');
        inputApodo.setAttribute('type','text');
        inputApodo.setAttribute('uk-tooltip',apodoAlum);
        celdaApodo.appendChild(inputApodo).value = this.apodo.trim();
        conteo += 1;
    }else {
            celdaApodo.innerHTML = this.apodo.trim();
            if (apodos.includes(this.apodo.trim()) === false){
                apodos.push(this.apodo.trim());
            }
    }
    if(!this.validaNombre()){
        let inputNombre = document.createElement('input');
        inputNombre.setAttribute('type','text');
        celdaNombre.appendChild(inputNombre).value = this.nombre.trim();
        conteo += 1;
    }else {
        celdaNombre.innerHTML = this.nombre.trim();
    }
    if(!this.validaApellidomaterno()){
        let inputApellidomaterno = document.createElement('input');
        inputApellidomaterno.setAttribute('type','text');
        celdaApellidoMaterno.appendChild(inputApellidomaterno).value = this.apellidomaterno.trim();
        conteo += 1;
    }else{
        celdaApellidoMaterno.innerHTML = this.apellidomaterno.trim();
    }
    if(!this.validaApellidopaterno()){
        let inputApellidopaterno = document.createElement('input');
        inputApellidopaterno.setAttribute('type','text');
        celdaApellidoPaterno.appendChild(inputApellidopaterno).value = this.apellidopaterno.trim();
        conteo += 1;
    }else {
        celdaApellidoPaterno.innerHTML = this.apellidopaterno.trim();
    }
    if(!this.validaFecha()){
        let inputFecha = document.createElement('input');
        inputFecha.setAttribute('type','text');
        inputFecha.setAttribute('uk-tooltip',dateAlum);
        celdaFechaNacimento.appendChild(inputFecha).value = this.fechanacimento.trim();
        conteo += 1;
    }else{
        celdaFechaNacimento.innerHTML = this.fechanacimento.trim();
    }
    if(!this.validaNivellenguaje()){
        let inputLenguaje = document.createElement('input');
        inputLenguaje.setAttribute('type','text');
        inputLenguaje.setAttribute('uk-tooltip',levelAlum);
        celdaNivelLenguaje.appendChild(inputLenguaje).value = this.nivellenguaje.trim();
        conteo += 1;
    }else{
        celdaNivelLenguaje.innerHTML = this.nivellenguaje.trim();
    }
    if(!this.validaGenero()){
        let inputGenero = document.createElement('input');
        inputGenero.setAttribute('type','text');
        inputGenero.setAttribute('uk-tooltip',genderAlum);
        celdaGenero.appendChild(inputGenero).value = this.genero.trim();
        conteo += 1;
    }else {
        celdaGenero.innerHTML = this.genero.trim();
    }
    let  celdaValida = fila.insertCell(-1);
    let boton = document.createElement("img");
    boton.width="24";
    boton.src="../resources/img/upload.png";
    // boton.classList.add('uk-icon-button','uk-margin-small-right');
    // boton.setAttribute('uk-icon','cloud-upload');
    if (conteo === 0){
        boton.addEventListener('click', insertaAlumno);
    }

    celdaValida.appendChild(boton);
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
    let apodito = 0;
  filasRechazadas[num].querySelectorAll("td").forEach(function (celda,indice) {
      if(celda.querySelector('input[type=text]')){
          alumno.push(celda.querySelector('input[type=text]').value);
          if(indice === 0){
              apodito = 1;
          }
      }else{
          alumno.push(celda.innerHTML.trim());
      }
  });
  usuario = new Alumno(alumno[0],alumno[1],alumno[2],alumno[3],alumno[4],alumno[5],alumno[6]);
console.log(usuario);
    switch (apodito) {
        case 0: {
            apodos.splice( apodos.indexOf(alumno[0]), 1 );
            if (usuario.validaNombre() && usuario.validaApellidopaterno() && usuario.validaApellidomaterno() && usuario.validaFecha() && usuario.validaNivellenguaje() && usuario.validaGenero() ) {
                usuario.insertaDatos(tablaBuena);
                // tablaMala.tBodies[0].deleteRow(num);
                filasRechazadas[num].style.display = 'none';
            } else {
                console.log('no aceptada');
            }

        } break;
        case 1: {
            if (usuario.validaApodo() && usuario.validaNombre() && usuario.validaApellidopaterno() && usuario.validaApellidomaterno() && usuario.validaFecha() && usuario.validaNivellenguaje() && usuario.validaGenero() ) {
                usuario.insertaDatos(tablaBuena);
                // tablaMala.tBodies[0].deleteRow(num);
                filasRechazadas[num].style.display = 'none';
            } else {
                console.log('no aceptada');
            }
        }break;
    }


}

function cargaVista() {
   var  salida , aux1,aux2;
    var contrasenias = [];
    while (contrasenias.length < 2) {
        var numeroAlteatorio = Math.floor(Math.random() * 27);
        if (!contrasenias.includes(numeroAlteatorio)) {
            contrasenias.push(numeroAlteatorio);
        }
    }
    contrasenias.sort((a,b) => a - b);

    salida = contrasenias.join(',');

    return salida;
}




function insertaAlumno(evento) {
    let  totalCupo = document.querySelector('#cupo-alumnos > span').innerText.split('/')[0];

    if(parseInt(totalCupo) >= parseInt(cupo)){
        let mensaje = document.querySelector('#cupo-alumnos > span');
        mensaje.style.color = "red";
    }

    console.log(totalCupo + '' + (parseInt(cupo) + Math.round(margenCupo)));
    if (parseInt(totalCupo) <= (parseInt(cupo) + Math.round(margenCupo))){
        let celdas = evento.target.parentElement.parentElement.querySelectorAll('td');
        let apodo = document.getElementById('formulario-alumno:apodo');
        let nombre = document.getElementById('formulario-alumno:nombre');
        let apelldioPaterno = document.getElementById('formulario-alumno:apellido-paterno');
        let apelldioMaterno = document.getElementById('formulario-alumno:apellido-materno');
        let fechaNacimiento = document.getElementById('formulario-alumno:fecha-nacimiento');
        let sexo = document.getElementById('formulario-alumno:sexo');
        let nivelLenguaje = document.getElementById('formulario-alumno:nivel-lenguaje');
        let contrasena = document.getElementById('formulario-alumno:contrasenia');
        let botonEnviar = document.getElementById('formulario-alumno:enviar');
        apodo.value = celdas[0].textContent;
        nombre.value = celdas[1].textContent;
        apelldioPaterno.value = celdas[2].textContent;
        apelldioMaterno.value = celdas[3].textContent;
        fechaNacimiento.value = celdas[4].textContent;
        sexo.value = celdas[6].textContent.trim();
        switch (celdas[5].textContent) {
            case 'A1':
                nivelLenguaje.value = 1;
                break;
            case 'A2':
                nivelLenguaje.value = 2;
                break;
            case 'B1':
                nivelLenguaje.value = 3;
                break;
            case 'B2':
                nivelLenguaje.value = 4;
                break;
            case 'C1':
                nivelLenguaje.value = 5;
                break;
            case 'C2':
                nivelLenguaje.value = 6;
                break;
        }
        contrasena.value = cargaVista();
        botonEnviar.click();
        evento.target.src="../resources/img/checked.png";
        evento.target.parentElement.removeEventListener("click", insertaAlumno);
    }else {
      desactivarDescarga();
    }
}

function agregarApodos() {
    var listApodos = document.querySelector('#apodos').querySelectorAll('li');
    listApodos.forEach(function (apodo) {
        apodos.push(apodo.innerHTML);
    });
}

function limpiar() {
    while (tablaBuena.tBodies[0].hasChildNodes()){
        tablaBuena.tBodies[0].removeChild(tablaBuena.tBodies[0].firstChild);
    }
    while (tablaMala.tBodies[0].hasChildNodes()){
        tablaMala.tBodies[0].removeChild(tablaMala.tBodies[0].firstChild);
    }
    let imagVacio = document.createElement('img');
    let imagVacio2 = document.createElement('img');
    imagVacio.src="../resources/img/vacio.svg";
    imagVacio.width = 280;
    imagVacio2.src="../resources/img/vacio.svg";
    imagVacio2.width = 280;
    let filaBuena = tablaBuena.tBodies[0].insertRow();
    let filaMala = tablaMala.tBodies[0].insertRow();
    let filaImgBuena = filaBuena.insertCell();
    filaImgBuena.setAttribute('colspan','8');
    filaImgBuena.appendChild(imagVacio);
    let filaImgMala = filaMala.insertCell();
    filaImgMala.setAttribute('colspan','8');
    filaImgMala.appendChild(imagVacio2);

    document.querySelector('input[type=file]').value="";
    apodos = [];
    agregarApodos();
    document.querySelector('.botones label').innerHTML="choose your save file ...";
}

function desactivarDescarga() {
    UIkit.modal.alert('Ya no puedes agregar mas Alumnos');
    let boton = document.querySelector('.botones input[type=file]');
    document.querySelector('.botones label').innerHTML = "DENEGADO";
    boton.disabled = true;
    document.querySelector('.botones .agregar').disabled = true;
}

function cargaContrasenia() {
    var imagenes = document.querySelectorAll('#imagenes img');
    var seleccionados = [];
    for (i = 0; i < imagenes.length; i++) {
        if (imagenes[i].classList.contains('seleccionado')) {
            seleccionados.push(i);
        }
    }
    document.getElementById('formulario-modal-nuevo-profesor:input-secret-password').value = seleccionados.length === 0 ? null : seleccionados.join(',');
}

/**
 * funcion para cuando selecciones una imagen
 * @param evento evento de click
 */
function cambioImagen(evento) {
    ++cuentaContrasena;
    if(cuentaContrasena > 2 ){
       let imagenes = evento.target.parentElement.querySelectorAll('img.seleccionado');
       imagenes.forEach(img =>{
          img.classList.remove('seleccionado');
       });
       cuentaContrasena = 1;
    }
    evento.target.classList.toggle('seleccionado');

}
function resetContrasena() {
 let imagenes = document.querySelectorAll('#modal-nuevo-alumno #imagenes > img.seleccionado');
 if (imagenes.length > 0 ){
    imagenes.forEach(img => {
       img.classList.remove('seleccionado');
    });
 }
 cuentaContrasena = 0;
}