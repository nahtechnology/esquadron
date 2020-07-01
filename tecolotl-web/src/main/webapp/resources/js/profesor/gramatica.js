var slider = document.querySelectorAll('.calificar input[type=range]');
var oracion = document.querySelectorAll('.calificar > form > div > div:first-child > span');
var respuesta = document.querySelectorAll('.calificar > form > div > div:first-child > input[type=text]');
var rubrica = document.querySelectorAll('.calificar > form > div > div:last-child > :first-child');
var score = document.querySelectorAll('.calificar > form > div > div:last-child > :first-child + span');
var oraciones = [];
var totalPalabras = [];
var calificacion = [];
var datos = [];
document.addEventListener('DOMContentLoaded',function (ev) {
    calificarOracion(oracion,respuesta);
    var botonRespuesta = document.querySelector('form .boton-escuadron-base');
    var respuestas = document.querySelectorAll('form > div  input[type=text]');
    var conta = 0;
    respuestas.forEach(respuesta => {
        if(respuesta.value === ""){
            conta++;
        }
    });

    let porcentaje = Math.round((conta * 100)/respuestas.length);
    console.log(porcentaje);
    if(porcentaje >= 20){
        console.log(porcentaje,"desabilitado");
        botonRespuesta.disabled = true;
        botonRespuesta.classList.add('boton-disabilitado');
        UIkit.notification(termina);
    }else{
        console.log('se puede');
        botonRespuesta.classList.remove('boton-disabilitado');
        botonRespuesta.disabled = false;
    }

});

function calificarOracion(oracion,respuesta) {
    oracion.forEach(function (texto,indice) {
         let oracionTexto = texto.innerHTML;
        var textos = new Textos(oracionTexto,respuesta[indice].value);
        oraciones.push(textos);
    });
    oraciones.forEach(function (value) {
        var contador = 0,palabrasTotal = 0;
        var primera = value.oracionActividad.split(" ");
        var segunda = value.oracionAlumno.split(" ");
        primera.forEach(function (value1,index) {
            palabrasTotal++;
            if (value1 === segunda[index]){
                contador++;
            }
        });
        calificacion.push(contador);
        totalPalabras.push(palabrasTotal);
    });


    rubrica.forEach(function (value,index) {

        switch (slider[index].value) {
            case "1":
                rubrica[index].innerHTML = "to improve";
                score[index].innerHTML = "25";
                break;
            case "2":
                rubrica[index].innerHTML = "Satisfactory";
                score[index].innerHTML = "50";
                break;
            case "3":
                rubrica[index].innerHTML = "Good";
                score[index].innerHTML = "75";
                break;
            case "4":
                rubrica[index].innerHTML = "Outstanding";
                score[index].innerHTML = "100";
                break;
            default:
                console.error("Seleccion aun no programada");
        }

    });

    slider.forEach(function (value,index){
        datos[index] = value.value;
        value.oninput = function() {

            switch (this.value) {
                case "1":
                    rubrica[index].innerHTML = "to improve";
                    score[index].innerHTML = "25";
                    break;
                case "2":
                    rubrica[index].innerHTML = "Satisfactory";
                    score[index].innerHTML = "50";
                    break;
                case "3":
                    rubrica[index].innerHTML = "Good";
                    score[index].innerHTML = "75";
                    break;
                case "4":
                    rubrica[index].innerHTML = "Outstanding";
                    score[index].innerHTML = "100";
                    break;
            }
        };

    });

    totalPalabras.forEach(function (value, index) {
        var calculo;
        calculo = (calificacion[index]*100)/totalPalabras[index];
        console.log(calculo);

        if(calculo <= 37.5) {
            slider[index].value = "1";
            // score[index].innerHTML = Math.round(calculo);
            score[index].innerHTML = "25";
            rubrica[index].innerHTML = "to improve";
        }
        if(calculo > 37.5 && calculo <= 62.5 ){
            slider[index].value = "2";
            score[index].innerHTML = "50";
            rubrica[index].innerHTML = "Satisfactory";
        }

        if(calculo > 62.5 && calculo <= 87.5 ){
            slider[index].value = "3";
            score[index].innerHTML = "75";
            rubrica[index].innerHTML = "Good";
        }
        if (calculo > 87.5 ){
            slider[index].value = "4";
            score[index].innerHTML = "100";
            rubrica[index].innerHTML = "Outstanding";
        }

    });
}

function  Textos(oracionActividad,oracionAlumno) {
    this.oracionActividad = oracionActividad;
    this.oracionAlumno = oracionAlumno;
}