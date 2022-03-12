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
public class Position {
    private PositionNode first;
    public void finalInsert(int row, int column, String color){
        PositionNode node= new PositionNode(row,column,color);
        if(first==null){
        first=node;
        }else{
            PositionNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
        
    }
}
