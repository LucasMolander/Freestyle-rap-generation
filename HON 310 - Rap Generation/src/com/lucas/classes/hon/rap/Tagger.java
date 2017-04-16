package com.lucas.classes.hon.rap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * Singleton.
 * 
 * Wraps the Stanford POS tagging library.
 * 
 * @author Lucas Molander
 */
public class Tagger {

	private static final String MODEL_FILE = "models/english-bidirectional-distsim.tagger";
	
	private static MaxentTagger libTagger = null;
	
	public static void init()
	{
		libTagger = new MaxentTagger(MODEL_FILE);
	}
	
	public static Song tagFile(String inFile) throws FileNotFoundException
	{
		Song song = new Song();
		Stanza stanza = new Stanza();
		Line line = new Line();
		Word word = null;
		
		List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader(inFile)));
		
		for (List<HasWord> sentence : sentences) {
			List<TaggedWord> tSentence = libTagger.tagSentence(sentence);
			// System.out.println(" ----- " + SentenceUtils.listToString(tSentence, false));

			// for (TaggedWord tw : tSentence) {
			for (int i = 0; i < tSentence.size(); i++) {
				TaggedWord tw = tSentence.get(i);
				word = new Word(tw.toString().split("/")[0], tw.toString().split("/")[1]);

				if (!word.getValue().equals(".")) { // Regular word
					song.addUniqueWord(word);
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
	
	// Back from when this would have been singleton
//	public static Tagger getInstance()
//	{
//		if (theTagger == null) {
//			theTagger = new Tagger();
//		}
//		
//		return theTagger;
//	}
//	
//	private Tagger()
//	{
//		libTagger = new MaxentTagger(MODEL_FILE);
//	}
	
}
