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
public class Image {
    private int idClient;
    private String format;
    private String state;
    
    public Image(int  idClient, String format,String state){
        this.idClient = idClient;
        this.format = format;
        this.state=state;
}

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
}
