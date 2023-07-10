package com.tfIdfModel.tfidfModel;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

public class TfidfLucene {

    public void tfidfVectors() throws IOException, ParseException {

        String[] documents = {
                "This is the first document.",
                "This document is the second document.",
                "And this is the third one.",
                "Is this the first document?"
        };

        // Create an analyzer (for tokenization and stemming)
        Analyzer analyzer = new SimpleAnalyzer();

        // Create the index
        Directory index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);
        for (int i = 0; i < documents.length; i++) {
            Document doc = new Document();
            doc.add(new TextField("content", documents[i], Field.Store.YES));
            writer.addDocument(doc);
        }
        writer.close();

        DirectoryReader reader = DirectoryReader.open(index);

        // Perform a TF-IDF search
        IndexSearcher searcher = new IndexSearcher(reader);

        // Query string
        String queryString = "document";

        // Parse the query
        QueryParser parser = new QueryParser("content", analyzer);
        Query query = parser.parse(queryString);

        // Perform the search
        TopDocs results = searcher.search(query, 10);
        ScoreDoc[] hits = results.scoreDocs;

        // Print the search results
        for (ScoreDoc hit : hits) {
            Document doc = searcher.doc(hit.doc);
            System.out.println("Score: " + hit.score);
            System.out.println("Content: " + doc.get("content"));
            System.out.println();
        }
        reader.close();
    }
}
