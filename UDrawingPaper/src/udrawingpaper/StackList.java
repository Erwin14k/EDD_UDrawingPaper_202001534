
package udrawingpaper;

/**
 *
 * @author Erwin14k
 */
public class StackList {
    private StackListNode ultimate;
    int size=0;
    UDrawingPaper uDrawinPaperHandler= new UDrawingPaper();
    public StackList(){
        ultimate=null;
        size=0;
    }
    
    public boolean isEmpty(){
        return ultimate==null;
    }
    
    public void insert(Image image){
        StackListNode newNode=new StackListNode(image);
        newNode.next=ultimate;
        ultimate=newNode;
        size++;
    }
    
    public Image deleteNode(){
        Image temp = ultimate.image;
        ultimate=ultimate.next;
        size--;
        return temp;
        
        
    }
    
    public Image showUltimate(){
        if (ultimate!=null){
            return ultimate.image;
            
        }
        else{
            return null;
        }
        
    }
    
    public int stackListSize(){
        return size;
    }
    
    public void empyStack(){
        while(!isEmpty()){
            deleteNode();
        }
    }
        
    public void showData(){
        StackListNode temp=ultimate;
        while(temp!=null){
            System.out.println(temp.image.getIdClient()+"--"+temp.image.getFormat()); 
            temp=temp.next;
        } 
    }
    
    public void showtackById(int id){
        StackListNode temp=ultimate;
        while(temp!=null){
            System.out.println(temp.image.getIdClient()+"--"+temp.image.getFormat()); 
            temp=temp.next;
        } 
    }
    //Método para que la ventanilla traslade todas las imágenes de un cliente a la cola de impresión.
    public void moveImages(int id){
        StackListNode temp=ultimate;
        while(temp!=null){
            if(temp.image.getIdClient()==id){
                if(temp.image.getFormat().equals("color")){
                    temp.image.setState("impresion");
                    uDrawinPaperHandler.colorPrinter.getPrinterQueue().insert(temp.image);
                }else if(temp.image.getFormat().equals("bw")){
                    temp.image.setState("impresion");
                    uDrawinPaperHandler.bwPrinter.getPrinterQueue().insert(temp.image);
                }
            }
            temp=temp.next;
        }
        
    }
    public String collectStackList(){
        String graphText="";
        String conections="";
        StackListNode temp=ultimate;
        while(temp!=null){
            graphText+="N"+temp.hashCode()+"[label=\""+"Cliente "+temp.image.getIdClient()+" "+temp.image.getFormat()+"\"];\n";
            if(temp.next != null){
                conections+="N"+temp.hashCode()+ " -> "+"N"+temp.next.hashCode()+";\n";
            }
            temp=temp.next;
        } 
        return graphText;
        
    }
    public String collectCollections(){
        String graphText="";
        String conections="";
        StackListNode temp=ultimate;
        if(ultimate!=null){
           conections+="N"+ultimate.hashCode()+"\n";
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
    
    

