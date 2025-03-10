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

import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> {

    enum SearchType{
        PREORDER,
        INORDER,
        POSTORDER;
    }

    private BinaryTreeNode<T> root;

    // Initialize with no root value.
    public BinarySearchTree() {
        root = null;
    }

    // Initialize with a starting value.
    public BinarySearchTree(T value) {
        root = new BinaryTreeNode<>(value);
    }

    
    /*
     * Void Method
     * Adds a node to the tree, at the proper location. Sets to root if root doesn't already exist.
     */
    public void add(T newData){
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
     */
    public T get(T value){
        return getPreOrder(value).getValue();
    }

    public T get(T value, SearchType searchType){
        switch (searchType) {
            case PREORDER:
                return this.getPreOrder(value).getValue();
            case INORDER:
                return this.getInOrder(value).getValue();
            case POSTORDER:
                return this.getPostOrder(value).getValue();
            default:
                return null; // Really, really shouldn't ever happen.
        }
    }
    //Debug: (11) root->left->left-right-left
    /*
     * If the parent's value isn't the value we're looking for, check the left nodes until we hit null.
     * If we still haven't hit the value, go and check each right node, doing the same left node check each time.
     */
    private  BinaryTreeNode<T> getPreOrder(T value){
        Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();
        nodeStack.push(root);
        while(!nodeStack.empty()){
            BinaryTreeNode<T> currNode = nodeStack.pop();
            if(currNode != null){
                if(currNode.getValue().equals(value)){
                    return currNode;
                }
            }
            if(currNode.getRight() != null){
                nodeStack.push(currNode.getRight());
            }
            if(currNode.getLeft() != null){
                nodeStack.push(currNode.getLeft());
            }
        }
        throw new NodeNotFoundException();
    }

    private BinaryTreeNode<T> getInOrder(T value){
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

    private BinaryTreeNode<T> getPostOrder(T value){
        Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();
        Stack<BinaryTreeNode<T>> parentStack = new Stack<>();
        BinaryTreeNode<T> currNode = root;

        // Okay! This is crappy! But it works!
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
                    currNode = currNode.getLeft(); // If I didn't include this, currNode won't ever equal null, and we'll never meet leave conditions. TODO Fix it.
                }
            }

            currNode = nodeStack.pop();
            if(currNode.getValue() == value) {
                return currNode;
            }
            currNode = null; // Same as above, we need to get into the loop without a value in the stack, and this one works. TODO Fix this as well.
        }
        throw new NodeNotFoundException();
    }


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
        throw new NodeNotFoundException();
    }

    public BinaryTreeNode<T> getParentNode(T value){
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

    // Remove and replace with the branch from the *left*.
    public void remove(T value){
        BinaryTreeNode<T> removeNode = this.getPostOrder(value);
        if(removeNode.getLeft() == null){
            BinaryTreeNode<T> parent = this.getParentNode(value);
            if(parent.getLeft() == removeNode){
                parent.setLeft(null);
            }
            if(parent.getRight() == removeNode){
                parent.setRight(null);
            }
            return;
        }
        while (removeNode != null) {
            BinaryTreeNode<T> leftNode = removeNode.getLeft();
            removeNode.setValue(leftNode.getValue());
            removeNode.setLeft(leftNode.getLeft());
            removeNode = removeNode.getLeft();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(11);
        tree.add(7);
        tree.add(9);
        tree.add(5);
        tree.add(15);
        tree.add(13);
        tree.add(17);
        tree.add(12);
        tree.add(14);
        tree.add(18);
        tree.add(16);
        tree.add(3);
        tree.add(6);
        tree.add(8);
        tree.add(10);

        tree.remove(16);

        Integer x = tree.get(12, SearchType.POSTORDER);
        System.out.println(x);
    }
}


class DuplicateNodeException extends RuntimeException {}

class NodeNotFoundException extends RuntimeException {}