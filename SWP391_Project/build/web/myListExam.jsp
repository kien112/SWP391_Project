
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
          <table id="tableData" class="mb-5 table-bordered table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Subject</th>
                    <th>Level</th>
                    <th>Duration</th>
                    <th>Pass Rate</th>
                    <th>Number Of Question</th>
                    <th>Description</th>
                    <th>Type</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listE}" var="e">
                    <tr>
                        <td>${e.name}</td>
                        <td>${e.subject_name}</td>
                        <td>${e.level.level}</td>
                        <td>${e.duration}</td>
                        <td>${e.pass_rate}</td>
                        <td>${e.number_of_question}</td>
                        <td>${e.description}</td>
                        <td>${e.examType.name}</td>
                        <td>
                            <a href="practiceExam?action=viewScore&examId=${e.id}">Go to exam</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div style="margin-bottom: 370px"></div>
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
