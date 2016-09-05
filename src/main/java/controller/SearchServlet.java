package main.java.controller;

import main.java.valueobject.CountryAirportsVO;
import main.java.valueobject.SearchInfo;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "controller.SearchServlet", urlPatterns = {"/controller.SearchServlet"})
public class SearchServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(SearchServlet.class);
    static CountryAirportsVO countryAirportsVO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug(":: START doPost function :: ");
        RequestDispatcher rd;

        String radio = request.getParameter("radios");
        SearchInfo searchInfo = new SearchInfo();
        if (radio.equals("radio1")) {
            searchInfo.setCountryCode(request.getParameter("searchField"));
        } else if (radio.equals("radio2")) {
            searchInfo.setCountryName(request.getParameter("searchField"));
        }

        BusinessHandler businessHandler = new BusinessHandler();
        try {
            countryAirportsVO = businessHandler.getCountryAirportsVO(searchInfo);
            doGet(request, response);
        } catch (Exception e) {
            logger.error(e, e);
            request.setAttribute("exception", e);
            rd = request.getRequestDispatcher("/errorPage.jsp");
            rd.forward(request, response);
        }

        logger.debug(":: END doPost function :: ");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        try {

            int page = 1;
            int recordsPerPage = 7;
            int numOfPageIndex = 4;
            if (request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            CountryAirportsVO countryAirportsInfo = new CountryAirportsVO(countryAirportsVO.getCountryName(), countryAirportsVO.getCountryCode());
            countryAirportsInfo.setAirportVOList(BusinessHandler.getAirportVOListToDisplay(countryAirportsVO.getAirportVOList(), (page - 1) * recordsPerPage, recordsPerPage));

            int noOfRecords = countryAirportsVO.getAirportVOList() != null ? countryAirportsVO.getAirportVOList().size() : 0;
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            int firstPageIndex = Math.max(1, page - numOfPageIndex);
            int lastPageIndex = Math.min(noOfPages, page + numOfPageIndex);
            request.setAttribute("countryAirportsInfo", countryAirportsInfo);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("firstPageIndex", firstPageIndex);
            request.setAttribute("lastPageIndex", lastPageIndex);

            rd = request.getRequestDispatcher("/resultPage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            logger.error(e, e);
            request.setAttribute("exception", e);
            rd = request.getRequestDispatcher("/errorPage.jsp");
            rd.forward(request, response);
        }
    }
}
