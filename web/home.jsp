<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
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

        <link rel="stylesheet/less" type="text/css" href="static/less/search.less" />
    </head>
    <body>
        <div class="site-wrap">

            <jsp:include page="header.jsp"/>

            <!--Search section starts-->
            <div class="site-blocks-cover" data-aos="fade" style="background-color: gainsboro; top: -35px">
                <div class="container">
                    <br/>
                    <br/>
                    <div class="row align-items-center">
                        <div class="col-lg-5 text-center">
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <div class="featured-hero-product w-100">
                                <h1 class="mb-2">Let's Find a Phone</h1>
                                <h4>We'll Help You to Find the Best Phone for Your Budget</h4>
                                <br/>
                                <div class="container">
                                    <div class="cntr">
                                        <div class="cntr-innr">
                                            <div>
                                                <form id="search_form" action="servlet_home" method="post">
                                                    <label class="search" for="inpt_search">
                                                        <input id="inpt_search" placeholder="Type in Your Budget and Hit Enter..." class="searchBox" type="number" name="search" min="0" max="300000" autocomplete="off" pattern="^[0-9]*$" required />
                                                    </label>
                                                </form>
                                            </div>
                                            <br/>
                                            <p class="text-muted search_hint" style="margin-bottom: 0"><small>Mouse-over the Magnifying Glass</small></p>
                                            <small class="text-danger currency_hint" style="margin-top: 0">NOTE: This site's currency is LKR!</small>
                                            <br/>
                                            <br/>
                                            <br/>
                                            <br/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-7 align-self-end text-center text-lg-right" style="pointer-events: none; z-index: -5">
                            <img src="static/image/new/girl.png" alt="Image" class="img-fluid img-1">
                        </div>
                    </div>
                </div>
            </div>
            <!--Search section ends-->

            <!--Featured Brands section starts-->
            <div class="site-section">
                <div class="container">
                    <div class="row">
                        <div class="title-section text-center col-12">
                            <h2 class="text-uppercase" style="margin-bottom: 0"><b>Featured Brands</b></h2>
                            <hr style="background-color: gainsboro; margin-top: 0"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 block-3 products-wrap">
                            <div class="nonloop-block-3 owl-carousel">

                                <div class="product">
                                    <a href="servlet_smartphonesByBrand?brandCode=7" class="item">
                                        <img src="static/image/sony.png" alt="Image" class="img-fluid">
                                    </a>
                                </div>

                                <div class="product">
                                    <a href="servlet_smartphonesByBrand?brandCode=9" class="item">
                                        <img src="static/image/samsung.png" alt="Image" class="img-fluid">
                                    </a>
                                </div>

                                <div class="product">
                                    <a href="servlet_smartphonesByBrand?brandCode=48" class="item">
                                        <img src="static/image/apple.png" alt="Image" class="img-fluid">
                                    </a>
                                </div>

                                <div class="product">
                                    <a href="servlet_smartphonesByBrand?brandCode=95" class="item">
                                        <img src="static/image/oneplus.png" alt="Image" class="img-fluid">
                                    </a>
                                </div>

                                <div class="product">
                                    <a href="servlet_smartphonesByBrand?brandCode=1" class="item">
                                        <img src="static/image/nokia.png" alt="Image" class="img-fluid">
                                    </a>
                                </div>

                                <div class="product">
                                    <a href="servlet_smartphonesByBrand?brandCode=80" class="item">
                                        <img src="static/image/xiaomi.png" alt="Image" class="img-fluid">
                                    </a>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--Featured Brands section ends-->

            <!--Latest Smartphones section starts-->
            <div class="site-blocks-cover inner-page py-5"  data-aos="fade" style="background-color: gainsboro">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-6 ml-auto order-lg-2 align-self-start">
                            <div class="site-block-cover-content">
                                <h2 class="sub-title"><script>document.write(new Date().getFullYear());</script></h2>
                                <h1>LATEST SMARTPHONES</h1>
                                <p><a href="servlet_latestSmartphones" class="btn btn-black rounded-0">View Now</a></p>
                            </div>
                        </div>
                        <div class="col-lg-6 order-1">
                            <img src="static/image/latest.png" alt="Image" class="img-fluid">
                        </div>
                    </div>
                </div>
            </div>
            <!--Latest Smartphones section ends-->

            <br/>
            <br/>

            <!--Top Smartphone Dealers section starts-->
            <div class="products-wrap border-top-0">
                <div class="container">
                    <div class="row">
                        <div class="title-section text-center col-12">
                            <h2 class="text-uppercase" style="margin-bottom: 0"><b>Top Smartphone Dealers</b></h2>
                            <hr style="background-color: gainsboro; margin-top: 0"/>
                        </div>
                    </div>
                    <br/>
                    <div class="row no-gutters products">
                        <div class="col-6 col-md-6 col-lg-4">
                            <a href="servlet_smartphoneDealerDetails?dealerName=dialcom" class="item">
                                <img src="static/image/dialcom.png" alt="Image" class="img-fluid">
                                <div class="item-info">
                                    <span class="collection d-block">Colombo 3, Colombo 4, Borella, Ja-Ela</span>
                                </div>
                            </a>
                        </div>
                        <div class="col-6 col-md-6 col-lg-4">
                            <a href="servlet_smartphoneDealerDetails?dealerName=techmart" class="item">
                                <img src="static/image/techmart.png" alt="Image" class="img-fluid">
                                <div class="item-info">
                                    <span class="collection d-block">Colombo 4</span>
                                </div>
                            </a>
                        </div>
                        <div class="col-6 col-md-6 col-lg-4">
                            <a href="servlet_smartphoneDealerDetails?dealerName=celltronics" class="item">
                                <img src="static/image/celltronics.png" alt="Image" class="img-fluid">
                                <div class="item-info">
                                    <span class="collection d-block">Colombo 4</span>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <!--Top Smartphone Dealers section ends-->

            <br/>
            <br/>

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
