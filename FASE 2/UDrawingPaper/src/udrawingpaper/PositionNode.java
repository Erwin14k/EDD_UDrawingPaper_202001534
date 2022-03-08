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
public class PositionNode {
    public int layerId;
    public int row;
    public int column;
    public String color;
    public PositionNode next = null;
    public PositionNode(int layerId,int row,int column,String color ){
        this.layerId = layerId;
        this.row = row;
        this.column = column;
        this.color = color;
    }
}
