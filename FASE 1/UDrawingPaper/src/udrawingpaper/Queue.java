
package udrawingpaper;
/**
 *
 * @author Erwin14k
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;


public class Queue {
    private QueueNode begin,end;
    public int size;
    public Queue(){
        begin=null;
        end=null;
        size=0;
    }
    
    //Método para verificar si está vacía la cola
    public boolean isEmpty(){
        //Con esto valido que la cola no esté vacía, ya que si no cumple la condición, es que está vacía.
        return begin==null;
    }
    public  void insert(Client client){
        QueueNode newNode = new QueueNode();
        newNode.clientC=client;
        newNode.next=null;
        if (isEmpty()){
            begin= newNode;
            end=newNode;
             
        }else{
            end.next=newNode;
            end=newNode;
        }
        end=newNode;
        size+=1;  
    }
    public void delete(){
        if(begin != null){
            QueueNode delete=begin;
            begin=begin.next;
            delete.next=null;
            if(begin==null){
                end=null;
            }  
        }
    }
    public Client obtain(){
        if(!isEmpty()){
            Client client=begin.clientC;
            if(begin == end){
                begin=null;
                end=null;
            }else{
                begin=begin.next;
            }
            return client;
        }else{
            return null;
        }
    }
    
    public String beginQueue(){
        return begin.clientC.getName();
    }
    
    public int queueSize(){
        //System.out.println("gggg");
        return size;
    }
    public void showQueue(){
        QueueNode temp=begin;
        while(temp!=null){
            System.out.println(temp.clientC.getName()); 
            temp=temp.next;
        }
        
    
    }
    
    public void updateClientWindow(int id,int idWindow){
        QueueNode temp=begin;
        while(temp!=null){
            if (temp.clientC.getId()==id){
                temp.clientC.setIdWindow(idWindow);
                temp.clientC.setSteps(temp.clientC.getSteps()+1);
                temp.clientC.setState("ventanilla");
                break;
            }else{
                temp=temp.next;
            } 
            
        }
        
    }
    public Client firstInQueue(){
        if (begin!=null){
            return begin.clientC;
            
        }else{
            return null;
        }
        
    }
    public int firstIdInQueue(){
        if(begin!=null){
        return begin.clientC.getId();
    }else{
            return -1;
        }
    }
    
    public String graphvizGenerator() throws IOException{
        String route="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Texto\\colarecepcion.txt";
        String graph="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Img\\ColaRecepción.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        String finalText="digraph G{\nnode [shape=box];\n";
        QueueNode temp = begin;
        String conections="";
        String nodes="";
        while(temp != null){
            nodes+="N"+temp.hashCode()+"[label=\""+"Cliente con id No. "+temp.clientC.getId()+"\n"+temp.clientC.getName()+"\"];\n";
            if(temp.next != null){
                conections+="N"+temp.next.hashCode()+ " -> "+"N"+temp.hashCode()+";\n";
            }
            temp = temp.next;
        }
        
        finalText+=nodes+"\n";
        
        finalText+="{rank= same;\n"+conections+"\n";
        
        finalText+="start [shape=Mdiamond label=\"Cola De Recepción\"];";
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

