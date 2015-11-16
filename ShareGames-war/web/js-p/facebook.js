window.fbAsyncInit = function () {
    FB.init({
        appId: '769314139846147',
        status: true, // check login status
        cookie: true, // enable cookies to allow the server to access the session
        xfbml: true, // parse XFBML
        oauth: true, // enables OAuth 2.0
        version: 'v2.4'
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

function fb_logout() {


    FB.logout();
    form = $(document.createElement('form'));
    $(form).attr("action", "ServletController");
    $(form).attr("method", "post");
    input = $("<input>").attr("type", "hidden").attr("name", "action").val("logout");
    $(form).append($(input));
    $(form).submit();



}



            