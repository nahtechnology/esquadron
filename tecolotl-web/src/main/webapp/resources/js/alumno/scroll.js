var time;

/*document.addEventListener("DOMContentLoaded",function (evt) {

    var mouse = document.getElementById('scrollTranscript');
    pageScroll();
    mouse.addEventListener("click",function (evt) {
        clearTimeout(time);
    }).mouseout(function() {
        pageScroll();
    });

});*/

function pageScroll() {
    var objDiv = document.getElementById("scrollTranscript");
    objDiv.scrollTop = objDiv.scrollTop + 1;
    //insertar los segundo directamente en funcion de la API de youtube.
    time = setTimeout('pageScroll()', 1000);
}

var videoPlayer = document.querySelector('.scrollFinal');
var player;
function onYouTubeIframeAPIReady() {
    console.log(videoPlayer, videoPlayer.getAttribute('id'));
    player = new YT.Player( videoPlayer, {
        height: '100%',
        width: '100',
        videoId: videoPlayer.getAttribute('id'),
        events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange
        }
    });
}

// 4. The API will call this function when the video player is ready.
function onPlayerReady(event) {
 console.log(event, 'Evento Listo!!');
}

// 5. The API calls this function when the player's state changes.
//    The function indicates that when playing a video (state=1),
//    the player should play for six seconds and then stop.
var done = false;
function onPlayerStateChange(event) {
    if (event.data == YT.PlayerState.PLAYING && !done) {
        console.log('Ocurrio el evento! ');
        pageScroll();
        done = true;
    }
}