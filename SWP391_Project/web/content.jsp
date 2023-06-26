<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="CSS/styles.css" rel="stylesheet" type="text/css"/>

<div class="container px-4 px-lg-5">


    <!-- Call to Action-->
    <div style="margin-top: 400px;"></div>
    <div class="card text-white bg-secondary my-5 py-4 text-center">
        <div class="card-body"><h1 class="text-white m-0" style="font-size: 50px;">Course list</h1></div>
    </div>
    <!-- Content Row-->
    <form action="searchCourse" method="post">
        <div class="search" style="margin-bottom: 10px;">
            <input type="text" name="search" style=" padding: 5px;">
            <input type="submit" value="Search" style=" padding: 5px;">
        </div>
    </form>

    <div class="row gx-4 gx-lg-5">
        <c:forEach items="${listC}" var="o">
            <div class="col-md-4 mb-5">
                <div class="card h-100">
                    <div class="card-body">
                        <h2 class="card-title">${o.name}</h2>
                        <p class="card-text">${o.brief_infor}</p>
                        <div class="col-lg-7"><img class="img-fluid mb-4 mb-lg-0" src="${o.image}" alt="..." /></div>      
                    </div>
                    <div class="card-footer"><a class="btn btn-primary btn-sm" href="#!">More Info</a></div>                    
                </div>
            </div>
        </c:forEach>       
    </div>
</div>