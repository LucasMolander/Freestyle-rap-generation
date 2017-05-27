package com.lucas.classes.hon.rap;

import java.util.Random;

/**
 * Contains a useful method for TransitionGraphs.
 * 
 * @author Lucas Molander
 */
public class GraphUtils {

	/** "And there was pseudorandomness" */
	private static Random rand = new Random(System.currentTimeMillis());
	
	/**
	 * Given a column in the graph, select a random row
	 * following the distribution of weights in the column.
	 * 
	 * @param tGraph TransitionGraph to select a row from
	 * @param col column in tGraph to select a row from
	 * @return the randomly selected row
	 */
	public static int randomRowInColumn(TransitionGraph tGraph, int col)
	{
		int[][] graph = tGraph.getCopy();
		
		int colTotal = 0;
		for (int r = 0; r < graph.length; r++) {
			colTotal += graph[r][col];
		}
		
		// double place = Math.random() * colTotal;
		double place = rand.nextDouble() * colTotal;
		
		for (int r = 0; r < graph.length; r++) {
			place -= graph[r][col];
			
			if (place <= 0) {
				return r;
			}
		}
		
		// Mathematically, it should never get here
		return -1;
	}
	
}
