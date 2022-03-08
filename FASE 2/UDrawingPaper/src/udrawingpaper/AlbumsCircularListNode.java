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
public class AlbumsCircularListNode {
    public Album album;
    public AlbumsCircularListNode next;
    public AlbumsCircularListNode previous;
    public AlbumsCircularListNode(Album album){
        this.album=album;
    }
}
