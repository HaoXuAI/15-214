package edu.cmu.cs.cs214.hw6.sequential.sentiment;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * represents a text (typically a github commit message) that can be analyzed
 * for its sentiments.
 * <p>
 * the sentiment analysis is performed lazily and the result of it is cached.
 * <p>
 * uses the StanfordCoreNLP library for the actual analysis
 */
public class Message {
    private final String text;
    private SentimentResult result;


    public Message(String text) {
        this.text = text;
    }

    public SentimentResult getSentiment() throws IOException {
        if (result == null)
            result = analyzeSentiment(text);
        return result;
    }


    private static SentimentResult analyzeSentiment(String text) throws IOException {
        //taken largely from SentimentPipeline implementation
        SentimentResult result = new SentimentResult();
        Properties pipelineProps = new Properties();
        pipelineProps.setProperty("annotators", "parse, sentiment");
        pipelineProps.setProperty("enforceRequirements", "false");
        Properties tokenizerProps = new Properties();
        tokenizerProps.setProperty("annotators", "tokenize, ssplit");
        tokenizerProps.setProperty(StanfordCoreNLP.NEWLINE_SPLITTER_PROPERTY, "true");
        StanfordCoreNLP tokenizer = new StanfordCoreNLP(tokenizerProps);
        StanfordCoreNLP pipeline = new StanfordCoreNLP(pipelineProps);
        BufferedReader reader = new BufferedReader(new StringReader(text));

        for (String line; (line = reader.readLine()) != null; ) {
            line = line.trim();
            if (!line.isEmpty()) {
                Annotation annotation = tokenizer.process(line);
                pipeline.annotate(annotation);
                for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                    result.addSentimentScore(sentence.get(SentimentCoreAnnotations.SentimentClass.class));
                }
            }
        }
        return result;
    }


}
