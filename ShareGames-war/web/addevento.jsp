<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript"  src="js-dario/evento.js"></script> 
<script type="text/javascript" src="js-dario/data.js"></script>
    </head>
    <body>

        
        <form id="formevento">
            impianto: 
            <%=request.getSession().getAttribute("selectimpianto")%>;</br>
            campi:
            <select id="selectcampo" name="selectcampo"></select></br>
            <label>
                <span>
                    Data:
                </span>
                <script language="JavaScript">crea_data();</script></label><br>
                
                Ora:  <select id="selectora" name="selectora">
                        <option value="09">09</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                </select> : 00</br>
                    
            Sport: <select id="selectsport" name="selectsport"></select></br>
            
           
            <button type="button" id="aggiungievento">Aggiungi l'evento</button>
            <div id="eventoaggiunto"></div>
            
        </form>
            
    </body>
</html>
