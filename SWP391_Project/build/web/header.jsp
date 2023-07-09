<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/js/bootstrap.min.js" integrity="sha512-1/RvZTcCDEUjY/CypiMz+iqqtaoQfAITmNSJY17Myp4Ms5mdxPS5UV7iOfdZoxcGhzFbOm6sntTKJppjvuhg4g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA==" crossorigin="anonymous" referrerpolicy="no-referrer" />     
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<link href="CSS/styles.css" rel="stylesheet" type="text/css"/>

<!-- Login Modal -->
<style>
    #navbarText ul.navbar-nav>li{
        position: relative;
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
<div class="modal" id="loginModal" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Login</h5>
                <button type="button" class="close" onclick="hideModal('loginModal')">&times;</button>
            </div>
            <div class="modal-body">
                <form method="POST" action="login" >
                    <div class="form-outline mb-4">
                        <label for="username">Username</label>
                        <input class="form-control" type="text" name="username" required="" placeholder="Enter username">
                    </div>
                    <div class="form-outline mb-4">
                        <label for="password">Password</label>
                        <input class="form-control" type="password" name="password"  required="" placeholder="Enter password">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block mb-4">Login</button>
                    <a href="#" class="tm-register" onclick="showDoNotAccountOrLogin('registerModal', 'loginModal')">
                        <i>Haven't have account yet?</i>
                    </a>
                    <a href="forgotPassword.jsp" class="tm-register">
                        <i>Remember me?</i>
                    </a>

                    ${message}
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="hideModal('loginModal')">Close</button>
            </div>
        </div>
    </div>
</div>


<!-- Register Modal -->
<div class="modal" id="registerModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Register</h5>
            </div>
            <div class="modal-body">
                <form method="POST" action="register">
                    <div class="form-outline mb-4">
                        <label for="email">Email</label>
                        <input class="form-control" type="email" name="email" required="" placeholder="Enter email">
                    </div>
                    <div class="form-outline mb-4">
                        <label for="password">Password</label>
                        <input class="form-control" type="password" name="password" required="" placeholder="Enter password">
                    </div>
                    <div class="form-outline mb-4">
                        <label for="username">Full name</label>
                        <input class="form-control" type="text" name="fullname" required="" placeholder="Enter Full Name">
                    </div>
                    <div>
                        <label>Gender</label>
                        <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" name="gender">
                            <option value="1" selected>Male</option>
                            <option value="0">Female</option>
                        </select>
                    </div>
                    <div class="form-outline mb-4">
                        <label for="username">Address</label>
                        <input class="form-control" type="text" name="address" required="" placeholder="Enter email">
                    </div>
                    <div class="form-outline mb-4">
                        <label for="username">Phone Number</label>
                        <input class="form-control" type="number" name="phone_number" required="" placeholder="Enter Phone Number">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block mb-4">Register</button>                    
                    ${messageRegister}
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="hideModal('registerModal')">Close</button>
            </div>
        </div>
    </div>
</div>

<nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
    <div class="container-fluid" style="padding: 10px; background-color: white; box-shadow:0 -4px 25px 0 blue;
         position: fixed; top: 0; left: 0;">
        <a class="navbar-brand" href="home" style="color: var(--primary);font-size: 2.7rem;font-weight: 600;padding: 0.2rem;border: 3px solid var(--black); border-radius: 15px;">Online Learning</a>

        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="margin: auto;">
                <li class="nav-item">
                    <c:if test="${user!=null}"><h1 style="margin-top:23px;">Welcome, ${sessionScope.user.getFull_name()}</h1>
                        <ul>         
                            <c:if test="${user!=null}">
                                <li><a href="userProfile.jsp">User profile</a></li>  
                                <li><a href="logout">Logout</a></li>  
                                <li><a href="changePassword.jsp">Change Password</a></li>  
                                </c:if>                                                                            
                        </ul>
                    </c:if>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="courses" style="font-size: 20px; margin-left: 20px;">Course</a>
                    <ul>         
                        <c:if test="${user!=null}">
                            <li><a href="myListCourse">My List Course</a></li>
                            <li><a href="myCourse">My Course</a></li>  
                            <li><a href="myRegistrations">My Registration</a></li>  
                            </c:if>                                                                            
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="blog" style="font-size: 20px; margin-left: 20px;">Blog</a>                       
                </li>
                <c:if test="${sessionScope.user==null}">
                    <li class="nav-item">
                        <a class="nav-link" href="#" onclick="showModal('loginModal')" style="font-size: 20px; margin-left: 20px;">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" onclick="showModal('registerModal')" style="font-size: 20px; margin-left: 20px;">Register</a>
                    </li>
                </c:if>   
                <c:if test="${sessionScope.user.role_id == 4 || sessionScope.user.role_id == 5}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="manageCourse">Manage Course</a>
                        <ul>         
                            <li><a href="questions">Manage Question</a></li>
                            <li><a href="manageExams">Manage Exam</a></li>  
                            <li><a href="registrations">Registration</a></li>  
                        </ul>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.role_id == 4}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="subject">Subject List</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.role_id==5}">
                    <li class="nav-item">
                        <a class="nav-link" href="userList" style="font-size: 20px; margin-left: 20px;">Manage user</a>
                    </li>                    
                </c:if>  
                <c:if test="${sessionScope.user.role_id==5}">
                    <li class="nav-item">
                        <a class="nav-link" href="settingList" style="font-size: 20px; margin-left: 20px;">Manage list</a>
                    </li>                    
                </c:if>  
                <c:if test="${sessionScope.user.role_id == 2}">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page">Blog Edit</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
                <div style=""></div>
<script>
    function showModal(modalId) {
        document.getElementById(modalId).style.display = "block";
    }

    function hideModal(modalId) {
        document.getElementById(modalId).style.display = "none";
    }

    function showDoNotAccountOrLogin(modalIdOpen, modalIdClose) {
        hideModal(modalIdClose)
        showModal(modalIdOpen)
    }

</script>

