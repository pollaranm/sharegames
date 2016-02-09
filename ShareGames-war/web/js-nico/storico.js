$(document).ready(function () {
//inizializzazione campo regioni per la ricerca
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getstorico"},
        success: function (data) {
            //alert(data);
            $("#storico").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
});
