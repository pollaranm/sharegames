$(document).ready(function(){
    
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
    })
    
    
})
    

     
     



















































//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////function addEvento(){
//                
//               var form="";
//                var i;
//                
//                    form="action=null"+" id=formaddevento" +
//                         "Nome impianto: "+
//                         "<form> \n "+
//                         "<select name=\"impianti\">\n";
//  
//                    for(i=0; i<nomiimpianti.get; i++){
//                        
//                      form+="<option value="+nomiimpianti[i]+"</option>\n";
//                    }  
                    /*
                    form+="Id campo: ";
            
                    for(i=0; i<lc.size(); i++){
                    form+=
                        "<select name=\"campi\"> \n +"+
                        "<div id=\"campo\"><option value="+lc.get(i).getCampoPK().getIdcampo()+">"+lc.get(i).getCampoPK().getIdcampo()+"</option>\n</div>";
                    }
            
                    form+="Data: "+
                          "<select name=\"Data\"> \n "+
                          "<div id=\"Data\"><option value=21"+21+"</option>\n</div>";

                    form+="Ora: "+
                          "<select name=\"Ora\"> \n "+
                          "<div id=\"Ora\">"+22+"</option>\n</div>";
                    form+="Sport: "+
                          "<select name=\"Sport\"> \n "+
                          "<div id=\"sport\"><option value=22"+22+"</option>\n</div>";
    
                    form+="</form>";
                   */
                  
                  
//                  return form;
//            }
//                       document.getElementById("demo").innerHTML = addEvento();

            //document.getElementById("demo").innerHTML = addEvento();