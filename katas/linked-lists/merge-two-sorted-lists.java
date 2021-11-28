/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // Time complexity: O(N) where N is the length of list1 + list2
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode ret = new ListNode();
        ListNode currentRetNode = new ListNode();
        currentRetNode = ret;
        
        ListNode currentL1Node = list1;
        ListNode currentL2Node = list2;
        
        // Handle edge cases where list1 or list2 are null
        if(list1 == null){
            return list2;
        }
        
        if(list2 == null){
            return list1;
        }
        
        // Loop through the first list
        while(currentL1Node != null){
            // Loop through the second list
            while(currentL2Node != null){
                // If the L2 node is less than or equal to the L1 node then we need to add it to the
                // return list and continue looping through L2                
                if(currentL2Node.val <= currentL1Node.val){
                    ListNode next = new ListNode(currentL2Node.val);
                    currentRetNode.next = next;
                    currentRetNode = currentRetNode.next;
                    
                    currentL2Node = currentL2Node.next;
                }else{
                    // If the L1 node is less than the L2 node, then we need to keep L2 where it is
                    // and continue looping through L1
                    break;
                }
            }
            
            ListNode next = new ListNode(currentL1Node.val);
            currentRetNode.next = next;
            currentRetNode = currentRetNode.next;
            
            currentL1Node = currentL1Node.next;
        }
        
        // If we still have l2 nodes remaining (meaning they were larger than the last l1 node)
        // then add the l2 node2 to the end of the list
        while(currentL2Node != null){
            ListNode next = new ListNode(currentL2Node.val);
            currentRetNode.next = next;
            currentRetNode = currentRetNode.next;
            currentL2Node = currentL2Node.next;            
        }
        
        return ret.next;
    }
}