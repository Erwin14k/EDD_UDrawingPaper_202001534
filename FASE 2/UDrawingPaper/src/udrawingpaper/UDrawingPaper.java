/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;

import java.io.IOException;

/**
 *
 * @author Erwin14k
 */
public class UDrawingPaper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       
        /*Matrix matrix1=new Matrix();
        System.out.println("=============================");
        matrix1.insert(2, 1, "#8CF201");
        matrix1.insert(3, 1, "#8CF201");
        matrix1.insert(2, 2, "#8CF201");
        matrix1.insert(1, 1, "#01A7F2");
        matrix1.insert(8, 9, "#01A7F2");
        matrix1.insert(7, 9, "#01A7F2");
        matrix1.insert(6, 9, "#01A7F2");
        matrix1.printData();
        System.out.println("=============================");
        System.out.println(matrix1.GraphMatrix());*/
        LoginModule loginModuleHandler = new LoginModule();
        loginModuleHandler.loginFrameModule();
    }
    
}
