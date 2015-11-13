window.fbAsyncInit = function () {
    FB.init({
        appId: '769314139846147',
        status: true, // check login status
        cookie: true, // enable cookies to allow the server to access the session
        xfbml: true, // parse XFBML
        oauth: true, // enables OAuth 2.0
        version: 'v2.4'
    });

    FB.getLoginStatus(function (response) {

        if (response.status === 'connected') {

            FB.api('/me', 'GET', {"fields": "id,name,picture{url}"}, function (response) {

                form = $(document.createElement('form'));
                $(form).attr("action", "ServletController");
                $(form).attr("method", "post");

                action = $("<input>").attr("type", "hidden").attr("name", "action").val("loginFacebook");
                name = $("<input>").attr("type", "hidden").attr("name", "name").val(response.name);
                id = $("<input>").attr("type", "hidden").attr("name", "id").val(response.id);
                email = $("<input>").attr("type", "hidden").attr("name", "email").val(response.email);
                url = $("<input>").attr("type", "hidden").attr("name", "url").val(response.picture.data.url);

                $(form).append($(action));
                $(form).append($(name));
                $(form).append($(id));
                $(form).append($(email));
                $(form).append($(url));
                $(form).submit();
            });


        } else if (response.status === 'not_authorized') {
            fb_login();
        } else {

        }
    });



};

// Load the SDK Asynchronously
(function (d) {
    var js, id = 'facebook-jssdk';
    if (d.getElementById(id)) {
        return;
    }
    js = d.createElement('script');
    js.id = id;
    js.async = true;
    js.src = "//connect.facebook.net/it_IT/all.js";
    d.getElementsByTagName('head')[0].appendChild(js);
}(document));

function fb_login() {


    FB.login(function (response) {

        if (response.authResponse) {
            access_token = response.authResponse.accessToken; //get access token
            user_id = response.authResponse.userID; //get FB UID

            FB.api('/me', 'GET', {"fields": "id,name,picture{url}"}, function (response) {

                form = $(document.createElement('form'));
                $(form).attr("action", "ServletController");
                $(form).attr("method", "post");

                input = $("<input>").attr("type", "hidden").attr("name", "action").val("loginFacebook");
                input1 = $("<input>").attr("type", "hidden").attr("name", "name").val(response.name);
                input2 = $("<input>").attr("type", "hidden").attr("name", "id").val(user_id);
                input3 = $("<input>").attr("type", "hidden").attr("name", "email").val(response.email);
                url = $("<input>").attr("type", "hidden").attr("name", "url").val(response.picture.data.url);

                $(form).append($(input));
                $(form).append($(input1));
                $(form).append($(input2));
                $(form).append($(input3));
                $(form).append($(url));
                $(form).submit();

            });


        } else {
            //user hit cancel button
            console.log('User cancelled login or did not fully authorize.');

        }
    }, {
        scope: 'public_profile,email'
    });



}



            