/*
A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
*/

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    HashMap<Node,  Node> cloned = new HashMap<Node, Node>();
    
    private Node getClonedNode(Node node){
        // This method creates a clone of the current node with next and random set to null.
        // These nodes will need to be copied separately and then linked to this node
        if(node != null){
            // If we've already cloned this node, then return the clone
            if(this.cloned.containsKey(node)){
                return cloned.get(node);        
            }else{
                // If we haven't cloned the node, then create a clone and return it
                Node nodeClone = new Node(node.val);
                cloned.put(node, nodeClone);
                return nodeClone;   
            }
        }
        return null;
    }
    
    public Node copyRandomList(Node head) {

        // If we don't have a head, then return null
        if(head == null){
            return null;
            
        }
        
        Node oldNode = head;
        Node cloned = getClonedNode(oldNode);

        // Iterate through the list and make copies of each node
        while(oldNode != null){
            // Create copies of next and random
            Node nextClone = this.getClonedNode(oldNode.next);
            Node randomClone = this.getClonedNode(oldNode.random);
            
            // Associated the copies with the cloned node            
            cloned.next = nextClone;
            cloned.random = randomClone;

            // move to the next node in the list
            cloned = cloned.next;
            oldNode = oldNode.next;
        }        
        
        // Return the cloned head
        return this.getClonedNode(head);
    }
}