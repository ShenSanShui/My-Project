$(function () {//
    $(window).scroll(function () {
        if ($(window).scrollTop() >= 50) {
            $('#btn_top').fadeIn();
        }
        else {
            $('#btn_top').fadeOut();
        }
    });
});
$('#btn_top').click(function () {
    $('html,body').animate({ scrollTop: 0 }, 500);
});
