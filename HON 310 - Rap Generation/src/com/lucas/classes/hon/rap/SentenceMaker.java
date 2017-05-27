package com.lucas.classes.hon.rap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates sentences given a list of words and a rhyming word.
 * 
 * @author Lucas Molander
 */
public class SentenceMaker {

	private ArrayList<Word> nounList = null;
	private ArrayList<Word> pronounList = null;
	private ArrayList<Word> verbList = null;
	private ArrayList<Word> adjectiveList = null;
	private ArrayList<Word> adverbList = null;
	private ArrayList<Word> prepositionList = null;
	private ArrayList<Word> conjunctionList = null;
	
	private Random rand = new Random(System.currentTimeMillis());
	
	/**
	 * Create a SentenceMaker given a list of Words.
	 * 
	 * @param words
	 */
	public SentenceMaker(ArrayList<Word> words)
	{
		nounList = new ArrayList<Word>();
		pronounList = new ArrayList<Word>();
		verbList = new ArrayList<Word>();
		adjectiveList = new ArrayList<Word>();
		adverbList = new ArrayList<Word>();
		prepositionList = new ArrayList<Word>();
		conjunctionList = new ArrayList<Word>();
		
		// Ensure each list has at least one word
		nounList.add(new Word("city", "N"));
		pronounList.add(new Word("he", "PRO"));
		verbList.add(new Word("crushing", "V"));
		adjectiveList.add(new Word("ratchet", "ADJ"));
		adverbList.add(new Word("quickly", "ADV"));
		prepositionList.add(new Word("on", "PREP"));
		conjunctionList.add(new Word("and", "CON"));
		
		Word w;
		for (int i = 0; i < words.size(); i++) {
			w = words.get(i);
			
			switch (w.getPos()) {
			case NOUN:
				nounList.add(w);
				break;
			case PRONOUN:
				pronounList.add(w);
				break;
			case VERB:
				verbList.add(w);
				break;
			case ADJECTIVE:
				adjectiveList.add(w);
				break;
			case ADVERB:
				adverbList.add(w);
				break;
			case PREPOSITION:
				prepositionList.add(w);
				break;
			case CONJUNCTION:
				conjunctionList.add(w);
				break;
			default:
				// Only use parts of speech I recognize
				continue;
			}
		}
	}
	
	/**
	 * Makes a line.
	 * 
	 * @param endingWord
	 * @param minSyllables
	 * @param graph
	 * @return the randomly generated line
	 */
	public Line makeLine(Word endingWord, int minSyllables, TransitionGraph graph)
	{
		ArrayList<Word> list = new ArrayList<Word>();
		int currentSyllables = 0;
		
		list.add(endingWord);
		
		Word lastWord = endingWord;
		
		while (currentSyllables < minSyllables) {
			int col = TransitionGraph.getIndex(lastWord.getPos());
			int row = GraphUtils.randomRowInColumn(graph, col);
			PartOfSpeech pred = TransitionGraph.getPos(row);
			
			// Get the list that contains the next word
			Word nextWord;
			ArrayList<Word> nextWordList = null;
			switch (pred) {
			case NOUN:
				nextWordList = nounList;
			case PRONOUN:
				nextWordList = pronounList;
				break;
			case VERB:
				nextWordList = verbList;
				break;
			case ADJECTIVE:
				nextWordList = adjectiveList;
				break;
			case ADVERB:
				nextWordList = adverbList;
				break;
			case PREPOSITION:
				nextWordList = prepositionList;
				break;
			case CONJUNCTION:
				nextWordList = conjunctionList;
				break;
			default:
				// Just guess at nouns
				nextWordList = nounList;
				break;
			}
			
			// nextWord = nextWordList.get((int) Math.floor(Math.random() * nextWordList.size()));
			nextWord = nextWordList.get((int) Math.floor(rand.nextDouble() * nextWordList.size()));
			
			list.add(nextWord);
			currentSyllables += nextWord.getSyllables();
			lastWord = nextWord;
		}
		
		Line l = new Line();
		
		// Go backwards
		for (int i = list.size() - 1; i >= 0; i--) {
			l.addWord(list.get(i));
		}
		
		return l;
	}
	
}
