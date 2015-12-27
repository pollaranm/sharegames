
   function editaform() {
        
    $("#data").removeProp("readonly");
    $("#ora").removeProp("readonly");
    $("#idcampo").removeProp("readonly");
    $("#idimpianto").removeProp("readonly");
    $("#edit").text("Salva dati");
    $("#edit").attr("onclick", "aggiornaform()");

}

        /*$("#edit").click(function(){
     
                var oggi;

                oggi=new Date();
                var giorno=oggi.getDate();
                var mese=oggi.getMonth()+1;
                var anno=oggi.getFullYear();

                if(mese<10) mese="0"+mese;
                if(giorno<10)giorno="0"+mese;
                var data=giorno+"-"+mese + "-"+anno;

                $("#data").append("<input id=data type=date name=data min=",data," required/>");

        });*/


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
    


