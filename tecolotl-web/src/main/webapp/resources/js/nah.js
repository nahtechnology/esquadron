/**
 * Objeto para la inserccion de un calendario
 * @param localidad en caso de requerir alguna zona horaria
 * @constructor para cpnstruir un objeto del tipo fecha
 */
function SelectorFecha(localidad) {
    this.localidad = localidad == null || typeof localidad === 'undefined' ? 'default' : localidad;
    this.fecha = new Date();
    this.fecha.setHours(0,0,0,0);
    this.entrada;
}

SelectorFecha.prototype.diasSemana = function () {
    var formato = Intl.DateTimeFormat(this.localidad, {weekday: 'short'});
    var fechaLocal = new Date();
    while (fechaLocal.getDay() !== 0) {
        fechaLocal.setDate(fechaLocal.getDate() + 1);
    }
    var diasSeamana = [];
    for (var i = 0; i < 7; i++) {
        diasSeamana.push(formato.format(fechaLocal));
        fechaLocal.setDate(fechaLocal.getDate() + 1);
    }
    return diasSeamana;
};

SelectorFecha.prototype.mesAnio = function () {
    var formato = Intl.DateTimeFormat(this.localidad, {month: 'long'});
    return formato.format(this.fecha);
};

SelectorFecha.prototype.creaSelector = function (elemento) {
    this.entrada = elemento;
    var contenedorSelector = document.createElement('div');
    contenedorSelector.classList.add('contenedor-date');
    //dias de la semana
    var selectorDiasSeamana = document.createElement('div');
    selectorDiasSeamana.classList.add("calendar-week");
    this.diasSemana().forEach(function (dia) {
        var diaSemana = document.createElement('div');
        diaSemana.appendChild(document.createTextNode(dia));
        selectorDiasSeamana.appendChild(diaSemana);
    });
    //agregando contenedor
    this.selectorCuerpoAnio(contenedorSelector);
    this.selectorCuerpoMes(contenedorSelector);
    contenedorSelector.appendChild(selectorDiasSeamana);
    this.selectorCuerpoDias(contenedorSelector);
    elemento.addEventListener('focus', function (evento) {
        evento.target.parentNode.insertBefore(contenedorSelector, evento.target.nextSibling);
    });
    /*elemento.addEventListener('blur', function (evento) {
            console.log(_this.click ? 'click' : 'no click');
            //evento.target.parentNode.removeChild(evento.target.nextSibling);
    })*/
};

SelectorFecha.prototype.selectorCuerpoAnio = function (contenedorSelector) {
    var _this = this;
    var selectorAnioReemplazar = contenedorSelector.querySelector('.calendar-year');
    var selectorAnio = document.createElement('div');
    selectorAnio.classList.add('calendar-year');
    var iconoAnioIzquierdo = document.createElement('a');
    iconoAnioIzquierdo.classList.add('navLeftY');
    iconoAnioIzquierdo.setAttribute('uk-icon', 'icon:  triangle-left;ratio: 1.2');
    iconoAnioIzquierdo.addEventListener('click', function (evento) {
        _this.fecha.setFullYear(_this.fecha.getFullYear() - 1);
        _this.selectorCuerpoAnio(contenedorSelector);
        _this.selectorCuerpoMes(contenedorSelector);
        _this.selectorCuerpoDias(contenedorSelector);
    });
    var tituloAnio = document.createElement('h4');
    tituloAnio.appendChild(document.createTextNode(this.fecha.getFullYear().toString()));
    var iconoAnioDerecho = document.createElement('a');
    iconoAnioDerecho.classList.add('navRightY');
    iconoAnioDerecho.setAttribute('uk-icon', 'icon:  triangle-right;ratio: 1.2');
    iconoAnioDerecho.addEventListener('click', function (evento) {
        _this.fecha.setFullYear(_this.fecha.getFullYear() + 1);
        _this.selectorCuerpoAnio(contenedorSelector);
        _this.selectorCuerpoMes(contenedorSelector);
        _this.selectorCuerpoDias(contenedorSelector);
    });
    if (selectorAnioReemplazar === null) {
        selectorAnio.appendChild(iconoAnioIzquierdo);
        selectorAnio.appendChild(tituloAnio);
        selectorAnio.appendChild(iconoAnioDerecho);
        contenedorSelector.appendChild(selectorAnio);
    } else {
        while (selectorAnioReemplazar.firstChild) {
            selectorAnioReemplazar.removeChild(selectorAnioReemplazar.firstChild);
        }
        selectorAnioReemplazar.appendChild(iconoAnioIzquierdo);
        selectorAnioReemplazar.appendChild(tituloAnio);
        selectorAnioReemplazar.appendChild(iconoAnioDerecho);
    }
};

