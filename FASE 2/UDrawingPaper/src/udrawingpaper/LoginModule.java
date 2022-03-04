/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaper;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Erwin14k
 */
public class LoginModule {
    static BinarySearchTree bstTreeHandler= new BinarySearchTree();
    static SelfBalancingTree avlTreeHandler= new SelfBalancingTree();
    public static void main(String[] args) throws IOException {
            loginFrameModule();
    }
    
    //Frame para iniciar sesión, ya se como administrador o como cliente.
    public static void loginFrameModule() throws IOException{

        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,50);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        //=========================Creación del Frame Login Principal=========================
        
        //Se crea el frame y se le agrega un título
        JFrame loginFrame = new JFrame("UDrawing Paper");
        loginFrame.setLayout(null);
        //Se hace visible el frame
        loginFrame.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        loginFrame.setResizable(false);
        //Se le coloca un color de fondo al frame
        loginFrame.getContentPane().setBackground(Color.BLUE);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame login
        loginFrame.setSize(1080,720);
        //Le agreamos una locación al frame login
        loginFrame.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame login
        Image myIcon= myScreen.getImage("iconoLogin.png");
        loginFrame.setIconImage(myIcon);
        //loginFrame.setLayout(new BorderLayout());
        
        
        
        
        
   
        
        //=========================Creación de Labels =========================
        //label que nos muestra el mensaje "DTT"
        JLabel titleL = new JLabel("UDrawingPaper");
        titleL.setLayout(null);
        titleL.setVisible(true);
        titleL.setForeground(Color.BLACK);
        titleL.setBounds(350,10,400,60);
        titleL.setFont(font);
        loginFrame.add(titleL);
        //label que dice ingresar el nombre de usuario/ Código
        JLabel usernameL = new JLabel("DPI: ");
        usernameL.setLayout(null);
        usernameL.setVisible(true);
        usernameL.setForeground(Color.BLACK);
        usernameL.setBounds(220,150,250,30);
        usernameL.setFont(font3);
        loginFrame.add(usernameL);
        //label que dice ingresar contraseña
        JLabel passwordL = new JLabel("PASSWORD: ");
        passwordL.setLayout(null);
        passwordL.setVisible(true);
        passwordL.setForeground(Color.BLACK);
        passwordL.setBounds(220,250,300,30);
        passwordL.setFont(font3);
        loginFrame.add(passwordL);
        
        
        //=========================Creación de los campos de texto=========================
        //Creamos el campo de texto que recibirá el nombre de usuario
        JTextField usernameTF = new JTextField();
        usernameTF.setLayout(null);
        usernameTF.setVisible(true);
        usernameTF.setBounds(450,145,350,40);
        usernameTF.setFont(font2);
        usernameTF.setBackground(Color.red);
        loginFrame.add(usernameTF);
        
