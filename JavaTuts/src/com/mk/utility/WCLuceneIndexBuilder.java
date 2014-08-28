package com.mk.utility;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class WCLuceneIndexBuilder {

	static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:norkomdba/norkomdbastg@//lvsvmdb02.qa.paypal.com:2126/QADBA9OL";

	public static void index(IndexWriter w, String listName) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			String sql;
//			 sql =
//			 "SELECT user_id, row_id, first_name, last_name FROM wc_data where rownum < 3";
			sql = "select wc.row_id,wc.user_id,  wc.first_name, wc.last_name "
					+ "from wc_data wc "
					+ "join wc_keyword_data wkd on wkd.id = wc.row_id and wkd.status is null "
					+ "join wc_list_row wl on wl.row_id = wc.row_id and wl.list_definition_id = (select id from list_definition where list_definition_name =?) "
					+ "where TO_TIMESTAMP (wc.entered, 'YYYY/MM/DD') >= TO_TIMESTAMP ('2014/07/21', 'YYYY/MM/DD') or " 
					+ "TO_TIMESTAMP (wc.updated, 'YYYY/MM/DD') >= TO_TIMESTAMP ('2014/07/21', 'YYYY/MM/DD') ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, listName);
			ResultSet rs = ps.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				long uid = rs.getLong("user_id");
				long rid = rs.getLong("row_id");
				String fname = rs.getString("first_name");
				String lname =rs.getString("last_name");
				String name = formName(fname, lname) ;
				
				// String last = rs.getString("last_name");

				// Display values
				/*System.out.print("\nUID: " + uid);
				System.out.print(", RowId: " + rid);
				System.out.print(", Name: " + name);*/
				// System.out.println(", Last: " + last);
				addDoc(w, rid, uid, name);
			}
			// STEP 6: Clean-up environment
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}// end main

	private static String formName(String fname, String lname) {
		String name="";
		if(fname == null || fname.length() == 0)
		{
			name = lname;
		}
		else if(lname == null || lname.length() == 0)
		{
			name = fname;
		}
		else
		{
			name = fname + " " + lname;
		}
		return name.toLowerCase();
	}

	public static void main(String[] args) throws IOException, ParseException {
		// 0. Specify the analyzer for tokenizing text.
		// The same analyzer should be used for indexing and searching
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

		String[] lists = { "PAYPAL OFAC", "SDNLIST" };

		// 1. create the index
		FSDirectory dir = null;
		IndexWriter w = null;
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43, analyzer);

		for (String string : lists) {
			dir = FSDirectory.open(new File("c:/index/" + string.replace(" ", "_").toLowerCase()));
			w = new IndexWriter(dir, config);
			index(w, string);
			System.out.println("Index completed for " + string);
			w.close();
		}
		
		
		
		FSDirectory index = FSDirectory.open(new File("c:/index/" + lists[1].replace(" ",	"_").toLowerCase()));
		
		FuzzyQuery fq1 = new FuzzyQuery(new Term("name_data","varon"), 0, 0, 1, false);
	    FuzzyQuery fq2 = new FuzzyQuery(new Term("name_data","arbabsiar"), 0, 0, 1, false);
	    FuzzyQuery fq3 = new FuzzyQuery(new Term("name_data",""), 0, 0, 1, false);


	    BooleanQuery qry = new BooleanQuery();
	    qry.add(fq1, Occur.SHOULD);
	    qry.add(fq2, Occur.MUST);
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
	      System.out.println((i + 1) + ". " + d.get("rid") + "\t" +  d.get("uid") + "\t" + d.get("name_data") + "\t" + hits[i].score);
	    }

	    // reader can only be closed when there
	    // is no need to access the documents any more.
	    reader.close();
	}

	private static void addDoc(IndexWriter w, long rowId, long userId, String name) throws IOException {
		Document doc = new Document();
		doc.add(new StringField("rid", "" + rowId, Field.Store.YES));
		doc.add(new StringField("uid", "" + userId, Field.Store.YES));

		doc.add(new StringField("name_data", name, Field.Store.YES));
		w.addDocument(doc);
	}
}