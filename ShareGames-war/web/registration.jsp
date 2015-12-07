<%-- 
    Document   : registration
    Created on : 6-dic-2015, 21.20.11
    Author     : TTm
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title> </title>

        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
        <script src="js-p/jquery.min-p.js"></script>
        <script src="js-p/jquery.scrolly.min-p.js"></script>
        <script src="js-p/jquery.scrollzer.min-p.js"></script>
        <script src="js-p/skel.min-p.js"></script>
        <script src="js-p/skel-layers.min-p.js"></script>
        <script src="js-p/init-p.js"></script>
        <script src="js-p/facebook.js"></script>
        <script src="js-p/google.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
        <script src="https://apis.google.com/js/api:client.js"></script>
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
                <div id="logo">
                    <span class="image avatar48" ><%= request.getSession().getAttribute("url")%></span>
                    <span>Sei tu?</span>
                </div>


                <!-- Nav -->
                <nav id="nav">
                    <ul>
                        <li><a href="#cercaunevento" id="cercaunevento-link" class="skel-layers-ignoreHref"><span class="icon fa-search">Cerca un evento</span></a></li>
                        <li><a href="#ituoieventi" id="ituoieventi-link" class="skel-layers-ignoreHref"><span class="icon fa-crosshairs">I tuoi eventi</span></a></li>
                        <li><a href="#eventiprenotati" id="eventiprenotati-link" class="skel-layers-ignoreHref"><span class="icon fa-ticket">Storico eventi</span></a></li>
                        <li><a href="#pagamenti" id="pagamenti-link" class="skel-layers-ignoreHref"><span class="icon fa-dollar">Pagamenti</span></a></li>
                        <li><a href="#latuasquadra" id="latuasquadra-link" class="skel-layers-ignoreHref"><span class="icon fa-users">La tua squadra</span></a></li>
                        <li><a href="#paginapersonale" id="paginapersonale-link" class="skel-layers-ignoreHref"><span class="icon fa-user">Pagina personale</span></a></li>
                    </ul>
                </nav>

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

        <!-- Main -->
        <div id="main">

            <section id="registration" class="one dark cover">
                <div class="container">

                    <header>
                        <h2 class="alt">Conferma i tuoi dati</h2>

                        <div id="wrapper">
                            <form action="ServletController" method="POST">
                                <input type="hidden" name="action" value="registration">
                                
                                <div class="col-3">
                                    <label> Nome
                                        <input value="<%= request.getSession().getAttribute("name")%>" placeholder="<%= request.getSession().getAttribute("name")%>"id="name" name="name" tabindex="1">
                                    </label>
                                </div>
                                <div class="col-3">
                                    <label> Email
                                        <input placeholder="<%= request.getSession().getAttribute("email")%>" id="email" name="email" tabindex="2">
                                    </label>
                                </div> 
                                <div class="col-3">
                                    <label> Telefono 
                                        <input value="<%= request.getSession().getAttribute("phone")%>" id="phone" name="phone" tabindex="3">
                                    </label>
                                </div>

                                <div class="col-submit">
                                    <button class="submitbtn">Conferma registrazione</button>
                                </div>

                            </form>
                        </div>

                    </header>

                </div>
            </section>

        </div>

        <!-- Footer -->
        <div id="footer">

            <!-- Copyright -->
            <div class="copyright">
                <ul class="menu">
                    <li>&copy; WebLegends</li>
                </ul>
            </div>

        </div>
        <script>startApp();</script>
    </body>
</html>
