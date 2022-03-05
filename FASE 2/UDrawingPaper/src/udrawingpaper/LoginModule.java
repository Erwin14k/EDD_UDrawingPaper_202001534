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
import java.io.FileNotFoundException;
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
                    clientReports();
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
    
    
    public static void adminView() throws IOException {
            
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        
        
        //=========================Creación del Frame del Admin==============================
        
        //Se crea el frame y se le agrega un título
        JFrame adminView = new JFrame("UDrawing Paper");
        adminView.setLayout(null);
        
        //Se hace visible el frame
        adminView.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        adminView.setResizable(false);
        //Se le coloca un color de fondo al frame
        adminView.getContentPane().setBackground(Color.orange);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        adminView.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        adminView.setSize(1600,800);
        //Le agreamos una locación al frame admin
        adminView.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        adminView.setIconImage(myIcon);
        
        //=========================Creación de Labels =========================
        //label que nos muestra el mensaje "DTT"
        JLabel titleM = new JLabel("Listado Oficial De Clientes");
        titleM.setLayout(null);
        titleM.setVisible(true);
        titleM.setForeground(Color.BLACK);
        titleM.setBounds(90,20,600,60);
        titleM.setFont(font3);
        adminView.add(titleM);
 
       
        //Creamos un botón de Carga Masiva
        JButton bulkLoadButton = new JButton("Carga Masiva");
        bulkLoadButton.setLayout(null);
        bulkLoadButton.setVisible(true);
        bulkLoadButton.setBounds(20, 630, 300, 60);
        bulkLoadButton.setBackground(Color.yellow);
        bulkLoadButton.setFont(font3);
        bulkLoadButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(bulkLoadButton);
        
        //Botón para crear un nuevo cliente
        JButton newClient = new JButton("");
        ImageIcon iconobtn = new ImageIcon("../imgUsadas/new.png");
        newClient.setLayout(null);
        newClient.setVisible(true);
        newClient.setBounds(360, 630, 50, 60);
        newClient.setBackground(Color.green);
        newClient.setIcon(iconobtn);
        newClient.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(newClient);
        
        //Botón para eliminar un cliente
        JButton deleteClient = new JButton("");
        ImageIcon iconobtn2 = new ImageIcon("../imgUsadas/delete.png");
        deleteClient.setLayout(null);
        deleteClient.setVisible(true);
        deleteClient.setBounds(450, 630, 50, 60);
        deleteClient.setBackground(Color.red);
        deleteClient.setIcon(iconobtn2);
        deleteClient.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(deleteClient);
        
        //Botón para actualizar un cliente
        JButton updateClient = new JButton("");
        ImageIcon iconobtn3 = new ImageIcon("../imgUsadas/update2.png");
        updateClient.setLayout(null);
        updateClient.setVisible(true);
        updateClient.setBounds(540, 630, 50, 60);
        updateClient.setBackground(Color.magenta);
        updateClient.setIcon(iconobtn3);
        updateClient.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(updateClient);
        
        //Botón para cerrar sesión como admin
        JButton logOutButton = new JButton("");
        ImageIcon iconobtn4 = new ImageIcon("../imgUsadas/logout.png");
        logOutButton.setLayout(null);
        logOutButton.setVisible(true);
        logOutButton.setBounds(630, 630, 50, 60);
        logOutButton.setBackground(Color.red);
        logOutButton.setIcon(iconobtn4);
        logOutButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(logOutButton);
        
        //Botón para eliminar un nuevo cliente
        JButton graphView = new JButton("Ver Árbol De Clientes");
        graphView.setLayout(null);
        graphView.setVisible(true);
        graphView.setBounds(925, 630, 600, 60);
        graphView.setBackground(Color.white);
        graphView.setFont(font3);
        graphView.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(graphView);
        
        //Label para mostrar grafo
        JLabel graphLabel = new JLabel("");
        graphLabel.setLayout(null);
        graphLabel.setVisible(true);
        graphLabel.setForeground(Color.BLACK);
        graphLabel.setBounds(850,20,800,600);
        ImageIcon iconobtn5 = new ImageIcon("../imgUsadas/iconologin.png");
        graphLabel.setIcon(iconobtn5);
        JScrollPane graphScroll= new JScrollPane();
        graphScroll.setBounds(850,20,700,600);
        graphScroll.setViewportView(graphLabel);
        adminView.add(graphScroll);
        //=========================Creación de Tabla =========================
        
        int n=0;
        String[] header = {"DPI","Nombre","Password","No. Álbumes","No. Imágenes","No. Capas"}; 
        Object[][] clientArray;
        clientArray = new Object[1][6];
           
        JScrollPane clientsDataTableSC= new JScrollPane();
        clientsDataTableSC.getViewport().setBackground(Color.white);
        //Aignando el tamaño
        //Creando la tabla con los datos definidos anteriormente
        JTable clientable = new JTable(clientArray, header);
        clientable.getTableHeader().setBackground(Color.decode("#1D2A3B"));
        clientable.getTableHeader().setForeground(Color.WHITE);
        clientable.setBackground(Color.WHITE);
        clientsDataTableSC.setFont(font2);
        clientsDataTableSC.setViewportView(clientable);
        clientsDataTableSC.setBounds(20, 100, 700, 500);
        adminView.add(clientsDataTableSC);
        
      
       
        
        
        

            
            
            
            
        }
    
    public static void clientView() throws IOException {
            
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        
        
        //=========================Creación del Frame del Admin==============================
        
        //Se crea el frame y se le agrega un título
        JFrame adminView = new JFrame("UDrawing Paper");
        adminView.setLayout(null);
        
        //Se hace visible el frame
        adminView.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        adminView.setResizable(false);
        //Se le coloca un color de fondo al frame
        adminView.getContentPane().setBackground(Color.orange);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        adminView.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        adminView.setSize(1600,800);
        //Le agreamos una locación al frame admin
        adminView.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        adminView.setIconImage(myIcon);
        
 
       
        //Creamos un botón de Carga Masiva de imágenes
        JButton imgLoad = new JButton("Cargar Img");
        imgLoad.setLayout(null);
        imgLoad.setVisible(true);
        imgLoad.setBounds(20, 630, 300, 60);
        imgLoad.setBackground(Color.yellow);
        imgLoad.setFont(font3);
        imgLoad.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(imgLoad);
        //Creamos un botón de Carga Masiva de capas
        JButton layersLoad = new JButton("Cargar Capas");
        layersLoad.setLayout(null);
        layersLoad.setVisible(true);
        layersLoad.setBounds(20, 700, 300, 60);
        layersLoad.setBackground(Color.yellow);
        layersLoad.setFont(font3);
        layersLoad.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(layersLoad);
        
        //Creamos un botón de reportes
        JButton reportsButton = new JButton("Generar Reportes");
        reportsButton.setLayout(null);
        reportsButton.setVisible(true);
        reportsButton.setBounds(350, 700, 400, 60);
        reportsButton.setBackground(Color.blue);
        reportsButton.setFont(font3);
        reportsButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(reportsButton);
        
        
        //Botón para crear una nueva imagen
        JButton newImage = new JButton("");
        ImageIcon iconobtn = new ImageIcon("../imgUsadas/addImg.png");
        newImage.setLayout(null);
        newImage.setVisible(true);
        newImage.setBounds(440, 630, 50, 60);
        newImage.setBackground(Color.green);
        newImage.setIcon(iconobtn);
        newImage.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(newImage);
        
        //Botón para eliminar una imagen
        JButton deleteClient = new JButton("");
        ImageIcon iconobtn2 = new ImageIcon("../imgUsadas/delete.png");
        deleteClient.setLayout(null);
        deleteClient.setVisible(true);
        deleteClient.setBounds(530, 630, 50, 60);
        deleteClient.setBackground(Color.red);
        deleteClient.setIcon(iconobtn2);
        deleteClient.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(deleteClient);
        
        
        //Botón para cerrar sesión como cliente
        JButton logOutButton = new JButton("");
        ImageIcon iconobtn4 = new ImageIcon("../imgUsadas/logout.png");
        logOutButton.setLayout(null);
        logOutButton.setVisible(true);
        logOutButton.setBounds(620, 630, 50, 60);
        logOutButton.setBackground(Color.magenta);
        logOutButton.setIcon(iconobtn4);
        logOutButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(logOutButton);
        
        
        
        //Botón para eliminar un nuevo cliente
        JButton graphView = new JButton("Ver Árbol De Clientes");
        graphView.setLayout(null);
        graphView.setVisible(true);
        graphView.setBounds(925, 630, 600, 60);
        graphView.setBackground(Color.white);
        graphView.setFont(font3);
        graphView.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        adminView.add(graphView);
        
        //Label para mostrar grafo
        JLabel graphLabel = new JLabel("");
        graphLabel.setLayout(null);
        graphLabel.setVisible(true);
        graphLabel.setForeground(Color.BLACK);
        graphLabel.setBounds(850,20,800,600);
        ImageIcon iconobtn5 = new ImageIcon("../imgUsadas/iconologin.png");
        graphLabel.setIcon(iconobtn5);
        JScrollPane graphScroll= new JScrollPane();
        graphScroll.setBounds(850,20,700,600);
        graphScroll.setViewportView(graphLabel);
        adminView.add(graphScroll);
        
        
      
       
        
        
        

            
            
            
            
        }
    
    public static void clientReports() throws IOException {
            
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        
        
        //=========================Creación del Frame del Admin==============================
        
        //Se crea el frame y se le agrega un título
        JFrame clientsReports = new JFrame("UDrawing Paper");
        clientsReports.setLayout(null);
        
        //Se hace visible el frame
        clientsReports.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        clientsReports.setResizable(false);
        //Se le coloca un color de fondo al frame
        clientsReports.getContentPane().setBackground(Color.orange);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        clientsReports.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        clientsReports.setSize(1600,800);
        //Le agreamos una locación al frame admin
        clientsReports.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        clientsReports.setIconImage(myIcon);
        
        //=========================Creación de Labels =========================
        //label que nos muestra el mensaje "DTT"
        JLabel titleM = new JLabel("TOP 5 IMÁGENES CON MÁS CAPAS");
        titleM.setLayout(null);
        titleM.setVisible(true);
        titleM.setForeground(Color.BLACK);
        titleM.setBounds(90,20,600,60);
        titleM.setFont(font3);
        clientsReports.add(titleM);

        //=========================Creación de Tabla =========================
        
        int n=0;
        String[] header = {"Posición","Id Imágen","No. Capas"}; 
        Object[][] clientArray;
        clientArray = new Object[1][3];
           
        JScrollPane clientsDataTableSC= new JScrollPane();
        clientsDataTableSC.getViewport().setBackground(Color.white);
        //Aignando el tamaño
        //Creando la tabla con los datos definidos anteriormente
        JTable clientable = new JTable(clientArray, header);
        clientable.getTableHeader().setBackground(Color.decode("#1D2A3B"));
        clientable.getTableHeader().setForeground(Color.WHITE);
        clientable.setBackground(Color.WHITE);
        clientsDataTableSC.setFont(font2);
        clientsDataTableSC.setViewportView(clientable);
        clientsDataTableSC.setBounds(140, 100, 400, 200);
        clientsReports.add(clientsDataTableSC);
        
        
        //Label para mostrar Titulo Pre orden
        JLabel preOrderTitle = new JLabel("Capas En Preorden: ");
        preOrderTitle.setLayout(null);
        preOrderTitle.setVisible(true);
        preOrderTitle.setForeground(Color.BLACK);
        preOrderTitle.setBounds(50,330,500,60);
        preOrderTitle.setFont(font3);
        clientsReports.add(preOrderTitle);
        
        //Label para mostrar Titulo In orden
        JLabel inOrderTitle = new JLabel("Capas En Inorden: ");
        inOrderTitle.setLayout(null);
        inOrderTitle.setVisible(true);
        inOrderTitle.setForeground(Color.BLACK);
        inOrderTitle.setBounds(50,480,500,60);
        inOrderTitle.setFont(font3);
        clientsReports.add(inOrderTitle);
        
        //Label para mostrar Titulo postOrden
        JLabel postOrderTitle = new JLabel("Capas En Postorden: ");
        postOrderTitle.setLayout(null);
        postOrderTitle.setVisible(true);
        postOrderTitle.setForeground(Color.BLACK);
        postOrderTitle.setBounds(50,630,500,60);
        postOrderTitle.setFont(font3);
        clientsReports.add(postOrderTitle);
        
        //Label que muestra las capas en preorden
        JLabel preOrder = new JLabel("¡Nada que mostrar! ");
        preOrder.setLayout(null);
        preOrder.setVisible(true);
        preOrder.setForeground(Color.BLACK);
        preOrder.setBounds(50,380,500,50);
        preOrder.setFont(font2);
        JScrollPane preOrderScroll= new JScrollPane();
        preOrderScroll.setBounds(50,380,500,50);
        preOrderScroll.setViewportView(preOrder);
        clientsReports.add(preOrderScroll);
        
        //Label que muestra las capas en inorden
        JLabel inOrder = new JLabel("¡Nada que mostrar! ");
        inOrder.setLayout(null);
        inOrder.setVisible(true);
        inOrder.setForeground(Color.BLACK);
        inOrder.setBounds(50,530,500,50);
        inOrder.setFont(font2);
        JScrollPane inOrderScroll= new JScrollPane();
        inOrderScroll.setBounds(50,530,500,50);
        inOrderScroll.setViewportView(inOrder);
        clientsReports.add(inOrderScroll);
        
        //Label que muestra las capas en postorden
        JLabel postOrder = new JLabel("¡Nada que mostrar! ");
        postOrder.setLayout(null);
        postOrder.setVisible(true);
        postOrder.setForeground(Color.BLACK);
        postOrder.setBounds(50,680,500,50);
        postOrder.setFont(font2);
        JScrollPane postOrderScroll= new JScrollPane();
        postOrderScroll.setBounds(50,680,500,50);
        postOrderScroll.setViewportView(postOrder);
        clientsReports.add(postOrderScroll);
        
        //Label para mostrar Titulo profundidad árbol de capas
        JLabel depthTitle = new JLabel("Profundidad Árbol De Capas: ");
        depthTitle.setLayout(null);
        depthTitle.setVisible(true);
        depthTitle.setForeground(Color.BLACK);
        depthTitle.setBounds(800,330,700,60);
        depthTitle.setFont(font3);
        clientsReports.add(depthTitle);
        
        JLabel depthL = new JLabel("¡Nada que mostrar! ");
        depthL.setLayout(null);
        depthL.setVisible(true);
        depthL.setForeground(Color.BLACK);
        depthL.setBounds(800,380,500,50);
        depthL.setFont(font2);
        JScrollPane depthScroll= new JScrollPane();
        depthScroll.setBounds(800,380,500,50);
        depthScroll.setViewportView(depthL);
        clientsReports.add(depthScroll);
        
        //Label para mostrar Titulo capas que son hojas
        JLabel leafTitle = new JLabel("Capas Que Son Hojas: ");
        leafTitle.setLayout(null);
        leafTitle.setVisible(true);
        leafTitle.setForeground(Color.BLACK);
        leafTitle.setBounds(800,480,500,60);
        leafTitle.setFont(font3);
        clientsReports.add(leafTitle);
        
        
        JLabel leaf = new JLabel("¡Nada que mostrar! ");
        leaf.setLayout(null);
        leaf.setVisible(true);
        leaf.setForeground(Color.BLACK);
        leaf.setBounds(800,380,500,50);
        leaf.setFont(font2);
        JScrollPane leafScroll= new JScrollPane();
        leafScroll.setBounds(800,530,500,50);
        leafScroll.setViewportView(leaf);
        clientsReports.add(leafScroll);
        
        
        //Creamos un botón de reportes
        JButton reportsButton = new JButton("Generar Reportes");
        reportsButton.setLayout(null);
        reportsButton.setVisible(true);
        reportsButton.setBounds(900, 630, 400, 60);
        reportsButton.setBackground(Color.blue);
        reportsButton.setFont(font3);
        reportsButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        clientsReports.add(reportsButton);
      
       
        
        
        

            
            
            
            
        }
    
}
