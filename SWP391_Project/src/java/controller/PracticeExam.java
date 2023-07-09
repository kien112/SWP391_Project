

package controller;

import dao.ExamDAO;
import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Exam;
import model.Question;
import model.User;


@WebServlet(name="PracticeExam", urlPatterns={"/practiceExam"})
public class PracticeExam extends HttpServlet {
   
    QuestionDAO questionDAO = new QuestionDAO();
    ExamDAO examDAO = new ExamDAO();
    List<Question> listQ = new ArrayList<>();
    int examId = 0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PracticeExam</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PracticeExam at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        String action = "";
        
        if(request.getParameter("action") != null)
            action = request.getParameter("action");
        switch (action) {
            case "viewScore":
                viewScoreExam(request, response);
                break;
            case "start":
                startExam(request, response);
                break;
            default:
                viewScoreExam(request, response);
                break;
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        String action = "";
        
        if(request.getParameter("action") != null)
            action = request.getParameter("action");
        switch (action) {
            case "getResult":
                getResult(request, response);
                break;
            default:
                viewScoreExam(request, response);
                break;
        }
    }


    private void viewScoreExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        examId = Integer.parseInt(request.getParameter("examId"));
        User u = (User) request.getSession().getAttribute("user");
        
        float score = examDAO.viewLastScore(u.getEmail(), examId);
        
