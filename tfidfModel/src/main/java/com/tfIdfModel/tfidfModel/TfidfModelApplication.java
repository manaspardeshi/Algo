package com.tfIdfModel.tfidfModel;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class TfidfModelApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TfidfModelApplication.class, args);

//        TfidfLucene tfidfLucene=new TfidfLucene();
//        tfidfLucene.tfidfVectors();


//        List<String> documents = Arrays.asList(
//                "This is document one",
//                "This is document two"
//        );
//
//        // Set up the tokenizer
//        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
//        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());
//
//        // Create a LabelAwareIterator to iterate over the documents
//        LabelAwareIterator iterator = new SimpleLabelAwareIterator(documents);
//
//        // Create a TfidfVectorizer
//        TfidfVectorizer vectorizer = new TfidfVectorizer.Builder()
//                .setTokenizerFactory(tokenizerFactory)
//                .setLabelAwareIterator(iterator)
//                .build();
//
//        // Fit the vectorizer to the documents and transform them into TF-IDF vectors
//        vectorizer.fit();
//        List<List<LabelledDocument>> documentTfIdf = vectorizer.transform(iterator);
//
//        // Print the TF-IDF vectors for each document
//        for (List<LabelledDocument> document : documentTfIdf) {
//            for (LabelledDocument doc : document) {
//                System.out.println("Document: " + doc.getContent() + ", TF-IDF Vector: " + doc.getLabels());
//            }
//        }



//        List<String> documents = new ArrayList<>();
//        documents.add("This is the first document");
//        documents.add("This document is the second document");
//        documents.add("And this is the third one");
//        documents.add("Is this the first document?");
//
//        // Tokenize and calculate term frequency
//        List<Map<String, Integer>> termFrequencies = new ArrayList<>();
//        Map<String, Integer> docFrequencies = new HashMap<>();
//
//        for (String document : documents) {
//            String[] tokens = document.toLowerCase().split("\\s+");
//            Map<String, Integer> tf = new HashMap<>();
//
//            for (String token : tokens) {
//                tf.put(token, tf.getOrDefault(token, 0) + 1);
//            }
//
//            termFrequencies.add(tf);
//
//            for (String token : tf.keySet()) {
//                docFrequencies.put(token, docFrequencies.getOrDefault(token, 0) + 1);
//            }
//        }
//
//        // Calculate TF-IDF vectors
//        List<RealVector> tfidfVectors = new ArrayList<>();
//        int numDocuments = documents.size();
//
//        for (Map<String, Integer> tf : termFrequencies) {
//            RealVector tfidfVector = new ArrayRealVector();
//
//            for (String term : docFrequencies.keySet()) {
//                double tfidf = tf.getOrDefault(term, 0) * Math.log((double) numDocuments / docFrequencies.get(term));
//                tfidfVector.append(tfidf);
//            }
//
//            tfidfVectors.add(tfidfVector);
//        }
//
//        System.out.println(tfidfVectors);
//        // Print TF-IDF vectors
//        for (int i = 0; i < tfidfVectors.size(); i++) {
//            System.out.println("Document " + (i + 1) + ": " + tfidfVectors.get(i));
//        }
    }
}


