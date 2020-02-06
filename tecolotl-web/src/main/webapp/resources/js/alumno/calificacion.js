function puntaje() {

    var barras = document.querySelectorAll('.padding-contenedor progress');
    var cajas = document.querySelectorAll('.califica');

    cajas.forEach(calcula);

    function calcula(puntaje, indice, putajesLocal) {
        let valor = puntaje.innerText.split(':')[1];

        if(0 <= parseInt(valor) && parseInt(valor) <= 25){
            barras[indice].classList.add("rojo");
        }
        else if(26 <= parseInt(valor) && parseInt(valor) <= 85){
            barras[indice].classList.add("amarillo");
        }
        else if(86 <= parseInt(valor) && parseInt(valor) <= 100){
            barras[indice].classList.add("verde");
        }

    }

    clearTimeout(tiempo);
}
