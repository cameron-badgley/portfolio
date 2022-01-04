/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {       
        ListNode results = new ListNode(0);
        ListNode current = results;
        ListNode node1 = l1;
        ListNode node2 = l2;

        int carry = 0;
        
        // While we still have nodes to add in either list, continue adding the values together
        while(node1 != null || node2 != null){
            // If one of the nodes is null, then we need to use 0 for the value of that node
            int value1 = (node1 != null) ? node1.val : 0;
            int value2 = (node2 != null) ? node2.val : 0;
            
            // Add node1 value to node2 and determine whether we need to carry
            // anything over
            int sum = ( value1 + value2 + carry );

            // Store the next summed value (removing the carry if applicable) and move to the next node
            current.next = new ListNode(sum % 10);
            // Move to the next node
            current = current.next;
            // If the sum is greater than 10, then we need to carry over
            carry = sum / 10;
            
            if(node1 != null){ node1 = node1.next; }
            if(node2 != null){ node2 = node2.next; }
        }
        // If we're still carrying over, then we need to add another node
        if(carry > 0){
            current.next = new ListNode(carry);
        }
        
        return results.next;
    }
}