package DataExtractor;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataExtractor_dialcom {
    
    public static Map<String, Double> checkAvailablePhones() throws ConnectException, SocketTimeoutException, IOException {
        Map<String, Double> dialcomPhones = new HashMap<>();

        Document doc = Jsoup.connect("https://www.dialcom.lk/mobile-phones_price_in_sri_lanka/order-by-price/")
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com").timeout(0)
                .get();

        Elements pages = doc.select("div.pagibar > a");

        for (int i = 1; i <= pages.size(); i++) {
            Elements dialcomPhoneList = doc.select("div.product-gridview > ul > li > div.item-product > div.product-info");

            for (Element phone : dialcomPhoneList) {
                for (Element phoneName : phone.select("meta")) {
                    if (phoneName.attr("itemprop").equals("name")) {
                        String price = phone.select("div.product-price > ins > span").text();
                        price = price.replace("Rs. ", "");
                        price = price.replace(",", "");
                        if (!price.isEmpty()) {
                            if (Double.parseDouble(price) > 10000 && Double.parseDouble(price) < 300000) {
                                dialcomPhones.put(phoneName.attr("content"), Double.parseDouble(price));
                            }
                        }
                    }
                }
            }

            if (i < pages.size()) {
                doc = Jsoup.connect(pages.get(i).attr("href"))
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com").timeout(0)
                        .get();
            }
        }

        return dialcomPhones;
    }
}
