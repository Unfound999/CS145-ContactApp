/*
 * Authors: Christopher Waschke, Brody Weinkauf, Jackson Jenks
 * Assignment: Week 9 - Binary Search Trees
 * Description: Contact Application Using Binary Search Tree
 */

package src;

/*
 * NodeNotFoundException Class
 * Throws when a Node isn't found on the Binary Tree.
 */
public class NodeNotFoundException extends Exception {
    NodeNotFoundException(){
        super("Node was unable to be found.");
    }
}
