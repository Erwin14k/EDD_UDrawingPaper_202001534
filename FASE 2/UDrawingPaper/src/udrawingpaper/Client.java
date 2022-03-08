
package udrawingpaper;

import java.math.BigInteger;

/**
 *
 * @author Erwin14k
 */
public class Client {
    private BigInteger dpi;
    private String name;
    private String password;
    private SelfBalancingTree avlTree;
    private int albumsCounter;
    private int imgCounter;
    private int layersCounter;
    private BinarySearchTree abbTree;
    public Client(BigInteger  dpi, String name, String password,SelfBalancingTree avlTree,int albumsCounter,int imgCounter,int layersCounter,BinarySearchTree abbTree){
        this.dpi = dpi;
        this.name = name;
        this.password= password;  
        this.avlTree=avlTree;
        this.albumsCounter=albumsCounter;
        this.imgCounter=imgCounter;
        this.layersCounter=layersCounter;
        this.abbTree=abbTree;
    }

    public BigInteger getDpi() {
        return dpi;
    }

    public SelfBalancingTree getAvlTree() {
        return avlTree;
    }

    public void setAvlTree(SelfBalancingTree avlTree) {
        this.avlTree = avlTree;
    }

    public void setDpi(BigInteger dpi) {
        this.dpi = dpi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAlbumsCounter() {
        return albumsCounter;
    }

    public void setAlbumsCounter(int albumsCounter) {
        this.albumsCounter = albumsCounter;
    }

    public int getImgCounter() {
        return imgCounter;
    }

    public void setImgCounter(int imgCounter) {
        this.imgCounter = imgCounter;
    }

    public BinarySearchTree getAbbTree() {
        return abbTree;
    }

    public void setAbbTree(BinarySearchTree abbTree) {
        this.abbTree = abbTree;
    }

    public int getLayersCounter() {
        return layersCounter;
    }

    public void setLayersCounter(int layersCounter) {
        this.layersCounter = layersCounter;
    }
    
    
    
}
