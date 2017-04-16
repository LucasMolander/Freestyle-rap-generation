package com.lucas.classes.hon.rap;

import java.util.ArrayList;

public class Song {

	private ArrayList<Stanza> stanzas = null;
	
	/** All words used in this song */
	private ArrayList<Word> words = null;
	
	public Song()
	{
		stanzas = new ArrayList<Stanza>();
		words = new ArrayList<Word>();
	}
	
	public void addStanza(Stanza s)
	{
		stanzas.add(s);
	}
	
	/**
	 * If the list of words doesn't already contain w, adds w.
	 * 
	 * @param w Word to add
	 */
	public void addUniqueWord(Word w)
	{
		if (!words.contains(w)) {
			words.add(w);
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
