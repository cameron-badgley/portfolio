/*
Convert a non-negative integer num to its English words representation.

Example 1:
Input: num = 123
Output: "One Hundred Twenty Three"

Example 2:
Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"

Example 3:
Input: num = 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Example 4:
Input: num = 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
*/

class Solution {
    /* 
    Max value is 2^31 or 2,147,483,648, so the word conversion doesn't have to exceed "billion"

    The conversion of the max value (including the significance) can be done as follows:
    9: 2 -> Two Billion
    8: 1 -> One Hundred
    7: 4 -> Forty
    6: 7 -> Seven Million
    5: 4 -> For Hundred
    4: 8 -> Eighty
    3: 3 -> Three Thousand
    2: 6 -> Six Hundred
    1: 4 -> Forty
    0: 8 -> Eight
    */
    
    // Create a hashmap containing indices that need to append thousand, million or billion
    private Map<String, String> indicesToAppend = new HashMap<String, String>();
        
    // Create a hashmap for the tens (excluding 1x)
    private Map<String, String> tensNot1x = new HashMap<String, String>();
    
    // Create a hashmap for the 1x tens    
    private Map<String, String> tens1x = new HashMap<String, String>();
    
    // Create a hashmap for the ones
    private Map<String, String> ones = new HashMap<String, String>();

    public void initNumberToWordsMaps(){
        // Hashmap containing indices that need to append thousand, million or billion
        indicesToAppend.put("3", "Thousand");
        indicesToAppend.put("6", "Million");
        indicesToAppend.put("9", "Billion");

        // Hashmap for the tens (excluding 1x)
        tensNot1x.put("2", "Twenty");
        tensNot1x.put("3", "Thirty");
        tensNot1x.put("4", "Forty");
        tensNot1x.put("5", "Fifty");
        tensNot1x.put("6", "Sixty");
        tensNot1x.put("7", "Seventy");
        tensNot1x.put("8", "Eighty");
        tensNot1x.put("9", "Ninety");
        
        // Hashmap for the 1x tens    
        tens1x.put("0", "Ten");
        tens1x.put("1", "Eleven");
        tens1x.put("2", "Twelve");
        tens1x.put("3", "Thirteen");
        tens1x.put("4", "Fourteen");
        tens1x.put("5", "Fifteen");
        tens1x.put("6", "Sixteen");
        tens1x.put("7", "Seventeen");
        tens1x.put("8", "Eighteen");
        tens1x.put("9", "Nineteen");
        
        // Hashmap for the ones
        ones.put("1", "One");
        ones.put("2", "Two");
        ones.put("3", "Three");
        ones.put("4", "Four");
        ones.put("5", "Five");
        ones.put("6", "Six");
        ones.put("7", "Seven");
        ones.put("8", "Eight");
        ones.put("9", "Nine");
    }
    
    
    public String numberToWords(int num) {
        initNumberToWordsMaps();
        
        String ret = "";
        
        String numAsString = String.valueOf(num);
        
        String thisValue = "";
        String nextValue = ""; 
        String nextNextValue = "";
        int significance = -1;
        
        if(num == 0){
            return "Zero";
        }
        
        for(int i = 0; i < numAsString.length(); i++){
            // We start at the MSB when reading a number
            significance = numAsString.length() - i - 1;
            
            //System.out.println(significance + ":" + numAsString.charAt(i));
            thisValue = numAsString.substring(i, i+1).trim();
            if(i + 1 < numAsString.length()){
                nextValue = numAsString.substring(i+1, i+2).trim();
            }
            if(i + 2 < numAsString.length()){
                nextNextValue = numAsString.substring(i+2, i+3).trim();
            }
            
            /*System.out.println("Evaluating " + thisValue + " with significance " + significance + " and nextValue of " + nextValue);*/
            
            // If index % 3 == 0: Display the number and append thousand, million, or billion if appropriate
            if(significance % 3 == 0){
                //ret = ret + " " + ones.get(thisValue);
                 
                /*System.out.println("Converted digit value: " 
                    + ones.get(thisValue) + " " 
                    + indicesToAppend.getOrDefault(String.valueOf(significance), "") + " ");*/
                if(!thisValue.equals("0")){
                    ret = ret 
                        + ones.getOrDefault(thisValue, "") + " "
                        + indicesToAppend.getOrDefault(String.valueOf(significance), "") + " ";
                }
            }
            else if(significance % 3 == 2){
                // If index % 3 == 2: Convert to hundreds (e.g. 6 -> Six Hundred)
                
                 //System.out.println("Converted digit value: " + ones.get(thisValue) + " Hundred ");
                if(!thisValue.equals("0")){
                    ret = ret + ones.get(thisValue) + " Hundred ";

                    if(nextValue.equals("0") && nextNextValue.equals("0")){
                        ret = ret + indicesToAppend.getOrDefault(String.valueOf(significance - 2), "") + " ";
                    }                
                }
            }else if(significance % 3 == 1){
                if(thisValue.equals("1")){
                    // If the value is 1 then it will need to be handled separately because it 
                    // requires ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, 
                    // eighteen, and nineteen
                    
                    //System.out.println("Converted digit value: " + tens1x.get(nextValue) + " ");
                    ret = ret + tens1x.get(nextValue) + " ";
                    
                    // For tens we need to skip the next digit, so we don't end up returning something like
                    // 12 = Twelve two
                    i++;

                    // However, we also need to include the appended text based on the significance
                    // if applicable
                    ret = ret + indicesToAppend.getOrDefault(String.valueOf(significance - 1), "") + " ";
                }else if(!thisValue.equals("0")){
                    // If isn't 1 or 0 then multiply by 10 to get the english 
                    // word value 
                    // (e.g. 4 -> 40)

                    //System.out.println("Converted digit value: " + tensNot1x.get(thisValue) + " ");
                    ret = ret + tensNot1x.get(thisValue) + " ";
                    
                    // We also need to include the appended text based on the significance
                    // if the next digit is 0
                    if(nextValue.equals("0")){
                        ret = ret + indicesToAppend.getOrDefault(String.valueOf(significance - 1), "") + " ";
                    }
                }else{
                    // If value is 0 then do nothing (e.g. 403 is just "Four hundred three". 
                    // The 0 doesn't do anything here unless we want to include an "and".)
                }
            }
        }

        ret = ret.replace("  ", " ");
        return ret.trim();
    }
}