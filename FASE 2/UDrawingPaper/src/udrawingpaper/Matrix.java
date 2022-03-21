/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



/**
 *
 * @author Erwin14k
 */
public class Matrix {
    private MatrixNode root = new MatrixNode(-6,-6,"white",-1,-1);
    private String graphText;
    
    
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
        MatrixNode newNode= new MatrixNode(x,-1,"white",-1,-1);
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
        MatrixNode newNode= new MatrixNode(-1,y,"white",-1,-1);
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
        MatrixNode newNode=new MatrixNode(x,y,color,-1,-1);
        MatrixNode columnNode=searchColumn(x);
        MatrixNode rowNode= searchRow(y);
        //Caso 1: No existe fila ni columna
        if(rowNode==null && columnNode==null){
            //System.out.println("Caso 1");
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
            //System.out.println("Caso 2");
            //Creamos las cabeceras
            rowNode=rowCreation(y);
            //Se agrega el contenido.
            newNode=orderInsertInColumn(newNode, rowNode);
            newNode=orderInsertInRow(newNode, columnNode);
            return "";
        }
        //Caso 3: Existe fila pero no columna
        else if(rowNode!=null && columnNode==null){
            //System.out.println("Caso 3");
            //Creamos las cabeceras
            columnNode=columnCreation(x);
            //Agregamos el contenido
            newNode=orderInsertInColumn(newNode, rowNode);
            newNode=orderInsertInRow(newNode, columnNode);
            return "";
            
        }
        //caso 4: Si existe fila y columna
        else {
            //System.out.println("caso 4");
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
       //Temporal para recorrer cada fila
       MatrixNode temp2;
       graphText="";
       graphText+="digraph G {\n" +
        "node [shape=plaintext];\n" +
        "label=\"Imagen Completa\";\n"+
               "some_node [\nlabel=<\n"+
        "<table border=\"0\" cellborder=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\">\n";
       //Se inicia Un triple ciclo For para generar el código de gaphviz
       while(temp!=null){
           //Un segundo temporal, para recorrer todas las columas en una fila
           temp2=temp;
           //Por cada iteración sería una nueva fila, por lo tanto se inicia
           graphText+="<tr>\n";
           //Contador que sive para verificar el control correcto de posicionamiento
           int counter=0;
           //Segundo ciclo while para recorrer las columnas de cada fila
           while(temp2!=null){
               //Le ponemos un atributo a nuestos nodos, los cuales es un contador de columna
                temp2.setCounter(counter);
                //Esto se comentó, solo me sirvió para debuggear el flujo del código.
                /*System.out.println("==================================================");
                System.out.println("la posición que viene es: "+temp2.x+","+temp2.y);
                System.out.println("columna correlativo #"+temp2.counter);
                System.out.println("==================================================");*/
                 
                counter++;
                
                if(temp2.up!=null){
                    while(temp2.up.counter!=temp2.counter){
                        //Esto se comentó, solo me sirvió para debuggear el flujo del código.
                        /*System.out.println("==================================================");
                        System.out.println("No hay equlibrio: "+temp2.x+","+temp2.y);
                        System.out.println(temp2.up.counter+"------"+temp2.counter);
                        System.out.println("==================================================");*/
                        graphText+="<td bgcolor=\"White\" width=\"1\" height=\"1\">"+"</td>\n";
                        counter++;
                        temp2.setCounter(temp2.getCounter()+1); 
                  
                    }   
                }    
                graphText+="<td bgcolor=\""+temp2.color+"\" width=\"1\" height=\"1\">"+temp2.x+","+temp2.y+"</td>\n";
                temp2=temp2.next;
            }
            graphText+="</tr>\n";
            temp=temp.down;
       
      
       
        }
        graphText+="</table>>\n];\n}";
        return graphText;
   }
   
