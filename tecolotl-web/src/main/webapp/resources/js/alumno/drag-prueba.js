var conta = [];
var cadena = [];
var primera = [], resp = [];
var drag,respuestaDrag;
var cajaAnswer;

document.addEventListener('DOMContentLoaded', function (evt) {
    drag = UIkit.util.$$('.trancript-contenedor .respuesta-transcript');
    respuestaDrag = UIkit.util.$('.contenedor-respuesta');
    for (var inx = 0;inx < drag.length;inx++ ){
        conta.push(0);
    }
    UIkit.util.on(drag,'added',function (texto) {
        console.log(texto.target.dataset.indice);

        drag.forEach(function (caja,indice) {
            if(caja.innerText === ""){
                conta[caja.dataset.indice] = 0;
                // console.log(indice);
            }
            if(caja.dataset.indice === texto.target.dataset.indice ){
                conta[caja.dataset.indice]++;
                if (conta[caja.dataset.indice] === 1){
                    primera[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.respuesta-drag').textContent;
                    resp[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.respuesta-drag').dataset.index;
                }
                if(conta[caja.dataset.indice] === 2){
                    var textos = drag[texto.target.dataset.indice].querySelectorAll('.respuesta-drag');
                    console.log(textos);
                    var cajaTexto = drag[texto.target.dataset.indice];
                    var cajaResp = document.querySelector('.contenedor-respuesta');
                    var caja2 = document.createElement('span');
                    var caja3 = document.createElement('span');
                    textos.forEach(function (word) {
                        var palabra;
                        if (word.dataset.index !== resp[caja.dataset.indice]){
                            caja2.innerHTML = word.innerHTML;
                            console.log(word.dataset.index + " " + resp[caja.dataset.indice]);
                            while (cajaTexto.hasChildNodes()){
                                cajaTexto.removeChild(cajaTexto.firstChild);
                            }
                            caja3.innerText = primera[caja.dataset.indice];
                            caja2.classList.add('respuesta-drag');
                            caja2.dataset.index = word.dataset.index;
                            cajaTexto.appendChild(caja2);
                            caja3.classList.add('respuesta-drag');
                            caja3.dataset.index = resp[caja.dataset.indice];
                            cajaResp.appendChild(caja3);
                            console.log(primera[caja.dataset.indice]);
                        }

                    });
                    conta[caja.dataset.indice] = 1;
                    primera[caja.dataset.indice] = drag[texto.target.dataset.indice].textContent;
                    resp[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.respuesta-drag').dataset.index;
                }
            }
        });

    });

    UIkit.util.on(respuestaDrag,'added', function () {
        console.log('agregado');
        drag.forEach(function (box,indice) {
            if (box.innerText === "") {
                conta[indice] = 0;
                // console.log(indice);
            }
        });
    });

});