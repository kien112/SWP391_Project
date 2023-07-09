<%-- 
    Document   : manageRegistrations
    Created on : Jul 8, 2023, 9:47:24 PM
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
        <div style="margin-top: 70px">
            <h4>Filter & Search:</h4>
            <form action="registrations" method="post">
                <input value="filterAndSearch" name="action" hidden=""/>
                    <label>Course: </label>
                    <input name="course" value="${course}" type="text"/>
              
                    <label>Email: </label>
                    <input name="email" value="${email}" type="text"/>
              
                    <label>From Date: </label>
                    <input type="date" name="fromDate" value="${fromDate}" required=""/>
              
                    <label>To Date: </label>
                    <input name="toDate" type="date" value="${toDate}" required=""/>
               
                    <label>Status: </label>
                    <select name="status">
                        <option value="-1" ${status==-1?"selected":""}>--Status--</option>
                        <option value="0" ${status==0?"selected":""}>Cancel</option>
                        <option value="1" ${status==1?"selected":""}>Submit</option>
                        <option value="2" ${status==2?"selected":""}>Success</option>
                    </select>
                <button class="btn btn-primary">Filter & Search</button>
            </form>
        </div>
        
         <!-- Button to Open the Modal create lesson-->
        <button type="button" class="m-4 btn btn-primary" data-toggle="modal" data-target="#myModal">
            Add New Registration
        </button>
        
        <!-- The Modal Create -->
        <div  class="modal" id="myModal">
            <div  class="modal-dialog">
                <div style="width: 700px" class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Create Registration</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="registrations" method="post">
                            <input name="action" value="addRegistration" hidden=""/>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input class="form-control" name="email" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Course: </label>
                                <select name="courseId">
                                    <c:forEach items="${listC}" var="c">
                                        <option value="${c.course_id}">${c.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Price Package: </label>
                                <select name="price_packageId">
                                    <c:forEach items="${listP}" var="p">
                                        <option value="${p.id}">${p.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div> 
        
        
         <!-- The Modal Create -->
        <div  class="modal" id="editRegistration">
            <div  class="modal-dialog">
                <div style="width: 700px" class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Update Registration</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="registrations" method="post">
                            <input name="action" value="updateRegistration" hidden=""/>
                            <input id="id" name="rid" hidden=""/>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input class="form-control" name="email" id="email" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Course: </label>
                                <select name="courseId" id="courseId">
                                    <c:forEach items="${listC}" var="c">
                                        <option value="${c.course_id}">${c.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Price Package: </label>
                                <select name="price_packageId" id="ppId">
                                    <c:forEach items="${listP}" var="p">
                                        <option value="${p.id}">${p.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div> 
         
        <table id="tableData">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Registration Time</th>
                    <th>Course</th>
                    <th>Package</th>
                    <th>Cost</th>
                    <th>Status</th>
                    <th>Valid From</th>
                    <th>Valid To</th>
                    <th>Update By</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${listR}" var="r">
                <tr>
                    <td>${r.id}</td>
                    <td>${r.email}</td>
                    <td>${r.registration_time}</td>
                    <td>${r.course_name}</td>
                    <td>${r.pricePackage.name}</td>
                    <td>${r.pricePackage.sale}</td>
                    <td>
                        ${r.status==0?"Cancel":
                        r.status==1?"Submit":"Success"}
                    </td>
                    <td>${r.valid_from}</td>
                    <td>${r.valid_to}</td>
                    <td>${r.update_by}</td>
                    <td>
                        <input value="${r.course_id}" hidden=""/>
                        <input value="${r.pricePackage.id}" hidden=""/>
                        <button type="button" class="btnEdit m-4 btn btn-primary" data-toggle="modal" data-target="#editRegistration">
                            Edit
                        </button>
                        <c:if test="${r.status==1}">
                            <a href="registrations?action=changeStatus&rid=${r.id}&status=Success">
                                Success
                            </a>
                            <a href="registrations?action=changeStatus&rid=${r.id}&status=Cancel">
                                Cancel
                            </a>
                        </c:if>
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
                
                $('.btnEdit').click(function (){
                   var par = $(this).parent();
                   var parent = par.parent();
                   var id = parent.children().eq(0).text();
                   var email = parent.children().eq(1).text();
                   var courseId = par.children().eq(0).val();
                   var pId = par.children().eq(1).val();
                   
                   $('#id').val(id);
                   $('#email').val(email);
                   $('#courseId').val(courseId);
                   $('#ppId').val(pId);
                });
            });
        </script>
    </body>
</html>
