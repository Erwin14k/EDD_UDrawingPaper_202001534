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
    UDrawingPaper uDrawinPaperHandler= new UDrawingPaper();
    
    
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
    public void deleteById(int id){
    ClientWindowNode temp = first;
    ClientWindowNode previous = null;
    while (temp!=null && temp.client.getId() != id){
        previous=temp;
        temp = temp.next;
        
    }
    if (previous==null){
        first = temp.next;
        temp.next = null; 
    }
      
    else if(temp !=null){
        previous.next = temp.next;
        temp.next = null;
        
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
    
    public void deleteOfTheWindow(){
        if(first == null){
        }
        else{
            ClientWindowNode temp = first;
            do{
                if(temp.client.getDeleteWindow().equals("si")){
                    deleteById(temp.client.getId());
                }
                temp = temp.next;
            }while(temp != null);
        }
    }
    public int checkIntruders(){
        int intrudersCounter=0;
        if(first == null){
        }
        else{
            ClientWindowNode temp = first;
            do{
                if(temp.client.getDeleteWindow().equals("si")){
                    intrudersCounter++;
                }
                temp = temp.next;
            }while(temp != null);
        }
        return intrudersCounter;
    }
    
    // Método que sirve para que los clientes en ventanilla den una imagen por paso
    public void giveImageToTheWindow(int idClient){
        //Pirmero se verifica que si hayan clientes en ventanilla
        if(first == null){
            System.out.println("En este paso no se hacen entregas en ventanilla, ya que no hay clientes en ellas.");
        }
        else{
            ClientWindowNode temp = first;
            //Ciclo while, para recorrer la lista de los clientes en ventanilla
            while(temp != null){
                //Se verifica que el cliente si tenga imagenes por entregar y que su estado sea estar en ventanilla.
                if (temp.client.getTotalImages()!=temp.client.getImgCounter() && temp.client.getState().equals("ventanilla")){
                    //Este if es solo para validar que el cliente que acaba de entrar a ventanilla en el paso actual no pueda entregar imagen.
                    if(temp.client.getId()!=idClient){
                        //Se verifica que tenga imagenes de color por entregar
                        if (temp.client.getMissingColor()>0){
                            Image newImageColor=new Image(temp.client.getId(), "color", "ventanilla");
                            temp.client.setMissingColor(temp.client.getMissingColor()-1);
                            uDrawinPaperHandler.linkedListHandler.uptadeWindowStackList(temp.client.getIdWindow(), newImageColor);
                        //Si no tiene se verifica que tenga imagenes en blanco y negro
                        }else if(temp.client.getMissingBw()>0){
                            Image newImageBw=new Image(temp.client.getId(), "bw", "ventanilla");
                            temp.client.setMissingBw(temp.client.getMissingBw()-1);
                            uDrawinPaperHandler.linkedListHandler.uptadeWindowStackList(temp.client.getIdWindow(), newImageBw);
                        }
                        //Se hace la entrega la imagen en ventanilla
                        System.out.println("El cliente con el id: "+temp.client.getId()+" Entrega Imagen en ventanilla No."+temp.client.getIdWindow());
                        temp.client.setSteps(temp.client.getSteps()+1);
                        temp.client.setImgCounter(temp.client.getImgCounter()+1);
                        
                    }
                }else{
                    //Este else sirve para ver que clientes ya cumplieron con su entrega de imágnes, los cuales son trasladados a lista de espera.
                    System.out.println("El cliente con el id: "+temp.client.getId()+" Sale de Ventanilla, entra en lista de espera!!");
                    temp.client.setState("listaDeEspera");
                    temp.client.setSteps(temp.client.getSteps()+1);
                    uDrawinPaperHandler.waitingListHandler.finalInsert(temp.client);
                    uDrawinPaperHandler.linkedListHandler.uptadeFreeWindowState(temp.client.getIdWindow());
                    uDrawinPaperHandler.linkedListHandler.giveImagesToPrinters(temp.client.getId(),temp.client.getIdWindow());
                    temp.client.setDeleteWindow("si");
                    //deleteById(temp.client.getId());
                    
                }
                
                temp = temp.next;
            }
        }
    }
    //Este método es lo mismo que el de arriba, con la única diferencia de que aquí no se valida lo del cliente que acaba de entrar.
    public void giveImageToTheWindow2(){
        if(first == null){
            System.out.println("En este paso no se hacen entregas en ventanilla, ya que no hay clientes en ellas.");
        }
        else{
            ClientWindowNode temp = first;
            while(temp != null){
                if (temp.client.getTotalImages()!=temp.client.getImgCounter() && temp.client.getState().equals("ventanilla") ){
                    if (temp.client.getMissingColor()>0){
                        Image newImageColor=new Image(temp.client.getId(), "color", "ventanilla");
                        temp.client.setMissingColor(temp.client.getMissingColor()-1);
                        uDrawinPaperHandler.linkedListHandler.uptadeWindowStackList(temp.client.getIdWindow(), newImageColor);
                        
                    }else if(temp.client.getMissingBw()>0){
                        Image newImageBw=new Image(temp.client.getId(), "bw", "ventanilla");
                        temp.client.setMissingBw(temp.client.getMissingBw()-1);
                        uDrawinPaperHandler.linkedListHandler.uptadeWindowStackList(temp.client.getIdWindow(), newImageBw);
                    }
                    
                    System.out.println("El cliente con el id: "+temp.client.getId()+" Entrega Imagen en ventanilla No."+temp.client.getIdWindow());
                    temp.client.setSteps(temp.client.getSteps()+1);
                    temp.client.setImgCounter(temp.client.getImgCounter()+1);
                    
                    
                    
                }else{
                    System.out.println("El cliente con el id: "+temp.client.getId()+" Fue atendido, entra en lista de espera!!");
                    temp.client.setState("listaDeEspera");
                    temp.client.setSteps(temp.client.getSteps()+1);
                    uDrawinPaperHandler.waitingListHandler.finalInsert(temp.client);
                    uDrawinPaperHandler.linkedListHandler.uptadeFreeWindowState(temp.client.getIdWindow());
                    uDrawinPaperHandler.linkedListHandler.giveImagesToPrinters(temp.client.getId(),temp.client.getIdWindow());
                    temp.client.setDeleteWindow("si");
                    //deleteById(temp.client.getId());
                    
                    
                }
                temp = temp.next;
            }
        }
    }
    
   
    
    
}
