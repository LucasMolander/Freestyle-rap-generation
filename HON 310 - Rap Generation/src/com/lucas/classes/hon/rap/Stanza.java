package com.lucas.classes.hon.rap;

import java.util.ArrayList;

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
