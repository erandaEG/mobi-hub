<%@page import="java.util.List"%>
<%@page import="org.jsoup.select.Elements"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title><%=((List<String>) request.getAttribute("highlightedSpecs")).get(0)%> Details</title>
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
        <link rel="stylesheet" href="static/css/smartphoneDetails.css">
    </head>
    <body>

        <div class="site-wrap">

            <jsp:include page="header.jsp"/>

            <!--Main Details section starts-->
            <div class="main-details-section" data-aos="fade" style="margin-top: 200px">
                <div class="container-fluid">
                    <div class="row align-items-center">

                        <!--main-details div starts-->
                        <div class="main-details col-lg-4 ml-auto order-lg-2 align-self-start">
                            <h5 class="sub-title"><%= ((List<String>) request.getAttribute("highlightedSpecs")).get(1)%></h5>
                            <h1 class="main-title"><b><%= ((List<String>) request.getAttribute("highlightedSpecs")).get(2)%></b></h1>
                            <hr>
                            <div class="main-specs-container">
                                <div class="main-spec">
                                    <img class="main-spec-icon" src="static/image/touchscreen-technology.png"/>
                                    <h4><%= ((List<String>) request.getAttribute("highlightedSpecs")).get(3)%></h4>
                                    <p>
                                        <%= ((List<String>) request.getAttribute("highlightedSpecs")).get(4)%>
                                    </p>
                                </div>
                                <div class="main-spec">
                                    <img class="main-spec-icon" src="static/image/camera-shutter.png"/>
                                    <h4><%= ((List<String>) request.getAttribute("highlightedSpecs")).get(5)%></h4>
                                    <p>
                                        <%= ((List<String>) request.getAttribute("highlightedSpecs")).get(6)%>
                                    </p>
                                </div>
                                <div class="main-spec" class="specs">
                                    <img class="main-spec-icon" src="static/image/processor.png"/>
                                    <h4><%= ((List<String>) request.getAttribute("highlightedSpecs")).get(7)%></h4>
                                    <p>
                                        <%= ((List<String>) request.getAttribute("highlightedSpecs")).get(8)%>
                                    </p>
                                </div>
                                <div class="main-spec" class="specs">
                                    <img class="main-spec-icon" src="static/image/battery.png"/>
                                    <h4><%= ((List<String>) request.getAttribute("highlightedSpecs")).get(9)%></h4>
                                    <p>
                                        <%= ((List<String>) request.getAttribute("highlightedSpecs")).get(10)%>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <!--main-details div ends-->

                        <!--main-details-images div starts-->
                        <div class="main-details-images col-lg-8 order-1 align-self-end">
                            <div class="site-section">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-12 block-3 products-wrap">
                                            <div class="nonloop-block-3 owl-carousel">

                                                <c:forEach var="image" items="${images}">
                                                    <div class="product">
                                                        <img src="<%= (String) pageContext.getAttribute("image")%>" alt="Image" class="img-fluid">
                                                    </div>
                                                </c:forEach>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--main-details-images div ends-->
                    </div>
                </div>
            </div>
            <!--Main Details section ends-->

            <br>

            <!--Specifications List section starts-->
            <div id="specs-list">
                <c:forEach var="specTable" items="${specsList}">
                    ${specTable}
                </c:forEach>
            </div>
            <!--Specifications List section ends-->

            <br/>
            <br/>

            <!--Available Smartphone Dealers section starts-->
            <div class="products-wrap border-top-0">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="title-section text-center col-12">
                            <h2 class="text-uppercase" style="margin-bottom: 0"><b>Available Dealers</b></h2>
                            <hr style="background-color: gainsboro; margin-top: 0"/>

                            <c:if test="${notAvailableInAnyShop}">
                                <h3>This Phone is Currently Not Available in Sri Lanka!</h3>
                            </c:if>
                        </div>
                    </div>
                    <br/>
                    <div class="row no-gutters products">
                        <c:if test="${dialcomStatus == 'Available'}">
                            <div class="col-6 col-md-6 col-lg-4">
                                <a href="servlet_smartphoneDealerDetails?dealerName=dialcom" class="item">
                                    <img src="static/image/dialcom.png" alt="Image" class="img-fluid">
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <div class="item-info">
                                        <span class="collection d-block">Available for</span>
                                        <h3><small>LKR </small>${dialcomPrice}</h3>
                                    </div>
                                </a>
                            </div>
                        </c:if>

                        <c:if test="${techmartStatus == 'Available'}">
                            <div class="col-6 col-md-6 col-lg-4">
                                <a href="servlet_smartphoneDealerDetails?dealerName=techmart" class="item">
                                    <img src="static/image/techmart.png" alt="Image" class="img-fluid">
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <div class="item-info">
                                        <span class="collection d-block">Available for</span>
                                        <h3><small>LKR </small>${techmartPrice}</h3>
                                    </div>
                                </a>
                            </div>
                        </c:if>

                        <c:if test="${celltronicsStatus == 'Available'}">
                            <div class="col-6 col-md-6 col-lg-4">
                                <a href="servlet_smartphoneDealerDetails?dealerName=celltronics" class="item">
                                    <img src="static/image/celltronics.png" alt="Image" class="img-fluid">
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <div class="item-info">
                                        <span class="collection d-block">Available for</span>
                                        <h3><small>LKR </small>${celltronicsPrice}</h3>
                                    </div>
                                </a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <!--Available Smartphone Dealers section ends-->

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
