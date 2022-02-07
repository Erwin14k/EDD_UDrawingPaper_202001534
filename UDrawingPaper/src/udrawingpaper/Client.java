package udrawingpaper;
/**
 *
 * @author Erwin14k
 */

public class Client {
    private int id;
    private String name;
    private int colorImageCounter;
    private int bwImageCounter;
    private int steps;
    private int idWindow;
    private String state;
    private int totalImages;
    private int imgCounter;
    
    public Client(int  id, String name, int colorImageCounter, int bwImageCounter,int steps,int idWindow,String state,int totalImages,int imgCounter){
        this.id = id;
        this.name = name;
        this.colorImageCounter= colorImageCounter;
        this.bwImageCounter= bwImageCounter;
        this.steps=steps;
        this.idWindow=idWindow;
        this.state=state;
        this.totalImages=totalImages;
        this.imgCounter=imgCounter;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColorImageCounter() {
        return colorImageCounter;
    }

    public void setColorImageCounter(int colorImageCounter) {
        this.colorImageCounter = colorImageCounter;
    }

    
    public int getSteps() {
        return steps;
    }
    public void setSteps(int steps){
        this.steps=steps;
    }
    public int getIdWindow(){
        return idWindow;
    }
    public void setIdWindow(int idWindow){
        this.idWindow=idWindow;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state=state;
    }
    public int getTotalImages(){
        return totalImages;
    }
    public void setTotalImages(int totalImages){
        this.totalImages=totalImages;
    }

    public int getBwImageCounter() {
        return bwImageCounter;
    }

    public void setBwImageCounter(int bwImageCounter) {
        this.bwImageCounter = bwImageCounter;
    }

    public int getImgCounter() {
        return imgCounter;
    }

    public void setImgCounter(int imgCounter) {
        this.imgCounter = imgCounter;
    }
    
}
