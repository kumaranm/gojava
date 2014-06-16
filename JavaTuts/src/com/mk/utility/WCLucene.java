package com.mk.utility;
import java.io.IOException;

import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class WCLucene {
  public static void main(String[] args) throws IOException, ParseException {
    // 0. Specify the analyzer for tokenizing text.
    //    The same analyzer should be used for indexing and searching
//    StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
    RussianAnalyzer analyzer = new RussianAnalyzer(Version.LUCENE_43);
    // 1. create the index
    Directory index = new RAMDirectory();

    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);

    IndexWriter w = new IndexWriter(index, config);
    addDoc(w, "ОСМАЕВ АДАМ ЖАМАЛАЙЛОВИЧ ", "193398817");
    addDoc(w, "АДАМ,ЖАМАЛАЙЛОВИЧ ОСМАЕВ", "55320055Z");
    addDoc(w, "АДАМ,ОСМАЕВ,ЖАМАЛАЙЛОВИЧ", "55063554A");
    addDoc(w, "ЖАМАЛАЙЛОВИЧ АДАМ,ОСМАЕВ", "99003333");
    addDoc(w, "ЖАМАЛАЙЛОВИЧ АДАМ", "9900333e");
    addDoc(w, "ЖАМАЛАЙЛОВИЧ ОСМАЕВ", "9900333e");
    addDoc(w, "АДАМ", "9900333s");
    addDoc(w, "ОСМАЕВ", "9900333s");
    addDoc(w, "ЖАМАЛАЙЛОВИЧ", "9900333s");
    w.close(); 

    // 2. query
    String querystr = args.length > 0 ? args[0] : "АДАМ,ОСМАЕВ";

    // the "title" arg specifies the default field to use
    // when no field is explicitly specified in the query.
    Query q = new QueryParser(Version.LUCENE_43, "title", analyzer).parse(querystr);

    // 3. search
    int hitsPerPage = 10;
    IndexReader reader = DirectoryReader.open(index);
    IndexSearcher searcher = new IndexSearcher(reader);
    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
    searcher.search(q, collector);
    ScoreDoc[] hits = collector.topDocs().scoreDocs;
    
    // 4. display results
    System.out.println("Found " + hits.length + " hits.");
    for(int i=0;i<hits.length;++i) {
      int docId = hits[i].doc;
      Document d = searcher.doc(docId);
      System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title") + "\t" +hits[i].score);
    }

    // reader can only be closed when there
    // is no need to access the documents any more.
    reader.close();
  }

  private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
    Document doc = new Document();
    doc.add(new TextField("title", title, Field.Store.YES));

    // use a string field for isbn because we don't want it tokenized
    doc.add(new StringField("isbn", isbn, Field.Store.YES));
    w.addDocument(doc);
  }
}