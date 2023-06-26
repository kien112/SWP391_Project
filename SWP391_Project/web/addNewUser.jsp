<%-- 
    Document   : addNewUser
    Created on : Jun 26, 2023, 8:54:14 AM
    Author     : 84877
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./CSS/userStyle.css"/>
    </head>
    <body>
        
        <h1>Add New User</h1>
        <div>
            <form action="addNewUser" method="post">
                <div class="input-field">
                    <label>Email</label>
                    <input name="email" type="email"/>
                </div>
                <div class="input-field">
                    <label>Password</label>
                    <input name="password" type="password" minlength="6" maxlength="31"/>
                </div>
                <div class="input-field">
                    <label>Full Name</label>
                    <input name="fullname" type="text"/>
                </div>
                <div class="input-field">
                    <label>Gender</label>
                    <select name="gender">
                        <option value="1" selected>Male</option>
                        <option value="0">Female</option>
                    </select>
                </div>
                <div class="input-field">
                    <label>Address</label>
                    <input name="address" type="text"/>
                </div>
                <div class="input-field">
                    <label>Phone</label>
                    <input name="phone_number" type="number" minlength="10" maxlength="11"/>
                </div>
                <p>${message}</p>
                <button type="submit">Add New</button>
                <a href="userList">Back</a>
            </form>
        </div>
    </body>
</html>
