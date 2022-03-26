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
public class BinarySearchTree {
    private BinarySearchTreeNode root;
    public String depth;
    public String preOrder;
    public String preOrderLimited;
    public String inOrder;
    public String inOrderLimited;
    public String postOrder;
    public String postOrderLimited;
    public int  tempLimit;
    public int  tempLimit2;
    public int  tempLimit3;
    public int  limit;
    public String leafs;
    public String allCodes="";
    
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
    
    
    public void generatePersonalizeBstTreeGraphForEachImage(int id,String name) throws IOException{
        String route="../Reportes Texto/ArbolImagen"+id+name+".txt";
        String graph="../Reportes Img/ArbolImagen"+id+name+".png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        
        FileWriter fw = new FileWriter(route);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(getBstTreeGraphvizCode());
        bw.close();
        
        String[] cmd = new String[5];
        cmd[0] = pathString;//bin de graphviz
        cmd[1] = tParam;//-Tpng
        cmd[2] = route;//
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
    public String rreturnMeThePreOrderLimited(){
        return preOrderLimited;
    }
    public String returnMeTheInOrder(){
        return inOrder;
    }
    public String returnMeTheInOrderLimited(){
        return inOrderLimited;
    }
    public String returnMeThePostOrder(){
        return postOrder;
    }
    public String returnMeThePostOrderLimited(){
        return postOrderLimited;
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
    
    public String inOrderLimited(BinarySearchTreeNode root){
        
        if(root!=null){
            inOrderLimited(root.left);
            if(tempLimit<limit){
                inOrderLimited+=root.layer.getId()+",";
                tempLimit++;
            }
            inOrderLimited(root.right);
        }
        return inOrderLimited;
        
    }
    
    public String preOrder(BinarySearchTreeNode root){
        
        if(root!=null){
            preOrder+=root.layer.getId()+",";
            preOrder(root.left);
            preOrder(root.right);
        }
        return preOrder;
        
    }
    
    public String preOrderLimited(BinarySearchTreeNode root){
        
        if(root!=null){
            if(tempLimit2<limit){
                preOrderLimited+=root.layer.getId()+",";
                tempLimit2++;
            }
            preOrderLimited(root.left);
            preOrderLimited(root.right);
        }
        return preOrderLimited;
        
    }
    
    public String collectTheLeafs(BinarySearchTreeNode root){
        
        if(root!=null){
            if(root.left==null & root.right==null){
                leafs+=root.layer.getId()+",";
            }
            
            collectTheLeafs(root.left);
            collectTheLeafs(root.right);
        }
        return leafs;
        
    }
    public String postOrder(BinarySearchTreeNode root){
        
        if(root!=null){
            
            postOrder(root.left);
            postOrder(root.right);
            postOrder+=root.layer.getId()+",";
        }
        return postOrder;
    }
    
    public String postOrderLimited(BinarySearchTreeNode root){
        
        if(root!=null){
            
            postOrderLimited(root.left);
            postOrderLimited(root.right);
            if(tempLimit3<limit){
                postOrderLimited+=root.layer.getId()+",";
                tempLimit3++;
            }
        }
        return postOrderLimited;
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
        leafs="";
    }
    
    public void initialzeTheTravelersLimited(int limitOfTravel){
        preOrderLimited="";
        inOrderLimited="";
        postOrderLimited="";
        limit=limitOfTravel;
        tempLimit=0;
        tempLimit2=0;
        tempLimit3=0;
        leafs="";
        
    }
    
    public void collectCodes(){
        allCodes="";
        layersCodes(root);
    }
    //Este método recolecta todos los id´s de las capas para mostrarlas dentro de un combobx en la interfaz
    public String[] layersCodes(BinarySearchTreeNode root){
        if(root!=null){
            allCodes+=root.layer.getId()+",";
            layersCodes(root.left);
            layersCodes(root.right);
        }
        if(allCodes.equals("")){ 
            String temp="";
            temp+="No hay capas!!,";
            return temp.split(",");
        }
        return allCodes.split(",");
    }
}
