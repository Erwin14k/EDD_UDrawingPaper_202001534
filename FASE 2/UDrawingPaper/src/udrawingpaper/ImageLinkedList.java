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
public class ImageLinkedList {
    private ImageLinkedListNode first;
    private int countImg=0;
    public String allCodes="";
    
    public boolean isEmpty(){
        return first==null;
    }
    public void insert(Img img){
        ImageLinkedListNode node = new ImageLinkedListNode(img);
        node.next=first;
        first=node; 
        countImg++;
    }
    public void finalInsert(Img img){
        ImageLinkedListNode node= new ImageLinkedListNode(img);
        if(first==null){
        first=node;
        }else{
            ImageLinkedListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
        countImg++;
    }
    
    public String collectLinkedList(){
        String graphText="";
        String conections="";
        ImageLinkedListNode temp=first;
        while(temp!=null){
            graphText+="N"+temp.hashCode()+"[label=\""+"Img No. "+temp.img.getId()+"\"];\n";
            if(temp.next != null){
                conections+="N"+temp.hashCode()+ " -> "+"N"+temp.next.hashCode()+";\n";
            }
            temp=temp.next;
        } 
        return graphText;
        
    }
    public String collectConnections(){
        String graphText="";
        String conections="";
        ImageLinkedListNode temp=first;
        if(first!=null){
           conections+="N"+first.hashCode()+"\n";
        }
        
        while(temp!=null){
            
            graphText+="N"+temp.hashCode()+"[label=\""+"Img No. "+temp.img.getId()+"\"];\n";
            if(temp.next != null){
                conections+="N"+temp.hashCode()+ " -> "+"N"+temp.next.hashCode()+";\n";
                
            }
            temp=temp.next;
        } 
        return conections;
        
    }
    
    //Este método recolecta todos los id´s de las imagenes para mostrarlas dentro de un combobx en la interfaz
    public String[] imgCodes(){
        allCodes="";
        ImageLinkedListNode temp=first;
        while(temp!=null){
            allCodes+=temp.img.getId()+",";
            temp=temp.next;
                
            }
            
        if(allCodes.equals("")){ 
            String empty="";
            empty+="No hay imagenes!!,";
            return empty.split(",");
        }
        return allCodes.split(",");
    }
        
}
