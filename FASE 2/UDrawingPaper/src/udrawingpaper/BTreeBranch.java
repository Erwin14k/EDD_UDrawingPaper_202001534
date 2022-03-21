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
}
