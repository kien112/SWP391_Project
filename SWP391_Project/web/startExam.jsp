
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
        <div style="margin-left: 300px" class="time-out">
            <input hidden="" id="duration" value="${exam.duration}"/>
            <span id="timer"></span><span id="view-duration">/ ${exam.duration} minutes</span>
        </div>
        <div id="contentExam" style="margin: 100px 300px;">
            <c:forEach items="${listQ}" var="q">
                <div class="row card mb-3">
                    <div class="col-md-12">Question ${q.order}: ${q.content}</div>
                    <div class="row">
                        <div class="col-md-6">
                            <input type="radio" name="selectOption-${q.id}" value="${q.option_a}"/>
                            ${q.option_a}
                        </div>
                        <div class="col-md-6">
                            <input type="radio" name="selectOption-${q.id}" value="${q.option_b}"/>
                            ${q.option_b}
                        </div>
                        <c:if test="${q.option_c != null}">
                            <div class="col-md-6">
                                <input type="radio" name="selectOption-${q.id}" value="${q.option_c}"/>
                                ${q.option_c}
                            </div>
                        </c:if>
                        <c:if test="${q.option_d != null}">
                            <div class="col-md-6">
                                <input type="radio" name="selectOption-${q.id}" value="${q.option_d}"/>
                                ${q.option_d}
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
            <button id="btnSubmit" class="btn btn-success">Submit</button>
        </div>
     <!--phân trang-->
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
        var time = 0;
        $(document).ready(function () {
            $('#btnSubmit').click(function (){
                if(confirm('Do you want to finish exam?') === true){
                    GetResult();
                }
            });
        });
      
      
        var minutes = 39, seconds = 55, time;
        function Start() {
            var x = document.getElementById("duration").value;
            if (minutes + "" === x) {
                GetResult();
                clearInterval(count);
            } else {
                seconds++;
                if (seconds === 60) {
                    minutes++;
                    seconds = 0;
                }
                document.getElementById("timer").innerHTML = "Duration: "+ minutes + " minutes : " + seconds + " seconds";
            }

        }
        var count = setInterval('Start()', 1000);
            
        function GetResult(){
            var selectOptions = $('input[type=radio]');
            var op = [];
            var id = [];
            var idx = 0;
            for(let i = 0; i < selectOptions.length; i++){
                if(selectOptions[i].checked){
                    id[idx] = selectOptions[i].name;
                    op[idx++] = selectOptions[i].value;
                }
            }
            if(idx===0){
                op[0] = "no select ans";
                id[0] = "op--10";
            }
            $.ajax({
                type: "POST",
                url: "/SWP391_Project/practiceExam",
                data: {
                    id:id,
                    selectOp: op,
                    action: 'getResult'
                },
                success: function (data) {
                    $('#contentExam').html(data);
                }
            });
        }
   
    </script>
    </body>
</html>
