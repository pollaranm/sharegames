<%-- 
    Document   : index
    Created on : 15-ott-2015, 15-ott-2015 16.12.32
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <title>Sharegames</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />

        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
        <script src="js/skel.min.js"></script>
        <script src="js/init.js"></script>

        <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-wide.css" />
        <link rel="stylesheet" href="css/style-noscript.css" />
        </noscript>
        <!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
        <!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
    </head>
    <body class="loading">
        <div id="wrapper">
            <div id="bg"></div>
            <div id="overlay"></div>
            <div id="main">

                <!-- Header -->
                <header id="header">
                    <img src="SHARE-GAMES-small.png" width="46%" height="auto">  
                    <p>Gioca &nbsp;&bull;&nbsp; Cresci &nbsp;&bull;&nbsp; Vinci</p>
                    </br>
                    <footer>
                        <form action="<%=request.getContextPath()%>/ServletController" method="post" name="action" >
                            <label>
                                <button style="width:auto; font-size: large" type="submit" type="hidden" class="button style2 scrolly scrolly-centered"  name="action" value="accesso">Entra nel sito</button>
                            </label>
                        </form>				
                    </footer>

                    <nav>
                        <ul>
                            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                            <li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
                            <li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
                            <li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
                        </ul>
                    </nav>
                </header>

                <!-- Footer -->
                <footer id="footer">
                    <span class="copyright">&copy; WebLegends</span>
                </footer>

            </div>
        </div>
    </body>
</html>