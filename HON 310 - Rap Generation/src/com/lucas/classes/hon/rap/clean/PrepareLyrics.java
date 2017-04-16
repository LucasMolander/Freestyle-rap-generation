package com.lucas.classes.hon.rap.clean;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PrepareLyrics
{
	public static void prepareLyrics(String inPath, String outPath) throws IOException
	{
		// Get the data
		byte[] data = Files.readAllBytes(Paths.get(inPath));
		
		ArrayList<Character> newData = new ArrayList<Character>();
		
		byte b;
		for (int i = 0; i < data.length; i++) {
			b = data[i];
			
			if (b == '\r') {
				newData.add('.');
				newData.add('\r');
				newData.add('\n');
				i++;
				continue;
			} else if (b == '\n') {
				newData.add('.');
				newData.add('\n');
				continue;
			}
			
			if (isAlpha(b) || isSpace(b)) {
				newData.add((char) b);
			}
		}
		
		// One last dot!
		newData.add('.');
		
		/*
		char[] newData = new char[data.length];
		int size = 0;
		
		byte b;
		for (int i = 0; i < data.length; i++) {
			b = data[i];
			if (isAlpha(b) || isSpace(b) || isLineControl(b)) {
				newData[size] = (char) b;
				size++;
			}
		}
		*/
		
		char[] chars = new char[newData.size()];
		for (int i = 0; i < newData.size(); i++) {
			chars[i] = newData.get(i);
		}
		
		// Write it out
		FileWriter fw = new FileWriter(outPath);
		fw.write(chars, 0, newData.size());
		fw.close();
	}
	
	private static boolean isAlpha(byte b)
	{
		return (b >= 'a' && b <= 'z') || 
			   (b >= 'A' && b <= 'Z');
	}
	
	private static boolean isSpace(byte b)
	{
		return b == ' ';
	}
	
//	private static boolean isLineControl(byte b)
//	{
//		return b == '\r' || b == '\n';
//	}
}
