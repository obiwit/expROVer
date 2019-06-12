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
    var rov_ip = (location.search.split('ip=')[1]||'').split('&')[0];
    $('#rov-stream').attr("src", 'http://' + rov_ip + ':8080/ozzz1.cgi');
});