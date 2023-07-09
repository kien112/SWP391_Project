

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
    <body class="fs-3">
        <jsp:include page="header.jsp" />  
        <div style="margin-top: 80px">
            <h4>Filter And Search: </h4>
            <form action="manageExams" method="post">
                <input value="filterAndSearch" name="action" hidden=""/>
                <label>Subject: </label>
                <select name="subject" >
                    <option value="-1" ${-1==subjectId?"selected":""}>--Subject--</option>
                    <c:forEach items="${listS}" var="s">
                        <option value="${s.id}" ${s.id==subjectId?"selected":""}>${s.name}</option>
                    </c:forEach>
                </select>
                <label for="pwd">Type: </label>
                <select name="type">
                    <option value="-1" ${-1==typeId?"selected":""}>--Type--</option>
                    <c:forEach items="${listET}" var="et">
                        <option value="${et.id}" ${et.id==typeId?"selected":""}>${et.name}</option>
                    </c:forEach>
                </select>
                <label>Name: </label>
                <input name="searchName" value="${searchName}"/>
                <button class="btn btn-info" type="submit">Filter & Search</button>
            </form>
        </div>
        <!-- Button to Open the Modal create lesson-->
        <button type="button" class="m-4 btn btn-primary" data-toggle="modal" data-target="#myModal">
            Add New Question
        </button>
        
         <!-- The Modal Create -->
        <div  class="modal" id="myModal">
            <div  class="modal-dialog">
                <div style="width: 700px" class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Create Quiz</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="manageExams" method="post">
                            <input name="action" value="create" hidden=""/>
                            <div class="form-group">
                                <label for="email">Name:</label>
                                <input name="name" class="form-control" type="text" required=""/>
                            </div>
                            <div class="form-group">
                                <label for="email">Duration:</label>
                                <input name="duration" class="form-control" type="number" min="10" required=""/>
                            </div>
                            <div class="form-group">
                                <label for="email">Pass Rate:</label>
                                <input name="passRate" class="form-control" step="0.01" type="number" min="1" required=""/>
                            </div>
                            <div class="form-group">
                                <label for="email">Number Of Question:</label>
                                <input name="numberOfQuestion" class="form-control" type="number" min="1" required=""/>
                            </div>
                            <div class="form-group">
                                <label for="email">Description:</label>
                                <textarea name="description" class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Level:</label>
                                <select name="level" class="form-control">
                                    <c:forEach items="${listL}" var="l">
                                        <option value="${l.id}">${l.level}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Subject:</label>
                                <select name="subject" class="form-control">
                                    <c:forEach items="${listS}" var="s">
                                        <option value="${s.id}">${s.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Type:</label>
                                <select name="type" class="form-control">
                                    <c:forEach items="${listET}" var="et">
                                        <option value="${et.id}">${et.name}</option>
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
        <div  class="modal" id="updateExam">
            <div  class="modal-dialog">
                <div style="width: 700px" class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Update Quiz</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="manageExams" method="post">
                            <input name="action" value="update" hidden=""/>
                            <input name="id" id="id" hidden=""/>
                            <div class="form-group">
                                <label for="email">Name:</label>
                                <input name="name" id="name" class="form-control" type="text" required=""/>
                            </div>
                            <div class="form-group">
                                <label for="email">Duration:</label>
                                <input name="duration" id="duration" class="form-control" type="number" min="10" required=""/>
                            </div>
                            <div class="form-group">
                                <label for="email">Pass Rate:</label>
                                <input name="passRate" id="passRate" step="0.01" class="form-control" type="number" min="1" required=""/>
                            </div>
                            <div class="form-group">
                                <label for="email">Number Of Question:</label>
                                <input name="numberOfQuestion" id="numberOfQuestion" class="form-control" type="number" min="1" required=""/>
                            </div>
                            <div class="form-group">
                                <label for="email">Description:</label>
                                <textarea name="description" id="description" class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Level:</label>
                                <select name="level" class="form-control" id="level">
                                    <c:forEach items="${listL}" var="l">
                                        <option value="${l.id}">${l.level}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Subject:</label>
                                <select name="subject" class="form-control" id="subject">
                                    <c:forEach items="${listS}" var="s">
                                        <option value="${s.id}">${s.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Type:</label>
                                <select name="type" class="form-control" id="type">
                                    <c:forEach items="${listET}" var="et">
                                        <option value="${et.id}">${et.name}</option>
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
         
         <!-- The Modal add question -->
        <div  class="modal" id="addListQuestion">
            <div  class="modal-dialog">
                <div style="width: 800px; margin-left: -300px" class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Add List Question</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body" >
                        <label>Number of Selected Question: <span id="numQ"></span></label>
                        <form action="manageExams" method="post">
                            <input name="action" value="addListQuestion" hidden=""/>
                            <input name="eid" id="eid" hidden=""/>
                            <table id="tableData2">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>Content</th>
                                        <th>Options</th>
                                        <th>Answer</th>
                                        <th>Order</th>
                                        <th>Mark</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listQ}" var="q">
                                        <tr>
                                            <td>
                                                <input class="btnCheckBox" type="checkbox" name="id" value="${q.id}"/>
                                            </td>
                                            <td>${q.content}</td>
                                            <td>
                                                <ol>
                                                    <c:if test="${!q.option_a.isEmpty()}">
                                                        <li><b>A:</b> ${q.option_a}</li>
                                                    </c:if>
                                                    <c:if test="${!q.option_b.isEmpty()}">
                                                        <li><b>B:</b> ${q.option_b}</li>
                                                    </c:if>
                                                    <c:if test="${!q.option_c.isEmpty()}">
                                                        <li><b>C:</b> ${q.option_c}</li>
                                                    </c:if>
                                                    <c:if test="${!q.option_d.isEmpty()}">
                                                        <li><b>D:</b> ${q.option_d}</li>
                                                    </c:if>
                                                </ol>
                                            </td>
                                            <td>${q.answer}</td>
                                            <td>
                                                <input class="${q.id}" name="order-${q.id}" type="number" min="1" style="width: 50px"/>
                                            </td>
                                            <td>
                                                <input class="${q.id}" name="mark-${q.id}" type="number" min="0.1" step="0.01" style="width: 50px"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <p id="message"></p>
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
         
        <table id="tableData" class="mb-5 table-bordered table-striped">
            <thead>
                <tr>
                    <th>ID</th>
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
                        <td>${e.id}</td>
                        <td>${e.name}</td>
                        <td>${e.subject_name}</td>
                        <td>${e.level.level}</td>
                        <td>${e.duration}</td>
                        <td>${e.pass_rate}</td>
                        <td>${e.number_of_question}</td>
                        <td>${e.description}</td>
                        <td>${e.examType.name}</td>
                        <td>
                            <input value="${e.subject_id}" hidden=""/>
                            <input value="${e.level.id}" hidden=""/>
                            <input value="${e.examType.id}" hidden=""/>
                            <button type="button" class="btnUpdate m-4 btn btn-primary" data-toggle="modal" data-target="#updateExam">
                                Edit
                            </button>
                            <button class="btn">
                                <a href="manageExams?action=changeMode&eid=${e.id}" >${e.mode ? "InActive" : "Active"}</a>
                            </button>
                            <button type="button" class="btnAddList m-4 btn btn-primary" data-toggle="modal" data-target="#addListQuestion">
                                Add List Question
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div style="margin-bottom: 270px"></div>
        <jsp:include page="footer.jsp" />
        <!--phân trang-->
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script>
            var numQ = 0;
            $(document).ready(function () {
                $('#tableData,#tableData2').DataTable({
                    pagingType: 'full_numbers',
                    searching: false
                });
                
                $('.btnUpdate').click(function (){
                    var par = $(this).parent();
                    var subjectId = par.children().eq(0).val();
                    var levelId = par.children().eq(1).val();
                    var typeId = par.children().eq(2).val(); 
                    var parent = par.parent();
                    var id = parent.children().eq(0).text();
                    var name = parent.children().eq(1).text();
                    var duration = parent.children().eq(4).text();
                    var passRate = parent.children().eq(5).text();
                    var numberOfQuestion = parent.children().eq(6).text();
                    var description = parent.children().eq(7).text();
                    
                    $('#id').val(id);
                    $('#name').val(name);
                    $('#duration').val(duration);
                    $('#passRate').val(passRate);
                    $('#numberOfQuestion').val(numberOfQuestion);
                    $('#subject').val(subjectId);
                    $('#level').val(levelId);
                    $('#type').val(typeId);
                    $('#description').val(description);
                });
                
                $('.btnCheckBox').click(function (){
                   var id = $(this).val();
                   $('.'+id).attr("required", $(this).prop("checked"));
                   numQ = $(this).prop("checked") ? numQ+1 : numQ-1;
                   $('#numQ').text(numQ);
                });
                
                $('.btnAddList').click(function (){
                    var par = $(this).parent();
                    var parent = par.parent();
                    var id = parent.children().eq(0).text();
                    $('#eid').val(id);
                });
            });
        </script>
    </body>
</html>
