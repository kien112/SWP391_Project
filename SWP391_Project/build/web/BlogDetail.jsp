<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="CSS/userprofile.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container" style="margin-top: 10%">
            <div class="main-body">             
                <div class="row gutters-sm">
                    <div class="col-md-4 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex flex-column align-items-center text-center">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" class="rounded-circle" width="250">                                    
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="card mb-3">
                            <div class="card-body">
                                <div class="row">
                                    <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Full Name:</h6>
                                    <input type="text" class="fullname" value="${sessionScope.user.getFull_name()}">
                                </div>
                                <hr>
                                <div class="row">
                                    <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Email</h6>
                                    <input type="text" class="fullname" value="${sessionScope.user.getEmail()}">
                                </div>
                                <hr>
                                <div class="row">
                                    <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Phone</h6>
                                    <input type="text" class="fullname" value="${sessionScope.user.getPhone_number()}">

                                </div>
                                <hr>
                                <div class="row">
                                        <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Gender</h6>
                                        <input type="text" class="fullname" value="">
                                </div>
                                <hr>
                                <div class="row">
                                        <h6 class="mb-0" style="margin-left: 15px; font-size: 20px">Address</h6>
                                        <input type="text" class="fullname" value="${sessionScope.user.getAddress()}">
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <a class="btn btn-info" style="font-size: 20px">Save change</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
