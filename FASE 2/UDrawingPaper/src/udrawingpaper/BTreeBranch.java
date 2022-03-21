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
public class BTreeBranch {
    boolean root;
    int max;
    int min;
    int size;
    BTreeNodeList list;
    public BTreeBranch(){
        this.max=4;
        this.min=2;
        this.size=0;
        this.list=new BTreeNodeList();
    }
    
    public  Object insertInBranch(Object node){
        if(list.insert((BTreeNode)node)){
            size=list.size;
            if(size<5){
                System.out.println(this);
                return this;
            }else if(size==5){
                return divide(this);
            }
        }
        return null;
    }
   
    
    public BTreeNode divide(BTreeBranch branch){
        BTreeNode temp=branch.list.head; 
        //Ubicamos la mitad de la rama
        for (int i = 0; i < 2; i++) {
            temp=temp.next;
        }
        BTreeNode first=branch.list.head;
        BTreeNode second=branch.list.head.next;
        BTreeNode third=temp.next;
        BTreeNode fourth=branch.list.end;
        
        first.next=null;
        first.previous=null;
        second.next=null;
        second.previous=null;
        third.next=null;
        third.previous=null;
        fourth.next=null;
        fourth.previous=null;
        temp.next=null;
        temp.previous=null;
        
        //Se crean las nuevas ramas
        BTreeBranch leftBranch=new BTreeBranch();
        leftBranch.insertInBranch(first);
        leftBranch.insertInBranch(second);
        
        BTreeBranch rightBranch=new BTreeBranch();
        rightBranch.insertInBranch(third);
        rightBranch.insertInBranch(fourth);
        
        temp.left=leftBranch;
        temp.right=rightBranch;
        return temp;
    }
    public boolean isLeaf(BTreeBranch branch){
        if(branch.list.head.left==null){
            System.out.println("Es hoja");
            return true;
        }else{
            return false;
        }
        
    }
        
    
   
}
    
    
    /*boolean leaf;
    int counter;
    BTreeNode first;

    public BTreeBranch() {
        this.first = null;
        this.leaf = true;
        this.counter = 0;
    }
    public void insert(BTreeNode newNode){
        if(first==null){
            first=newNode;
            counter++;
        }else{
            BTreeNode temp=first;
            while(temp!=null){
                if(temp.client.getDpi().compareTo(newNode.client.getDpi())==0){
                    System.out.println("Nodo duplicado");
                    break;
                }else{
                    if(temp.client.getDpi().compareTo(newNode.client.getDpi())==1){
                        if(temp==first){
                            temp.previous=newNode;
                            newNode.next=temp;
                            //ramas del nodo
                            temp.left=newNode.right;
                            newNode.right=null;
                            first=newNode;
                            counter++;
                            break;
                            
                        }else{
                            newNode.next=temp;
                            //ramas del nodo                    
                            temp.left=newNode.right;
                            newNode.right=null;
                            newNode.previous=temp.previous;
                            temp.previous.next=newNode;
                            temp.previous=newNode;
                            counter++;
                            break;
                            
                        }
                    }else if (temp.next == null) {//------------->insertar al final
                        temp.next = newNode;
                        newNode.previous = temp;
                        counter++;
                        break;
                    }
                }
                temp=temp.next;
            }
        }
    }*/

