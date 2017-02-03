package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forms.ClientForm;
import com.interaction.*;
import com.objects.*;

/**
 * This class fills and shows up three html-forms
 */
public class InitialServlet extends HttpServlet {

    /**
     * This method is processing GET and POST requests
     * @param req - request
     * @param resp - response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text / html;charset=UTF-8");

        SystemDAO dao = new SystemDAO();
        List<Bus> bList = dao.getAllBuses();
        List<Station> sList = dao.getAllStations();

        ClientForm clientForm = new ClientForm();
        clientForm.setBuses(bList);
        clientForm.setStations(sList);
        req.setAttribute("form", clientForm);
        getServletContext().getRequestDispatcher("/ClientFrame.jsp").forward(req, resp);
    }

    /**
     * This method is processing GET-request
     * @param req - request from HTML
     * @param resp - response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * This method is processing POST-request
     * @param req - request from HTML
     * @param resp - response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }
}