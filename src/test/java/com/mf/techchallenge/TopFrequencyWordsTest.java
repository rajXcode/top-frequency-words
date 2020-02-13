package com.mf.techchallenge;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Represents test cases for different types of input
 *
 * @author rajender
 * @since 13/02/2020 - 9:54 AM
 */
public class TopFrequencyWordsTest {

    private TopFrequencyWords topFrequencyWords;


    /**
     * This method is setting the word limit in parametrized constructor before invoking test cases
     */
    @Before
    public void init() {
        topFrequencyWords = new TopFrequencyWords();
    }

    /**
     * This method ensures the input text is normalized
     */
    @Test
    public void testTextNormalization() {
        String text = "   \"T,\ne.\ts;t \ri:N   g\"   ";
        assertThat(topFrequencyWords.normalizeText(text)).isEqualTo("test in g");
    }

    /**
     * This method ensures handling long string
     */
    @Test
    public void testHandlingLongString() {
        String text = "In a village of La Mancha, the name of which I have\n" +
                "no desire to call to\n" +
                "mind, there lived not long since one of those gentlemen that keep a lance\n" +
                "in the lance-rack, an old buckler, a lean hack, and a greyhound for\n" +
                "coursing. An olla of rather more beef than mutton, a salad on most\n" +
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra\n" +
                "on Sundays, made away with three-quarters of his income.\")\n";

        assertThat(topFrequencyWords.getWordsInDescendingOrderOfOccurrence(text)).containsExactly("a", "of", "on");
    }

    /**
     * This method ensures handling short string
     */
    @Test
    public void testHandlingShortString() {
        String text = "wont won't won't";
        assertThat(topFrequencyWords.getWordsInDescendingOrderOfOccurrence(text)).containsExactly("won't", "wont");
    }

    /**
     * This method ensures clear unit test report for empty or null string
     */
    @Test
    public void testEmptyOrNullString() {
        String text = "";
        assertThat(topFrequencyWords.getWordsInDescendingOrderOfOccurrence(text)).isEmpty();
        assertThat(topFrequencyWords.getWordsInDescendingOrderOfOccurrence(null)).isEmpty();
    }

    /**
     * This method ensures correctness for calculation of word occurrences
     */
    @Test
    public void calculateOccurrenceOfWordsInDescendingOrder() {
        String normalizedString = "x z x y z x";

        Map<String, Integer> mapWords = topFrequencyWords.calculateOccurrenceOfWordsInDescendingOrder(normalizedString);

        assertThat(mapWords.keySet()).containsExactly("x", "z", "y");
        assertThat(mapWords.get("x")).isEqualTo(3);
        assertThat(mapWords.get("z")).isEqualTo(2);
        assertThat(mapWords.get("y")).isEqualTo(1);
    }
}
