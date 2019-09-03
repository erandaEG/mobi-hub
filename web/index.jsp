<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Please Wait...</title>
        <link rel="shortcut icon" type="image/x-icon" href="static/image/favicon.ico" />
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


        <style>
            body{
                background-color: gainsboro;
            }

            .loader {
                position: absolute;
                top: 30%;
                left: 42%;
                border: 16px solid #f3f3f3;
                border-radius: 50%;
                border-top: 16px solid #3498db;
                width: 120px;
                height: 120px;
                -webkit-animation: spin 2s linear infinite; /* Safari */
                animation: spin 2s linear infinite;
            }

            h3{
                position: absolute;
                top: 60%;
                left: 34%;
            }

            /* Safari */
            @-webkit-keyframes spin {
                0% { -webkit-transform: rotate(0deg); }
                100% { -webkit-transform: rotate(360deg); }
            }

            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }
        </style>
    </head>
    <body>

        <div id="loader-container">
            <div class="loader"></div>
            <h3>Please Wait While We Load the Necessary Data for You...</h3>
        </div>
    </body>
</html>
