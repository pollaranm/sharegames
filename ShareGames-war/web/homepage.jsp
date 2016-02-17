<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Sharegames</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />

        <script src="js-h/facebook.js"></script>                
        <script src="js/jquery.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
        <script src="https://apis.google.com/js/api:client.js"></script>
        <script src="js-h/google.js"></script>

        <!--
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-client_id" content="511603926371-sr21hvrj9jute7pvpgh9son8br3qskkh.apps.googleusercontent.com">
        -->
        <script src="js-h/jquery.min-h.js"></script>
        <script src="js-h/jquery.scrolly.min-h.js"></script>
        <script src="js-h/jquery.poptrox.min-h.js"></script>
        <script src="js-h/skel.min-h.js"></script>
        <script src="js-h/init-h.js"></script>
        <script src="assets-h/js/scripts.js"></script>     
        <script src="js-h/newjavascript-h.js"></script>    

        <script>
            $(window).load(function () {
                // Animate loader off screen
                $(".se-pre-con").delay(1800).fadeOut("slow");
            });
        </script>
        <noscript>
        <link rel="stylesheet" href="css-h/skel-h.css" />
        <link rel="stylesheet" href="css-h/style-h.css" />
        </noscript>
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="css-h/loading.css"/>   
        <link rel="stylesheet" href="css-h/default.css"/>
        <link rel="stylesheet" href="css-h/component.css"/>
        <link rel="stylesheet" href="css-h/newcss-h.css"/>

        <style media="all">@media screen and (max-width: 46.0625em) {}@media screen and (max-width: 25em) {}</style>
        <style media="all">.md-show.md-effect-16 ~ .container{-ms-filter:blur(3px);-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);filter: url(#blur3px)}</style>
        <style media="all"></style>
    </head>
    <body style="">
        <div class="se-pre-con"></div>
        <!-- Div di autentificazione -->
        <div class="md-modal md-effect-19" id="modal-19">
            <div class="md-content">
                <div class="page-container">
                    <div class="social" style="margin-top: 4%"> <span>Scegli il social network</br>che vuoi utilizzare</span></div>
                    <div class="buttons">
                        <a onclick="fb_login();" id="bottone"><button id="bottone" style="font-size: medium; text-transform: capitalize;" class="facebook" ><i class="fa fa-facebook"></i>Facebook</button></a>
                        <a style="pointer-events: none;cursor: default;"><button style="font-size: medium; text-transform: lowercase;" class="" ><i class=""></i>... oppure ...</button></a>
                        <div id="buttonGoogle">
                            <button style="font-size: medium; text-transform: capitalize;" class="google"><i class="fa fa-google-plus"></i>Google</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- FINE - Div di autentificazione -->

        <!-- Header -->
        <section id="header">
            <img src="SHARE-GAMES-small.png" width="46%" height="auto">  
            <p>@By WebLegends </p>
            <div id="status"></div>
            <section id="formloginaccesso" style="width: auto; border: 0 auto;">
                <button  style="margin-top: 1%" class="md-trigger md-setperspective button style2"  data-modal="modal-19">Accedi</button>
            </section>

            <footer>
                <a href="#banner" class="button style1 scrolly scrolly-centered">Esplora il sito</a>
            </footer>
        </section>

        <!-- Banner -->
        <section id="banner">
            <header>
                <h2>Le preoccupazioni sono finite</h2>
            </header>
            <p><br/>
                Partecipa ad un evento, conosci nuova gente, crea eventi.<br />
                Pagamento semplice, veloce, sicuro.</p>
            <footer>
                <a href="#first" class="button style2 scrolly">Cosa ti permette di fare?</a>
            </footer>
        </section>

        <!-- Feature 1 -->
        <article id="first" class="container box style1 right">
            <img class="image fit" src="images/pic01.jpg" alt="" />
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
            <img class="image fit" src="images/pic02.jpg" alt="" />
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
                    <div class="6u"><input type="text" class="text" name="name" placeholder="Nome" /></div>
                    <div class="6u"><input type="text" class="text" name="email" placeholder="Email" /></div>
                </div>
                <div class="row 50%">
                    <div class="12u">
                        <textarea name="message" placeholder="Messaggio"></textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="12u">
                        <ul class="actions">
                            <li><input type="submit" value="Invia Messaggio" /></li>
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
        <script>startApp();</script>
    <svg width="0" height="0" style="position:absolute"><filter id="blur3px"><feGaussianBlur in="SourceGraphic" stdDeviation="3"></feGaussianBlur></filter></svg></body></html>
</body>
</html>