   /*public String GraphMatrix2(){
        MatrixNode temp=root;
        //Temporal para recorrer cada fila
        MatrixNode temp2;
        String rankSame="{rank=same; ";
        String conections="";
        String nodes="";
        String rankDir="";
        String rankDirTotals="";
        String rankDirColumns="";
        graphText="";
        graphText+="digraph G {\n" +
         "node [shape=box];\n" +
         "label=\"Imagen Completa\";\n";
        //Se inicia Un triple ciclo For para generar el código de gaphviz
        while(temp!=null){
            //Un segundo temporal, para recorrer todas las columas en una fila
            temp2=temp;
            rankDir="{\nrank=same;\n";
            //Contador que sive para verificar el control correcto de posicionamiento
            int counter=0;
            //Segundo ciclo while para recorrer las columnas de cada fila
            while(temp2!=null){
                //Le ponemos un atributo a nuestos nodos, los cuales es un contador de columna
                 temp2.setCounter(counter);
                 //Esto se comentó, solo me sirvió para debuggear el flujo del código.
                 /*System.out.println("==================================================");
                 System.out.println("la posición que viene es: "+temp2.x+","+temp2.y);
                 System.out.println("columna correlativo #"+temp2.counter);
                 System.out.println("==================================================");*/

                 /*counter++;

                 nodes+="N"+temp2.hashCode()+"[label=\""+temp2.x+","+temp2.y+"\" color=\""+temp2.color+"\" style=\"filled\"];\n";
                 rankDir+="N"+temp2.hashCode();
                 if(temp2.next!=null){
                     conections+="N"+temp2.hashCode()+"->"+"N"+temp2.next.hashCode()+";\n";
                     //conections+="N"+temp2.next.hashCode()+"->"+"N"+temp2.hashCode()+";\n";
                     rankDir+=",";
                 }else{
                     rankDir+=";\n";
                 }
                 if(temp2.down!=null){
                     conections+="N"+temp2.hashCode()+"->"+"N"+temp2.down.hashCode()+";\n";
                     //conections+="N"+temp2.down.hashCode()+"->"+"N"+temp2.hashCode()+";\n";
                     rankDirColumns="{\nrank=same;\n";
                     rankDirColumns+="N"+temp2.hashCode()+",N"+temp2.down.hashCode()+";\n";
                     rankDirColumns+="rankdir=TB;\n}\n";
                     //rankDirTotals+=rankDirColumns;
                 }
                 temp2=temp2.next;
             }
             rankDir+="rankdir=LR;\n}\n";
             rankDirTotals+=rankDir;
             temp=temp.down;
             


         }
         graphText+=nodes;
         graphText+=conections;
         graphText+=rankDirTotals;
         graphText+="\n}";
         return graphText;
    }
   
   
   /*
   public String GraphMatrix3(){
        MatrixNode temp=root;
        //Temporal para recorrer cada fila
        MatrixNode temp2;
        //String importante ya que guarda una posición de equlibrio
        String hash="";
        String hash2="";
        String rankSame="{rank=same; ";
        String conections="";
        String conectionsTotals="";
        String rankSameTotals="";
        String join ="";
        graphText="";
        graphText+="digraph G {\n" +
            "node [shape=box];\n" +
            "N"+temp.hashCode()+"[label=\""+temp.x+","+temp.y+"\" color=\""+temp.color+"\" style=\"filled\" group=1];\n"+
                
            "e0[ shape = point, width = 0 ];\n" +
            "e1[ shape = point, width = 0 ];\n";
        temp.setGroup(1);
        //Vamos a meter dentro del mismo grupo a todas las filas
        while(temp!= null){
            if(temp.x!=-6){
                graphText+="N"+temp.hashCode()+"[label=\""+temp.x+","+temp.y+"\" color=\""+temp.color+"\" style=\"filled\" group=1];\n";
                temp.setGroup(1); 
                
                if(temp.down!=null){
                conections+="N"+temp.hashCode()+"->"+"N"+temp.down.hashCode()+";\n";
                conections+="N"+temp.down.hashCode()+"->"+"N"+temp.hashCode()+";\n";
                }
            }
            temp=temp.down;
       
        }
        graphText+=conections;
        conections="";
        //Una vez metidas las filas en un grupo, toca hacer lo mismo con las columnas
        //Solo que esta vez el grupo irá de forma ascendenter
        int columnGroupCounter=1;
        temp=root;
        rankSame+="N"+temp.hashCode()+";";
        while(temp!=null){
            if(temp.x!=-6){
                graphText+="N"+temp.hashCode()+"[label=\""+temp.x+","+temp.y+"\" color=\""+temp.color+"\" style=\"filled\" group="+columnGroupCounter+"];\n";
                temp.setGroup(columnGroupCounter);
                if(temp.next!=null){
                    conections+="N"+temp.hashCode()+"->"+"N"+temp.next.hashCode()+";\n";
                    conections+="N"+temp.next.hashCode()+"->"+"N"+temp.hashCode()+";\n";
                    rankSame+="N"+temp.hashCode()+";";
                }else{
                    rankSame+="N"+temp.hashCode()+";";
                }
            }
            columnGroupCounter++;
            if(temp.next==null){
                hash+="N"+temp.hashCode();
            }
            temp=temp.next;
        }
        temp=root;
        conections+="N"+temp.hashCode()+"->"+"N"+temp.down.hashCode()+";\n";
        conections+="N"+temp.hashCode()+"->"+"N"+temp.next.hashCode()+";\n";
        rankSame+=" }\n";
        graphText+=conections;
        graphText+=rankSame;
        conections="";
        rankSame="";
        
        //Ahora toca graficar los datos adentro de la matriz
        //Se tienen que hacer validaciones para no colocar datos repetidos o innecesarios.
       
        
        while(temp!=null){
            temp2=temp;
            rankSame="{rank=same; ";
            conections="";
            while(temp2!=null){
                if(temp2.up!=null && temp2.previous!=null){
                    temp2.setGroup(temp2.up.getGroup()); 
                    graphText+="N"+temp2.hashCode()+"[label=\""+temp2.x+","+temp2.y+"\" color=\""+temp2.color+"\" style=\"filled\" group="+temp2.getGroup()+"];\n";
                    if(temp2.previous.x!=-1 && temp2.up.y==-1){
                        conections+="N"+temp2.up.hashCode()+"->"+"N"+temp2.hashCode()+";\n";
                        conections+="N"+temp2.previous.hashCode()+"->"+"N"+temp2.hashCode()+";\n";
                        conections+="N"+temp2.hashCode()+"->"+"N"+temp2.previous.hashCode()+";\n";
                        
                    }else if(temp2.previous.x==-1 && temp2.up.y!=-1){
                        conections+="N"+temp2.previous.hashCode()+"->"+"N"+temp2.hashCode()+";\n";
                        conections+="N"+temp2.up.hashCode()+"->"+"N"+temp2.hashCode()+";\n";
                        //conections+="N"+temp2.hashCode()+"->"+"N"+temp2.up.hashCode()+";\n";
                       
                    }else if(temp2.previous.x==-1 && temp2.up.y==-1){
                        conections+="N"+temp2.up.hashCode()+"->"+"N"+temp2.hashCode()+";\n";
                        conections+="N"+temp2.previous.hashCode()+"->"+"N"+temp2.hashCode()+";\n";
                       
                    }else if(temp2.previous.x !=-1 && temp2.up.y!=-1){
                        conections+="N"+temp2.up.hashCode()+"->"+"N"+temp2.hashCode()+";\n";
                        //conections+="N"+temp2.hashCode()+"->"+"N"+temp2.up.hashCode()+";\n";
                        conections+="N"+temp2.previous.hashCode()+"->"+"N"+temp2.hashCode()+";\n";
                        conections+="N"+temp2.hashCode()+"->"+"N"+temp2.previous.hashCode()+";\n";
                    }
                  
                    if(temp2.next!=null){
                    
                        rankSame+="N"+temp2.hashCode()+";";
                    }else{
                        rankSame+="N"+temp2.hashCode()+";}\n";
                    }
                    
                    
                }else if(temp2.up!=null && temp2.previous==null){
                    rankSame+="N"+temp2.hashCode()+";";
                }
                if(temp2.next==null && temp.down==null){
                    hash2+="N"+temp2.hashCode();
                }
                temp2=temp2.next;
            }
            if(rankSame.contains("}")){
                
            }else{
                rankSame+="}\n";
            }
            conectionsTotals+=conections;
            rankSameTotals+=rankSame;
            join+=conectionsTotals+"\n";
            join+=rankSameTotals+"\n";
            conectionsTotals="";
            rankSameTotals="";
                
            temp=temp.down;
            
        }
        graphText+=join;
        //graphText+=hash+" -> "+"e0 -> e1[ dir = none ];\n";
        //graphText+="e1 -> "+hash2+";\n";
        graphText+="\n}";
        return graphText;
    }*/
   public void GraphSparseMatrixOfLayer(int idLayer,String name) throws IOException{
        String route="../Reportes Texto/capa"+idLayer+name+".txt";
        String graph="../Reportes Img/capa"+idLayer+name+".png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        MatrixNode temp=root;
        //Temporal para recorrer cada fila
        MatrixNode temp2;
   
        String rankSame="";
       
        String rankSameTotals="";
        
        
        graphText="";
        graphText+="digraph {\n" +
            "\n" +
            "rankdir = TB;\n" +
            "node [shape=rectangle, height=0.5, width=0.5];\n" +
            "graph[ nodesep = 0.5];\n" ;
            
        graphText+="//Las columnas cabeceras\n";
        while(temp!= null){
            
            graphText+="N"+temp.hashCode()+"[label=\""+temp.x+","+temp.y+"\" color=\""+temp.color+"\" style=\"filled\" ];\n";
            temp=temp.next;
            
        }
        temp=root;
        graphText+="\n\n\n//Las filas cabeceras\n";
        while(temp!= null){
            if(temp.x!=-6){
                
            
            graphText+="N"+temp.hashCode()+"[label=\""+temp.x+","+temp.y+"\" color=\""+temp.color+"\" style=\"filled\" ];\n";
            }
            temp=temp.down;
            
        }
        temp=root;
        graphText+="\n\n\n//Los valores de la Matriz\n";
        while(temp!=null){
            temp2=temp;
            rankSame="{rank=same; ";
            
            while(temp2!=null){
                if(temp2.up!=null && temp2.previous!=null){
                    graphText+="N"+temp2.hashCode()+"[label=\""+temp2.x+","+temp2.y+"\" color=\""+temp2.color+"\" style=\"filled\" ];\n";
                }
                temp2=temp2.next;
            }
            temp=temp.down;
        }
        
        temp=root;
        graphText+="\n\n\n//Conexiones de cada columna\n";
        rankSame="{ rank=same; ";
        while(temp!=null){
            
            
            
            if(temp.next!=null){
                graphText+="N"+temp.hashCode()+"->"+"N"+temp.next.hashCode()+"[dir=both];\n";
            }
            if(temp.down!=null && temp.down.x!=-1){
                graphText+="N"+temp.hashCode()+"->"+"N"+temp.down.hashCode()+"[dir=both];\n";
            }
            rankSame+="N"+temp.hashCode()+";";
            //System.out.println(rankSame);
            temp=temp.next;
           
        }
        //System.out.println(rankSame);
        rankSame+="}\n";
        rankSameTotals+=rankSame;
        rankSame="";
        temp=root;
        graphText+="\n\n\n//Conexiones de cada fila\n";
        if(temp.down!=null){
            graphText+="N"+temp.hashCode()+"->"+"N"+temp.down.hashCode()+"[dir=both];\n";
            temp=temp.down;
        }
        while(temp!=null){
            temp2=temp;
            rankSame="{rank=same; ";
            while(temp2!=null){
                if(temp2.down!=null){
                    graphText+="N"+temp2.hashCode()+"->"+"N"+temp2.down.hashCode()+"[dir=both];\n";
                }
                if(temp2.next!=null){
                    graphText+="N"+temp2.hashCode()+"->"+"N"+temp2.next.hashCode()+"[constraint=false,dir=both];\n";
                }
                rankSame+="N"+temp2.hashCode()+";";
                
                temp2=temp2.next;
            }
            rankSame+="}\n";
            rankSameTotals+=rankSame;
            
            temp=temp.down;
        }
        
        
        graphText+="\n\n\n//Los rankSame\n";
        graphText+=rankSameTotals;
        
      
        
        graphText+="\n}";
        FileWriter fw = new FileWriter(route);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(graphText);
        bw.close();
        
        String[] cmd = new String[5];
        cmd[0] = pathString;
        cmd[1] = tParam;
        cmd[2] = route;
        cmd[3] = tOParam;
        cmd[4] = graph;

        Runtime rt = Runtime.getRuntime();

        rt.exec( cmd );
   }
   
   
   
