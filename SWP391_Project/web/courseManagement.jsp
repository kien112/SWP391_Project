<%-- 
    Document   : courseManagement
    Created on : Jul 5, 2023, 10:58:05 AM
    Author     : 84877
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Management</title>
         <!--phân trang-->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp" />   
        <div style="margin: 50px 0px">
        <table id="tableData">
            <thead>
                <tr>
                    <th>Id</th>
                    <td>Name</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listC}" var="c">
                    <tr>
                        <th>${c.course_id}</th>
                        <th>${c.name}</th>
                        <th>
                            <a href="subjects?courseId=${c.course_id}">Subject List</a>
                        </th>
                    </tr>
                </c:forEach> 
                
            </tbody>
        </table>
            </div>
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
