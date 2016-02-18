$(document).ready(function () {
    getStorico();
});

//recupera gli eventi passati giocati
function getStorico() {
    $.ajax({
        type: "POST",
        url: "ListaeventiutenteController",
        data: {action: "getstorico"},
        success: function (data) {
            $("#storico").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}