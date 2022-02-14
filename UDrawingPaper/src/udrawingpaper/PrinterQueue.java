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
public class PrinterQueue {
    public PrinterQueueNode begin,end;
    public int size;
    public PrinterQueue(){
        begin=null;
        end=null;
        size=0;
    }
    
    //Método para verificar si está vacía la cola
    public boolean isEmpty(){
        //Con esto valido que la cola no esté vacía, ya que si no cumple la condición, es que está vacía.
        return begin==null;
    }
    public  void insert(Image image){
        PrinterQueueNode newNode = new PrinterQueueNode();
        newNode.image=image;
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
    public void showQueue(){
        PrinterQueueNode temp=begin;
        while(temp!=null){
            System.out.println(temp.image.getIdClient()); 
            temp=temp.next;
        }
        
    
    }
    public void delete(){
        if(begin != null){
            PrinterQueueNode delete=begin;
            begin=begin.next;
            delete.next=null;
            if(begin==null){
                end=null;
            }  
        }
    }
    public Image obtain(){
        if(!isEmpty()){
            Image image=begin.image;
            if(begin == end){
                begin=null;
                end=null;
            }else{
                begin=begin.next;
            }
            return image;
        }else{
            return null;
        }
    }
    
    public int idClientFirstImageInQueue(){
        if (begin!=null){
            return begin.image.getIdClient();
        }
        return -1;
    }
    public Image FirstImageInQueue(){
        return begin.image;
    }
    
    public String collectPrinterQueue(){
        String graphText="";
        String conections="";
        PrinterQueueNode temp=begin;
        while(temp!=null){
            graphText+="N"+temp.hashCode()+"[label=\""+"Imagen Del cliente: "+temp.image.getIdClient()+" "+temp.image.getFormat()+"\"];\n";
            if(temp.next != null){
                conections+="N"+temp.next.hashCode()+ " -> "+"N"+temp.hashCode()+";\n";
            }
            temp=temp.next;
        } 
        return graphText;
        
    }
    public String collectConections(){
        String graphText="";
        String conections="";
        PrinterQueueNode temp=begin;
        if(begin!=null){
           conections+="N"+begin.hashCode()+"\n";
        }
        
        while(temp!=null){
            
            graphText+="N"+temp.hashCode()+"[label=\""+"Cliente "+temp.image.getIdClient()+" "+temp.image.getFormat()+"\"];\n";
            if(temp.next != null){
                conections+="N"+temp.next.hashCode()+ " -> "+"N"+temp.hashCode()+";\n";
            }
            temp=temp.next;
        } 
        return conections;
        
    }

    /*public void updateStateFree(int id){
        PrinterQueueNode temp=begin;
        while(temp!=null){
            if (temp.printer.getId()==id){
                temp.printer.setState("libre");
                temp.printer.setSecondsToBeFree(0);
                break;
            }else{
                temp=temp.next;
            } 
            
        }
        
    }
    public void updateStatebusyColor(int id){
        PrinterQueueNode temp=begin;
        while(temp!=null){
            if (temp.printer.getId()==id){
                temp.printer.setState("ocupada");
                temp.printer.setSecondsToBeFree(2);
                break;
            }else{
                temp=temp.next;
            } 
            
        }
        
    }
    public void updateStatebusyBw(int id){
        PrinterQueueNode temp=begin;
        while(temp!=null){
            if (temp.printer.getId()==id){
                temp.printer.setState("ocupada");
                temp.printer.setSecondsToBeFree(1);
                break;
            }else{
                temp=temp.next;
            } 
            
        }
        
    }*/
    
    
    
}
