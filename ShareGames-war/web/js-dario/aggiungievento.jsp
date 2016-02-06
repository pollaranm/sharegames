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
                <form name="nuovoevento">
                    
                            <select id="selectimpianto">
                              
                            </select></br>

                           <select id="selectcampo">
                           <option value="" disabled selected hidden>Scegli campo</option>     
                           </select></br>
                           
                           <div id="data"></div>


                           <select id="selectora">
                           <option value="" disabled selected hidden>Ora</option>
                           </select></br>

                           <select id="selectminuti">
                           <option value="" disabled selected hidden>Minuti</option>
                           </select></br>

                           <select id="selectsport">
                           <option value="" disabled selected hidden>Sport</option>
                           </select></br>    
                
  
                    
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




