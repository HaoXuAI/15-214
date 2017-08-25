package edu.cmu.cs.cs214.hw6.sequential.sentiment;

/**
 * Represents a result of sentiment analysis, possibly aggregated over multiple sentences
 */
public class SentimentResult {

    private int very_positive = 0,
            positive = 0,
            neutral = 0,
            negative = 0,
            very_negative = 0;


    public void addSentimentScore(String value) {
        if ("Negative".equals(value))
            negative++;
        else if ("Positive".equals(value))
            positive++;
        else if ("Neutral".equals(value))
            neutral++;
        else if ("Very positive".equals(value))
            very_positive++;
        else if ("Very negative".equals(value))
            very_negative++;
        else assert false : "unknown: " + value;
    }


    /**
     * returns number of sentences analyzed
     */
    public int total() {
        return very_positive + very_negative + positive + negative + neutral;
    }

    /**
     * returns fraction of sentences that have been classified as very positive
     */
    public double very_positive() {
        return very_positive / (double) total();
    }

    /**
     * returns fraction of sentences that have been classified as positive
     */
    public double positive() {
        return positive / (double) total();
    }

    /**
     * returns fraction of sentences that have been classified as neutral
     */
    public double neutral() {
        return neutral / (double) total();
    }

    /**
     * returns fraction of sentences that have been classified as negative
     */
    public double negative() {
        return negative / (double) total();
    }

    /**
     * returns fraction of sentences that have been classified as very negative
     */
    public double very_negative() {
        return very_negative / (double) total();
    }

    /**
     * returns textual summary of the average and the relative scores
     */
    public String summary() {
        return String.format("%.1f avg of %d messages (%.2f, %.2f, %.2f, %.2f, %.2f)", average(), total(), very_negative(), negative(), neutral(), positive(), very_positive());
    }

    /**
     * computes an average from -2 (very negative) to +2 (very positive) for all analyzed
     * sentences
     */
    public double average() {
        return (-2 * very_negative - negative + positive + 2 * very_positive) / (double) total();
    }

    /**
     * adds the results of another SentimentResult to the statistics
     * collected in this result.
     */
    public void mergeWith(SentimentResult that) {
        this.negative += that.negative;
        this.positive += that.positive;
        this.neutral += that.neutral;
        this.very_negative += that.very_negative;
        this.very_positive += that.very_positive();
    }
}
