package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forms.TicketForm;
import com.objects.*;
import com.interaction.*;

/**
 * This class defines the processing of GET and POST requests from "Buy ticket" html-form.
 */
public class BuyTicketServlet extends HttpServlet {

    /**
     * This method is processing GET and POST requests
     * @param req - request
     * @param resp - response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Use DAO
        SystemDAO dao = new SystemDAO();

        // Read all parameters from the form
        int busId = Integer.parseInt(req.getParameter("listBuses"));
        int stationId = Integer.parseInt(req.getParameter("listStations"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String dateOfBirthString = req.getParameter("calendar");
        String timeStr = req.getParameter("timeFrom");

        java.util.Date dateFrom= null;
        java.util.Date DoB = null;

        // Trying to parse strings that store dates
        try {
            dateFrom = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(timeStr);
            DoB = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirthString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Ticket ticket = dao.buyTicket(busId, stationId, dateFrom, name, surname, DoB);
        TicketForm ticketForm = new TicketForm();
        ticketForm.setTicket(ticket);
        req.setAttribute("form", ticketForm);

        if (ticket == null)
            getServletContext().getRequestDispatcher("/TicketFrameFail.jsp").forward(req, resp);
        else
            getServletContext().getRequestDispatcher("/TicketFrame.jsp").forward(req, resp);
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