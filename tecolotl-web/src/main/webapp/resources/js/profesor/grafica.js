var  nivel = ['A1','A2','B1','B2','C1','C2'];

document.addEventListener('DOMContentLoaded', function (evento) {
    var botonReporte = document.querySelector('#boton-reporte');
    var canvasGrafic = document.querySelector('#canvas');
   botonReporte.addEventListener('click',reporteCuantitativo);
    if (!canvasGrafic.hasChildNodes()){
        console.log('entro');
        botonReporte.disabled = true;
        botonReporte.classList.add('boton-disabilitado');
    }else {
        botonReporte.disabled = false;
        botonReporte.classList.remove('boton-disabilitado');
    }
});
function datosGrupo() {
    console.log("invocada");
    var alumno = [],idalumno = [] , grupos = [],nivel2=[],clases = [] ;
    var tabla = document.querySelectorAll('table[class*=tabla-profesor-dropdown] tbody tr');
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
       var letras = new GraficaAlumno(algo[0].innerText,algo[1].innerText,algo[2].innerText,algo[3].innerText,algo[4].innerText);
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
                var persona = new DatosGrafica(grafica.idAlumo,grafica.nombreCompleto,grafica.totalTareas,grafica.nivelLenjuaje,grafica.idGrupo);
                graficas.push(persona);
       });
       crearGrafica(graficas,clases);
   }) ;


   // console.log(clases);
    if (!canvasGrafica.hasChildNodes()){
        console.log('entro');
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
    console.log("crear grafica invocada");
    var canvasGrafica = document.querySelector('#canvas');
    var idPersona = [];
    var grupo = document.createElement('div');
    // console.log(datosGrupo);

    datosGrupo.forEach(function (grupo) {
        if(!idPersona.includes(grupo.personaId)){
            idPersona.push(grupo.personaId);
        }
    });
    // console.log(datosGrupo[0].personaGrupo);
    claseGrupo.forEach(function (clg) {
        if (clg.idGrup === datosGrupo[0].personaGrupo){
            titleGrupo.innerHTML ='Grupo: ' + clg.grado + ' ' +clg.grupo;
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
        var alumnoGrupo = document.createElement('div');
        var nombreAlumno = document.createElement('p');
        var porcentajeAlumno = document.createElement('span');
        var estiloNombreAlumno = document.createElement('span');
        var sujeto = datosGrupo.filter(escolar => escolar.personaId.localeCompare(idPersona[indice]) === 0);
        // console.log(sujeto);
        estiloNombreAlumno.innerHTML = sujeto[0].personaNombre;
        nombreAlumno.appendChild(estiloNombreAlumno);
        alumnoGrupo.appendChild(nombreAlumno);

        nivel.forEach(function (lenguajeNivel) {
            var barraNivel = document.createElement('progress');
            // console.log(sujeto.filter(level => level.personaNivel.localeCompare(lenguajeNivel) === 0).reduce(function (acumulador,valorActual) { return acumulador + valorActual.tareasNivel},0));
            var cantidadTarea = sujeto.filter(level => level.personaNivel.localeCompare(lenguajeNivel) === 0).reduce(function (acumulador,valorActual) { return acumulador + valorActual.tareasNivel},0);
            barraNivel.setAttribute('max','18');
            barraNivel.setAttribute('value',cantidadTarea);
            alumnoGrupo.appendChild(barraNivel);
            if (cantidadTarea > 18){
                cantidadTarea = 18;
            }
            porcentajeTotal += cantidadTarea;
        });
        porcentajeTotal =  Math.round((porcentajeTotal * 100)/(18*6));
        porcentajeAlumno.innerHTML = 'porcentaje: '+ porcentajeTotal + '%';
        nombreAlumno.appendChild(porcentajeAlumno);
        grupo.appendChild(alumnoGrupo);
        canvasGrafica.appendChild(grupo);
    }
}


function GraficaAlumno(idGrupo,idAlumno,nombreCompleto,totalTareas,nivelLenguaje) {
    this.idGrupo = idGrupo;
    this.idAlumo = idAlumno;
    this.nombreCompleto = nombreCompleto;
    this.totalTareas = totalTareas;
    this.nivelLenjuaje = nivelLenguaje;
}

function DatosGrafica(personaId,personaNombre,tareasNivel,personaNivel,personaGrupo) {
    this.personaId = personaId;
    this.personaNombre = personaNombre;
    this.tareasNivel = parseInt(tareasNivel);
    this.personaNivel = personaNivel;
    this.personaGrupo = personaGrupo;
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
        console.log(resporteGrafica);
        csv = 'Nombre'+ "," + nivel.join().concat('\n');
        resporteGrafica.forEach(function (reporte,index) {
              csv += reporte.nombreAlumno + "," + reporte.levelA1 + "," + reporte.levelA2 + "," + reporte.levelB1 + "," +
                reporte.levelB2 + "," + reporte.levelC1 + "," + reporte.levelC2 + ('\n');
        });

    });
    var hiddenElemento = document.createElement('a');
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
