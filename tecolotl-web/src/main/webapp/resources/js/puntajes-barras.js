window.onload = function()  {
    var puntaje = document.querySelectorAll('.califica');
    var puntajes = [];

    puntaje.forEach(function (score,index) {
        puntajes[index] = score.innerText.split("\n");
    });

    console.log(puntajes);
};
