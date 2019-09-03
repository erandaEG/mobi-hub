package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servlet_smartphoneDealerDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("dealerName", request.getParameter("dealerName"));
        RequestDispatcher rd = request.getRequestDispatcher("smartphoneDealerDetails.jsp");
        rd.forward(request, response);
    }
}
