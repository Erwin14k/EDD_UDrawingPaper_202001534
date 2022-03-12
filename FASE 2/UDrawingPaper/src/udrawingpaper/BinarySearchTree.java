/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

/**
 *
 * @author Erwin14k
 */
public class BinarySearchTree {
    private BinarySearchTreeNode root;
    public String depth;
    public String preOrder;
    public String inOrder;
    public String postOrder;
    
    //Inseción al árbol de búsqueda binario
    
  
    public void insert(Layer layer){
        root=recursiveInsert(layer, root);
    }
    public BinarySearchTreeNode recursiveInsert(Layer layer,BinarySearchTreeNode root){
        if (root==null){
            BinarySearchTreeNode newNode= new BinarySearchTreeNode(layer);
            return newNode;
        }else{
            if (layer.getId() < root.layer.getId()){
                root.left=recursiveInsert(layer,root.left);
            }else{
                root.right=recursiveInsert(layer, root.right);
            } 
        }
        return root;
    }
    
    public String getBstTreeGraphvizCode(){
        
        String code="";
        code+="digraph G{\nnode [shape=record,style=filled];\n"+
                "rankdir=TB;\n";
        if(root!=null){
            System.out.println("si se puede");
            code+=root.bstTreeGraphvizCode();
        }
        code+="\n}";
        return code;
    }
    //Método para graficar el árbol binario de Busqueda
    public void generateBstTreeGraph() throws IOException{
        String route="../Reportes Texto/Abb.txt";
        String graph="../Reportes Img/Abb.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        FileWriter fw = new FileWriter(route);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(getBstTreeGraphvizCode());
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
    
    public void generatePersonalizeBstTreeGraph(String name) throws IOException{
        String route="../Reportes Texto/Abb"+name+".txt";
        String graph="../Reportes Img/Abb"+name+".png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        FileWriter fw = new FileWriter(route);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(getBstTreeGraphvizCode());
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
    public String returnMeTheAbbRoute(String name) throws IOException{
        
        String graph="../Reportes Img/Abb"+name+".png";
        return graph;
        
    }
    public String returnMeTheAvlRoute(String name) throws IOException{
        
        String graph="../Reportes Img/Avl"+name+".png";
        return graph;
        
    }
    
    //Método para buscar un nodo y validar que exista
    public Layer searchNodeAndReturnLayer(int id){
        BinarySearchTreeNode temp=root;
        while(temp.layer.getId()!=id){
            if(id<temp.layer.getId()){
                temp=temp.left;
            }else{
                temp=temp.right;
            }
            if(temp==null){
                return null;
            }
        }
        return temp.layer;
    }
    public BinarySearchTreeNode returnMeTheRoot(){
        return root;
    }
    public String rreturnMeThePreOrder(){
        return preOrder;
    }
    public String returnMeTheInOrder(){
        return inOrder;
    }
    public String returnMeThePostOrder(){
        return postOrder;
    }
    public String returnMeTheDepth(){
        return depth;
    }
    
    public String inOrder(BinarySearchTreeNode root){
        
        if(root!=null){
            inOrder(root.left);
            inOrder+=root.layer.getId()+",";
            inOrder(root.right);
        }
        return inOrder;
        
    }
    
    public String preOrder(BinarySearchTreeNode root){
        
        if(root!=null){
            preOrder+=root.layer.getId()+",";
            preOrder(root.left);
            preOrder(root.right);
        }
        return preOrder;
        
    }
    public String postOrder(BinarySearchTreeNode root){
        
        if(root!=null){
            
            preOrder(root.left);
            preOrder(root.right);
            postOrder+=root.layer.getId()+",";
        }
        return postOrder;
    }
    
   
    
    public int depth(BinarySearchTreeNode root){
        if(root!=null){
            return 1+ Math.max(depth(root.left), depth(root.right));
        }
        return 0;
        
    }
    
    public void printTheTravels(){
        System.out.println(preOrder);
        System.out.println(inOrder);
        System.out.println(postOrder);
    }
    
    public void initialzeTheTravelers(){
        preOrder="";
        inOrder="";
        postOrder="";
    }
}
