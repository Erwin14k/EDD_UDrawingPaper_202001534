/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

/**
 *
 * @author Erwin14k
 */
public class BTreeNode {
    Client client;
    BTreeNode next;
    BTreeNode previous;
    BTreeBranch left;
    BTreeBranch right;
    
    public BTreeNode(Client client){
        this.client=client;
        this.next=null;
        this.previous=null;
        this.left=null;
        this.right=null;
    }
    
}
