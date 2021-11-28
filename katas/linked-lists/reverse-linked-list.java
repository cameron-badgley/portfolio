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
            /*
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