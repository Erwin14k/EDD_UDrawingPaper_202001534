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
public class SelfBalancingTree {
    private SelfBalancingTreeNode root;
    
    public SelfBalancingTree(){
        root=null;
    }
    
    //Buscar
    public SelfBalancingTreeNode search(Img img,SelfBalancingTreeNode root){
        if(root==null){
            return null;
        }else if(root.img.getId()==img.getId()){
            return root;
            
        }else if(root.img.getId()<img.getId()){
            return search(img,root.right);
        }else{
            return search(img, root.left);
        }
    }
    //Obtener valor de equilibrio
    public int getEquilibrium(SelfBalancingTreeNode node){
        if(node==null){
            return -1;
        }else{
            return node.equilibrium;
        }
        
    }
    
    //rotación izquierda
    public SelfBalancingTreeNode leftRotation(SelfBalancingTreeNode node){
       SelfBalancingTreeNode temp=node.left;
       node.left=temp.right;
       temp.right=node;
       node.equilibrium=Math.max(getEquilibrium(node.left), getEquilibrium(node.right))+1;
       temp.equilibrium=Math.max(getEquilibrium(temp.left), getEquilibrium(temp.right))+1;
       return temp;
    }
    
    //rotación derecha
    public SelfBalancingTreeNode rightRotation(SelfBalancingTreeNode node){
       SelfBalancingTreeNode temp=node.right;
       node.right=temp.left;
       temp.left=node;
       node.equilibrium=Math.max(getEquilibrium(node.left), getEquilibrium(node.right))+1;
       temp.equilibrium=Math.max(getEquilibrium(temp.left), getEquilibrium(temp.right))+1;
       return temp;
    }
    //Rotación doble a la derecha
    public SelfBalancingTreeNode doubleLeftRotation(SelfBalancingTreeNode node){
        SelfBalancingTreeNode temp;
        node.left=rightRotation(node.left);
        temp=leftRotation(node);
        return temp;
    }
    //Rotación doble a la izquierda
    public SelfBalancingTreeNode doubleRightRotation(SelfBalancingTreeNode node){
        SelfBalancingTreeNode temp;
        node.right=leftRotation(node.right);
        temp=rightRotation(node);
        return temp;
    }
    
    //Método Insertar
    public SelfBalancingTreeNode recursiveInsert(SelfBalancingTreeNode newNode,SelfBalancingTreeNode subTree){
        SelfBalancingTreeNode newDad=subTree;
        if(newNode.img.getId()<subTree.img.getId()){
            if(subTree.left==null){
                subTree.left=newNode;
            }else{
                subTree.left=recursiveInsert(newNode, subTree.left);
                if((getEquilibrium(subTree.left)- getEquilibrium(subTree.right))==2){
                    if(newNode.img.getId()<subTree.left.img.getId()){
                        newDad=leftRotation(subTree);
                    }else{
                        newDad=doubleLeftRotation(subTree);
                    }
                }
            }
        }else if(newNode.img.getId()>subTree.img.getId()){
            if(subTree.right==null){
                subTree.right=newNode;
            }else{
                subTree.right=recursiveInsert(newNode, subTree.right);
                if((getEquilibrium(subTree.right)-getEquilibrium(subTree.left))==2){
                    if(newNode.img.getId()>subTree.right.img.getId()){
                        newDad=rightRotation(subTree);
                        
                    }else{
                        newDad=doubleRightRotation(subTree);
                    }
                }
            }
            
        }else{
            System.out.println("Nodo Duplicado.");
        }
        //Actualizando Equilibrio
        if((subTree.left==null)&&(subTree.right!=null)){
            subTree.equilibrium=subTree.right.equilibrium+1;
        }else if((subTree.right==null)&&(subTree.left!=null)){
            subTree.equilibrium=subTree.left.equilibrium+1;
        }else{
            subTree.equilibrium=Math.max(getEquilibrium(subTree.left), getEquilibrium(subTree.right))+1;
        }
        return newDad;
    }
    //Insertar
    public void insert(Img img){
        SelfBalancingTreeNode newNode=new SelfBalancingTreeNode(img);
        if(root==null){
            root=newNode;
        }else{
            root=recursiveInsert(newNode, root);
        }
        
    }
    
    public String getAVLTreeGraphvizCode(){
        
        String code="";
        code+="digraph G{\nnode [shape=record,style=filled];\n"+
                "rankdir=TB;\n";
        if(root!=null){
            System.out.println("si se puede");
            code+=root.selfBalalancingTreeGraphvizCode();
        }
        code+="\n}";
        return code;
    }
    
    public void generateAVLTreeGraph() throws IOException{
        String route="../Reportes Texto/avl.txt";
        String graph="../Reportes Img/avl.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        FileWriter fw = new FileWriter(route);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(getAVLTreeGraphvizCode());
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
