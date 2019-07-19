function SelectorFecha(localidad, diaSemanaFormato, mesFormato) {
    this.localidad = localidad == null || typeof localidad === 'undefined' ? 'default' : localidad;
    this.fecha = new Date();
    this.diaSemanaFormato = diaSemanaFormato == null || typeof diaSemanaFormato === 'undefined' ? 'short' : diaSemanaFormato;
    this.mesFormato = mesFormato == null || typeof mesFormato === 'undefined' ? 'long' : mesFormato;
}

SelectorFecha.prototype.diasSemana = function () {
    var formato = Intl.DateTimeFormat(this.localidad, {weekday: this.diaSemanaFormato});
    var fechaLocal = new Date();
    while (fechaLocal.getDay() !== 0) {
        fechaLocal.setDate(fechaLocal.getDate() + 1);
    }
    var diasSeamana = [];
    while (fechaLocal.getDay() !== 6) {
        diasSeamana.push(formato.format(diasSeamana));
        fechaLocal.setDate(fechaLocal.getDate() + 1);
    }
    return diasSeamana;
};

SelectorFecha.prototype.mesAnio = function () {
    var formato = Intl.DateTimeFormat(this.localidad, {month: this.mesFormato});
    return formato.format(this.fecha);
};

SelectorFecha.prototype.creaSelector = function (elemento) {
    var contenedorSelector = document.createElement('div');
    contenedorSelector.classList.add('contenedor-date');
    var selectorAnio = document.createElement('div');
    selectorAnio.classList.add('calendar-year');
    var iconoAnioIzquierdo = document.createElement('a');
    iconoAnioIzquierdo.classList.add('navLeftY');
    iconoAnioIzquierdo.setAttribute('uk-icon','icon:  triangle-left;ratio: 1.2');
    var tituloAnio = document.createElement('h4');
    tituloAnio.appendChild(document.createTextNode(this.fecha.getFullYear().toString()));
    selectorAnio.appendChild(iconoAnioIzquierdo);
    selectorAnio.appendChild(tituloAnio);
    contenedorSelector.appendChild(selectorAnio);
    //elemento.parentNode.insertBefore(selectorAnio, elemento.nextSibling);
    elemento.appendChild(contenedorSelector);
};