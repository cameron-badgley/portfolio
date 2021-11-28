/**
    Given the head of a singly linked list, reverse the list, and return the reversed list.
*/
/**
    Constraints:

    The number of nodes in the list is the range [0, 5000].
    -5000 <= Node.val <= 5000
*/

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
    
    public ListNode reverseList(ListNode head) {
        return reverseListRecursive(head);
    }
    
    public ListNode reverseListRecursive(ListNode head) {
        // When we get to the end of the list (or if we have an empty or single node list), we just return the head
        if(head == null || head.next == null){
            return head;
        }
        
        // After we're done recursing through the list, the rHead node will hold the head of our reversed list
        // (which is the end of our original list)
        ListNode rHead = reverseList(head.next);
        
        // The next node for the current head needs to point back to the current head to reverse the list
        head.next.next = head;
        // The head can't continue pointing to the next node now because we'll have a cycle in the list
        head.next = null;

        return rHead;
    } 
    
    public ListNode reverseListNonrecursive(ListNode head) {
        ListNode currentNode = head;
        
        if(head == null){
            return null;
        }
        
        if(head.next == null){
            return head;
        }
        
        // Create a new head
        ListNode rHead = new ListNode(head.val);
        
        while(currentNode != null){
            /**
             * The next value should be inserted at the front of this list
             * This means, that the next value for the current node should be 
             * the head of our reversed list (rHead).
             */
            if(currentNode.next != null){
                ListNode nextNode = new ListNode(currentNode.next.val);
                nextNode.next = rHead;
                
                /* 
                 * Now, we need to point the rHead to our new head (which is the nextNode that
                 * we just added to the front of the list
                 */
                rHead = nextNode;
            }

            // Move to the next node in the list
            currentNode = currentNode.next;
        }
        
        return rHead;
    }
}