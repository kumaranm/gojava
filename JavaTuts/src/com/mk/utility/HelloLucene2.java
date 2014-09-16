package com.mk.utility;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
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
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class HelloLucene2 {
	public static void main(String[] args) throws IOException, ParseException {
		// 0. Specify the analyzer for tokenizing text.
		// The same analyzer should be used for indexing and searching
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

		// 1. create the index
		Directory index = new RAMDirectory();

		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);

		IndexWriter w = new IndexWriter(index, config);
		addDoc(w, "fname mname lname", "11");
		addDoc(w, "fname lname mname", "22");
		addDoc(w, "mname fname lname", "33");
		addDoc(w, "mname lname fname", "44");
		addDoc(w, "lname mname fname", "55");
		addDoc(w, "lname fname mname", "au");
		// addDoc(w, "lname" , "fname", "33333333");
		// addDoc(w, "lname" , "fname mname", "us");
		// addDoc(w, "lname" , "fname mname", "us");
		addDoc(w, "fname lname", "jp");
		addDoc(w, "fnawe lname abc", "jp");
		addDoc(w, "sname dname", "99");
		addDoc(w, "dname sname", "99");
		addDoc(w, "fname lname jname", "au");
		addDoc(w, "fname mname hfg", "ru");

		// addDoc(w, "the great game of fname which comes lname after mname",
		// "992333X");
		w.close();

		// 2. query
		// String querystr = args.length > 0 ? args[0] : "lucene action";

		// the "title" arg specifies the default field to use
		// when no field is explicitly specified in the query.
		// Query q = new QueryParser(Version.LUCENE_43, "title",
		// analyzer).parse(querystr);

		BooleanQuery qry = new BooleanQuery();
		qry.add(new TermQuery(new Term("title", "lname")), Occur.SHOULD);
		qry.add(new TermQuery(new Term("title", "fname")), Occur.SHOULD);
		qry.add(new TermQuery(new Term("title", "mname")), Occur.SHOULD);
		qry.setMinimumNumberShouldMatch(2);

		BooleanQuery qry1 = new BooleanQuery();
		List<String> tokenizeString = tokenizeString(analyzer, "fname lname");

		for (String string : tokenizeString) {
			qry1.add(new TermQuery(new Term("title", string)), Occur.MUST);
		}

		BooleanQuery superQry = new BooleanQuery();
		BooleanClause clause = new BooleanClause(qry, Occur.SHOULD);
		BooleanClause clause1 = new BooleanClause(qry1, Occur.SHOULD);
		superQry.add(clause);
		superQry.add(clause1);

		// 3. search
		int hitsPerPage = 1000;
		IndexReader reader = DirectoryReader.open(index);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		searcher.search(superQry, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;

		// 4. display results
		System.out.println("Found " + hits.length + " hits.");
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println((i + 1) + ". " + d.get("country") + "\t" + d.get("title") + "\t" + hits[i].score);
		}

		// reader can only be closed when there
		// is no need to access the documents any more.
		reader.close();
	}

	private static void addDoc(IndexWriter w, String title, String country) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("title", title, Field.Store.YES));
		// doc.add(new StringField("ftitle", title, Field.Store.YES));

		// use a string field for isbn because we don't want it tokenized
		doc.add(new StringField("country", country, Field.Store.YES));
		w.addDocument(doc);
	}

	public static List<String> tokenizeString(Analyzer analyzer, String string) {
		List<String> result = new ArrayList<String>();
		try {
			TokenStream stream = analyzer.tokenStream(null, new StringReader(string));
			stream.reset();
			while (stream.incrementToken()) {
				result.add(stream.getAttribute(CharTermAttribute.class).toString());
			}
			stream.end();
			stream.close();
		} catch (IOException e) {
			// not thrown b/c we're using a string reader...
			throw new RuntimeException(e);
		}

		return result;
	}
}