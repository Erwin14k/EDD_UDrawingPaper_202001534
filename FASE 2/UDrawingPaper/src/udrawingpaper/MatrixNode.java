
package udrawingpaper;

/**
 *
 * @author Erwin14k
 */
public class MatrixNode {
    public int x;
    public int y;
    public String color;
    public MatrixNode up;
    public MatrixNode down;
    public MatrixNode next;
    public MatrixNode previous;
    
    public MatrixNode(int x, int y,String color ){
        this.x=x;
        this.y=y;
        this.color=color;
        up=down=next=previous=null;
    }
    
}
