<%-- 
    Document   : approval-result-failure
    Created on : Sep 23, 2021, 9:46:48 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fail</title>
    </head>
    <body>
        <script type='text/javascript'>
            window.onload = function () {
                if (!window.location.hash) {
                    window.location = window.location + '#loaded';
                    window.location.reload();
                }
            }
        </script>
        Please login as a manager user to do approvals
    </body>
</html>
