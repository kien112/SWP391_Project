<%-- 
    Document   : updateBlog
    Created on : Jun 26, 2023, 12:54:30 PM
    Author     : 84877
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
              integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog=="
              crossorigin="anonymous" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Update Blog</h1>
        <form action="updateBlog" method="post" enctype="multipart/form-data">
            <input name="blog_id" value="${b.blog_id}" hidden=""/>
            <div>
                <label>Title</label>
                <input name="title" value="${b.title}"/>
            </div>
            
            <div>
                <label>Brief Info</label>
                <textarea name="brief_info" value="${b.brief_info}">${b.brief_info}</textarea>
            </div>
            <div>
                <label>Blog Content</label>
                <textarea name="blog_content" value="${b.blog_content}">${b.blog_content}</textarea>
            </div>
            <div>
                <label for="category_id">Category</label>                           
                <select name="category_id">                                    
                    <c:forEach var="c" items="${category}">
                        <option value="${c.id}" ${c.id==b.cate_id ? "selected":""}>${c.name}</option>
                    </c:forEach>
                </select>  
            </div>
            <div>
                <label for="status">Status</label> 
                <select name="status">                                    
                    <option value="0" ${b.status?"":"selected"}>InActive</option>
                    <option value="1" ${b.status?"selected":""}>Active</option>
                </select>  
            </div>
            <div>
                <label>Flag</label>
                <select name="flag">
                    <option value="ON" ${b.flag=="ON"?"selected":""}>ON</option>
                    <option value="OFF" ${b.flag=="OFF"?"selected":""}>OFF</option>
                </select>
            </div>
            <div>
                <label>Image</label>
                <input id="old-image" name="old_image" value="${b.image}" hidden=""/>
                <img src="${b.image}" id="img-preview" width="50" height="50"/>
                <input accept="image/*" id="file-input" name="file" type="file"/>
            </div>
            <button type="submit">Update</button>
        </form>
 <jsp:include page="footer.jsp"/>
        <script>
            const input = document.getElementById('file-input');
            const image = document.getElementById('img-preview');

            input.addEventListener('change', (e) => {
                if (e.target.files.length) {
                    const src = URL.createObjectURL(e.target.files[0]);
                    image.src = src;
                }else{
                    image.src = document.getElementById('old-image').value;
                }
            });
        </script>
    </body>
</html>
