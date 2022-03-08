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
public class AlbumsCircularList {
    private AlbumsCircularListNode first;
    
  public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(Album album){
        AlbumsCircularListNode node = new AlbumsCircularListNode(album);
        node.next=first;
        first=node; 
      
    }
    public void finalInsert(Album album){
        AlbumsCircularListNode node= new AlbumsCircularListNode(album);
        if(first==null){
        first=node;
        }else{
            AlbumsCircularListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
       
    }
    
}
