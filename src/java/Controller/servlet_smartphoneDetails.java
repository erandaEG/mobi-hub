package Controller;

import DataExtractor.DataExtractor_gsmarena;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.servlet.http.HttpSession;

public class servlet_smartphoneDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getParameter("url");
        String smartphoneName = request.getParameter("smartphoneName");
        HttpSession session = request.getSession();
        Map<String, Double> dialcomPhoneAndPriceList = (Map<String, Double>) session.getAttribute("dialcom");
        Map<String, Double> techmartPhoneAndPriceList = (Map<String, Double>) session.getAttribute("techmart");
        Map<String, Double> celltronicsPhoneAndPriceList = (Map<String, Double>) session.getAttribute("celltronics");
        boolean notAvailableInAnyShop = true;

        try {
            Map<String, List<String>> specifications = DataExtractor_gsmarena.getSpecifications("https://www.gsmarena.com/" + url);

            request.setAttribute("specsList", specifications.get("specsList"));
            request.setAttribute("images", specifications.get("imagesList"));
            request.setAttribute("highlightedSpecs", specifications.get("highlightedSpecs"));

            for (String name : dialcomPhoneAndPriceList.keySet()) {
                if (name.toLowerCase().contains(smartphoneName.toLowerCase())) {
                    request.setAttribute("dialcomStatus", "Available");
                    request.setAttribute("dialcomPrice", dialcomPhoneAndPriceList.get(name));
                    notAvailableInAnyShop = false;
                }
            }

            for (String name : techmartPhoneAndPriceList.keySet()) {
                if (name.toLowerCase().contains(smartphoneName.toLowerCase())) {
                    request.setAttribute("techmartStatus", "Available");
                    request.setAttribute("techmartPrice", techmartPhoneAndPriceList.get(name));
                    notAvailableInAnyShop = false;
                }
            }

            for (String name : celltronicsPhoneAndPriceList.keySet()) {
                if (name.toLowerCase().contains(smartphoneName.toLowerCase())) {
                    request.setAttribute("celltronicsStatus", "Available");
                    request.setAttribute("celltronicsPrice", celltronicsPhoneAndPriceList.get(name));
                    notAvailableInAnyShop = false;
                }
            }

            if (notAvailableInAnyShop) {
                request.setAttribute("notAvailableInAnyShop", notAvailableInAnyShop);
            }

            RequestDispatcher rd = request.getRequestDispatcher("smartphoneDetails.jsp");
            rd.forward(request, response);
        } catch (ConnectException | SocketTimeoutException ex) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Internal Server Error!");
        } catch (UnknownHostException | NoRouteToHostException ex) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("No Internet!");
        }
    }
}
