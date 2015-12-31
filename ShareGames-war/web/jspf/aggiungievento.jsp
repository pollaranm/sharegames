<%-- 
    Document   : aggiungievento
    Created on : 31-dic-2015, 13.25.27
    Author     : Dario Gallo <your.name at your.org>
--%>

<%@ page pageEncoding="UTF-8" %>

<%
    HttpSession s = request.getSession(); // metodo dell’oggetto request
    String impianti=(String)s.getAttribute("selectimpianto");
%>

<section id="nuovoevento" class="three">
    <div class="container">

        <header>
            <h2>Aggiungi evento</h2>
        </header>

        <a href="#" class="image featured"><img src="images/pic08.jpg" alt="" /></a>

        <div id="addevento">
                <div class="row">
                    <div class="4u">
                        <article class="item">

                            <h3>Nuovo evento</h3>
                            <header>
                                <h1>
                                    Dettagli evento:
                                </h1>
                                <ul style="text-align: left; margin-left: 5%">
                                    
                                    
            <div id='aggiungievento'>
                                    
                <form onsubmit='return false'>
                    
                    <div class="col-3">
                        <label>Evento numero: (automatico) </label>
                    </div>
                    
                    <div class="col-3">
                        <label>Impianto: <%=impianti%></label>
                    </div>
                    
                    <div class="col-3">
                        <label>Campo: <select id="selectcampo" name="selectcampo" tabindex="1"></select></label>
                    </div>
                    
                    <div class="col-3">
                        <label>Data: <select id="selectdata" name="selectdata" tabindex="1"></select></label>
                    </div>
                    
                    <div class="col-3">
                        <label>Ora: <select id="selectora" name="selectora" tabindex="1"></select></label> : <select id="selectora" name="selectminuti" tabindex="1"></select>
                    </div>
                    
                    <div class="col-3">
                        <label>Sport: <select id="selectsport" name="selectsport" tabindex="1"></select></label>
                    </div>
                                   
                    <img style="width:94%;height:auto" src="http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|Corso+regina+margherita+221+10144+torino&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4"></img>
                                    
                                    
                      <div class="col-submit">
                         <button class="submitbtn" id="nuovoevento">Aggiungi evento</button>
                      </div>
                                    
                </form>
             </div>
                                    
            </ul>

        </header>
    </article>

    </div>
   </div>
  </div>
        
 </div>
</section>
