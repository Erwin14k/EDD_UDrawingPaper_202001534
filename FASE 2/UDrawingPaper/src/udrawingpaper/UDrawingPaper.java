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
        matrix1.insert(1, 1, "#8CF201");
        matrix1.insert(3, 1, "#8CF201");
        matrix1.insert(4, 1, "#8CF201");
        matrix1.insert(3, 3, "#01A7F2");
        matrix1.insert(3, 4, "#01A7F2");
        matrix1.insert(4, 3, "#01A7F2");
        matrix1.insert(4, 4, "#01A7F2");
        matrix1.insert(5, 5, "#01A7F2");
        matrix1.insert(6, 1, "#01A7F2");
        matrix1.insert(1, 4, "#01A7F2");
        matrix1.insert(1, 6, "#01A7F2");*/
        

        /*matrix1.insert(5,0,"#000000");
			matrix1.insert(6,0,"#000000");
			matrix1.insert(7,0,"#000000");
			matrix1.insert(8,0,"#000000");
			matrix1.insert(9,0,"#000000");
			matrix1.insert(10,0,"#000000");
			matrix1.insert(5,1,"#000000");
			matrix1.insert(6,1,"#FFFFFF");
			matrix1.insert(7,1,"#00FF00");
			matrix1.insert(8,1,"#00FF00");
			matrix1.insert(9,1,"#FFFFFF");
			matrix1.insert(10,1,"#000000");
			matrix1.insert(11,1,"#000000");
			matrix1.insert(12,1,"#000000");
			matrix1.insert(3,1,"#000000");
			matrix1.insert(4,1,"#000000");
			matrix1.insert(7,2,"#00FF00");
			matrix1.insert(8,2,"#00FF00");
			matrix1.insert(2,2,"#000000");
			matrix1.insert(3,2,"#000000");
			matrix1.insert(12,2,"#000000");
			matrix1.insert(13,2,"#000000");
			matrix1.insert(13,3,"#000000");
			matrix1.insert(14,3,"#000000");
			matrix1.insert(1,3,"#000000");
			matrix1.insert(2,3,"#000000");
			matrix1.insert(3,3,"#00FF00");
			matrix1.insert(7,3,"#00FF00");
			matrix1.insert(8,3,"#00FF00");
			matrix1.insert(6,3,"#00FF00");
			matrix1.insert(9,3,"#00FF00");
			matrix1.insert(12,3,"#00FF00");
			matrix1.insert(3,15,"#000000");
			matrix1.insert(4,15,"#000000");
			matrix1.insert(5,15,"#000000");
			matrix1.insert(6,15,"#000000");
			matrix1.insert(7,15,"#000000");
			matrix1.insert(8,15,"#000000");
			matrix1.insert(9,15,"#000000");
			matrix1.insert(10,15,"#000000");
			matrix1.insert(11,15,"#000000");
			matrix1.insert(12,15,"#000000");
			matrix1.insert(2,14,"#000000");
			matrix1.insert(3,14,"#000000");
			matrix1.insert(12,14,"#000000");
			matrix1.insert(13,14,"#000000");
			matrix1.insert(1,13,"#000000");
			matrix1.insert(2,13,"#000000");
			matrix1.insert(13,13,"#000000");
			matrix1.insert(1,12,"#000000");
			matrix1.insert(2,12,"#000000");
			matrix1.insert(13,12,"#000000");
			matrix1.insert(14,12,"#000000");
			matrix1.insert(6,12,"#000000");
			matrix1.insert(9,12,"#000000");
			matrix1.insert(0,11,"#000000");
			matrix1.insert(1,11,"#000000");
			matrix1.insert(2,11,"#000000");
			matrix1.insert(3,11,"#000000");
			matrix1.insert(6,11,"#000000");
			matrix1.insert(9,11,"#000000");
			matrix1.insert(12,11,"#000000");
			matrix1.insert(13,11,"#000000");
			matrix1.insert(14,11,"#000000");
			matrix1.insert(15,11,"#000000");
			matrix1.insert(0,10,"#000000");
			matrix1.insert(3,10,"#000000");
			matrix1.insert(4,10,"#000000");
			matrix1.insert(5,10,"#000000");
			matrix1.insert(6,10,"#000000");
			matrix1.insert(7,10,"#000000");
			matrix1.insert(8,10,"#000000");
			matrix1.insert(9,10,"#000000");
			matrix1.insert(10,10,"#000000");
			matrix1.insert(11,10,"#000000");
			matrix1.insert(12,10,"#000000");
			matrix1.insert(15,10,"#000000");
			matrix1.insert(1,10,"#00FF00");
			matrix1.insert(2,10,"#00FF00");
			matrix1.insert(13,10,"#00FF00");
			matrix1.insert(14,10,"#00FF00");
			matrix1.insert(0,9,"#000000");
			matrix1.insert(15,9,"#000000");
			matrix1.insert(1,9,"#00FF00");
			matrix1.insert(2,9,"#00FF00");
			matrix1.insert(3,9,"#00FF00");
			matrix1.insert(4,9,"#00FF00");
			matrix1.insert(5,9,"#00FF00");
			matrix1.insert(10,9,"#00FF00");
			matrix1.insert(11,9,"#00FF00");
			matrix1.insert(12,9,"#00FF00");
			matrix1.insert(13,9,"#00FF00");
			matrix1.insert(14,9,"#00FF00");
			matrix1.insert(0,8,"#000000");
			matrix1.insert(0,7,"#000000");
			matrix1.insert(0,6,"#000000");
			matrix1.insert(0,5,"#000000");
			matrix1.insert(1,5,"#000000");
			matrix1.insert(1,4,"#000000");
			matrix1.insert(15,8,"#000000");
			matrix1.insert(15,7,"#000000");
			matrix1.insert(15,6,"#000000");
			matrix1.insert(15,5,"#000000");
			matrix1.insert(14,5,"#000000");
			matrix1.insert(14,4,"#000000");
			matrix1.insert(3,4,"#00FF00");
			matrix1.insert(4,4,"#00FF00");
			matrix1.insert(5,4,"#00FF00");
			matrix1.insert(6,4,"#00FF00");
			matrix1.insert(7,4,"#00FF00");
			matrix1.insert(8,4,"#00FF00");
			matrix1.insert(9,4,"#00FF00");
			matrix1.insert(10,4,"#00FF00");
			matrix1.insert(11,4,"#00FF00");
			matrix1.insert(12,4,"#00FF00");
			matrix1.insert(4,5,"#00FF00");
			matrix1.insert(4,6,"#00FF00");
			matrix1.insert(4,7,"#00FF00");
			matrix1.insert(4,8,"#00FF00");
			matrix1.insert(11,5,"#00FF00");
			matrix1.insert(11,6,"#00FF00");
			matrix1.insert(11,7,"#00FF00");
			matrix1.insert(11,8,"#00FF00");
			matrix1.insert(5,5,"#00FF00");
			matrix1.insert(10,5,"#00FF00");*
        
        matrix1.printData();
        System.out.println("=============================");
        System.out.println(matrix1.GraphMatrix4());
        //System.out.println(matrix1.GraphMatrix());*/
        LoginModule loginModuleHandler = new LoginModule();
        loginModuleHandler.loginFrameModule();
    }
    
}
