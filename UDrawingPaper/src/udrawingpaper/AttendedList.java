
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
    
    public void bwDataSorting(Client client){
        AttendedListNode newNode=new AttendedListNode(client);
        AttendedListNode temp1;
        AttendedListNode temp2;
        if(first==null){
            first=newNode;
            newNode.next=null;
            
        }else{
            temp1=first;
            while(temp1!=null){
                temp2=temp1.next;
                if(newNode.client.getBwImageCounter()>temp1.client.getBwImageCounter()){
                    newNode.next=first;
                    first=newNode;
                    break;
                }else{
                    if(newNode.client.getBwImageCounter()<temp1.client.getBwImageCounter()&&temp2==null){
                        temp1.next=newNode;
                        newNode.next=null;
                        break;
                    }else{
                        if(temp1.client.getBwImageCounter()>newNode.client.getBwImageCounter()&& temp2.client.getBwImageCounter()<=newNode.client.getBwImageCounter()){
                            temp1.next=newNode;
                            newNode.next=temp2;
                            break;
                        }else{
                            temp1=temp1.next;
                        }
                        
                    }
                }
            }
        }
        
    }
    
    public void colorDataSorting(Client client){
        AttendedListNode newNode=new AttendedListNode(client);
        AttendedListNode temp1;
        AttendedListNode temp2;
        if(first==null){
            first=newNode;
            newNode.next=null;
            
        }else{
            temp1=first;
            while(temp1!=null){
                temp2=temp1.next;
                if(newNode.client.getColorImageCounter()>temp1.client.getColorImageCounter()){
                    newNode.next=first;
                    first=newNode;
                    break;
                }else{
                    if(newNode.client.getColorImageCounter()<temp1.client.getColorImageCounter()&&temp2==null){
                        temp1.next=newNode;
                        newNode.next=null;
                        break;
                    }else{
                        if(temp1.client.getColorImageCounter()>newNode.client.getColorImageCounter()&& temp2.client.getColorImageCounter()<=newNode.client.getColorImageCounter()){
                            temp1.next=newNode;
                            newNode.next=temp2;
                            break;
                        }else{
                            temp1=temp1.next;
                        }
                        
                    }
                }
            }
        }
        
    }
    public void stepsDataSorting(Client client){
        AttendedListNode newNode=new AttendedListNode(client);
        AttendedListNode temp1;
        AttendedListNode temp2;
        if(first==null){
            first=newNode;
            newNode.next=null;
            
        }else{
            temp1=first;
            while(temp1!=null){
                temp2=temp1.next;
                if(newNode.client.getSteps()>temp1.client.getSteps()){
                    newNode.next=first;
                    first=newNode;
                    break;
                }else{
                    if(newNode.client.getSteps()<temp1.client.getSteps()&&temp2==null){
                        temp1.next=newNode;
                        newNode.next=null;
                        break;
                    }else{
                        if(temp1.client.getSteps()>newNode.client.getSteps()&& temp2.client.getSteps()<=newNode.client.getSteps()){
                            temp1.next=newNode;
                            newNode.next=temp2;
                            break;
                        }else{
                            temp1=temp1.next;
                        }
                        
                    }
                }
            }
        }
        
    }
    public void topBwReport(){
        int positionCounter=1;
        if(first == null){
            System.err.print("El reporte de top clientes con más imágenes en blanco y negro no se puede realizar debdo a que la lista de clientes atendidos está vacía!!");
        }
        else{
            AttendedListNode temp = first;
            System.out.println("---------------Top Clientes Bw Images-----------------");
            do{
                
                System.out.println(positionCounter+". Id: "+temp.client.getId()+"--"+temp.client.getName()+"-- Imágenes bw: "+temp.client.getBwImageCounter());
                positionCounter++;
                temp = temp.next;
            }while(temp != null);
            
        }
        System.out.println("******************************************************");
    }
    public void topColorReport(){
        int positionCounter=1;
        if(first == null){
            System.err.print("El reporte de top clientes con más imágenes a color no se puede realizar debdo a que la lista de clientes atendidos está vacía!!");
        }
        else{
            AttendedListNode temp = first;
            System.out.println("---------------Top Clientes Color Images-----------------");
            do{
                
                System.out.println(positionCounter+". Id: "+temp.client.getId()+"--"+temp.client.getName()+"-- Imágenes a Color: "+temp.client.getColorImageCounter());
                positionCounter++;
                temp = temp.next;
            }while(temp != null);
            
        }
        System.out.println("*********************************************************");
    }
    public void topStepsReport(){
        int positionCounter=1;
        if(first == null){
            System.err.print("El reporte de top clientes con más pasos en el sistema no se puede realizar debdo a que la lista de clientes atendidos está vacía!!");
        }
        else{
            AttendedListNode temp = first;
            System.out.println("---------------Top Clientes Pasos En Sistema-----------------");
            do{
                
                System.out.println(positionCounter+". Id: "+temp.client.getId()+"--"+temp.client.getName()+"-- Pasos en sistema: "+temp.client.getSteps());
                positionCounter++;
                temp = temp.next;
            }while(temp != null);
            
        }
        
        System.out.println("*************************************************************");
    }
    
    public boolean specificClientReport(int id){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            AttendedListNode temp = first;
            do{
                if(temp.client.getId()==id){
                    System.out.println("---------------Reporte Cliente Específico-----------------");
                    System.out.println("Id: "+temp.client.getId());
                    System.out.println("Nombre: "+temp.client.getName());
                    System.out.println("Imágenes Bw Impresas: "+temp.client.getBwImageCounter());
                    System.out.println("Imágenes A Color Impresas: "+temp.client.getColorImageCounter());
                    System.out.println("Pasos En Sistema: "+temp.client.getSteps());
                    System.out.println("***********************************************************");
                    return true;
                }
                temp = temp.next;
            }while(temp != null);
        }
        System.out.println("El cliente buscado no existe, o no ha sido atendido");
        return false;
    }
}
