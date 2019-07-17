document.addEventListener("DOMContentLoaded",function () {
    var elemento = document.querySelector('.picker');
    elemento.addEventListener('click',function (evento) {
        elInner = elemento.nextSibling;
        console.log(elemento.nextSibling, 'Me borraran', elInner);
        elemento.parentNode.removeChild(elemento.nextSibling);
        elemento.parentNode.insertBefore(dataPicker(anio, mesNumero),elemento.nextSibling);
    });
});
//Arrays de nombres de los días de la semana, y nombres de los meses.
var diaSemana = ["Dom","Lun", "Mar","Mie","Jue", "Vie", "Sab"];
var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
//Contador para cada mes.
var mesLength = 11;
//Se obtiene la fecha actual.
var anio = new Date().getFullYear();
var mesNumero = new Date().getMonth();
//Calcula el día de la semana en el que comienza el mes. Utilizar dia = 1, como valor fijo.
var diaSem = function(anio, mes, dia){
    return new Date(mes+' '+dia+' '+anio+' 12:00:00');
}
//Calcula el número de días que tiene el mes de acuerdo al año y el mes
var dias = function(anio, mes){
    return new Date(anio, mes, 0).getDate();
}
//Elemento necesario para ocultar el calendario
var elInner = null;
//Formato para la fecha
var opcionesFecha = {
    year: "numeric",
    month: "2-digit",
    day: "2-digit"
};
var incrementaAnio = function(){
    return ++anio;
}
var decrementaAnio = function(){
    return --anio;
}
function dataPicker(anio, mesNumero){
    //Guardamos el valor en diasMes
    var diasMes = dias(anio, mesNumero + 1);
    //Guardamos el valor en diasSem
    var diasSem = diaSem(anio, mesNumero + 1, 1);
    console.log(diasMes, diasSem.getUTCDay());
    console.log(diaSemana[diasSem.getUTCDay()], mes[mesNumero], mesNumero);
    //Contador para llenar los días del mes
    var llena = 1;
    var contenedorPicker = document.createElement('div');
    contenedorPicker.classList.add('contenedor-date');
    var pickerYear = document.createElement('div');
    pickerYear.classList.add('calendar-year');
    var iconoLeftYear = document.createElement('a');
    iconoLeftYear.classList.add('navLeftY');
    iconoLeftYear.setAttribute('uk-icon','icon:  triangle-left;ratio: 1.2');
    //Se agrega un evento del tipom 'click' para decrementar el año
    iconoLeftYear.addEventListener('click', function(){
        anio = decrementaAnio();
        console.log(anio);
        var elemento = document.querySelector('.picker');
        elemento.parentNode.removeChild(elemento.nextSibling);
        elemento.parentNode.insertBefore(dataPicker(anio, mesNumero),elemento.nextSibling);
    });
    var tituloYear = document.createElement('h4');
    tituloYear.appendChild(document.createTextNode(anio)); //Año
    var iconoRightYear = document.createElement('a');
    iconoRightYear.classList.add('navRightY');
    iconoRightYear.setAttribute('uk-icon','icon:  triangle-right;ratio: 1.2');
    //Se agrega un evento del tipom 'click' para incrementar el año
    iconoRightYear.addEventListener('click', function(){
        anio = incrementaAnio();
        console.log(anio);

        var elemento = document.querySelector('.picker');
        elemento.parentNode.removeChild(elemento.nextSibling);
        elemento.parentNode.insertBefore(dataPicker(anio, mesNumero),elemento.nextSibling);
    });
    pickerYear.appendChild(iconoLeftYear);
    pickerYear.appendChild(tituloYear);
    pickerYear.appendChild(iconoRightYear);
    var pickerMount = document.createElement('div');
    pickerMount.classList.add('calendar-mount');
    var tituloMount = document.createElement('h4');
    tituloMount.appendChild(document.createTextNode(mes[mesNumero])); //Mes
    var iconoLeftMount = document.createElement('a');
    iconoLeftMount.classList.add('navLeftM');
    iconoLeftMount.setAttribute('uk-icon','icon:  triangle-left;ratio: 1.2');
    //Se agrega un evento del tipom 'click' para decrementar el mes
    iconoLeftMount.addEventListener('click', function(){
        var auxMes = (mesNumero == 0) ? mesNumero = 11 : mesNumero--;
        var elemento = document.querySelector('.picker');
        elemento.parentNode.removeChild(elemento.nextSibling);
        elemento.parentNode.insertBefore(dataPicker(anio, mesNumero),elemento.nextSibling);
    });
    var iconoRightMount = document.createElement('a');
    iconoRightMount.classList.add('navRightM');
    iconoRightMount.setAttribute('uk-icon','icon:  triangle-right;ratio: 1.2');
    //Se agrega un evento del tipom 'click' para incrementar el año
    iconoRightMount.addEventListener('click', function(){
        var auxMes = (mesNumero == 11) ? mesNumero = 0 : mesNumero++;
        var elemento = document.querySelector('.picker');
        elemento.parentNode.removeChild(elemento.nextSibling);
        elemento.parentNode.insertBefore(dataPicker(anio, mesNumero),elemento.nextSibling);
    });
    pickerMount.appendChild(iconoLeftMount);
    pickerMount.appendChild(tituloMount);
    pickerMount.appendChild(iconoRightMount);
    var pickerWeek = document.createElement('div');
    pickerWeek.classList.add("calendar-week");
    for (var i = 0; i < 7; i++){
        var dayWeek = document.createElement('div');
        dayWeek.appendChild(document.createTextNode(diaSemana[i])); //Dias Semana
        pickerWeek.appendChild(dayWeek);
    }
    var pickerDay = document.createElement("div");
    pickerDay.classList.add('calendar-day');
    for (var i =  1 ; i <= 35; i++){
        var day = document.createElement("div");
        day.classList.add('.dias');
        day.appendChild(document.createTextNode((i <= diasSem.getUTCDay()) ? ((diasSem.getUTCDay() == 0) ? llena++ : "") : ((llena <= diasMes) && (diasMes >= llena)) ? llena++ : ""));
        if(day.innerHTML != ""){
            day.addEventListener('click', function(){
                var elemento = document.querySelector('.picker');
                var date = new Date(Date.UTC(anio, mesNumero, parseInt(this.innerHTML, 10) + 1));
                elemento.value = new Intl.DateTimeFormat('default', opcionesFecha).format(date);
                console.log(elemento.value);
                elemento.parentNode.removeChild(elemento.nextSibling);
                elemento.parentNode.insertBefore(elInner,elemento.nextSibling);
            });
        }
        pickerDay.appendChild(day);
    }
    contenedorPicker.appendChild(pickerYear);
    contenedorPicker.appendChild(pickerMount);
    contenedorPicker.appendChild(pickerWeek);
    contenedorPicker.appendChild(pickerDay);
    return contenedorPicker;
}

/**
 * window.addEventListener('keydown', function(event){
        if(event.keyCode == 27){
            var elemento = document.querySelector('.picker');
            elemento.parentNode.removeChild(elemento.nextSibling);
            elemento.parentNode.insertBefore(elInner,elemento.nextSibling);
        }
    }, false);
 */