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
                
                
                
                
                
                $("#searchevento").click(function(){
                    
                    //regione =document.getElementById("selectregione").value;
                    citta=document.getElementById("selectcitta").value;
                    provincia=document.getElementById("selectprovincia").value;
                    sport=document.getElementById("selectsport").value;
                    
                    $.ajax({
                    type: "POST",
                    url: "EventiController",
                    data: {action: "getsearchevento", /*regione:regione, */citta:citta, provincia:provincia, sport:sport},
                    success: function(data){
                        $("#seeevento").html(data);

                        
                    },
         
                    error: function (xhr, status, error) {
                                alert(error);
                    }
         
                    });
                    
                    
                });
                
                
                
                
                
                
                
                
                
                



});
            

