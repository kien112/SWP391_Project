<%-- 
    Document   : settingList.jsp
    Created on : Jun 20, 2023, 9:37:21 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="CSS/myRegis.css" rel="stylesheet" type="text/css"/>            
        <link href="CSS/styles.css" rel="stylesheet" type="text/css"/> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>

        <jsp:include page="header.jsp" />   
        <div class="container"  style="margin-right: 2px; margin-left: 15%">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-3">
                            <h2>Setting List</h2>                                
                        </div> 
                        <br><div class="col-sm-9">
                            <form action="searchSettingList" method="post">
                                Search: <input type="text" name="value" style="color: black;" placeholder="Search by value">
                                <button style="color: black; padding: 2px;"><i class="fa-solid fa-magnifying-glass"></i></button>
                            </form>             
                            Add new: <button style="color: black; border-radius: 4px">Click to add new</button>
                            <form action="sortSetting" method="post">
                                <select name="opU" onchange="this.form.submit()" style="color: black;">
                                    <option value="all">All</option>
                                    <option value="op1">ID</option>
                                    <option value="op2">Type</option>
                                    <option value="op3">Value</option>
                                    <option value="op4">Order</option>
                                    <option value="op5">Status</option>
                                </select>(Sort) 
                            </form>
                        </div>

                    </div>
                </div>
<!--                <div>${aa}</div>-->
                <form method="post" action="settingList">
                    <table class="table table-striped table-hover">
                    <thead>
                        <tr>                            
                            <th>ID</th>
                            <th>Name</th>
                            <th>Type</th>
                            <th>Value</th>
                            <th>Order</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody><!--
                        <c:forEach var="u" items="${listS}">
-->                            <tr>                               
                                <td>${u.setting_id}</td>
                                <td>${u.setting_name}</td>
                                <td>${u.role_name}</td>
                                <td>${u.value}</td>
                                <td>${u.order}</td>
                                <td>${u.status ? "Active" : "Inactive"}</td>
                                <td><a  href="settingDetail?sid=${u.setting_id}"><button type="button" class="btn btn_detail ">Edit
                                             </button> </a></td>
                            </tr><!--
                        </c:forEach>                           
-->                    </tbody>
                </table>  
                </form>
                             
            </div>
        </div> 
        <jsp:include page="footer.jsp" />
    </body>
</html>