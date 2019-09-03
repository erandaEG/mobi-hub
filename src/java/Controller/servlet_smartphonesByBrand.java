package Controller;

import DataExtractor.DataExtractor_gsmarena;
import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class servlet_smartphonesByBrand extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String brandCode = request.getParameter("brandCode");
        HttpSession session = request.getSession();
        Map<String, Double> dialcomPhoneAndPriceList = (Map<String, Double>) session.getAttribute("dialcom");
        Map<String, Double> techmartPhoneAndPriceList = (Map<String, Double>) session.getAttribute("techmart");
        Map<String, Double> celltronicsPhoneAndPriceList = (Map<String, Double>) session.getAttribute("celltronics");

        try {
            Map<String, String[]> smartphoneData = DataExtractor_gsmarena.findSmartphonesByBrand(brandCode, dialcomPhoneAndPriceList, techmartPhoneAndPriceList, celltronicsPhoneAndPriceList);
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

            if (brandCode.equals("1")) {
                session.setAttribute("brandName", "Nokia");
            } else if (brandCode.equals("7")) {
                session.setAttribute("brandName", "Sony");
            } else if (brandCode.equals("9")) {
                session.setAttribute("brandName", "Samsung");
            } else if (brandCode.equals("48")) {
                session.setAttribute("brandName", "Apple");
            } else if (brandCode.equals("80")) {
                session.setAttribute("brandName", "Xiaomi");
            } else if (brandCode.equals("95")) {
                session.setAttribute("brandName", "One Plus");
            }
            session.setAttribute("smartphoneNames", smartphoneNames);
            session.setAttribute("smartphoneLinks", smartphoneLinks);
            session.setAttribute("smartphoneImages", smartphoneImages);

            RequestDispatcher rd = request.getRequestDispatcher("smartphonesByBrand.jsp");
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
