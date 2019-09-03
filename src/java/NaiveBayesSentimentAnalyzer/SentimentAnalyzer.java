package NaiveBayesSentimentAnalyzer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class SentimentAnalyzer {

    private static final String[] questionWords = {"what", "who", "why", "when", "where", "how", "can", "is", "do", "does"};

    public static double getScaleOfPositivity(List<String> reviews, RealMatrix savedPositiveProbabilities, RealMatrix savedNegativeProbabilities, double savedPriorProbability, ArrayList<String> savedVocabulary) throws IOException, FileNotFoundException, ClassNotFoundException {
        double scaleOfPositivity = 0.0;

        int originalReviewArraySize = reviews.size();

        for (int i = 0; i < originalReviewArraySize; i++) {
            String[] splits = reviews.get(i).split("(?<=[.?!])");
            StringBuilder newString = new StringBuilder();

//<editor-fold defaultstate="collapsed" desc="Removing questions">
            for (String split : splits) {
                if (questionDetector(split)) {
                    newString.append(split);
                }
            }

            if (newString.toString().isEmpty()) {
                reviews.remove(i);
                continue;
            }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Removing unwanted symbols/numbers/spaces">
            reviews.add(i, newString.toString().trim());
            reviews.add(i, reviews.get(i).replaceAll("[.;:!=$£€%+\\'?,\\\"()\\[\\]]", ""));
            reviews.add(i, reviews.get(i).replaceAll("(<br\\s*/><br\\s*/>)|(\\-)|(\\/)", " "));
            reviews.add(i, reviews.get(i).replaceAll("\\d+", ""));
            reviews.add(i, reviews.get(i).replaceAll(" +", " "));

            if (reviews.get(i).isEmpty()) {
                reviews.remove(i);
                continue;
            }
//</editor-fold>

            String[] individualWords = reviews.get(i).split(" ");

            if (individualWords.length > 20) {
                reviews.remove(i);
            }
        }

        for (int i = 0; i < reviews.size(); i++) {
            String[] individualWords = reviews.get(i).split(" ");

            scaleOfPositivity += classifyReview(individualWords, savedPositiveProbabilities, savedNegativeProbabilities, savedPriorProbability, savedVocabulary);
        }

        return scaleOfPositivity;
    }

    public static double classifyReview(String[] docArray, RealMatrix savedPositiveProbabilities, RealMatrix savedNegativeProbabilities, double savedPriorProbability, ArrayList<String> savedVocabulary) throws FileNotFoundException, IOException, ClassNotFoundException {
        RealMatrix doc = MatrixUtils.createRowRealMatrix(mapReviewsToVocabulary(docArray, savedVocabulary));

        double pLogSums = Math.log(savedPriorProbability) + doc.multiply(savedPositiveProbabilities.transpose()).getData()[0][0];
        double nLogSums = Math.log(1 - savedPriorProbability) + doc.multiply(savedNegativeProbabilities.transpose()).getData()[0][0];

        if (pLogSums > nLogSums) {
            return pLogSums;
        }

        return 0;
    }

    private static double[] mapReviewsToVocabulary(String[] doc, ArrayList<String> savedVocabulary) throws FileNotFoundException, IOException, ClassNotFoundException {
        double[] mappedReview = new double[savedVocabulary.size()];

        for (int i = 0; i < savedVocabulary.size(); i++) {
            mappedReview[i] = 0;
        }

        for (String doc1 : doc) {
            for (int j = 0; j < savedVocabulary.size(); j++) {
                if (doc1.equalsIgnoreCase(savedVocabulary.get(j))) {
                    mappedReview[j] += 1;
                }
            }
        }

        return mappedReview;
    }

    public static boolean questionDetector(String str) {
        boolean noQuestionDetected = true;

        for (String questionWord : questionWords) {
            if (str.toLowerCase().startsWith(questionWord)) {
                noQuestionDetected = false;
            }
        }

        if (str.endsWith("?")) {
            noQuestionDetected = false;
        }

        return noQuestionDetected;
    }
}
