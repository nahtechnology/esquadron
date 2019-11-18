var conta = [];
var cadena = [];
var primera = [], resp = [],identDrag = [], codigo = [];
var drag,respuestaDrag;
var cajaAnswer;

document.addEventListener('DOMContentLoaded', function (evt) {
    drag = UIkit.util.$$('.relacion-imagen .contenedor-imagen .oraciones-relacion-img');
    respuestaDrag = UIkit.util.$$('.relacion-imagen .respuesta-imagen .palabra-imagen');
    for (var inx = 0;inx < drag.length;inx++ ){
        conta.push(0);
    }
    UIkit.util.on(drag,'added',function (texto) {
        console.log(texto.target.dataset.indice);

        drag.forEach(function (caja) {
            if(caja.dataset.indice === texto.target.dataset.indice ){
                conta[caja.dataset.indice]++;
                if (conta[caja.dataset.indice] === 1){
                    primera[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.palabra-imagen').textContent;
                    resp[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.palabra-imagen').dataset.index;
                    identDrag[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.palabra-imagen').id;
                    codigo[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.palabra-imagen').dataset.codigo;
                }
                if(conta[caja.dataset.indice] === 2){
                    var textos = drag[texto.target.dataset.indice].querySelectorAll('.palabra-imagen');
                    console.log(textos);
                    var cajaTexto = drag[texto.target.dataset.indice];
                    var cajaResp = document.querySelector('.respuesta-imagen');
                    var caja2 = document.createElement('div');
                    var caja3 = document.createElement('div');
                    textos.forEach(function (word) {
                        var palabra;
                        if (word.dataset.index !== resp[caja.dataset.indice]){
                            caja2.innerHTML = word.innerHTML;
                            console.log(word.dataset.index + " " + resp[caja.dataset.indice]);
                            while (cajaTexto.hasChildNodes()){
                                cajaTexto.removeChild(cajaTexto.firstChild);
                            }
                            caja3.innerHTML = primera[caja.dataset.indice];
                            caja2.classList.add('palabra-imagen');
                            caja2.id = word.id;
                            caja2.dataset.index = word.dataset.index;
                            caja2.dataset.codigo = word.dataset.codigo;
                            cajaTexto.appendChild(caja2);
                            caja3.classList.add('palabra-imagen');
                            caja3.id = identDrag[caja.dataset.indice];
                            caja3.dataset.index = resp[caja.dataset.indice];
                            caja3.dataset.codigo = codigo[caja.dataset.indice];
                            cajaResp.appendChild(caja3);
                            console.log(primera[caja.dataset.indice]);
                        }

                    });
                    conta[caja.dataset.indice] = 1;
                    primera[caja.dataset.indice] = drag[texto.target.dataset.indice].textContent;
                    resp[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.palabra-imagen').dataset.index;
                    codigo[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.palabra-imagen').dataset.codigo;
                    identDrag[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.palabra-imagen').id;

                }
            }
        });

    });
});