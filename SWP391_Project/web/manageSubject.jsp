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
            <a style="margin: 10px; margin-top: 20px;" href="subjects?action=create" class="btn btn-success float-right">
                    <i class="fa fa-plus"></i> New subject
                </a>
        <table id="tableData">
            <thead>
                <tr>
                    <th>Id</th>
                    <td>Name</td>
                    <td>Image</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listS}" var="s">
                    <tr>
                        <th>${s.id}</th>
                        <th>${s.name}</th>
                        <th>
                            <img src="${s.illustration}" width="30" height="30" alt="alt"/>
                        </th>
                        <th>
                            Edit
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
