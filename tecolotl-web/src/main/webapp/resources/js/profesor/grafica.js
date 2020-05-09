var  nivel = ['A1','A2','B1','B2','C1','C2'];
var cantidadGraficas = 2;
var botonAtras = document.querySelector('#canvas + div > button:first-child');
var botonSiguiente = document.querySelector('#canvas + div > button:last-child');
var limite = 0;

document.addEventListener('DOMContentLoaded', function (evento) {
    // var botonReporte = document.querySelector('#boton-reporte');

    var canvasGrafic = document.querySelector('#canvas');
   // botonReporte.addEventListener('click',reporteCuantitativo);
   datosGrupo();
    if (!canvasGrafic.hasChildNodes()){
        console.log('entro');
       // botonReporte.disabled = true;
       // botonReporte.classList.add('boton-disabilitado');
        botonSiguiente.disabled = true;
        botonSiguiente.classList.add('boton-disabilitado');
        botonAtras.disabled = true;
        botonAtras.classList.add('boton-disabilitado');

    }else {
       // botonReporte.disabled = false;
       // botonReporte.classList.remove('boton-disabilitado');
        botonSiguiente.disabled = false;
        botonSiguiente.classList.remove('boton-disabilitado');
        botonAtras.disabled = false;
        botonAtras.classList.remove('boton-disabilitado');
    }
    visualizar();
    botonAtras.addEventListener('click',function () {paginacionGraficas(1) });
    botonSiguiente.addEventListener('click',function () {paginacionGraficas(2)});
});
function datosGrupo() {
    console.log("invocada");
    var alumno = [],idalumno = [] , grupos = [],nivel2=[],clases = [],nivelAlumnoIngles=[] ;
    var tabla = document.querySelectorAll('table[id*=tabla-grupo] tbody tr');
    var datosAlumnos = document.querySelector('#graficas ');
    var listaDatos = datosAlumnos.querySelectorAll('div');
    var canvasGrafica = document.querySelector('#canvas');
    while (datosAlumnos.hasChildNodes()){
        datosAlumnos.removeChild(datosAlumnos.firstChild);
    }
    while (canvasGrafica.hasChildNodes()){
        canvasGrafica.removeChild(canvasGrafica.firstChild);
    }
    var botonReport = document.querySelector('#boton-reporte');



    listaDatos.forEach(function (datos) {
       var algo = datos.querySelectorAll("span");
       var letras = new GraficaAlumno(algo[0].innerText,algo[1].innerText,algo[2].innerText,algo[3].innerText,algo[4].innerText,algo[5].innerText,algo[6].innerText);
       alumno.push(letras);
    });

    alumno.forEach(function (persona) {
        if (!idalumno.includes(persona.idAlumo)){
            idalumno.push(persona.idAlumo);
        }
        if (!nivel2.includes(persona.nivelLenjuaje)){
            nivel2.push(persona.nivelLenjuaje);
        }
        if (!grupos.includes(persona.idGrupo)){
            grupos.push(persona.idGrupo);
        }

    });
    tabla.forEach(function (table) {
        var grado = table.querySelector('td:first-child').innerText;
        var grupo = table.querySelector('td:nth-child(2)').innerText;
        var id = table.querySelector('td:last-child ul li:first-child a').search;
        var ident = id.split('=');
        var classroom = new DatosGrupo(grado,grupo,ident[1]);
        clases.push(classroom);
    });
    var grup;
   grupos.forEach(function (value, index) {
       var graficas = [];
       alumno.filter(person => person.idGrupo.localeCompare(grupos[index]) === 0).forEach(function (grafica) {
                var persona = new DatosGrafica(grafica.idAlumo,grafica.nombreCompleto,grafica.totalTareas,grafica.nivelLenjuaje,grafica.idGrupo,grafica.nivelLenguajeAlumno,grafica.puntajeActividad);
                graficas.push(persona);
       });
       crearGrafica(graficas,clases);
   });


   // console.log(clases);
    if (!canvasGrafica.hasChildNodes()){
        // console.log('entro');
        botonReport.disabled = true;
        botonReport.classList.add('boton-disabilitado');
    }else{
        botonReport.disabled = false;
        botonReport.classList.remove('boton-disabilitado');
    }
}

