/*
Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
*/

class Solution {
    // Repeating in this example means it is only included in the String once.
    // It doesn't have to be repeated sequentially to be excluded. If it's included more than one anywhere 
    // in the String, then it's not a valid unique char.
    // Assume all lowercase, so only 26 possible values

    public int firstUniqChar(String s) {
        int charsWithCount[] = new int[26];
        int length = s.length();
        
        // Build an array containing each possible char and the number of times it occurs
        for(int i = 0; i < length; i++){
            // Subtracting chars gives you that chars index alphabetically. For example, 'c' - 'a' == 2.
            int index = s.charAt(i) - 'a';
            charsWithCount[index] = charsWithCount[index] + 1;
        }
        
        // Start at the beginning of the String and look for the first char that isn't repeated later on
        for(int i = 0; i < length; i++){
            int index = s.charAt(i) - 'a';
            
            if(charsWithCount[index] == 1){
                return i;
            }
        }
        
        // If we made it here, then all chars were repeated (or we have an empty String)
        return -1;
        
        // Inefficient method below

        /*
        // We'll look at each char in the String sequentially and then check 
        // to see if it's included further down the String
        Character thisChar = ' ';
        String remainingString = "";
        
        // If we've already processed a particular char, then we don't 
        // need to check it again further down the String.
        List<Character> processedChars = new ArrayList<Character>();
        
        for(int i = 0; i < s.length(); i++){
            thisChar = s.charAt(i);

            // Search the rest of the String to see if thisChar is repeated.
            if(!processedChars.contains(thisChar)){
                processedChars.add(thisChar);
                
                remainingString = s.substring(i+1, s.length());
                if(remainingString.indexOf(thisChar) == -1){
                    // If we can't find another instance of thisChar, then 
                    // this is the first unique char
                    return i;
                }
            }else{
                // Skip thisChar. It has already been ruled out.
            }
        }
        
        return -1;
        */
        
        // Old method of searching for sequential repetition
        /*
        char previousChar = ' ';
        char thisChar = ' ';
        
        for(int i = 0; i < s.length(); i++){
            thisChar = s.charAt(i);
            
            if(previousChar != ' ' && thisChar != previousChar){
                return i;
            }else{
                previousChar = s.charAt(i);
            }
            
        }
        return -1;
        */
    }
}