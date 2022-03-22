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
public class BTree {
    
    BTreeBranch root;
    int order;
    int height;
    String code;
    
    public BTree(){
        this.root=null;
        this.order=5;
        this.height=0;
        this.code="";
    }
    
    public void insert(Client client){
        BTreeNode newNode= new BTreeNode(client);
        if(root==null){
            root=new BTreeBranch();
            root.root=true;
            root=(BTreeBranch)root.insertInBranch(newNode);
        }else{
            if(height==0){
                Object response=root.insertInBranch(newNode);
                //Si la cabeza no se ha dividido:
                if(response instanceof BTreeBranch){
                    //System.out.println("rama11");
                    root=(BTreeBranch)response;
                }else{
                    height++;
                    root=new BTreeBranch();
                    root=(BTreeBranch) root.insertInBranch( response);
                }
            }else{
                //Hay que validar si hay más de una rama para recorrer el arbol e insertar el nodo.
                if(root==null){
                    System.out.println("Raíz Vacía");
                    return;
                }
                Object response=travelInsert(newNode,root);
                if(response instanceof BTreeNode){//Se dividió root
                    //System.out.println("Nodo11");
                    height++;
                    root=new BTreeBranch();
                    root=(BTreeBranch)root.insertInBranch(response);
                }else if(response instanceof BTreeBranch){
                    //System.out.println("rama22");
                    root=(BTreeBranch)response;
                }
            }
        }
    }
    
    
    public Object travelInsert(BTreeNode newNode, BTreeBranch branch){
        if(branch.isLeaf(branch)){
            //System.out.println("ES HOJAAAAAAAAAAAAA");
            Object response= branch.insertInBranch(newNode);
            return response;
        }else{
            //if(BigInteger.compare(newNode.client.getDpi(),branch.list.head.client.getDpi())==-1
            System.out.println("ssss"+newNode.client.getDpi().compareTo(branch.list.head.client.getDpi()));
            if(newNode.client.getDpi().compareTo(branch.list.head.client.getDpi())==(-1)){
                System.out.println("por que no entras?");
                //System.out.println(newNode.client.getDpi()+"-"+branch.list.head.client.getDpi());
                Object response=travelInsert(newNode, branch.list.head.left);
                if(response instanceof BTreeNode){
                    //System.out.println("Nodo1");
                    return branch.insertInBranch((BTreeNode)response);
                }else if(response instanceof BTreeBranch){
                    //System.out.println("Rama1");
                    branch.list.head.left=(BTreeBranch)response;
                    return branch;
                }
            }else if(newNode.client.getDpi().compareTo(branch.list.end.client.getDpi())==1){
                //System.out.println("Si es mayor");
                Object response=travelInsert(newNode, branch.list.end.right);
                if(response instanceof BTreeNode){
                    //System.out.println("Nodo2");
                    return branch.insertInBranch((BTreeNode)response);
                }else if(response instanceof BTreeBranch){
                    //System.out.println("Rama2");
                    branch.list.end.right=(BTreeBranch)response;
                    return branch;
                }
            }else{
                BTreeNode temp=branch.list.head;
                while(temp!=null){
                    switch (newNode.client.getDpi().compareTo(temp.client.getDpi())) {
                        case -1:
                            Object response =travelInsert(newNode, temp.left);
                            if(response instanceof BTreeNode){
                                //System.out.println("Nodo3");
                                return branch.insertInBranch((BTreeNode)response);
                            }else if(response instanceof BTreeBranch){
                                //System.out.println("Rama3");
                                temp.left=(BTreeBranch)response;
                                temp.previous.right=(BTreeBranch)response;
                                return branch;
                            }   break;
                        case 0:
                            return branch;
                        default:
                            temp=temp.next;
                            
                    }
                }
            }
        }
        return this;
    }
    
