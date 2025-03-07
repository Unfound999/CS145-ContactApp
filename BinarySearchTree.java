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
    public BinaryTreeNode<T> getNode(T value){
        return getPreOrder(value);
    }

    public BinaryTreeNode<T> getNode(T value, SearchType searchType){
        switch (searchType) {
            case PREORDER:
                return getPreOrder(value);
            case INORDER:
                return getInOrder(value);
            case POSTORDER:
                return getPostOrder(value);
            default:
                return null; // Really, really shouldn't ever happen.
        }
    }
    //Debug: (11) root->left->left-right-left
    /*
     * If the parent's value isn't the value we're looking for, check the left nodes until we hit null.
     * If we still haven't hit the value, go and check each right node, doing the same left node check each time.
     */
    public  BinaryTreeNode<T> getPreOrder(T value){
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

    public BinaryTreeNode<T> getInOrder(T value){
        Stack<BinaryTreeNode<T>> nodeStack = new Stack<>();

        throw new UnsupportedOperationException();
    }

    public BinaryTreeNode<T> getPostOrder(T value){
        BinaryTreeNode<T> parent = root;
        throw new UnsupportedOperationException();
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

    // Remove and replace with the branch from the *right*.
    public void removeNode(T value){
        BinaryTreeNode<T> removeNode = getNode(value);
        while (removeNode != null) {
            BinaryTreeNode<T> leftNode = removeNode.getLeft();
            removeNode.setValue(leftNode.getValue());
            removeNode.setLeft(leftNode.getLeft());
            removeNode = removeNode.getLeft();
        }
    }
}


class DuplicateNodeException extends RuntimeException {}

class NodeNotFoundException extends RuntimeException {}