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
public class Album {
    private String name;
    private ImageLinkedList imgList;
    private int imgCounter;
    
    public Album(String name, ImageLinkedList imgList,int imgCounter){
        this.name=name;
        this.imgList=imgList;
        this.imgCounter=imgCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageLinkedList getImgList() {
        return imgList;
    }

    public void setImgList(ImageLinkedList imgList) {
        this.imgList = imgList;
    }

    public int getImgCounter() {
        return imgCounter;
    }

    public void setImgCounter(int imgCounter) {
        this.imgCounter = imgCounter;
    }
    
    
}
