package com.jiaoyiping.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: 焦一平
 * Date: 2015/3/21
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class TestLuceneIndex {
    private static String indexPath = "d:\\lucene";
    @Test
    public void testIndex() throws IOException {
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        IndexWriter writer = new IndexWriter(directory,new IndexWriterConfig(new StandardAnalyzer()));
        Document document = new Document();
        //Field有多种实现类可选，不同的实现类有不同的索引策略
        document.add(new TextField("name","张三", Field.Store.YES));
        document.add(new IntField("age",23,Field.Store.YES));
        writer.addDocument(document);
        writer.commit();
        writer.close();
    }
    @Test
    public void testSearch() throws IOException, ParseException {
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        QueryParser queryParser = new QueryParser("name",new StandardAnalyzer());
        Query query = queryParser.parse("张三");
        TopDocs tds = searcher.search(query, 10);
        ScoreDoc[] sds = tds.scoreDocs;
        for(ScoreDoc sd: sds){
            Document document = searcher.doc(sd.doc);
            System.out.println(document.get("name"));
            System.out.println(document.get("age"));
        }
    }
}
