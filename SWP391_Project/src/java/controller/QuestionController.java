
package controller;

import dao.DimensionDAO;
import dao.LessonDAO;
import dao.QuestionDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.List;
import java.util.logging.Logger;
import model.Dimension;
import model.Lesson;
import model.Level;
import model.Question;
import model.Subject;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
@WebServlet(name="QuestionController", urlPatterns={"/questions"})
public class QuestionController extends HttpServlet {
   
    String FileUpload_Directory = "E:\\kien\\ABC\\SWP391_Project\\web\\excel\\";
    QuestionDAO quesionDAO = new QuestionDAO();
    SubjectDAO subjectDAO = new SubjectDAO();
    DimensionDAO dimensionDAO = new DimensionDAO();
    LessonDAO lessonDAO = new LessonDAO();
    int subjectId = -1, dimensionId = -1, lesson_id = -1, level_id = -1, status = -1;
    String searchContent = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuestionController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionController at " + request.getContextPath () + "</h1>");
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
            case "changeStatus":
                changeStatusQuestion(request, response);
                redirectToQuestionPage(request, response);
                break;
            default:
                redirectToQuestionPage(request, response);
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
                createQuestion(request, response);
                redirectToQuestionPage(request, response);
                break;
            case "update":
                updateQuestion(request, response);
                redirectToQuestionPage(request, response);
                break;
            case "filterAndSearch":
                filterAndSearch(request, response);
                redirectToQuestionPage(request, response);
                break;
            case "importQuestion":
                importQuestion(request, response);
                redirectToQuestionPage(request, response);
                break;
            default:
                redirectToQuestionPage(request, response);
                break;
        }
    }

    
    private void redirectToQuestionPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Question> listQ = quesionDAO.getAllQuestions(subjectId, dimensionId, lesson_id, level_id, status, searchContent);
        List<Lesson> listL = lessonDAO.findAllLessonBySubjectId(-1);
        List<Dimension> listD = dimensionDAO.getAllDimensions();
        List<Subject> listS = subjectDAO.getAllSubjectByCourseId(-1);
        List<Level> listLevels = quesionDAO.getAllLevelsByStatus(-1);
        
        request.setAttribute("levelId", level_id);
        request.setAttribute("subjectId", subjectId);
        request.setAttribute("lessonId", lesson_id);
        request.setAttribute("status", status);
        request.setAttribute("dimensionId", dimensionId);
        request.setAttribute("searchContent", searchContent);
        request.setAttribute("listQ", listQ);
        request.setAttribute("listL", listL);
        request.setAttribute("listD", listD);
        request.setAttribute("listS", listS);
        request.setAttribute("listLevels", listLevels);
        
        request.getRequestDispatcher("manageQuestion.jsp").forward(request, response);
    }
    
    private void createQuestion(HttpServletRequest request, HttpServletResponse response) {
        String content = request.getParameter("content");
        String option_a = request.getParameter("option_a");
        String option_b = request.getParameter("option_b");
        String option_c = request.getParameter("option_c");
        String option_d = request.getParameter("option_d");
        String answer = request.getParameter("answer");
        String explanation = request.getParameter("explanation");
        int level = Integer.parseInt(request.getParameter("level"));
        int lesson = Integer.parseInt(request.getParameter("lesson"));

        Question question = new Question();
        question.setContent(content);
        question.setOption_a(option_a==null?"":option_a);
        question.setOption_b(option_b==null?"":option_b);
        question.setOption_c(option_c==null?"":option_c);
        question.setOption_d(option_d==null?"":option_d);
        question.setAnswer(answer);
        question.setExplanation(explanation);
        question.setLevel(new Level());
        question.getLevel().setId(level);
        question.setLesson(new Lesson());
        question.getLesson().setId(lesson);
        
        quesionDAO.addNewQuestion(question);
    }
    
    private void updateQuestion(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        String option_a = request.getParameter("option_a");
        String option_b = request.getParameter("option_b");
        String option_c = request.getParameter("option_c");
        String option_d = request.getParameter("option_d");
        String answer = request.getParameter("answer");
        String explanation = request.getParameter("explanation");
        int level = Integer.parseInt(request.getParameter("level"));
        int lesson = Integer.parseInt(request.getParameter("lesson"));

        Question question = new Question();
        question.setId(id);
        question.setContent(content);
        question.setOption_a(option_a==null?"":option_a);
        question.setOption_b(option_b==null?"":option_b);
        question.setOption_c(option_c==null?"":option_c);
        question.setOption_d(option_d==null?"":option_d);
        question.setAnswer(answer);
        question.setExplanation(explanation);
        question.setLevel(new Level());
        question.getLevel().setId(level);
        question.setLesson(new Lesson());
        question.getLesson().setId(lesson);
  
        quesionDAO.updateQuestion(question);
    }
    
    private void changeStatusQuestion(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("qid"));
        
        quesionDAO.changeStatusQuestion(id);
    }
    
    private void filterAndSearch(HttpServletRequest request, HttpServletResponse response) {
        subjectId = Integer.parseInt(request.getParameter("subject"));
        lesson_id = Integer.parseInt(request.getParameter("lesson"));
        level_id = Integer.parseInt(request.getParameter("level"));
        dimensionId = Integer.parseInt(request.getParameter("dimension"));
        status = Integer.parseInt(request.getParameter("status"));
        searchContent = request.getParameter("searchContent");
    }
    
    private void importQuestion(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "";
        try {
            Part filePart = request.getPart("file");
            fileName = filePart.getSubmittedFileName();
            if(!fileName.equals("")){
                for (Part part : request.getParts()) {
                    part.write(FileUpload_Directory + fileName);
                }
            }
            int lessonId = Integer.parseInt(request.getParameter("lesson"));
            List<Object[]> list = quesionDAO.getQuestionsFromExcel(FileUpload_Directory+fileName);
            Question q = new Question();
            q.setLesson(new Lesson());
            q.getLesson().setId(lessonId);
            
            quesionDAO.insertQuestionToDBFromExcel(list, q);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    

    

    

    

}
