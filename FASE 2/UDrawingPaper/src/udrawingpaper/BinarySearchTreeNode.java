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
public class BinarySearchTreeNode {
    Layer layer;
    BinarySearchTreeNode left=null;
    BinarySearchTreeNode right=null;
    private static int counter=1;
    private  final int id; 
    
    public BinarySearchTreeNode(Layer layer){
            this.layer = layer;
            this.left=null;
            this.right=null;
            this.id=counter++;
        }
    
    public String bstTreeGraphvizCode(){
        String code="";
        if(left==null && right==null){
            code+="N"+id+" [ label =\""+"Capa "+layer.getId()+"\"];\n";
        }else{
            code="N"+id+" [ label =\"<C0>|"+"Capa "+layer.getId()+"|<C1>\"];\n";
        }
        if(left!=null){
            code=code+left.bstTreeGraphvizCode()+"N"+id+":C0->N"+left.id+"\n";
        }
        if(right!=null){
           code=code+right.bstTreeGraphvizCode()+"N"+id+":C1->N"+right.id+"\n";
            }
        return code;
        
    }
    
}
