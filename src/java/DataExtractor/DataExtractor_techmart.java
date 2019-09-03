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

public class DataExtractor_techmart {

    public static Map<String, Double> checkAvailablePhones() throws ConnectException, SocketTimeoutException, IOException {

        Map<String, Double> techmartPhones = new HashMap<>();

        Document doc = Jsoup.connect("https://www.techmart.lk/mobile-phone.html?product_list_order=price&product_list_limit=30")
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com").timeout(0)
                .get();

        Elements nextPage = doc.select("div.toolbar-products > div.pages > ul > li.pages-item-next");

        do {
            Elements techmartPhoneList = doc.select("div.products-grid > ol > li");

            for (Element phone : techmartPhoneList) {
                //if phone is in stock
                if (phone.select("div.product-item-info > div.product-item-details > div.product-item-inner > div.product-item-actions > div.actions-primary > div.unavailable > span").isEmpty()) {

                    //if this condition is true, then it means there's a special price available
                    if (phone.select("div.product-item-info > div.product-item-details > div.price-box > span.price-container > span.price-wrapper").attr("data-price-amount").isEmpty()) {
                        double specialPrice = Double.parseDouble(phone.select("div.product-item-info > div.product-item-details > div.price-box > span.special-price > span.price-container > span.price-wrapper").attr("data-price-amount"));

                        if (specialPrice > 10000 && specialPrice < 300000) {
                            techmartPhones.put(phone.select("div.product-item-info > div.product-item-details > strong.product-item-name > a").text(), specialPrice);
                        }
                    } else {
                        double normalPrice = Double.parseDouble(phone.select("div.product-item-info > div.product-item-details > div.price-box > span.price-container > span.price-wrapper").attr("data-price-amount"));

                        if (normalPrice > 10000 && normalPrice < 300000) {
                            techmartPhones.put(phone.select("div.product-item-info > div.product-item-details > strong.product-item-name > a").text(), normalPrice);
                        }
                    }
                }
            }

            nextPage = doc.select("div.toolbar-products > div.pages > ul > li.pages-item-next");

            if (!nextPage.isEmpty()) {
                doc = Jsoup.connect(doc.select("div.toolbar-products > div.pages > ul > li.pages-item-next > a").attr("href"))
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com").timeout(0)
                        .get();
            }
        } while (!nextPage.isEmpty());

        return techmartPhones;
    }
}
