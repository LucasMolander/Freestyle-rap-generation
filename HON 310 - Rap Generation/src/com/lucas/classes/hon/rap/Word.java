package com.lucas.classes.hon.rap;

import java.util.HashMap;

import com.lucas.classes.hon.rap.lib.PhraseUtilities;

public class Word
{
	private static final HashMap<String, String> APOSTROPHES = createMap();
    private static HashMap<String, String> createMap()
    {
        HashMap<String,String> m = new HashMap<String,String>();
        m.put("hes", "he's");
        m.put("youre", "you're");
        m.put("aint", "ain't");
        m.put("arent", "aren't");
        m.put("im", "i'm");
        m.put("youd", "you'd");
        m.put("youve", "you've");
        m.put("dont", "don't");
        m.put("wont", "won't");
        m.put("yall", "y'all");
        m.put("couldnt", "couldn't");
        m.put("its", "it's");
        m.put("cant", "can't");
        m.put("ill", "i'll");
        m.put("ive", "i've");
        m.put("weve", "we've");
        m.put("didnt", "didn't");
        m.put("shes", "she's");
        m.put("shed", "she'd");
        m.put("theyre", "they're");
        m.put("lets", "let's");
        m.put("whos", "who's");
        return m;
    }
	
	private String value;
	private String pos;
	private int syllables;
	
	public Word(String value, String pos)
	{
		value = value.toLowerCase();
		if (APOSTROPHES.get(value) != null) {
			value = APOSTROPHES.get(value);
		}
		this.value = value;
		
		this.pos = pos;
		
		syllables = PhraseUtilities.countSyllablesInWord(this.value);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public String getPos()
	{
		return pos;
	}
	
	public int getSyllables()
	{
		return syllables;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Word)) {
			return false;
		}
		
		Word other = (Word) o;
		
		if (!safeEquals(value, other.value)) {
			return false;
		}
		
		if (!safeEquals(pos, other.pos)) {
			return false;
		}
		
		return true;
	}
	
	private static boolean safeEquals(String s1, String s2)
	{
		if (s1 != null) {
			return s1.equals(s2);
		}
		
		// s1 is null
		return s2 == null;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
	public String toStringVerbose()
	{
		return "[" + value + ", " + pos + ", " + syllables + "]";
	}
}