function crearGrafica(datosGrupo,claseGrupo) {
    var titleGrupo = document.createElement('h6');
    var level1 = document.createElement('span');
    var level2 = document.createElement('span');
    var level3 = document.createElement('span');
    var level4 = document.createElement('span');
    var level5 = document.createElement('span');
    var level6 = document.createElement('span');
    // console.log("crear grafica invocada");
    var canvasGrafica = document.querySelector('#canvas');
    var idPersona = [];
    var levelGrafic;
    var grupo = document.createElement('div');
    var space = document.createTextNode("\u00A0");
    // console.log(datosGrupo);
    grupo.classList.add('scroll-profesor');
    datosGrupo.forEach(function (grupo) {
        if(!idPersona.includes(grupo.personaId)){
            idPersona.push(grupo.personaId);
        }
    });
    // console.log(datosGrupo[0].personaGrupo);
    claseGrupo.forEach(function (clg) {
        if (clg.idGrup === datosGrupo[0].personaGrupo){
            titleGrupo.innerHTML ='Group: ' + clg.grado + ' ' +clg.grupo;
        }
    });
    level1.innerHTML='A1';
    level2.innerHTML='A2';
    level3.innerHTML='B1';
    level4.innerHTML='B2';
    level5.innerHTML='C1';
    level6.innerHTML='C2';
    grupo.appendChild(titleGrupo);
    grupo.appendChild(level1);
    grupo.appendChild(level2);
    grupo.appendChild(level3);
    grupo.appendChild(level4);
    grupo.appendChild(level5);
    grupo.appendChild(level6);
    // console.log(idPersona);
    for (var indice = 0 ; indice < idPersona.length ; indice++) {
        var porcentajeTotal = 0;
        var porcentajeNivel  = 0;
        var promedioSubirNivel;
        var alumnoGrupo = document.createElement('div');
        var nombreAlumno = document.createElement('p');
        var porcentajeAlumno = document.createElement('span');
        var estiloNombreAlumno = document.createElement('span');
        var contenedorBotones = document.createElement('span');
        var sujeto = datosGrupo.filter(escolar => escolar.personaId.localeCompare(idPersona[indice]) === 0);
        // var nivelAlumno = (sujeto[0].personaNivel !== "" ? sujeto[0].personaNivel : 'N/A');
        var nivelAlumno;
        var numeroActividades = [];
        var botonSubirNivel = document.createElement('button');
        var botonBajarNivel = document.createElement('button');
       // botonSubirNivel.setAttribute('uk-icon','icon : upload; ratio : 1.2');
       // botonBajarNivel.setAttribute('uk-icon','icon : download; ratio : 1.2');
       botonBajarNivel.setAttribute('uk-tooltip','Place at level');
       botonSubirNivel.setAttribute('uk-tooltip','Place at level');
        botonSubirNivel.dataset.idAlumno = sujeto[0].personaId;
        botonBajarNivel.dataset.idAlumno = sujeto[0].personaId;
        botonSubirNivel.classList.add('boton-nivel-base','boton-subir-nivel');
        botonBajarNivel.classList.add('boton-nivel-base','boton-bajar-nivel');
        botonSubirNivel.style.marginLeft = "15px";
        contenedorBotones.appendChild(botonBajarNivel);
        contenedorBotones.appendChild(botonSubirNivel);
        nombreAlumno.style.display = "flex";
        contenedorBotones.style.marginLeft="auto";
        promedioSubirNivel = sujeto.filter(level => level.personaNivel.localeCompare(nivelAlumno) === 0);
        botonBajarNivel.addEventListener("click",abrirBajarNivel );
        switch(parseInt(sujeto[0].personaNivelIngles)) {
            case 1 :{
                nivelAlumno = 'A1';
                numeroActividades[0] = promedioSubirNivel.length === 0 ? 0 : parseInt(promedioSubirNivel[0].tareasNivel);
                if (numeroActividades[0] >= 18){
                    botonSubirNivel.addEventListener('click',incrementaNivel);
                }else {
                    botonSubirNivel.addEventListener('click',abrirSubirNivel);
                }
                numeroActividades[1] = 0;
                numeroActividades[2] = 0;
                numeroActividades[3] = 0;
                numeroActividades[4] = 0;
                numeroActividades[5] = 0;
                porcentajeNivel = Math.round((numeroActividades[0]*100)/18);
                botonBajarNivel.disabled = true;
                botonBajarNivel.classList.add('desactivado-nivel');
            }
            break;
            case 2 :{
                nivelAlumno = 'A2';
                numeroActividades[0] = 18;
                numeroActividades[1] = promedioSubirNivel.length === 0 ? 0 : parseInt(promedioSubirNivel[0].tareasNivel);
                if (numeroActividades[1] >= 18){
                    botonSubirNivel.addEventListener('click',incrementaNivel);
                }else {
                    botonSubirNivel.addEventListener('click',abrirSubirNivel);
                }
                numeroActividades[2] = 0;
                numeroActividades[3] = 0;
                numeroActividades[4] = 0;
                numeroActividades[5] = 0;
                porcentajeNivel = Math.round((numeroActividades[1]*100)/18);
            }
                break;
            case 3 :{
                nivelAlumno = 'B1';
                numeroActividades[0] = 18;
                numeroActividades[1] = 18;
                numeroActividades[2] = promedioSubirNivel.length === 0 ? 0 : parseInt(promedioSubirNivel[0].tareasNivel);
                if (numeroActividades[2] >= 18){
                    botonSubirNivel.addEventListener('click',incrementaNivel);
                }else {
                    botonSubirNivel.addEventListener('click',abrirSubirNivel);
                }
                numeroActividades[3] = 0;
                numeroActividades[4] = 0;
                numeroActividades[5] = 0;
                porcentajeNivel = Math.round((numeroActividades[2]*100)/18);
            }
                break;
            case 4 :{
                nivelAlumno = 'B2';
                numeroActividades[0] = 18;
                numeroActividades[1] = 18;
                numeroActividades[2] = 18;
                numeroActividades[3] = promedioSubirNivel.length === 0 ? 0 : parseInt(promedioSubirNivel[0].tareasNivel);
                if (numeroActividades[3] >= 18){
                    botonSubirNivel.addEventListener('click',incrementaNivel);
                }else {
                    botonSubirNivel.addEventListener('click',abrirSubirNivel);
                }
                numeroActividades[4] = 0;
                numeroActividades[5] = 0;
                porcentajeNivel = Math.round((numeroActividades[3]*100)/18);
            }
                break;
            case 5 :{
                nivelAlumno = 'C1';
                numeroActividades[0] = 18;
                numeroActividades[1] = 18;
                numeroActividades[2] = 18;
                numeroActividades[3] = 18;
                numeroActividades[4] = promedioSubirNivel.length === 0 ? 0 : parseInt(promedioSubirNivel[0].tareasNivel);
                if (numeroActividades[4] >= 18){
                    botonSubirNivel.addEventListener('click',incrementaNivel);
                }else {
                    botonSubirNivel.addEventListener('click',abrirSubirNivel);
                }
                numeroActividades[5] = 0;
                porcentajeNivel = Math.round((numeroActividades[4]*100)/18);
            }
                break;
            case 6 :{
                nivelAlumno = 'C2';
                numeroActividades[0] = 18;
                numeroActividades[1] = 18;
                numeroActividades[2] = 18;
                numeroActividades[3] = 18;
                numeroActividades[4] = 18;
                numeroActividades[5] = promedioSubirNivel.length === 0 ? 0 : parseInt(promedioSubirNivel[0].tareasNivel);
                if (numeroActividades[5] >= 18){
                    botonSubirNivel.addEventListener('click',incrementaNivel);
                }else {
                    botonSubirNivel.addEventListener('click',abrirSubirNivel);
                }
                porcentajeNivel = Math.round((numeroActividades[5]*100)/18);
                botonSubirNivel.disabled = true;
                botonSubirNivel.classList.add('desactivado-nivel');
            }
                break;
        }


        // console.log(sujeto);
        // console.log(nivelesAlumno);
        estiloNombreAlumno.innerHTML = sujeto[0].personaNombre;
        nombreAlumno.appendChild(estiloNombreAlumno);
        alumnoGrupo.appendChild(nombreAlumno);

        nivel.forEach(function (lenguajeNivel,index) {
            var barraNivel = document.createElement('progress');
            // console.log(sujeto.filter(level => level.personaNivel.localeCompare(lenguajeNivel) === 0).reduce(function (acumulador,valorActual) { return acumulador + valorActual.tareasNivel},0));
            // var cantidadTarea = sujeto.filter(level => level.personaNivel.localeCompare(lenguajeNivel) === 0).reduce(function (acumulador,valorActual) { return acumulador + valorActual.tareasNivel},0);
            // var nivelPersona = sujeto.filter(level => level.personaNivel.localeCompare(lenguajeNivel) === 0);

            barraNivel.setAttribute('max','18');
            barraNivel.setAttribute('value',numeroActividades[index]);
            alumnoGrupo.appendChild(barraNivel);
            if (numeroActividades[index] > 18){
                numeroActividades[index] = 18;
            }
            porcentajeTotal += numeroActividades[index];
        });

        porcentajeTotal =  Math.round((porcentajeTotal * 100)/(18*6));
        porcentajeAlumno.innerText = nivelAlumno + ":"+ " \u00A0 \u00A0"  + porcentajeNivel + '%' + " \u00A0 \u00A0" + "Total:" + "\u00A0 \u00A0" + porcentajeTotal + '%';
        nombreAlumno.appendChild(porcentajeAlumno);
        nombreAlumno.appendChild(contenedorBotones);
        grupo.appendChild(alumnoGrupo);
        canvasGrafica.appendChild(grupo);
    }
}


