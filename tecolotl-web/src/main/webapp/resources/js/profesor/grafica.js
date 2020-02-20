var  nivel = ['A1','A2','B1','B2','C1','C2'];
var cantidadGraficas = 2;
var botonAtras = document.querySelector('#canvas + div > button:first-child');
var botonSiguiente = document.querySelector('#canvas + div > button:last-child');

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
       var letras = new GraficaAlumno(algo[0].innerText,algo[1].innerText,algo[2].innerText,algo[3].innerText,algo[4].innerText,algo[5].innerText);
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
                var persona = new DatosGrafica(grafica.idAlumo,grafica.nombreCompleto,grafica.totalTareas,grafica.nivelLenjuaje,grafica.idGrupo,grafica.nivelLenguajeAlumno);
                graficas.push(persona);
       });
       crearGrafica(graficas,clases);
   }) ;


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
        var porcentajeNivel  = 0;
        var alumnoGrupo = document.createElement('div');
        var nombreAlumno = document.createElement('p');
        var porcentajeAlumno = document.createElement('span');
        var estiloNombreAlumno = document.createElement('span');
        var sujeto = datosGrupo.filter(escolar => escolar.personaId.localeCompare(idPersona[indice]) === 0);
        // var nivelAlumno = (sujeto[0].personaNivel !== "" ? sujeto[0].personaNivel : 'N/A');
        var nivelAlumno ;

        switch(parseInt(sujeto[0].personaNivelIngles)) {
            case 1 :{
                nivelAlumno = 'A1';
            }
            break;
            case 2 :{
                nivelAlumno = 'A2';
            }
                break;
            case 3 :{
                nivelAlumno = 'B1';
            }
                break;
            case 4 :{
                nivelAlumno = 'B2';
            }
                break;
            case 5 :{
                nivelAlumno = 'C1';
            }
                break;
            case 6 :{
                nivelAlumno = 'C2';
            }
                break;
        }

        // var nivelesAlumno = [];

        // sujeto.forEach(function (alguien,index) {
        //     if(!nivel[index].includes(alguien.personaNivel)){
        //         nivelesAlumno.push(alguien.personaNivel);
        //     }
        //    nivelesAlumno.push(alguien.personaNivel);
        // });


        // console.log(sujeto);
        // console.log(nivelesAlumno);
        estiloNombreAlumno.innerHTML = sujeto[0].personaNombre;
        nombreAlumno.appendChild(estiloNombreAlumno);
        alumnoGrupo.appendChild(nombreAlumno);


        nivel.forEach(function (lenguajeNivel) {
            var barraNivel = document.createElement('progress');
            // console.log(sujeto.filter(level => level.personaNivel.localeCompare(lenguajeNivel) === 0).reduce(function (acumulador,valorActual) { return acumulador + valorActual.tareasNivel},0));
            var cantidadTarea = sujeto.filter(level => level.personaNivel.localeCompare(lenguajeNivel) === 0).reduce(function (acumulador,valorActual) { return acumulador + valorActual.tareasNivel},0);
            var nivelPersona = sujeto.filter(level => level.personaNivel.localeCompare(lenguajeNivel) === 0);

            barraNivel.setAttribute('max','18');
            barraNivel.setAttribute('value',cantidadTarea);
            alumnoGrupo.appendChild(barraNivel);
            if (cantidadTarea > 18){
                cantidadTarea = 18;
            }
            porcentajeTotal += cantidadTarea;

        });
        if (porcentajeTotal <= 18){
            levelGrafic = nivel[0];
            porcentajeNivel = Math.round((porcentajeTotal * 100)/18);
        }else if(porcentajeTotal > 18 && porcentajeTotal <= 36){
            levelGrafic = nivel[1];
            porcentajeNivel = Math.round(((porcentajeTotal - 18)*100)/18);
        }else if(porcentajeTotal > 36 && porcentajeTotal <= 54){
            levelGrafic = nivel[2];
            porcentajeNivel = Math.round(((porcentajeTotal-36) * 100)/18);
        }else if(porcentajeTotal > 54 && porcentajeTotal <= 72){
            porcentajeNivel = Math.round(((porcentajeTotal-54) * 100)/18);
            levelGrafic = nivel[3];
        }else if(porcentajeTotal > 72 && porcentajeTotal <= 90){
            porcentajeNivel = Math.round(((porcentajeTotal-72) * 100)/18);
            levelGrafic = nivel[4];
        }else if(porcentajeTotal >= 90 ){
            porcentajeNivel = Math.round(((porcentajeTotal-90) * 100)/18);
            levelGrafic = nivel[5];
        }
        porcentajeTotal =  Math.round((porcentajeTotal * 100)/(18*6));
        porcentajeAlumno.innerText = nivelAlumno + ":"+ " \u00A0 \u00A0"  + porcentajeNivel + '%' + " \u00A0 \u00A0" + "Total:" + "\u00A0 \u00A0" + porcentajeTotal + '%';
        nombreAlumno.appendChild(porcentajeAlumno);
        grupo.appendChild(alumnoGrupo);
        canvasGrafica.appendChild(grupo);
    }
}


function GraficaAlumno(idGrupo,idAlumno,nombreCompleto,totalTareas,nivelLenguaje,nivelLenguajeAlumno) {
    this.idGrupo = idGrupo;
    this.idAlumo = idAlumno;
    this.nombreCompleto = nombreCompleto;
    this.totalTareas = totalTareas;
    this.nivelLenjuaje = nivelLenguaje;
    this.nivelLenguajeAlumno = nivelLenguajeAlumno
}

function DatosGrafica(personaId,personaNombre,tareasNivel,personaNivel,personaGrupo,personaNivelIngles) {
    this.personaId = personaId;
    this.personaNombre = personaNombre;
    this.tareasNivel = parseInt(tareasNivel);
    this.personaNivel = personaNivel;
    this.personaGrupo = personaGrupo;
    this.personaNivelIngles = personaNivelIngles;
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
 console.log(numGraficas);
}
