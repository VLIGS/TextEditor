package spelling;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size; //number of words in dictionary?
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		if(word.length()==0){return false;}
		word = word.toLowerCase();
		TrieNode myCurrentNode = root;
		return addSubWord(word, myCurrentNode);
	}

	private boolean addSubWord(String word, TrieNode myCurrentNode){
		if(word.length()==0){
			if(!myCurrentNode.endsWord()){
				myCurrentNode.setEndsWord(true);
				size++;
			}
			return false;
		}
		if(myCurrentNode.getChild(word.charAt(0))==null) {
			myCurrentNode = myCurrentNode.insert(word.charAt(0));
			if (word.length() == 1) {
				myCurrentNode.setEndsWord(true);
				size++;
				return true;
			} else {
				for (int i = 1; i < word.length(); i++) {
					myCurrentNode = myCurrentNode.insert(word.charAt(i));
				}
				myCurrentNode.setEndsWord(true);
				size++;
				return true;
			}
		}
		else {
			myCurrentNode = myCurrentNode.getChild(word.charAt(0));
			return addSubWord(word.substring(1),myCurrentNode);
		}
	}
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		if(s.length()==0 || this.size == 0){return false;}
		s = s.toLowerCase();
		TrieNode myCurrentNode = root;
		return isWordLong(s,myCurrentNode);
	}
	public boolean isWordLong(String s, TrieNode myCurrentNode){
		if(s.length()==0) {
			if (myCurrentNode.endsWord()) {
				return true;
			} else {
				return false;
			}
		}
		else if(myCurrentNode.getChild(s.charAt(0))!=null) {
			myCurrentNode = myCurrentNode.getChild(s.charAt(0));
			return isWordLong(s.substring(1),myCurrentNode);
		}
		else {
			return false;
		}
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param //the text to use at the word stem
     * @param //n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
		 if(numCompletions==0){
			 return new LinkedList<>();
		 }
		 LinkedList<String> myReturnList = new LinkedList<String>();

		 // This method should implement the following algorithm:
		 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an empty list
		 prefix = prefix.toLowerCase();
		 TrieNode myCurrentNode = root;
		 TrieNode myStemNode = findStem(prefix,myCurrentNode);

    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
		 LinkedList<TrieNode> myQueue = new LinkedList<TrieNode>();
		 myQueue.add(myStemNode);
    	 //    Create a list of completions to return (initially empty)
		 LinkedList<String> completions = new LinkedList<String>();
		 //    While the queue is not empty and you don't have enough completions:
		 TrieNode myNode;
		 while(myQueue.size()!=0 && numCompletions != 0){
			 //       remove the first Node from the queue
			 myNode = myQueue.removeFirst();
			 //       If it is a word, add it to the completions list
			 if(myNode.endsWord()){
				 completions.add(myNode.getText());
				 numCompletions--;
			 }
			 //       Add all of its child nodes to the back of the queue
			 Set<Character> myCharacters = myNode.getValidNextCharacters();
			 for(Character myCharacter: myCharacters){
				 myQueue.add(myNode.getChild(myCharacter));
			 }
		 }
		 // Return the list of completions
         return completions;
     }
	private TrieNode findStem (String stem, TrieNode currentNode){
		if(stem.length()==0){return currentNode;}
		else if(currentNode.getChild(stem.charAt(0))!=null) {
			currentNode = currentNode.getChild(stem.charAt(0));
			return findStem(stem.substring(1), currentNode);
		}
		return new TrieNode();
	}

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
}