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
    
    public void deleteClientImg(BigInteger dpi,int id) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.getImgList().deleteImg(id); 
                    temp.client.getAvlTree().removeNode(temp.client.getAvlTree().returnMeTheRoot(), id);
                    temp.client.getAlbumList().deleteImgOfTheAlbum(id);
                    temp.client.getAlbumList().generatePersonalizeAlbum(temp.client.getName());
                    returnMeMyAvl(dpi); 
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
    
    public boolean imageExist(BigInteger dpi,int id){
        if(first == null){
            //System.err.print("La lista se encuentra vacia");
            return true;
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    return temp.client.getImgList().exist(id);
                    
                }
                
                temp = temp.next;
            }while(temp != null);
            
        }
        return true;
      
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
                    //temp.client.getAbbTree().printTheTravels();
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
    public String inOrderLimitedOfMyAbb(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    //System.out.println("hola bro");
                    temp.client.getAbbTree().inOrderLimited(temp.client.getAbbTree().returnMeTheRoot());
                    return temp.client.getAbbTree().inOrderLimited;
                   
                    
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
    
    public String preOrderLimitedOfMyAbb(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    //System.out.println("hola bro");
                    temp.client.getAbbTree().preOrderLimited(temp.client.getAbbTree().returnMeTheRoot());
                    return temp.client.getAbbTree().preOrderLimited;
                   
                    
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
    
    public String postOrderLimitedOfMyAbb(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    //System.out.println("hola bro");
                    temp.client.getAbbTree().postOrderLimited(temp.client.getAbbTree().returnMeTheRoot());
                    return temp.client.getAbbTree().postOrderLimited;
                   
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return "";
    }
    
    public String leafsOfMyAbb(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    //System.out.println("hola bro");
                    temp.client.getAbbTree().collectTheLeafs(temp.client.getAbbTree().returnMeTheRoot());
                    return temp.client.getAbbTree().leafs;
                   
                    
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
    
    public void initializeMyTravelersLimited(BigInteger dpi,int limit) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    temp.client.getAbbTree().initialzeTheTravelersLimited(limit);
                   
                    
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
                    temp.client.getImgList().imageDataSorting(img); 
                    temp.client.setImgCounter(temp.client.getImgCounter()+1); 
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    public Object[][] getMyImgTop(BigInteger dpi){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                    return temp.client.getImgList().returnImgData();
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        Object[][] imgTopData;
        imgTopData = new Object[0][3];
        return imgTopData;
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
    public String[] returnMyLayersCodes(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                   temp.client.getAbbTree().collectCodes(); 
                   return temp.client.getAbbTree().allCodes.split(",");
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return null;
    }
    public String[] returnMyImgsCodes(BigInteger dpi) throws IOException{
        if(first == null){
            System.err.print("La lista se encuentra vacia");
            
        }
        else{
            ClientListNode temp = first;
            do{
                if(temp.client.getDpi().compareTo(dpi)==0){
                   temp.client.getImgList().imgCodes();
                   return temp.client.getImgList().allCodes.split(",");
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return null;
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
    /*Método que sirve para meter todo en una tabla en la interfaz
    Por eso se usan los [], ya que eso nos pide el objeto tabla
    como parámtetro.
    */
    
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
