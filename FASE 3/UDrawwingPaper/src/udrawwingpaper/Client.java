
package udrawwingpaper;

import java.math.BigInteger;

/**
 *
 * @author Erwin14k
 */
public class Client {
    private BigInteger dpi;
    private String name;
    private String userName;
    private String mail;
    private String password;
    private String direction;
    private int cityId;

 
    public Client(BigInteger  dpi,String name, String userName, String mail, String password, String direction, int cityId){
        this.dpi = dpi;
        this.name=name;
        this.userName=userName;
        this.mail=mail;
        this.password=password;
        this.direction=direction;
        this.cityId=cityId;

    }

    public BigInteger getDpi() {
        return dpi;
    }

    public void setDpi(BigInteger dpi) {
        this.dpi = dpi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    
}
