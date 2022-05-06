/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawwingpaper;

/**
 *
 * @author Erwin14k
 */
public class RoutesList {
    private RoutesListNode first;
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(Route route){
        RoutesListNode node = new RoutesListNode(route);
        node.next=first;
        first=node; 
    }
    public void finalInsert(Route route){
        RoutesListNode node= new RoutesListNode(route);
        if(first==null){
        first=node;
        }else{
            RoutesListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
    }
}
