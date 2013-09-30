import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*This program takes an English sentence as input and determines whether if it is a  
panagram (a sentence containing all leters of the alphabet). If the sentence is a panagram, the program returns null. 
If the sentence is not a panagram, the program outputs all the missing characters preventing it from being a panagram.
*/

public class Panagram {
	
	public static void isPanagram(String s){
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] inputstr = s.toLowerCase().replaceAll("\\s","").toCharArray();
		Set<Character> charSet = new HashSet<Character>();
		Queue<Character> missingChars = new LinkedList<Character>();
		String missingCharString = null;
		
		for(char k: inputstr){
			charSet.add(k);
		}
		for(char c : alphabet){
			if(!charSet.contains(c)) //if input string does not have character from alphabet, add to missing character queue
				missingChars.add(c);
		}
		if(!missingChars.isEmpty())
			missingCharString = "";
		while(!missingChars.isEmpty()){
			missingCharString+=missingChars.poll();
		}
		System.out.println(missingCharString);	//print out missing characters
		
	}
	
	public static void main(String[] args) throws IOException
    {
       String file = args[0];
       BufferedReader br = null;
       try {
    	   br = new BufferedReader(new FileReader(file));
           String line = null;
           while ((line = br.readLine()) != null) {
            	isPanagram(line);
           }
       }
       finally {
           if (br != null) {
                br.close();
           }
      }
    }
}
