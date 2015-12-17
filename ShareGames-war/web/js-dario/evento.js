

    

    function editaform() {
    $("#data").attr('readonly', 'false');
    $("#ora").attr('readonly', 'false');
    $("#idcampo").attr('readonly', 'false');
    $("#idimpianto").attr('readonly', 'false');
    $("#idevento").attr('readonly', 'false');
    $("#modificaevento").attr("onclick", "aggiornaform()");
}

function aggiornaform() {

    $("#data").attr("readonly", "true");
    $("#ora").attr("readonly", "true");
    $("#idcampo").attr("readonly", "true");
    $("#idimpianto").attr("readonly", "true");
    $("#idevento").attr("readonly", "true");
    
    $("#modificaevento").text("Aggiorna i tuoi dati");
    $("#modificaevento").attr("onclick", "editaform()");
    $.ajax({
        url: "EventiController",
        type: 'POST',
        dataType: "html",
        data: {
            /*action: "aggiorna",
            data: $("#data").val(),
            ora: $("#ora").val(),
            idcampo: $("#idcmpo").val(),
            idimpianto: $("#idimpianto").val(),
            idevento: $("#idevento").val()*/
        },
        /*success: function (data) {
            alert("Valori aggiornati con successo!");
            //$("#menu_profileName").html("Benvenuto<br>" + $("#namePersonal").val());
        },*/
        error: function (xhr, status, error) {
            alert(error);
        }

        });
    }


