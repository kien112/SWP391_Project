<%-- 
    Document   : listDetail.jsp
    Created on : Jun 20, 2023, 9:38:12 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link href="CSS/userprofile.css" rel="stylesheet" type="text/css"/> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container" style="margin-top: 50px;">
           
            <div class="main-body">             
                <div class="row gutters-sm">
                    <div class="col-md-8">
                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="row">
                                    <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Name:</h6>
                                    <input type="text" class="fullname" style="width: 90%" value="${u.setting_name}">
                                </div>
                                <hr>
                                <div class="row">
                                    <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Type:</h6>
                                    <input type="text" class="fullname" style="width: 90%" value="${u.type}">
                                </div>
                                <hr>
                                <div class="row">
                                    <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Value:</h6>
                                    <input type="text" class="fullname" style="width: 90%" value="${u.value}">
                                </div>
                                <hr>
                                <div class="row">
                                    <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Order:</h6>
                                    <input type="text" class="fullname" style="width: 90%" value="${u.order}">                                  
                                </div>
                                <div class="row">
                                    <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Description:</h6>
                                    <textarea class="fullname" style="width: 90%"></textarea> 
                                </div>
                                <hr>
                                <div class="row">
                                    <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Status:</h6>
                                    <select style="padding: 1%; width: 90%; font-size: 200%">
                                        <option>Activate</option>
                                        <option>Deactivate</option>
                                    </select>
                                </div>
                                <hr/>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <button style="color: black; padding: 5px; font-size: 20px; border-radius: 10px">Save change</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
    <script>
        function showModal(modalId) {
            document.getElementById(modalId).style.display = "block";
        }

        function hideModal(modalId) {
            document.getElementById(modalId).style.display = "none";
        }

        function showDoNotAccountOrLogin(modalIdOpen, modalIdClose) {
            hideModal(modalIdClose)
            showModal(modalIdOpen)
        }
    </script>
</body>
</html> 