function GraficaAlumno(idGrupo,idAlumno,nombreCompleto,totalTareas,nivelLenguaje,nivelLenguajeAlumno,puntajeActividad) {
    this.idGrupo = idGrupo;
    this.idAlumo = idAlumno;
    this.nombreCompleto = nombreCompleto;
    this.totalTareas = totalTareas;
    this.nivelLenjuaje = nivelLenguaje;
    this.nivelLenguajeAlumno = nivelLenguajeAlumno;
    this.puntajeActividad = puntajeActividad;
}

function DatosGrafica(personaId,personaNombre,tareasNivel,personaNivel,personaGrupo,personaNivelIngles,personaPuntaje) {
    this.personaId = personaId;
    this.personaNombre = personaNombre;
    this.tareasNivel = parseInt(tareasNivel);
    this.personaNivel = personaNivel;
    this.personaGrupo = personaGrupo;
    this.personaNivelIngles = personaNivelIngles;
    this.personaPuntaje = parseInt(personaPuntaje);
}

function DatosGrupo(grado,grupo,idGrup){
    this.grado = grado;
    this.grupo = grupo;
    this.idGrup = idGrup;
}

function reporteCuantitativo(evento) {
    var resporteGrafica = [];
    // var csv;
    var grafica = document.querySelectorAll('#canvas > div');
    grafica.forEach(function (grupo) {
        var alumno = grupo.querySelectorAll('div');
        alumno.forEach(function (datos) {
            var datoNombre = datos.querySelector('p > span:first-child').textContent;
            var datoProgreso = datos.querySelectorAll('progress');
            var totalDatos = new DatosReporte(datoNombre,datoProgreso[0].value,datoProgreso[1].value,datoProgreso[2].value,datoProgreso[3].value,datoProgreso[4].value,datoProgreso[5].value,8);
            resporteGrafica.push(totalDatos);
        });
        // console.log(resporteGrafica);
        csv = 'Nombre'+ "," + nivel.join().concat('\n');
        resporteGrafica.forEach(function (reporte,index) {
              csv += reporte.nombreAlumno + "," + reporte.levelA1 + "," + reporte.levelA2 + "," + reporte.levelB1 + "," +
                reporte.levelB2 + "," + reporte.levelC1 + "," + reporte.levelC2 + ('\n');
        });

    });
    // var hiddenElemento = document.createElement('a');
    var hiddenElemento = document.querySelector('#reporte');
    hiddenElemento.href = 'data:text/csv;charset=utf-8,' + encodeURI(csv);
    hiddenElemento.target = '_blank';
    hiddenElemento.download = 'quantitativeReport'  + '.csv';
    hiddenElemento.click();
}

