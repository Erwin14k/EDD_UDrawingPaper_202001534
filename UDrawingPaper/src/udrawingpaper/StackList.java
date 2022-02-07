
package udrawingpaper;

/**
 *
 * @author Erwin14k
 */
public class StackList {
    private StackListNode ultimate;
    int size=0;
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
}
    
    

