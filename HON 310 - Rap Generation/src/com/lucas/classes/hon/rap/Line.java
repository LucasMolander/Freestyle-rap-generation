package com.lucas.classes.hon.rap;

import java.util.ArrayList;

/**
 * Represents a line in a song.
 * 
 * @author Lucas Molander
 */
public class Line {

	/** Words in this line */
	private ArrayList<Word> words = null;
	
	public Line()
	{
		words = new ArrayList<Word>();
	}
	
	public void addWord(Word w)
	{
		words.add(w);
	}
	
	public ArrayList<Word> getWords()
	{
		return words;
	}
	
	/**
	 * Gets the words in this Line separated by spaces.
	 * 
	 * @return the words in this Line separated by spaces.
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < words.size(); i++) {
			sb.append(words.get(i));
			
			if (i < words.size() - 1) {
				sb.append(" ");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * For debugging.
	 * 
	 * @return same as toString(), but more info
	 */
	public String toStringVerbose()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < words.size(); i++) {
			sb.append(words.get(i).toStringVerbose());
			
			if (i < words.size() - 1) {
				sb.append(" ");
			}
		}
		
		return sb.toString();
	}
}
