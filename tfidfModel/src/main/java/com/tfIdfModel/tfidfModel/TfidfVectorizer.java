package com.tfIdfModel.tfidfModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import weka.core.*;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TfidfVectorizer {



    @Autowired
    private MongoTemplate mongoTemplate;

//    private String tokenizerModelPath;
//    private List<String> documents;
//
//    public void TFIDFVectorizer(String tokenizerModelPath, List<String> documents) {
//        this.tokenizerModelPath = tokenizerModelPath;
//        this.documents = documents;
//    }
//
//    public Map<String, Double> calculateTFIDF() throws IOException {
//        // Load tokenizer model
//        TokenizerModel tokenizerModel = new TokenizerModel(new FileInputStream(tokenizerModelPath));
//        TokenizerME tokenizer = new TokenizerME(tokenizerModel);
//
//        // Tokenize documents
//        List<List<String>> tokenizedDocuments = new ArrayList<>();
//        for (String document : documents) {
//            String[] tokens = tokenizer.tokenize(document);
//            tokenizedDocuments.add(Arrays.asList(tokens));
//        }
//
//        // Calculate TF-IDF vectors (using a separate IDF implementation)
//        Map<String, Double> tfidfVector = calculateIDF(tokenizedDocuments);
//
//        return tfidfVector;
//    }
//
//    // Calculate IDF using external implementation (example)
//    private Map<String, Double> calculateIDF(List<List<String>> tokenizedDocuments) {
//        // Implement your IDF calculation logic here
//        // This example calculates IDF based on document frequency
//
//        Map<String, Double> idfMap = new HashMap<>();
//        int numDocuments = tokenizedDocuments.size();
//
//        // Count document frequency for each term
//        for (List<String> document : tokenizedDocuments) {
//            Set<String> uniqueTerms = new HashSet<>(document);
//            for (String term : uniqueTerms) {
//                idfMap.putIfAbsent(term, 0.0);
//                idfMap.put(term, idfMap.get(term) + 1.0);
//            }
//        }
//
//        // Calculate IDF from document frequency
//        for (Map.Entry<String, Double> entry : idfMap.entrySet()) {
//            String term = entry.getKey();
//            Double documentFrequency = entry.getValue();
//            double idf = Math.log(numDocuments / (1.0 + documentFrequency));
//            idfMap.put(term, idf);
//        }
//
//        System.out.println(idfMap);
//        return idfMap;



    public void calculateTfidfVector() throws Exception {

        List<String> texts = List.of(new String[]{
                "Mescoe coe",
                "coep",
                "vit"
        });

//        Query query = new Query();
//        query.fields().include("address");
//        List<String> texts = mongoTemplate.find(query, Student.class).stream().map(Student::getAddress).toList();
        System.out.println(texts);
        // Convert text data to Weka's Instances format
        FastVector attributes = new FastVector(1);
        Attribute textAttribute = new Attribute("address", (FastVector) null);
        attributes.addElement(textAttribute);

        Instances dataRaw = new Instances("TextData", attributes, texts.size());
        dataRaw.setClass(textAttribute);

        for (String text : texts) {
            DenseInstance instance = new DenseInstance(1);
            instance.setValue(textAttribute, text);
            dataRaw.add(instance);
        }

        // Apply StringToWordVector filter
        StringToWordVector filter = new StringToWordVector();
        filter.setInputFormat(dataRaw);
        Instances dataFiltered = Filter.useFilter(dataRaw, filter);

        System.out.println(dataFiltered);
        // Save the ARFF file
        ArffSaver saver = new ArffSaver();
        saver.setInstances(dataFiltered);
        saver.setFile(new File("output.arff"));
        saver.writeBatch();

        System.out.println(saver);

        System.out.println("TF-IDF vectors calculated and saved to output.arff");
    }


}

