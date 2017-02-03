package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forms.FindBusForm;
import com.objects.*;
import com.interaction.*;

/**
 * This class defines the processing of GET and POST requests from "Find bus" html-form by searching for buses
 * between two stations at given time-lapse and sending .jsp as a response.
 * In case when list of buses is empty this servlet sends special .jsp
 */
public class FindBusServlet extends HttpServlet {

    /**
     * This method is processing GET and POST requests
     * @param req - request
     * @param resp - response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        SystemDAO dao = new SystemDAO();

        int stationId1 = Integer.parseInt(req.getParameter("listStationsFrom"));
        int stationId2 = Integer.parseInt(req.getParameter("listStationsTo"));
        if (stationId1 == stationId2)
            getServletContext().getRequestDispatcher("/BusesFrameFailSameStation.jsp").forward(req, resp);

        String strDate1 = req.getParameter("minTime");
        String strDate2 = req.getParameter("maxTime");
        // java.util.Data objects
        Date date1= null;
        Date date2= null;

        // Trying to parse strings that store dates
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(strDate1);
            date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(strDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Find buses
        List<Bus> buses = dao.getBusesBetweenStations(stationId1, stationId2, date1, date2);
        // Create a model
        FindBusForm findBusForm = new FindBusForm();
        findBusForm.setBuses(buses);
        // Connect model with view
        req.setAttribute("form", findBusForm);

        if (buses.size() == 0)
            getServletContext().getRequestDispatcher("/BusesFrameFail.jsp").forward(req, resp);
        else
            getServletContext().getRequestDispatcher("/BusesFrame.jsp").forward(req, resp);
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