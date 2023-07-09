
package controller;

import dao.ExamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Exam;


@WebServlet(name="MyListExam", urlPatterns={"/myListExam"})
public class MyListExam extends HttpServlet {
   
    ExamDAO examDAO = new ExamDAO();
    int subjectId = -3;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyListExam</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyListExam at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        try {
            subjectId = Integer.parseInt(request.getParameter("subjectId"));
        } catch (Exception e) {
        }
        List<Exam> listE = examDAO.getAllExam(subjectId, -1, "");
        
        request.setAttribute("subjectId", subjectId);
        request.setAttribute("listE", listE);
        request.getRequestDispatcher("myListExam.jsp").forward(request, response);
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
