/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.SubjectDAO;
import dao.UserDBContext;
import dao.courseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.CourseCategory;
import model.Subject;
import model.SubjectCategory;
import model.User;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
@WebServlet(name="SubjectController", urlPatterns={"/subjects"})
public class SubjectController extends HttpServlet {
   
    String FileUpload_Directory = "E:\\kien\\ABC\\SWP391_Project\\web\\image\\";
    courseDAO dao = new courseDAO();
    SubjectDAO subjectDAO = new SubjectDAO();
    int courseId = -1;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SubjectController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        String action = request.getParameter("action");
        action = action == null ? "" : action;
        try {
            if(request.getParameter("courseId")!=null)
                courseId = Integer.parseInt(request.getParameter("courseId"));
            UserDBContext udbc = new UserDBContext();
            List<User> listU = udbc.getAllUserByRoleId(4);
            request.setAttribute("list_expert", listU);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<SubjectCategory> listC = subjectDAO.getAllSubjecCategorys();
        request.setAttribute("list_sc", listC);
        switch (action) {
            case "create":
                request.getRequestDispatcher("AddNewSubject.jsp").forward(request, response);
                break;
            default:
                redirectToMainPage(request, response);
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createNewSubject(request, response);
                break;
            default:
                redirectToMainPage(request, response);
        }
        redirectToMainPage(request, response);
    }
    
    private void redirectToMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Subject> listS = subjectDAO.getAllSubjectByCourseId(courseId);
        
        request.setAttribute("listS", listS);
        request.getRequestDispatcher("manageSubject.jsp").forward(request, response);
    }

    private void createNewSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = "";
        PrintWriter out = response.getWriter();
        try {
            Part filePart = request.getPart("thumbnail");
            fileName = filePart.getSubmittedFileName();
            if(!fileName.equals("")){
                for (Part part : request.getParts()) {
                    part.write(FileUpload_Directory + fileName);
                }
            }
            String name = request.getParameter("name");
            int cate_id = Integer.parseInt(request.getParameter("category"));
            int user_id = Integer.parseInt(request.getParameter("owner"));
            boolean status = request.getParameter("status").equals("1");
            String description = request.getParameter("description");
            boolean featured = request.getParameter("featured").equals("on");
            
            Subject subject = new Subject();
            subject.setAuthor_id(user_id);
            subject.setName(name);
            subject.setIllustration("image/"+fileName);
            subject.setStatus(status);
            subject.setDescription(description);
            subject.setFeatured(featured);
            subject.setCourse_id(courseId);
            SubjectCategory category = new SubjectCategory();
            category.setId(cate_id);
            subject.setCategory(category);
            
            subjectDAO.addNewCourse(subject);
        } catch (Exception e) {
            out.print(e.getMessage());
        }
    }
    
    
    
    
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}
