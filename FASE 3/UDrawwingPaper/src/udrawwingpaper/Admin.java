/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawwingpaper;

import java.math.BigInteger;

/**
 *
 * @author Erwin14k
 */
public class Admin {
    private String user;
    private String password;
    private BigInteger code;
    
    public Admin(String user, String password,BigInteger code){
        this.user=user;
        this.password=password;
        this.code=code;
        
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigInteger getCode() {
        return code;
    }

    public void setCode(BigInteger code) {
        this.code = code;
    }
    
    
}