function DatosReporte(nombreAlumno,levelA1,levelA2,levelB1,levelB2,levelC1,levelC2,calificacion) {
    this.nombreAlumno = nombreAlumno;
    this.levelA1 = levelA1;
    this.levelA2 = levelA2;
    this.levelB1 = levelB1;
    this.levelB2 = levelB2;
    this.levelC1 = levelC1;
    this.levelC2 = levelC2;
    this.calificacion = calificacion;
}
function visualizar() {
    var graficas = document.querySelectorAll('#canvas > div');
    var numGraficas = graficas.length;
    cantidadGraficas = 2;
    botonAtras.disabled = true;
    botonAtras.classList.add('boton-disabilitado');
    if (graficas.length > cantidadGraficas){
        for (var i = cantidadGraficas; i < numGraficas ; i++){
            graficas[i].style.display='none';
            // console.log('dato:' + i);
        }
        botonSiguiente.disabled = false;
        botonSiguiente.classList.remove('boton-disabilitado');
    }else {
        botonSiguiente.disabled = true;
        botonSiguiente.classList.add('boton-disabilitado');
    }
}
// Paginacion de graficas
function paginacionGraficas(dato) {
 var graficas = document.querySelectorAll('#canvas > div');
 var numGraficas = graficas.length;



 switch (dato) {
     case 1 : {
         cantidadGraficas -= 2;
         graficas.forEach(function (grafic) {
            grafic.style.display="none";
         });
         for (var i = cantidadGraficas-2; i < cantidadGraficas ; i++){
             graficas[i].style.display='block';
         }
         break;
     }
     case 2: {

         graficas.forEach(function (grafic) {
             grafic.style.display="none";
         });

         for (var j = cantidadGraficas; j < cantidadGraficas + 2 ; j++){
             if( j < numGraficas){
                 graficas[j].style.display='block';
             }
         }
         cantidadGraficas += 2;
         break;
     }
 }

    if (cantidadGraficas > 2){
        botonAtras.disabled = false;
        botonAtras.classList.remove('boton-disabilitado');
    }else{
        botonAtras.disabled = true;
        botonAtras.classList.add('boton-disabilitado');
    }

    if(cantidadGraficas >= numGraficas){
        botonSiguiente.disabled = true;
        botonSiguiente.classList.add('boton-disabilitado');
    }else {
        botonSiguiente.disabled = false;
        botonSiguiente.classList.remove('boton-disabilitado');
    }
 // console.log(numGraficas);
}

