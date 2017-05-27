package com.lucas.classes.hon.rap;

import java.util.ArrayList;

/**
 * Represents an entire (rap) song.
 * Contains the words and POS transitions used.
 * Words are organized by stanzas. 
 * 
 * @author Lucas Molander
 */
public class Song {

	/** The stanzas that make up this Song */
	private ArrayList<Stanza> stanzas = null;
	
	/** All words used in this song */
	private ArrayList<Word> words = null;
	
	/** Graph of transitions for parts of speech */
	private TransitionGraph graph = null;
	
	/** To filter out bad words */
	private static final String[] BAD_WORDS = new String[] {
		"ass",
		"ball",
		"bastard",
		"bitch",
		"boob",
		"clit",
		"cock",
		"cunt",
		"damn",
		"dick",
		"fuck",
		"hoe",
		"homo",
		"nigg",
		"pussy",
		"queer",
		"retard",
		"shit",
		"whore"
	};
	
	public Song()
	{
		stanzas = new ArrayList<Stanza>();
		words = new ArrayList<Word>();
		graph = new TransitionGraph();
	}
	
	/**
	 * Adds to the list of stanzas.
	 * Also, updates the POS transition graph.
	 * 
	 * @param s Stanza to add
	 */
	public void addStanza(Stanza s)
	{
		stanzas.add(s);
		
		ArrayList<Line> lines = s.getLines();
		for (Line l : lines) {
			graph.doLine(l);
		}
	}
	
	public TransitionGraph getTransitionGraph()
	{
		return graph;
	}
	
	/**
	 * UNIQUELY adds words.
	 * 
	 * Also, prevents bad words from being entered.
	 * 
	 * @param word Word to add
	 */
	public void addWord(Word word)
	{
		if (!words.contains(word)) {
			
			// If any bad word is in the word, reject it.
			for (String bw : BAD_WORDS) {
				if (word.getValue().contains(bw)) {
					return;
				}
			}
			
			words.add(word);
		}
	}
	
	public ArrayList<Word> getWords()
	{
		return words;
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < stanzas.size(); i++) {
			sb.append(stanzas.get(i).toString());
			
			if (i < stanzas.size() - 1) {
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Class method that aggregates words together.
	 * 
	 * @param songs
	 * @return words from an array of songs
	 */
	public static ArrayList<Word> compileWords(Song[] songs)
	{
		ArrayList<Word> allWords = new ArrayList<Word>();
		ArrayList<Word> tempWords = new ArrayList<Word>();
		
		for (int i = 0; i < songs.length; i++) {
			tempWords = songs[i].getWords();
			
			for (Word tw : tempWords) {
				if (!allWords.contains(tw)) {
					allWords.add(tw);
				}
			}
		}
		
		return allWords;
	}
	
	/**
	 * Class method that adds transition graphs together.
	 * 
	 * @param songs
	 * @return aggregate graph
	 */
	public static TransitionGraph compileTransitionGraphs(Song[] songs)
	{
		TransitionGraph allGraph = new TransitionGraph();
		
		for (Song s : songs) {
			allGraph.addGraph(s.getTransitionGraph());
		}
		
		return allGraph;
	}
}
