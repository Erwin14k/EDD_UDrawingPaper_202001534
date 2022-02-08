
package udrawingpaper;

/**
 *
 * @author Erwin14k
 */
public class Printer {
    private int id;
    private String state;
    private int secondsToBeFree;
    private PrinterQueue printerQueue;
    
    
    public Printer(int  id, String state,PrinterQueue printerQueue,int secondsToBeFree){
        this.id = id;
        this.state = state;
        this.printerQueue=printerQueue;
        this.secondsToBeFree=secondsToBeFree;
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

    public int getSecondsToBeFree() {
        return secondsToBeFree;
    }

    public void setSecondsToBeFree(int secondsToBeFree) {
        this.secondsToBeFree = secondsToBeFree;
    }

    public PrinterQueue getPrinterQueue() {
        return printerQueue;
    }

    public void setPrinterQueue(PrinterQueue printerQueue) {
        this.printerQueue = printerQueue;
    }
    
    
    
}
