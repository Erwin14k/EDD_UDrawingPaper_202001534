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
public class SelfBalancingTreeNode {
    Img img;
    SelfBalancingTreeNode left;
    SelfBalancingTreeNode right;
    int equilibrium;
    private static int counter=1;
    private  final int id; 
    
    public  SelfBalancingTreeNode (Img img){
        this.img=img;
        this.equilibrium=0;
        this.left=null;
        this.right=null;
        this.id=counter++;
        
    }
    
    public String selfBalalancingTreeGraphvizCode(){
        String code="";
        if(left==null && right==null){
            code+="N"+id+" [ label =\""+"IMG "+img.getId()+"\"];\n";
        }else{
            code="N"+id+" [ label =\"<C0>|"+"IMG "+img.getId()+"|<C1>\"];\n";
        }
        if(left!=null){
            code=code+left.selfBalalancingTreeGraphvizCode()+"N"+id+":C0->N"+left.id+"\n";
        }
        if(right!=null){
           code=code+right.selfBalalancingTreeGraphvizCode()+"N"+id+":C1->N"+right.id+"\n";
            }
        return code;
        
    }
    
}
