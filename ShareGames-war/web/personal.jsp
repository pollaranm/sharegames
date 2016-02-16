<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Sharegames</title>

        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <script src="js-p/jquery.min-p.js"></script>
        <script src="js-p/jquery.scrolly.min-p.js"></script>
        <script src="js-p/jquery.scrollzer.min-p.js"></script>
        <script src="js-p/skel.min-p.js"></script>
        <script src="js-p/skel-layers.min-p.js"></script>
        <script src="js-p/init-p.js"></script>
        <script src="js-p/facebook.js"></script>
        <script src="js-p/google.js"></script>

        <script src="js-nico/utente.js"></script>
        <script src="js-nico/squadra.js"></script>
        <script src="js-nico/storico.js"></script>
        <script src="js-nico/prossimieventi.js"></script>
        <script src="js-dario/cercaevento.js"></script>
        <script src="js-nico/creaevento.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">

        <noscript>
        <link rel="stylesheet" href="css-p/skel-p.css" />
        <link rel="stylesheet" href="css-p/style-p.css" />
        <link rel="stylesheet" href="css-p/style-wide-p.css" />
        </noscript>
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
        <!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
        <link rel="shortcut icon" href="http://static.tmimgcdn.com/img/favicon.ico">
        <link rel="icon" href="http://static.tmimgcdn.com/img/favicon.ico">
        <link rel="stylesheet" type="text/css" media="all" href="css-p/styles-p.css">
        <link rel="stylesheet" type="text/css" media="all" href="css-p/switchery.min-p.css">
        <script type="text/javascript" src="js-p/switchery.min-p.js"></script>

    </head>
    <body>

        <!-- Header -->
        <div id="header" class="skel-layers-fixed">
            <div class="top">
                <!-- Logo -->
                <%
                    String pic;
                    if (((String) request.getSession().getAttribute("url")).equals("<img src=>")) {
                        pic = "<img src='images/avatar_1.jpg'></img>";
                    } else {
                        pic = (String) request.getSession().getAttribute("url");
                    }
                %>
                <div id="logo">
                    <span class="image avatar48" ><%=pic%></span>
                    <p style="" id="menu_profileName" name="menu_profileName">Benvenuto<br><%= request.getSession().getAttribute("name")%></p>
                </div>

                <button class="submitbtn" id="logoutButton" onclick="logoutUtente()">LOGOUT</button>

                <!-- Nav -->
                <nav id="nav">
                    <ul> 
                        <li><a href="#cercaunevento" id="cercaunevento-link" class="skel-layers-ignoreHref"><span class="icon fa-search">Cerca un evento</span></a></li>
                        <li><a href="#creaunevento" id="ituoieventi-link" class="skel-layers-ignoreHref"><span class="icon fa-pencil-square-o">Crea un evento</span></a></li>
                        <li><a href="#ituoieventi" id="ituoieventi-link" class="skel-layers-ignoreHref"><span class="icon fa-crosshairs">I tuoi prossimi eventi</span></a></li>
                        <li><a href="#storicoeventi" id="storicoeventi-link" class="skel-layers-ignoreHref"><span class="icon fa-ticket">Storico eventi</span></a></li>
                        <li><a href="#latuasquadra" id="latuasquadra-link" class="skel-layers-ignoreHref"><span class="icon fa-users">La tua squadra</span></a></li>
                        <li><a href="#paginapersonale" id="paginapersonale-link" class="skel-layers-ignoreHref"><span class="icon fa-user">Pagina personale</span></a></li>
                    </ul>
                </nav>
            </div> <!--end div TOP -->

        </div>
        <!-- /Header -->

        <!-- Main -->
        <div id="main">
            <!-- Cerca un evento -->
            <%@include file='jspf/cercaunevento.jspf' %>

            <!-- Crea un evento -->
            <%@include file='jspf/creaunevento.jspf' %>

            <!-- I tuoi eventi -->
            <%@include file='jspf/ituoieventi.jspf' %>

            <!-- Storico eventi -->
            <%@include file='jspf/storicoeventi.jspf' %>

            <!-- latuasquadra -->
            <%@include file='jspf/latuasquadra.jspf' %>

            <!-- paginapersonale -->
            <%@include file='jspf/paginapersonale.jspf' %>



        </div>
        <!-- /Main -->

        <!-- Footer -->
        <div id="footer">

            <!-- Copyright -->
            <div class="copyright">
                <ul class="menu">
                    <li>&copy; WebLegends</li>
                </ul>
            </div>
            <div class="bottom">

                <!-- Social Icons -->
                <ul class="icons">
                    <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                    <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                    <li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
                    <li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
                    <li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li>
                </ul>

            </div>

        </div>
        <!-- /Footer -->
    </body>
</html>
