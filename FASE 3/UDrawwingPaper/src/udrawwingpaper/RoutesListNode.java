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
public class RoutesListNode {
    public Route route;
    public RoutesListNode next = null;
    public RoutesListNode(Route route){
            this.route = route;
            
    }
}
