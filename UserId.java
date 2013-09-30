
import java.util.*;
public class UserId {
	public String userid(String[] inUse, String first, String middle, String last) {
		if(badCharsCheck(first) == false || badCharsCheck(middle) == false || badCharsCheck(last) == false){
			return "BAD DATA";
		}
		Set<String> IDs = new HashSet<String>(); //we use a set because all user IDs are guaranteed to be unique
		String f_initial_last = Character.toLowerCase(first.charAt(0))+last.toLowerCase().trim(); //[first initial][last name]  
		String first_last_digit = Character.toLowerCase(first.charAt(0))+last.toLowerCase().trim() + "01"; 
		StringBuilder digitname = new StringBuilder(first_last_digit);  //[first initial][last name][digit][digit] 
																		//we use StringBuilder instead of String because we will in many cases have to alter the digits several times 
																		//to find a unique user ID
		IDs.addAll(Arrays.asList(inUse)); 
		if(!IDs.contains(f_initial_last)){
			return f_initial_last;
		}
		else if(!middle.trim().equals("")){ //middle name exists
			String f_initial_mlast = Character.toLowerCase(first.charAt(0))+Character.toLowerCase(middle.charAt(0))+last.toLowerCase().trim(); ////[first initial][middle initial][last name] 
			if(!IDs.contains(f_initial_mlast)) //check if the user ID of form [first initial][middle initial][last name] already exists 
				return f_initial_mlast;
		}
		else{
			if(!IDs.contains(first_last_digit)){
				return digitname.toString();
			}
			else{
				char lastchar = digitname.charAt(digitname.length()-1);
				int increment = 2;
				while(IDs.contains(digitname.toString())){
					digitname.setCharAt(digitname.length()-1, (char)(('0' + lastchar)+increment)); //increment last digit until we find a user name that hasn't been used
					increment++;	
				}
				return digitname.toString();
			}
					
		}
		return null;
			
	}
		public boolean badCharsCheck(String name){
			Set<Character> allowedChars = new HashSet<Character>();
			allowedChars.add(' ');
			allowedChars.add('\'');
			for(char lowercase = 'a', uppercase = 'A'; lowercase <= 'z'; lowercase++, uppercase++){ //add alphabet A-Z and a-z to set
				allowedChars.add(lowercase);
				allowedChars.add(uppercase);
			}
			for(int i = 0; i < name.length(); i++){
				if(!allowedChars.contains(name.charAt(i)))
						return false;
			}
			
			return true;
		}
	
}
