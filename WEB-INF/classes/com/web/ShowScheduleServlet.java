package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.interaction.*;

import com.forms.ShowScheduleForm;
import com.objects.*;

/**
 * This class defines the processing of GET and POST requests from "Buy ticket" html-form.
 */
public class ShowScheduleServlet extends HttpServlet {

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
        int stationId = Integer.parseInt(req.getParameter("listStations"));

        List<Schedule> records = dao.getScheduleByStationId(stationId);
        ShowScheduleForm showScheduleForm = new ShowScheduleForm();
        showScheduleForm.setRecords(records);

        req.setAttribute("form", showScheduleForm);
        if (records.size() > 0)
            getServletContext().getRequestDispatcher("/ScheduleFrame.jsp").forward(req, resp);
        else
            getServletContext().getRequestDispatcher("/ScheduleFrameFail.jsp").forward(req, resp);

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
