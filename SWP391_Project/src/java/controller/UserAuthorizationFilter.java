/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RoleDAO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;

/**
 *
 * @author Acer
 */
public class UserAuthorizationFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse respone = (HttpServletResponse) sr1;
        HttpSession session = request.getSession();
        RoleDAO r = new RoleDAO();
        User u = (User) session.getAttribute("user");
        String role = null;
        if (u != null) {
            role = r.getRoleNameByUserId(u.getUser_id());
        }
        if (role == null) {
            role = "Guest";
        }
//        System.out.println(role);
        String requestedURL = request.getRequestURI();
        if (u != null) {
            if (requestedURL.contains("changepassword") || requestedURL.contains("changePassword.jsp")
                    || requestedURL.contains("userProfile.jsp")) {
                fc.doFilter(request, respone);
            }
        } else {
            respone.sendRedirect("accessDenied.jsp");
        }
//        System.out.println(role + ":" + requestedURL);
//        System.out.println(hasPermission(role, requestedURL));
//        if (hasPermission(role, requestedURL)) {
//            fc.doFilter(request, respone);
//        } else {
//            respone.sendRedirect("accessDenied.jsp");
//
//        }

    }

    private boolean hasPermission(String role, String requestedURL) {
        if (requestedURL.contains("changePassword.jsp") || requestedURL.contains("changepassword")
                || requestedURL.contains("userProfile.jsp"))  {
            return !role.equals("Guest");
        }

        else if (requestedURL.contains("forgotPassword.jsp") || requestedURL.contains("forgotPassword")
                || requestedURL.contains("EnterOtp.jsp") || requestedURL.contains("ValidateOtp")
                || requestedURL.contains("newPassword.jsp") || requestedURL.contains("newPassword")
                || requestedURL.contains("register.jsp") || requestedURL.contains("register")
                || requestedURL.contains("blogList.jsp") || requestedURL.contains("blog")
                || requestedURL.contains("blogDetails.jsp") || requestedURL.contains("blogdetail")
                || requestedURL.contains("home.jsp") || requestedURL.contains("home")
                || requestedURL.contains("courseDetails.jsp") || requestedURL.contains("details")
                || requestedURL.contains("courseList.jsp") || requestedURL.contains("courses")
                || requestedURL.contains("myCourse.jsp") || requestedURL.contains("home")
                || requestedURL.contains("myRegistration.jsp") || requestedURL.contains("myRegistration")
                || requestedURL.contains("home.jsp") || requestedURL.contains("home")) {
            return role.equals("Guest");
        }

        else if (requestedURL.contains("myCourse.jsp") || requestedURL.contains("myCourse")
                || requestedURL.contains("myRegistration.jsp") || requestedURL.contains("myRegistration")) {
            return role.equals("Customer");
        }

        else if (requestedURL.contains("footer.jsp")) {
            return false;
        }

        else if (requestedURL.contains("header.jsp")) {
            return false;
        }
        return true;
    }

}
