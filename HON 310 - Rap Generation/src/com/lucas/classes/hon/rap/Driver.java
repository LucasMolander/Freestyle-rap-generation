package com.lucas.classes.hon.rap;

import java.io.IOException;
import java.util.ArrayList;

import com.lucas.classes.hon.rap.clean.PrepareLyrics;

/**
 * High level controller for the program.
 * Houses the main method.
 * 
 * Also contains functions to clean up
 * lyrics for the part of speech (POS) tagger.
 * 
 * The overall approach is to assign the POS
 * and number of syllables to each word,
 * and keep track of each POS transition.
 * 
 * Based on the POS transition graph,
 * generate logical POS transitions while rhyming.
 * 
 * @author Lucas Molander
 */
public class Driver
{
	/** Lyric files */
	private static final String[] lyricFiles = {
		"99_problems.txt",					// Jay Z
		"Brothers_in_paris.txt",			// Jay Z and Kanye West
		"Dirt_off_your_shoulder.txt",		// Jay Z
		"Gangsta_rap_made_me_do_it.txt",	// Ice Cube
		"Where_the_hood_at.txt",			// DMX
		"X_gon_give_it_to_ya.txt"			// DMX
	};
	
	public static void main(String[] args) throws IOException
	{
		Rhymer.init();
		Tagger.init();
		
		// Tag each lyric file
		Song[] songs = new Song[lyricFiles.length];
		for (int i = 0; i < lyricFiles.length; i++) {
			songs[i] = Tagger.tagFile("input/" + lyricFiles[i]);
		}
		
		ArrayList<Word> rhymes;
		ArrayList<Word> uniqueWords = Song.compileWords(songs);
		TransitionGraph graph = Song.compileTransitionGraphs(songs);
		
		SentenceMaker mak = new SentenceMaker(uniqueWords);
		
		for (int i = 0; i < uniqueWords.size(); i++) {
			rhymes = Rhymer.rhyme(uniqueWords.get(i), uniqueWords);
			
			if (rhymes.size() > 0) {
				System.out.println(mak.makeLine(uniqueWords.get(i), 6, graph));
				System.out.println(mak.makeLine(rhymes.get(0), 6, graph));
				System.out.println();
			}
		}
	}
	
	/**
	 * Calls PrepareLyrics.removeNonAlpha() on each lyric file.
	 * 
	 * @param inFolder input folder
	 * @param outFolder output folder
	 * @throws IOException
	 */
	public static void prepareLyrics(String inFolder, String outFolder) throws IOException
	{
		String[] filesToClean = new String[lyricFiles.length];
		for (int i = 0; i < filesToClean.length; i++) {
			filesToClean[i] = inFolder + "/" + lyricFiles[i];
		}
		
		for (String toClean : filesToClean) {
			String outPath = outFolder + "/" + toClean.split("/")[1];
			PrepareLyrics.removeNonAlpha(toClean, outPath);
		}
	}
}
