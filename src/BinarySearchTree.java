/*
 * Authors: Christopher Waschke, Brody Weinkauf, Jackson Jenks
 * Assignment: Week 9 - Binary Search Trees
 * Description: Contact Application Using Binary Search Tree
 */


package src;

/*
 * Notes about Binary Trees
 * Binary Trees are good for performing searches.
 * Three types of searching these trees are:
 * Pre-Order Top to Left to Right.
 * In-Order, think of this as left to right reading.
 * Post-order Left to Right, bottom to top.
 * 
 * In a binary search tree, Right is greater than Left in comparison to branches above.
 * No duplicates are allowed.
 */

import java.util.ArrayList;
import java.util.Stack;

/*
 * A Generic BinarySearchTree Class.
 * Type T must extend Comparable as to be used in the sorting of new nodes.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    /*
     * Enum SearchType
     * Used to decide what type of traversal in the get method.
     */
    public enum SearchType{
        PREORDER,
        INORDER,
        POSTORDER,
        SEARCH;
    }

    private BinaryTreeNode<T> root; // The root Node of our Binary Tree.

    /*
     * Constructor Method
     * 
     * Creates a new BinarySearchTree, without an initial value for the root.
     */
    public BinarySearchTree() {
        root = null;
    }

    /*
     * Constructor Method
     * 
     * Constructs a new BinarySearchTree object with an initial value for the root.
     */
    public BinarySearchTree(T value) {
        root = new BinaryTreeNode<>(value);
    }

    
    /* 
     * Void Method
     * If the tree was initialized without a value, it sets the root to the new Node with value T.
     * Otherwise this method uses the traditional binary search method to add a node to the tree.
     * We added weights to the values of the First and Last names in the Contact class, which are used here to decide where to find the node.
     * In a general since, this class, as it's generic, simply compares the two values till it find the right node to append our name to.
     */
    public void add(T newData) throws DuplicateNodeException{
        if(root == null){
            this.root = new BinaryTreeNode<>(newData);
            return;
        }

        BinaryTreeNode<T> current = root;
        while(current != null){
            if(current.getValue().compareTo(newData) == 0){
                throw new DuplicateNodeException();
            }
            if(current.getValue().compareTo(newData) > 0){
                if(current.getLeft() == null){
                    current.setLeft(new BinaryTreeNode<>(newData));
                    break;
                }
                current = current.getLeft();
            }
            else if(current.getValue().compareTo(newData) < 0){
                if(current.getRight() == null){
                    current.setRight(new BinaryTreeNode<>(newData));
                    break;
                }
                current = current.getRight();
            }
        }
    }

    /*
     * BinaryTreeNode<T> Method
     * We default to a Pre-Order search, as such this method calls getPreOrder() directly and just returns the result.
     * 
     * Parameters:
     * value (T) The value we're finding for.
     */
    public T get(T value) throws NodeNotFoundException{
        return getPreOrder(value).getValue();
    }

    /*
     * OverLoad of T get Method
     * This is just a switch case front-end to our internal different methods of traversal.
     * Parameters:
     *   value (T) The value we're finding for.
     *   searchType (SearchType Enum) The type of tree traversal desired to find the node.
     */
    public T get(T value, SearchType searchType) throws NodeNotFoundException{
        switch (searchType) {
            case PREORDER:
                return this.getPreOrder(value).getValue();
            case INORDER:
                return this.getInOrder(value).getValue();
            case POSTORDER:
                return this.getPostOrder(value).getValue();
            case SEARCH:
                return this.getSearch(value).getValue();
            default:
                return null; // Really, really shouldn't ever happen.
        }
    }

    /* BinaryTreeMethod<T> 
     * This method uses the traditional binary search method to find a node in the tree.
     * We added weights to the values of the First and Last names in the Contact class, which are used here to decide where to find the node.
     * In a general since, this class, as it's generic, simply compares the two values till it find the right node.
     * 
     * Parameters:
     *  value (T) The value we're finding for
    */
    public BinaryTreeNode<T> getSearch(T value) throws NodeNotFoundException{
        BinaryTreeNode<T> current = root;
        while(current != null){
            int compareVal = current.getValue().compareTo(value);
            if(compareVal == 0){
                return current;
            }
            if(compareVal > 0){
                current = current.getLeft();
            }
            if(compareVal < 0){
                current = current.getRight();
            }
        }
        throw new NodeNotFoundException();
    }
    
    /*
     * BinaryTreeNode<T> method
     * Traverses the Tree using the Pre-Order traversal style.
     * First, we create a stack to use as a processing order of nodes.
     * Unlike the other methods, we start by popping this node, to initially process the root.
     * If the node isn't the one we are looking for, we add the right child, if it exists, to the stack for processing.
     * We then add the left child to the tree for processing.
     * We loop this process until we have searched every node in the tree for the value we are looking for.
     * 
     * Parameters:
     *   value (T) The value we're finding for
     */
    private  BinaryTreeNode<T> getPreOrder(T value) throws NodeNotFoundException{
        Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();
        nodeStack.push(root);
        while(!nodeStack.empty()){
            BinaryTreeNode<T> currNode = nodeStack.pop();
            if(currNode != null){
                if(currNode.getValue().equals(value)){
                    return currNode;
                }
                if(currNode.getRight() != null){
                    nodeStack.push(currNode.getRight());
                }
                if(currNode.getLeft() != null){
                    nodeStack.push(currNode.getLeft());
                }
            }
        }
        throw new NodeNotFoundException();
    }


    /*
     * BinaryTreeNode<T> method
     * Traverses the Binary Tree using the In Order traversal pattern. (Left->Root->Right)
     * We dig through the nodes to the furthest left node. Adding each node to the stack as we go.
     * Once we're at the bottom, we process the left most node. Then we process the center. Finally, we change currNode to the right most node.
     * This will re-trigger our digging, if the node has left children, restarting the process.
     * We do this process until we found the node that we are looking for, which will be returned.
     * 
     * Parameters:
     *  value (T) The value we're finding for
     */
    private BinaryTreeNode<T> getInOrder(T value) throws NodeNotFoundException{
        Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();
        BinaryTreeNode<T> currNode = root;  // Start at our first node.

        while(currNode != null || !nodeStack.empty()){

            // Loop through the the left most node of each branch. Note that all we're doing here is digging, not processing.
            // Each node we're processing, we dig again on. That's why it's at the top of the first loop.
            while (currNode != null) {
                nodeStack.push(currNode);
                currNode = currNode.getLeft();
            }

            // Finally, we start processing the nodes.  We get the first node off the stack, and check if it's value is what we're looking for.
            currNode = nodeStack.pop();
            if(currNode.getValue().equals(value)){
                return currNode;
            }

            // If it's not the node we're looking for, we move onto it's right sibling.
            currNode = currNode.getRight();
        }

        throw new NodeNotFoundException();
    }

    /*
     * ArrayList<T> method
     * This method is the exact same as the method above, except instead of looking for any single node,
     * We travel everyone adding each to an ArrayList to be returned later. Used for viewing every entry in the tree.
     */
    public  ArrayList<T> getAllInOrder(){
        Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();
        BinaryTreeNode<T> currNode = root;  // Start at our first node.
        ArrayList<T> allNodeValues = new ArrayList<>();

        while(currNode != null || !nodeStack.empty()){

            // Loop through the the left most node of each branch. Note that all we're doing here is digging, not processing.
            // Each node we're processing, we dig again on. That's why it's at the top of the first loop.
            while (currNode != null) {
                nodeStack.push(currNode);
                currNode = currNode.getLeft();
            }

            // Finally, we start processing the nodes.  We get the first node off the stack, and check if it's value is what we're looking for.
            currNode = nodeStack.pop();
            allNodeValues.add(currNode.getValue());

            // If it's not the node we're looking for, we move onto it's right sibling.
            currNode = currNode.getRight();
        }
        return allNodeValues;
    }

    /*
     * BinaryTreeNode<T> method
     * Traverses the tree using the Post Order traversal method.
     * We use two stacks to keep track the nodes that will be processed, once we leave finding the nodes
     * And the use a separate stack, to keep track of which nodes are parent nodes, to be able to process and find the nodes to left
     * After finding the nodes to the right. (Result in left->right->top when popped.)
     * 
     * Parameters:
     *  value (T): The value of the node we're looking for.
     */
    private BinaryTreeNode<T> getPostOrder(T value) throws NodeNotFoundException{
        Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();
        Stack<BinaryTreeNode<T>> parentStack = new Stack<>();
        BinaryTreeNode<T> currNode = root;

        // What we do here, is loop through each node, first processing the right nodes,
        // And then processing the left nodes.
        // We keep track of the parent nodes to pop back into once we're done processing right-left.
        while(currNode != null || !nodeStack.empty()){
            while(currNode != null || !parentStack.empty()){
                if(!nodeStack.contains(currNode)){
                    nodeStack.push(currNode); // Push current node, starting with root.
                }
                if(currNode.getRight() != null){
                    parentStack.push(currNode);
                    currNode = currNode.getRight();
                    continue;
                }
                if(!parentStack.empty()){
                    currNode = parentStack.pop();
                    if(currNode.getLeft() != null){
                        currNode = currNode.getLeft();
                    } else if (currNode.getRight() != null){
                        currNode = currNode.getRight();
                    }
                } else{
                    currNode = null; // Set currNode to null, exiting the loop.
                }
            }

            // Process the nodes, popping each of the stack. This will only be reached after processing the node's children, if it has any. 
            currNode = nodeStack.pop();
            if(currNode.getValue().equals(value)) {
                return currNode;
            }
            currNode = null; // Set currNode to null, exiting the loop.
        }
        throw new NodeNotFoundException();
    }


    /*
     * Boolean Method
     * Using the Search style, return if the desired value is in the tree.
     * 
     * Parameters:
     *  value (T): The value of the node we're looking for.
     */
    public Boolean contains(T value){
        BinaryTreeNode<T> current = root;
        while(current != null){
            int compareVal = current.getValue().compareTo(value);
            if(compareVal == 0){
                return true;
            }
            if(compareVal > 0){
                current = current.getLeft();
            }
            if(compareVal < 0){
                current = current.getRight();
            }
        }
        return false;
    }

    /*
     * BinaryTreeNode<T> method.
     * Private Method.
     * Returns the node that is the parent of the value we're looking for. Used in removing.
     * Parameters:
     *  value (T): The value of the node we're looking for.
     */
    private BinaryTreeNode<T> getParentNode(T value) throws NodeNotFoundException{
        BinaryTreeNode<T> parent = null;
        BinaryTreeNode<T> current = root;
        while(current != null){
            int compareVal = current.getValue().compareTo(value);
            if(compareVal == 0){
                return parent;
            }
            parent = current;
            if(compareVal > 0){
                current = current.getLeft();
            }
            if(compareVal < 0){
                current = current.getRight();
            }
        }
        throw new NodeNotFoundException();
    }

    /*
     * Void Method
     * Removes a tree from the Binary Tree node.
     * Shifts every the left branch into the current value, while removing the current value.
     * Parameters:
     * value (T): The value of the node we're looking for.
     */
    public void remove(T value) throws NodeNotFoundException{
        BinaryTreeNode<T> removeNode = this.getPostOrder(value);

        if(removeNode == root && removeNode.getLeft() == null && removeNode.getRight() == null){
            root = null;
            return;
        }

        if(removeNode.getLeft() == null && removeNode != root){
            BinaryTreeNode<T> parent = this.getParentNode(value);
            if(parent.getLeft() == removeNode){
                parent.setLeft(null);
            }
            if(parent.getRight() == removeNode){
                parent.setRight(null);
            }
            return;
        }

        // Do the shifting of the value to the left.
        if(removeNode.getLeft() != null){
            BinaryTreeNode<T> leftNode = removeNode.getLeft();
            removeNode.setValue(leftNode.getValue());
            removeNode.setLeft(leftNode.getLeft());
            removeNode = removeNode.getLeft();
        } else if(removeNode.getRight() != null){
            BinaryTreeNode<T> rightNode = removeNode.getRight();
            removeNode.setValue(rightNode.getValue());
            removeNode.setRight(rightNode.getRight());
            removeNode = removeNode.getLeft();
        }
    }

        public static void main(String[] args) throws Exception{
        BinarySearchTree<Contact> tree = new BinarySearchTree<>();
        tree.add(new Contact("Test1", "Test1", "Test1"));
        tree.add(new Contact("Test2", "Test2", "Test2"));
        tree.add(new Contact("Test3", "Test3", "Test3"));
        tree.remove(new Contact("Test1", "Test1", "Test1"));
        for(Contact x : tree.getAllInOrder()){
            System.out.println(x);
        }
    }
}
