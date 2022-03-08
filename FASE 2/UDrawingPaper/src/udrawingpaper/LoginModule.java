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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;

/**
 *
 * @author Erwin14k
 */
public class LoginModule {
    //Variable importantísima, ya que nos indica que usuario está loggeado.
    static BigInteger userLogged;
    //Instancias que se usarán en la ejecución de la aplicación
    static BinarySearchTree bstTreeHandler= new BinarySearchTree();
    static SelfBalancingTree avlTreeHandler= new SelfBalancingTree();
    static ClientList clientListHandler = new ClientList();
    static Position positionHandler= new Position();
    //Objeto de tipo admin, este objeto es para validar el inicio de sesión como admin
    static BigInteger adminCode=new BigInteger("0");
    static Admin admin=new Admin("admin","EDD2022",adminCode);
    //Variables que almacenan el texto de los json antes de ser parseados
    public static String clientsJsonContent="";
    public static String imagesJsonContent="";
    public static String layersJsonContent="";
    public static String albumsJsonContent="";
    
    
    
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
                
                if((usernameTF.getText().equals(admin.getUser()))&&(passwordTF.getText().equals(admin.getPassword()))){
                    System.out.println(usernameTF.getText()+"--"+admin.getUser());
                    try {
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Inicio De Sesión Exitoso Admin!!</p></html>" );
                        loginFrame.dispose();
                        userLogged=admin.getCode();
                        adminView();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    if(usernameTF.getText().matches("[0-9]+")){
                        BigInteger dpi=new BigInteger(usernameTF.getText());
                        if(clientListHandler.loginValidation(dpi,passwordTF.getText())==true){
                            
                            String tempName=clientListHandler.nameByDpi(dpi);
                            JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Le Damos La Bienvenida A: </p><center><p style=\"color:blue; font:20px;\">"+tempName+"</p></center></html>" );
                            userLogged=dpi;
                            loginFrame.dispose();
                            try {
                                clientView();
                            } catch (IOException ex) {
                                Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                            }
                
                        }else{
                            JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">Dpi / Contraseña Incorrectos, Intente De Nuevo!!</p></html>" );
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">Dpi / Contraseña Incorrectos, Intente De Nuevo!!</p></html>" );
                    }    
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
        clientsRegister.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
                if(codeT.getText().matches("[0-9]+")){
                    if((!nameT.getText().equals(""))&&(!passT.getText().equals(""))){
                        BigInteger temp=new BigInteger(codeT.getText());
                        SelfBalancingTree tempSelfBalancingTree = new SelfBalancingTree();
                        BinarySearchTree tempBinarySearchTree= new BinarySearchTree();
                        Client newClient= new Client(temp,nameT.getText(),passT.getText(),tempSelfBalancingTree,0,0,0,tempBinarySearchTree);
                        clientListHandler.finalInsert(newClient);
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Cliente Registrad@ Con Éxito!!</p></html>" );
                        clientsRegister.dispose();
                        try {
                            loginFrameModule();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">Debes llenar todos los campos, intenta de nuevo!!</p></html>" );
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">El dpi tiene que ser un número entero, intenta de nuevo!!</p></html>" );
                }
                
                      
                          
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
        
