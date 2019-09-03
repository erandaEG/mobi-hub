package Controller;

import DataExtractor.*;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class servlet_home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        double searchAmount = Double.parseDouble(request.getParameter("search"));

        String url = "";

//<editor-fold defaultstate="collapsed" desc="Defining smartphone price-range to be searched">
        double amountInEuros = searchAmount / 201.01;

        if (amountInEuros <= 0) {
            url = "invalid input";
        } else if (amountInEuros < 50) {
            url = "invalid input";
        } else if (amountInEuros < 300) {
            url = "https://www.gsmarena.com/results.php3?nYearMin=2018nPriceMin=50&nPriceMax=300";
        } else if (amountInEuros < 500) {
            url = "https://www.gsmarena.com/results.php3?nYearMin=2018nPriceMin=300&nPriceMax=500";
        } else if (amountInEuros > 500) {
            url = "https://www.gsmarena.com/results.php3?nYearMin=2018&nPriceMin=500";
        } else {
            url = "invalid input";
        }
//</editor-fold>

        if (url.equals("invalid input")) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Invalid Input!");
        } else {
            try {
                Map<String, String[]> smartphoneData = DataExtractor_gsmarena.findSmartphonesByBudget(url, searchAmount, (Map<String, Double>) session.getAttribute("dialcom"), (Map<String, Double>) session.getAttribute("techmart"), (Map<String, Double>) session.getAttribute("celltronics"), session, request);
                if (smartphoneData == null) {
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("No Smartphones!");
                } else {
                    String[] smartphoneNames = new String[smartphoneData.size()];
                    String[] smartphoneLinks = new String[smartphoneData.size()];
                    String[] smartphoneImages = new String[smartphoneData.size()];
                    int i = 0;

                    for (String key : smartphoneData.keySet()) {
                        smartphoneNames[i] = key;
                        smartphoneLinks[i] = smartphoneData.get(key)[0];
                        smartphoneImages[i] = smartphoneData.get(key)[1];

                        i++;
                    }

                    session.setAttribute("smartphoneNames", smartphoneNames);
                    session.setAttribute("smartphoneLinks", smartphoneLinks);
                    session.setAttribute("smartphoneImages", smartphoneImages);
                }
            } catch (ConnectException | SocketTimeoutException ex) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Connection Error!");
            } catch (UnknownHostException ex) {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("No Internet!");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(servlet_home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
