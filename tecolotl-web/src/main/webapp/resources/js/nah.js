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
    while (fechaLocal.getDay() !== 7) {
        diasSeamana.push(formato.format(fechaLocal));
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
    //año
    var selectorAnio = document.createElement('div');
    selectorAnio.classList.add('calendar-year');
    var iconoAnioIzquierdo = document.createElement('a');
    iconoAnioIzquierdo.classList.add('navLeftY');
    iconoAnioIzquierdo.setAttribute('uk-icon','icon:  triangle-left;ratio: 1.2');
    var tituloAnio = document.createElement('h4');
    tituloAnio.appendChild(document.createTextNode(this.fecha.getFullYear().toString()));
    var iconoAnioDerecho = document.createElement('a');
    iconoAnioDerecho.classList.add('navRightY');
    iconoAnioDerecho.setAttribute('uk-icon', 'icon:  triangle-right;ratio: 1.2');
    selectorAnio.appendChild(iconoAnioIzquierdo);
    selectorAnio.appendChild(tituloAnio);
    selectorAnio.appendChild(iconoAnioDerecho);
    //mes
    var selectorMes = document.createElement('div');
    selectorMes.classList.add('calendar-mount');
    var tituloMes = document.createElement('h4');
    tituloMes.appendChild(document.createTextNode(this.mesAnio()));
    var iconoIzquierdoMes = document.createElement('a');
    iconoIzquierdoMes.classList.add('navLeftM');
    iconoIzquierdoMes.setAttribute('uk-icon','icon:  triangle-left;ratio: 1.2');
    var iconoDerechoMes = document.createElement('a');
    iconoDerechoMes.classList.add('navRightM');
    iconoDerechoMes.setAttribute('uk-icon','icon:  triangle-right;ratio: 1.2');
    selectorMes.appendChild(iconoIzquierdoMes);
    selectorMes.appendChild(tituloMes);
    selectorMes.appendChild(iconoDerechoMes);
    //dias de la semana
    var selectorDiasSeamana = document.createElement('div');
    selectorDiasSeamana.classList.add("calendar-week");
    this.diasSemana().forEach(function (dia) {
        var diaSemana = document.createElement('div');
        diaSemana.appendChild(document.createTextNode(dia));
        selectorDiasSeamana.appendChild(diaSemana);
    });
    //calendario
    var selectorDiasSeamana = document.createElement("div");
    selectorDiasSeamana.classList.add('calendar-day');
    var fechaLocal = new Date(this.fecha.getMilliseconds());
    fechaLocal.setDate(1);
/*    while (fechaLocal.getDay() !== 0) {
        fechaLocal.setDate(fechaLocal.getDate() - 1);
    }
    for (var i =  1 ; i <= 35; i++) {
        var dia = document.createElement("div");
        dia.classList.add('.dias');
        dia.appendChild(document.createTextNode(fechaLocal.getDate().toString()));
        selectorDiasSeamana.appendChild(dia);
        fechaLocal.setDate(fechaLocal.getDate() + 1);
    }*/
    //agregando contenedor
    contenedorSelector.appendChild(selectorAnio);
    contenedorSelector.appendChild(selectorMes);
    contenedorSelector.appendChild(selectorDiasSeamana);
    elemento.appendChild(contenedorSelector);
};