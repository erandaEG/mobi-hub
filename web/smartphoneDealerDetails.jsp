<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dealer Details</title>
        <link rel="shortcut icon" type="image/x-icon" href="static/image/favicon.ico" />
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Rubik:400,700" rel="stylesheet">
        <link rel="stylesheet" href="static/fonts/icomoon/style.css">

        <link rel="stylesheet" href="static/css/bootstrap.min.css">
        <link rel="stylesheet" href="static/css/magnific-popup.css">
        <link rel="stylesheet" href="static/css/jquery-ui.css">
        <link rel="stylesheet" href="static/css/owl.carousel.min.css">
        <link rel="stylesheet" href="static/css/owl.theme.default.min.css">


        <link rel="stylesheet" href="static/css/aos.css">
        <link rel="stylesheet" href="static/css/style.css">
    </head>
    <body style="background-color: gainsboro">

        <div class="site-wrap">

            <jsp:include page="header.jsp"/>

            <!--Smartphone Dealer Details section starts-->
            <c:if test="${dealerName == 'dialcom'}">
            <center>
                <div data-aos="fade" style="margin-top: 200px">
                    <div class="card" style="width: 50%; box-shadow: 0 3px 10px">
                        <div style="border-bottom: 1px solid #999">
                            <img class="img-responsive" src="static/image/dialcom.png" alt="dealer-logo">
                        </div>
                        <div class="card-block" style="padding: 10px; background-color: #999; color: black">
                            <p class="card-text">Dialcom was established in the year 1999 and started its retail business at Baseline Road. They have grown from to a full fledge mobile device outlet catering to the needs of the discerning user. Their business ethics have always been honesty and reliability, this is one reason why they have manage to expand it a very competitive industry.</p>
                            <hr>
                            <p class="card-text"><small>279, R A De Mel Maw, Colombo 03</small></p>
                            <p class="card-text"><small>82-A, Galle Road (Haig Road), Colombo 04</small></p>
                            <p class="card-text"><small>190-A, Castle Street, Colombo 08</small></p>
                            <p class="card-text"><small>L2 - 43, Reality Plaza, Jaela</small></p>
                        </div>
                        <button class="btn-warning"><a href="tel://0114544522">Contact: KOLLUPITIYA</a></button>
                        <button class="btn-warning"><a href="tel://0112592597">Contact: BAMBA (GALLE RD)</a></button>
                        <button class="btn-warning"><a href="tel://0114544356">Contact: BORELLA</a></button>
                        <button class="btn-warning"><a href="tel://0112226322">Contact: JAELA</a></button>
                        <button class="btn-warning"><a href="tel://0777173661">Contact: SERVICE CENTER (BAMBA)</a></button>
                    </div>
                </div>
            </center>
            </c:if>
            
            <c:if test="${dealerName == 'techmart'}">
            <center>
                <div data-aos="fade" style="margin-top: 200px">
                    <div class="card" style="width: 50%; box-shadow: 0 3px 10px">
                        <div style="border-bottom: 1px solid #999">
                            <img class="img-responsive" src="static/image/techmart.png" alt="dealer-logo">
                        </div>
                        <div class="card-block" style="padding: 10px; background-color: #999; color: black">
                            <p class="card-text">Techmart was established in the year 2015 and has accumulated worthy experience at its credit towards ensuring quality and timeliness for the clients. Techmart is a licensed importer and distributor of smartphones, telecom gadgets, and accessories in Sri Lanka. They offer the broadest range of telecom products from the leading manufacturers from across the world.</p>
                            <hr>
                            <p class="card-text"><small>410 Galle Road , Colombo 03</small></p>
                        </div>
                        <button class="btn-warning"><a href="tel://0112372672">Contact</a></button>
                    </div>
                </div>
            </center>
            </c:if>
            
            <c:if test="${dealerName == 'celltronics'}">
            <center>
                <div data-aos="fade" style="margin-top: 200px">
                    <div class="card" style="width: 50%; box-shadow: 0 3px 10px">
                        <div style="border-bottom: 1px solid #999">
                            <img class="img-responsive" src="static/image/celltronics.png" alt="dealer-logo">
                        </div>
                        <div class="card-block" style="padding: 10px; background-color: #999; color: black">
                            <p class="card-text">Celltronics Pvt. Ltd is one of the leading electronics distributors in Sri Lanka that has grown from strength to strength since its establishment. Incorporated in 2003, the company started with humble beginnings but with sound knowledge on the mobile phone sphere and strong leadership skills, Celltronics today is recognized as a strong competitor in the electronics market, having started with offering mobile phones, and later accessories.</p>
                            <hr>
                            <p class="card-text"><small>351 R A De Mel Mawatha, Colombo 3 00300.</small></p>
                        </div>
                        <button class="btn-warning"><a href="tel://0112574475">Contact</a></button>
                    </div>
                </div>
            </center>
            </c:if>
            <!--Smartphone Dealer Details section ends-->

            <br>
            <br>

            <jsp:include page="footer.jsp"/>

        </div>

        <script src="static/js/jquery-3.3.1.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/3.9.0/less.min.js" ></script>
        <script src="static/js/jquery-ui.js"></script>
        <script src="static/js/popper.min.js"></script>
        <script src="static/js/bootstrap.min.js"></script>
        <script src="static/js/owl.carousel.min.js"></script>
        <script src="static/js/jquery.magnific-popup.min.js"></script>
        <script src="static/js/aos.js"></script>

        <script src="static/js/main.js"></script>
        <script src="static/js/search.js"></script>
    </body>
</html>
