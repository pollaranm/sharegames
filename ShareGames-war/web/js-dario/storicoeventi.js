$(document).ready(function () {

    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getstoricoeventi"},
        success: function (data) {
            $("#storico").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
});

