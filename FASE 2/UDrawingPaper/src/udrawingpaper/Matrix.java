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
public class Matrix {
    private MatrixNode root = new MatrixNode(-6,-6,"white");
    
    
    //Buscamos la columna
    public MatrixNode searchColumn(int x){
        MatrixNode temp=root;
        while (temp!=null){
            if(temp.x==x){
                return temp;
            }
            temp=temp.next;
        }
        return null;
    }
    //Buscamos la fila
    public MatrixNode searchRow(int y){
        MatrixNode temp=root;
        while (temp!=null){
            if(temp.y==y){
                return temp;
            }
            temp=temp.down;
        }
        return null;
    }
   //Creación de nueva cabecera columna
    public MatrixNode columnCreation(int x){
        MatrixNode columnNode=root;
        MatrixNode newNode= new MatrixNode(x,-1,"white");
        MatrixNode column=orderInsertInColumn(newNode,columnNode);
        return column;
    }
    //Inseción ordenada de las columnas
    public MatrixNode orderInsertInColumn(MatrixNode newNode,MatrixNode columnHead){
        MatrixNode temp=columnHead;
        boolean added=false;
        while(true){
            if(newNode.x==temp.x){
                temp.y=newNode.y;
                temp.color=newNode.color;
                return temp;
            }else if(temp.x>newNode.x){
                added=true;
                break;
            }
            if(temp.next!=null){
                temp=temp.next;
            }else{
                added=false;
                break;
            }
        }
        if(added){
            newNode.next=temp;
            temp.previous.next=newNode;
            newNode.previous=temp.previous;
            temp.previous=newNode;
        }else{
            temp.next=newNode;
            newNode.previous=temp;
        }
        return newNode;
    }
    
    //Creación de nueva cabecera fila
    public MatrixNode rowCreation(int y){
        MatrixNode rowNode=root;
        MatrixNode newNode= new MatrixNode(-1,y,"white");
        MatrixNode column=orderInsertInRow(newNode,rowNode);
        return column;
        
    }
    //Inseción ordenada de las filas
    public MatrixNode orderInsertInRow(MatrixNode newNode, MatrixNode rowHead){
        MatrixNode temp=rowHead;
        boolean added=false;
        while(true){
            if(newNode.y==temp.y){
                temp.x=newNode.x;
                temp.color=newNode.color;
                return temp;
            }else if(temp.y>newNode.y){
                added=true;
                break;
                
            }
            if (temp.down!=null){
                temp=temp.down;
            }else{
                added=false;
                break;
            }
        }
        if(added){
            newNode.down=temp;
            temp.up.down=newNode;
            newNode.up=temp.up;
            temp.up=newNode;
        }else{
            temp.down=newNode;
            newNode.up=temp;
        }
        return newNode;
    }
    //Método de la inserción a la matriz
    public String insert(int x ,int y,String color){
        MatrixNode newNode=new MatrixNode(x,y,color);
        MatrixNode columnNode=searchColumn(x);
        MatrixNode rowNode= searchRow(y);
        //Caso 1: No existe fila ni columna
        if(rowNode==null && columnNode==null){
            System.out.println("Caso 1");
            //Creación de cabeceras
            columnNode=columnCreation(x);
            rowNode=rowCreation(y);
            //Inserción a la matriz
            newNode=orderInsertInColumn(newNode, rowNode);
            newNode=orderInsertInRow(newNode, columnNode);
            return "";
        }
        //Caso 2: No existe la fila, pero si la columna
        else if(rowNode==null && columnNode !=null){
            System.out.println("Caso 2");
            //Creamos las cabeceras
            rowNode=rowCreation(y);
            //Se agrega el contenido.
            newNode=orderInsertInColumn(newNode, rowNode);
            newNode=orderInsertInRow(newNode, columnNode);
            return "";
        }
        //Caso 3: Existe fila pero no columna
        else if(rowNode!=null && columnNode==null){
            System.out.println("Caso 3");
            //Creamos las cabeceras
            columnNode=columnCreation(x);
            //Agregamos el contenido
            newNode=orderInsertInColumn(newNode, rowNode);
            newNode=orderInsertInRow(newNode, columnNode);
            return "";
            
        }
        //caso 4: Si existe fila y columna
        else {
            System.out.println("caso 4");
            //Creamos las cabeceras
            //No hay jeje
            //Agregamos el contenido
            newNode=orderInsertInColumn(newNode, rowNode);
            newNode=orderInsertInRow(newNode, columnNode);
            return "";
            
        }
        
    }
   
     
   public void printData(){
       MatrixNode temp=root;
       MatrixNode temp2;
       String tempText="";
       while(temp!=null){
           tempText="";
           temp2=temp;
           while(temp2!=null){
               tempText+="{"+temp2.x+","+temp2.y+"}";
               temp2=temp2.next;
           }
           System.out.println(tempText);
           temp=temp.down;
           
       }
   }
   
   public String GraphMatrix(){
       MatrixNode temp=root;
       MatrixNode temp2;
       String graphText="";
       graphText+="digraph G {\n" +
        "node [shape=plaintext];\n" +
        "label=\"Imagen Completa\";\n"+
               "some_node [\nlabel=<\n"+
        "<table border=\"0\" cellborder=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\">\n";
       while(temp!=null){
           temp2=temp;
           graphText+="<tr>\n";
           while(temp2!=null){
               graphText+="<td bgcolor=\""+temp2.color+"\" width=\"1\" height=\"1\">"+temp2.x+","+temp2.y+"</td>\n";
               temp2=temp2.next;
           }
           graphText+="</tr>\n";
           temp=temp.down;
           
       }
       graphText+="</table>>\n];\n}";
      
       return graphText;
   }
   


}
