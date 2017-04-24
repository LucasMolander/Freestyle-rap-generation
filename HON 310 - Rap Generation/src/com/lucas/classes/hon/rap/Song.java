package com.lucas.classes.hon.rap;

import java.util.ArrayList;

public class Song {

	private ArrayList<Stanza> stanzas = null;
	
	/** All words used in this song */
	private ArrayList<Word> words = null;
	
	/** Graph of transitions for parts of speech */
	private TransitionGraph graph = null;
	
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
	 * If the list of words doesn't already contain w, adds w.
	 * 
	 * @param word Word to add
	 */
	public void addUniqueWord(Word word)
	{
//		Word w;
//		for (int i = 0; i < words.size(); i++) {
//			w = words.get(i);
//			if (w.getValue().)
//		}
		if (!words.contains(word)) {
			words.add(word);
		}
	}
	
	public ArrayList<Word> getUniqueWords()
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
	
	public String toStringVerbose()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < stanzas.size(); i++) {
			sb.append(stanzas.get(i).toStringVerbose());
			
			if (i < stanzas.size() - 1) {
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
}
