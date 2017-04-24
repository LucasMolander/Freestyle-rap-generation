package com.lucas.classes.hon.rap;

import java.util.ArrayList;

public class Line {

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
