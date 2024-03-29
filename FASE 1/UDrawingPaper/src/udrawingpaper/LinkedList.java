
package udrawingpaper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Erwin14k
 */

public class LinkedList {
    private LinkedListNode first;
    private int countWindows=0; 
    UDrawingPaper uDrawinPaperHandler= new UDrawingPaper();
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(Window window){
        LinkedListNode node = new LinkedListNode(window);
        node.next=first;
        first=node; 
        countWindows++;
    }
    public void finalInsert(Window window){
        LinkedListNode node= new LinkedListNode(window);
        if(first==null){
        first=node;
        }else{
            LinkedListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
        countWindows++;
    }
    public void personalizeInsert(int id,Window window){
        LinkedListNode node= new LinkedListNode(window);
        if(first==null){
            first=node;
        }else{
            LinkedListNode pointer=first;
            int counter =0;
            while(counter< id && pointer.next !=null){
                pointer=pointer.next;
                counter++;
            }
            node.next=pointer.next;
            pointer.next=node;
        }
        countWindows++;
    }
    
    public Window obtainData(int id){
        if (first==null){
            return null;
        }else{
            LinkedListNode pointer=first;
            int counter=0;
            while(counter < id && pointer.next != null){
                pointer = pointer.next;
                counter++;
            }
            if (counter !=id){
                return null;
            }else {
                return pointer.window;
            }
        }
    }
    public void deleteFirst(){
        if(first!=null){
            LinkedListNode begin=first;
            first=first.next;
            begin.next=null;
            countWindows--;
        }    
    }
    
    public void deleteAtTheEnd(){
        if(first!=null){
            if(first.next==null){
                first=null;
                countWindows--;
           }else{
            
            LinkedListNode pointer=first;
            while(pointer.next.next!=null){
                pointer=pointer.next;
            }
            pointer.next=null;
            countWindows--;
            }  
        }
    }
    
    public int countWindows(){
        return countWindows;
    }
    public void travelList(){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            LinkedListNode temp = first;
            do{
                System.out.println("----------------");
                System.out.println(temp.window.getId());
                System.out.println(temp.window.getState());
                System.out.println(temp.window.getStacklist().showUltimate());
                System.out.println("----------------");
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    public void travelListById(int id){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            LinkedListNode temp = first;
            do{
                if(temp.window.getId()==id){
                    temp.window.getStacklist().showData();
                    
                }
                
                temp = temp.next;
            }while(temp != null);
        }
    }
    
    public void giveImagesToPrinters(int idClient, int idWindow){
        if(first == null){
            System.err.print("La lista se encuentra vacia");
        }
        else{
            LinkedListNode temp = first;
            do{
                //System.out.println("Cliente: "+id);
                if(temp.window.getId()==idWindow){
                temp.window.getStacklist().moveImages(idClient);
                temp.window.getStacklist().empyStack();
                }
                temp = temp.next;
            }while(temp != null);
        }
    }
    public int isAWindowOpen(){
        LinkedListNode temp = first;
        do{
            if(temp.window.getState().equals("libre")){
                return temp.window.getId();
            }else{
                temp = temp.next;
            }
            }while(temp != null);
        return -1;
    
    }
    
    public void uptadeBusyWindowState(int id){
        LinkedListNode temp=first;
        while(temp!=null){
            if (temp.window.getId()==id){
                temp.window.setState("ocupada");
                break;
            }else{
                temp=temp.next;
            } 
            
        }
        
    }
    
    public void uptadeFreeWindowState(int id){
        LinkedListNode temp=first;
        while(temp!=null){
            if (temp.window.getId()==id){
                temp.window.setState("libre");
                break;
            }else{
                temp=temp.next;
            } 
            
        }
        
    }
    
    public void uptadeWindowStackList(int id, Image image){
        LinkedListNode temp=first;
        while(temp!=null){
            if (temp.window.getId()==id){
                temp.window.getStacklist().insert(image);
                break;
            }else{
                temp=temp.next;
            } 
            
        }
        
    }
    
    public String graphvizGenerator() throws IOException{
        String route="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Texto\\listapilasventanillas.txt";
        String graph="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Img\\ListaPilasVentanillas.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        String finalText="digraph G{\nnode [shape=box];\n";
        LinkedListNode temp = first;
        String rankSame="{rank=same; ";
        String conections="";
        String nodes="";
        while(temp != null){
            nodes+="N"+temp.hashCode()+"[label=\""+"Ventanilla No."+temp.window.getId()+"\"];\n";
            if(!temp.window.getStacklist().isEmpty()){
            nodes+=temp.window.getStacklist().collectStackList();
            conections+="N"+temp.hashCode()+ " -> ";
            conections+=temp.window.getStacklist().collectCollections();
            conections+="start"+ " -> "+"N"+temp.hashCode()+";\n";
            }
            if (temp.next!=null){
                conections+="N"+temp.hashCode()+ " -> "+"N"+temp.next.hashCode()+";\n";
                rankSame+="N"+temp.hashCode()+",";
            }else{
                rankSame+="N"+temp.hashCode();
            }
            
            temp = temp.next;
            
        }
        rankSame+="};";
        
        
        finalText+=nodes+"\n";
        finalText+=conections+"\n";
        finalText+="start [shape=Mdiamond label=\"Lista De Pilas De Ventanillas\"];";
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
