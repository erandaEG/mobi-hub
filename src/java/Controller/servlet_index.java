package Controller;

import DataExtractor.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.Instant;

public class servlet_index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("dialcom") == null && session.getAttribute("techmart") == null && session.getAttribute("celltronics") == null) {
            Instant start = Instant.now();
            session.setAttribute("dialcom", DataExtractor_dialcom.checkAvailablePhones());
            session.setAttribute("techmart", DataExtractor_techmart.checkAvailablePhones());
            session.setAttribute("celltronics", DataExtractor_celltronics.checkAvailablePhones());
            Instant finish = Instant.now();
            System.out.println("data extraction duration: " + Duration.between(start, finish).getSeconds());

            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request, response);
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request, response);
        }
    }
}
