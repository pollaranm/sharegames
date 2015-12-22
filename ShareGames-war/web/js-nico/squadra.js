/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getregioni"},
        success: function (data) {
            $("#geo1").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });

    $.ajax({
        type: "POST",
        url: "EventiController",
        data: {action: "getsport"},
        success: function (data) {
            $("#team_sport").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });


    $("#geo1").change(function () {
        idregione = document.getElementById("geo1").value;
        $.ajax({
            type: "POST",
            url: "EventiController",
            data: {action: "getprovincia", idregione: idregione},
            success: function (data) {
                $("#geo2").html(data);
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
    });

});

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
            //window.alert("Valori aggiornati con successo!");
            $('#team_div_res').html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }

    });
}