        //Creamos el campo de texto que recibirá la contraseña
        JPasswordField passwordTF = new JPasswordField();
        passwordTF.setBounds(450,245,350,40);
        passwordTF.setVisible(true); 
        passwordTF.setLayout(null);
        passwordTF.setFont(font2);
        passwordTF.setBackground(Color.red);
        loginFrame.add(passwordTF);
        //=========================Creación del Botón Inicio de sesión=========================
        //Creamos un botón de inicio de Sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setLayout(null);
        loginButton.setVisible(true);
        loginButton.setBounds(380, 350, 300, 60);
        loginButton.setFont(font3);
        loginButton.setBackground(Color.yellow);
        loginButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e){  
                
                
                try {
                    avlTreeHandler.generateAVLTreeGraph();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        loginFrame.add(loginButton);
        
        
        //Creamos un botón de registro de usuarios
        JButton registerButton = new JButton("Regístrate!!");
        registerButton.setLayout(null);
        registerButton.setVisible(true);
        registerButton.setBounds(380, 450, 300, 60);
        registerButton.setFont(font3);
        registerButton.setBackground(Color.MAGENTA);
        registerButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e){  
                try {
                    loginFrame.dispose();
                    clientRegistration();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        loginFrame.add(registerButton);
        //=========================Generación del Repintado=========================
       
        /* Este método provoca una llamada al método de pintura de este componente
        lo antes posible. De lo contrario, este método provoca una llamada al
        método de actualización de este componente lo antes posible.*/
        loginFrame.repaint();
    }
    
    
    //El frame para registrarse, en caso de no estar registrado
    public static void clientRegistration() throws IOException {
            
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        
        
        //=========================Creación del Frame de la creación==============================
        
        //Se crea el frame y se le agrega un título
        JFrame clientsRegister = new JFrame("UDrawingPaper");
        clientsRegister.setLayout(null);
        
        //Se hace visible el frame
        clientsRegister.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        clientsRegister.setResizable(false);
        //Se le coloca un color de fondo al frame
        clientsRegister.getContentPane().setBackground(Color.BLUE);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        clientsRegister.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        clientsRegister.setSize(900,600);
        //Le agreamos una locación al frame admin
        clientsRegister.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        clientsRegister.setIconImage(myIcon);
        
    
      
        JLabel titleM = new JLabel("UDrawingPaper");
        titleM.setLayout(null);
        titleM.setVisible(true);
        titleM.setForeground(Color.BLACK);
        titleM.setBounds(320,20,450,60);
        titleM.setFont(font3);
        clientsRegister.add(titleM);
        
        //label que nos muestra el mensaje "DPI"
        JLabel dpi = new JLabel("DPI: ");
        dpi.setLayout(null);
        dpi.setVisible(true);
        dpi.setForeground(Color.BLACK);
        dpi.setBounds(50,100,450,60);
        dpi.setFont(font3);
        clientsRegister.add(dpi);
        //label que nos muestra el mensaje "Nombre"
        JLabel name = new JLabel("Nombre: ");
        name.setLayout(null);
        name.setVisible(true);
        name.setForeground(Color.BLACK);
        name.setBounds(50,200,450,60);
        name.setFont(font3);
        clientsRegister.add(name);
        //label que nos muestra el mensaje "Contraseña"
        JLabel pass = new JLabel("Contraseña: ");
        pass.setLayout(null);
        pass.setVisible(true);
        pass.setForeground(Color.BLACK);
        pass.setBounds(50,300,450,60);
        pass.setFont(font3);
        clientsRegister.add(pass);
        
        //=========================Creación de TextFields =========================
        
        
        //Creamos el campo de texto que recibirá el Código
        JTextField codeT = new JTextField();
        codeT.setLayout(null);
        codeT.setVisible(true);
        codeT.setBounds(300,110,400,40);
        codeT.setBackground(Color.red);
        codeT.setFont(font2);
        clientsRegister.add(codeT);
        
        //Creamos el campo de texto que recibirá el Nombre
        JTextField nameT = new JTextField();
        nameT.setLayout(null);
        nameT.setVisible(true);
        nameT.setBounds(300,210,400,40);
        nameT.setBackground(Color.red);
        nameT.setFont(font2);
        clientsRegister.add(nameT);
       
        //Creamos el campo de texto que recibirá el Contraseña
        JTextField passT = new JTextField();
        passT.setLayout(null);
        passT.setVisible(true);
        passT.setBounds(300,310,400,40);
        passT.setBackground(Color.red);
        passT.setFont(font2);
        clientsRegister.add(passT);
        
        
        
        
         //=========================Creación de los  Botones del frame creacion de profesores=========================
        //Creamos un botón de registro de clientes
        JButton registerButton = new JButton("");
        ImageIcon iconobtn = new ImageIcon("../imgUsadas/new.png");
        registerButton.setLayout(null);
        registerButton.setVisible(true);
        registerButton.setBounds(450, 470, 320, 60);
        registerButton.setBackground(Color.yellow);
        registerButton.setIcon(iconobtn);
        registerButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e4){  
                      
                          
            }      
        }); 
        
        clientsRegister.add(registerButton);
        
        //Creamos un botón de registro de clientes
        JButton exitButton = new JButton("");
        ImageIcon iconobtn2 = new ImageIcon("../imgUsadas/leave.png");
        exitButton.setLayout(null);
        exitButton.setVisible(true);
        exitButton.setBounds(80, 470, 320, 60);
        exitButton.setBackground(Color.magenta);
        exitButton.setIcon(iconobtn2);
        exitButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e4){  
                
                try {
                    clientsRegister.dispose();
                    loginFrameModule();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                          
            }      
        }); 
        
        clientsRegister.add(exitButton);
            
            
            
            
        }
    
}
