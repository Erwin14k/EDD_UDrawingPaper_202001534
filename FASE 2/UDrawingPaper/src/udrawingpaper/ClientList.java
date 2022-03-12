/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.io.IOException;
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
                if(temp.client.getDpi().compareTo(dpi)==0){
                    System.out.println(temp.client.getName());
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    public void clientNewLayer(BigInteger dpi,Layer layer){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.getAbbTree().insert(layer);
                    temp.client.setLayersCounter(temp.client.getLayersCounter()+1);
                    
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
    
    public void graphClientTree(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.getAbbTree().generateBstTreeGraph();
                    
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
    public String personalizeAbbRoute(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    return temp.client.getAbbTree().returnMeTheAbbRoute(temp.client.getName());
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    public String personalizeAlbumsListRoute(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    return temp.client.getAlbumList().returnMeTheAlbumsListRoute(temp.client.getName());
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    
    public String personalizeAvlRoute(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    return temp.client.getAbbTree().returnMeTheAvlRoute(temp.client.getName());
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    
    public String depthOfMyAbb(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    //System.out.println("hola bro");
                    temp.client.getAbbTree().depth="La profundidad del árbol es: "+temp.client.getAbbTree().depth(temp.client.getAbbTree().returnMeTheRoot());
                    temp.client.getAbbTree().printTheTravels();
                    return temp.client.getAbbTree().depth;
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    
    public String inOrderOfMyAbb(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    //System.out.println("hola bro");
                    temp.client.getAbbTree().inOrder(temp.client.getAbbTree().returnMeTheRoot());
                    return temp.client.getAbbTree().inOrder;
                   
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    public String preOrderOfMyAbb(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    //System.out.println("hola bro");
                    temp.client.getAbbTree().preOrder(temp.client.getAbbTree().returnMeTheRoot());
                    return temp.client.getAbbTree().preOrder;
                   
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    
    public String postOrderOfMyAbb(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    //System.out.println("hola bro");
                    temp.client.getAbbTree().postOrder(temp.client.getAbbTree().returnMeTheRoot());
                    return temp.client.getAbbTree().postOrder;
                   
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    
    public void initializeMyTravelers(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.getAbbTree().initialzeTheTravelers();
                   
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
    }
        
    
    public void addImage(BigInteger dpi,Img img){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.getAvlTree().insert(img); 
                    temp.client.setImgCounter(temp.client.getImgCounter()+1); 
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    public void addAlbum(BigInteger dpi,Album album){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.getAlbumList().finalInsert(album); 
                    temp.client.setAlbumsCounter(temp.client.getAlbumsCounter()+1); 
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    public void returnMeMyAbb(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.getAbbTree().generatePersonalizeBstTreeGraph(temp.client.getName()); 
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
    }
    public void returnMeMyAlbumsList(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.getAlbumList().generatePersonalizeAlbum(temp.client.getName()); 
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    public void returnMeMyAvl(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.getAvlTree().generatePersonalizeAVLTreeGraph(temp.client.getName()); 
                    
                }
                
                temp = temp.next;
            }while(temp != null);
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
