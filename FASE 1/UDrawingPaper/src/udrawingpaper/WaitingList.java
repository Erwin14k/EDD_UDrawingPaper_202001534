/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Erwin14k
 */
public class WaitingList {
    private WaitingNode first;
    private int countClients=0;
    UDrawingPaper uDrawinPaperHandler= new UDrawingPaper();
    
    public boolean isEmpty(){
        return first==null;
    }
    public void deleteById(int id){
    WaitingNode temp = first;
    WaitingNode previous = null;
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
    
    public void insert(Client client){
        WaitingNode node = new WaitingNode(client);
        node.next=first;
        first=node; 
        countClients++;
    }
    public void finalInsert(Client client){
        WaitingNode node= new WaitingNode(client);
        if(first==null){
        first=node;
        }else{
            WaitingNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
        countClients++;
    }
    public void personalizeInsert(int id,Client client){
        WaitingNode node= new WaitingNode(client);
        if(first==null){
            first=node;
        }else{
            WaitingNode pointer=first;
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
            WaitingNode pointer=first;
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
            WaitingNode begin=first;
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
            
            WaitingNode pointer=first;
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
    
    public void updateWaitingStatus(int idClient, Image image){
        if(first == null){
            //System.err.print("La lista se encuentra vacia");
        }
        else{
            WaitingNode temp = first;
            do{
                if(temp.client.getId()==idClient){
                    temp.client.getImageList().finalInsert(image);
                    temp.client.setWaitingCounter(temp.client.getWaitingCounter()-1);
                    if(temp.client.getWaitingCounter()==0){
                        temp.client.setSteps(uDrawinPaperHandler.stepsCounter-temp.client.getInitialStep());
                        uDrawinPaperHandler.attendedListHandler.finalInsert(temp.client);
                        uDrawinPaperHandler.bwDataList.bwDataSorting(temp.client);
                        uDrawinPaperHandler.colorDataList.colorDataSorting(temp.client);
                        uDrawinPaperHandler.stepsDataList.stepsDataSorting(temp.client);
                        System.out.println("El cliente con id: "+temp.client.getId()+" Fue atendido, sale del sistema!");
                        deleteById(temp.client.getId());
                        
                    }
                }
                temp = temp.next;
            }while(temp != null);
        }
    }
    public void updateStepsStatus(){
        if(first != null){
            //System.err.print("La lista se encuentra vacia");
            WaitingNode temp = first;
        
            
            do{
                temp.client.setSteps(temp.client.getSteps()+1);
                temp = temp.next;
            }while(temp != null);
        }
        }
    
    
    public void validateClientsToPrint(){
        if(first == null){
            //System.err.print("La lista se encuentra vacia");
        }
        else{
            WaitingNode temp = first;
            do{
                temp.client.setCanYouPrint(true);
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    public boolean  checkTheValidation(int id){
        if(first == null){
            //System.err.print("La lista se encuentra vacia");
        }
        else{
            WaitingNode temp = first;
            do{
                if(temp.client.getId()==id && temp.client.isCanYouPrint()){
                    return true;
                }
                
                temp = temp.next;
            }while(temp != null);
        }
        return false;
    }
    
    public String graphvizGenerator() throws IOException{
        String route="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Texto\\listaespera.txt";
        String graph="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Img\\ListaEspera.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        String finalText="digraph G{\nnode [shape=box];\n";
        WaitingNode temp = first;
        String rankSame="{rank=same; ";
        String conections="";
        String nodes="";
        while(temp != null){
            nodes+="N"+temp.hashCode()+"[label=\""+"Cliente Id No."+temp.client.getId()+"\"];\n";
            if(!temp.client.getImageList().isEmpty()){
                nodes+=temp.client.getImageList().collectLinkedList();
                conections+="N"+temp.hashCode()+ " -> ";
                conections+=temp.client.getImageList().collectConnections();
                //conections+="start"+ " -> "+"N"+temp.hashCode()+";\n";
            }
            if (temp.next!=null){
                conections+="N"+temp.hashCode()+ " -> "+"N"+temp.next.hashCode()+";\n";
                conections+="N"+temp.next.hashCode()+ " -> "+"N"+temp.hashCode()+";\n";
                rankSame+="N"+temp.hashCode()+",";
            }else{
                conections+="N"+temp.hashCode()+ " -> "+"N"+first.hashCode()+";\n";
                conections+="N"+first.hashCode()+ " -> "+"N"+temp.hashCode()+";\n";
                rankSame+="N"+temp.hashCode();
            }
            
            temp = temp.next;
            
        }
        rankSame+="};";
        
        
        finalText+=nodes+"\n";
        finalText+=conections+"\n";
        finalText+="start [shape=Mdiamond label=\"Lista De Espera De Clientes\"];";
        finalText+=rankSame;
        finalText+="}\n}";
        FileWriter fw = new FileWriter(route);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(finalText);
        bw.close();
        
        String[] cmd = new String[5];
        cmd[0] = pathString;
        cmd[1] = tParam;
        cmd[2] = route;
        cmd[3] = tOParam;
        cmd[4] = graph;

        Runtime rt = Runtime.getRuntime();

        rt.exec( cmd );
        
        return finalText;
    }
    
}
