package com.lucas.classes.hon.rap;

public class GraphUtils {

	public static int randomRowInColumn(TransitionGraph tGraph, int col)
	{
		int[][] graph = tGraph.getGraph();
		
		int colTotal = 0;
		for (int r = 0; r < graph.length; r++) {
			colTotal += graph[r][col];
		}
		
		double place = Math.random() * colTotal;
		
		for (int r = 0; r < graph.length; r++) {
			place -= graph[r][col];
			
			if (place <= 0) {
				return r;
			}
		}
		
		// Should never get here
		return -1;
	}
	
}
