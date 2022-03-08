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
    
    public Album(String name, ImageLinkedList imgList){
        this.name=name;
        this.imgList=imgList;
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
    
    
}
