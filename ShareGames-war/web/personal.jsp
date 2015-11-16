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
                </div>


                <!-- Nav -->
                <nav id="nav">
                    <ul>

                        <a onclick="fb_logout();" id="bottone"><button id="bottone" style="font-size: medium; text-transform: capitalize;" class="facebook" ></i>LogoutFacebook</button></a>
                        <a onclick="signOut();" id="bottone"><button id="bottone" style="font-size: medium; text-transform: capitalize;" class="facebook" ></i>LogoutGoogle</button></a>


                        <form action="<%=request.getContextPath()%>/ServletController" method="post" name="action" >
                            <label>
                                <button style="font-size: medium; text-transform: capitalize;" type="submit" class="md-trigger md-setperspective button style1"  name="action" value="removeUtente">remove</button>
                            </label>
                        </form>



                        <!--                                                                


<li>Benvenuto,<%= request.getSession().getAttribute("name")%></p></li>-->
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

            <!-- Cerca un evento -->
            <section id="cercaunevento" class="one dark cover">
                <div class="container">

                    <header>
                        <h2 class="alt">Cerca il tuo evento</h2>
                        <p>In lavorazione</p>

                        <div id="wrapper">
                            <form onsubmit="return false">
                                <div class="col-2">
                                    <label> Stato
                                        <input placeholder="Seleziona lo Stato" id="name" name="name" tabindex="1">
                                    </label>
                                </div>
                                <div class="col-2">
                                    <label> Regione
                                        <input placeholder="Seleziona la Regione" id="company" name="company" tabindex="2">
                                    </label>
                                </div> 
                                <div class="col-3">
                                    <label> Provincia 
                                        <input placeholder="Seleziona la Provincia" id="phone" name="phone" tabindex="3">
                                    </label>
                                </div>
                                <div class="col-3">
                                    <label> Città
                                        <input placeholder="Seleziona la Città" id="email" name="email" tabindex="4">
                                    </label>
                                </div>
                                <!--                                                                    <div class="col-3">
                                                                                                      <label> Availability
                                                                                                        <select tabindex="5">
                                                                                                          <option>5-15 hrs per week</option>
                                                                                                          <option>15-30 hrs per week</option>
                                                                                                          <option>30-40 hrs per week</option>
                                                                                                        </select>
                                                                                                      </label>
                                                                                                    </div>-->
                                <div class="col-3">
                                    <label> Sport
                                        <input placeholder="Seleziona lo Sport" id="skills" name="skills" tabindex="6">
                                    </label>
                                </div>

                                <div class="col-submit">
                                    <button class="submitbtn">Cerca</button>
                                </div>

                            </form>
                        </div>

                        <script type="text/javascript">
                            var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));

                            elems.forEach(function (html) {
                                var switchery = new Switchery(html);
                            });
                        </script>

                    </header>

                </div>
            </section>

            <!-- I tuoi eventi -->
            <section id="ituoieventi" class="two">
                <div class="container">

                    <h1 style="font-size: 50px">I tuoi eventi</h1> </br>

                    <p>             </p>

                    <div class="row">
                        <div class="4u">
                            <article class="item">
                                <!--										<a href="#" class="image fit"><img src="images/pic02.jpg" alt="" /></a>-->

                                <h3>Evento 1</h3>
                                <header>
                                    <h1>
                                        Dettagli evento:
                                    </h1>
                                    <ul style="text-align: left; margin-left: 5%">
                                        <li><span >Evento numero: xxx-xxx-xxx-xxx</span></a></li>
                                        <li><span >Data : 00/00/0000</span></a></li>
                                        <li><span >Ora : 00:00h</span></a></li>
                                        <li>Pagato :<span class="icon fa-remove " ></span></a></li>
                                        <li><span >Impianto : xxxxxxxxxx</span></a></li>
                                        <li> <img style="width:94%;height:auto" src="http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|Corso+regina+margherita+221+10144+torino&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4"></img>
                                        </li>

                                </header>
                            </article>
                            <article class="item">
                                <a href="#" class="image fit"><img src="images/pic03.jpg" alt="" /></a>
                                <header>
                                    <h3>Evento 3</h3>
                                </header>
                            </article>
                        </div>
                        <div class="4u">
                            <article class="item">
                                <!--										<a href="#" class="image fit"><img src="images/pic04.jpg" alt="" /></a>-->
                                <h3>Evento 2</h3>
                                <header>
                                    <h1>
                                        Dettagli evento:
                                    </h1>
                                    <ul style="text-align: left; margin-left: 5%">
                                        <li><span >Evento numero: xxx-xxx-xxx-xxx</span></a></li>
                                        <li><span >Data : 00/00/0000</span></a></li>
                                        <li><span >Ora : 00:00h</span></a></li>
                                        <li>Pagato :<span class="icon fa-check " ></span></a></li>
                                        <li><span >Impianto : xxxxxxxxxx</span></a></li>
                                        <li> <img style="width:94%;height:auto" src="http://maps.google.com/maps/api/staticmap?markers=size:mid|color:blue|Corso+regina+margherita+221+10144+torino&size=500x300&sensor=false&size=600x300&key=AIzaSyAbz8o3xVmsMTpHh3DRWO1kIW38K3zBVJ4"></img>
                                        </li>

                                </header>
                            </article>
                            <article class="item">
                                <a href="#" class="image fit"><img src="images/pic05.jpg" alt="" /></a>
                                <header>
                                    <h3>Evento 4</h3>
                                </header>
                            </article>
                        </div>
                        <div class="4u">
                            <article class="item">
                                <a href="#" class="image fit"><img src="images/pic06.jpg" alt="" /></a>
                                <header>
                                    <h3>Evento 5</h3>
                                </header>
                            </article>
                            <article class="item">
                                <a href="#" class="image fit"><img src="images/pic07.jpg" alt="" /></a>
                                <header>
                                    <h3>Evento 6</h3>
                                </header>
                            </article>
                        </div>
                    </div>

                </div>

            </section>

            <!-- eventi prenotati -->
            <section id="eventiprenotati" class="three">
                <div class="container">

                    <header>
                        <h2>Storico eventi</h2>
                    </header>

                    <a href="#" class="image featured"><img src="images/pic08.jpg" alt="" /></a>

                    <p>         </p>

                </div>
            </section>

            <!-- pagamenti -->
            <section id="pagamenti" class="four">
                <div class="container">

                    <header>
                        <h2>Pagamenti</h2>
                    </header>

                    <p> </p>


                </div>
            </section>

            <!-- latuasquadra -->
            <section id="latuasquadra" class="four">
                <div class="container">

                    <header>
                        <h2>La tua squadra</h2>
                    </header>

                    <p>             </p>



                </div>
            </section>

            <!-- paginapersonale -->
            <section id="paginapersonale" class="four">
                <div class="container">

                    <header>
                        <h2>Pagina Personale</h2>
                    </header>

                    <div id="wrapper">

                        <p><%= request.getSession().getAttribute("name")%></p>


                    </div>

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