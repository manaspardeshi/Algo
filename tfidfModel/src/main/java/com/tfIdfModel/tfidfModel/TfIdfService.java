package com.tfIdfModel.tfidfModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TfIdfService {
    @Autowired
    private MongoTemplate mongoTemplate;
    public double[][] createTFIDFVectors() {

        List<String> list = List.of(new String[]{
                "My Name is Manas",
                "Mescoe college",
                "Hhi heio3ownd"
        });
//        Query query = new Query();
//        query.fields().include(texts.toArray(new String[0]));
//        List<String> list = mongoTemplate.find(query,String.class,"Student");

        // Step 1: Calculate term frequencies
        Map<String, int[]> termFrequencies = new HashMap<>();
        int documentCount = 0;

        for (String text : list) {
            documentCount++;
            Set<String> uniqueTerms = new HashSet<>(Arrays.asList(text.split(" ")));

            for (String term : uniqueTerms) {
                termFrequencies.computeIfAbsent(term, k -> new int[1])[0]++;
            }
        }

        // Step 2: Calculate inverse document frequencies
        Map<String, Double> inverseDocumentFrequencies = new HashMap<>();
        double logTotalDocuments = Math.log(documentCount);

        for (String term : termFrequencies.keySet()) {
            int documentFrequency = termFrequencies.get(term)[0];
            double inverseDocumentFrequency = logTotalDocuments - Math.log(1 + documentFrequency);
            inverseDocumentFrequencies.put(term, inverseDocumentFrequency);
        }

        // Step 3: Calculate TF-IDF vectors
        double[][] tfidfVectors = new double[list.size()][termFrequencies.size()];

        for (int i = 0; i < list.size(); i++) {
            String[] terms = list.get(i).split(" ");

            for (int j = 0; j < terms.length; j++) {
                String term = terms[j];
                double termFrequency = (double) termFrequencies.get(term)[0];
                double inverseDocumentFrequency = inverseDocumentFrequencies.get(term);
                tfidfVectors[i][j] = termFrequency * inverseDocumentFrequency;
            }
        }

        return tfidfVectors;
    }


}
