
package udrawingpaper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Erwin14k
 */
public class AttendedList {
    private AttendedListNode first;
    private int countClients=0;
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(Client client){
        AttendedListNode node = new AttendedListNode(client);
        node.next=first;
        first=node; 
        countClients++;
    }
    public void finalInsert(Client client){
        AttendedListNode node= new AttendedListNode(client);
        if(first==null){
        first=node;
        }else{
            AttendedListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
        countClients++;
    }
    public void personalizeInsert(int id,Client client){
        AttendedListNode node= new AttendedListNode(client);
        if(first==null){
            first=node;
        }else{
            AttendedListNode pointer=first;
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
            AttendedListNode pointer=first;
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
            AttendedListNode begin=first;
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
            
            AttendedListNode pointer=first;
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
    
    public String graphvizGenerator() throws IOException{
        String route="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Texto\\atentidos.txt";
        String graph="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Img\\ListAtendidos.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        String finalText="digraph G{\nnode [shape=box];\n";
        AttendedListNode temp = first;
        String conections="";
        String nodes="";
        while(temp != null){
            nodes+="N"+temp.hashCode()+"[label=\""+"Cliente con id No. "+temp.client.getId()+"\n"+"Nombre: "+temp.client.getName()+"\n"+"Total imágenes: "+temp.client.getTotalImages()+"\n"+"Total Pasos En Sistema: "+temp.client.getSteps()+"\"];\n";
            if(temp.next != null){
                conections+="N"+temp.next.hashCode()+ " -> "+"N"+temp.hashCode()+";\n";
            }
            temp = temp.next;
        }
        
        finalText+=nodes+"\n";
        
        finalText+="{rank= same;\n"+conections+"\n";
        //finalText+="start"+" -> "+"N"+begin.hashCode()+";\n";
        finalText+="start [shape=Mdiamond label=\"Lista Clientes Atendidos\"];";
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
