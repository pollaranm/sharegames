$(document).ready(function(){

    
    //Aggiungere un campo
    $("#selectimpianto").click(function(){
        
        impianto=document.getElementById("selectimpianto").value;
        
 

    $.ajax({
         url : "EventiController",
         type: 'POST',
         dataType: "html",
         data: {action:"getimpianto",impianto:impianto},
         
         
         success: function(data){
                  $("#selectcampo").html(data);
         },
         
            error: function (xhr, status, error) {
            alert(error);
        }
         
     });
    });
    
    
    //quando aggiungo evento visualizzo lo sport dopo la selezione del campo
    //Aggiungere un evento
    $("#selectcampo").click(function(){
        
        campo=document.getElementById("selectcampo").value;
        impianto=document.getElementById("selectimpianto").value;
        
    $.ajax({
         url : "EventiController",
         type: 'POST',
         dataType: "html",
         data: {action:"getsport",campo:campo, impianto:impianto},
         
         
         success: function(data){
                  $("#selectsport").html(data);
         },
         
            error: function (xhr, status, error) {
            alert(error);
        }
         
     });
    });
            
            
            
                  //mostra listaeventi utente dopo click su bottone
    $("#eventibyuser").on('click', function(){
        
    $.ajax({
         url : "EventiController",
         type: 'POST',
         dataType: "html",
         data: {action:"geteventibyuser"},
         
         
         success: function(data){
                  $("#listaeventi").html(data);
         },
         
            error: function (xhr, status, error) {
            alert(error);
        }
    });
    
    });
    
    
    
        
   
        //agigungere un evento
        $("#insertevento").on('click', function(){
        
        campo=document.getElementById("selectcampo").value;
        impianto=document.getElementById("selectimpianto").value;

        data=document.getElementById("selectdata").value;
        ora=document.getElementById("selectora").value;
        minuti=document.getElementById("selectminuti").value;
        sport=document.getElementById("selectsport").value;

        
    $.ajax({
        
         url : "EventiController",
         type: 'POST',
         dataType: "html",
         data: {action:"insertevento",campo:campo, impianto:impianto, data:data, ora:ora, minuti:minuti, sport:sport},
         
         
         success: function(data){
                  $("#eventoaggiunto").html(data);
                  
         },
         
            error: function (xhr, status, error) {
            alert(error);
        }
         
     });
    });
    
    
    
    
    //rimuovere evento
        $(document).on("click", ".rimuovievento ", function(){

        idevento=$(this).attr("value");
        
        
    $.ajax({
        
         url : "EventiController",
         type: 'POST',
         dataType: "html",
         data: {action:"rimuovi", idevento:idevento},
         
         
         success: function(data){
                  $("#eventorimosso").html(data);
                  alert("OK");
         },
         
            error: function (xhr, status, error) {
            alert(error);
        }
         
     });
    });
    
    
        //Aggiungere la l'ora dopo la selezione della data
    $("#selectdata").click(function(){
        
    $.ajax({
         url : "EventiController",
         type: 'POST',
         dataType: "html",
         data: {action:"selectora"},
         
         
         success: function(data){
                  $("#selectora").html(data);
         },
         
            error: function (xhr, status, error) {
            alert(error);
        }
         
     });
    });
    
    
        //Aggiungere i minuti dopo avere seleziona l'ora
    $("#selectora").click(function(){
        
    $.ajax({
         url : "EventiController",
         type: 'POST',
         dataType: "html",
         data: {action:"selectminuti"},
         
         
         success: function(data){
                  $("#selectminuti").html(data);
         },
         
            error: function (xhr, status, error) {
            alert(error);
        }
         
     });
    });
    
    
        
       $.ajax({
         url : "EventiController",
         type: 'POST',
         dataType: "html",
         data: {action:"caricaimpianti"},
         
         
         success: function(data){
                  $("#selectimpianto").html(data);
         },
         
            error: function (xhr, status, error) {
            alert(error);
        }
         
     });
    
    
    

    
    
 
});
    

