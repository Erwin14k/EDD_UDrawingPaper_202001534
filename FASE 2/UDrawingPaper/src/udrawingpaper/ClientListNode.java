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
public class ClientListNode {
    public Client client;
    public ClientListNode next = null;
    public ClientListNode(Client client){
            this.client = client;
        }
}
