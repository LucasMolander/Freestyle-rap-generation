package com.lucas.classes.hon.rap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Uses the CMU pronouncing dictionary to provide rhyming utility.
 * 
 * @author Lucas Molander
 */
public class Rhymer {

	private static final String DICT_PATH = "dictionary/cmudict-07b.txt";
	
	/** Maps words to their sounds */
	private static HashMap<String, String> dict = null;
	
	/**
	 * Read the rhyming dictionary into memory.
	 * 
	 * @throws IOException
	 */
	public static void init() throws IOException
	{
		dict = new HashMap<String, String>();
		
		File f = new File(DICT_PATH);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		String word;
		String info;
		int firstSpaceIndex;
		while ((line = br.readLine()) != null) {
			if (line.startsWith(";", 0)) { // Don't read a comment
				continue;
			}
			
			firstSpaceIndex = line.indexOf(' ');
			word = line.substring(0, firstSpaceIndex);
			info = line.substring(firstSpaceIndex + 2, line.length());
			
			dict.put(word.toLowerCase(), info);
		}
		
		br.close();
	}
	
	/**
	 * Yields a sentences given a word to rhyme with
	 * and a list of potential words to use.
	 * 
	 * @param toRhyme word to rhyme with
	 * @param words potential words to use
	 * @return sentence that rhymes with the given word
	 */
	public static ArrayList<Word> rhyme(Word toRhyme, ArrayList<Word> words)
	{
		String rhymeSounds = dict.get(toRhyme.getValue());
		
		ArrayList<Word> rhymes = new ArrayList<Word>();
		
		// Not found in phonetic dictionary
		if (rhymeSounds == null)
		{
			return rhymes;
		}
		
		String[] sounds = rhymeSounds.split(" ");
		String newRhymeSounds;
		String[] newSounds;
		Word w;
		
		if (sounds.length == 1) {
			for (int i = 0; i < words.size(); i++) {
				w = words.get(i);
				newRhymeSounds = dict.get(w.getValue());
				
				// Not found in dictionary
				if (newRhymeSounds == null) {
					continue;
				}
				
				newSounds = newRhymeSounds.split(" ");
				
				// Same ending sound
				if (sounds[sounds.length - 1].equals(newSounds[newSounds.length - 1])
						&& !w.getValue().equals(toRhyme.getValue())) {
					
					// System.out.println("\tadd(" + w.getValue() + ", " + w.getPos() + ") 1");
					
					rhymes.add(w);
				}
			}
		} else {
			// First pass to get the same 2 endings
			for (int i = 0; i < words.size(); i++) {
				w = words.get(i);
				newRhymeSounds = dict.get(w.getValue());
				
				// Not found in dictionary
				if (newRhymeSounds == null) {
					continue;
				}
				
				newSounds = newRhymeSounds.split(" ");
				
				if (newSounds.length == 1) {
					continue;
				}
				
				// Same ending sound
				if (sounds[sounds.length - 1].equals(newSounds[newSounds.length - 1])
						&& sounds[sounds.length - 2].equals(newSounds[newSounds.length - 2])
						&& !w.getValue().equals(toRhyme.getValue())) {
					// Because we already did an initial pass
					// we have to see if we already added it
					if (!rhymes.contains(w)) {
						rhymes.add(w);
					}
				}
			}
			
			// Second pass to get singular same ending
			for (int i = 0; i < words.size(); i++) {
				w = words.get(i);
				newRhymeSounds = dict.get(w.getValue());
				
				// Not found in dictionary
				if (newRhymeSounds == null) {
					continue;
				}
				
				newSounds = newRhymeSounds.split(" ");
				
				// Same ending sound
				if (sounds[sounds.length - 1].equals(newSounds[newSounds.length - 1])
						&& !w.getValue().equals(toRhyme.getValue())) {
					// Because we already did an initial pass
					// we have to see if we already added it
					if (!rhymes.contains(w)) {
						rhymes.add(w);
					}
				}
			}
		}
		
		return rhymes;
	}
}
