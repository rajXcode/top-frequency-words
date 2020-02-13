package com.mf.techchallenge;

import com.mf.techchallenge.utils.Constants;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents top most occurring words, in descending order of the number of occurrences.
 * @author rajender
 * @since 12/02/2020 - 6:32 PM
 */

public class TopFrequencyWords {

    private final int topWordsLimit;
    private static final Logger LOGGER = Logger.getLogger(TopFrequencyWords.class);

    /**
     * Parametrized constructor to set the top number of most occurring words in descending order
     */
    public TopFrequencyWords() {
        this.topWordsLimit = Constants.topWordsLimit;
    }

    /**
     * This method takes the input string and return desired output
     * @param input This is the input string
     * @return Array of String having top most occurring words, in descending order of the number of occurrences.
     */
    String[] getWordsInDescendingOrderOfOccurrence(String input) {

        LOGGER.debug("Inside getWordsInDescendingOrderOfOccurrence");

        if (input == null || input.isEmpty()) {
            LOGGER.debug("Input is empty or null");
            return new String[0];
        }

        //Text normalization
        input = normalizeText(input);

        //HashMap of all words in descending order value
        Map<String, Integer> hMapWords = calculateOccurrenceOfWordsInDescendingOrder(input);

        LOGGER.debug("Returning desired output");
        return hMapWords.keySet().stream()
                .limit(topWordsLimit)
                .toArray(String[]::new);
    }

    /**
     * This method takes the input string and normalize string by removing break lines, tabs, special characters, etc.
     * @param inputText This is the string which has to be normalized
     * @return String This returns normalized string
     */
    String normalizeText(String inputText) {

        LOGGER.debug("Normalizing the text -> " + inputText);
        return inputText == null ? Constants.EMPTY_STRING :
                inputText.toLowerCase()
                        .replaceAll(Constants.REGEX_FOR_SPCL_CHAR, Constants.EMPTY_STRING)
                        .replaceAll(Constants.REGEX_FOR_MULTI_SPACE, Constants.SPACE)
                        .trim();
    }

    /**
     * This method takes the normalized string as an input and calculate occurrence of words in a string
     * @param normalizedString This is the normalized string
     * @return Map<String, Integer> which contains word with their occurrences as value in descending order
     */
    Map<String, Integer> calculateOccurrenceOfWordsInDescendingOrder(String normalizedString) {

        LOGGER.debug("calculateOccurrenceOfWordsInDescendingOrder : Finding words with their occurrences in descending order");

        Map<String, Integer> hMapOfWordWithOccurrence = new HashMap<>();
        String[] arrWords = normalizedString.split(Constants.SPACE);

        for (String word : arrWords) {
            if (hMapOfWordWithOccurrence.containsKey(word)) {
                hMapOfWordWithOccurrence.put(word, hMapOfWordWithOccurrence.get(word) + 1);
            } else {
                hMapOfWordWithOccurrence.put(word, 1);
            }
        }

        Map<String, Integer> finalMapWordsWithDescendingOccurrence = new LinkedHashMap<>();
        hMapOfWordWithOccurrence.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> finalMapWordsWithDescendingOccurrence.put(x.getKey(), x.getValue()));

        return finalMapWordsWithDescendingOccurrence;
    }
}
