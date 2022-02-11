
package udrawingpaper;

/**
 *
 * @author Erwin14k
 */
public class ImageLinkedList {
    
    private ImageNode first;
    private int countImages=0;
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(Image image){
        ImageNode node = new ImageNode(image);
        node.next=first;
        first=node; 
        countImages++;
    }
    public void finalInsert(Image image){
        ImageNode node= new ImageNode(image);
        if(first==null){
        first=node;
        }else{
            ImageNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
        countImages++;
    }
    public void personalizeInsert(int id,Image image){
        ImageNode node= new ImageNode(image);
        if(first==null){
            first=node;
        }else{
            ImageNode pointer=first;
            int counter =0;
            while(counter< id && pointer.next !=null){
                pointer=pointer.next;
                counter++;
            }
            node.next=pointer.next;
            pointer.next=node;
        }
        countImages++;
    }
    
    public Image obtainData(int id){
        if (first==null){
            return null;
        }else{
            ImageNode pointer=first;
            int counter=0;
            while(counter < id && pointer.next != null){
                pointer = pointer.next;
                counter++;
            }
            if (counter !=id){
                return null;
            }else {
                return pointer.image;
            }
        }
    }
    public void deleteFirst(){
        if(first!=null){
            ImageNode begin=first;
            first=first.next;
            begin.next=null;
            countImages--;
        }    
    }
    
    public void deleteAtTheEnd(){
        if(first!=null){
            if(first.next==null){
                first=null;
                countImages--;
           }else{
            
            ImageNode pointer=first;
            while(pointer.next.next!=null){
                pointer=pointer.next;
            }
            pointer.next=null;
            countImages--;
            }  
        }
    }
    
    public int countImages(){
        return countImages;
    }
    
    public String collectLinkedList(){
        String graphText="";
        String conections="";
        ImageNode temp=first;
        while(temp!=null){
            graphText+="N"+temp.hashCode()+"[label=\""+"Cliente "+temp.image.getIdClient()+" "+temp.image.getFormat()+"\"];\n";
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
        ImageNode temp=first;
        if(first!=null){
           conections+="N"+first.hashCode()+"\n";
        }
        
        while(temp!=null){
            
            graphText+="N"+temp.hashCode()+"[label=\""+"Cliente "+temp.image.getIdClient()+" "+temp.image.getFormat()+"\"];\n";
            if(temp.next != null){
                conections+="N"+temp.hashCode()+ " -> "+"N"+temp.next.hashCode()+";\n";
                
            }
            temp=temp.next;
        } 
        return conections;
        
    }
    
}