        request.setAttribute("score", score);
        request.setAttribute("examId", examId);
        request.getRequestDispatcher("viewScoreExam.jsp").forward(request, response);
    }

    private void startExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listQ = questionDAO.getAllQuestionByExamId(examId);
        Exam exam = examDAO.getExamById(examId);
        request.setAttribute("listQ", listQ);
        request.setAttribute("exam", exam);
        request.getRequestDispatcher("startExam.jsp").forward(request, response);
    }

    private void getResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        String[] options = request.getParameterValues("selectOp[]");
        String[] ids = request.getParameterValues("id[]");
            
        float score = 0;
        
        for (int i = 0; i < ids.length; i++) {
            int idx = ids[i].indexOf("-");
            ids[i] = ids[i].substring(idx+1);
        }
        
        for (int i = 0; i < options.length; i++) {
            int id = Integer.parseInt(ids[i]);
            float mark = isCorrect(options[i], id);
            if(mark != -1){
                score += mark;
            }
        }
        User u = (User) request.getSession().getAttribute("user");
        examDAO.insertScore(u.getEmail(), examId, score);
        
        out.print("<a href='practiceExam?action=viewScore&examId="+examId+"'>Back</a>");
        out.print("<div>Result: "+score+"</div>");
        
        for (Question q : listQ) {
            String text = "<div class=\"row card mb-3 shadow p-3 bg-body-tertiary rounded\">\n" +
"                    <div class=\"col-md-12\">Question"+ q.getOrder() +": "+q.getContent()+"</div>\n" +
"                    <div class=\"row\">\n" ;
            String op = isExistId(q.getId(), ids, options);
            if(!op.equals("")){
                if(q.getOption_a().equals(q.getAnswer()))
                    text += " <div class=\"col-md-6\" style=\"color:green\">\n" +
                    "  <input disabled='disabled' type=\"radio\" checked />\n" +q.getOption_a()+
                    "   </div>";
                else if(op.equals(q.getOption_a()) && !op.equals(q.getAnswer()))
                    text += " <div class=\"col-md-6\" style=\"color:red\">\n" +
                    "  <input  disabled='disabled' type=\"radio\" checked />\n" +q.getOption_a()+
                    "   </div>";
                else
                    text += " <div class=\"col-md-6\" >\n" +
                    "  <input  disabled='disabled'  type=\"radio\" />\n" +q.getOption_a()+
                    "   </div>";
                
                if(q.getOption_b().equals(q.getAnswer()))
                    text += " <div class=\"col-md-6\" style=\"color:green\">\n" +
                    "  <input  disabled='disabled'  type=\"radio\" checked />\n" +q.getOption_b()+
                    "   </div>";
                else if(op.equals(q.getOption_b()) && !op.equals(q.getAnswer()))
                    text += " <div class=\"col-md-6\" style=\"color:red\">\n" +
                    "  <input  disabled='disabled'  type=\"radio\" checked />\n" +q.getOption_b()+
                    "   </div>";
                else
                    text += " <div class=\"col-md-6\" >\n" +
                    "  <input  disabled='disabled'  type=\"radio\" />\n" +q.getOption_b()+
                    "   </div>";
                if(q.getOption_c() != null){
                    if(q.getOption_c().equals(q.getAnswer()))
                        text += " <div class=\"col-md-6\" style=\"color:green\">\n" +
                        "  <input  disabled='disabled'  type=\"radio\" checked />\n" +q.getOption_c()+
                        "   </div>";
                    else if(op.equals(q.getOption_c()) && !op.equals(q.getAnswer()))
                        text += " <div class=\"col-md-6\" style=\"color:red\">\n" +
                        "  <input  disabled='disabled'  type=\"radio\" checked />\n" +q.getOption_c()+
                        "   </div>";
                    else
                        text += " <div class=\"col-md-6\" >\n" +
                        "  <input  disabled='disabled'  type=\"radio\" />\n" +q.getOption_c()+
                        "   </div>";
                }
                
                if(q.getOption_d() != null){
                    if(q.getOption_d().equals(q.getAnswer()))
                        text += " <div class=\"col-md-6\" style=\"color:green\">\n" +
                        "  <input  disabled='disabled'  type=\"radio\" checked />\n" +q.getOption_d()+
                        "   </div>";
                    else if(op.equals(q.getOption_d()) && !op.equals(q.getAnswer()))
                        text += " <div class=\"col-md-6\" style=\"color:red\">\n" +
                        "  <input  disabled='disabled'  type=\"radio\" checked />\n" +q.getOption_d()+
                        "   </div>";
                    else
                        text += " <div class=\"col-md-6\" >\n" +
                        "  <input  disabled='disabled'  type=\"radio\" />\n" +q.getOption_d()+
                        "   </div>";
                }
                
            }
            else{
                if(q.getAnswer().equals(q.getOption_a()))
                    text += " <div class=\"col-md-6\" style='color: "+"yellow"+"'>\n" +
                    "  <input  disabled='disabled'  type=\"radio\" checked />\n" +q.getOption_a()+
                    "   </div>";
                else
                    text += " <div class=\"col-md-6\" >\n" +
                    "  <input  disabled='disabled'  type=\"radio\" />\n" +q.getOption_a()+
                    "   </div>";
                if(q.getAnswer().equals(q.getOption_b()))
                    text += " <div class=\"col-md-6\" style='color: "+"yellow"+"'>\n" +
                    "  <input  disabled='disabled'  type=\"radio\" checked />\n" +q.getOption_b()+
                    "   </div>";
                else
                    text += " <div class=\"col-md-6\" >\n" +
                    "  <input  disabled='disabled'  type=\"radio\" />\n" +q.getOption_b()+
                    "   </div>";
                
                if(q.getOption_c() != null){
                    if(q.getAnswer().equals(q.getOption_c()))
                        text += " <div class=\"col-md-6\" style='color: "+"yellow"+"'>\n" +
                        "  <input  disabled='disabled'  type=\"radio\" checked />\n" +q.getOption_c()+
                        "   </div>";
                    else
                        text += " <div class=\"col-md-6\" >\n" +
                        "  <input  disabled='disabled'  type=\"radio\" />\n" +q.getOption_c()+
                        "   </div>";
                }
                
                if(q.getOption_d() != null){
                    if(q.getAnswer().equals(q.getOption_d()))
                        text += " <div class=\"col-md-6\" style='color: "+"yellow"+"'>\n" +
                        "  <input  disabled='disabled'  type=\"radio\" checked />\n" +q.getOption_d()+
                        "   </div>";
                    else
                        text += " <div class=\"col-md-6\" >\n" +
                        "  <input  disabled='disabled'  type=\"radio\" />\n" +q.getOption_d()+
                        "   </div>";
                }
                
            }
            text += "</div><div class='col-md-12'>Explanation: "+(q.getExplanation()==null?"No Explanation":q.getExplanation())+"</div>";
            out.print(text+"</div>");
        }
        
    }

    private float isCorrect(String option, int id) {
        for (Question q : listQ) {
            if(q.getAnswer().equals(option) && q.getId() == id)
                return q.getMark();
        }
        return -1;
    }

    private String isExistId(int id, String[] ids, String[] ops) {
        for (int i = 0; i < ids.length; i++) {
            if(id == Integer.parseInt(ids[i]))
                return ops[i];
        }
        return "";
    }

}
