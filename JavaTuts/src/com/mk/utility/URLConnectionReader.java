package com.mk.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class URLConnectionReader {
	public static void main(String[] args) throws Exception {
		URL yahoo = new URL("http://localhost:9200/");
		URLConnection yc = yahoo.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		StringBuilder sb = new StringBuilder();
		
		while ((inputLine = in.readLine()) != null)
		{
			System.out.println(inputLine);
			sb.append(inputLine);
		}
		

		//another way
//		myMap = objectMapper.readValue(sb.toString().getBytes(), new TypeReference<HashMap<String,String>>() {});
		//System.out.println("Map using TypeReference: "+myMap);
		
		in.close();
	}
}