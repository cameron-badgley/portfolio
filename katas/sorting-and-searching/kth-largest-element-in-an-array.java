/*
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.
*/

class Solution {
    public int findKthLargest(int[] nums, int k) {   
        // Create a copy of the array to avoid modifying the input
        int[] sortedDesc = nums.clone();

        quickSort(sortedDesc, 0, sortedDesc.length - 1);
        
        return sortedDesc[k-1];
    }

    // Sorts in descending order
    private void quickSort(int[] nums, int start, int end){
        
        // If we don't have an array, then there's nothing to sort
        if(nums == null || nums.length == 0){
            return;
        }
        
        // If start >= end, then we've sorted all partitions
        if(start >= end){
            return;
        }
        
        int subLow = start, subHigh = end;
        
        int middle = start + (end - start) / 2;
        int pivot = nums[middle];
        
        while(subLow <= subHigh){
            // All values on the left should be higher than the pivot
            while(nums[subLow] > pivot){
                subLow++;
            }
                
            // All values on the right should be lower than the pivot
            while(nums[subHigh] < pivot){
                subHigh--;
            }
            
            // If we haven't met in the middle yet, then we have some values that need to be swapped
            if(subLow <= subHigh){
                swap(nums, subLow, subHigh);
                subLow++;
                subHigh--;
            }
        }
        
        // Recurse through the sub arrays
        if(start < subHigh){
            quickSort(nums, start, subHigh);
        }
        
        if(end > subLow){
            quickSort(nums, subLow, end);
        }
        
    }
    
    // Modifies the nums array by swapping index1 and index
    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}