/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.math.BigInteger;

/**
 *
 * @author Erwin14k
 */
public class ClientList {
    
    private ClientListNode first;
    private int countClients=0;
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(Client client){
        ClientListNode node = new ClientListNode(client);
        node.next=first;
        first=node; 
        countClients++;
    }
    public void finalInsert(Client client){
        ClientListNode node= new ClientListNode(client);
        if(first==null){
        first=node;
        }else{
            ClientListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
        countClients++;
    }
    
    public void travelListById(BigInteger dpi){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi()==dpi){
                    System.out.println(temp.client.getName());
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    public String nameByDpi(BigInteger dpi){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    return temp.client.getName();
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    
    public void travel(){
        if(first == null){
            System.err.println("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                System.out.println(temp.client.getName());
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    public boolean  loginValidation(BigInteger dpi, String password){
        if(first == null){
            System.err.println("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if((temp.client.getDpi().compareTo(dpi)==0)&&(temp.client.getPassword().equals(password))){
                    return true;
                    
                }else{
                    temp = temp.next;
                }
                
            }while(temp != null);
        }
        return false;
    }
    
    public  Object[][] returnClientsData(){
        int rows = 0;
        Object[][] clientsMatrix;
        if(first!=null){
            
        
            ClientListNode temp = first;
            do{
                rows++;
                temp = temp.next;
            }while(temp != null);
            
            clientsMatrix = new Object[rows][6];
            ClientListNode aux = first;
            int counter =0;
            do{
                clientsMatrix[counter][0] = aux.client.getDpi();
                clientsMatrix[counter][1] = aux.client.getName();
                clientsMatrix[counter][2] = aux.client.getPassword();
                clientsMatrix[counter][3] = aux.client.getAlbumsCounter();
                clientsMatrix[counter][4] = aux.client.getImgCounter();
                clientsMatrix[counter][5] = aux.client.getLayersCounter();
                counter++;
                aux = aux.next;
            }while(aux != null);

            return clientsMatrix;
        
        }
        clientsMatrix = new Object[rows][6];
        return clientsMatrix;
    }
        
    
}
