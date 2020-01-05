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
    document.querySelector('#formulario-califica input[type=submit]').addEventListener('click', validaRespuesta);
});

function calificarOracion(oracion,respuesta) {
    oracion.forEach(function (texto,indice) {
        let oracionTexto = texto.innerHTML.split(".");
        var textos = new Textos(oracionTexto[0],respuesta[indice].value);
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
                rubrica[index].innerHTML = "Deficiente";
                score[index].innerHTML = "25";
                break;
            case "2":
                rubrica[index].innerHTML = "Regular";
                score[index].innerHTML = "50";
                break;
            case "3":
                rubrica[index].innerHTML = "Bueno";
                score[index].innerHTML = "75";
                break;
            case "4":
                rubrica[index].innerHTML = "Excelente";
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
                    rubrica[index].innerHTML = "Deficiente";
                    score[index].innerHTML = "25";
                    break;
                case "2":
                    rubrica[index].innerHTML = "Regular";
                    score[index].innerHTML = "50";
                    break;
                case "3":
                    rubrica[index].innerHTML = "Bueno";
                    score[index].innerHTML = "75";
                    break;
                case "4":
                    rubrica[index].innerHTML = "Excelente";
                    score[index].innerHTML = "100";
                    break;
            }
        };

    });

    totalPalabras.forEach(function (value, index) {
        var calculo;
        calculo = (calificacion[index]*100)/totalPalabras[index];
        console.log(calculo);

        if(calculo <= 25) {
            slider[index].value = "1";
            // score[index].innerHTML = Math.round(calculo);
            score[index].innerHTML = 25;
            rubrica[index].innerHTML = "Deficiente";
        }
        if(calculo > 25 && calculo <= 50 ){
            slider[index].value = "2";
            score[index].innerHTML = Math.round(calculo);
            rubrica[index].innerHTML = "Regular";
        }

        if(calculo > 50 && calculo <= 75 ){
            slider[index].value = "3";
            score[index].innerHTML = Math.round(calculo);
            rubrica[index].innerHTML = "Bueno";
        }
        if (calculo > 75 ){
            slider[index].value = "4";
            score[index].innerHTML = Math.round(calculo);
            rubrica[index].innerHTML = "Excelente";
        }

    });
}

function  Textos(oracionActividad,oracionAlumno) {
    this.oracionActividad = oracionActividad;
    this.oracionAlumno = oracionAlumno;
}

function validaRespuesta(evento) {
    var entradas = document.getElementById('formulario-califica').parentNode.querySelectorAll('input[type=text]');
    entradas = Array.from(entradas);
    if (entradas.filter(entrada => entrada.value).length < entradas.length) {
        UIkit.modal.alert('No se puede calificar hasta que el alumno haya terminado de responder todos los ejercicios.').then(function () {
            console.log('Alert closed.')
        });
        return false;
    }
    return true;
}