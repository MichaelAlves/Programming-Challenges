import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Poetry {
	
	public String endingPattern(String s){ 
		Set<Character> vowels = new HashSet<Character>();
		vowels.add('a'); vowels.add('e'); vowels.add('i'); vowels.add('o'); vowels.add('u');
		vowels.add('y'); //vowels + y, as defined by TopCoder
		Queue<Integer> vowelorder = new PriorityQueue<Integer>(); //priority queue to get lowest index of a vowel in the input string
		char[] str = s.toCharArray();
	
		for(int i = 0; i < str.length; i++){
			if(vowels.contains(str[i]))
				if(str[i] == 'y' && (i == 0 || i == str.length-1))
					continue;
				else
					vowelorder.add(i);
		}
	
		return s.substring(vowelorder.poll());  //returns a substring starting with first appearing vowel in s
									            //e.g. if input is "spying", this method returns "ying", as index of y < index of i
	}
	
	public String rhymeScheme(String[] s){
		String[] endWords = new String[s.length];
		Map<String, Character> pattern_char_pairs = new HashMap<String, Character>(); //we use a map to create pattern-character pairs
		pattern_char_pairs.put("whitespace", ' ');
		char start = 'a';
		String outputString = "";
		int i = 0;
		for(String line: s){
			endWords[i] = line.replaceAll("\\s+$", "");	//remove trailing spaces
			endWords[i] = endWords[i].substring(endWords[i].lastIndexOf(" ")+1).toLowerCase();	//last word in each string
			i++;
		}
		for(String key: endWords){
			if(key.trim().equals("")){
				outputString+=pattern_char_pairs.get("whitespace");
			}
			else if (pattern_char_pairs.containsKey(endingPattern(key))){
				outputString+=pattern_char_pairs.get(endingPattern(key)); //adds mapped value (character) from key (pattern) to output string  
			}
			else{
				pattern_char_pairs.put(endingPattern(key), start);		//add new pattern-character pair to map
				outputString+=start;									//add current char to output string
				if(start=='z')											//then increment the char	
					start = 'A';
				else
					start++;
			}
		}
		
		return outputString;
	}
}
