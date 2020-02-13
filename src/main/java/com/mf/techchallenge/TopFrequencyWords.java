package com.mf.techchallenge;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents top most occurring words, in descending order of the number of occurrences.
 *
 * @author rajender
 * @since 12/02/2020 - 6:32 PM
 */

public class TopFrequencyWords {

    private final String SPACE = " ";
    private final int topWordsLimit;

    /**
     * Parametrized constructor to set the top number of most occurring words in descending order
     *
     * @param topWordsLimit This is the number of top most occurring words
     */
    public TopFrequencyWords(int topWordsLimit) {
        this.topWordsLimit = topWordsLimit;
    }

    /**
     * This method takes the input string and return desired output
     *
     * @param input This is the input string
     * @return Array of String having top most occurring words, in descending order of the number of occurrences.
     */
    String[] getWordsInDescendingOrderOfOccurrence(String input) {

        if (input == null || input.isEmpty()) {
            return new String[0];
        }

        //Text normalization
        input = normalizeText(input);

        //HashMap of all words in descending order value
        Map<String, Integer> hMapWords = calculateOccurrenceOfWordsInDescendingOrder(input);

        return hMapWords.keySet().stream()
                .limit(topWordsLimit)
                .toArray(String[]::new);
    }

    /**
     * This method takes the input string and normalize string by removing break lines, tabs, special characters, etc.
     *
     * @param inputText This is the string which has to be normalized
     * @return String This returns normalized string
     */
    String normalizeText(String inputText) {

        final String REGEX_FOR_SPCL_CHAR = "[\n\r\t/.:,;\"]";
        final String REGEX_FOR_MULTI_SPACE = " +";
        final String EMPTY_STRING = "";

        return inputText == null ? EMPTY_STRING :
                inputText.toLowerCase()
                        .replaceAll(REGEX_FOR_SPCL_CHAR, EMPTY_STRING)
                        .replaceAll(REGEX_FOR_MULTI_SPACE, SPACE)
                        .trim();
    }

    /**
     * This method takes the normalized string as an input and calculate occurrence of words in a string
     *
     * @param normalizedString This is the normalized string
     * @return Map<String, Integer> which contains word with their occurrences as value in descending order
     */
    Map<String, Integer> calculateOccurrenceOfWordsInDescendingOrder(String normalizedString) {

        Map<String, Integer> hMapOfWordWithOccurrence = new HashMap<>();
        String[] arrWords = normalizedString.split(SPACE);

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
