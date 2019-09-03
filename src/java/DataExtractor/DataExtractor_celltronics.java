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

public class DataExtractor_celltronics {

    public static Map<String, Double> checkAvailablePhones() throws ConnectException, SocketTimeoutException, IOException {

        Map<String, Double> celltronicsPhones = new HashMap<>();

        Document doc = Jsoup.connect("https://www.celltronics.lk/mobile-phones.html")
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com").timeout(0)
                .get();

        Elements celltronicsNextPage = doc.select("div.toolbar-products > div.pages > ul > li.pages-item-next");
        
        int counter = 0;

        do {
            counter++;
            Elements celltronicsPhoneList = doc.select("div.products-grid > ul > li");

            for (Element phone : celltronicsPhoneList) {
                //if phone is in stock
                if (phone.select("div.product-item-info > div.product-item-details > div.box-hover > div.actions-primary > div.unavailable > span").isEmpty()) {

                    //if this condition is true, then it means there's a special price available
                    if (phone.select("div.product-item-info > div.product-item-details > div.item-price > div.price-box > span.price-container > span.price-wrapper").attr("data-price-amount").isEmpty()) {
                        double specialPrice = Double.parseDouble(phone.select("div.product-item-info > div.product-item-details > div.item-price > div.price-box > span.special-price > span.price-container > span.price-wrapper").attr("data-price-amount"));

                        if (specialPrice > 10000 && specialPrice < 300000) {
                            celltronicsPhones.put(phone.select("div.product-item-info > div.product-item-details > strong.product-item-name > a").text(), specialPrice);
                        }
                    } else {
                        double normalPrice = Double.parseDouble(phone.select("div.product-item-info > div.product-item-details > div.item-price > div.price-box > span.price-container > span.price-wrapper").attr("data-price-amount"));

                        if (normalPrice > 10000 && normalPrice < 300000) {
                            celltronicsPhones.put(phone.select("div.product-item-info > div.product-item-details > strong.product-item-name > a").text(), normalPrice);
                        }
                    }
                }
            }

            celltronicsNextPage = doc.select("div.toolbar-products > div.pages > ul > li.pages-item-next");

            if (!celltronicsNextPage.isEmpty()) {
                doc = Jsoup.connect(doc.select("div.toolbar-products > div.pages > ul > li.pages-item-next > a").attr("href"))
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com").timeout(0)
                        .get();
            }
        } while (!celltronicsNextPage.isEmpty());

        return celltronicsPhones;
    }
}
