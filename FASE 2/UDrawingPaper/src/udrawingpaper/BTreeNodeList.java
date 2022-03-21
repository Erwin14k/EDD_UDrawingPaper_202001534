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
public class BTreeNodeList {
    BTreeNode head;
    BTreeNode end;
    int size;
    public BTreeNodeList(){
        this.head=null;
        this.end=null;
        this.size=0;
    }
    
    public boolean  insert(BTreeNode newNode){
        if(head==null){
            head=newNode;
            end=newNode;
            size++;
            System.out.println("se inserto: "+newNode.client.getName());
            return true;
        }else{
            if(head==end){
                if(newNode.client.getDpi().compareTo(head.client.getDpi())==-1){
                    newNode.next=head;
                    head.previous=newNode;
                    //Cambiar los punteros en las ramas
                    head.left=newNode.right;
                    head=newNode;
                    size++;
                    System.out.println("se inserto: "+newNode.client.getName());
                    return true;
                }else if(newNode.client.getDpi().compareTo(end.client.getDpi())==1){
                    end.next=newNode;
                    newNode.previous=end;
                    //Cambiar los punteros de las ramas
                    end.right=newNode.left;
                    end=newNode;
                    size++;
                    System.out.println("se inserto: "+newNode.client.getName());
                    return true;
                }else{
                    //Quiere decir que es igual que el primero
                    System.out.println("Dato repetido en Árbol B");
                    System.out.println("se inserto: "+newNode.client.getName()+"REPETIDO");
                    return false;
                }
            }else{
                //Si hay más de un dato
                if(newNode.client.getDpi().compareTo(head.client.getDpi())==-1){
                    //Si el dato es menor que la cabeza
                    newNode.next=head;
                    head.previous=newNode;
                    //Cambian los punteros de la rama
                    head.left=newNode.right;
                    head=newNode;
                    size++;
                    System.out.println("se inserto: "+newNode.client.getName());
                    return true;
                }else if(newNode.client.getDpi().compareTo(end.client.getDpi())==1){
                    end.next=newNode;
                    newNode.previous=end;
                    //Cambiar los punteros de las ramas
                    end.right=newNode.left;
                    end=newNode;
                    size++;
                    System.out.println("se inserto: "+newNode.client.getName());
                    return true;
                }else{
                    BTreeNode temp=head;
                    while(temp!=null){
                        if(newNode.client.getDpi().compareTo(temp.client.getDpi())==-1){
                            newNode.next=temp;
                            newNode.previous=temp.previous;
                            //Cambian punteros de ramas
                            temp.left=newNode.right;
                            temp.previous.right=newNode.left;
                            temp.previous.next=newNode;
                            temp.previous=newNode;
                            size++;
                            System.out.println("se inserto: "+newNode.client.getName());
                            return true;
                        }else if(newNode.client.getDpi().compareTo(temp.client.getDpi())==0){
                            System.out.println("Nodo repetido");
                            System.out.println("se inserto: "+newNode.client.getName()+"REPETIDO");
                            return false;
                        }else{
                            temp=temp.next;
                        }
                    }
                }
                
            }
        }
        System.out.println("se inserto: "+newNode.client.getName()+"NOOOOOOOO");
        return false;
    }

}
