/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawwingpaper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Erwin14k
 */
public class CitiesList {
    private CitiesListNode first;
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(City city){
        CitiesListNode node = new CitiesListNode(city);
        node.next=first;
        first=node; 
    }
    public void finalInsert(City city){
        CitiesListNode node= new CitiesListNode(city);
        if(first==null){
        first=node;
        }else{
            CitiesListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
    }
    
    public void cityNewRoute(int id,Route route){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            CitiesListNode temp = first;
            do{
                if(temp.city.getId()==id){
                    temp.city.getRoutesList().finalInsert(route);
                }
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    
    public String generateAdjancecyList() throws IOException{
        String route="Reportes Texto/listaAdyacente.txt";
        String graph="Reportes Img/listaAdyacente.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        String finalText="digraph G{\nnode [shape=box];\n";
        CitiesListNode temp = first;
        String rankSame="{rank=same; ";
        String conections="";
        String nodes="";
        while(temp != null){
            nodes+="N"+temp.hashCode()+"[label=\""+"Ciudad No. \n"+temp.city.getId()+"\"];\n";
            if(!temp.city.getRoutesList().isEmpty()){
                nodes+=temp.city.getRoutesList().collectLinkedList();
                conections+="N"+temp.hashCode()+ " -> ";
                conections+=temp.city.getRoutesList().collectConnections();
                //conections+="start"+ " -> "+"N"+temp.hashCode()+";\n";
            }
            if (temp.next!=null){
                conections+="N"+temp.hashCode()+ " -> "+"N"+temp.next.hashCode()+";\n";
                conections+="N"+temp.next.hashCode()+ " -> "+"N"+temp.hashCode()+";\n";
                rankSame+="N"+temp.hashCode()+",";
            }else{
                rankSame+="N"+temp.hashCode();
            }
            
            temp = temp.next;
            
        }
        rankSame+="};";
        
        
        finalText+=nodes+"\n";
        finalText+=conections+"\n";
        finalText+="start [shape=Mdiamond label=\"Lista De Adyacencia:\n "+""+"\"];";
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
