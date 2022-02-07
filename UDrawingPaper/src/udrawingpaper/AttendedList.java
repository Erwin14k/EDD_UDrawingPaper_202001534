
package udrawingpaper;

/**
 *
 * @author Erwin14k
 */
public class AttendedList {
    private AttendedListNode first;
    private int countClients=0;
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void insert(Client client){
        AttendedListNode node = new AttendedListNode(client);
        node.next=first;
        first=node; 
        countClients++;
    }
    public void finalInsert(Client client){
        AttendedListNode node= new AttendedListNode(client);
        if(first==null){
        first=node;
        }else{
            AttendedListNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
                }
            pointer.next=node;
        }
        countClients++;
    }
    public void personalizeInsert(int id,Client client){
        AttendedListNode node= new AttendedListNode(client);
        if(first==null){
            first=node;
        }else{
            AttendedListNode pointer=first;
            int counter =0;
            while(counter< id && pointer.next !=null){
                pointer=pointer.next;
                counter++;
            }
            node.next=pointer.next;
            pointer.next=node;
        }
        countClients++;
    }
    
    public Client obtainData(int id){
        if (first==null){
            return null;
        }else{
            AttendedListNode pointer=first;
            int counter=0;
            while(counter < id && pointer.next != null){
                pointer = pointer.next;
                counter++;
            }
            if (counter !=id){
                return null;
            }else {
                return pointer.client;
            }
        }
    }
    public void deleteFirst(){
        if(first!=null){
            AttendedListNode begin=first;
            first=first.next;
            begin.next=null;
            countClients--;
        }    
    }
    
    public void deleteAtTheEnd(){
        if(first!=null){
            if(first.next==null){
                first=null;
                countClients--;
           }else{
            
            AttendedListNode pointer=first;
            while(pointer.next.next!=null){
                pointer=pointer.next;
            }
            pointer.next=null;
            countClients--;
            }  
        }
    }
    
    public int countClients(){
        return countClients;
    }
    
}
