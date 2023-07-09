<%-- 
    Document   : myListCourse
    Created on : Jul 9, 2023, 12:06:01 AM
    Author     : 84877
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
         <!--phân trang-->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <div style="margin-top: 70px"></div>
        <table id="tableData" class="table-bordered">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Title</th>
                    <th>Image</th>
                    <th>Brief Info</th>
                    <th>List Subject</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${listC}" var="c">
                <tr>
                    <td>${c.name}</td>
                    <td>${c.title}</td>
                    <td>
                        <img src="${c.image}" width="50" height="50"/>
                    </td>
                    <td>${c.brief_infor}</td>
                    <td>
                        <a href="myListSubject?courseId=${c.course_id}">List Subject</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="footer.jsp" />
        <!--phân trang-->
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#tableData').DataTable({
                    pagingType: 'full_numbers',
                    searching: false
                });
            });
        </script>
    </body>
</html>
