<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js-dario/evento.js"></script> 
    </head>
    <body>
        
        
        <form id="formevento" action="<%=request.getContextPath()%>/EventiController">
            impianto: 
            <%=request.getSession().getAttribute("selectimpianto")%>;</br>
            campi:
            <select id="selectcampo" name="selectcampo">
            </select></br>
            
        </form>
            
    </body>
</html>
