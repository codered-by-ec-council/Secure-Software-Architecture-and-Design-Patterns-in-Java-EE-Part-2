<%-- 
    Document   : approval-result-success
    Created on : Sep 23, 2021, 9:46:30 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="p1.data.Approval"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
    </head>
    <body>
        <script type='text/javascript'>
            window.onload = function () {
                if (!window.location.hash) {
                    window.location = window.location + '#loaded';
                    window.location.reload();
                }
            }
        </script><!-- comment -->
        <jsp:useBean id="approval" class="p1.data.Approval" scope="session"/>
        Leave request of Employee ID ${approval.employeeId} is ${approval.status}
        By ${approval.approvedBy}
    </body>
</html>
