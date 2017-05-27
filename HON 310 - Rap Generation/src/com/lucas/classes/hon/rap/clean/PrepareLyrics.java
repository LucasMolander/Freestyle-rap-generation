package com.lucas.classes.hon.rap.clean;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Contains a useful method for excluding unwanted lyric information.
 * 
 * @author Lucas Molander
 */
public class PrepareLyrics
{
	/**
	 * Removes non-alphabetical characters from a file (but it keeps spaces).
	 * 
	 * @param inPath input file path
	 * @param outPath output file path
	 * @throws IOException
	 */
	public static void removeNonAlpha(String inPath, String outPath) throws IOException
	{
		// Get data from input file
		byte[] data = Files.readAllBytes(Paths.get(inPath));
		
		ArrayList<Character> newData = new ArrayList<Character>();
		
		// Only take alphabetical characters and spaces
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
			
			if (isAlpha(b) || b == ' ') {
				newData.add((char) b);
			}
		}
		
		// One last dot! Otherwise it skips over the last part of the song
		newData.add('.');
		
		// Can't use ArrayList.toArray() because FileWriter.write()
		// wants a char (primitive) array
		char[] chars = new char[newData.size()];
		for (int i = 0; i < newData.size(); i++) {
			chars[i] = newData.get(i);
		}
		
		// Write to output file
		FileWriter fw = new FileWriter(outPath);
		fw.write(chars, 0, newData.size());
		fw.close();
	}
	
	/**
	 * Determine whether a character is alphabetical.
	 * 
	 * @param b input character
	 * @return whether b is alphabetical
	 */
	private static boolean isAlpha(byte b)
	{
		return (b >= 'a' && b <= 'z') || 
			   (b >= 'A' && b <= 'Z');
	}
}
