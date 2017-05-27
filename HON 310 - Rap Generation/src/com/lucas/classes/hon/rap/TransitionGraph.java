package com.lucas.classes.hon.rap;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Adjacency matrix representation of a weighted, directed graph.
 * 
 * @author Lucas Molander
 */
public class TransitionGraph {

	/** Number of vertices in the graph */
	private static final int SIZE = PartOfSpeech.values().length;
	
	/** Weights for each edge */
	private int[][] graph = null;
	
	/**
	 * Initialize a new TransitionGraph with 
	 * all weights equal to 0.
	 */
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
	
	/**
	 * Maps PartOfSpeech values to indices for the graph.
	 * 
	 * @param pos PartOfSpeech to get the index of
	 * @return index for pos
	 */
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
	
	/**
	 * Maps indices in the graph to PartOfSpeech values.
	 * 
	 * @param index index to get the PartOfSpeech for
	 * @return the PartOfSpeech for the given index
	 */
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
	
	/**
	 * Gets a copy of this graph's weights.
	 * 
	 * @return a copy of this graph's weights
	 */
	public int[][] getCopy()
	{
		int[][] copy = new int[SIZE][SIZE];
		
		for (int r = 0; r < copy.length; r++) {
			copy[r] = Arrays.copyOf(graph[r], copy[r].length);
		}
		
		return copy;
	}
	
	/**
	 * For debugging.
	 * 
	 * Prints the 2D grid.
	 */
	public String toString()
	{
		String out = "";
		
		for (int r = 0; r < graph.length - 1; r++) {		// -1 b/c don't want IDK entries
			for (int c = 0; c < graph[0].length - 1; c++) {
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
	
	/**
	 * Combine another graph's weights with graph's weights.
	 * 
	 * @param other graph to add to this graph
	 */
	public void addGraph(TransitionGraph other)
	{
		int[][] otherGraph = other.graph;
		
		for (int r = 0; r < graph.length; r++) {
			for (int c = 0; c < graph[0].length; c++) {
				graph[r][c] += otherGraph[r][c];
			}
		}
	}
}
