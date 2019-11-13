/*
 * Author: Branden Hitt
 * Purpose: Implement a trie structure that stores keywords and user created
 *          identifiers. Example: absolute, abs, ...
 *                          A   B   C  ...    Z   a  ...    z
 *      Switch:  array = { -1, -1, -1, ... , -1,  0, ... , -1}
 *
 *                          0   1   2   3   4   5   6   7   8
 *      Symbol:  array = {  b,  s,  o,  l,  u,  t,  e,  @,  @}
 *      Next  :  array = {   ,   ,  8, ...                   } 
 */
package trie_structure_implementation;

/**
 *
 * @author bhitt
 */
public class Trie_Structure_Implementation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("fatass");
        trie.insert("dumb");
        trie.insert("bitch");
        trie.insert("flowers");
        trie.insert("wrestlers");
        trie.insert("Glow");
        trie.printTrie();
        
    }
    
}
