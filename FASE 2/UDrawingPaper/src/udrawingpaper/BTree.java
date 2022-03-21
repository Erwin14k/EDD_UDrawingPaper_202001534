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
public class BTree {
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
