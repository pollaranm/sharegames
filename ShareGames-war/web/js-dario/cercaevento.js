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



    $("#searchEvento").click(function () {
        provincia = document.getElementById("searchProvincia").value;
        sport = document.getElementById("searchSport").value;
        $.ajax({
            type: "POST",
            url: "EventiController",
            data: {action: "searchEvento", prov: provincia, sport: sport},
            success: function (data) {
                /*alert("!!!");
                alert(data);*/
                $("#seeevento").html(data);
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
    });
    
    
    $(document).on("click", ".partecipa", function(){

        alert("Benvenuto! Grazie per partecipare all'evento!");

    });
    
    
    
    

});


