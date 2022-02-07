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
public class ClientWindow {
    private ClientWindowNode first;
    private int countClients=0; 
    
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(Client client){
        ClientWindowNode node = new ClientWindowNode(client);
        node.next=first;
        first=node; 
        countClients++;
    }
    public void finalInsert(Client client){
        ClientWindowNode node= new ClientWindowNode(client);
        if(first==null){
        first=node;
        }else{
            ClientWindowNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
        countClients++;
    }
    public void personalizeInsert(int id,Client client){
        ClientWindowNode node= new ClientWindowNode(client);
        if(first==null){
            first=node;
        }else{
            ClientWindowNode pointer=first;
            int counter =0;
            while(counter< id && pointer.next !=null){
                pointer=pointer.next;
                counter++;
            }
            node.next=pointer.next;
            pointer.next=node;
        }
        countClients++;
    }
    
    public Client obtainData(int id){
        if (first==null){
            return null;
        }else{
            ClientWindowNode pointer=first;
            int counter=0;
            while(counter < id && pointer.next != null){
                pointer = pointer.next;
                counter++;
            }
            if (counter !=id){
                return null;
            }else {
                return pointer.client;
            }
        }
    }
    public void deleteFirst(){
        if(first!=null){
            ClientWindowNode begin=first;
            first=first.next;
            begin.next=null;
            countClients--;
        }    
    }
    
    public void deleteAtTheEnd(){
        if(first!=null){
            if(first.next==null){
                first=null;
                countClients--;
           }else{
            
            ClientWindowNode pointer=first;
            while(pointer.next.next!=null){
                pointer=pointer.next;
            }
            pointer.next=null;
            countClients--;
            }  
        }
    }
    
    public int countClients(){
        return countClients;
    }
    public void travelList(){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientWindowNode temp = first;
            do{
                System.out.println("----------------");
                System.out.println(temp.client.getId());
                System.out.println(temp.client.getState());
                System.out.println("----------------");
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    
}
