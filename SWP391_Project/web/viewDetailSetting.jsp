<%-- 
    Document   : viewDetailSetting
    Created on : Jun 27, 2023, 10:08:46 AM
    Author     : 84877
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .body{
            height: 500px;
            text-align: center;
            padding-top: 100px;
        }
        a{
            font-size: 30px;
        }
    </style>
    <body>
        <jsp:include page="header.jsp" />   
        <div class="body">
            <h1>Setting Detail</h1>
            <div>
                <h3>Setting ID: ${s.setting_id}</h3>
            </div>
            <div>
                <h3>Setting Name: ${s.setting_name}</h3>
            </div>
            <div>
                <h3>Type: ${s.role_name}</h3>
            </div>
            <div>
                <h3>Value: ${s.value}</h3>
            </div>
            <div>
                <h3>Order: ${s.order}</h3>
            </div>
            <div>
                <h3>Status: ${s.status}</h3>
            </div>
            <div>
                <h3>Description: ${s.description}</h3>
            </div>
            <a href="settingList">Back</a>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
