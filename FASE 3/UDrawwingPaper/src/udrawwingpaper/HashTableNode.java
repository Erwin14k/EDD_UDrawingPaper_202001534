/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawwingpaper;

/**
 *
 * @author Erwin14k
 */
public class HashTableNode {
    public DeliveryCourier deliveryCourier;
    public HashTableNode next = null;
    public int key;
    public HashTableNode(int key,DeliveryCourier deliveryCourier){
            this.deliveryCourier = deliveryCourier;
            this.key=key;
            
        }
}
