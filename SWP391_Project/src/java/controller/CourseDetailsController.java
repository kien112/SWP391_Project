/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.Price_PackageDAO;
import dao.RegistrationDAO;
import dao.courseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import model.Course;
import model.CourseCategory;
import model.PricePackage;
import model.Registrations;
import model.User;




@WebServlet(name="CourseDetailsController", urlPatterns={"/details"})
public class CourseDetailsController extends HttpServlet {
    String courseId = "1";
    
    RegistrationDAO registrationDAO = new RegistrationDAO();
    Price_PackageDAO price_PackageDAO = new Price_PackageDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = "";
        action = request.getParameter("action") == null 
                ? "" : request.getParameter("action");
        switch (action) {
            case "details":
                
                break;
            default:
                redirectToCourseDetail(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = "";
        action = request.getParameter("action") == null 
                ? "" : request.getParameter("action");
        switch (action) {
            case "registrationCourse":
                registrationCourse(request, response);
                break;
            default:
                redirectToCourseDetail(request, response);
                break;
        }
        redirectToCourseDetail(request, response);
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
        request.setAttribute("message", "register successfully");
    }
    
    private void redirectToCourseDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        courseId = request.getParameter("courseId");
        courseDAO dbCourse = new courseDAO();
        User user = (User) request.getSession().getAttribute("user");
        Price_PackageDAO dbPrice_Package = new Price_PackageDAO();
        List<PricePackage> price_packages = dbPrice_Package.findAll();
        Course course = dbCourse.findByCouseId(Integer.parseInt(courseId));
        boolean checkUserRegister = false;
        if (user != null) {
            checkUserRegister = dbCourse.checkUserRegisterCourse(user.getUser_id(), Integer.parseInt(courseId));
        }
        
        List<Course> top3 = dbCourse.getTop3ListCourses();
        List<CourseCategory> cc = dbCourse.getBlogCategories();
        request.setAttribute("clist", cc);
        request.setAttribute("top3", top3);
        
        request.setAttribute("checkRegister", checkUserRegister);
        request.setAttribute("price_packages", price_packages);
        request.setAttribute("course", course);
        request.getRequestDispatcher("courseDetails.jsp").forward(request, response);
    
    }
}