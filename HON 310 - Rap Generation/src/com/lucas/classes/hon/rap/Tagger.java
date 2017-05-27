package com.lucas.classes.hon.rap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * Wraps the Stanford POS tagging library.
 * 
 * @author Lucas Molander
 */
public class Tagger {

	/** Path to the tagger file (as per the library's request) */
	private static final String MODEL_FILE = "models/english-bidirectional-distsim.tagger";
	
	/** Library object */
	private static MaxentTagger libTagger = null;
	
	
	public static void init()
	{
		libTagger = new MaxentTagger(MODEL_FILE);
	}
	
	/**
	 * Tags a file and returns a Song that represents the tagged file.
	 * 
	 * @param inFile input file
	 * @return Song that inFile represents
	 * @throws FileNotFoundException
	 */
	public static Song tagFile(String inFile) throws FileNotFoundException
	{
		Song song = new Song();
		Stanza stanza = new Stanza();
		Line line = new Line();
		Word word = null;
		
		List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader(inFile)));
		
		// For each sentence (line) in the lyric file
		for (List<HasWord> sentence : sentences) {
			List<TaggedWord> tSentence = libTagger.tagSentence(sentence);
			
			// For each word in the tagged sentence (line)
			for (int i = 0; i < tSentence.size(); i++) {
				TaggedWord tw = tSentence.get(i);
				word = new Word(tw.toString().split("/")[0], tw.toString().split("/")[1]);

				if (!word.getValue().equals(".")) { // Regular word
					song.addWord(word);
					line.addWord(word);
				} else {						    // End of a line
					stanza.addLine(line);
					line = new Line();
					
					// Have another period left
					if (i < tSentence.size() - 1) {
						song.addStanza(stanza);
						stanza = new Stanza();
					}
				}
			}
		}
		
		return song;
	}
	
}
