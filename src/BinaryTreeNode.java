/*
 * Authors: Christopher Waschke, Brody Weinkauf, Jackson Jenks
 * Assignment: Week 9 - Binary Search Trees
 * Description: Contact Application Using Binary Search Tree
 */

package src;

/*
 * BinaryTreeNode<T> Class
 * A Generic Class representing a branch in a Binary Tree
 * Has three properties, the value of the node, and the two branches that come off of it.
 */
public class BinaryTreeNode<T extends Comparable> {

    private T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    /*
     * Constructor Method
     * 
     * Creates a tree node with the given value value.
     * Parameters:
     *  value (T) the value of the node.
     */
    public BinaryTreeNode(T value) {
        this.value = value;
    }

    /*
     * T getValue
     * Returns the value of the node.
     */
    public T getValue() {
        return value;
    }

    /*
     * void Method
     * Sets the value of the Node.
     * Parameters:
     *  value (T) the value of the node.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /*
     * BinaryTreeNode<T> Method
     * Returns the left Branch of our node.
     */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /*
     * void Method
     * Sets the Left Branch of our node with a new BinaryTreeNode<T> (of same T)
     * 
     * Parameters:
     *  left (BinaryTreeNode<T>) The new branch we're setting as our left branch.
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /*
     * BinaryTreeNode<T> Method
     * Returns the value of the Right Branch.
     */
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    /*
     * void Method
     * 
     * Sets the Right Branch of our node with a new BinaryTreeNode<T> (of same T)
     * 
     * Parameters:
     *  right (BinaryTreeNode<T>) The new branch we're setting as our right branch.
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }
}
