package com.mk.solutions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Java7New {

	public static void main(String[] args) {

		// -----------------
		// Before
		Map<String, List<String>> tlist = new HashMap<String, List<String>>();

		// After - Diamond Operator
		Map<String, List<String>> tlist1 = new HashMap<>();

		// --------------------
		// Before - no String in switch statement

		// After
		String s = "TEST";
		switch (s) {
		case "TEST":
			System.out.println("Java 7 supports String in switch");
			break;
		case "TEST1":
			System.out.println("Java 7 supports String in switch");
			break;
		default:
			System.out.println("Invalid");
		}
		// ----------------
		// Before - explicit closing of IO resources
		FileInputStream fin = null;
		BufferedReader br = null;
		try {
			fin = new FileInputStream("test.txt");
			br = new BufferedReader(new InputStreamReader(fin));
		} catch (FileNotFoundException ex) {
			System.out.println("test.txt is not found");
		} catch (IOException ioe) {
			System.out.println("Failed to close files");
		} finally {
			try {
				if (fin != null)
					fin.close();
				if (br != null)
					br.close();
			} catch (IOException ie) {
				System.out.println("Failed to close files");
			}
		}

		// After
		try (FileInputStream fin1 = new FileInputStream("test.txt");
				BufferedReader br1 = new BufferedReader(new InputStreamReader(
						fin1));) {
			if (br.ready()) {
				String line1 = br.readLine();
				System.out.println(line1);
			}

		} catch (FileNotFoundException ex) {
			System.out.println("test.txt is not found");
		} catch (IOException ioe) {
			System.out.println("Failed to close files");
		}

		// ----------------
		// New Fork Join Framework

		// ------------------
		// Before - No underscore in numbers
		long i = 1000000;
		System.out.println(i);

		// After - underscore in numeric literals
		long j = 1_000_000;
		System.out.println(j);
		// -------------------

		// Before
		try {
			throw new ClassNotFoundException();
		} catch (ClassNotFoundException cfe) {

		} catch (Exception e) {
		}

		// After - multiple exceptions in same catch - cannot be subclasses
		try {
			FileInputStream fin1 = new FileInputStream("test.txt");
			throw new ClassNotFoundException();
		} catch (ClassNotFoundException | IOException e) {

		}
		// --------------
		// Before - only octal or hexa values
		int mask = 0x0101001;

		// After - prefix 0b for byte,short,int,long
		int mask1 = 0b0101001;

		// --------------
		// java.nio.file package provide comprehensive support for file
		// I/O and for accessing the default file system

		String content = "Hello Java Code Geeks";
		byte[] bytes = content.getBytes();
		Path filepath = Paths.get("");
		try (OutputStream out = Files.newOutputStream(filepath))
		{
			out.write(bytes);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		
		// -----------------
		// New Garbage Collector G1 - Garbage First
		// provides clean up where there is most garbage
		// heap memory is split into multiple regions as opposed to 3

		// ------------------
		// Before - obscure();
		
		// After - precise re thrown exceptions - precise()
		
	}

	// Before
	public void obscure() throws Exception {
		try {
			new FileInputStream("abc.txt").read();
			new SimpleDateFormat("ddMMyyyy").parse("12-03-2014");
		} catch (Exception ex) {
			System.out.println("Caught exception: " + ex.getMessage());
			throw ex;
		}
	}

	// After
	public void precise() throws ParseException, IOException {
		try {
			new FileInputStream("abc.txt").read();
			new SimpleDateFormat("ddMMyyyy").parse("12-03-2014");
		} catch (Exception ex) {
			System.out.println("Caught exception: " + ex.getMessage());
			throw ex;
		}
	}
}
