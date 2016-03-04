$(document).ready(function () {
    getMyTeamSection();
    
    //Al cambio della regione nel form di ricerca squadre carica le
    //provincie nella selezione
    $(document).on("change", "#geo1", function () {
        idregione = document.getElementById("geo1").value;
        $.ajax({
            type: "POST",
            url: "EventiController",
            data: {action: "getprovince", idregione: idregione},
            success: function (data) {
                $("#geo2").html(data);
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
    });

    //Al cambio della regione nel form di creazione squadre carica le
    //provincie nella selezione
    $(document).on("change", "#geo3", function () {
        idregione = document.getElementById("geo3").value;
        $.ajax({
            type: "POST",
            url: "EventiController",
            data: {action: "getprovince", idregione: idregione},
            success: function (data) {
                $("#geo4").html(data);
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
    });

});

//Preimposta l'elenco delle regioni nel form per la ricerca
//e la creazione delle squadre
function setupRegioniTeam() {
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getregioni"},
        success: function (data) {
            $("#geo1").html(data);
            $("#geo3").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}

//Carica dinamicamente l'elenco delle provincie al cambio della regione.
//Al caricamento della pagina preimposta la regione selezionata con id=1
function setupProvinceTeam() {
    idregione = 1;
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getprovince", idregione: idregione},
        success: function (data) {
            $("#geo2").html(data);
            $("#geo4").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}

//Precarica l'elenco degli sport selezionabili
function setupSportTeam() {
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getsport"},
        success: function (data) {
            $("#team_sport").html(data);
            $("#team_sport2").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}
//Recupera le informazioni relative alla squadra dell'utente
//e costruisce il form contententi tali informazioni
function getMyTeamSection() {
    $.ajax({
        type: 'POST',
        url: 'SquadraController',
        data: {action: 'getMyTeamSection'},
        success: function (data) {
            $("#myTeamSection").html(data);
            setupRegioniTeam();
            setupProvinceTeam();
            setupSportTeam();
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}

//Funzione richiamata per effettuare la rierca di squadre con i parametri di 
//ricerca indicati nel form
function retrieveTeams() {
    $.ajax({
        url: 'SquadraController',
        type: 'POST',
        dataType: 'html',
        data: {
            action: 'findTeams',
            prov: $('#geo2').val(),
            sport: $('#team_sport').val()
        },
        success: function (data) {
            $('#team_div_res').html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}

//l'on si mette in ascolto di eventi ANCHE su oggetti generati dinamicamente
//dopo la creazione del DOM

//Funzione che permette di ritornare alla creazione di una squadra nel caso 
//la ricerca non dia risultati
$(document).on("click", "#returnCreateBtn", function () {
    $.ajax({
        url: 'SquadraController',
        type: 'POST',
        dataType: 'html',
        data: {action: 'returnCreateTeam'},
        success: function (data) {
            $('#team_div_res').html(data);
            setupRegioniTeam();
            setupProvinceTeam();
            setupSportTeam();
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
});

//In seguito al click sul tasto di creazione di una squadra richiama
//la servlet e la crea nel database
$(document).on("click", "#createTeamBtn", function () {
    $.ajax({
        url: 'SquadraController',
        type: 'POST',
        dataType: 'html',
        data: {
            action: 'createTeam',
            prov: $('#geo4').val(),
            sport: $('#team_sport2').val(),
            newname: $('#newteamname').val()
        },
        success: function (data) {
            alertify.success(data, "", 0);
            getMyTeamSection();
            getMyPersonal();
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
});

$(document).on("click", ".joinTeam", function () {
    form = $(this).closest("form");
    idsquadra = form.find("#idsquadra").val();
    namesquadra = form.find("#namesquadra").val();
    $.ajax({
        url: 'SquadraController',
        type: 'POST',
        dataType: 'html',
        data: {
            action: 'joinTeam',
            idsquadra: idsquadra,
            namesquadra: namesquadra
        },
        success: function (data) {
            alertify.log("Benvenuto in squadra!", "", 0);
            getMyTeamSection();
            getMyPersonal();
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
});

$(document).on("click", "#leaveTeamBtn", function () {
    $.ajax({
        url: 'SquadraController',
        type: 'POST',
        dataType: 'html',
        data: {
            action: 'leaveTeam'
        },
        success: function (data) {
            alertify.log("Squadra abbandonata...", "", 0);
            getMyTeamSection();
            getMyPersonal();
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
});