   public void GraphSparseMatrixOfImg(int idImg,String name) throws IOException{
        String route="../Reportes Texto/imagen"+idImg+name+".txt";
        String graph="../Reportes Img/imagen"+idImg+name+".png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        MatrixNode temp=root;
        //Temporal para recorrer cada fila
        MatrixNode temp2;
   
        String rankSame="";
       
        String rankSameTotals="";
        
        
        graphText="";
        graphText+="digraph {\n" +
            "\n" +
            "rankdir = TB;\n" +
            "node [shape=rectangle, height=0.5, width=0.5];\n" +
            "graph[ nodesep = 0.5];\n" ;
            
        graphText+="//Las columnas cabeceras\n";
        while(temp!= null){
            
            graphText+="N"+temp.hashCode()+"[label=\""+temp.x+","+temp.y+"\" color=\""+temp.color+"\" style=\"filled\" ];\n";
            temp=temp.next;
            
        }
        temp=root;
        graphText+="\n\n\n//Las filas cabeceras\n";
        while(temp!= null){
            if(temp.x!=-6){
                
            
            graphText+="N"+temp.hashCode()+"[label=\""+temp.x+","+temp.y+"\" color=\""+temp.color+"\" style=\"filled\" ];\n";
            }
            temp=temp.down;
            
        }
        temp=root;
        graphText+="\n\n\n//Los valores de la Matriz\n";
        while(temp!=null){
            temp2=temp;
            rankSame="{rank=same; ";
            
            while(temp2!=null){
                if(temp2.up!=null && temp2.previous!=null){
                    graphText+="N"+temp2.hashCode()+"[label=\""+temp2.x+","+temp2.y+"\" color=\""+temp2.color+"\" style=\"filled\" ];\n";
                }
                temp2=temp2.next;
            }
            temp=temp.down;
        }
        
        temp=root;
        graphText+="\n\n\n//Conexiones de cada columna\n";
        rankSame="{ rank=same; ";
        while(temp!=null){
            
            
            
            if(temp.next!=null){
                graphText+="N"+temp.hashCode()+"->"+"N"+temp.next.hashCode()+"[dir=both];\n";
            }
            if(temp.down!=null && temp.down.x!=-1){
                graphText+="N"+temp.hashCode()+"->"+"N"+temp.down.hashCode()+"[dir=both];\n";
            }
            rankSame+="N"+temp.hashCode()+";";
            //System.out.println(rankSame);
            temp=temp.next;
           
        }
        //System.out.println(rankSame);
        rankSame+="}\n";
        rankSameTotals+=rankSame;
        rankSame="";
        temp=root;
        graphText+="\n\n\n//Conexiones de cada fila\n";
        if(temp.down!=null){
            graphText+="N"+temp.hashCode()+"->"+"N"+temp.down.hashCode()+"[dir=both];\n";
            temp=temp.down;
        }
        while(temp!=null){
            temp2=temp;
            rankSame="{rank=same; ";
            while(temp2!=null){
                if(temp2.down!=null){
                    graphText+="N"+temp2.hashCode()+"->"+"N"+temp2.down.hashCode()+"[dir=both];\n";
                }
                if(temp2.next!=null){
                    graphText+="N"+temp2.hashCode()+"->"+"N"+temp2.next.hashCode()+"[constraint=false,dir=both];\n";
                }
                rankSame+="N"+temp2.hashCode()+";";
                
                temp2=temp2.next;
            }
            rankSame+="}\n";
            rankSameTotals+=rankSame;
            
            temp=temp.down;
        }
        
        
        graphText+="\n\n\n//Los rankSame\n";
        graphText+=rankSameTotals;
        
      
        
        graphText+="\n}";
        FileWriter fw = new FileWriter(route);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(graphText);
        bw.close();
        
        String[] cmd = new String[5];
        cmd[0] = pathString;
        cmd[1] = tParam;
        cmd[2] = route;
        cmd[3] = tOParam;
        cmd[4] = graph;

        Runtime rt = Runtime.getRuntime();

        rt.exec( cmd );
   }
}
       
       
       
  
   



