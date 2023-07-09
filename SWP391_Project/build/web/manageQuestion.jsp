

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
        <div style="margin-top: 60px; margin-left: 50px">
            <h4>Filter & Search:</h4>
            <form action="questions" method="post">
                <input value="filterAndSearch" name="action" hidden=""/>
                <label class="fs-3">Level: </label>
                <select name="level">
                    <option value="-1" ${-1==levelId?"selected":""}>--Level--</option>
                    <c:forEach items="${listLevels}" var="l">
                        <option value="${l.id}" ${l.id==levelId?"selected":""}>${l.level}</option>
                    </c:forEach> 
                </select>
                <label class="fs-3">Subject: </label>
                <select name="subject">
                    <option value="-1" ${-1==subjectId?"selected":""}>--Subject--</option>
                    <c:forEach items="${listS}" var="s">
                        <option value="${s.id}" ${s.id==subjectId?"selected":""}>${s.name}</option>
                    </c:forEach>
                </select>
                <label class="fs-3">Lesson: </label>
                <select name="lesson">
                    <option value="-1" ${-1==lessonId?"selected":""}>--Lesson--</option>
                    <c:forEach items="${listL}" var="s">
                        <option value="${s.id}" ${s.id==lessonId?"selected":""}>${s.name}</option>
                    </c:forEach>
                </select>
                <label class="fs-3">Dimension: </label>
                <select name="dimension">
                    <option value="-1" ${-1==dimensionId?"selected":""}>--Dimension--</option>
                    <c:forEach items="${listD}" var="s">
                        <option value="${s.id}" ${s.id==dimensionId?"selected":""}>${s.name}</option>
                    </c:forEach>
                </select>
                <label class="fs-3">Status: </label>
                <select name="status">
                    <option value="-1" ${status==-1?"selected":""}>--Status--</option>
                    <option value="1" ${status==1?"selected":""}>Active</option>
                    <option value="0" ${status==0?"selected":""}>InActive</option>
                </select>
                <label class="fs-3">Content: </label>
                <textarea name="searchContent">${searchContent}</textarea> 
                <button type="submit" class="btn btn-primary">Filter & Search</button>
            </form>
        </div>
        
        <!-- Button to Open the Modal create lesson-->
        <button type="button" class="m-4 btn btn-primary" data-toggle="modal" data-target="#myModal">
            Add New Question
        </button>
        
        <button type="button" class="m-4 btn btn-primary" data-toggle="modal" data-target="#importQuestion">
            Import Question
        </button>
        <a href="./excel/Book1.xlsx" download>Download Template</a>
        
         <!-- The Modal Create -->
        <div  class="modal" id="importQuestion">
            <div  class="modal-dialog">
                <div style="width: 700px" class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Import Question</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="questions" method="post" enctype="multipart/form-data">
                            <input name="action" value="importQuestion" hidden=""/>
                            <div class="form-group">
                                <label for="pwd">Lesson:</label>
                                <select name="lesson" class="form-control">
                                    <c:forEach items="${listL}" var="l">
                                        <option value="${l.id}">${l.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Excel File: </label>
                                <input name="file" type="file" />
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
        <div  class="modal" id="myModal">
            <div  class="modal-dialog">
                <div style="width: 700px" class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Create Question</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="questions" method="post">
                            <input name="action" value="create" hidden=""/>
                            <div class="form-group">
                                <label for="email">Content:</label>
                                <textarea class="form-control" name="content" required=""></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Option A:</label>
                                <textarea class="form-control" name="option_a" required=""></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Option B:</label>
                                <textarea class="form-control" name="option_b" required=""></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Option C:</label>
                                <textarea class="form-control" name="option_c"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Option D:</label>
                                <textarea class="form-control" name="option_d"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Answer:</label>
                                <textarea class="form-control" name="answer" required=""></textarea>
                                <p id="message"></p>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Explanation:</label>
                                <textarea class="form-control" name="explanation"></textarea>
                            </div>
                            
                            <div class="form-group">
                                <label for="pwd">Level:</label>
                                <select name="level" class="form-control">
                                    <c:forEach items="${listLevels}" var="l">
                                        <option value="${l.id}">${l.level}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Lesson:</label>
                                <select name="lesson" class="form-control">
                                    <c:forEach items="${listL}" var="l">
                                        <option value="${l.id}">${l.name}</option>
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
        
         <!-- The Modal Update -->
        <div  class="modal" id="editQuestion">
            <div  class="modal-dialog">
                <div style="width: 700px" class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Update Question</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="questions" method="post">
                            <input name="action" value="update" hidden=""/>
                            <input name="id" hidden="" id="id"/>
                            <div class="form-group">
                                <label for="email">Content:</label>
                                <textarea id="content" class="form-control" name="content" required=""></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Option A:</label>
                                <textarea id="op_a" class="form-control" name="option_a" required=""></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Option B:</label>
                                <textarea id="op_b" class="form-control" name="option_b" required=""></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Option C:</label>
                                <textarea id="op_c" class="form-control" name="option_c"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Option D:</label>
                                <textarea id="op_d" class="form-control" name="option_d"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Answer:</label>
                                <textarea id="answer" class="form-control" name="answer" required=""></textarea>
                                <p id="message"></p>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Explanation:</label>
                                <textarea id="explanation" class="form-control" name="explanation"></textarea>
                            </div>
                            
                            <div class="form-group">
                                <label for="pwd">Level:</label>
                                <select id="level" name="level" class="form-control">
                                    <c:forEach items="${listLevels}" var="l">
                                        <option value="${l.id}">${l.level}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pwd">Lesson:</label>
                                <select id="lesson" name="lesson" class="form-control">
                                    <c:forEach items="${listL}" var="l">
                                        <option value="${l.id}">${l.name}</option>
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
        
        <table class="table-striped table-bordered fs-3" style="margin-bottom: 100px;" id="tableData">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Content</th>
                    <th>Options</th>
                    <th>Answer</th>
                    <th>Explanation</th>
                    <th>Level</th>
                    <th>Lesson</th>
                    <th>Subject</th>
                    <th>Dimension</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listQ}" var="q">
                    <tr>
                        <td>${q.id}</td>
                        <td>${q.content}</td>
                        <td>
                            <input value="${q.option_a}" hidden=""/>
                            <input value="${q.option_b}" hidden=""/>
                            <input value="${q.option_c}" hidden=""/>
                            <input value="${q.option_d}" hidden=""/>
                            <input value="${q.level.id}" hidden=""/>
                            <input value="${q.lesson.id}" hidden=""/>
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
                        <td>${q.explanation}</td>
                        <td>${q.level.level}</td>
                        <td>${q.lesson.name}</td>
                        <td>${q.subject.name}</td>
                        <td>${q.subject.dimension.name}</td>
                        <td>${q.status?"Active":"InActive"}</td>
                        <td>
                            <button type="button" class="btnEdit m-4 btn btn-primary" data-toggle="modal" data-target="#editQuestion">
                                Edit
                            </button>
                            <a href="questions?action=changeStatus&qid=${q.id}">
                                <button class="btn btn-secondary">
                                    ${q.status?"InActive":"Active"}
                                </button>
                            </a>
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
                   var content = parent.children().eq(1).text();
                   var ans = parent.children().eq(3).text();
                   var exp = parent.children().eq(4).text();
                   var td = parent.children().eq(2);
                   var op_a = td.children().eq(0).val();
                   var op_b = td.children().eq(1).val();
                   var op_c = td.children().eq(2).val();
                   var op_d = td.children().eq(3).val();
                   var level = td.children().eq(4).val();
                   var lesson = td.children().eq(5).val();

                   $('#id').val(id);
                   $('#content').val(content);
                   $('#answer').val(ans);
                   $('#explanation').val(exp);
                   $('#op_a').val(op_a);
                   $('#op_b').val(op_b);
                   $('#op_c').val(op_c);
                   $('#op_d').val(op_d);
                   $('#level').val(level);
                   $('#lesson').val(lesson);
                });
                
            });
            
        </script>
    </body>
</html>
