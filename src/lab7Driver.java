import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class lab7Driver {
	public static void main(String[] args) throws IOException 
	  {
	    String word;
	    WordFreq wordToTry;
	    WordFreq wordInTree;

	    BinarySearchTree<WordFreq> tree = new BinarySearchTree<WordFreq>();

	    int numWords = 0;
	    int numValidWords = 0;
	    int numValidFreqs = 0;
	    int minSize;
	    int minFreq;
	    int treeSize;

	    // Set up command line reading
	    Scanner scan = new Scanner(System.in);

	    // Set up file reading
	    String fn;
	    System.out.print("File name > ");
	    fn = scan.next();
	    Scanner wordsIn = new Scanner(new FileReader(fn));
	    wordsIn.useDelimiter("[^a-zA-Z']");  // delimiters are nonletters,'

	    // Process file
	    while (wordsIn.hasNext())      // while more words to process
	    {
	      word = wordsIn.next();          
	      numWords++;
	      if (word.length() >= 1) {
	    	  numValidWords++;
		        word = word.toLowerCase();
		        wordToTry = new WordFreq(word);
		        wordInTree = tree.get(wordToTry);
		        if (wordInTree != null)
		        {
		          // word already in tree, just increment frequency
		          wordInTree.inc();
		        }
		        else
		        {
		          // insert new word into tree
		          wordToTry.inc();               // set frequency to 1
		          tree.add(wordToTry);
		        }
	      }
	        
	     
	    }
	    
	    int heightFreq = 0;
	    int heightLength = 0;
	    for (WordFreq wordFromTree: tree)
	    {
	      if (heightFreq<wordFromTree.getFreq())
	    	  heightFreq = wordFromTree.getFreq();
	      if (heightLength<wordFromTree.getWordIs().length())
	    	  heightLength=wordFromTree.getWordIs().length();

	    }
	    
	    System.out.println();
	    System.out.println("a. Longest word in the file \n");
	    System.out.println("Word");
	    System.out.println("-----------------");
	    
	    for (WordFreq wordFromTree: tree)
	    {
	      if (wordFromTree.getWordIs().length() == heightLength)
	      {
	        System.out.println(wordFromTree.getWordIs());
	      }
	    }
	  
	    System.out.println();
	    System.out.println("b. Most frequently used word in the file\n");
	    System.out.println("Freq  Word");
	    System.out.println("----- -----------------");
	    
	    for (WordFreq wordFromTree: tree)
	    {
	      if (wordFromTree.getFreq() == heightFreq)
	      {
	        System.out.println(wordFromTree);
	      }
	    }
	    
	    // Display results

	    System.out.println();
	    System.out.println("c. The word or words in the file that occur exactly once\n");
	    System.out.println("Freq  Word");
	    System.out.println("----- -----------------");
	    
	    for (WordFreq wordFromTree: tree)
	    {
	      if (wordFromTree.getFreq() == 1)
	      {
	        System.out.println(wordFromTree);
	      }
	    }
	    
	    
	  }
}
