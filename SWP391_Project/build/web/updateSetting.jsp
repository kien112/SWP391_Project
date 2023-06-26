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
        <h1>Update Setting</h1>
        <form action="updateSetting" method="post">
            <input name="setting_id" value="${s.setting_id}" hidden=""/>
            <div class="input-field">
                <label>Type</label>
                <select name="type">
                    <c:forEach items="${listR}" var="r">
                        <option value="${r.role_id}" ${s.role_name==r.role_name?"selected":""}>${r.role_name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-field">
                <label>Value</label>
                <input name="value" value="${s.value}"/>
            </div>
            <div class="input-field">
                <label>Order</label>
                <input name="order" type="number" value="${s.order}"/>
            </div>
            <div class="input-field">
                <label>Description</label>
                <textarea name="description" style="width: 200px" value="${s.description}">${s.description}</textarea>
            </div>
            <div class="input-field">
                <label>Status</label>
                <select name="status">
                     <option value="0" ${s.status?"":"selected"}>InActive</option>
                    <option value="1" ${s.status?"selected":""}>Active</option>
                </select>
            </div>
            <button style="margin: 0px 600px;" type="submit">Update</button>
        </form>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
