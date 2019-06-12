firebase.auth().onAuthStateChanged(function(user) {
    console.log("hi, something changed");
    console.log(user);

    if (user) {
        // User is signed in, redirect to main screens
        window.location.href = '/index.html';
    } else {

    }
});
    
$(function () {
    console.log(firebase);
    console.log(firebase.auth());

    $("form").submit(function(e){
        return false;
    });

    $('#login').click(function() {
        var email = $('#inputEmail').val();
        var password = $('#inputPassword').val();

        console.log("clicked sign in with email");
        console.log(email);
        console.log(password);

        firebase.auth().signInWithEmailAndPassword(email, password).then(console.log("can you see me?"))
        .catch(function(error) {
            console.log("errors! errors everywhere!");
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
            console.log(errorCode);
            console.log(errorMessage);
          });
        console.log("supposedly sent the request");
    });
    $('#g_login').click(function() {
        var provider = new firebase.auth.GoogleAuthProvider();
        provider.addScope('https://www.googleapis.com/auth/contacts.readonly');
        firebase.auth().signInWithPopup(provider).then(function(result) {
            // This gives you a Google Access Token. You can use it to access the Google API.
            var token = result.credential.accessToken;
            // User is signed in, redirect to main screens
            // window.location.href = '/index.html';
          }).catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
          });
    });

    $('#sign_up').click(function() {
        firebase.auth().createUserWithEmailAndPassword($('#inputEmail').val(), $('#inputPassword').val())
        .catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
          });
    });
    $('#g_signup').click(function() {
        var provider = new firebase.auth.GoogleAuthProvider();
        provider.addScope('https://www.googleapis.com/auth/contacts.readonly');
        firebase.auth().signInWithPopup(provider).then(function(result) {
            // This gives you a Google Access Token. You can use it to access the Google API.
            var token = result.credential.accessToken;
            // User is signed in, redirect to main screens
            window.location.href = '/index.html';
          }).catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
          });
    });
    
});