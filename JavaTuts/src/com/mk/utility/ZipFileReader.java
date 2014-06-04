package com.mk.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * * Java program to iterate and read file entries from Zip archive. * This
 * program demonstrate two ways to retrieve files from Zip using ZipFile and by
 * using ZipInputStream class. * @author Javin
 */

public class ZipFileReader
{

	// This Zip file contains 11 PNG images
	private static final String FILE_NAME = "C:\\temp\\temp.zip";
	private static final String OUTPUT_DIR = "C:\\temp\\Images\\";
	private static final int BUFFER_SIZE = 8192;

	public static void main(String[] args) throws IOException
	{
		readUsingZipFile();
//		readUsingZipInputStream();
	}

	private static void readUsingZipFile() throws IOException
	{
		final ZipFile file = new ZipFile(FILE_NAME);
		System.out.println("Iterating over zip file - " + FILE_NAME);

		try
		{
			final Enumeration<? extends ZipEntry> entries = file.entries();
			while (entries.hasMoreElements())
			{
				final ZipEntry entry = new ZipEntry(entries.nextElement());
				System.out.printf("File %s Size %d Modified on %TD %n", entry.getName(), entry.getSize(), new Date(
						entry.getTime()));
				extractEntry(entry, file.getInputStream(entry));
			}
			System.out.printf("Zip File %s extracted successfully in %s", FILE_NAME, OUTPUT_DIR);
		} finally
		{
			file.close();
		}
	}

	private static void readUsingZipInputStream() throws IOException
	{
		final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(FILE_NAME)));
		final ZipInputStream zis = new ZipInputStream(bis);
		try
		{
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null)
			{
				System.out.printf("File %s Size %d Modified on %TD %n", entry.getName(), entry.getSize(), new Date(
						entry.getTime()));
				extractEntry(entry, bis);
			}
		} finally
		{
			bis.close();
		}
	}

	private static void extractEntry(ZipEntry entry, InputStream is) throws IOException
	{
		String extractedFile = OUTPUT_DIR + entry.getName();
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream(extractedFile);
			final byte[] buf = new byte[BUFFER_SIZE];
			int length;
			while ((length = is.read(buf, 0, buf.length)) >= 0)
			{
				fos.write(buf, 0, length);
			}
		} catch (IOException e)
		{
			fos.close();
		}
	}
}
