

package controller;

import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Subject;


@WebServlet(name="MyListSubject", urlPatterns={"/myListSubject"})
public class MyListSubject extends HttpServlet {
   
    SubjectDAO subjectDAO = new SubjectDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyListSubject</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyListSubject at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        int courseId = -2;
        try {
            courseId = Integer.parseInt(request.getParameter("courseId"));
        } catch (Exception e) {
        }
        List<Subject> listS = subjectDAO.getAllSubjectByCourseId(courseId);
        
        request.setAttribute("listS", listS);
        request.getRequestDispatcher("myListSubject.jsp").forward(request, response);
    } 

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
