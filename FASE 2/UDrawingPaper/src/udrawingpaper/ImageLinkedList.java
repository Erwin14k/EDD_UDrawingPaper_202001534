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
public class ImageLinkedList {
    private ImageLinkedListNode first;
    private int countImg=0;
    
    public boolean isEmpty(){
        return first==null;
    }
    public void insert(Img img){
        ImageLinkedListNode node = new ImageLinkedListNode(img);
        node.next=first;
        first=node; 
        countImg++;
    }
    public void finalInsert(Img img){
        ImageLinkedListNode node= new ImageLinkedListNode(img);
        if(first==null){
        first=node;
        }else{
            ImageLinkedListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
        countImg++;
    }
}
