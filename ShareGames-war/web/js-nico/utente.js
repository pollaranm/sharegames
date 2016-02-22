//Effettua il loguot dell'utente richiamando la funzione nella servlet
function logoutUtente() {
    form = $(document.createElement('form'));
    $(form).attr("action", "ServletController");
    $(form).attr("method", "post");
    input = $("<input>").attr("type", "hidden").attr("name", "action").val("logout");
    $(form).append($(input));
    $(form).submit();
}

//Rende editabile il form contenente le informazioni personali
function editableForm() {
    $("#namePersonal").removeProp("readonly");
    $("#phonePersonal").removeProp("readonly");
    $("#emailPersonal").removeProp("readonly");
    $("#namePersonal").attr("style", "color:green;");
    $("#phonePersonal").attr("style", "color:green;");
    $("#emailPersonal").attr("style", "color:green;");
    $("#buttonPersonal").text("Salva i nuovi dati");
    $("#buttonPersonal").attr("onclick", "updateForm()");
}

//Aggiorna i dati personali modificati nel form utente
function updateForm() {
    $("#namePersonal").attr("style", "");
    $("#phonePersonal").attr("style", "");
    $("#emailPersonal").attr("style", "");
    $("#namePersonal").attr("readonly", "true");
    $("#phonePersonal").attr("readonly", "true");
    $("#emailPersonal").attr("readonly", "true");
    $("#buttonPersonal").text("Aggiorna i tuoi dati");
    $("#buttonPersonal").attr("onclick", "editableForm()");
    $.ajax({
        url: "UtenteController",
        type: 'POST',
        dataType: "html",
        data: {
            action: "updateUtente",
            name: $("#namePersonal").val(),
            email: $("#emailPersonal").val(),
            phone: $("#phonePersonal").val()
        },
        success: function (data) {

//            alertify.confirm("This is a confirm dialog", function (e) {
//                if (e) {
//                    alertify.success("You've clicked OK");
//                } else {
//                    alertify.error("You've clicked Cancel");
//                }
//            });
            alertify.success("Valori aggiornati!", "", 0);
            $("#menu_profileName").html("Benvenuto<br>" + $("#namePersonal").val());
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    });
}
