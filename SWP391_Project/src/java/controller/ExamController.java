
package controller;

import dao.ExamDAO;
import dao.QuestionDAO;
import dao.QuestionExamDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Exam;
import model.ExamType;
import model.Level;
import model.Question;
import model.Subject;


@WebServlet(name="ExamController", urlPatterns={"/manageExams"})
public class ExamController extends HttpServlet {
   
    int subjectId = -1, typeId = -1;
    String searchName = "";
    ExamDAO examDAO = new ExamDAO();
    SubjectDAO subjectDAO = new SubjectDAO();
    QuestionDAO questionDAO = new QuestionDAO();
    QuestionExamDAO qedao = new QuestionExamDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ExamController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExamController at " + request.getContextPath () + "</h1>");
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
            case "changeMode":
                changeModeExam(request, response);
                redirectToExamPage(request, response);
                break;
            default:
                redirectToExamPage(request, response);
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
            case "create":
                createExam(request, response);
                redirectToExamPage(request, response);
                break;
            case "update":
                updateExam(request, response);
                redirectToExamPage(request, response);
                break;
            case "filterAndSearch":
                filterAndSearch(request, response);
                redirectToExamPage(request, response);
                break;
            case "addListQuestion":
                addListQuestion(request, response);
                redirectToExamPage(request, response);
                break;
            default:
                redirectToExamPage(request, response);
                break;
        }
    }

    
    private void redirectToExamPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Exam> listE = examDAO.getAllExam(subjectId, typeId, searchName);
        List<ExamType> listET = examDAO.getAllExamTypes();
        List<Subject> listS = subjectDAO.getSimpleSubjects(-1);
        List<Level> listL = questionDAO.getAllLevelsByStatus(-1);
        List<Question> listQ = questionDAO.getAllQuestions(-1, -1, -1, -1, 1, "");
        
        request.setAttribute("subjectId", subjectId);
        request.setAttribute("typeId", typeId);
        request.setAttribute("searchName", searchName);
        request.setAttribute("listE", listE);
        request.setAttribute("listET", listET);
        request.setAttribute("listS", listS);
        request.setAttribute("listL", listL);
        request.setAttribute("listQ", listQ);
        
        request.getRequestDispatcher("manageExam.jsp").forward(request, response);
    }
    
    
    private void createExam(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int subjectId = Integer.parseInt(request.getParameter("subject"));
        int type = Integer.parseInt(request.getParameter("type"));
        int level = Integer.parseInt(request.getParameter("level"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int numberOfQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
        float passRate = Float.parseFloat(request.getParameter("passRate"));
        String description = request.getParameter("description");
        
        Exam exam = new Exam();
        exam.setName(name);
        exam.setSubject_id(subjectId);
        exam.setExamType(new ExamType());
        exam.getExamType().setId(type);
        exam.setLevel(new Level());
        exam.getLevel().setId(level);
        exam.setDuration(duration);
        exam.setPass_rate(passRate);
        exam.setNumber_of_question(numberOfQuestion);
        exam.setDescription(description);
        
        examDAO.addNewExam(exam);
        
    }

    private void updateExam(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int subjectId = Integer.parseInt(request.getParameter("subject"));
        int type = Integer.parseInt(request.getParameter("type"));
        int level = Integer.parseInt(request.getParameter("level"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int numberOfQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
        float passRate = Float.parseFloat(request.getParameter("passRate"));
        String description = request.getParameter("description");
        
        Exam exam = new Exam();
        exam.setId(id);
        exam.setName(name);
        exam.setSubject_id(subjectId);
        exam.setExamType(new ExamType());
        exam.getExamType().setId(type);
        exam.setLevel(new Level());
        exam.getLevel().setId(level);
        exam.setDuration(duration);
        exam.setPass_rate(passRate);
        exam.setNumber_of_question(numberOfQuestion);
        exam.setDescription(description);
        
        examDAO.updateExam(exam);
    }
    
    private void changeModeExam(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("eid"));
        
        examDAO.changeModeExam(id);
    }
    
    private void filterAndSearch(HttpServletRequest request, HttpServletResponse response) {
        subjectId = Integer.parseInt(request.getParameter("subject"));
        typeId = Integer.parseInt(request.getParameter("type"));
        searchName = request.getParameter("searchName");
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void addListQuestion(HttpServletRequest request, HttpServletResponse response) {
        String[] id = request.getParameterValues("id");
        int examId = Integer.parseInt(request.getParameter("eid"));
        System.out.println("-----");
        for (int i = 0; i < id.length; i++) {
            if(qedao.getQuestionExam(examId, Integer.parseInt(id[i])) == null){
                qedao.addListQuestionToExam(examId,
                    Integer.parseInt(id[i]),
                    Integer.parseInt(request.getParameter("order-"+id[i])),
                    Float.parseFloat(request.getParameter("mark-"+id[i])));
            }
        }
    }

    

    

   

}
