$(document).ready(function(){

              
                $.ajax({
                    type: "POST",
                    url: "EventiController",
                    data: {action: "getregioni"},
                    success: function(data){
                        $("#selectregione").html(data);
                    },
         
                    error: function (xhr, status, error) {
                        alert(error);
                    }
         
                });
                
                $("#selectregione").click(function(){
                    
                    idregione =document.getElementById("selectregione").value;
                    
                    $.ajax({
                    type: "POST",
                    url: "EventiController",
                    data: {action: "getcitta", idregione:idregione},
                    success: function(data){
                        $("#selectcitta").html(data);
                        
                    },
         
                    error: function (xhr, status, error) {
                                alert(error);
                    }
         
                    });
                    
                    
                });
                
                
                 $("#selectcitta").click(function(){
                    
                    citta =document.getElementById("selectcitta").value;
                    
                    
                    $.ajax({
                    type: "POST",
                    url: "EventiController",
                    data: {action: "getprovincia", citta:citta},
                    success: function(data){
                        $("#selectprovincia").html(data);
                        
                    },
         
                    error: function (xhr, status, error) {
                                alert(error);
                    }
         
                    });
                    
                    
                });
                
                
                
                $("#selectprovincia").click(function(){
                    

                    $.ajax({
                    type: "POST",
                    url: "EventiController",
                    data: {action: "getsport"},
                    success: function(data){
                        $("#selectsport").html(data);
                        
                    },
         
                    error: function (xhr, status, error) {
                                alert(error);
                    }
         
                    });
                    
                    
                });
                
                



});
            

