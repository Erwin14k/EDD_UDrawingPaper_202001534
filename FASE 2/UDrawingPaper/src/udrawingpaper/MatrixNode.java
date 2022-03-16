
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
    public int counter;
    public int group;
    
    public MatrixNode(int x, int y,String color,int counter,int group ){
        this.x=x;
        this.y=y;
        this.color=color;
        up=down=next=previous=null;
        this.counter=counter;
        this.group=group;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
    
    
}
