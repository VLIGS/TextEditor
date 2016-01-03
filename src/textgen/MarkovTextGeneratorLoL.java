package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		String [] myStringArray = sourceText.split("[\\s]+");
		starter = myStringArray[0];
		String prevWord = starter;
		for (int i = 1; i<myStringArray.length; i++){
			if(checkIfWordinList(prevWord)){
				addToNodeList(prevWord, myStringArray[i]);
			}
			else{
				ListNode myListNode = new ListNode(prevWord);
				myListNode.addNextWord(myStringArray[i]);
				wordList.add(myListNode);
				prevWord = myStringArray[i];
			}
		}
		if(checkIfWordinList(myStringArray[myStringArray.length-1])){
			addToNodeList(prevWord, starter);
		}
		else {
			ListNode myListNode = new ListNode(myStringArray[myStringArray.length-1]);
			myListNode.addNextWord(starter);
			wordList.add(myListNode);
		}
	}
	private boolean checkIfWordinList(String myWord){
		for(ListNode myNode: wordList){
			if(myWord.equals(myNode.getWord())){
				return true;
			}
		}
		return false;
	}
	private void addToNodeList(String myNodeWord, String wordtoAdd){
		for(ListNode myNode: wordList){
			if(myNodeWord.equals(myNode.getWord())){
				myNode.addNextWord(wordtoAdd);
				return;
			}
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {

		if(wordList.size()==0){
			return "";
		}
		String currWord = starter;
		String output = "";
		output+=currWord;
		output+=" ";
		while(numWords!=0){
			String myRandomWord = getNextWord(currWord);
			output+=myRandomWord;
			output+=" ";
			currWord = myRandomWord;
			numWords--;
		}
		return output;
	}
	private  String getNextWord (String myCurrentWord){
		for(ListNode myNode: wordList){
			if(myCurrentWord.equals(myNode.getWord())){
				return myNode.getRandomNextWord(rnGenerator);
			}
		}
		return null;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		train(sourceText);
	}
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		//some testing
		String myTextString = "hi there hi Leo";
		MarkovTextGeneratorLoL myGen = new MarkovTextGeneratorLoL(new Random(42));
		myGen.train(myTextString);
		System.out.println(myGen.generateText(20));

		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


