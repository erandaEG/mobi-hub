<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Best Selection</title>
        <link rel="shortcut icon" type="image/x-icon" href="static/image/favicon.ico" />

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Rubik:400,700" rel="stylesheet">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="static/fonts/icomoon/style.css">

        <link rel="stylesheet" href="static/css/bootstrap.min.css">
        <link rel="stylesheet" href="static/css/magnific-popup.css">
        <link rel="stylesheet" href="static/css/jquery-ui.css">
        <link rel="stylesheet" href="static/css/owl.carousel.min.css">
        <link rel="stylesheet" href="static/css/owl.theme.default.min.css">

        <link rel="stylesheet" href="static/css/aos.css">
        <link rel="stylesheet" href="static/css/style.css">
        <link rel="stylesheet" href="static/css/budgetResults.css">
    </head>
    <body>

        <div class="site-wrap">

            <jsp:include page="header.jsp"/>

            <!--Best Selection section starts-->
            <div class="best-selection-section container" data-aos="fade">
                <div class="row align-items-center">
                    <div class="col-lg-4 ml-auto order-lg-2 align-self-start">
                        <!--card-container starts-->
                        <div  class="w-100">
                            <!--rotating card starts-->
                            <div class="card-container">
                                <!--card starts-->
                                <div class="card">
                                    <!--front panel starts-->
                                    <div class="front">
                                        <center>
                                            <div class="imageDiv" style="background-color: white; padding: 50px 10px 50px 10px">
                                                <img class="image-fluid" src="${bestPhoneImageLink}"/>
                                            </div>
                                        </center>
                                        <div class="content">
                                            <div class="main">
                                                <h3 class="name">${bestPhoneName}</h3>
                                            </div>
                                            <div class="footer">
                                                <i class="fa fa-mail-forward"></i> Hover for More Details
                                            </div>
                                        </div>
                                    </div>
                                    <!--front panel ends-->

                                    <!--back panel starts-->
                                    <div class="back">
                                        <div class="header">
                                            <h5 class="motto">${bestPhoneName}</h5>
                                        </div>
                                        <div class="content">
                                            <div class="main">

                                                <div>
                                                    <div>
                                                        <h4>Released Date</h4>
                                                        <p>
                                                            <%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(11)%>
                                                        </p>
                                                    </div>
                                                    <div>
                                                        <h4>Thickness</h4>
                                                        <p>
                                                            <%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(12)%>
                                                        </p>
                                                    </div>
                                                    <div>
                                                        <h4>Android Version</h4>
                                                        <p>
                                                            <%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(13)%>
                                                        </p>
                                                    </div>
                                                    <div>
                                                        <h4>Storage</h4>
                                                        <p>
                                                            <%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(14)%>
                                                        </p>
                                                    </div>
                                                </div>
                                                <br>
                                                <br>
                                                <hr>
                                                <div class="social-links text-center">
                                                    <a href="servlet_smartphoneDetails?url=${bestPhoneSpecLink}&smartphoneName=${bestPhoneName}">View Full Specs</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--back panel ends-->

                                </div>
                                <!--card ends-->
                            </div>
                            <!--rotating card ends-->
                        </div>
                        <!--card-container ends-->
                    </div>

                    <!--best selection details div starts-->
                    <div class="best-selection-details col-lg-8 order-1">
                        <br>
                        <br>
                        <div class="text-center">
                            <h1> <b> The Best Selection for You! </b> </h1>
                            <hr>
                            <h3> <b> ${bestPhoneName} </b> </h3 >
                            <hr>
                            <p>This is the best smartphone currently available in the market that brings the best value for your budget.</p>
                        </div>
                        <br>
                        <div class="specs-container">
                            <div class="specs">
                                <img src="static/image/touchscreen-technology.png"/>
                                <br>
                                <br>
                                <h4><%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(3)%></h4>
                                <p>
                                    <%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(4)%>
                                </p>
                            </div>
                            <div class="specs">
                                <img src="static/image/camera-shutter.png"/>
                                <br>
                                <br>
                                <h4><%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(5)%></h4>
                                <p>
                                    <%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(6)%>
                                </p>
                            </div>
                            <div class="specs">
                                <img src="static/image/processor.png"/>
                                <br>
                                <br>
                                <h4><%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(7)%></h4>
                                <p>
                                    <%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(8)%>
                                </p>
                            </div>
                            <div class="specs">
                                <img src="static/image/battery.png"/>
                                <br>
                                <br>
                                <h4><%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(9)%></h4>
                                <p>
                                    <%= ((List<String>) session.getAttribute("bestPhoneHighlightedSpecs")).get(10)%>
                                </p>
                            </div>
                        </div>
                    </div>
                    <!--best selection details div ends-->
                </div>
            </div>
            <!--Best Selection section ends-->

            <!--Phone List section starts-->
            <div class="phone-list-section container-fluid">
                <div id="phone-list" class="row">
                    <c:set var="count" value="0" scope="page"/>
                    <c:forEach var="image" items="${smartphoneImages}">
                        <div class="col-sm-3">
                            <div class="card">
                                <div class="hovereffect">
                                    <center>
                                        <img class="img-fluid" src="${image}" alt="phone">
                                    </center>
                                    <div class="overlay">
                                        <h2>Wanna Know Specifications?</h2>
                                        <a class="info" href="servlet_smartphoneDetails?url=<%= ((String[]) session.getAttribute("smartphoneLinks"))[(Integer.parseInt(String.valueOf(pageContext.getAttribute("count"))))]%>&smartphoneName=<%= ((String[]) session.getAttribute("smartphoneNames"))[(Integer.parseInt(String.valueOf(pageContext.getAttribute("count"))))].replace("+", " plus")%>">View Specs</a>
                                    </div>
                                </div>
                                    <div class="card-block" style="width: 300px">
                                    <h6 class="card-title  text-center"><b><%= ((String[]) session.getAttribute("smartphoneNames"))[(Integer.parseInt(String.valueOf(pageContext.getAttribute("count"))))]%></b></h6>
                                </div>
                                <div class="card-footer text-center"><small class="text-muted">Hover on the Image to View Specifications</small></div>
                            </div>
                        </div>
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:forEach>
                </div>
            </div>
            <!--Phone List section ends-->

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

        <script>
            $(document).ready(function () {
                $('.info').click(function (event) {
//                    event.preventDefault();
                    $.ajax({
                        type: "GET",
                        url: "servlet_smartphoneDetails",
                        cache: false,
                        success: function (response) {
                            if (response === "Internal Server Error!") {
                                alert("Oops! Seems like we faced an internal server error! Please try performing the search again.");
                            } else if (response === "No Internet!") {
                                alert("Oops! Seems like there's no stable connection available! Please check your internet connection and try again.");
                            }
                        }
                    });
                });
            });
        </script>
    </body>
</html>
