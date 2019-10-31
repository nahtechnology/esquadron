var  nivel = ['A1','A2','B1','B2','C1','C2'];

function datosGrupo() {
    console.log("invocada");
    var alumno = [],idalumno = [] , grupos = [],nivel2=[] ;
    var datosAlumnos = document.querySelector('#graficas ');
    var listaDatos = datosAlumnos.querySelectorAll('div');
    var canvasGrafica = document.querySelector('#canvas');
    while (datosAlumnos.hasChildNodes()){
        datosAlumnos.removeChild(datosAlumnos.firstChild);
    }
    while (canvasGrafica.hasChildNodes()){
        canvasGrafica.removeChild(canvasGrafica.firstChild);
    }


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
            grupos.push(persona.idGrupo);`
            `
        }
    });
    var grup;
   grupos.forEach(function (value, index) {
       var graficas = [];
       alumno.filter(person => person.idGrupo.localeCompare(grupos[index]) === 0).forEach(function (grafica) {
                var persona = new DatosGrafica(grafica.idAlumo,grafica.nombreCompleto,grafica.totalTareas,grafica.nivelLenjuaje);
                graficas.push(persona);
       });
       crearGrafica(graficas);
   }) ;

}

function crearGrafica(datosGrupo) {
    console.log("crear grafica invocada");
    var canvasGrafica = document.querySelector('#canvas');
    var idPersona = [];
    var grupo = document.createElement('div');
    console.log(datosGrupo);

    datosGrupo.forEach(function (grupo) {
        if(!idPersona.includes(grupo.personaId)){
            idPersona.push(grupo.personaId);
        }
    });
    // console.log(idPersona);
    for (var indice = 0 ; indice < idPersona.length ; indice++) {
        var alumnoGrupo = document.createElement('div');
        var nombreAlumno = document.createElement('p');
        var level1 = document.createElement('span');
        var level2 = document.createElement('span');
        var level3 = document.createElement('span');
        var level4 = document.createElement('span');
        var level5 = document.createElement('span');
        var level6 = document.createElement('span');
        var sujeto = datosGrupo.filter(escolar => escolar.personaId.localeCompare(idPersona[indice]) === 0);
        console.log(sujeto);
        nombreAlumno.innerHTML = sujeto[0].personaNombre;
        alumnoGrupo.appendChild(nombreAlumno);
        level1.innerHTML='A1';
        level2.innerHTML='A2';
        level3.innerHTML='B1';
        level4.innerHTML='B2';
        level5.innerHTML='C1';
        level6.innerHTML='C2';
        alumnoGrupo.appendChild(level1);
        alumnoGrupo.appendChild(level2);
        alumnoGrupo.appendChild(level3);
        alumnoGrupo.appendChild(level4);
        alumnoGrupo.appendChild(level5);
        alumnoGrupo.appendChild(level6);
        nivel.forEach(function (lenguajeNivel) {
            var barraNivel = document.createElement('progress');
            // console.log(sujeto.filter(level => level.personaNivel.localeCompare(lenguajeNivel) === 0).reduce(function (acumulador,valorActual) { return acumulador + valorActual.tareasNivel},0));
            var cantidadTarea = sujeto.filter(level => level.personaNivel.localeCompare(lenguajeNivel) === 0).reduce(function (acumulador,valorActual) { return acumulador + valorActual.tareasNivel},0);
            barraNivel.setAttribute('max','18');
            barraNivel.setAttribute('value',cantidadTarea);
            alumnoGrupo.appendChild(barraNivel);
        });
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

function DatosGrafica(personaId,personaNombre,tareasNivel,personaNivel) {
    this.personaId = personaId;
    this.personaNombre = personaNombre;
    this.tareasNivel = parseInt(tareasNivel);
    this.personaNivel = personaNivel;
}

