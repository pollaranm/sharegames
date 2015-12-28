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
            $("#geo3").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
    
    $.ajax({
        type: 'POST',
        url: 'SquadraController',
        data: {action: 'getMyTeam'},
        success: function(data) {
            $("#myTeamForm").html(data);
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
    
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
    $("#geo1").change(function () {
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
    $("#geo3").change(function () {
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
    
    $("#createTeamBtn").click(function () {
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
                window.alert(data);
                location.reload();
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

