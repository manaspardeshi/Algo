package com.tfIdfModel.tfidfModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import weka.core.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.util.List;

@Service
public class WekaTfidf {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Instances createVectors() throws Exception {

        List<String> list = mongoTemplate.findAll(Student.class,"Student").stream().map(Student::getAddress).toList();

        FastVector attributes = new FastVector(list.size());
        for (String field : list) {
            Attribute attribute = new Attribute(field, (FastVector) null);
            attributes.addElement(attribute);
        }

        Instances dataset = new Instances("list", attributes, 0);

        for (String document : list) {
            DenseInstance instance = new DenseInstance(list.size());
            instance.setDataset(dataset);
            for (int i = 0; i < list.size(); i++) {
                String fieldValue = document;
                instance.setValue(i, fieldValue);
            }
            dataset.add(instance);
        }

        String arffData = dataset.toString();

        StringToWordVector filter = new StringToWordVector();
        filter.setInputFormat(dataset);
        Instances filteredData = Filter.useFilter(dataset, filter);

        String arffDataFiltered = filteredData.toString();
        System.out.println(arffDataFiltered);

        return dataset;
    }


}
