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
        <!--phân trang-->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>

    </head>
    <body>
        <jsp:include page="header.jsp" />   
        <div class="container"  style="margin-right: 2px; margin-left: 12%;">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-3">
                            <h2>User <b>Search</b></h2>                                
                        </div> 
                        <br>
                        <div class="col-sm-9">
                            <form action="searchUserBy" method="post">
                                Name <input type="text" name="name" style="color: black;">     
                                Email <input type="text" name="email" style="color: black;">                          
                                Phone <input type="text" name="phone" style="color: black;">
                                <button style="color: black; padding: 2px;"><i class="fa-solid fa-magnifying-glass"></i></button>
                            </form>             
                            <a href="addNewUser"><button style="color: black;">Add new</button></a>
                            <form action="sortUser" method="post">
                                <select name="opU" onchange="this.form.submit()" style="color: black;">
                                    <option value="all">All</option>
                                    <option value="op1">Name</option>
                                    <option value="op2">Email</option>
                                    <option value="op3">Gender</option>
                                    <option value="op4">Phone number</option>
                                    <option value="op5">Role</option>
                                    <option value="op6">Status</option>
                                    <option value="op7">ID</option>
                                </select>(Sort increase) 
                            </form>
                        </div>
                    </div>
                    <div class="filter">
                        Filter:
                        <form action="filterUserList" method="post">
                            Gender: <select name="gender" style="color: black">
                                <option value="-1">--Gender--</option>
                                <option value="1">Male</option>
                                <option value="0">Female</option>
                            </select>
                            Role: <select name="role" style="color: black">
                                <option value="-1">--Role--</option>
                                <c:forEach items="${listR}" var="r">
                                    <option value="${r.role_id}">${r.role_name}</option>
                                </c:forEach>
                            </select>
                            Status: <select name="status" style="color: black">
                                <option value="-1">--Status--</option>
                                <option value="1">Active</option>
                                <option value="0">InActive</option>
                            </select>
                            <button style="color: black" type="submit">Filter</button>
                        </form>
                    </div>
                </div>
                <c:if test="${message != null}">
                    <h2>${message}</h2>
                </c:if>
                <c:if test="${message == null}">
                    <table id="tableData" class="table table-striped table-hover">
                        <thead>
                            <tr>                            
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Gender</th>
                                <th>Phone number</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th>Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="o" items="${listC}">
                                <tr>                               
                                    <td>${o.user_id}</td>
                                    <td>${o.full_name}</td>
                                    <td>${o.email}</td>
                                    <td>${o.gender ? "Male" : "Fermale"}</td>
                                    <td>${o.phone_number}</td>
                                    <td>${o.role.role_name}</td>
                                    <td>${o.status ? "Active" : "Inactive"}</td>
                                    <td><a  href="userdetail?uid=${o.user_id}"><button type="button" class="btn btn_detail "><i class="fa-solid fa-eye"></i>
                                            </button> </a></td>
                                </tr>
                            </c:forEach>                           
                        </tbody>
                    </table>    
                </c:if>
            </div>
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
