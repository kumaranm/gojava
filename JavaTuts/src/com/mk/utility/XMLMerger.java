package com.mk.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLMerger {

	public static void main(String[] args) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document doc = null;
		Document doc2 = null;

		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse(new File("C:\\Kumaran\\Personal Projects\\xmlmerge\\base.xml"));
			doc2 = db.parse(new File("C:\\Kumaran\\Personal Projects\\xmlmerge\\new.xml"));
			NodeList ndListFirstFile = doc.getElementsByTagName("staff");

			Node nodeArea = doc.importNode(doc2.getElementsByTagName("area").item(0), true);
			Node nodeCity = doc.importNode(doc2.getElementsByTagName("city").item(0), true);
			ndListFirstFile.item(0).appendChild(nodeArea);
			ndListFirstFile.item(0).appendChild(nodeCity);

			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new StringWriter());
			transformer.transform(source, result);

			Writer output = new BufferedWriter(new FileWriter("C:\\Kumaran\\Personal Projects\\xmlmerge\\merged.xml"));
			String xmlOutput = result.getWriter().toString();
			output.write(xmlOutput);
			output.close();

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		} catch (IOException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		} catch (TransformerException ef) {
			// TODO Auto-generated catch block
			ef.printStackTrace();
		}
	}

}