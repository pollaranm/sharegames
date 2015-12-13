<%-- 
    Document   : prezziario
    Created on : 8-dic-2015, 12.47.02
    Author     : Ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="js-ivan/prezziario.js"></script> 
        <title>JSP Page</title>
        
    </head>
    
    
    <body>
        
        <form id="fuffa" action="<%=request.getContextPath()%>/PrezziarioController">
            Impianto: 
            <%=request.getSession().getAttribute("selectimpianto")%></br>
            Campo:
            <select id="selectcampo" name="selectcampo">
            </select></br>
            <div id="selectprezzo" name="selectprezzo"/>
           </br>
        </form>
            
    </body>
</html>
