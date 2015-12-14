var googleUser = {};
var startApp = function () {
    gapi.load('auth2', function () {
        // Retrieve the singleton for the GoogleAuth library and set up the client.
        auth2 = gapi.auth2.init({
            client_id: '511603926371-sr21hvrj9jute7pvpgh9son8br3qskkh.apps.googleusercontent.com',
            cookiepolicy: 'single_host_origin',
            // Request scopes in addition to 'profile' and 'email'
            //scope: 'additional_scope'
        });
        attachSignin(document.getElementById('buttonGoogle'));

    });
};

function attachSignin(element) {
    console.log(element.id);

    var profile;

    auth2.attachClickHandler(element, {},
            function (googleUser) {
                //document.getElementById('name').innerText = "Signed in: " +
                //        googleUser.getBasicProfile().getName();

                profile = googleUser.getBasicProfile();

                form1 = $(document.createElement('form'));
                $(form1).attr("action", "ServletController");
                $(form1).attr("method", "get");

                action1 = $("<input>").attr("type", "hidden").attr("name", "action").val("loginGoogle");
                name2 = $("<input>").attr("type", "hidden").attr("name", "name").val(profile.getName());
                id3 = $("<input>").attr("type", "hidden").attr("name", "id").val(profile.getId());
                email3 = $("<input>").attr("type", "hidden").attr("name", "email").val(profile.getEmail());
                url4 = $("<input>").attr("type", "hidden").attr("name", "url").val(profile.getImageUrl());

                $(form1).append($(action1));
                $(form1).append($(name2));
                $(form1).append($(id3));
                $(form1).append($(email3));
                $(form1).append($(url4));
                $(form1).submit();

                console.log("fine form");

            }, function (error) {
        alert(JSON.stringify(error, undefined, 2));
    });
}
