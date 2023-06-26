/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Slider;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateSliderController", urlPatterns = {"/slider/update"})
public class UpdateSliderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        SliderDAO dbSlider = new SliderDAO();
        try {
            Slider slider = dbSlider.findById(Integer.parseInt(id));
            request.setAttribute("slider", slider);
            request.getRequestDispatcher("../sliderUpdate.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/slider");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String backlink = request.getParameter("backlink");
        String status = request.getParameter("status");

        SliderDAO dbSlider = new SliderDAO();
        dbSlider.updateSlider(Integer.parseInt(id), title, backlink, Boolean.parseBoolean(status));
        response.sendRedirect(request.getContextPath() + "/slider/update?id="+id);

    }

}
