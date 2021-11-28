/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> processedValues = new HashMap<>();
        
        // As we loop through the array we'll build a HashMap containing the value and index.
        // We can then check the hashmap to see if we have a value that equals the target when added to the current value
        // The format for the hashmap will be <value, index>
        for(int i=0; i < nums.length; i++){
            int thisValue = nums[i];
            int pairValue = target - thisValue;
            
            if(processedValues.containsKey(pairValue)){
                return new int[]{i, processedValues.get(pairValue)};
            }else{
                processedValues.put(thisValue, i);
            }
        }
        
        // We should never get here assuming each input has exactly one solution
        return null;

        // Inefficient method below
        /*
        int ret[] = new int[2];
        int sum;
        
        // If we sort the input, then when two numbers exceed the target we know we can move to the next element
        Arrays.sort(nums);
        
        // Loop through the array. We need at least two numbers to add together, so 'i' should be less than nums.length - 1
        for(int i=0; i < nums.length - 1; i++){            
            for(int j=1; j < nums.length; j++){
                sum = nums[i] + nums[j];
                if(sum == target){
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }else if(sum > target){
                    // Given that the array is sorted, the next value for j will also cause i + j to exceed the sum
                    // We can move on to the next i value
                    break;
                }
            }
        }
        
        // We should never get here assuming each input has exactly one solution
        return null;
        */
    }
}