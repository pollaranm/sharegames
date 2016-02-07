$(document).ready(function () {
    //inizializzazione campo regioni per la ricerca
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getregioni"},
        success: function (data) {
            $("#searchRegione").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });

    //inizializzazione campo provincia per la ricerca
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getprovince", idregione: 1},
        success: function (data) {
            $("#searchProvincia").html(data);

        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });

    //inizializzazione campo sport per la ricerca
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getsport"},
        success: function (data) {
            $("#searchSport").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });

    //modifica dinamica delle provincie selezionabili al cambiare della regione
    $("#searchRegione").change(function () {
        idregione = document.getElementById("searchRegione").value;
        $.ajax({
            type: "POST",
            url: "EventiController",
            data: {action: "getprovince", idregione: idregione},
            success: function (data) {
                $("#searchProvincia").html(data);
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
    });

    //effettua la ricerca degli eventi per la sezione "Cerca un evento"
    $("#searchEvento").click(function () {
        provincia = document.getElementById("searchProvincia").value;
        sport = document.getElementById("searchSport").value;
        $.ajax({
            type: "POST",
            url: "EventiController",
            data: {action: "searchEvento", prov: provincia, sport: sport},
            success: function (data) {
                $("#seeevento").html(data);
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
    });
});

//Al click sul tasto partecipa avvia la procedura di registrazione al'evento,
//andando a creare una registrazione per l'utente ed incrementanto il numero di
//partecipanti all'evento
$(document).on("click", ".partecipa", function () {
    form = $(this).closest("form");
    idEvento = form.find("#idEvento").val();
    $(this).closest("article").fadeOut("slow");
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "joinEvento", idEvento: idEvento},
        success: function (data) {
            setTimeout(function () {
                alert("Partecipazione registrata!");
                location.reload();
                ;
            }, 1000);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
});

