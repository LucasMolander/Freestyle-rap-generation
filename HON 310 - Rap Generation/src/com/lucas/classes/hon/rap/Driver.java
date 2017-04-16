package com.lucas.classes.hon.rap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.lucas.classes.hon.rap.clean.PrepareLyrics;

public class Driver
{
	public static void main(String[] args) throws IOException
	{
		Rhymer.init();
		Tagger.init();
		
		String toTag = "clean_input/99_problems.txt";
		Song s = Tagger.tagFile(toTag);
		
		ArrayList<Word> uniqueWords = s.getUniqueWords();
		ArrayList<Word> rhymes;
		
		for (int i = 0; i < uniqueWords.size(); i++) {
			System.out.print(uniqueWords.get(i) + "\t");
			
			rhymes = Rhymer.rhyme(uniqueWords.get(i), uniqueWords);
			
			System.out.print("[");
			for (int j = 0; j < rhymes.size(); j++) {
				System.out.print(rhymes.get(j));
				
				if (j < rhymes.size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("]");
		}
		
		
		// printAllFiles("clean_input", "clean_output");
		// printAllFilesVerbose("clean_input", "clean_output_verbose");
		
		// prepareLyrics("clean_input", "clean_input_test");
	}
	
	public static void prepareLyrics(String inFolder, String outFolder) throws IOException
	{
		ArrayList<String> filesToClean = new ArrayList<String>();
		filesToClean.add(inFolder + "/99_problems.txt");
		filesToClean.add(inFolder + "/Brothers_in_paris.txt");
		filesToClean.add(inFolder + "/Dirt_off_your_shoulder.txt");
		filesToClean.add(inFolder + "/Gangsta_rap_made_me_do_it.txt");
		filesToClean.add(inFolder + "/Where_the_hood_at.txt");
		filesToClean.add(inFolder + "/X_gon_give_it_to_ya.txt");
		
		for (String toClean : filesToClean) {
			String outPath = outFolder + "/" + toClean.split("/")[1];
			PrepareLyrics.prepareLyrics(toClean, outPath);
		}
	}
	
	public static void printAllFiles(String inFolder, String outFolder) throws IOException
	{
		Tagger.init();
		
		// String fileToTag = "input/in.txt";
		// String fileToTag = "input/99_problems.txt";
		
		ArrayList<String> filesToTag = new ArrayList<String>();
		filesToTag.add(inFolder + "/99_problems.txt");
		filesToTag.add(inFolder + "/Brothers_in_paris.txt");
		filesToTag.add(inFolder + "/Dirt_off_your_shoulder.txt");
		filesToTag.add(inFolder + "/Gangsta_rap_made_me_do_it.txt");
		filesToTag.add(inFolder + "/Where_the_hood_at.txt");
		filesToTag.add(inFolder + "/X_gon_give_it_to_ya.txt");
		
		for (int i = 0; i < filesToTag.size(); i++) {
			String fileToTag = filesToTag.get(i);
			String outFile = outFolder + "/" + fileToTag.split("/")[1];
			
			Song s = Tagger.tagFile(fileToTag);
			
			File f = new File(outFile);
			FileWriter fw = new FileWriter(f);
			fw.write(s.toString());
			fw.close();
		}
	}
	
	public static void printAllFilesVerbose(String inFolder, String outFolder) throws IOException
	{
		Tagger.init();
		
		// String fileToTag = "input/in.txt";
		// String fileToTag = "input/99_problems.txt";
		
		ArrayList<String> filesToTag = new ArrayList<String>();
		filesToTag.add(inFolder + "/99_problems.txt");
		filesToTag.add(inFolder + "/Brothers_in_paris.txt");
		filesToTag.add(inFolder + "/Dirt_off_your_shoulder.txt");
		filesToTag.add(inFolder + "/Gangsta_rap_made_me_do_it.txt");
		filesToTag.add(inFolder + "/Where_the_hood_at.txt");
		filesToTag.add(inFolder + "/X_gon_give_it_to_ya.txt");
		
		for (int i = 0; i < filesToTag.size(); i++) {
			String fileToTag = filesToTag.get(i);
			String outFile = outFolder + "/" + fileToTag.split("/")[1];
			
			Song s = Tagger.tagFile(fileToTag);
			
			File f = new File(outFile);
			FileWriter fw = new FileWriter(f);
			fw.write(s.toStringVerbose());
			fw.close();
		}
	}
}
