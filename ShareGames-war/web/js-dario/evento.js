

//Questo contiene le funzione usate da Nico per l'utente IDENTICHE nel funzionamento ma con dei nomi diversi.
//Avevo inserito qui il contenuto attualmente presente in data.js perchè dovrebbe funzionare al caricamento della pagina personal
   function editaform() {
        
    $("#data").removeProp("readonly");
    $("#ora").removeProp("readonly");
    $("#idcampo").removeProp("readonly");
    $("#idimpianto").removeProp("readonly");
    $("#edit").text("Salva dati");
    $("#edit").attr("onclick", "aggiornaform()");

function aggiornaform() {


        $("#data").attr("readonly", "true");
        $("#ora").attr("readonly", "true");
        $("#idcampo").attr("readonly", "true");
        $("#idimpianto").attr("readonly", "true");
        $("#edit").text("Aggiorna il tuo evento");
        $("#edit").attr("onclick", "editaform()");
        $.ajax({
            url: "EventiController",
            type: 'POST',
            dataType: "html",
            data: {
                action: "aggiorna",
                idevento: $("#idevento").val(),
                data: $("#data").val(),
                ora: $("#ora").val(),
                idcampo: $("#idcampo").val(),
                idimpianto: $("#idimpianto").val()

            },
            success: function (data) {
                alert("Valori aggiornati con successo!");
            },
            error: function () {
                alert("Evento gia' presente, modifica nuovamente");
            }

            });
    }
}
    


