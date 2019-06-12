var userInfo;
firebase.auth().onAuthStateChanged(function(user) {
    if (!user) {
        // User is not signed in, redirect to login screen
        window.location.href = '/login.html';
    } else {
        userInfo = user;
    }
});

$(function () {
    function updateHeading() {
        $('#heading img').each(function() {
            var deg = 360 - ($(this).data('rotate') || 0);
            var rotate = 'rotate(' + deg + 'deg)';
            $(this).css({ 
                '-webkit-transform': rotate,
                '-moz-transform': rotate,
                '-o-transform': rotate,
                '-ms-transform': rotate,
                'transform': rotate 
            }); 
        });
    }

    updateHeading();

    var rov_ip = (location.search.split('ip=')[1]||'').split('&')[0];
    $('#rov-stream').attr("src", 'http://' + rov_ip + ':8080/ozzz1.cgi');

});

c