function incrementaNivel(evento) {
    console.log(evento);
    let formulario = document.getElementById('formulario-cambio-nivel');
    document.getElementById('formulario-cambio-nivel:entrada-alumno').value = evento.dataset.idAlumno;
    document.getElementById('formulario-cambio-nivel:entrada-nivel').value = 1;
    formulario.querySelector('input[type=submit]').click();
    UIkit.notification("se incremento el nivel correctamente");
}

function decrementaNivel(evento) {
    console.log(evento);
    let formulario = document.getElementById('formulario-cambio-nivel');
    document.getElementById('formulario-cambio-nivel:entrada-alumno').value = evento.dataset.idAlumno;
    document.getElementById('formulario-cambio-nivel:entrada-nivel').value = -1;
    formulario.querySelector('input[type=submit]').click();
    UIkit.notification("se decremento el nivel correctamente");
}

function abrirBajarNivel(evento) {
nahModal["modal-nivel-bajar"].abrirModal();
    var boton = evento.target;
    let botonNivelBajar = document.querySelector('#modal-nivel-bajar .modal-pie > button:first-child');
    let botonCerrar = document.querySelector('#modal-nivel-bajar .modal-pie > button:last-child');

    botonNivelBajar.addEventListener('click',funcionBajar);
    botonCerrar.addEventListener('click',cerrarModales);
    function funcionBajar() {
        decrementaNivel(boton);
        nahModal['modal-nivel-bajar'].cerrarModal();
        botonNivelBajar.removeEventListener('click',funcionBajar);
        botonCerrar.removeEventListener('click',cerrarModales);
    }
    function cerrarModales() {
        nahModal['modal-nivel-bajar'].cerrarModal();
        botonNivelBajar.removeEventListener('click',funcionBajar);
        botonCerrar.removeEventListener('click',cerrarModales);
    }
}


function abrirSubirNivel(evento) {
nahModal["modal-nivel-subir"].abrirModal();
    var boton = evento.target;
    let botonNivelSubir = document.querySelector('#modal-nivel-subir .modal-pie > button:first-child');
    let botonCerrar = document.querySelector('#modal-nivel-subir .modal-pie > button:last-child');

    botonNivelSubir.addEventListener('click',funcionSubir);
    botonCerrar.addEventListener('click',cerrarModales);

    function funcionSubir() {
        incrementaNivel(boton);
        nahModal['modal-nivel-subir'].cerrarModal();
        botonNivelSubir.removeEventListener('click',funcionSubir);
        botonCerrar.removeEventListener('click',cerrarModales);
    }
    function cerrarModales() {
        nahModal['modal-nivel-subir'].cerrarModal();
        botonNivelSubir.removeEventListener('click',funcionSubir);
        botonCerrar.removeEventListener('click',cerrarModales);
    }
}
