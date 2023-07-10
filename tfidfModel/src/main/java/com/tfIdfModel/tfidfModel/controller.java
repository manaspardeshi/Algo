package com.tfIdfModel.tfidfModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weka.core.Instance;
import weka.core.Instances;

@RestController
public class controller {

    @Autowired
    private TfidfVectorizer tfidfVectorizer;

    @Autowired
    private TfIdfService tfIdfService;

    @Autowired
    private WekaTfidf tfidf;

//    @GetMapping("/tf")
//    public double[][] createVectors() throws Exception {
//      return tfIdfService.createTFIDFVectors();
//
//    }

//    @GetMapping("/tfidf")
//    public void createVectors() throws Exception {
//        tfidfVectorizer.calculateTfidfVector();
//    }

    @GetMapping("/tfidfWeka")
    public Instances createVectors() throws Exception {
       return tfidf.createVectors();
    }
}
