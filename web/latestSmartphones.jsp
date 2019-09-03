<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Latest Smartphones (<%=Calendar.getInstance().get(Calendar.YEAR)%>)</title>
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
        <link rel="stylesheet" href="static/css/smartphonesByBrand.css">
    </head>
    <body>
        <div class="site-wrap">

            <jsp:include page="header.jsp"/>
            
            <div class="brand-topic text-center">
                <h1><b>Latest Smartphones (<script>document.write(new Date().getFullYear());</script>)</b></h1>
                <hr>
            </div>

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
                                    <h6 class="card-title text-center"><b><%= ((String[]) session.getAttribute("smartphoneNames"))[(Integer.parseInt(String.valueOf(pageContext.getAttribute("count"))))]%></b></h6>
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
