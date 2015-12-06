<%-- 
    Document   : homepage
    Created on : 15-ott-2015, 15-ott-2015 16.08.47
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title> </title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />

        <script src="js-h/jquery.min-h.js"></script>
        <script src="js-h/jquery.scrolly.min-h.js"></script>
        <script src="js-h/jquery.poptrox.min-h.js"></script>
        <script src="js-h/skel.min-h.js"></script>
        <script src="js-h/init-h.js"></script>
        <script src="js-h/facebook1.js"></script>

        <noscript>
        <link rel="stylesheet" href="css-h/skel-h.css" />
        <link rel="stylesheet" href="css-h/style-h.css" />
        </noscript>

        <script src="assets-h/js/scripts.js"></script>     

        <link rel="stylesheet" href="css-h/default.css"/>
        <link rel="stylesheet" href="css-h/component.css"/>

        <link rel="stylesheet" href="css-h/newcss-h.css"/>
        <script src="js-h/newjavascript-h.js"></script>   
        <style media="all">@media screen and (max-width: 46.0625em) {}@media screen and (max-width: 25em) {}</style>
        <style media="all">.md-show.md-effect-16 ~ .container{-ms-filter:blur(3px);-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);filter: url(#blur3px)}</style>
        <style media="all"></style>


    </head>
    <body style="">

        <!-- Header -->
        <section id="header">
            <img src="SHARE-GAMES-small.png" width="46%" height="auto">  
            <p>@By WebLegends </p>

            <section style="height: auto">
                <form action="<%=request.getContextPath()%>/ServletController" method="post" name="action" >
                    <label>
                        <button style="text-transform: capitalize;" type="submit" class="md-trigger md-setperspective button style1"  name="action" value="personal">Il mio profilo</button>
                    </label>
                </form>
            </section>

            <footer >
                <a href="#banner" class="button style2 scrolly scrolly-centered">Esplora il sito</a>
            </footer>

        </section>



        <!-- Banner -->
        <section id="banner">
            <header>
                <h2>Le preoccupazioni sono finite</h2>
            </header>
            <p><br />
                Partecipa ad un evento, conosci nuova gente, crea eventi.<br />
                Pagamento semplice, veloce, sicuro.</p>
            <footer>
                <a href="#first" class="button style2 scrolly">Cosa ti permette di fare?</a>
            </footer>
        </section>

        <!-- Feature 1 -->
        <article id="first" class="container box style1 right">
            <a href="#" class="image fit"><img src="images/pic001.jpg" alt="" /></a>
            <div class="inner">
                <header>
                    <h2>Le preoccupazioni<br />
                        sono finite</h2>
                </header>
                <p>
                    La piattaforma ShareGames, ti permette di giocare al tuo sport preferito.
                </p>
            </div>
        </article>

        <!-- Feature 2 -->
        <article class="container box style1 left">
            <a href="#" class="image fit"><img src="images/pic002.jpg" alt="" /></a>
            <div class="inner">
                <header>
                    <h2>Gioca, cresci, vinci</h2>
                </header>
                <p>
                    Partecipa agli eventi di amici o dei player,
                    crea un tuo evento,
                    conosci nuovi giocatori.
                </p>
            </div>
        </article>

        <!-- Portfolio -->
        <article class="container box style2">
            <header>
                <h2>Scegli il tuo sport preferito</h2>
                <p>Calcio<br />Pallavolo<br />Basket<br />Tennis<br />.....</p>
            </header>
            <div class="inner gallery">
                <!--					<div class="row 0%">
                                                                <div class="3u"><a href="images/fulls/01.jpg" class="image fit"><img src="images/thumbs/01.jpg" alt="" title="Ut porttitor" /></a></div>
                                                                <div class="3u"><a href="images/fulls/02.jpg" class="image fit"><img src="images/thumbs/02.jpg" alt="" title="Natoque ac Cubilia" /></a></div>
                                                                <div class="3u"><a href="images/fulls/03.jpg" class="image fit"><img src="images/thumbs/03.jpg" alt="" title="Nulla" /></a></div>
                                                                <div class="3u"><a href="images/fulls/04.jpg" class="image fit"><img src="images/thumbs/04.jpg" alt="" title="Erat nisl ata cep ac Condimentum, varius" /></a></div>
                                                        </div>
                                                        <div class="row 0%">
                                                                <div class="3u"><a href="images/fulls/05.jpg" class="image fit"><img src="images/thumbs/05.jpg" alt="" title="Aliquet" /></a></div>
                                                                <div class="3u"><a href="images/fulls/06.jpg" class="image fit"><img src="images/thumbs/06.jpg" alt="" title="Adipiscing." /></a></div>
                                                                <div class="3u"><a href="images/fulls/07.jpg" class="image fit"><img src="images/thumbs/07.jpg" alt="" title="Feugiat dis arcu eget" /></a></div>
                                                                <div class="3u"><a href="images/fulls/08.jpg" class="image fit"><img src="images/thumbs/08.jpg" alt="" title="Turpis odio ata mi est nunc nisl" /></a></div>
                                                        </div>-->
            </div>
        </article>

        <!-- Contact -->
        <article class="container box style3">
            <header>
                <h2>Inviaci un messaggio</h2>
                <p>Mandaci qualche vostra idea, potremmo accontentarvi</p>
            </header>
            <form method="post" action="#">
                <div class="row 50%">
                    <div class="6u"><input type="text" class="text" name="name" placeholder="Name" /></div>
                    <div class="6u"><input type="text" class="text" name="email" placeholder="Email" /></div>
                </div>
                <div class="row 50%">
                    <div class="12u">
                        <textarea name="message" placeholder="Message"></textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="12u">
                        <ul class="actions">
                            <li><input type="submit" value="Send Message" /></li>
                        </ul>
                    </div>
                </div>
            </form>
        </article>

        <section id="footer">
            <ul class="icons">
                <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                <li><a href="#" class="icon fa-google-plus"><span class="label">Google+</span></a></li>
                <li><a href="#" class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
                <li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
                <li><a href="#" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
            </ul>
            <div class="copyright">
                <ul class="menu">
                    <li>&copy; WebLegends</li>
                </ul>
            </div>
        </section>

        <div class="md-overlay"></div>
        <script src="js-h/Other/classie.js"></script>
        <script src="js-h/Other/cssParser.js"></script>
        <script src="js-h/Other/modalEffects.js"></script>
        <script src="js-h/Other/modernizr.custom.js"></script>

    <svg width="0" height="0" style="position:absolute"><filter id="blur3px"><feGaussianBlur in="SourceGraphic" stdDeviation="3"></feGaussianBlur></filter></svg></body></html>
</body>
</html>