SelectorFecha.prototype.selectorCuerpoMes = function (contenedorSelector) {
    var _this = this;
    var selectorMesReemplazar = contenedorSelector.querySelector('.calendar-mount');
    var selectorMes = document.createElement('div');
    selectorMes.classList.add('calendar-mount');
    var tituloMes = document.createElement('h4');
    tituloMes.appendChild(document.createTextNode(this.mesAnio()));
    var iconoIzquierdoMes = document.createElement('a');
    iconoIzquierdoMes.classList.add('navLeftM');
    iconoIzquierdoMes.setAttribute('uk-icon', 'icon:  triangle-left;ratio: 1.2');
    iconoIzquierdoMes.addEventListener('click', function (evento) {
        _this.fecha.setMonth(_this.fecha.getMonth() - 1);
        _this.selectorCuerpoAnio(contenedorSelector);
        _this.selectorCuerpoMes(contenedorSelector);
        _this.selectorCuerpoDias(contenedorSelector);
    });
    var iconoDerechoMes = document.createElement('a');
    iconoDerechoMes.classList.add('navRightM');
    iconoDerechoMes.setAttribute('uk-icon', 'icon:  triangle-right;ratio: 1.2');
    iconoDerechoMes.addEventListener('click', function (evento) {
        _this.fecha.setMonth(_this.fecha.getMonth() + 1);
        _this.selectorCuerpoAnio(contenedorSelector);
        _this.selectorCuerpoMes(contenedorSelector);
        _this.selectorCuerpoDias(contenedorSelector);
    });
    if (selectorMesReemplazar === null) {
        selectorMes.appendChild(iconoIzquierdoMes);
        selectorMes.appendChild(tituloMes);
        selectorMes.appendChild(iconoDerechoMes);
        contenedorSelector.appendChild(selectorMes);
    } else {
        while (selectorMesReemplazar.firstChild) {
            selectorMesReemplazar.removeChild(selectorMesReemplazar.firstChild);
        }
        selectorMesReemplazar.appendChild(iconoIzquierdoMes);
        selectorMesReemplazar.appendChild(tituloMes);
        selectorMesReemplazar.appendChild(iconoDerechoMes);
    }
};

SelectorFecha.prototype.selectorCuerpoDias = function (contenedorSelector) {
    var selectorDiasSemana = document.createElement('div');
    var hoy = new Date();
    selectorDiasSemana.classList.add('calendar-day');
    var fechaLocal = new Date(this.fecha.getFullYear(), this.fecha.getMonth(), this.fecha.getDate());
    var diasMes = 35;
    var _this = this;
    fechaLocal.setDate(1);
    while (fechaLocal.getDay() !== 0) {
        fechaLocal.setDate(fechaLocal.getDate() - 1);
    }
    for (var i = 0; i < diasMes; i++) {
        var dia = document.createElement('div');
        dia.appendChild(document.createTextNode(fechaLocal.getDate().toString()));
        if (fechaLocal.getDate() === hoy.getDate() && fechaLocal.getMonth() === hoy.getMonth() && fechaLocal.getFullYear() === hoy.getFullYear()) {
            dia.classList.add(this.fecha.getMonth() === fechaLocal.getMonth() ? 'dia-actual' : 'dia-actual-no-mes');
        }
        if (fechaLocal.getMonth() !== this.fecha.getMonth()) {
            dia.classList.add('dia-no-mes');
        }
        if ((fechaLocal.getDate() >= 1) && ( fechaLocal.getDate() <= 31) && (fechaLocal.getMonth() === this.fecha.getMonth())) {
            dia.addEventListener("mouseover", function(event){
                event.target.style.background = "black";
                event.target.style.cursor = "pointer";
                event.target.style.color = "white";
            });
            dia.addEventListener("mouseleave", function(e){
                e.target.removeAttribute("style");
            });
            dia.addEventListener('click', function (evento) {
                var formato = Intl.DateTimeFormat(this.localidad, {month: '2-digit', year: 'numeric', day: '2-digit'});
                var fechaInput = new Date(_this.fecha.getFullYear(), _this.fecha.getMonth(), evento.target.textContent);
                _this.entrada.value = formato.format(fechaInput);
                evento.target.style = null;
                contenedorSelector.remove();
            });
        }
        selectorDiasSemana.appendChild(dia);
        if((i == 34) && ((fechaLocal.getDate() == 29) || (fechaLocal.getDate() == 30)) && ((fechaLocal.getMonth() != 1))){
            diasMes = 42;
        }
        fechaLocal.setDate(fechaLocal.getDate() + 1);
        if((diasMes == 42) && (fechaLocal.getDate() == 1) && i == 34){
            break;
        }
    }
    if (contenedorSelector.querySelector('.calendar-day') !== null) {
        contenedorSelector.removeChild(contenedorSelector.lastChild);
    }
    contenedorSelector.appendChild(selectorDiasSemana);
};

/**
 * Funcion que sólo permite la entrada de fechas, está asignada por número y con el caracter: /
 * @param evento Evento donde se hizo la llama input
 */
function entradaFecha(evento) {
    switch (evento.inputType) {
        case 'insertText':
            var codigo = evento.data.charCodeAt(0);
            if (codigo < 48 || codigo > 57 ) {
                evento.target.value = evento.target.value.slice(0, -1);
            }
            if (evento.target.value.length === 2 || evento.target.value.length  === 5) {
                evento.target.value = evento.target.value.concat('/');
            }
            if (evento.target.value.length === 11) {
                evento.target.value = evento.target.value.slice(0, -1);
            }
            break;
        case 'deleteContentBackward':
            break;
    }
}