var conta = [];
var cadena = [];
var primera = [], copy;
var drag;
var cajaAnswer;

document.addEventListener('DOMContentLoaded', function (evt) {
    drag = UIkit.util.$$('.trancript-contenedor .respuesta-transcript');
    for (var inx = 0;inx < drag.length;inx++ ){
        conta.push(0);
    }
    UIkit.util.on(drag,'added',function (texto) {
        console.log(texto.target.dataset.indice);

        drag.forEach(function (caja) {
            if(caja.dataset.indice === texto.target.dataset.indice ){
                conta[caja.dataset.indice]++;
                if (conta[caja.dataset.indice] === 1){
                    primera[caja.dataset.indice] = drag[texto.target.dataset.indice].querySelector('.respuesta-drag').textContent;
                }
                if(conta[caja.dataset.indice] === 2){
                    var textos = drag[texto.target.dataset.indice].querySelectorAll('.respuesta-drag');
                    console.log(textos);
                    var cajaTexto = drag[texto.target.dataset.indice];
                    var cajaResp = document.querySelector('.contenedor-respuesta');
                    var caja2 = document.createElement('span');
                    var caja3 = document.createElement('span');
                    textos.forEach(function (word) {
                        var palabra = word.innerHTML;
                        if (palabra !== primera[caja.dataset.indice]){
                            caja2.innerHTML = palabra;
                            while (cajaTexto.hasChildNodes()){
                                cajaTexto.removeChild(cajaTexto.firstChild);
                            }
                            caja3.innerText = primera[caja.dataset.indice];
                            caja2.classList.add('respuesta-drag');
                            cajaTexto.appendChild(caja2);
                            caja3.classList.add('respuesta-drag');
                            cajaResp.appendChild(caja3);


                            console.log(primera[caja.dataset.indice]);

                        }

                    });
                    conta[caja.dataset.indice] = 1;
                    primera[caja.dataset.indice] = drag[texto.target.dataset.indice].textContent;
                }
            }
        });


        // if (conta === 1){
        //     primera = document.querySelectorAll('.trancript-contenedor .respuesta-transcript > .respuesta-drag');
        //     primera.forEach(function (texto) {
        //         cadena.push(texto.innerHTML);
        //     })
        // }

        // if(conta === 2){
        //     var textos = document.querySelectorAll('.trancript-contenedor p:nth-child(1) .respuesta-transcript > .respuesta-drag');
        //     console.log(textos);
        //     var cajaTexto = document.querySelector('.trancript-contenedor p:nth-child(1) .respuesta-transcript');
        //     var caja = document.createElement('span');
        //     textos.forEach(function (texto) {
        //         var palabra = texto.innerHTML;
        //         cadena.push(texto.innerHTML);
        //         if (palabra !== primera){
        //             caja.innerHTML = palabra;
        //             console.log(primera);
        //             while (cajaTexto.hasChildNodes()){
        //                 cajaTexto.removeChild(cajaTexto.firstChild);
        //             }
        //             caja.classList.add('respuesta-drag');
        //             cajaTexto.appendChild(caja);
        //             console.log(primera);
        //         }
        //     });
        //     conta = 1;
        //     primera = document.querySelector('.trancript-contenedor p:nth-child(1) .respuesta-transcript > .respuesta-drag').textContent;
        // }
    });
});