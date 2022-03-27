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
public class AlbumsCircularList {
    private AlbumsCircularListNode first;
    
  public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(Album album){
        AlbumsCircularListNode node = new AlbumsCircularListNode(album);
        node.next=first;
        first=node; 
      
    }
    public void finalInsert(Album album){
        AlbumsCircularListNode node= new AlbumsCircularListNode(album);
        if(first==null){
        first=node;
        }else{
            AlbumsCircularListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
       
    }
    public String returnMeTheAlbumsListRoute(String name) throws IOException{
        
        String graph="Reportes Img/album"+name+".png";
        
        return graph;
        
    }
    
    public String generatePersonalizeAlbum(String name) throws IOException{
        String route="Reportes Texto/album"+name+".txt";
        String graph="Reportes Img/album"+name+".png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        String finalText="digraph G{\nnode [shape=box];\n";
        AlbumsCircularListNode temp = first;
        String rankSame="{rank=same; ";
        String conections="";
        String nodes="";
        while(temp != null){
            nodes+="N"+temp.hashCode()+"[label=\""+"Nombre Del Album: \n"+temp.album.getName()+"\"];\n";
            if(!temp.album.getImgList().isEmpty()){
                nodes+=temp.album.getImgList().collectLinkedList();
                conections+="N"+temp.hashCode()+ " -> ";
                conections+=temp.album.getImgList().collectConnections();
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
        finalText+="start [shape=Mdiamond label=\"Lista De Álbumes Del Cliente:\n "+name+"\"];";
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
    
    
    /*public void generatePersonalizeAlbum2(String name) throws IOException{
        String route="C:\\Users\\Erwin14k\\Desktop\\matriz.png";
        String graph="C:\\Users\\Erwin14k\\Desktop\\matrizzzzz.txt";
        String tParam = "-Ttxt";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        String[] cmd = new String[5];
        cmd[0] = pathString;
        cmd[1] = tParam;
        cmd[2] = route;
        cmd[3] = tOParam;
        cmd[4] = graph;

        Runtime rt = Runtime.getRuntime();

        rt.exec( cmd );
        
    }*/
    
    
}
