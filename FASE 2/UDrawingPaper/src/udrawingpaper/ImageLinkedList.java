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
    
    public void imageDataSorting(Img img){
        ImageLinkedListNode newNode=new ImageLinkedListNode(img);
        ImageLinkedListNode temp1;
        ImageLinkedListNode temp2;
        if(first==null){
            first=newNode;
            newNode.next=null;
            
        }else{
            temp1=first;
            while(temp1!=null){
                temp2=temp1.next;
                if(newNode.img.getLayersCounter()>temp1.img.getLayersCounter()){
                    newNode.next=first;
                    first=newNode;
                    break;
                }else{
                    if(newNode.img.getLayersCounter()<temp1.img.getLayersCounter()&&temp2==null){
                        temp1.next=newNode;
                        newNode.next=null;
                        break;
                    }else{
                        if(temp1.img.getLayersCounter()>newNode.img.getLayersCounter()&& temp2.img.getLayersCounter()<=newNode.img.getLayersCounter()){
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
    
    public boolean exist(int id){
        if(first == null){
            //System.err.print("La lista se encuentra vacia");
            return true;
        }
        else{
            ImageLinkedListNode temp = first;
            do{
                if(temp.img.getId()==id){
                    //System.out.println("Imagen repetido");
                    return false;
                    
                }
                
                temp = temp.next;
            }while(temp != null);
            
        }
        return true;
    }
    
    public void  deleteImg(int id){
        ImageLinkedListNode temp=first;
        ImageLinkedListNode previous = null;
        while(temp!=null && temp.img.getId()!=id){
            previous=temp;
            temp=temp.next;
        }
        if(previous==null){
            first=temp.next;
            temp.next=null;
        }else if(temp!=null){
            previous.next=temp.next;
            temp.next=null;
        }
   

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
    
    /*Método que sirve para meter todo en una tabla en la interfaz
    Por eso se usan los [], ya que eso nos pide el objeto tabla
    como parámtetro.
    */
    
    public  Object[][] returnImgData(){
        int rows = 0;
        Object[][] imgTopData;
        if(first!=null){
            
        
            ImageLinkedListNode temp = first;
            do{
                rows++;
                temp = temp.next;
            }while(temp != null);
            
            imgTopData = new Object[rows][3];
            ImageLinkedListNode aux = first;
            int counter =0;
            do{
                imgTopData[counter][0] = counter+1+".";
                imgTopData[counter][1] = aux.img.getId();
                imgTopData[counter][2] = aux.img.getLayersCounter();
                counter++;
                aux = aux.next;
            }while(aux != null);

            return imgTopData;
        
        }
        imgTopData = new Object[rows][3];
        return imgTopData;
    }
        
}
