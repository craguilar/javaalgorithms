/**
 * Given a tree with an arbitrary number of children, sort the nodes by the order in which they would "fall" off a tree.
 * At every step, all the nodes which have no children are deleted from the tree.
 * This process is repeated until only the root node remains, at which point it falls off the tree as well.
 * For example, given the following tree:
 *          A
 *         / \
 *        B   C    
 *       / \   
 *      D   E    
 *           \
 *            I
 *                    
 *       A 
 *      / \
 *     B    H              
 *    / \   
 *   C    D                       Map<Integer,Set<TreeNodes>> perLevelNode 
 *  / \    \                          Z,G,H,I,X,D,C,B,A   
 * G   X   Z
 *     I                    
 * 
 * At the first step, leaves "D", "E", and "C" fall off the tree. That leaves us with the following:
 * 
 *  DFS = {(C,D,E), (B), (A)]
 * 
 * At the second step, only leaf "B" falls off the tree.
 * At the third step, the root "A" falls off the tree.
 *
 * Therefore, for the example tree this method should return: [(C,D,E), (B), (A)]
 *
 */

 /*
  *       A 3
 *      / \
 *     B 2   H  0            
 *    / \   
 *   C 1  D 0                      Map<Integer,Set<TreeNodes>> perLevelNode 
 *  / \                                  
 * G - H
      /
    I 
 */