
package udrawingpaper;

/**
 *
 * @author Erwin14k
 */
public class Client {
    private int dpi;
    private String name;
    private String password;
    private SelfBalancingTree avlTree;
    public Client(int  dpi, String name, String password,SelfBalancingTree avlTree){
        this.dpi = dpi;
        this.name = name;
        this.password= password;  
        this.avlTree=avlTree;
    }

    public int getDpi() {
        return dpi;
    }

    public SelfBalancingTree getAvlTree() {
        return avlTree;
    }

    public void setAvlTree(SelfBalancingTree avlTree) {
        this.avlTree = avlTree;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    
}
