var time;

document.addEventListener("DOMContentLoaded",function (evt) {

    var mouse = document.getElementById('scroll');
    pageScroll();
    mouse.addEventListener("click",function (evt) {
        clearTimeout(time);
    }).mouseout(function() {
        pageScroll();
    });

});

function pageScroll() {
    var objDiv = document.getElementById("scroll");
    objDiv.scrollTop = objDiv.scrollTop + 1;

    time = setTimeout('pageScroll()', 100);
}
