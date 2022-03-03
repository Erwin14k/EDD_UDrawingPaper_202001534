
package udrawingpaper;

/**
 *
 * @author Erwin14k
 */
public class Window {
    private int id;
    private String state;
    private StackList stacklist;
    
    public Window(int  id, String state,StackList stacklist){
        this.id = id;
        this.state = state;
        this.stacklist=stacklist;
}

    public StackList getStacklist() {
        return stacklist;
    }

    public void setStacklist(StackList stacklist) {
        this.stacklist = stacklist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
}
