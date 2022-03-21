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
            return true;
        }else{
            
        }
        
    }
    /*
    insertar(nuevo){
        if(this.head==null){
            this.head=nuevo
            this.end=nuevo;
            this.size++;
            return true;
        }else{// esto valida si solo se tiene un dato en el arbol
            if(this.head == this.end){ 
                if(nuevo.data.id < this.head.data.id){
                    nuevo.siguiente = this.head;
                    this.head.prev = nuevo;
                    //cambiar punteros de paginas
                    this.head.izquierda = nuevo.der;

                    this.head = nuevo;
                    this.size++;
                    return true;
                }else if(nuevo.data.id> this.end.data.id){
                    this.end.siguiente = nuevo;
                    nuevo.prev = this.end;
                    //cambiar punteros de paginas
                    this.end.der = nuevo.izquierda;

                    this.end = nuevo;
                    this.size++;
                    return true;
                }else{ // el dato es igual al primero
                    console.log("Ya existe un dato con ese valor en la lista");
                    return false;
                }
            }else{ //ahora bien si se tiene mas de un dato
                if(nuevo.data.id < this.head.data.id){
                    //si el dato es menor que mi cabeza
                    nuevo.siguiente = this.head;
                    this.head.prev = nuevo;
                    //se cambian los punteros de mi pagina
                    this.head.izquierda = nuevo.derecha;

                    this.head = nuevo;
                    this.size++;
                    return true;
                }else if(nuevo.data.id> this.end.data.id){
                    this.end.siguiente = nuevo;
                    nuevo.prev = this.end;
                    //cambiar punteros de paginas
                    this.end.derecha = nuevo.izquierda;

                    this.end = nuevo;
                    this.size++;
                    return true;
                }else{
                    let aux = this.head;
                    while(aux != null){
                        if(nuevo.data.id < aux.data.id){
                            nuevo.siguiente = aux;
                            nuevo.prev = aux.prev;
                            // se cambian los punteros de las paginas
                            aux.izquierda= nuevo.derecha;
                            aux.prev.derecha = nuevo.izquierda;
                            
                            aux.prev.siguiente = nuevo;
                            aux.prev = nuevo;
                            this.size++;
                            return true;
                        }else if(nuevo.data.id == aux.data.id){
                            console.log("Ya existe un dato con ese valor en la lista");
                            return false;
                        }else{
                            aux = aux.siguiente;
                        }
                    }
                }
            }
        }
    }
}*/
}
