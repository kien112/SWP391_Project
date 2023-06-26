<%-- 
    Document   : sliderList
    Created on : Jun 21, 2023, 9:50:09 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
    </table>
    <div>
        <form method="get" action="slider">
            <label>Filter</label>
            <label>Status: </label>
            <select name="status_filter">
                <option value="true">Active</option>
                <option value="false">DeActive</option>
            </select>
            <button>Filter</button>
        </form>
    </div>

    <div>
        <form method="get" action="slider">
            <label>Search: </label>
            <input type="text" name="title_search">
            <button>Search</button>
        </form>
    </div>
    <c:if test="${sliders.size() eq 0}">NOT FOUND ANY SLIDES</c:if>
    <c:if test="${sliders.size() ne 0}"> <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Image</th>
                    <th>Back Link</th>
                    <th>Status</th>
                    <th>Display(Hide/Show)</th>
                    <th>Action</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.sliders}" var="s" varStatus="loop">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.title}</td>
                        <td><img src="${s.image}" alt="alt" width="200px"/></td>
                        <td><a href="${s.backlink}">Enter slide</a></td>
                        <td><c:if test="${s.status}">Active</c:if>
                            <c:if test="${!s.status}">Deactive</c:if></td>
                            <td>
                                <form method="get" action="updateStatusSlider">
                                <c:if test="${s.status}"><button>Hide</button></c:if>
                                <c:if test="${!s.status}"><button>Show</button></c:if> 
                                <input type="hidden" name="sliderId" value="${s.id}">
                                <input type="hidden" name="status" value="${s.status}">
                            </form>
                        </td>
                        <td><button><a href="<%=request.getContextPath()%>/slider/update?id=${s.id}">Update</a></button></td>
                        <td>
                            <form method="post" action="slider/delete" onsubmit="return confirm('Are you sure you want to delete this item?')">
                                <input type="hidden" name="id" value="${s.id}">
                                <button>Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table></c:if>
<jsp:include page="footer.jsp"/>

</body>
</html>
