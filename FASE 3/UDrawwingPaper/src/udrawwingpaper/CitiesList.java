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
public class CitiesList {
    private CitiesListNode first;
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(City city){
        CitiesListNode node = new CitiesListNode(city);
        node.next=first;
        first=node; 
    }
    public void finalInsert(City city){
        CitiesListNode node= new CitiesListNode(city);
        if(first==null){
        first=node;
        }else{
            CitiesListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
    }
    
    public void cityNewRoute(int id,Route route){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            CitiesListNode temp = first;
            do{
                if(temp.city.getId()==id){
                    temp.city.getRoutesList().finalInsert(route);
                }
                temp = temp.next;
            }while(temp != null);
        }
    }
}
