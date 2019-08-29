var videos = [];
document.addEventListener("DOMContentLoaded", function (ev) {
    videos = document.querySelectorAll('.uk-card');
    videos.forEach(function (value, index, array) {
        var llamadaAjax = new XMLHttpRequest();
        var parametros = new URLSearchParams();
        parametros.append("id", value.querySelector('.video').textContent);
        parametros.append("key", "AIzaSyAmcLP361O-YxnYqJmUhCgA97WuFYab2E8");
        parametros.append("part", "snippet,contentDetails,statistics,status");
        llamadaAjax.open('GET', 'https://www.googleapis.com/youtube/v3/videos'.concat('?').concat(parametros.toString()), true);
        llamadaAjax.onreadystatechange = function (ev1) {
            if (llamadaAjax.readyState == XMLHttpRequest.DONE) {
                var papa = value.querySelector('.video').parentElement;
                if (llamadaAjax.status == 200) {
                    var respuesta = JSON.parse(llamadaAjax.response);
                    console.log(respuesta);
                    if (respuesta.pageInfo.totalResults == 0) {
                        videoError(papa);
                    } else {
                        var contenedor = document.createElement('div');
                        contenedor.classList.add('uk-inline');
                        contenedor.setAttribute('uk-lightbox', '');
                        var imagen = document.createElement('img');
                        imagen.setAttribute('src', respuesta.items[0].snippet.thumbnails.medium.url);
                        contenedor.appendChild(imagen);
                        var videoHipervinculo = document.createElement('a');
                        videoHipervinculo.classList.add('uk-position-center');
                        videoHipervinculo.setAttribute('uk-icon', 'icon: youtube; ratio: 3');
                        videoHipervinculo.setAttribute('href', 'https://www.youtube.com/watch?v='.concat(value.querySelector('.video').textContent));
                        videoHipervinculo.setAttribute('data-caption', respuesta.items[0].snippet.title);
                        contenedor.appendChild(videoHipervinculo);
                        papa.removeChild(papa.firstElementChild);
                        papa.appendChild(contenedor);
                    }
                } else {
                    console.error('llamadaAjax.readyState' + llamadaAjax.readyState + ' llamadaAjax.status:' + llamadaAjax.status);
                    videoError(papa);
                }
            }
        };
        llamadaAjax.send();
    });

    function videoError(papa) {
        papa.removeChild(papa.firstElementChild);
        papa.removeChild(papa.firstElementChild);
        papa.querySelector('.video-error').style.display = 'block';
    }
});