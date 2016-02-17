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
        provincia = document.getElementById("createProvincia").value;
        sport = document.getElementById("createSport").value;
        dataE = document.getElementById("createData").value;
        ora = document.getElementById("createOra").value;
        $.ajax({
            type: "POST",
            url: "EventiController",
            data: {
                action: 'searchFields',
                provincia: provincia,
                sport: sport,
                dataE: dataE,
                ora: ora
            },
            success: function (data) {
                $("#exploreResearch").html(data);
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
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

//Metodo in ascolto per la creazione di un evento
$(document).on("click", ".createE", function () {
    form = $(this).closest("form");
    idC = form.find("#idCampo").val();
    idI = form.find("#idImpianto").val();
    ora = $("#createOra").val();
    data = $("#createData").val();
    sport = $("#createSport").val();
    if (idC === "") {
        alert("Devi scegliere un campo in cui giocare!");
    } else {
        form.closest("div").fadeOut("slow");
        $.ajax({
            type: "POST",
            url: "EventiController",
            data: {
                action: "createEvento",
                idC: idC,
                idI: idI,
                ora: ora,
                data: data,
                sport: sport
            },
            success: function (data) {
                setTimeout(function () {
                    getNextEvents();
                    ;
                }, 1000);
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
    }

});

//Sciccheria grafica per la rimozione del placeholder nella select del campo
$(document).on("click", "#idCampo", function () {
    $(this).find('option:disabled').remove();
});
