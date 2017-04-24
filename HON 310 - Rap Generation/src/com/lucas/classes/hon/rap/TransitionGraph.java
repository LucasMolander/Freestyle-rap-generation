package com.lucas.classes.hon.rap;

import java.util.ArrayList;

public class TransitionGraph {

	private static final int SIZE = PartOfSpeech.values().length;
	
	private int[][] graph = null;
	
	public TransitionGraph()
	{
		graph = new int[SIZE][SIZE];
		
		// Zero out the graph
		for (int r = 0; r < graph.length; r++) {
			for (int c = 0; c < graph[0].length; c++) {
				graph[r][c] = 0;
			}
		}
	}
	
	public void doLine(Line l)
	{
		ArrayList<Word> words = l.getWords();
		
		Word w1, w2;
		int i1, i2;
		for (int i = 0; i < words.size() - 1; i++) {
			w1 = words.get(i);
			w2 = words.get(i + 1);
			
			i1 = getIndex(w1.getPos());
			i2 = getIndex(w2.getPos());
			
			if (i1 == -1 || i2 == -1) {
				continue;
			}
			
			graph[i1][i2]++;
		}
	}
	
	public static int getIndex(PartOfSpeech pos)
	{
		switch (pos)
		{
		case NOUN:
			return 0;
		case PRONOUN:
			return 1;
		case VERB:
			return 2;
		case ADJECTIVE:
			return 3;
		case ADVERB:
			return 4;
		case PREPOSITION:
			return 5;
		case CONJUNCTION:
			return 6;
		default:
			// Just default to nouns
			return 0;
		}
	}
	
	public static PartOfSpeech getPos(int index)
	{
		switch (index)
		{
		case 0:
			return PartOfSpeech.NOUN;
		case 1:
			return PartOfSpeech.PRONOUN;
		case 2:
			return PartOfSpeech.VERB;
		case 3:
			return PartOfSpeech.ADJECTIVE;
		case 4:
			return PartOfSpeech.ADVERB;
		case 5:
			return PartOfSpeech.PREPOSITION;
		case 6:
			return PartOfSpeech.CONJUNCTION;
		default:
			return PartOfSpeech.IDK;
		}
	}
	
	public int[][] getGraph()
	{
		return graph;
	}
	
	public String toString()
	{
		String out = "";
		
		for (int r = 0; r < graph.length; r++) {
			for (int c = 0; c < graph[0].length; c++) {
				out += graph[r][c];
				
				if (c < graph[0].length - 1) {
					out += "\t";
				}
			}
			
			if (r < graph.length - 1) {
				out += "\n";
			}
		}
		
		return out;
	}
	
}
