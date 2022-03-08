/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.math.BigInteger;

/**
 *
 * @author Erwin14k
 */
public class Img {
    private int id;
    private BinarySearchTree tree;
    private BigInteger clientId;

    
    public Img(int id,BinarySearchTree tree,BigInteger clientId){
        this.id = id;
        this.tree=tree;
        this.clientId=clientId;
   
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }

    public BinarySearchTree getTree() {
        return tree;
    }

    public void setTree(BinarySearchTree tree) {
        this.tree = tree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    
}
