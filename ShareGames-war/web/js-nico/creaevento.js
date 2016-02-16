$(document).ready(function () {

    setupRegioniCreate();
    setupProvinceCreate();
    setupSportCreate();
    setupOraCreate();
    setupDataCreate("#createData");


    //Al cambio della regione nel form di creazione evento carica le
    //provincie nella selezione
    $("#createRegione").change(function () {
        idregione = document.getElementById("createRegione").value;
        $.ajax({
            type: "POST",
            url: "EventiController",
            data: {action: "getprovince", idregione: idregione},
            success: function (data) {
                $("#createProvincia").html(data);
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
    });

    $("#searchFields").click(function () {
        alert($('#createData').val());
    });

});

//Preimposta l'elenco delle regioni nel form per la ricerca
//e la creazione delle squadre
function setupRegioniCreate() {
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getregioni"},
        success: function (data) {
            $("#createRegione").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}

//Carica dinamicamente l'elenco delle provincie al cambio della regione.
//Al caricamento della pagina preimposta la regione selezionata con id=1
function setupProvinceCreate() {
    idregione = 1;
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getprovince", idregione: idregione},
        success: function (data) {
            $("#createProvincia").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}

//Precarica l'elenco degli sport selezionabili
function setupSportCreate() {
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getsport"},
        success: function (data) {
            $("#createSport").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}

//Preimposta la data per la creazione di un evento ad oggi
function setupDataCreate(_id) {
    var _dat = document.querySelector(_id);
    var hoy = new Date(),
            d = hoy.getDate(),
            m = hoy.getMonth() + 1,
            y = hoy.getFullYear(),
            data;
    if (d < 10) {
        d = "0" + d;
    }
    if (m < 10) {
        m = "0" + m;
    }
    data = y + "-" + m + "-" + d;
    _dat.value = data;
}

//Precarica l'elenco degli orari selezionabili
function setupOraCreate() {
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getora"},
        success: function (data) {
            $("#createOra").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}

