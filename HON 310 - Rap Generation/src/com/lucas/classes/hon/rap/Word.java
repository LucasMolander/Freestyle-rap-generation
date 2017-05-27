package com.lucas.classes.hon.rap;

import java.util.HashMap;

import com.lucas.classes.hon.rap.lib.PhraseUtilities;

/**
 * Wrapper for a String because it contains:
 * more info (POS, number of syllables)
 * and utility ().
 * 
 * @author Lucas Molander
 */
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
	private PartOfSpeech pos;
	private int syllables;
	
	public Word(String value, String pos)
	{
		value = value.toLowerCase();
		if (APOSTROPHES.get(value) != null) {
			value = APOSTROPHES.get(value);
		}
		this.value = value;
		
		this.pos = getSimplifiedPos(pos);
		
		syllables = PhraseUtilities.countSyllablesInWord(this.value);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public PartOfSpeech getPos()
	{
		return pos;
	}
	
	/**
	 * A lot of these include non-alphabetical characters,
	 * whereas this program currently only considers alphabetical words.
	 * Therefore, a lot of them will go unused.
	 * 
	 * @return the simplified part of speech
	 */
	private static PartOfSpeech getSimplifiedPos(String complicatedPos)
	{
		if (complicatedPos.equals("CC")) // Coordinating conjunction
			return PartOfSpeech.CONJUNCTION;

		if (complicatedPos.equals("CD")) // Cardinal number
			return PartOfSpeech.IDK;

		if (complicatedPos.equals("DT")) // Determiner
			return PartOfSpeech.ADJECTIVE;

		if (complicatedPos.equals("EX")) // Existential there
			return PartOfSpeech.IDK;

		if (complicatedPos.equals("FW")) // Foreign word
			return PartOfSpeech.IDK;

		if (complicatedPos.equals("IN")) // Preposition or subordinating conjunction
			return PartOfSpeech.PREPOSITION; // Used to be CON

		if (complicatedPos.equals("JJ")) // Adjective
			return PartOfSpeech.ADJECTIVE;

		if (complicatedPos.equals("JJR")) // Adjective, comparative
			return PartOfSpeech.ADJECTIVE;

		if (complicatedPos.equals("JJS")) // Adjective, superlative
			return PartOfSpeech.ADJECTIVE;

		if (complicatedPos.equals("LS")) // List item marker
			return PartOfSpeech.IDK;

		if (complicatedPos.equals("MD")) // Modal
			return PartOfSpeech.VERB;

		if (complicatedPos.equals("NN")) // Noun, singular or mass
			return PartOfSpeech.NOUN;

		if (complicatedPos.equals("NNS")) // Noun, plural
			return PartOfSpeech.NOUN;

		if (complicatedPos.equals("NNP")) // Proper noun, singular
			return PartOfSpeech.NOUN;

		if (complicatedPos.equals("NNPS")) // Proper noun, plural
			return PartOfSpeech.NOUN;

		if (complicatedPos.equals("PDT")) // Predeterminer
			return PartOfSpeech.ADJECTIVE;

		if (complicatedPos.equals("POS")) // Possessive ending
			return PartOfSpeech.NOUN;

		if (complicatedPos.equals("PRP")) // Personal pronoun
			return PartOfSpeech.PRONOUN;

		if (complicatedPos.equals("PRP$")) // Possessive pronoun
			return PartOfSpeech.PRONOUN;

		if (complicatedPos.equals("RB")) // Adverb
			return PartOfSpeech.ADVERB;

		if (complicatedPos.equals("RBR")) // Adverb, comparative
			return PartOfSpeech.ADVERB;

		if (complicatedPos.equals("RBS")) // Adverb, superlative
			return PartOfSpeech.ADVERB;

		if (complicatedPos.equals("RP")) // Particle
			return PartOfSpeech.IDK;

		if (complicatedPos.equals("SYM")) // Symbol
			return PartOfSpeech.IDK;

		if (complicatedPos.equals("TO")) // to
			return PartOfSpeech.ADJECTIVE;

		if (complicatedPos.equals("UH")) // Interjection
			return PartOfSpeech.IDK;

		if (complicatedPos.equals("VB")) // Verb, base form
			return PartOfSpeech.VERB;

		if (complicatedPos.equals("VBD")) // Verb, past tense
			return PartOfSpeech.VERB;

		if (complicatedPos.equals("VBG")) // Verb, gerund or present participle
			return PartOfSpeech.VERB;

		if (complicatedPos.equals("VBN")) // Verb, past participle
			return PartOfSpeech.VERB;

		if (complicatedPos.equals("VBP")) // Verb, non-3rd person singular present
			return PartOfSpeech.VERB;

		if (complicatedPos.equals("VBZ")) // Verb, 3rd person singular present
			return PartOfSpeech.VERB;

		if (complicatedPos.equals("WDT")) // Wh-determiner
			return PartOfSpeech.ADJECTIVE;

		if (complicatedPos.equals("WP")) // Wh-pronoun
			return PartOfSpeech.PRONOUN;

		if (complicatedPos.equals("WP$")) // Possessive wh-pronoun
			return PartOfSpeech.PRONOUN;

		if (complicatedPos.equals("WRB")) // Wh-adverb
			return PartOfSpeech.ADVERB;
		
		// Shouldn't get here
		return PartOfSpeech.IDK;
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
		
		if (this.pos != other.pos) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Used in equals() to carelessly compare Strings.
	 * 
	 * @param s1 first String to compare
	 * @param s2 second String to compare
	 * @return whether s1.equals(s2) (taking null into account)
	 */
	private static boolean safeEquals(String s1, String s2)
	{
		if (s1 != null) {
			return s1.equals(s2);
		}
		
		// s1 is null
		return s2 == null;
	}
	
	/**
	 * The value for this word.
	 * 
	 * @return the value for this word
	 */
	@Override
	public String toString()
	{
		return value;
	}
	
	/**
	 * Gets the value, POS, and number of syllables for this Word.
	 * 
	 * @return the value, POS, and number of syllables for this Word
	 */
	public String toStringVerbose()
	{
		return "[" + value + ", " + pos + ", " + syllables + "]";
	}
}
