<%-- 
    Document   : viewScoreExam
    Created on : Jul 9, 2023, 8:47:06 AM
    Author     : 84877
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div style="margin-top: 70px"></div>
        <h3>Last Score: ${score}</h3>
        <a href="practiceExam?action=start&examId=${examId}">Start Exam</a>
        <a href="myListExam">Back</a>
        <div style="margin-bottom: 370px"></div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
