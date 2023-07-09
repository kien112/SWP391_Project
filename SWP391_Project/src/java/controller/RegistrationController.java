

package controller;

import dao.Price_PackageDAO;
import dao.RegistrationDAO;
import dao.UserDBContext;
import dao.courseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import model.Course;
import model.PricePackage;
import model.Registrations;
import model.User;
import utils.EmailUtils;


@WebServlet(name="RegistrationController", urlPatterns={"/registrations"})
public class RegistrationController extends HttpServlet {
   
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    RegistrationDAO registrationDAO = new RegistrationDAO();
    Price_PackageDAO price_PackageDAO = new Price_PackageDAO();
    courseDAO cdao = new courseDAO();
    String course = "", email = "";
    Date fromDate = null, toDate = null;
    int status = -1;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrationController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrationController at " + request.getContextPath () + "</h1>");
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
                changeStatus(request, response);
                redirectToManageRegistration(request, response);
                break;
            default:
                redirectToManageRegistration(request, response);
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
            case "addRegistration":
                registrationCourse(request, response);
                break;
            case "updateRegistration":
                updateRegistration(request, response);
                break;
            case "filterAndSearch":
                filterAndSearch(request, response);
                break;
            default:
                redirectToManageRegistration(request, response);
                break;
        }
        redirectToManageRegistration(request, response);
    }

    private void registrationCourse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        int pricePackageId = Integer.parseInt(request.getParameter("price_packageId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        
        PricePackage pricePackage = price_PackageDAO.getPricePackageById(pricePackageId);
        long millis=System.currentTimeMillis();  
        Date fromDate = new Date(millis);
        LocalDate date = fromDate.toLocalDate();
        LocalDate newDate = date.plusMonths(pricePackage.getDuration()); 
        Date toDate = Date.valueOf(newDate);
        
        Registrations r = new Registrations();
        r.setCourse_id(courseId);
        r.setEmail(email);
        r.setPricePackage(pricePackage);
        r.setValid_from(fromDate);
        r.setValid_to(toDate);
        r.setStatus(1);
        
        registrationDAO.addNewRegistration(r);
    }
    
    private void redirectToManageRegistration(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Registrations> listR = registrationDAO.getAllRegistrations(course, 
                fromDate, toDate, status, email);
        List<PricePackage> listP = price_PackageDAO.findAll();
        List<Course> listC = cdao.getAllCourse();
        
        request.setAttribute("course", course);
        request.setAttribute("fromDate", fromDate);
        request.setAttribute("toDate", toDate);
        request.setAttribute("status", status);
        request.setAttribute("email", email);
        
        request.setAttribute("listR", listR);
        request.setAttribute("listP", listP);
        request.setAttribute("listC", listC);
        
        request.getRequestDispatcher("manageRegistrations.jsp").forward(request, response);
    }
    
    
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void updateRegistration(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        int pricePackageId = Integer.parseInt(request.getParameter("price_packageId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int id = Integer.parseInt(request.getParameter("rid"));
        PricePackage pricePackage = price_PackageDAO.getPricePackageById(pricePackageId);
        long millis=System.currentTimeMillis();  
        Date fromDate = new Date(millis);
        LocalDate date = fromDate.toLocalDate();
        LocalDate newDate = date.plusMonths(pricePackage.getDuration()); 
        Date toDate = Date.valueOf(newDate);
        
        
        
        Registrations r = new Registrations();
        r.setCourse_id(courseId);
        r.setId(id);
        r.setEmail(email);
        r.setPricePackage(pricePackage);
        r.setValid_from(fromDate);
        r.setValid_to(toDate);
        r.setStatus(1);
    
        if(request.getSession() != null){
            User user = (User) request.getSession().getAttribute("user");
            if(user != null){
                r.setUpdate_by(user.getFull_name());
            }
        }
        
        registrationDAO.updateRegistration(r);
    }

    private void changeStatus(HttpServletRequest request, HttpServletResponse response){
        String status = request.getParameter("status");
        
        if(status.equals("Success") || status.equals("Cancel")){
            int id = Integer.parseInt(request.getParameter("rid"));
            int stat = status.equals("Success") ? 2 : 0;
            String updateBy = "";
            if(request.getSession() != null){
            User user = (User) request.getSession().getAttribute("user");
            if(user != null){
                updateBy = (user.getFull_name());
            }
        }
            
            registrationDAO.changeStatusRegistration(id, stat, updateBy);
            if(registrationDAO.checkEmailExist(id).equals("") && status.equals("Success")){
                String sendEmail = registrationDAO.getEmailRegistration(id);
                if(!sendEmail.equals("")){
                    User user = new User();
                    user.setEmail(sendEmail);
                    String password = createPassword(10);
                    user.setPassword(password);
                    user.setFull_name("customer");
                    try {
                        UserDBContext udbc = new UserDBContext();
                        udbc.insertUser(user);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    String subject = "Account Registration Course";
                    String body = "Email: "+sendEmail+"\nPassword: "+password;
                    EmailUtils.sendVerifyEmail(sendEmail, subject, body);
                }
                
            }
        }
    
    }

    public String createPassword(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    
    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
    private static Random generator = new Random();

    private void filterAndSearch(HttpServletRequest request, HttpServletResponse response) {
        email = request.getParameter("email");
        course = request.getParameter("course");
        if(request.getParameter("fromDate") != null)
            fromDate = Date.valueOf(request.getParameter("fromDate"));
        if(request.getParameter("toDate") != null)
            toDate = Date.valueOf(request.getParameter("toDate"));
        status = Integer.parseInt(request.getParameter("status"));
    }
    
}
