$(document).ready(function () {
    //inizializzazione campo regioni per la ricerca
    getNextEvents();
});

function getNextEvents() {
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getnextevents"},
        success: function (data) {
            //alert(data);
            $("#yournext").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}
$(document).on("click", ".cancellaE", function () {
    form = $(this).closest("form");
    idEvento = form.find("#idEvento").val();
    $(this).closest("article").fadeOut("slow");
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "deleteEvento", idEvento: idEvento},
        success: function (data) {
            setTimeout(function () {
                alert("Evento cancellato!");
                getNextEvents();
                ;
            }, 1000);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
});

$(document).on("click", ".ritiratiE", function () {
    form = $(this).closest("form");
    idEvento = form.find("#idEvento").val();
    $(this).closest("article").fadeOut("slow");
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "withdrawEvento", idEvento: idEvento},
        success: function (data) {
            setTimeout(function () {
                alert("Partecipazione annullata!");
                getNextEvents();
            }, 1000);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
});