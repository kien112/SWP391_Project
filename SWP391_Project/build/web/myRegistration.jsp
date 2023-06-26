

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="CSS/myRegis.css" rel="stylesheet" type="text/css"/>            
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            @import url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap');

            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                list-style: none;
                text-decoration: none;
            }

            body{
                background-color: #f3f5f9;
            }

            .wrapper{
                display: flex;
                position: relative;
                margin-top: 100px;
            }

            .wrapper .sidebar{
                width: 200px;
                height: 100%;
                background: white;
                padding: 30px 0px;
                position: fixed;
            }

            .wrapper .sidebar h2{
                color: black;
                text-transform: uppercase;
                text-align: center;
                margin-bottom: 30px;
            }

            .wrapper .sidebar ul li{
                padding: 15px;
                border-bottom: 1px solid #bdb8d7;
                border-bottom: 1px solid rgba(0,0,0,0.05);
                border-top: 1px solid rgba(255,255,255,0.05);
            }

            .wrapper .sidebar ul li a{
                color: black;
                display: block;
            }

            .wrapper .sidebar ul li a .fas{
                width: 25px;
            }

            .wrapper .sidebar ul li:hover{
                background-color: #594f8d;
            }

            .wrapper .sidebar ul li:hover a{
                color: #fff;
            }

            .wrapper .sidebar .social_media{
                position: absolute;
                bottom: 0;
                left: 50%;
                transform: translateX(-50%);
                display: flex;
            }

            .wrapper .sidebar .social_media a{
                display: block;
                width: 40px;
                background: #594f8d;
                height: 40px;
                line-height: 45px;
                text-align: center;
                margin: 0 5px;
                color: #bdb8d7;
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
            }
            #navbarText ul.navbar-nav{
                
            }
            #navbarText ul.navbar-nav>li{
                position: relative;                     
            }
            #navbarText ul.navbar-nav>li:nth-child(1){
                margin-bottom: -30px;               
            }
            #navbarText ul.navbar-nav>li:nth-child(2){
                margin-bottom: -37px;               
            }

            #navbarText ul.navbar-nav>li>ul{
                position: absolute;
                background-color: white;
                list-style: none;
                left: 20px;
                width: 150px;
                display: none;
                font-size: 13px;
                color: black;           
            }

            #navbarText ul.navbar-nav>li:hover>ul{
                display: block;
            }

            #navbarText ul.navbar-nav>li>ul>li{
                padding: 10px;
                border-bottom: 1px solid #fff;
               
            }
        </style>
    <body>
        <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>

        <div class="wrapper">
            <div class="sidebar" style="height: 40%; margin-top: 20px;box-shadow:0 -3px 3px 0 blue; margin-left: 10px;">                
                <h2>Sider</h2>
                <ul>
                    <li>
                        <form action="searchRC" method="post">
                            <div style="display: flex;">
                                <input type="text" name="searchRC" placeholder="Course name" style="width: 85%;">
                                <button><i class="fa-solid fa-magnifying-glass"></i></button>
                            </div>
                        </form>
                    </li>
                    <li>
                        <form action="sortRC" method="post">
                            <select name="opC" onchange="this.form.submit()">
                                <option value="all">All</option>
                                <option value="op1">New release</option>
                                <option value="op2">Price increase</option>
                                <option value="op3">Price descend</option>
                                <option value="op4">Done</option>
                                <option value="op5">Submitted</option>
                            </select>(Sort) 
                        </form>
                    </li>
                </ul>                 
            </div>
        </div>
    </body>
    <div class="container-fluid" style="padding: 10px; background-color: white; box-shadow:0 -4px 25px 0 blue; padding: 0;
         position: fixed; top: 0; left: 0; width: 100%; height: 95px;">
        <a class="navbar-brand" href="home" style="font-size: 27px;border: 3px solid var(--black); border-radius: 15px; color: #0397d6; height: auto; padding: 15px 0 15px; font-weight: 600;margin-top: 10px; margin-left: 10px;">Online Learning</a>

        <div class="collapse navbar-collapse" id="navbarText" style="">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <c:if test="${user!=null}"><h1 style="margin-left: 460px; margin-top: 33px;">Welcome, ${sessionScope.user.getFull_name()}</h1>

                    </c:if>
                </li>
                <li class="nav-item">
                    <a  href="courses" style="font-size: 20px; color: black; margin-left: 650px; margin-top: -40px; margin-bottom: 10px">Course</a>
                    <ul style="margin-left: 630px;" >         
                        <c:if test="${user!=null}">
                            <li><a href="myCourse">My Course</a></li>  
                            <li><a href="myRegistration">My Registration</a></li>  
                            </c:if>                                                                            
                    </ul>              
                </li>
                <li>
                    <a class="nav-link active" aria-current="page" href="blog" style="font-size: 20px; margin-left: 750px; color: black;">Blog</a>                       
                </li>

            </ul>
        </div>
    </div>
    <body>
        <div class="magic"></div>
        <div class="container"  style="margin-right: 2px;">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Course Registration</b></h2>
                        </div>                       
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>                            
                            <th>ID</th>
                            <th>Course</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Registration time</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="o" items="${listC}">
                            <tr>                               
                                <td>${o.user_id.user_id}</td>
                                <td>${o.course_id.name}</td>
                                <td>
                                    <img src="${o.course_id.image}">
                                </td>
                                <td>${o.price_package_id.price}</td>
                                <td>${o.created}</td>
                                <td>${o.regis_status}</td>
                                <td>                                   
                                    <form action="deleteRegis" method="post" id="deleteForm" onsubmit="return confirm('Are you sure?');" >
                                        <div class="modal-footer">
                                            <c:if test="${o.regis_status == 'Submitted'}">
                                                <input type="hidden" name="res_id" value="${o.registration_id}">
                                                <button >Delete</button><button >Update</button>                                                
                                            </c:if>                                       
                                        </div>
                                    </form>                                  
                                </td>
                            </tr>
                        </c:forEach>                           
                    </tbody>
                </table>               
            </div>
        </div>

        <div class="modal" id="registerCourseModal" >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Register Course</h5>

                    </div>
                    <div class="modal-body">
                        <form method="POST" action="updateRegis">
                            <input type="hidden" value="${course.course_id}" name="courseId">
                            Choose your price packages: 
                            <select name="price_packageId">
                                <c:forEach items="${price_packages}" var="pp">
                                    <option value="${pp.id}">${pp.price}/${pp.duration}</option>
                                </c:forEach>
                            </select>
                            <button>Update</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" onclick="hideModal('registerCourseModal')">Close</button>
                    </div>
                </div>
            </div>

        </div>
        <jsp:include page="footer.jsp"/>
        <script>
            function showModal(modalId) {
                document.getElementById(modalId).style.display = "block";
            }

            function hideModal(modalId) {
                document.getElementById(modalId).style.display = "none";
            }
            function confirmDelete() {
                var result = confirm("Are you sure you want to delete?");
                if (result) {
                    document.getElementById("deleteForm").submit();
                } else {
                    return false;
                }
            }
        </script>

    </body>
</html>