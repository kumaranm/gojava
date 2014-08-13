package com.mk.utility;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.AutomatonQuery;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.util.automaton.Automaton;
import org.apache.lucene.util.automaton.State;

public class HelloLucene1 {
  public static void main(String[] args) throws IOException, ParseException {
    // 0. Specify the analyzer for tokenizing text.
    //    The same analyzer should be used for indexing and searching
    StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

    // 1. create the index
    Directory index = new RAMDirectory();

    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);

    IndexWriter w = new IndexWriter(index, config);
//    addDoc(w, "lucene in action", "193398817");
//    addDoc(w, "lucene for test dummies", "55320055Z");
//    addDoc(w, "managing gigabytes", "55063554A");
//    addDoc(w, "the art of computer science", "9900333X");
    addDoc(w, "МОРОЗОВ Ищенко ВИКТОРОВИЧ", "9900333X");
    addDoc(w, "МОРОЗОВ АЛЕКСАНДР ВИКТОРОВИЧ", "9900333X");
    addDoc(w, "морозов,александр викторович", "990023X");
    addDoc(w, "МОРОЗОВ,ВИКТОРОВИЧ АЛЕКСАНДР", "990023X");
    addDoc(w, "ВИКТОРОВИЧ МОРОЗОВ,АЛЕКСАНДР", "990023X");
    addDoc(w, "МОРОЗОВ,АЛЕКСАНДР", "990023X");
    addDoc(w, "ВИКТОРОВИЧ,АЛЕКСАНДР", "990023X");
    addDoc(w, "АНДРЕЕВ РОМАН ВЛАДИМИРОВИЧ", "992333X");
    addDoc(w, "АНДРЕЕВ РОМАН ВИКТОРОВИЧ", "992333X");
    addDoc(w, "fname mname lname", "992333X");
    addDoc(w, "fname lname mname", "992333X");
    addDoc(w, "mname fname lname", "992333X");
    addDoc(w, "mname lname fname", "992333X");
    addDoc(w, "lname mname fname", "992333X");
    addDoc(w, "lname fname mname", "992333X");
    addDoc(w, "lname fname", "992333X");
    addDoc(w, "fname lname", "992333X");
    addDoc(w, "fnawe lname abc", "992333X");
    addDoc(w, "sname dname", "992333X");
    addDoc(w, "fname lname jname", "992333X");
    addDoc(w, "fnamemname hfg", "992333X");
    addDoc(w, "ALOARDI Carlo Giovanni", "322");
    addDoc(w, "ALOARDI Carlo", "2233");
    addDoc(w, "ALOARDI Giovanni Carlo", "2233");
    addDoc(w, "ALOARDI,Carlo,Giovanni", "2233");
    addDoc(w, "Carlo ALOARDI Giovanni", "2233");
    addDoc(w, "Carlo Giovanni ALOARDI", "2233");
    addDoc(w, "Giovanni ALOARDI Carlo", "2233");
    addDoc(w, "Giovanni Carlo ALOARDI", "2233");
    addDoc(w, "Giovanny Carlo ALOARDy", "2233");
    addDoc(w, "Giovanny Carlo ALOARDi", "2233");
    addDoc(w, "Giovanny Giovanni", "2233");
    addDoc(w, "Carlo austin Giovanni", "2233");
    addDoc(w, "Giovanni Carlo ALOARDI KIM", "2233");
    addDoc(w, "Giovanni Carlo ALOARds KIM", "2233");
//    addDoc(w, "the great game of fname which comes lname after mname", "992333X");
    w.close();

    // 2. query
    String querystr = args.length > 0 ? args[0] : "lucene action";

    // the "title" arg specifies the default field to use
    // when no field is explicitly specified in the query.
    Query q = new QueryParser(Version.LUCENE_43, "title", analyzer).parse(querystr);

    MultiPhraseQuery mpq = new  MultiPhraseQuery();
    Term t1 = new Term("title","fname");
    Term t2 = new Term("title","mname");
    Term t3 = new Term("title","lname");
    mpq.add(new Term[] { t1, t2, t3}, 0);
    mpq.add(new Term[] { t1, t2, t3}, 1);
    mpq.add(new Term[] { t1, t2, t3}, 2);
//    mpq.add(new Term[] { t2}, 1);
//    mpq.add(new Term[] { t3}, 2);
//    mpq.setSlop(4); 
    
    MultiPhraseQuery mpq1 = new  MultiPhraseQuery();
    Term t4 = new Term("title","fname");
    Term t5 = new Term("title","lname");
    mpq1.add(new Term[] {t4, t5}, 0);
    mpq1.add(new Term[] {t5, t4}, 1);
//    mpq1.setSlop(2); 
    
//    FuzzyQuery fq1 = new FuzzyQuery(new Term("title","fndme"), 1, 0, 1, false);
//    FuzzyQuery fq2 = new FuzzyQuery(new Term("title","mndme"), 1, 0, 1, false);
//    FuzzyQuery fq3 = new FuzzyQuery(new Term("title","lnaae"), 1, 0, 1, false);
    FuzzyQuery fq1 = new FuzzyQuery(new Term("title","aloardi"), 1, 0, 1, false);
    FuzzyQuery fq2 = new FuzzyQuery(new Term("title","carlo"), 1, 0, 1, false);
    FuzzyQuery fq3 = new FuzzyQuery(new Term("title","giovanni"), 1, 0, 1, false);


    BooleanQuery qry = new BooleanQuery();
//    qry.add(mpq, Occur.SHOULD);
//    qry.add(mpq1, Occur.SHOULD);
    qry.add(fq1, Occur.SHOULD);
    qry.add(fq2, Occur.SHOULD);
    qry.add(fq3, Occur.SHOULD);
    
    // 3. search
    int hitsPerPage = 1000;
    IndexReader reader = DirectoryReader.open(index);
    IndexSearcher searcher = new IndexSearcher(reader);
    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
    searcher.search(qry, collector);
    ScoreDoc[] hits = collector.topDocs().scoreDocs;
    
    // 4. display results
    System.out.println("Found " + hits.length + " hits.");
    for(int i=0;i<hits.length;++i) {
      int docId = hits[i].doc;
      Document d = searcher.doc(docId);
      System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title") + "\t" + hits[i].score);
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