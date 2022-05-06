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
    
    public String collectLinkedList(){
        String graphText="";
        String conections="";
        RoutesListNode temp=first;
        while(temp!=null){
            graphText+="N"+temp.hashCode()+"[label=\""+"Ciduad No. "+temp.route.getEnd()+"\"];\n";
            if(temp.next != null){
                conections+="N"+temp.hashCode()+ " -> "+"N"+temp.next.hashCode()+";\n";
            }
            temp=temp.next;
        } 
        return graphText;
        
    }
    public String collectConnections(){
        String graphText="";
        String conections="";
        RoutesListNode temp=first;
        if(first!=null){
           conections+="N"+first.hashCode()+"\n";
        }
        
        while(temp!=null){
            
            graphText+="N"+temp.hashCode()+"[label=\""+"Ciudad No. "+temp.route.getEnd()+"\"];\n";
            if(temp.next != null){
                conections+="N"+temp.hashCode()+ " -> "+"N"+temp.next.hashCode()+";\n";
                
            }
            temp=temp.next;
        } 
        return conections;
        
    }
}
