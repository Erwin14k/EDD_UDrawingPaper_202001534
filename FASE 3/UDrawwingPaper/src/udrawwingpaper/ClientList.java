/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawwingpaper;
import java.io.IOException;
import java.math.BigInteger;
/**
 *
 * @author Erwin14k
 */
public class ClientList {
    private ClientListNode first;
    private int countClients=0;
    public String allCodes;
    
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
                if(temp.client.getDpi().compareTo(dpi)==0){
                    System.out.println(temp.client.getName());
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
    }
 
    public Client returnMeTheClient(BigInteger dpi){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    return temp.client;
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return null;
    }
    
    public String nameByUserName(String userName){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getUserName().equals(userName)){
                    return temp.client.getName();
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    
    public void updateClient(BigInteger dpi,String name, String password){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.setName(name); 
                    temp.client.setPassword(password);
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
      
    }
    
    public String passwordByDpi(BigInteger dpi){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    return temp.client.getPassword();
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    
    public boolean exist(BigInteger dpi){
        if(first == null){
            //System.err.print("La lista se encuentra vacia");
            return true;
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    //System.out.println("Cliente repetido");
                    return false;
                    
                }
                
                temp = temp.next;
            }while(temp != null);
            
        }
        return true;
    }
    
    
  
   
    
    public String[] returnMeClientsCodes() throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
            String temp="";
            return temp.split(";");
        }
        else{
            return clientsCodes(); 
            
        }
     
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
    
    public boolean  loginValidation(String userName, String password){
        if(first == null){
            System.err.println("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getUserName().equals(userName)&& temp.client.getPassword().equals(password)){
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
            
            clientsMatrix = new Object[rows][4];
            ClientListNode aux = first;
            int counter =0;
            do{
                clientsMatrix[counter][0] = aux.client.getDpi();
                clientsMatrix[counter][1] = aux.client.getName();
                clientsMatrix[counter][2] = aux.client.getUserName();
                clientsMatrix[counter][3] = aux.client.getCityId(); 
                counter++;
                aux = aux.next;
            }while(aux != null);

            return clientsMatrix;
        
        }
        clientsMatrix = new Object[rows][4];
        return clientsMatrix;
    }
    
    //Se utiliza para mandarle como parametro a un combobox todos los dpi existentes.
    public String[] clientsCodes(){
        allCodes="";
        ClientListNode temp=first;
        while(temp!=null){
            allCodes+=temp.client.getDpi()+",";
            temp=temp.next;
                
            }
            
        if(allCodes.equals("")){ 
            String empty="";
            empty+="No hay clientes!!,";
            return empty.split(",");
        }
        return allCodes.split(",");
    }
    
}
