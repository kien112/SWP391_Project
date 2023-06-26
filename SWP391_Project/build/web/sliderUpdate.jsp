<%-- 
    Document   : sliderUpdate
    Created on : Jun 23, 2023, 3:00:53 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="update">
           
            <div>
                <label>Title</label>
                <input type="text" name="title" value="${slider.title}">
            </div>
            <div>
                <label>Image</label>
                <img src="${slider.image}" alt="alt"/>
            </div>
            <div>
                <label>Back link</label>
                <input type="text" name="backlink" value="${slider.backlink}">
            </div>
            <div>
                <label>Status</label>
                <select name="status">
                    <option value="true" <c:if test="${s.status}">selected</c:if>>Active</option>
                    <option value="false" <c:if test="${s.status}">selected</c:if>>Deactive</option>
                </select>
            </div>
                <input type="hidden" name="id" value="${slider.id}">
            <button>Update</button>
        </form>
    </body>
</html>
