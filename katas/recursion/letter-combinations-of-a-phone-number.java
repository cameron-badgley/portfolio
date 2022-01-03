/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
*/

class Solution {
    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> phoneLetterMapping = Map.of('2', "abc", '3', "def", '4', "ghi", '5', "jkl", '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
    private String phoneDigits;
    
    public List<String> letterCombinations(String digits) {
        // If we don't have any digits, then we can't have any letter combinations
        if(digits.length() == 0){
            return combinations;
            
        }
        
        phoneDigits = digits;
 
        generateCombinations(0, new StringBuilder());
        
        return combinations;
    }
    
    private void generateCombinations(int digitIndex, StringBuilder currentCombo){
        System.out.println("generateCombinations() curentCombo: " + currentCombo.toString());

        // If we have the same number of letters as numbers, then we've generated a valid combination
        if(currentCombo.length() >= phoneDigits.length()){
            combinations.add(currentCombo.toString());
            return;
        }

        // Get the possible letters for this digit
        String possibleLetters = phoneLetterMapping.get(phoneDigits.charAt(digitIndex));
        System.out.println("Possible letters at index " + digitIndex + ": " + possibleLetters);

        // Loop through each letter for the current digit
        for(char letter: possibleLetters.toCharArray()){
            System.out.println("Possible letter: " + letter);
            // Add the letter to our current combo
            currentCombo.append(letter);
            // Move on to the next digit
            generateCombinations(digitIndex+1, currentCombo);
            // Pop this letter because we need to start a new combo for the next possible letter
            currentCombo.deleteCharAt(currentCombo.length() - 1);
        }
    }
}