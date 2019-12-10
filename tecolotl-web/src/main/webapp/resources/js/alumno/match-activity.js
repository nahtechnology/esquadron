var contador = [];
var cadena = [];
var primero = [], answer = [],identDrag = [], codigo = [];
var arrastrar,respuestaDrag2;
var cajaAnswer;

document.addEventListener('DOMContentLoaded', function (evt) {
    arrastrar = UIkit.util.$$('.match .caja-preguntas-ordenar  .respuestas-ordenar-lista');
    respuestaDrag2 = UIkit.util.$$('.match .respuestas-ordenar');
    for (var inx = 0;inx < arrastrar.length;inx++ ){
        contador.push(0);
    }
    UIkit.util.on(arrastrar,'added',function (texto) {
        console.log(texto.target.dataset.indice);
        arrastrar.forEach(function (caja) {
            if(caja.innerText === ""){
                contador[caja.dataset.indice] = 0;
                // console.log(caja);
            }
            if(caja.dataset.indice === texto.target.dataset.indice ){
                contador[caja.dataset.indice]++;
                if (contador[caja.dataset.indice] === 1){
                    primero[caja.dataset.indice] = arrastrar[texto.target.dataset.indice].querySelector('.respuesta').textContent;
                    answer[caja.dataset.indice] = arrastrar[texto.target.dataset.indice].querySelector('.respuesta').dataset.index;
                    identDrag[caja.dataset.indice] = arrastrar[texto.target.dataset.indice].querySelector('.respuesta').id;
                    codigo[caja.dataset.indice] = arrastrar[texto.target.dataset.indice].querySelector('.respuesta').dataset.id;
                }
                if(contador[caja.dataset.indice] === 2){
                    var textos = arrastrar[texto.target.dataset.indice].querySelectorAll('.respuesta');
                    console.log(textos);
                    var cajaTexto = arrastrar[texto.target.dataset.indice];
                    var cajaResp = document.querySelector('.respuestas-ordenar');
                    var caja2 = document.createElement('div');
                    var caja3 = document.createElement('div');
                    textos.forEach(function (word) {
                        var palabra;
                        if (word.dataset.index !== answer[caja.dataset.indice]){
                            caja2.innerHTML = word.innerHTML;
                            console.log(word.dataset.index + " " + answer[caja.dataset.indice]);
                            while (cajaTexto.hasChildNodes()){
                                cajaTexto.removeChild(cajaTexto.firstChild);
                            }
                            caja3.innerHTML = primero[caja.dataset.indice];
                            caja2.classList.add('respuesta');
                            caja2.id = word.id;
                            caja2.dataset.index = word.dataset.index;
                            caja2.dataset.id = word.dataset.id;
                            cajaTexto.appendChild(caja2);
                            caja3.classList.add('respuesta');
                            caja3.id = identDrag[caja.dataset.indice];
                            caja3.dataset.index = answer[caja.dataset.indice];
                            caja3.dataset.id = codigo[caja.dataset.indice];
                            cajaResp.appendChild(caja3);
                            console.log(primero[caja.dataset.indice]);
                        }

                    });
                    contador[caja.dataset.indice] = 1;
                    primero[caja.dataset.indice] = arrastrar[texto.target.dataset.indice].textContent;
                    answer[caja.dataset.indice] = arrastrar[texto.target.dataset.indice].querySelector('.respuesta').dataset.index;
                    codigo[caja.dataset.indice] = arrastrar[texto.target.dataset.indice].querySelector('.respuesta').dataset.id;
                    identDrag[caja.dataset.indice] = arrastrar[texto.target.dataset.indice].querySelector('.respuesta').id;

                }
            }
        });

    });
    UIkit.util.on(respuestaDrag2,'added', function () {
        console.log('agregado');
        arrastrar.forEach(function (box,indice) {
            if (box.innerText === "") {
                contador[box.dataset.indice] = 0;
                console.log(indice);
            }
        });
    });


});