var googleUser = {};
var startApp = function () {
    gapi.load('auth2', function () {
        // Retrieve the singleton for the GoogleAuth library and set up the client.
        auth2 = gapi.auth2.init({
            client_id: '927846435147-pm1es5ii4p0h0ie6826t8chksniapmls.apps.googleusercontent.com',
            cookiepolicy: 'single_host_origin',
            // Request scopes in addition to 'profile' and 'email'
            //scope: 'additional_scope'
        });
    });
};



function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });

    form = $(document.createElement('form'));
    $(form).attr("action", "ServletController");
    $(form).attr("method", "post");
    input = $("<input>").attr("type", "hidden").attr("name", "action").val("logout");
    $(form).append($(input));
    $(form).submit();
}