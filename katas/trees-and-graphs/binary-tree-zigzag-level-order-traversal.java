/*
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<List<Integer>> results = new ArrayList<List<Integer>>();
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
        if(root == null){
            return results;
        }
        
        // TODO: Traverse through the tree from right to left, then left to right, until we've 
        // visited all nodes
        navigateTree(root);
        
        return results;
    }
    
    // For direction 1 == right to left, 0 == left to right
    private void navigateTree(TreeNode root){
        
        // Stores the nodes at the current level.
        List<Integer> thisLevel = new ArrayList<Integer>();
        
        // Stores the upcoming nodes for the BFS traversal
        LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.add(root);
        // Add a null node as a delimiter for the end of a row
        nodeQueue.add(null);
        
        Boolean leftToRight = true;
        int level = 0;
        
        while(nodeQueue.size() > 0){
            // Get the current node and remove it from the queue
            TreeNode currentNode = nodeQueue.pollFirst();
            
            if(currentNode != null){
                // We still have nodes on this level, so continue processing them
                //System.out.println("Current node value: " + currentNode.val);

                // Add the current node's value to the list for the current level
                // Handle direction (left->right == FIFO and right->left == FILO)
                if(leftToRight){
                    thisLevel.add(currentNode.val);
                }else{
                    thisLevel.add(0, currentNode.val);
                }
                //System.out.println("Current level: " + thisLevel.toString());

                // Add the current node's left node to the end of the queue
                if(currentNode.left != null){nodeQueue.add(currentNode.left);}
                // Add the current node's right node to the end of the queue            
                if(currentNode.right != null){nodeQueue.add(currentNode.right);}
            }else{
                // If we've reached the end of a level, then add the level to the results
                // and start a new level
                results.add(thisLevel);
                thisLevel = new ArrayList<Integer>();
                
                // If we have more nodes to visit, then add a delimiter to signify the end of the next level
                if(nodeQueue.size() > 0){
                    nodeQueue.add(null);
                }
                
                // Zig zag direction
                leftToRight = !leftToRight;
            }
        }
    }
}

