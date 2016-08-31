package com.mk.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileCompare {

	public static void main(String[] args) {

		String alertFileName = "C:\\Users\\kmuruganandham\\Desktop\\CLEAN DESKTOP\\alertcustomers.txt";
		String bizFileName = "C:\\Users\\kmuruganandham\\Desktop\\CLEAN DESKTOP\\businesscustomers.txt";
		String outputFile = "C:\\Users\\kmuruganandham\\Desktop\\CLEAN DESKTOP\\activecustomers.txt";

		try (BufferedReader br1 = new BufferedReader(new FileReader(alertFileName));
				BufferedReader br2 = new BufferedReader(new FileReader(bizFileName));
				PrintWriter pw = new PrintWriter(new FileWriter(outputFile))) {

			String alertCustomer = br1.readLine();
			String bizCustomer = br2.readLine();
			long alertcounter = 0;
			long bizcounter = 0;
			while (alertCustomer != null && bizCustomer != null) {

				System.out.println("ac" + alertcounter +", bc"+ bizcounter);
				long ac = Long.valueOf(alertCustomer);
				long bc = Long.valueOf(bizCustomer);

				if (ac == bc) {
					pw.println(alertCustomer);
					bizCustomer = br2.readLine();
					alertCustomer = br1.readLine();
					++alertcounter;
					++bizcounter;
				} else if (ac > bc) {
					bizCustomer = br2.readLine();
					++bizcounter;
				} else if (ac < bc) {
					alertCustomer = br1.readLine();
					++alertcounter;
				}
			}

			if (bizCustomer == null || alertCustomer == null) {
				System.out.println("ac" + alertcounter +", bc"+ bizcounter);
//				break;
			}
			br1.close();
			br2.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
