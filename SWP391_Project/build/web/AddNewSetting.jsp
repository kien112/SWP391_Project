<%-- 
    Document   : AddNewSetting
    Created on : Jun 26, 2023, 4:00:26 PM
    Author     : 84877
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./CSS/userStyle.css"/>
    </head>
    <body>
         <jsp:include page="header.jsp"/>
        <h1>Add New Setting</h1>
        <form action="addNewSetting" method="post">
            <div class="input-field">
                <label>Setting Name</label>
                <input name="setting_name"/>
            </div>
            <div class="input-field">
                <label>Type</label>
                <select name="type">
                    <c:forEach items="${listR}" var="r">
                        <option value="${r.role_id}">${r.role_name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-field">
                <label>Value</label>
                <input name="value"/>
            </div>
            <div class="input-field">
                <label>Order</label>
                <input name="order" type="number"/>
            </div>
            <div class="input-field">
                <label>Description</label>
                <textarea name="description"></textarea>
            </div>
            <div class="input-field">
                <label>Status</label>
                <select name="status">
                    <option value="1">Active</option>
                    <option value="0">InActive</option>
                </select>
            </div>
            <button style="margin: 0px 600px;" type="submit">Add New</button>
        </form>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
