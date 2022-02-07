package udrawingpaper;

/**
 *
 * @author Erwin14k
 */
public class ClientDAO {
    private static ClientDAO instance;
   
     
    public static ClientDAO getInstance() {
        if(instance == null) {
            instance = new ClientDAO();            
        }
        return instance;
    }
    
}
