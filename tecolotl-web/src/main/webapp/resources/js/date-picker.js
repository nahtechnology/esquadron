document.addEventListener("DOMContentLoaded",function () {
    var entradas = document.querySelectorAll('.picker');
    entradas.forEach(function (elemento) {
       elemento.addEventListener('click',function (evento) {
            elemento.parentNode.insertBefore(dataPicker(),elemento.nextSibling);
        });
    });
});
function dataPicker(){
var contenedorPicker = document.createElement('div');
contenedorPicker.classList.add('contenedor-date');
var pickerYear = document.createElement('div');
pickerYear.classList.add('calendar-year');
var iconoLeftYear = document.createElement('a');
iconoLeftYear.setAttribute('uk-icon','icon:  triangle-left;ratio: 1.2');
var tituloYear = document.createElement('h4');
tituloYear.appendChild(document.createTextNode('2019'));
var iconoRightYear = document.createElement('a');
iconoRightYear.setAttribute('uk-icon','icon:  triangle-right;ratio: 1.2');
pickerYear.appendChild(iconoLeftYear);
pickerYear.appendChild(tituloYear);
pickerYear.appendChild(iconoRightYear);
var pickerMount = document.createElement('div');
pickerMount.classList.add('calendar-mount');
var tituloMount = document.createElement('h4');
tituloMount.appendChild(document.createTextNode('julio'));
var iconoLeftMount = document.createElement('a');
iconoLeftMount.setAttribute('uk-icon','icon:  triangle-left;ratio: 1.2');
var iconoRightMount = document.createElement('a');
iconoRightMount.setAttribute('uk-icon','icon:  triangle-right;ratio: 1.2');
pickerMount.appendChild(iconoLeftMount);
pickerMount.appendChild(tituloMount);
pickerMount.appendChild(iconoRightMount);
var pickerWeek = document.createElement('div');
pickerWeek.classList.add("calendar-week");
for (var i=0; i<7;i++){
    var dayWeek = document.createElement('div');
    dayWeek.appendChild(document.createTextNode('Di'));
    pickerWeek.appendChild(dayWeek);
}
var pickerDay = document.createElement("div");
pickerDay.classList.add('calendar-day');
for (var i=0; i<35;i++){
    var day = document.createElement("div");
    day.appendChild(document.createTextNode(i));
    pickerDay.appendChild(day);
}
contenedorPicker.appendChild(pickerYear);
contenedorPicker.appendChild(pickerMount);
contenedorPicker.appendChild(pickerWeek);
contenedorPicker.appendChild(pickerDay);
return contenedorPicker;
}