        clientsRegister.repaint();
            
            
            
            
        }
    
    //El frame que visualiza el admin al estar loggeado.
    public static void adminView() throws IOException {
            
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        Font font4 =new Font("Arial",Font.BOLD,18);
        
        
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
        adminView.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
                adminView.dispose();
                clientsCreation();
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
                adminView.dispose();
                deleteClient();
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
                adminView.dispose();
                updateClient();
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
                adminView.dispose();
                try {
                    loginFrameModule();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        String[] header = {"DPI","Nombre","Password","No. Álbumes","No. Img","Capas"}; 
        JTable clientTable = new JTable(clientListHandler.returnClientsData(), header);
        JScrollPane clientsDataTableSC= new JScrollPane(clientTable);
        
        clientsDataTableSC.getViewport().setBackground(Color.white);
        //Aignando el tamaño
        //Creando la tabla con los datos definidos anteriormente
        
        clientTable.getTableHeader().setBackground(Color.decode("#1D2A3B"));
        clientTable.getTableHeader().setForeground(Color.WHITE);
        clientTable.getTableHeader().setFont(font4);
        clientTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        clientTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        clientTable.getColumnModel().getColumn(2).setPreferredWidth(90);
        clientTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        clientTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        clientTable.getColumnModel().getColumn(5).setPreferredWidth(60);
        clientTable.setRowHeight(40);
        clientTable.setFont(font4);
        
        
        //clientable.setBackground(Color.WHITE);
        clientsDataTableSC.setBounds(20, 100, 800, 500);
        clientsDataTableSC.setViewportView(clientTable);
        
        adminView.add(clientsDataTableSC);
      
        
        //Creamos un botón de Carga Masiva
        JButton bulkLoadButton = new JButton("Carga Masiva");
        bulkLoadButton.setLayout(null);
        bulkLoadButton.setVisible(true);
        bulkLoadButton.setBounds(20, 630, 300, 60);
        bulkLoadButton.setBackground(Color.yellow);
        bulkLoadButton.setFont(font3);
        bulkLoadButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                adminView.dispose();
                readClientsJson();
                clientsBulkLoad();
                System.out.println("Los clientes ingresados por carga Masiva:");
                clientListHandler.travel();
                System.out.println("=========================================");
                System.out.println("\n\n\n");
                try {
                    adminView();
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
        }); 
        adminView.add(bulkLoadButton);
        
      
       
        
        
        

            
            
            
            
        }
    //El frame que visualiza el cliente al estar loggeado.
    public static void clientView() throws IOException {
            
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        
        
        //=========================Creación del Frame del Admin==============================
        
        //Se crea el frame y se le agrega un título
        JFrame clientView = new JFrame("UDrawing Paper");
        clientView.setLayout(null);
        
        //Se hace visible el frame
        clientView.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        clientView.setResizable(false);
        //Se le coloca un color de fondo al frame
        clientView.getContentPane().setBackground(Color.orange);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        clientView.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        clientView.setSize(1600,800);
        //Le agreamos una locación al frame admin
        clientView.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        clientView.setIconImage(myIcon);
        
        JLabel titleM = new JLabel("Cliente: "+clientListHandler.nameByDpi(userLogged));
        titleM.setLayout(null);
        titleM.setVisible(true);
        titleM.setForeground(Color.BLACK);
        titleM.setBounds(30,10,800,60);
        titleM.setFont(font2);
        clientView.add(titleM);
        
        JLabel layerLabel = new JLabel("Capa En Específico: ");
        layerLabel.setLayout(null);
        layerLabel.setVisible(true);
        layerLabel.setForeground(Color.BLACK);
        layerLabel.setBounds(90,120,800,60);
        layerLabel.setFont(font3);
        clientView.add(layerLabel);
        
        JLabel imgLabel = new JLabel("Imágen con árbol de capas: ");
        imgLabel.setLayout(null);
        imgLabel.setVisible(true);
        imgLabel.setForeground(Color.BLACK);
        imgLabel.setBounds(90,260,800,60);
        imgLabel.setFont(font3);
        clientView.add(imgLabel);
        
        //Creamos el campo de texto que despliega los id de las capas
        JComboBox idLayers= new JComboBox ();
        idLayers.setModel(new javax.swing.DefaultComboBoxModel<>());       
        idLayers.setLayout(null);
        idLayers.setVisible(true);
        idLayers.setBounds(90,200,400,30);
        clientView.add(idLayers);
        
        
        JButton layerReportButton = new JButton("");
        ImageIcon iconobtn = new ImageIcon("../imgUsadas/search.png");
        layerReportButton.setLayout(null);
        layerReportButton.setVisible(true);
        layerReportButton.setBounds(520, 180, 50, 60);
        layerReportButton.setBackground(Color.green);
        layerReportButton.setIcon(iconobtn);
        layerReportButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
            }
        }); 
        clientView.add(layerReportButton);
        
        //Creamos el campo de texto que despliega los id de las imágenes
        JComboBox idImages= new JComboBox ();
        idImages.setModel(new javax.swing.DefaultComboBoxModel<>());       
        idImages.setLayout(null);
        idImages.setVisible(true);
        idImages.setBounds(90,340,400,30);
        clientView.add(idImages);
        
        JButton imageTreeReport = new JButton("");
        ImageIcon iconobtn9 = new ImageIcon("../imgUsadas/search.png");
        imageTreeReport.setLayout(null);
        imageTreeReport.setVisible(true);
        imageTreeReport.setBounds(520, 320, 50, 60);
        imageTreeReport.setBackground(Color.green);
        imageTreeReport.setIcon(iconobtn9);
        imageTreeReport.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
            }
        }); 
        clientView.add(imageTreeReport);
        
       
        //Creamos un botón de Carga Masiva de imágenes
        JButton imgLoad = new JButton("Cargar Img");
        imgLoad.setLayout(null);
        imgLoad.setVisible(true);
        imgLoad.setBounds(20, 630, 300, 60);
        imgLoad.setBackground(Color.yellow);
        imgLoad.setFont(font3);
        imgLoad.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                readImagesJson();
                imagesBulkLoad();
            }
        }); 
        clientView.add(imgLoad);
        //Creamos un botón de Carga Masiva de capas
        JButton layersLoad = new JButton("Cargar Capas");
        layersLoad.setLayout(null);
        layersLoad.setVisible(true);
        layersLoad.setBounds(20, 700, 300, 60);
        layersLoad.setBackground(Color.yellow);
        layersLoad.setFont(font3);
        layersLoad.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
                clientView.dispose();
                readLayersJson();
                layersBulkLoad();
                try {
                    clientView();
                    clientListHandler.graphClientTree(userLogged);
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        clientView.add(layersLoad);
        
        //Creamos un botón de reportes
        JButton reportsButton = new JButton("Generar Reportes");
        reportsButton.setLayout(null);
        reportsButton.setVisible(true);
        reportsButton.setBounds(350, 700, 400, 60);
        reportsButton.setBackground(Color.blue);
        reportsButton.setFont(font3);
        reportsButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
                clientView.dispose();
                try {
                    clientReports();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        clientView.add(reportsButton);
        
        
        //Botón para crear una nueva imagen
        JButton newImage = new JButton("");
        ImageIcon iconobtn2 = new ImageIcon("../imgUsadas/addImg.png");
        newImage.setLayout(null);
        newImage.setVisible(true);
        newImage.setBounds(440, 630, 50, 60);
        newImage.setBackground(Color.green);
        newImage.setIcon(iconobtn2);
        newImage.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
                clientView.dispose();
                imageCreation();
            }
        }); 
        clientView.add(newImage);
        
        //Botón para eliminar una imagen
        JButton deleteClient = new JButton("");
        ImageIcon iconobtn7 = new ImageIcon("../imgUsadas/delete.png");
        deleteClient.setLayout(null);
        deleteClient.setVisible(true);
        deleteClient.setBounds(530, 630, 50, 60);
        deleteClient.setBackground(Color.red);
        deleteClient.setIcon(iconobtn7);
        deleteClient.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
                clientView.dispose();
                deleteImage();
            }
        }); 
        clientView.add(deleteClient);
        
        
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
                clientView.dispose();
                try {
                    loginFrameModule();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        clientView.add(logOutButton);
        
        
        
        //Botón para mostrar el grafo Avl de imágenes del cliente.
        JButton avlImgButton = new JButton("Img");
        avlImgButton.setLayout(null);
        avlImgButton.setVisible(true);
        avlImgButton.setBounds(940, 630, 150, 60);
        avlImgButton.setBackground(Color.white);
        avlImgButton.setFont(font3);
        avlImgButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        clientView.add(avlImgButton);
        
        //Botón para mostrar el grafo ABB de capas del cliente.
        JButton abbImgButton = new JButton("Capas");
        abbImgButton.setLayout(null);
        abbImgButton.setVisible(true);
        abbImgButton.setBounds(1115, 630, 150, 60);
        abbImgButton.setBackground(Color.white);
        abbImgButton.setFont(font3);
        abbImgButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        clientView.add(abbImgButton);
        
        //Botón para mostrar la lista circular doblemente enlazada de álbumes del cliente
        JButton albumImgButton = new JButton("Álbums");
        albumImgButton.setLayout(null);
        albumImgButton.setVisible(true);
        albumImgButton.setBounds(1300, 630, 200, 60);
        albumImgButton.setBackground(Color.white);
        albumImgButton.setFont(font3);
        albumImgButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
            }
        }); 
        clientView.add(albumImgButton);
        
        //Label para mostrar los grafos y reportes
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
        clientView.add(graphScroll);
        
        clientView.repaint();

            
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
        clientsReports.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
      
       
        //Label para mostrar imágen para decoración
        JLabel ckDecoration = new JLabel("");
        ImageIcon iconobtn = new ImageIcon("../imgUsadas/ckdecoration.png");
        ckDecoration.setLayout(null);
        ckDecoration.setVisible(true);
        ckDecoration.setForeground(Color.BLACK);
        ckDecoration.setBounds(700, 10, 800, 300);
        ckDecoration.setIcon(iconobtn);
        clientsReports.add(ckDecoration);
        
        
        //Creamos el boton que regresará de ventana
        ImageIcon iconobtn2 = new ImageIcon("../imgUsadas/leave.png");
        JButton returnButton = new JButton("");
        returnButton.setLayout(null);
        returnButton.setVisible(true);
        returnButton.setBounds(810, 630, 60, 60);
        returnButton.setBackground(Color.magenta);
        returnButton.setIcon(iconobtn2);
        returnButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent buscarProfesorPorCodigo){  
                clientsReports.dispose();
                try {
                    clientView();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
            }
        }); 
        clientsReports.add(returnButton);
        
        clientsReports.repaint();
        

            
            
            
            
        }
    
    public static void clientsCreation(){
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        
        
        //=========================Creación del Frame de la creación==============================
        
        //Se crea el frame y se le agrega un título
        JFrame clientsCreation = new JFrame("UDrawingPaper");
        clientsCreation.setLayout(null);
        
        //Se hace visible el frame
        clientsCreation.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        clientsCreation.setResizable(false);
        //Se le coloca un color de fondo al frame
        clientsCreation.getContentPane().setBackground(Color.BLUE);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        clientsCreation.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        clientsCreation.setSize(900,600);
        //Le agreamos una locación al frame admin
        clientsCreation.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        clientsCreation.setIconImage(myIcon);
        
    
      
        JLabel titleM = new JLabel("UDrawingPaper");
        titleM.setLayout(null);
        titleM.setVisible(true);
        titleM.setForeground(Color.BLACK);
        titleM.setBounds(320,20,450,60);
        titleM.setFont(font3);
        clientsCreation.add(titleM);
        
        //label que nos muestra el mensaje "DPI"
        JLabel dpi = new JLabel("DPI: ");
        dpi.setLayout(null);
        dpi.setVisible(true);
        dpi.setForeground(Color.BLACK);
        dpi.setBounds(50,100,450,60);
        dpi.setFont(font3);
        clientsCreation.add(dpi);
        //label que nos muestra el mensaje "Nombre"
        JLabel name = new JLabel("Nombre: ");
        name.setLayout(null);
        name.setVisible(true);
        name.setForeground(Color.BLACK);
        name.setBounds(50,200,450,60);
        name.setFont(font3);
        clientsCreation.add(name);
        //label que nos muestra el mensaje "Contraseña"
        JLabel pass = new JLabel("Contraseña: ");
        pass.setLayout(null);
        pass.setVisible(true);
        pass.setForeground(Color.BLACK);
        pass.setBounds(50,300,450,60);
        pass.setFont(font3);
        clientsCreation.add(pass);
        
        //=========================Creación de TextFields =========================
        
        
        //Creamos el campo de texto que recibirá el Código
        JTextField codeT = new JTextField();
        codeT.setLayout(null);
        codeT.setVisible(true);
        codeT.setBounds(300,110,400,40);
        codeT.setBackground(Color.red);
        codeT.setFont(font2);
        clientsCreation.add(codeT);
        
        //Creamos el campo de texto que recibirá el Nombre
        JTextField nameT = new JTextField();
        nameT.setLayout(null);
        nameT.setVisible(true);
        nameT.setBounds(300,210,400,40);
        nameT.setBackground(Color.red);
        nameT.setFont(font2);
        clientsCreation.add(nameT);
       
        //Creamos el campo de texto que recibirá el Contraseña
        JTextField passT = new JTextField();
        passT.setLayout(null);
        passT.setVisible(true);
        passT.setBounds(300,310,400,40);
        passT.setBackground(Color.red);
        passT.setFont(font2);
        clientsCreation.add(passT);
        
        
        
        
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
                if(codeT.getText().matches("[0-9]+")){
                    if((!nameT.getText().equals(""))&&(!passT.getText().equals(""))){
                        BigInteger temp=new BigInteger(codeT.getText());
                        SelfBalancingTree tempSelfBalancingTree = new SelfBalancingTree();
                        BinarySearchTree tempBinary=new BinarySearchTree();
                        Client newClient= new Client(temp,nameT.getText(),passT.getText(),tempSelfBalancingTree,0,0,0,tempBinary);
                        clientListHandler.finalInsert(newClient);
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Cliente Registrad@ Con Éxito!!</p></html>" );
                        clientsCreation.dispose();
                        try {
                            adminView();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">Debes llenar todos los campos, intenta de nuevo!!</p></html>" );
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">El dpi tiene que ser un número entero, intenta de nuevo!!</p></html>" );
                }
                          
            }      
        }); 
        
        clientsCreation.add(registerButton);
        
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
                    clientsCreation.dispose();
                    adminView();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                          
            }      
        }); 
        
        clientsCreation.add(exitButton);
        
        clientsCreation.repaint();
        
    }
    
    public static  void deleteClient(){
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        
        
        //=========================Creación del Frame de la actualización==============================
        
        //Se crea el frame y se le agrega un título
        JFrame deleteClient = new JFrame("UDrawing Paper");
        deleteClient.setLayout(null);
        
        //Se hace visible el frame
        deleteClient.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        deleteClient.setResizable(false);
        //Se le coloca un color de fondo al frame
        deleteClient.getContentPane().setBackground(Color.ORANGE);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        deleteClient.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        deleteClient.setSize(900,300);
        //Le agreamos una locación al frame admin
        deleteClient.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        deleteClient.setIconImage(myIcon);
        
        
      
        
        
        
        
        
        
        //=========================Creación de Labels =========================
        JLabel titleM = new JLabel("Eliminar Cliente");
        titleM.setLayout(null);
        titleM.setVisible(true);
        titleM.setForeground(Color.BLACK);
        titleM.setBounds(300,20,600,60);
        titleM.setFont(font3);
        deleteClient.add(titleM);
        
        //label que nos muestra el mensaje "Código"
        JLabel code = new JLabel("Dpi a eliminar:");
        code.setLayout(null);
        code.setVisible(true);
        code.setForeground(Color.BLACK);
        code.setBounds(10,100,450,60);
        code.setFont(font3);
        deleteClient.add(code);
        
        JLabel imgggg = new JLabel("");
        ImageIcon icon= new ImageIcon("../imgUsadas/search.png");
        imgggg.setLayout(null);
        imgggg.setVisible(true);
        imgggg.setForeground(Color.BLACK);
        imgggg.setBounds(720,100,60,60);
        imgggg.setFont(font3);
        imgggg.setIcon(icon);
        deleteClient.add(imgggg);
       
        
        
        
        //Creamos el campo de texto que despliega los dpi
        JComboBox dpiJ= new JComboBox ();
        dpiJ.setModel(new javax.swing.DefaultComboBoxModel<>());       
        dpiJ.setLayout(null);
        dpiJ.setVisible(true);
        dpiJ.setBounds(300,110,400,30);
        deleteClient.add(dpiJ);
        deleteClient.repaint();
        
        
        
        
          //=========================Creación de los  Botones del frame creacion de profesores=========================
        
        //Creamos el boton que buscará que eliminara al cliente seleccionado
        ImageIcon iconobtn = new ImageIcon("../imgUsadas/delete.png");
        JButton deleteButton = new JButton("");
        deleteButton.setLayout(null);
        deleteButton.setVisible(true);
        deleteButton.setBounds(480, 200, 50, 50);
        deleteButton.setBackground(Color.red);
        deleteButton.setIcon(iconobtn);
        deleteButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent buscarProfesorPorCodigo){  
                        
                        
            }
        }); 
        deleteClient.add(deleteButton);
        
        //Creamos el boton que regresará de ventana
        ImageIcon iconobtn2 = new ImageIcon("../imgUsadas/leave.png");
        JButton returnButton = new JButton("");
        returnButton.setLayout(null);
        returnButton.setVisible(true);
        returnButton.setBounds(400, 200, 50, 50);
        returnButton.setBackground(Color.magenta);
        returnButton.setIcon(iconobtn2);
        returnButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent buscarProfesorPorCodigo){  
                deleteClient.dispose();
                try {
                    adminView();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
            }
        }); 
        deleteClient.add(returnButton);
        
        
        deleteClient.repaint();
    }
    
    public static void updateClient(){
        
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,50);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        
        //=========================Creación del Frame de la actualización==============================
        
        //Se crea el frame y se le agrega un título
        JFrame updateClient = new JFrame("UDrawing Paper");
        updateClient.setLayout(null);
        
        //Se hace visible el frame
        updateClient.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        updateClient.setResizable(false);
        //Se le coloca un color de fondo al frame
        updateClient.getContentPane().setBackground(Color.ORANGE);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        updateClient.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        updateClient.setSize(900,550);
        //Le agreamos una locación al frame admin
        updateClient.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        updateClient.setIconImage(myIcon);
        
        
      
        
        
        
        
        
        
        //=========================Creación de Labels =========================
        //label que nos muestra el mensaje "Actualizar Datos Del Cliente"
        JLabel titleM = new JLabel("Actualizar Datos Del Cliente");
        titleM.setLayout(null);
        titleM.setVisible(true);
        titleM.setForeground(Color.BLACK);
        titleM.setBounds(190,20,600,60);
        titleM.setFont(font3);
        updateClient.add(titleM);
        
        //label que nos muestra el mensaje "Dpi"
        JLabel code = new JLabel("Dpi:");
        code.setLayout(null);
        code.setVisible(true);
        code.setForeground(Color.BLACK);
        code.setBounds(100,100,450,60);
        code.setFont(font2);
        updateClient.add(code);
        //label que nos muestra el mensaje "Nombre"
        JLabel name = new JLabel("Nombre:");
        name.setLayout(null);
        name.setVisible(true);
        name.setForeground(Color.BLACK);
        name.setBounds(100,200,450,60);
        name.setFont(font2);
        updateClient.add(name);
        //label que nos muestra el mensaje "contraseña"
        JLabel password = new JLabel("contraseña:");
        password.setLayout(null);
        password.setVisible(true);
        password.setForeground(Color.BLACK);
        password.setBounds(100,300,450,60);
        password.setFont(font2);
        updateClient.add(password);
        
      
        
     
        
        //=========================Creación de TextFields =========================
        
        
        //Creamos el campo de texto que despliega los dpi
        JComboBox codeT= new JComboBox ();
        codeT.setModel(new javax.swing.DefaultComboBoxModel<>());       
        codeT.setLayout(null);
        codeT.setVisible(true);
        codeT.setBounds(300,110,400,30);
        updateClient.add(codeT);
        updateClient.repaint();
        
        //Creamos el campo de texto que recibirá el Nombre
        JTextField nameT = new JTextField();
        nameT.setLayout(null);
        nameT.setVisible(true);
        nameT.setBounds(300,210,400,30);
        updateClient.add(nameT);
        
        //Creamos el campo de texto que recibirá la contraseña
        JTextField passwordT = new JTextField();
        passwordT.setLayout(null);
        passwordT.setVisible(true);
        passwordT.setBounds(300,310,400,30);
        updateClient.add(passwordT);
    
 
               

        //Creamos un botón de creación de buscar dpi de cliente para buscar datos
        ImageIcon iconobtn = new ImageIcon("../imgUsadas/search.png");
        JButton searchButton = new JButton("");
        searchButton.setLayout(null);
        searchButton.setVisible(true);
        searchButton.setBounds(770, 100, 50, 50);
        searchButton.setBackground(Color.LIGHT_GRAY);
        searchButton.setIcon(iconobtn);
        searchButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e8){  
                        
                        
            }
        }); 
        updateClient.add(searchButton);
        updateClient.repaint();
        
        //Creamos un botón de actualización de clientes
        JButton updateButton = new JButton("");
        ImageIcon iconobtnw = new ImageIcon("../imgUsadas/update2.png");
        updateButton.setLayout(null);
        updateButton.setVisible(true);
        updateButton.setBounds(500, 400, 60, 60);
        updateButton.setBackground(Color.green);
        updateButton.setIcon(iconobtnw);
        updateButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e4){  
                        
                      
            }
        }); 
        updateClient.add(updateButton);
        updateClient.repaint();
        
        //Creamos un botón de actualización de clientes
        JButton returnButton = new JButton("");
        ImageIcon iconobtnw2 = new ImageIcon("../imgUsadas/leave.png");
        returnButton.setLayout(null);
        returnButton.setVisible(true);
        returnButton.setBounds(400, 400, 60, 60);
        returnButton.setBackground(Color.magenta);
        returnButton.setIcon(iconobtnw2);
        returnButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e7){  
                updateClient.dispose();
                try {
                    adminView();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                      
            }
        }); 
        updateClient.add(returnButton);
        updateClient.repaint();
    }
    
    public static void imageCreation(){
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        
        
        //=========================Creación del Frame de la creación==============================
        
        //Se crea el frame y se le agrega un título
        JFrame imageCreation = new JFrame("UDrawingPaper");
        imageCreation.setLayout(null);
        
        //Se hace visible el frame
        imageCreation.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        imageCreation.setResizable(false);
        //Se le coloca un color de fondo al frame
        imageCreation.getContentPane().setBackground(Color.BLUE);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        imageCreation.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        imageCreation.setSize(900,600);
        //Le agreamos una locación al frame admin
        imageCreation.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        imageCreation.setIconImage(myIcon);
        
    
      
        JLabel titleM = new JLabel("UDrawingPaper");
        titleM.setLayout(null);
        titleM.setVisible(true);
        titleM.setForeground(Color.BLACK);
        titleM.setBounds(320,20,450,60);
        titleM.setFont(font3);
        imageCreation.add(titleM);
        
        //label que nos muestra el mensaje "DPI"
        JLabel id = new JLabel("Id: ");
        id.setLayout(null);
        id.setVisible(true);
        id.setForeground(Color.BLACK);
        id.setBounds(50,100,450,60);
        id.setFont(font3);
        imageCreation.add(id);
        //label que nos muestra el mensaje "Nombre"
        JLabel layers = new JLabel("Capas (Por Comas): ");
        layers.setLayout(null);
        layers.setVisible(true);
        layers.setForeground(Color.BLACK);
        layers.setBounds(50,200,450,60);
        layers.setFont(font3);
        imageCreation.add(layers);
    
        
        //=========================Creación de TextFields =========================
        
        
        //Creamos el campo de texto que recibirá el Código
        JTextField idT = new JTextField();
        idT.setLayout(null);
        idT.setVisible(true);
        idT.setBounds(400,110,400,40);
        idT.setBackground(Color.red);
        idT.setFont(font2);
        imageCreation.add(idT);
        
        //Creamos el campo de texto que recibirá el Nombre
        JTextField layersT = new JTextField();
        layersT.setLayout(null);
        layersT.setVisible(true);
        layersT.setBounds(400,210,400,40);
        layersT.setBackground(Color.red);
        layersT.setFont(font2);
        imageCreation.add(layersT);
    
        
        
        
        
      
        JButton registerButton = new JButton("");
        ImageIcon iconobtn = new ImageIcon("../imgUsadas/addimg.png");
        registerButton.setLayout(null);
        registerButton.setVisible(true);
        registerButton.setBounds(450, 470, 320, 60);
        registerButton.setBackground(Color.yellow);
        registerButton.setIcon(iconobtn);
        registerButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e4){  
                      
                          
            }      
        }); 
        
        imageCreation.add(registerButton);
        
      
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
                    imageCreation.dispose();
                    clientView();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                          
            }      
        }); 
        
        imageCreation.add(exitButton);
        
        imageCreation.repaint();
        
    }
    
    public static void deleteImage(){
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        
        
        //=========================Creación del Frame de la actualización==============================
        
        //Se crea el frame y se le agrega un título
        JFrame deleteImage = new JFrame("UDrawing Paper");
        deleteImage.setLayout(null);
        
        //Se hace visible el frame
        deleteImage.setVisible(true);
        // Se restringe al frame a no poder cambiar de tamaño
        deleteImage.setResizable(false);
        //Se le coloca un color de fondo al frame
        deleteImage.getContentPane().setBackground(Color.ORANGE);
        //Se le indica al frame que hacer en caso de que se cierre el mismo,
        //en este caso la aplicación termina su ejecución
        deleteImage.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Con esto obtenemos las dimensiones de resolución de pantlla
        Toolkit myScreen= Toolkit.getDefaultToolkit();
        Dimension sizeScreen = myScreen.getScreenSize();
        // Guardamos la altura de la dimension de pantalla
        int h= sizeScreen.height;
        // Guardamos la anchura de la dimension de pantalla
        int w= sizeScreen.width;
        //Le agreamos un tamaño al frame admin
        deleteImage.setSize(900,300);
        //Le agreamos una locación al frame admin
        deleteImage.setLocation(w/4,h/4);
        //Le agreamos un  ícono al frame admin
        Image myIcon= myScreen.getImage("iconoLogin.png");
        deleteImage.setIconImage(myIcon);
        
        
      
        
        
        
        
        
        
        //=========================Creación de Labels =========================
        JLabel titleM = new JLabel("Eliminar Imagen");
        titleM.setLayout(null);
        titleM.setVisible(true);
        titleM.setForeground(Color.BLACK);
        titleM.setBounds(300,20,600,60);
        titleM.setFont(font3);
        deleteImage.add(titleM);
        
        //label que nos muestra el mensaje "Código"
        JLabel code = new JLabel("id a eliminar:");
        code.setLayout(null);
        code.setVisible(true);
        code.setForeground(Color.BLACK);
        code.setBounds(10,100,450,60);
        code.setFont(font3);
        deleteImage.add(code);
        
        JLabel imgggg = new JLabel("");
        ImageIcon icon= new ImageIcon("../imgUsadas/search.png");
        imgggg.setLayout(null);
        imgggg.setVisible(true);
        imgggg.setForeground(Color.BLACK);
        imgggg.setBounds(720,100,60,60);
        imgggg.setFont(font3);
        imgggg.setIcon(icon);
        deleteImage.add(imgggg);
       
        
        
        
        //Creamos el campo de texto que despliega los id de imágenes del cliente
        JComboBox idJ= new JComboBox ();
        idJ.setModel(new javax.swing.DefaultComboBoxModel<>());       
        idJ.setLayout(null);
        idJ.setVisible(true);
        idJ.setBounds(300,110,400,30);
        deleteImage.add(idJ);
        deleteImage.repaint();
        
        
        
        
          //=========================Creación de los  Botones del frame creacion de profesores=========================
        
        //Creamos el boton que buscará que eliminara al cliente seleccionado
        ImageIcon iconobtn = new ImageIcon("../imgUsadas/delete.png");
        JButton deleteButton = new JButton("");
        deleteButton.setLayout(null);
        deleteButton.setVisible(true);
        deleteButton.setBounds(480, 200, 50, 50);
        deleteButton.setBackground(Color.red);
        deleteButton.setIcon(iconobtn);
        deleteButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent buscarProfesorPorCodigo){  
                        
                        
            }
        }); 
        deleteImage.add(deleteButton);
        
        //Creamos el boton que regresará de ventana
        ImageIcon iconobtn2 = new ImageIcon("../imgUsadas/leave.png");
        JButton returnButton = new JButton("");
        returnButton.setLayout(null);
        returnButton.setVisible(true);
        returnButton.setBounds(400, 200, 50, 50);
        returnButton.setBackground(Color.magenta);
        returnButton.setIcon(iconobtn2);
        returnButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent buscarProfesorPorCodigo){  
                deleteImage.dispose();
                try {
                    clientView();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
            }
        }); 
        deleteImage.add(returnButton);
        
        
        deleteImage.repaint();
    }
    
    public static void readClientsJson(){
          File archive = null;
          FileReader fr = null;
          BufferedReader br;
        
        try{
           
            JFileChooser fc = new JFileChooser();
            int op;
              op = fc.showOpenDialog(fc);
            if (op == JFileChooser.APPROVE_OPTION) {
                
                archive = fc.getSelectedFile();
            }
            try {
                // HACEMOS LA LECTURA DEL ARCHIVO
                fr = new FileReader(archive);
                br = new BufferedReader(fr);
                String line;
                // LEER LINEA POR LINEA
                while ((line = br.readLine()) != null) {
                    // Solo agregamos el contenido a un String
                    clientsJsonContent += line;

            }
                
            } catch (Exception e) {
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public static void readImagesJson(){
          File archive = null;
          FileReader fr = null;
          BufferedReader br;
        
        try{
           
            JFileChooser fc = new JFileChooser();
            int op;
              op = fc.showOpenDialog(fc);
            if (op == JFileChooser.APPROVE_OPTION) {
                
                archive = fc.getSelectedFile();
            }
            try {
                // HACEMOS LA LECTURA DEL ARCHIVO
                fr = new FileReader(archive);
                br = new BufferedReader(fr);
                String line;
                // LEER LINEA POR LINEA
                while ((line = br.readLine()) != null) {
                    // Solo agregamos el contenido a un String
                    imagesJsonContent += line;

            }
                
            } catch (Exception e) {
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public static void readLayersJson(){
          File archive = null;
          FileReader fr = null;
          BufferedReader br;
        
        try{
           
            JFileChooser fc = new JFileChooser();
            int op;
              op = fc.showOpenDialog(fc);
            if (op == JFileChooser.APPROVE_OPTION) {
                
                archive = fc.getSelectedFile();
            }
            try {
                // HACEMOS LA LECTURA DEL ARCHIVO
                fr = new FileReader(archive);
                br = new BufferedReader(fr);
                String line;
                // LEER LINEA POR LINEA
                while ((line = br.readLine()) != null) {
                    // Solo agregamos el contenido a un String
                    layersJsonContent += line;

            }
                
            } catch (Exception e) {
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public static void readAlbumsJson(){
          File archive = null;
          FileReader fr = null;
          BufferedReader br;
        
        try{
           
            JFileChooser fc = new JFileChooser();
            int op;
              op = fc.showOpenDialog(fc);
            if (op == JFileChooser.APPROVE_OPTION) {
                
                archive = fc.getSelectedFile();
            }
            try {
                // HACEMOS LA LECTURA DEL ARCHIVO
                fr = new FileReader(archive);
                br = new BufferedReader(fr);
                String line;
                // LEER LINEA POR LINEA
                while ((line = br.readLine()) != null) {
                    // Solo agregamos el contenido a un String
                    albumsJsonContent += line;

            }
                
            } catch (Exception e) {
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public static void clientsBulkLoad(){
        try {
            //Empezamos el parseo
            JsonParser parser = new JsonParser();
            // JsonArray = arreglo de objetos Json, en este caso de tipo cliente.
            JsonArray clientsList = parser.parse(clientsJsonContent).getAsJsonArray();
            //System.out.println(clientsList);
            //Ya con el arreglo con objetos, para meterlos al árbol B
            for (int i = 0; i < clientsList.size(); i++) {
                // JsonObject = Toma el Objeto del Json actual
                JsonObject object = clientsList.get(i).getAsJsonObject();                
                //Guardamos atributos del objeto en variables
                BigInteger dpi = object.get("dpi").getAsBigInteger();

                String name = object.get("nombe_cliente").getAsString();
                String password = object.get("password").getAsString();
                //Se crea un árbol AVL para cada cliente
                SelfBalancingTree tempSelfBalancingTree = new SelfBalancingTree();
                BinarySearchTree tempBinaryTree = new BinarySearchTree();
                // Se crea el objeto cliente

                Client newClient = new Client(dpi, name,password,tempSelfBalancingTree,0,0,0,tempBinaryTree);
                clientListHandler.finalInsert(newClient);
                clientsJsonContent ="";
            
            }
            JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Carga Masiva De Clientes Realizada Con Éxito!!</p></html>" );
        } catch (Exception e) {
        }
        
    }
    
    
    public static void imagesBulkLoad(){
        try {
            //Empezamos el parseo
            JsonParser parser = new JsonParser();
            // JsonArray = arreglo de objetos Json, en este caso de tipo cliente.
            JsonArray imagesList = parser.parse(imagesJsonContent).getAsJsonArray();
            //System.out.println(clientsList);
            //Ya con el arreglo con objetos, para meterlos al árbol B
            for (int i = 0; i < imagesList.size(); i++) {
                // JsonObject = Toma el Objeto del Json actual
                JsonObject object = imagesList.get(i).getAsJsonObject();                
                //Guardamos atributos del objeto en variables
                int id = object.get("id").getAsInt();
                BinarySearchTree tempTree =new BinarySearchTree();
                JsonArray  layers = object.get("capas").getAsJsonArray();
                Img tempImg=new Img(id,tempTree,userLogged);
                for(int j = 0; j < layers.size(); j++){
                    System.out.println(layers.get(j));
                    Layer temp;
                    Client tempClient;
                    tempClient=clientListHandler.returnMeTheClient(userLogged);
                    if(tempClient != null){
                        temp=tempClient.getAbbTree().searchNodeAndReturnLayer(layers.get(j).getAsInt());
                        if(temp!=null){
                            tempImg.getTree().insert(temp);
                        }
                    }
                }
                clientListHandler.addImage(userLogged, tempImg); 
                imagesJsonContent ="";
            
            }
            JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Carga Masiva De Imágenes Realizada Con Éxito!!</p></html>" );
        } catch (Exception e) {
        }
    }
    
    public static void layersBulkLoad(){
        try {
            //Empezamos el parseo
            JsonParser parser = new JsonParser();
            // JsonArray = arreglo de objetos Json, en este caso de tipo cliente.
            JsonArray layersList = parser.parse(layersJsonContent).getAsJsonArray();
            //System.out.println(clientsList);
            //Ya con el arreglo con objetos, para meterlos al árbol B
            for (int i = 0; i < layersList.size(); i++) {
                // JsonObject = Toma el Objeto del Json actual
                JsonObject object = layersList.get(i).getAsJsonObject();                
                //Guardamos atributos del objeto en variables
                int id = object.get("id_capa").getAsInt();
                System.out.println("La capa No.: "+id+" Tiene las posiciones:");
                Position tempPosition = new Position();
                JsonArray  pixelsList = object.get("pixeles").getAsJsonArray();
                for (int j = 0; j < pixelsList.size(); j++) {
                    JsonObject object2 = pixelsList.get(j).getAsJsonObject();
                    int row = object2.get("fila").getAsInt();
                    int column = object2.get("columna").getAsInt();
                    String color = object2.get("color").getAsString();
                    tempPosition.finalInsert(id, row, column, color); 
                    System.out.println("fila: "+row);
                    System.out.println("columna: "+column);
                    System.out.println("color: "+color);
                    System.out.println("=============================");
                }
                Layer tempLayer= new Layer(id, tempPosition,userLogged);
                clientListHandler.clientNewLayer(userLogged, tempLayer);
            }
            layersJsonContent ="";
            JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Carga Masiva De Capas Realizada Con Éxito!!</p></html>" );
        } catch (Exception e) {
        }
    }
}
