var userInfo;
firebase.auth().onAuthStateChanged(function(user) {
    if (!user) {
        // User is not signed in, redirect to login screen
        window.location.href = '/login.html';
    } else {
        userInfo = user;
        getROVS(user);
    }
});

$(function () {});

function getROVS(user) {
    var db = firebase.firestore();
    var docRef = db.collection("users").doc(userInfo.email);
    var storage = firebase.storage();

    docRef.get().then(function(doc) {
        if (doc.exists) {
            for (rov_name in doc.data()) {
                var rov_ip = doc.data()[rov_name];

                var storageRef = storage.ref();
                storageRef.child('images/'+md5(rov_name)+'.jpg').getDownloadURL().then(function(url) {

                    var newRovHTML = 
                        "<div class=\"card\" style=\"width: 18rem;\" id=\"" + rov_name +
                                    "\" onclick='location.href=\"/cockpit.html?ip=" + rov_ip + "\"'> \
                            <img class=\"card-img-top\" src=\"" + url + "\" alt=\""+rov_name+"\"> \
                            <div class=\"card-body\"> \
                                <h5 class=\"card-title\">" + rov_name + "</h5> \
                                <p class=\"card-text\">" + rov_ip + "</p> \
                            </div> \
                        </div>";

                    $('div#rovlist').append(newRovHTML);
                });
            }
        }
    }).catch(function(error) {
        console.log("Error getting document:", error);
    });

}