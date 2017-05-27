package com.lucas.classes.hon.rap;

import java.util.ArrayList;

/**
 * Represents a stanza in a (rap) song.
 * 
 * @author Lucas Molander
 */
public class Stanza {

	private ArrayList<Line> lines = null;
	
	public Stanza()
	{
		lines = new ArrayList<Line>();
	}
	
	public void addLine(Line l)
	{
		lines.add(l);
	}
	
	public ArrayList<Line> getLines()
	{
		return lines;
	}
	
	/**
	 * Gets each line separated by a line feed.
	 * 
	 * @return each line separated by a line feed
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < lines.size(); i++) {
			sb.append(lines.get(i).toString());
			
			if (i < lines.size() - 1) {
				sb.append("\n");
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
		
		for (int i = 0; i < lines.size(); i++) {
			sb.append(lines.get(i).toStringVerbose());
			
			if (i < lines.size() - 1) {
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
}