    public String getCode() throws IOException{
        //System.out.println("INICIA");
        String dot="digraph B_Tree{\n";
        //this.code="{\n";
        dot+="rankr=TB;\n";
        //this.code+="rankr=TB;\n";
        dot+="node[shape = box,fillcolor=\"#01F2C1\" color=\"black\" style=\"filled\"];\n";
        dot+= graph1(root);
        
        //this.code+= this.graph2(this.root);
        dot+=  connect(root);
        //this.code+=  this.connect(this.root);
        dot+="}\n";
        //this.code+="}\n";
        //System.out.println("FINALIZA");
        String route="../Reportes Texto/ARBOLB.txt";
        String graph="../Reportes Img/ARBOLB.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        FileWriter fw = new FileWriter(route);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(dot);
        bw.close();
        
        String[] cmd = new String[5];
        cmd[0] = pathString;
        cmd[1] = tParam;
        cmd[2] = route;
        cmd[3] = tOParam;
        cmd[4] = graph;

        Runtime rt = Runtime.getRuntime();

        rt.exec( cmd );
        return dot;
    }
    public String graph1(BTreeBranch root){
        String string1="";
        if(root.isLeaf(root)){
            string1+="node[shape=record label= \"<p0>";
            int counter=0;
            BTreeNode temp=root.list.head;
            while(temp!=null){
                counter++;
                string1+="|{"+temp.client.getDpi()+"  ---  "+temp.client.getName()+"   "+  "}|<p"+counter+"> ";
                temp=temp.next;
            }
            string1+="\"]"+root.list.head.client.getDpi()+";\n";
            return string1;
        }else{
            string1+="node[shape=record label= \"<p0>";
            int counter=0;
            BTreeNode temp = root.list.head;
            
            while(temp!=null){
                counter++;
                string1+="|{"+temp.client.getDpi()+"  ---  "+temp.client.getName()+"   "+"}|<p"+counter+"> ";
                temp= temp.next;
            }
            string1+="\"]"+root.list.head.client.getDpi()+";\n";
            
            //recorrer los hijos de cada clave
            temp = root.list.head;
            while(temp != null){
                string1+= graph1(temp.left);
                temp = temp.next;
            }
            string1+= graph1(root.list.end.right);
            return string1;
        }
    }
    
    
    
  
    
    public String connect(BTreeBranch root){
        String stringConnect="";
        if(root.isLeaf(root)){
            System.out.println("Si es hoja");
            return ""+root.list.head.client.getDpi()+";\n";
        }else{
            System.out.println("No es hoja perro");

            BTreeNode temp = root.list.head;
            int counter =0;
            String currentRoot = root.list.head.client.getDpi().toString();
            while(temp != null){
                stringConnect+= "\n"+currentRoot+":p"+counter+"->"+connect(temp.left);
                counter++;
                temp = temp.next;
            }
            stringConnect+="\n"+currentRoot+":p"+counter+"->"+connect(root.list.end.right);
            System.out.println("----"+stringConnect);
            return stringConnect;
        }
    }
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*int treeGrade=5;
    BTreeBranch root;
    
    public BTree(){
        this.root=null;
    }
    public void insert(Client client){
        BTreeNode node= new BTreeNode(client);
        if(root==null){
            root=new BTreeBranch();
            root.insert(node); 
        }else{
            BTreeNode newNode = branchInsert(node,root);
            if(newNode!=null){
                root= new BTreeBranch();
                root.insert(newNode);
                root.leaf=false;
            }
        }
    }
    public BTreeNode branchInsert(BTreeNode node , BTreeBranch branch){
        if(branch.leaf){
            branch.insert(node);
            if(branch.counter==treeGrade){
                return divide(branch);
            }else{
                return null;
            }
        }else{
            BTreeNode temp = branch.first;
            do{
                if(node.client.getDpi().compareTo(temp.client.getDpi())==0){
                    return null;
                }else if(node.client.getDpi().compareTo(temp.client.getDpi())==-1){
                    BTreeNode resultNode=branchInsert(node, temp.left);
                    if(resultNode instanceof BTreeNode){
                        branch.insert((BTreeNode) resultNode); 
                        if(branch.counter==treeGrade){
                            return divide(branch);
                        
                        }
                    }
                    return null;
                }else if(temp.next==null){
                    BTreeNode resultNode=branchInsert(node, temp.right);
                    if(resultNode instanceof BTreeNode){
                        branch.insert((BTreeNode) resultNode); 
                        if(branch.counter==treeGrade){
                            return divide(branch);
                        
                        }
                    }
                    return null;
                }
                temp=(BTreeNode) temp.next;
            }while(temp!=null);
        }
        return null;
        
    }
    
    public BTreeNode divide(BTreeBranch branch){
        Client tempClient = null;
        BTreeNode temp, newNode;
        BTreeNode interim =branch.first;
        BTreeBranch rightBranch=new BTreeBranch();
        BTreeBranch leftBranch=new BTreeBranch();
        int tempCounter=0;
        while(interim!=null){
            tempCounter++;
            if(tempCounter<3){
                temp=new BTreeNode(interim.client);
                temp.left=interim.left;
                if(tempCounter==2){
                    temp.right=interim.next.left;
                }else{
                    temp.right=interim.right;
                }
                if(temp.right!=null && temp.left!=null){
                    rightBranch.leaf=false;
                }
                leftBranch.insert(temp); 
            }else if(tempCounter==3){
                tempClient=interim.client;
            }else{
                temp=new BTreeNode(interim.client);
                temp.left=interim.left;
                temp.right=interim.right;
                
                if(temp.right!=null && temp.left!=null){
                    rightBranch.leaf=false;
                }
                rightBranch.insert(temp); 
            }
            interim=interim.next;
        }
        newNode=new BTreeNode(tempClient);
        newNode.right=rightBranch;
        newNode.left=leftBranch;
        return newNode;
    }
    
    
    public void getNodes1(BTreeBranch root){
        String code="";
        if(root.leaf){
            
        }
    }
    
    */
}
