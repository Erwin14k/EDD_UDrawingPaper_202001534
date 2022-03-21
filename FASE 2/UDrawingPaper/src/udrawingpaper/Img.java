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
    private Matrix matrix;
    private int layersCounter;
    private int albumId;

    
    public Img(int id,BinarySearchTree tree,BigInteger clientId,Matrix matrix,int layersCounter,int albumId){
        this.id = id;
        this.tree=tree;
        this.clientId=clientId;
        this.matrix=matrix;
        this.layersCounter=layersCounter;
        this.albumId=albumId;
   
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

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public int getLayersCounter() {
        return layersCounter;
    }

    public void setLayersCounter(int layersCounter) {
        this.layersCounter = layersCounter;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
    

    
}
