package DataExtractor;

import NaiveBayesSentimentAnalyzer.SentimentAnalyzer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.math3.linear.RealMatrix;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataExtractor_gsmarena {

    public static Map<String, String[]> findSmartphonesByBudget(String url, double userBudget, Map<String, Double> dialcomPhoneList, Map<String, Double> techmartPhoneList, Map<String, Double> celltronicsPhoneList, HttpSession session, HttpServletRequest request) throws ConnectException, SocketTimeoutException, IOException, ClassNotFoundException {
        ServletContext context = request.getServletContext();
        InputStream fis = context.getResourceAsStream("/WEB-INF/positiveProbabilities.txt");
        ObjectInputStream iis = new ObjectInputStream(fis);
        RealMatrix savedPositiveProbabilities = (RealMatrix) iis.readObject();

        fis = context.getResourceAsStream("/WEB-INF/negativeProbabilities.txt");
        iis = new ObjectInputStream(fis);
        RealMatrix savedNegativeProbabilities = (RealMatrix) iis.readObject();

        fis = context.getResourceAsStream("/WEB-INF/priorProbability.txt");
        iis = new ObjectInputStream(fis);
        double savedPriorProbability = (double) iis.readObject();

        fis = context.getResourceAsStream("/WEB-INF/vocabulary.txt");
        iis = new ObjectInputStream(fis);
        ArrayList<String> savedVocabulary = (ArrayList<String>) iis.readObject();

        fis.close();
        iis.close();

        List<String> dialcomAvailableList = new ArrayList<>();
        List<String> techmartAvailableList = new ArrayList<>();
        List<String> celltronicsAvailableList = new ArrayList<>();
        List<String> finalMergeList = new ArrayList<>();

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com").timeout(0)
                .get();

        Elements phoneList = doc.select("#review-body > div.makers > ul > li");
        Elements names = phoneList.select("a > strong > span");

//<editor-fold defaultstate="collapsed" desc="Cross-referencing the smartphones for Sri Lankan market availability">
//<editor-fold defaultstate="collapsed" desc="Check availability in Dialcom">
        for (Element name : names) {
            for (String dialcomPhoneName : dialcomPhoneList.keySet()) {
                if ((dialcomPhoneName.toLowerCase().contains(name.ownText().toLowerCase()) || dialcomPhoneName.toLowerCase().contains(name.ownText().toLowerCase().replace("+", " plus"))) && (dialcomPhoneList.get(dialcomPhoneName) < userBudget)) {
                    if (!dialcomAvailableList.contains(name.ownText())) {
                        dialcomAvailableList.add(name.ownText());
                    }
                }
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Check availability in Techmart">
        for (Element name : names) {
            for (String techmartPhoneName : techmartPhoneList.keySet()) {
                if ((techmartPhoneName.toLowerCase().contains(name.ownText().toLowerCase()) || techmartPhoneName.toLowerCase().contains(name.ownText().toLowerCase().replace("+", " plus"))) && (techmartPhoneList.get(techmartPhoneName) < userBudget)) {
                    if (!techmartAvailableList.contains(name.ownText())) {
                        techmartAvailableList.add(name.ownText());
                    }
                }
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Check availability in Celltronics">
        for (Element name : names) {
            for (String celltronicsPhoneName : celltronicsPhoneList.keySet()) {
                if ((celltronicsPhoneName.toLowerCase().contains(name.ownText().toLowerCase()) || celltronicsPhoneName.toLowerCase().contains(name.ownText().toLowerCase().replace("+", " plus"))) && (celltronicsPhoneList.get(celltronicsPhoneName) < userBudget)) {
                    if (!celltronicsAvailableList.contains(name.ownText())) {
                        celltronicsAvailableList.add(name.ownText());
                    }
                }
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Merge all the available phones into one List">
        finalMergeList.addAll(dialcomAvailableList);

        for (String techmartPhoneName : techmartAvailableList) {
            if (!finalMergeList.contains(techmartPhoneName)) {
                finalMergeList.add(techmartPhoneName);
            }
        }

        for (String celltronicsPhoneName : celltronicsAvailableList) {
            if (!finalMergeList.contains(celltronicsPhoneName)) {
                finalMergeList.add(celltronicsPhoneName);
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Get the specifications links and images of all the available phones and the best phone out of all the available phones">
        if (finalMergeList.isEmpty()) {
            return null;
        }

        String[] anchors = new String[finalMergeList.size()];
        String[] images = new String[finalMergeList.size()];

        int round = 0;
        double highestScore = 0.0;
        String bestPhoneName = " ";
        for (Element li : phoneList) {
            String currentPhoneName = li.select("a > strong > span").get(0).ownText();
            if (finalMergeList.contains(currentPhoneName)) {
                anchors[finalMergeList.indexOf(currentPhoneName)] = li.select("a").attr("href");

                List<String> tempReviewList = getReviews(li.select("a").attr("href"));
                double tempScaleOfPositivity = SentimentAnalyzer.getScaleOfPositivity(tempReviewList, savedPositiveProbabilities, savedNegativeProbabilities, savedPriorProbability, savedVocabulary);

                if (round == 0) {
                    highestScore = tempScaleOfPositivity;
                }

                highestScore = Math.max(highestScore, tempScaleOfPositivity);

                if (highestScore == tempScaleOfPositivity) {
                    bestPhoneName = currentPhoneName;
                }

                images[finalMergeList.indexOf(currentPhoneName)] = li.select("a > img").attr("src");

                round++;
            }
        }
//</editor-fold>

        String bestPhoneSpecLink = anchors[finalMergeList.indexOf(bestPhoneName)];
        String bestPhoneImageLink = images[finalMergeList.indexOf(bestPhoneName)];

//<editor-fold defaultstate="collapsed" desc="Gather all the data collected into a Hashmap">
        Map<String, String[]> smartphoneData = new HashMap<>();

        for (int i = 0; i < finalMergeList.size(); i++) {
            String[] linkAndImage = new String[2];
            linkAndImage[0] = anchors[i];
            linkAndImage[1] = images[i];

            smartphoneData.put(finalMergeList.get(i), linkAndImage);
        }
//</editor-fold>

//</editor-fold>
        session.setAttribute("bestPhoneName", bestPhoneName);
        session.setAttribute("bestPhoneImageLink", bestPhoneImageLink);
        System.out.println(bestPhoneImageLink);
        session.setAttribute("bestPhoneSpecLink", bestPhoneSpecLink);
        Map<String, List<String>> bestPhoneSpecs = getSpecifications("https://www.gsmarena.com/" + bestPhoneSpecLink);
        System.out.println(bestPhoneSpecs.get("highlightedSpecs"));
        session.setAttribute("bestPhoneHighlightedSpecs", bestPhoneSpecs.get("highlightedSpecs"));

        session.setAttribute("dialcomAvailableList", dialcomAvailableList);
        session.setAttribute("techmartAvailableList", techmartAvailableList);
        session.setAttribute("celltronicsAvailableList", celltronicsAvailableList);

        return smartphoneData;
    }

    public static Map<String, List<String>> getSpecifications(String url) throws ConnectException, SocketTimeoutException, IOException {

//<editor-fold defaultstate="collapsed" desc="Code to get specifications list">
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com").timeout(0)
                .get();

        Elements specsElements = doc.select("#specs-list > table");
        specsElements.select("a").unwrap();

        List<String> specsList = new ArrayList<>();

        for (Element spec : specsElements) {
            specsList.add(spec.toString());
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Code to get highlighted specs">
        Elements name = doc.select("h1.specs-phone-name-title");
        Elements display = doc.select("div.center-stage > ul > li.help-display > strong > span");
        Elements displayDiv = doc.select("div.center-stage > ul > li.help-display > div");
        Elements camera = doc.select("div.center-stage > ul > li.help-camera > strong > span");
        Elements cameraDiv = doc.select("div.center-stage > ul > li.help-camera > div");
        Elements expansion = doc.select("div.center-stage > ul > li.help-expansion > strong > span");
        Elements expansionDiv = doc.select("div.center-stage > ul > li.help-expansion > div");
        Elements battery = doc.select("div.center-stage > ul > li.help-battery > strong > span");
        Elements batteryDiv = doc.select("div.center-stage > ul > li.help-battery > div");
        Elements briefSpecs = doc.select("div.center-stage > ul > li.specs-brief > span.specs-brief-accent > span");

        String[] splits = name.text().split(" ", 2);

        List<String> highlightedSpecs = new ArrayList<>();
        highlightedSpecs.add(name.text()); //full name
        highlightedSpecs.add(splits[0]); //brand
        highlightedSpecs.add(splits[1]); //model
        highlightedSpecs.add(display.text());
        highlightedSpecs.add(displayDiv.text());
        highlightedSpecs.add(camera.text());
        highlightedSpecs.add(cameraDiv.text());
        highlightedSpecs.add(expansion.text());
        highlightedSpecs.add(expansionDiv.text());
        highlightedSpecs.add(battery.text());
        highlightedSpecs.add(batteryDiv.text());

        for (Element briefSpec : briefSpecs) {
            highlightedSpecs.add(briefSpec.select("span").text());
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Code to get images">
        Elements headLinks = doc.select("ul.article-info-meta > li > a");
        String picturesLink = " ";

        for (int i = 0; i < headLinks.size(); i++) {
            if (headLinks.get(i).text().equals("Pictures")) {
                picturesLink = headLinks.get(i).attr("href");
            }
        }

        doc = Jsoup.connect("https://www.gsmarena.com/" + picturesLink)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com").timeout(0)
                .get();

        Elements images = doc.select("#pictures-list > img");

        List<String> imagesList = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).attr("src").isEmpty()) {
                imagesList.add(images.get(i).attr("data-src"));
            } else {
                imagesList.add(images.get(i).attr("src"));
            }
        }
//</editor-fold>

        Map<String, List<String>> specificationsData = new HashMap<>();
        specificationsData.put("specsList", specsList);
        specificationsData.put("highlightedSpecs", highlightedSpecs);
        specificationsData.put("imagesList", imagesList);

        return specificationsData;
    }

    public static List<String> getReviews(String url) throws ConnectException, SocketTimeoutException, IOException, ClassNotFoundException, FileNotFoundException {
        System.out.println("getReviews() called for " + url);
        List<String> reviewList = new ArrayList<>();

        Document doc = Jsoup.connect("https://www.gsmarena.com/" + url)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com").timeout(0)
                .get();

        Elements headLinks = doc.select("ul.article-info-meta > li > a");
        String opinionsLink = " ";

        for (int i = 0; i < headLinks.size(); i++) {
            if (headLinks.get(i).text().equals("Opinions")) {
                opinionsLink = headLinks.get(i).attr("href");
            }
        }

        doc = Jsoup.connect("https://www.gsmarena.com/" + opinionsLink)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com").timeout(0)
                .get();

        Elements userOpinionNextPage = doc.select("div.user-comments > div.sub-footer > div.nav-pages > a");
        String nextPage = " ";

        int pageCount = 0;

        do {
            pageCount++;
            Elements reviewElements = doc.select("#all-opinions > div.user-thread");

            for (Element review : reviewElements) {
                if (review.select("p.uopin > span.uinreply-msg").isEmpty()) {
                    reviewList.add(review.select("p.uopin").text());
                }
            }

            userOpinionNextPage = doc.select("div.sub-footer > #user-pages > a");

            for (int i = 0; i < userOpinionNextPage.size(); i++) {
                if (userOpinionNextPage.get(i).attr("title").equals("Next page")) {
                    nextPage = userOpinionNextPage.get(i).attr("href");
                } else {
                    nextPage = "No Next Page";
                }
            }

            if (!nextPage.equals("No Next Page")) {
                doc = Jsoup.connect("https://www.gsmarena.com/" + nextPage)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com").timeout(0)
                        .get();
            }
        } while (pageCount == 1);

        System.out.println("Number of review pages for " + url + ": " + pageCount);

        return reviewList;
    }

    public static Map<String, String[]> findSmartphonesByBrand(String brandCode, Map<String, Double> dialcomPhoneList, Map<String, Double> techmartPhoneList, Map<String, Double> celltronicsPhoneList) throws ConnectException, SocketTimeoutException, IOException {
        List<String> dialcomAvailableList = new ArrayList<>();
        List<String> techmartAvailableList = new ArrayList<>();
        List<String> celltronicsAvailableList = new ArrayList<>();
        List<String> finalMergeList = new ArrayList<>();

        Document doc = Jsoup.connect("https://www.gsmarena.com/results.php3?nYearMin=2018&sMakers=" + brandCode)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com").timeout(0)
                .get();

        Elements phoneList = doc.select("#review-body > div.makers > ul > li");
        Elements names = phoneList.select("a > strong > span");

//<editor-fold defaultstate="collapsed" desc="Cross-referencing the smartphones for Sri Lankan market availability">
//<editor-fold defaultstate="collapsed" desc="Check availability in Dialcom">
        for (Element name : names) {
            for (String dialcomPhoneName : dialcomPhoneList.keySet()) {
                if ((dialcomPhoneName.toLowerCase().contains(name.ownText().toLowerCase()) || dialcomPhoneName.toLowerCase().contains(name.ownText().toLowerCase().replace("+", " plus")))) {
                    if (!dialcomAvailableList.contains(name.ownText())) {
                        dialcomAvailableList.add(name.ownText());
                    }
                }
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Check availability in Techmart">
        for (Element name : names) {
            for (String techmartPhoneName : techmartPhoneList.keySet()) {
                if ((techmartPhoneName.toLowerCase().contains(name.ownText().toLowerCase()) || techmartPhoneName.toLowerCase().contains(name.ownText().toLowerCase().replace("+", " plus")))) {
                    if (!techmartAvailableList.contains(name.ownText())) {
                        techmartAvailableList.add(name.ownText());
                    }
                }
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Check availability in Celltronics">
        for (Element name : names) {
            for (String celltronicsPhoneName : celltronicsPhoneList.keySet()) {
                if ((celltronicsPhoneName.toLowerCase().contains(name.ownText().toLowerCase()) || celltronicsPhoneName.toLowerCase().contains(name.ownText().toLowerCase().replace("+", " plus")))) {
                    if (!celltronicsAvailableList.contains(name.ownText())) {
                        celltronicsAvailableList.add(name.ownText());
                    }
                }
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Merge all the available phones into one List">
        finalMergeList.addAll(dialcomAvailableList);

        for (String techmartPhoneName : techmartAvailableList) {
            if (!finalMergeList.contains(techmartPhoneName)) {
                finalMergeList.add(techmartPhoneName);
            }
        }

        for (String celltronicsPhoneName : celltronicsAvailableList) {
            if (!finalMergeList.contains(celltronicsPhoneName)) {
                finalMergeList.add(celltronicsPhoneName);
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Get the specifications links and images of all the available phones">
        String[] anchors = new String[finalMergeList.size()];
        String[] images = new String[finalMergeList.size()];

        for (Element li : phoneList) {
            String currentPhoneName = li.select("a > strong > span").get(0).ownText();
            if (finalMergeList.contains(currentPhoneName)) {
                anchors[finalMergeList.indexOf(currentPhoneName)] = li.select("a").attr("href");
                images[finalMergeList.indexOf(currentPhoneName)] = li.select("a > img").attr("src");
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Gather all the data collected into a Hashmap">
        Map<String, String[]> smartphoneData = new HashMap<>();

        for (int i = 0; i < finalMergeList.size(); i++) {
            String[] linkAndImage = new String[2];
            linkAndImage[0] = anchors[i];
            linkAndImage[1] = images[i];

            smartphoneData.put(finalMergeList.get(i), linkAndImage);
        }
//</editor-fold>

//</editor-fold>
        return smartphoneData;
    }

    public static Map<String, String[]> findLatestSmartphones(Map<String, Double> dialcomPhoneList, Map<String, Double> techmartPhoneList, Map<String, Double> celltronicsPhoneList) throws ConnectException, SocketTimeoutException, IOException {
        List<String> dialcomAvailableList = new ArrayList<>();
        List<String> techmartAvailableList = new ArrayList<>();
        List<String> celltronicsAvailableList = new ArrayList<>();
        List<String> finalMergeList = new ArrayList<>();

        Document doc = Jsoup.connect("https://www.gsmarena.com/results.php3?nYearMin=" + Calendar.getInstance().get(Calendar.YEAR))
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com").timeout(0)
                .get();

        Elements phoneList = doc.select("#review-body > div.makers > ul > li");
        Elements names = phoneList.select("a > strong > span");

//<editor-fold defaultstate="collapsed" desc="Cross-referencing the smartphones for Sri Lankan market availability">
//<editor-fold defaultstate="collapsed" desc="Check availability in Dialcom">
        for (Element name : names) {
            for (String dialcomPhoneName : dialcomPhoneList.keySet()) {
                if ((dialcomPhoneName.toLowerCase().contains(name.ownText().toLowerCase()) || dialcomPhoneName.toLowerCase().contains(name.ownText().toLowerCase().replace("+", " plus")))) {
                    if (!dialcomAvailableList.contains(name.ownText())) {
                        dialcomAvailableList.add(name.ownText());
                    }
                }
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Check availability in Techmart">
        for (Element name : names) {
            for (String techmartPhoneName : techmartPhoneList.keySet()) {
                if ((techmartPhoneName.toLowerCase().contains(name.ownText().toLowerCase()) || techmartPhoneName.toLowerCase().contains(name.ownText().toLowerCase().replace("+", " plus")))) {
                    if (!techmartAvailableList.contains(name.ownText())) {
                        techmartAvailableList.add(name.ownText());
                    }
                }
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Check availability in Celltronics">
        for (Element name : names) {
            for (String celltronicsPhoneName : celltronicsPhoneList.keySet()) {
                if ((celltronicsPhoneName.toLowerCase().contains(name.ownText().toLowerCase()) || celltronicsPhoneName.toLowerCase().contains(name.ownText().toLowerCase().replace("+", " plus")))) {
                    if (!celltronicsAvailableList.contains(name.ownText())) {
                        celltronicsAvailableList.add(name.ownText());
                    }
                }
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Merge all the available phones into one List">
        finalMergeList.addAll(dialcomAvailableList);

        for (String techmartPhoneName : techmartAvailableList) {
            if (!finalMergeList.contains(techmartPhoneName)) {
                finalMergeList.add(techmartPhoneName);
            }
        }

        for (String celltronicsPhoneName : celltronicsAvailableList) {
            if (!finalMergeList.contains(celltronicsPhoneName)) {
                finalMergeList.add(celltronicsPhoneName);
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Get the specifications links and images of all the available phones">
        String[] anchors = new String[finalMergeList.size()];
        String[] images = new String[finalMergeList.size()];

        for (Element li : phoneList) {
            String currentPhoneName = li.select("a > strong > span").get(0).ownText();
            if (finalMergeList.contains(currentPhoneName)) {
                anchors[finalMergeList.indexOf(currentPhoneName)] = li.select("a").attr("href");
                images[finalMergeList.indexOf(currentPhoneName)] = li.select("a > img").attr("src");
            }
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Gather all the data collected into a Hashmap">
        Map<String, String[]> smartphoneData = new HashMap<>();

        for (int i = 0; i < finalMergeList.size(); i++) {
            String[] linkAndImage = new String[2];
            linkAndImage[0] = anchors[i];
            linkAndImage[1] = images[i];

            smartphoneData.put(finalMergeList.get(i), linkAndImage);
        }
//</editor-fold>

//</editor-fold>
        return smartphoneData;
    }
}
