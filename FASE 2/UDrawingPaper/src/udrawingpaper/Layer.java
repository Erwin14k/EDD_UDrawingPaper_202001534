/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.math.BigInteger;

/**
 *
 * @author Erwin14k
 */
public class Layer {
    private int id;
    private Position position;
    private BigInteger clientId;
    
    public Layer(int  id,Position position,BigInteger clientId){
        this.id = id;
        this.position=position;
        this.clientId=clientId;
      
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }
    

 
    
    
}
