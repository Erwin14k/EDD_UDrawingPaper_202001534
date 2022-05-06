
package udrawwingpaper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Erwin14k
 */
public class GUI_Module {
    //Objeto de tipo admin, este objeto es para validar el inicio de sesión como admin
    static BigInteger adminCode=new BigInteger("0");
    static Admin admin=new Admin("admin","EDD2022",adminCode);
    //Dpi del usuario Loggeado
    static String userLogged;
    //Lista enlazada donde se guardan todos los clientes registrados
    static ClientList clientListHandler = new ClientList();
    //Variables que almacenan el texto de los json antes de ser parseados
    public static String deliveryCourierJsonContent="";
    public static String clientsJsonContent="";
    public static String citiesJsonContent="";
    public static String routesJsonContent="";
    //Tabla hash donde se guardan los mensajeros
    public static HashTable hastTable = new HashTable();
    //Lista donde se guardan los municipios o ciudades
    public static CitiesList citiesListHandler=new CitiesList();
    
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
        JLabel usernameL = new JLabel("USER_NAME: ");
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
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Inicio De Sesión Exitoso Admin!!</p></html>" );
                    loginFrame.dispose();
                    userLogged=admin.getUser();
                    try {
                        adminView();
                    } catch (IOException ex) {
                        Logger.getLogger(GUI_Module.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    if(!usernameTF.getText().equals("") && !passwordTF.getText().equals("")){
                        if(clientListHandler.loginValidation(usernameTF.getText(),passwordTF.getText())==true){
                            
                            String tempName=clientListHandler.nameByUserName(usernameTF.getText());
                            JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Le Damos La Bienvenida A: </p><center><p style=\"color:blue; font:20px;\">"+tempName+"</p></center></html>" );
                            userLogged=usernameTF.getText();
                            loginFrame.dispose();
                            //clientView();
                
                        }else{
                            JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">Nombre de Usuario / Contraseña Incorrectos, Intente De Nuevo!!</p></html>" );
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">Debes llenar todos los campos, intenta de nuevo!!</p></html>" );
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
                    Logger.getLogger(GUI_Module.class.getName()).log(Level.SEVERE, null, ex);
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
        clientsRegister.setSize(900,1000);
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
        JLabel userLabel = new JLabel("Usuario: ");
        userLabel.setLayout(null);
        userLabel.setVisible(true);
        userLabel.setForeground(Color.BLACK);
        userLabel.setBounds(50,300,450,60);
        userLabel.setFont(font3);
        clientsRegister.add(userLabel);
        
        
        //label que nos muestra el mensaje "Contraseña"
        JLabel pass = new JLabel("Contraseña: ");
        pass.setLayout(null);
        pass.setVisible(true);
        pass.setForeground(Color.BLACK);
        pass.setBounds(50,400,450,60);
        pass.setFont(font3);
        clientsRegister.add(pass);
        
        
        //label que nos muestra el mensaje "Id Ciudad"
        JLabel city = new JLabel("Id Ciudad: ");
        city.setLayout(null);
        city.setVisible(true);
        city.setForeground(Color.BLACK);
        city.setBounds(50,500,450,60);
        city.setFont(font3);
        clientsRegister.add(city);
        
        
        //label que nos muestra el mensaje "Teléfono"
        JLabel phone = new JLabel("Teléfono: ");
        phone.setLayout(null);
        phone.setVisible(true);
        phone.setForeground(Color.BLACK);
        phone.setBounds(50,600,450,60);
        phone.setFont(font3);
        clientsRegister.add(phone);
        
        //label que nos muestra el mensaje "Correo"
        JLabel mail = new JLabel("Correo: ");
        mail.setLayout(null);
        mail.setVisible(true);
        mail.setForeground(Color.BLACK);
        mail.setBounds(50,700,450,60);
        mail.setFont(font3);
        clientsRegister.add(mail);
        
        //label que nos muestra el mensaje "Dirección"
        JLabel direction = new JLabel("Dirección: ");
        direction.setLayout(null);
        direction.setVisible(true);
        direction.setForeground(Color.BLACK);
        direction.setBounds(50,800,450,60);
        direction.setFont(font3);
        clientsRegister.add(direction);
        
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
       
        //Creamos el campo de texto que recibirá el nombre de usuario
        JTextField userT = new JTextField();
        userT.setLayout(null);
        userT.setVisible(true);
        userT.setBounds(300,310,400,40);
        userT.setBackground(Color.red);
        userT.setFont(font2);
        clientsRegister.add(userT);
        
        
        //Creamos el campo de texto que recibirá la Contraseña
        JTextField passT = new JTextField();
        passT.setLayout(null);
        passT.setVisible(true);
        passT.setBounds(300,410,400,40);
        passT.setBackground(Color.red);
        passT.setFont(font2);
        clientsRegister.add(passT);
        
        
        //Creamos el campo de texto que recibirá el id de la ciudad.
        JTextField cityT = new JTextField();
        cityT.setLayout(null);
        cityT.setVisible(true);
        cityT.setBounds(300,510,400,40);
        cityT.setBackground(Color.red);
        cityT.setFont(font2);
        clientsRegister.add(cityT);
        
        //Creamos el campo de texto que recibirá el número de teléfono.
        JTextField phoneT = new JTextField();
        phoneT.setLayout(null);
        phoneT.setVisible(true);
        phoneT.setBounds(300,610,400,40);
        phoneT.setBackground(Color.red);
        phoneT.setFont(font2);
        clientsRegister.add(phoneT);
        
        //Creamos el campo de texto que recibirá el correo electrónico.
        JTextField mailT = new JTextField();
        mailT.setLayout(null);
        mailT.setVisible(true);
        mailT.setBounds(300,710,400,40);
        mailT.setBackground(Color.red);
        mailT.setFont(font2);
        clientsRegister.add(mailT);
        
        //Creamos el campo de texto que recibirá la dirección.
        JTextField directionT = new JTextField();
        directionT.setLayout(null);
        directionT.setVisible(true);
        directionT.setBounds(300,810,400,40);
        directionT.setBackground(Color.red);
        directionT.setFont(font2);
        clientsRegister.add(directionT);
        
        
        
        
        
         //=========================Creación de los  Botones del frame creacion de profesores=========================
        //Creamos un botón de registro de clientes
        JButton registerButton = new JButton("");
        ImageIcon iconobtn = new ImageIcon("imgUsadas/new.png");
        registerButton.setLayout(null);
        registerButton.setVisible(true);
        registerButton.setBounds(450, 900, 320, 60);
        registerButton.setBackground(Color.yellow);
        registerButton.setIcon(iconobtn);
        registerButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e4){
                if(codeT.getText().matches("[0-9]+") && cityT.getText().matches("[0-9]+")){
                    if((!nameT.getText().equals(""))&&(!passT.getText().equals("")) &&(!userT.getText().equals(""))
                      &&(!phoneT.getText().equals(""))&&(!mailT.getText().equals("")&&(!directionT.getText().equals("")))){
                        BigInteger temp=new BigInteger(codeT.getText());
                        if(clientListHandler.exist(temp)){ 
                            Client newClient= new Client(temp,nameT.getText(),userT.getText(),mailT.getText(),passT.getText(),directionT.getText(),Integer.parseInt(cityT.getText()));
                            clientListHandler.finalInsert(newClient);
                            JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Cliente Registrad@ Con Éxito!!</p></html>" );
                            clientsRegister.dispose();
                            try {
                                loginFrameModule();
                            } catch (IOException ex) {
                                Logger.getLogger(GUI_Module.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">El dpi ya existe, intenta con otro!!</p></html>" );
                            
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">Debes llenar todos los campos, intenta de nuevo!!</p></html>" );
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"<html><p style=\"color:red; font:20px;\">El dpi y el id de la ciudad tienen que ser un número entero, intenta de nuevo!!</p></html>" );
                }
                
                      
                          
            }      
        }); 
        
        clientsRegister.add(registerButton);
        
        //Creamos un botón de registro de clientes
        JButton exitButton = new JButton("");
        ImageIcon iconobtn2 = new ImageIcon("imgUsadas/leave.png");
        exitButton.setLayout(null);
        exitButton.setVisible(true);
        exitButton.setBounds(80, 900, 320, 60);
        exitButton.setBackground(Color.magenta);
        exitButton.setIcon(iconobtn2);
        exitButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e4){  
                
                try {
                    clientsRegister.dispose();
                    loginFrameModule();
                } catch (IOException ex) {
                    Logger.getLogger(GUI_Module.class.getName()).log(Level.SEVERE, null, ex);
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
 
       
        
   
        
        
        
        //Botón para actualizar un cliente
        JButton updateClient = new JButton("");
        ImageIcon iconobtn3 = new ImageIcon("imgUsadas/update2.png");
        updateClient.setLayout(null);
        updateClient.setVisible(true);
        updateClient.setBounds(700, 630, 50, 60);
        updateClient.setBackground(Color.magenta);
        updateClient.setIcon(iconobtn3);
        updateClient.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                adminView.dispose();
                //updateClient();
            }
        }); 
        adminView.add(updateClient);
        
        //Botón para cerrar sesión como admin
        JButton logOutButton = new JButton("");
        ImageIcon iconobtn4 = new ImageIcon("imgUsadas/logout.png");
        logOutButton.setLayout(null);
        logOutButton.setVisible(true);
        logOutButton.setBounds(770, 630, 50, 60);
        logOutButton.setBackground(Color.red);
        logOutButton.setIcon(iconobtn4);
        logOutButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                adminView.dispose();
                try {
                    loginFrameModule();
                } catch (IOException ex) {
                    Logger.getLogger(GUI_Module.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        adminView.add(logOutButton);
        
         //Label para mostrar grafo
        JLabel graphLabel = new JLabel("");
        graphLabel.setLayout(null);
        graphLabel.setVisible(true);
        graphLabel.setForeground(Color.BLACK);
        graphLabel.setBounds(850,20,800,600);
        ImageIcon iconobtn5 = new ImageIcon("imgUsadas/iconologin.png");
        graphLabel.setIcon(iconobtn5);
        JScrollPane graphScroll= new JScrollPane();
        graphScroll.setBounds(850,20,700,600);
        graphScroll.setViewportView(graphLabel);
        adminView.add(graphScroll);
        
        
        //Botón para ver la tabla hash
        JButton hashButton = new JButton("HASH");
        hashButton.setLayout(null);
        hashButton.setVisible(true);
        hashButton.setBounds(875, 630, 150, 60);
        hashButton.setBackground(Color.white);
        hashButton.setFont(font3);
        hashButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){  
                String route="Reportes Img/Tabla Hash.png";
                ImageIcon imgIcon = new ImageIcon(route);
                imgIcon.getImage().flush();
                graphLabel.setIcon(imgIcon); 
            }
        }); 
        adminView.add(hashButton);
        
        
        JButton adjacencyButton = new JButton("Adyacencia");
        adjacencyButton.setLayout(null);
        adjacencyButton.setVisible(true);
        adjacencyButton.setBounds(1045, 630, 300, 60);
        adjacencyButton.setBackground(Color.white);
        adjacencyButton.setFont(font3);
        adjacencyButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){  
                String route="Reportes Img/listaAdyacente.png";
                ImageIcon imgIcon = new ImageIcon(route);
                imgIcon.getImage().flush();
                graphLabel.setIcon(imgIcon); 
            }
        }); 
        adminView.add(adjacencyButton);
        
        JButton undirectedButton = new JButton("Grafo");
        undirectedButton.setLayout(null);
        undirectedButton.setVisible(true);
        undirectedButton.setBounds(1375, 630, 200, 60);
        undirectedButton.setBackground(Color.white);
        undirectedButton.setFont(font3);
        undirectedButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){  
                String route="Reportes Img/Grafo.png";
                ImageIcon imgIcon = new ImageIcon(route);
                imgIcon.getImage().flush();
                graphLabel.setIcon(imgIcon); 
            }
        }); 
        adminView.add(undirectedButton);
        
       
        //=========================Creación de Tabla =========================
        
        int n=0;
        String[] header = {"DPI","Nombre","Usuario","Id Ciudad"}; 
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
        clientTable.setRowHeight(40);
        clientTable.setFont(font4);
        
        
        //clientable.setBackground(Color.WHITE);
        clientsDataTableSC.setBounds(20, 100, 800, 500);
        clientsDataTableSC.setViewportView(clientTable);
        
        adminView.add(clientsDataTableSC);
      
        
        //Creamos un botón de Carga Masiva de mensajeros
        JButton deliveryCouriersbulkLoadButton = new JButton("Mensajeros");
        deliveryCouriersbulkLoadButton.setLayout(null);
        deliveryCouriersbulkLoadButton.setVisible(true);
        deliveryCouriersbulkLoadButton.setBounds(20, 630, 300, 60);
        deliveryCouriersbulkLoadButton.setBackground(Color.yellow);
        deliveryCouriersbulkLoadButton.setFont(font3);
        deliveryCouriersbulkLoadButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                hastTable.initialize();
                adminView.dispose();
                readDeliveryCouriersJson();
                deliveryCouriersBulkLoad();
                //System.out.println("Los mensajeros ingresados por carga Masiva:");
                //clientListHandler.travel();
                //System.out.println("=========================================");
                //System.out.println("\n\n\n");
                try {
                    adminView();
                    
                } catch (IOException ex) {
                    Logger.getLogger(GUI_Module.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
        }); 
        adminView.add(deliveryCouriersbulkLoadButton);
        
      
       //Creamos un botón de Carga Masiva de clientes
        JButton clientsBulkLoadButton = new JButton("Clientes");
        clientsBulkLoadButton.setLayout(null);
        clientsBulkLoadButton.setVisible(true);
        clientsBulkLoadButton.setBounds(20, 700, 300, 60);
        clientsBulkLoadButton.setBackground(Color.yellow);
        clientsBulkLoadButton.setFont(font3);
        clientsBulkLoadButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
 
                adminView.dispose();
                readClientsJson();
                clientsBulkLoad();
                //System.out.println("Los clientes ingresados por carga Masiva:");
                //clientListHandler.travel();
                //System.out.println("=========================================");
                //System.out.println("\n\n\n");
                try {
                    adminView();
                    
                } catch (IOException ex) {
                    Logger.getLogger(GUI_Module.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
        }); 
        adminView.add(clientsBulkLoadButton);
        
        
        JButton citiesBulkLoadButton = new JButton("Lugares");
        citiesBulkLoadButton.setLayout(null);
        citiesBulkLoadButton.setVisible(true);
        citiesBulkLoadButton.setBounds(350, 630, 300, 60);
        citiesBulkLoadButton.setBackground(Color.yellow);
        citiesBulkLoadButton.setFont(font3);
        citiesBulkLoadButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                hastTable.initialize();
                adminView.dispose();
                readCitiesJson();
                citiesBulkLoad();
                System.out.println("Los lugares ingresados por carga Masiva:");
                //clientListHandler.travel();
                System.out.println("=========================================");
                System.out.println("\n\n\n");
                try {
                    adminView();
                    
                } catch (IOException ex) {
                    Logger.getLogger(GUI_Module.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
        }); 
        adminView.add(citiesBulkLoadButton);
        
        
        JButton routesBulkLoadButton = new JButton("Rutas");
        routesBulkLoadButton.setLayout(null);
        routesBulkLoadButton.setVisible(true);
        routesBulkLoadButton.setBounds(350, 700, 300, 60);
        routesBulkLoadButton.setBackground(Color.yellow);
        routesBulkLoadButton.setFont(font3);
        routesBulkLoadButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                
                adminView.dispose();
                readRoutesJson();
                routesBulkLoad();
                System.out.println("Las rutas ingresadas por carga Masiva:");
                //clientListHandler.travel();
                System.out.println("=========================================");
                System.out.println("\n\n\n");
                try {
                    citiesListHandler.generateAdjancecyList();
                    citiesListHandler.generateUndirectedGraph();
                    adminView();
                    
                } catch (IOException ex) {
                    Logger.getLogger(GUI_Module.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
        }); 
        adminView.add(routesBulkLoadButton);
            
            
            
            
        }
    
    public static void readDeliveryCouriersJson(){
          File archive = null;
          FileReader fr = null;
          BufferedReader br;
        //System.out.println("ooooooo");
        try{
            //System.out.println("Aqui si entra 1");
           
            JFileChooser fc = new JFileChooser();
            int op;
              op = fc.showOpenDialog(fc);
            if (op == JFileChooser.APPROVE_OPTION) {
                //System.out.println("Aqui si entra 2");
                archive = fc.getSelectedFile();
            }
            try {
                // HACEMOS LA LECTURA DEL ARCHIVO
                //System.out.println("Aqui si entra 3");
                fr = new FileReader(archive);
                br = new BufferedReader(fr);
                String line;
                // LEER LINEA POR LINEA
                while ((line = br.readLine()) != null) {
                    // Solo agregamos el contenido a un String
                    deliveryCourierJsonContent += line;
                    //System.out.println("siu");

            }
                
            } catch (Exception e) {
                System.out.println("No se pudo 1."+e);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo 2."+e);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("No se pudo 3."+e2);
            }
        }
    }
    public static void deliveryCouriersBulkLoad(){
        try {
            //Empezamos el parseo
            JsonParser parser = new JsonParser();
            // JsonArray = arreglo de objetos Json, en este caso de tipo cliente.
            JsonArray deliveryCouriersList = parser.parse(deliveryCourierJsonContent).getAsJsonArray();
            System.out.println("=============================================");
            System.out.println("el numero de mensajeros es: "+deliveryCouriersList.size());
            System.out.println("Si existen repetidos se descartan!!");
            System.out.println("=============================================");
            for (int i = 0; i < deliveryCouriersList.size(); i++) {
                //System.out.println("EMPIEZAAAAAAAAA");
                // JsonObject = Toma el Objeto del Json actual
                JsonObject object = deliveryCouriersList.get(i).getAsJsonObject();     
                //System.out.println(object);
                //Guardamos atributos del objeto en variables
                BigInteger dpi = object.get("dpi").getAsBigInteger();
                //System.out.println(dpi);
                String name = object.get("nombres").getAsString();
                //System.out.println(name);
                String lastName = object.get("apellidos").getAsString();
                String license = object.get("tipo_licencia").getAsString();
                String gender = object.get("genero").getAsString();
                String direction = object.get("direccion").getAsString();
                String phone = object.get("telefono").getAsString();
                
                
                // Se crea el objeto mensajero

                DeliveryCourier newDeliveryCourier = new DeliveryCourier(dpi,name,lastName,license,gender,phone,direction);
                hastTable.insert(newDeliveryCourier); 
                
                
                
                
            
            }
            deliveryCourierJsonContent ="";
            hastTable.generateGraph();
            //hastTable.printData();
            JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Carga Masiva De Mensajeros Realizada Con Éxito!!</p></html>" );
        } catch (Exception e) {
        }
        
    }
    
    public static void readClientsJson(){
          File archive = null;
          FileReader fr = null;
          BufferedReader br;
        //System.out.println("ooooooo");
        try{
            //System.out.println("Aqui si entra 1");
           
            JFileChooser fc = new JFileChooser();
            int op;
              op = fc.showOpenDialog(fc);
            if (op == JFileChooser.APPROVE_OPTION) {
                //System.out.println("Aqui si entra 2");
                archive = fc.getSelectedFile();
            }
            try {
                // HACEMOS LA LECTURA DEL ARCHIVO
                //System.out.println("Aqui si entra 3");
                fr = new FileReader(archive);
                br = new BufferedReader(fr);
                String line;
                // LEER LINEA POR LINEA
                while ((line = br.readLine()) != null) {
                    // Solo agregamos el contenido a un String
                    clientsJsonContent += line;
                    //System.out.println("siu");

            }
                
            } catch (Exception e) {
                System.out.println("No se pudo 1."+e);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo 2."+e);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("No se pudo 3."+e2);
            }
        }
    }
    
    public static void clientsBulkLoad(){
        try {
            //Empezamos el parseo
            JsonParser parser = new JsonParser();
            // JsonArray = arreglo de objetos Json, en este caso de tipo cliente.
            JsonArray clientsList = parser.parse(clientsJsonContent).getAsJsonArray();
            System.out.println("=============================================");
            System.out.println("el numero de clientes es: "+clientsList.size());
            System.out.println("Si existen repetidos se descartan!!");
            System.out.println("=============================================");
            //System.out.println(clientsList);
            //Ya con el arreglo con objetos, para meterlos a una lista enlazada
            for (int i = 0; i < clientsList.size(); i++) {
                //System.out.println("EMPIEZAAAAAAAAA");
                // JsonObject = Toma el Objeto del Json actual
                JsonObject object = clientsList.get(i).getAsJsonObject();     
                //System.out.println(object);
                //Guardamos atributos del objeto en variables
                BigInteger dpi = object.get("dpi").getAsBigInteger();
                //System.out.println(dpi);
                String name = object.get("nombre_completo").getAsString();
                //System.out.println(name);
                String userName = object.get("nombre_usuario").getAsString();
                String mail = object.get("correo").getAsString();
                String password = object.get("contrasenia").getAsString();
                String phone = object.get("telefono").getAsString();
                String direction = object.get("direccion").getAsString();
                int cityId = object.get("id_municipio").getAsInt();
                System.out.println(name);
                
                // Se crea el objeto cliente

                Client newClient = new Client(dpi,name,userName,mail,password,direction,cityId);
                clientListHandler.finalInsert(newClient); 

            }
            clientsJsonContent ="";
            //clientListHandler.generateGraph();
            JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Carga Masiva De Clientes Realizada Con Éxito!!</p></html>" );
        } catch (Exception e) {
        }
        
    }
    
    public static void readCitiesJson(){
          File archive = null;
          FileReader fr = null;
          BufferedReader br;
        //System.out.println("ooooooo");
        try{
            //System.out.println("Aqui si entra 1");
           
            JFileChooser fc = new JFileChooser();
            int op;
              op = fc.showOpenDialog(fc);
            if (op == JFileChooser.APPROVE_OPTION) {
                //System.out.println("Aqui si entra 2");
                archive = fc.getSelectedFile();
            }
            try {
                // HACEMOS LA LECTURA DEL ARCHIVO
                //System.out.println("Aqui si entra 3");
                fr = new FileReader(archive);
                br = new BufferedReader(fr);
                String line;
                // LEER LINEA POR LINEA
                while ((line = br.readLine()) != null) {
                    // Solo agregamos el contenido a un String
                    citiesJsonContent += line;
                    //System.out.println("siu");

            }
                
            } catch (Exception e) {
                System.out.println("No se pudo 1."+e);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo 2."+e);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("No se pudo 3."+e2);
            }
        }
        
        citiesJsonContent=personalizeCitiesJson(citiesJsonContent);
    }
    public static void citiesBulkLoad(){
        try {
            //Empezamos el parseo
            JsonParser parser = new JsonParser();
            System.out.println(citiesJsonContent);
            // JsonArray = arreglo de objetos Json, en este caso de tipo ciudad.
            JsonArray citiesList = parser.parse(citiesJsonContent).getAsJsonArray();
            System.out.println("=============================================");
            System.out.println("el numero de ciudades es: "+citiesList.size());
            System.out.println("Si existen repetidos se descartan!!");
            System.out.println("=============================================");
            //System.out.println(clientsList);
            //Ya con el arreglo con objetos, para meterlos a una lista enlazada
            for (int i = 0; i < citiesList.size(); i++) {
                //System.out.println("EMPIEZAAAAAAAAA");
                // JsonObject = Toma el Objeto del Json actual
                JsonObject object = citiesList.get(i).getAsJsonObject();     

                //Guardamos atributos del objeto en variables
                int id = object.get("id").getAsInt();

                String department = object.get("departamento").getAsString();
                String name = object.get("nombre").getAsString();
                String office = object.get("sn_sucursal").getAsString();
                
                // Se crea el objeto cliente
                RoutesList routesList=new  RoutesList();   
                City newCity = new City(id,department,name,office,routesList);
                citiesListHandler.finalInsert(newCity); 

            }
            citiesJsonContent ="";
            JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Carga Masiva De Lugares Realizada Con Éxito!!</p></html>" );
        } catch (Exception e) {
        }
        
    }
    
    public static void readRoutesJson(){
          File archive = null;
          FileReader fr = null;
          BufferedReader br;
        //System.out.println("ooooooo");
        try{
            //System.out.println("Aqui si entra 1");
           
            JFileChooser fc = new JFileChooser();
            int op;
              op = fc.showOpenDialog(fc);
            if (op == JFileChooser.APPROVE_OPTION) {
                //System.out.println("Aqui si entra 2");
                archive = fc.getSelectedFile();
            }
            try {
                // HACEMOS LA LECTURA DEL ARCHIVO
                //System.out.println("Aqui si entra 3");
                fr = new FileReader(archive);
                br = new BufferedReader(fr);
                String line;
                // LEER LINEA POR LINEA
                while ((line = br.readLine()) != null) {
                    // Solo agregamos el contenido a un String
                    routesJsonContent += line;
                    //System.out.println("siu");

            }
                
            } catch (Exception e) {
                System.out.println("No se pudo 1."+e);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo 2."+e);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("No se pudo 3."+e2);
            }
        }
        routesJsonContent=personalizeRoutesJson(routesJsonContent);
    }
    
    public static void routesBulkLoad(){
        try {
            //Empezamos el parseo
            JsonParser parser = new JsonParser();
            // JsonArray = arreglo de objetos Json, en este caso de tipo ciudad.
            JsonArray routesList = parser.parse(routesJsonContent).getAsJsonArray();
            System.out.println("=============================================");
            System.out.println("el numero de rutas es: "+routesList.size());
            System.out.println("Si existen repetidos se descartan!!");
            System.out.println("=============================================");
            //System.out.println(clientsList);
            //Ya con el arreglo con objetos, para meterlos a una lista enlazada
            for (int i = 0; i < routesList.size(); i++) {
                //System.out.println("EMPIEZAAAAAAAAA");
                // JsonObject = Toma el Objeto del Json actual
                JsonObject object = routesList.get(i).getAsJsonObject();     

                //Guardamos atributos del objeto en variables
                int start = object.get("inicio").getAsInt();
                int end = object.get("final").getAsInt();
                int weight = object.get("peso").getAsInt();
                
                
                // Se crea el objeto ruta
                  
                Route newRoute = new Route(start,end,weight);
                citiesListHandler.cityNewRoute(start,newRoute);
                //citiesListHandler.cityNewRoute(end,newRoute); 

            }
            routesJsonContent ="";
            JOptionPane.showMessageDialog(null,"<html><p style=\"color:green; font:20px;\">Carga Masiva De Rutas Realizada Con Éxito!!</p></html>" );
        } catch (Exception e) {
        }
        
    }
    
    public static String personalizeCitiesJson(String content){
        /*Variable importantísima llamada rgxIDontWant, la cuál almacena una expresión 
        regular para eliminar del archivo json todas las coincidencias.*/
        String rgxIDontWant="\"Lugares\":";
        //Variable para almacenar content con las coincidencias borradas
        String contentWithoutRgx = content.replaceAll(rgxIDontWant, "");
        //Variable la cuál guarda un string reemplaza el primer caracter "{" por "["
        String firstSymbolDelete= contentWithoutRgx.replaceFirst("\\{", "");
        //Variable la cuál guarda un string reemplaza el ultimo caracter "}" por "]" 
        String lastSymbolDelete = firstSymbolDelete.replaceFirst(".$", "");
        //Ya con un string adecuado lo devolvemos para que lo pueda utilizar otro método.
        return lastSymbolDelete;
    }
    
    public static String personalizeRoutesJson(String content){
        /*Variable importantísima llamada rgxIDontWant, la cuál almacena una expresión 
        regular para eliminar del archivo json todas las coincidencias.*/
        String rgxIDontWant="\"Grafo\":";
        //Variable para almacenar content con las coincidencias borradas
        String contentWithoutRgx = content.replaceAll(rgxIDontWant, "");
        //Variable la cuál guarda un string reemplaza el primer caracter "{" por "["
        String firstSymbolDelete= contentWithoutRgx.replaceFirst("\\{", "");
        //Variable la cuál guarda un string reemplaza el ultimo caracter "}" por "]" 
        String lastSymbolDelete = firstSymbolDelete.replaceFirst(".$", "");
        //Ya con un string adecuado lo devolvemos para que lo pueda utilizar otro método.
        return lastSymbolDelete;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    //El frame que visualiza el cliente al estar loggeado.
    public static void clientView() throws IOException {
            
        //Creamos unos tipos de letra, que nos servirán más adelante
        Font font =new Font("Arial",Font.BOLD,36);
        Font font2 =new Font("Helvetica",Font.BOLD,30);
        Font font3 =new Font("Showcard Gothic",Font.BOLD,30);
        Font font4 =new Font("Showcard Gothic",Font.BOLD,18);
        
        
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
        layerLabel.setBounds(90,80,800,60);
        layerLabel.setFont(font3);
        clientView.add(layerLabel);
        
        JLabel imgLabel = new JLabel("Imágen En Específico: ");
        imgLabel.setLayout(null);
        imgLabel.setVisible(true);
        imgLabel.setForeground(Color.BLACK);
        imgLabel.setBounds(90,220,800,60);
        imgLabel.setFont(font3);
        clientView.add(imgLabel);
        
        JLabel imgTreeLabel = new JLabel("Árbol En Específico: ");
        imgTreeLabel.setLayout(null);
        imgTreeLabel.setVisible(true);
        imgTreeLabel.setForeground(Color.BLACK);
        imgTreeLabel.setBounds(90,360,800,60);
        imgTreeLabel.setFont(font3);
        clientView.add(imgTreeLabel);
        
        //Creamos el combobox que despliega los id de las capas
        JComboBox idLayers= new JComboBox ();
        idLayers.setModel(new javax.swing.DefaultComboBoxModel<>(clientListHandler.returnMyLayersCodes(userLogged)));        
        idLayers.setLayout(null);
        idLayers.setVisible(true);
        idLayers.setBounds(90,160,400,30);
        clientView.add(idLayers);
        
        
        
       
        
        
        
        //Creamos el combobox que despliega los id de las imágenes
        JComboBox idImages= new JComboBox ();
        idImages.setModel(new javax.swing.DefaultComboBoxModel<>(clientListHandler.returnMyImgsCodes(userLogged)));       
        idImages.setLayout(null);
        idImages.setVisible(true);
        idImages.setBounds(90,300,400,30);
        clientView.add(idImages);
        
        JComboBox idImagesForTree= new JComboBox ();
        idImagesForTree.setModel(new javax.swing.DefaultComboBoxModel<>(clientListHandler.returnMyImgsCodes(userLogged)));       
        idImagesForTree.setLayout(null);
        idImagesForTree.setVisible(true);
        idImagesForTree.setBounds(90,440,400,30);
        clientView.add(idImagesForTree);
        
        
        
       
        //Creamos un botón de Carga Masiva de imágenes
        JButton imgLoad = new JButton("Cargar Img");
        imgLoad.setLayout(null);
        imgLoad.setVisible(true);
        imgLoad.setBounds(20, 630, 300, 60);
        imgLoad.setBackground(Color.yellow);
        imgLoad.setFont(font3);
        imgLoad.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                clientView.dispose();
                readImagesJson();
                imagesBulkLoad();
                try {
                    clientView();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        clientView.add(imgLoad);
        
        //Creamos un botón de Carga Masiva de Albumes
        JButton albumLoad = new JButton("Cargar Album");
        albumLoad.setLayout(null);
        albumLoad.setVisible(true);
        albumLoad.setBounds(20, 700, 300, 60);
        albumLoad.setBackground(Color.yellow);
        albumLoad.setFont(font3);
        albumLoad.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                clientView.dispose();
                readAlbumsJson();
                albumsBulkLoad();
                try {
                    clientView();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        clientView.add(albumLoad);
        //Creamos un botón de Carga Masiva de capas
        JButton layersLoad = new JButton("Cargar Capas");
        layersLoad.setLayout(null);
        layersLoad.setVisible(true);
        layersLoad.setBounds(20, 560, 300, 60);
        layersLoad.setBackground(Color.yellow);
        layersLoad.setFont(font3);
        layersLoad.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
                clientView.dispose();
                readLayersJson();
                layersBulkLoad();
                try {
                    clientView();
                    //clientListHandler.graphClientTree(userLogged);
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
        ImageIcon iconobtn2 = new ImageIcon("imgUsadas/addImg.png");
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
        ImageIcon iconobtn7 = new ImageIcon("imgUsadas/delete.png");
        deleteClient.setLayout(null);
        deleteClient.setVisible(true);
        deleteClient.setBounds(530, 630, 50, 60);
        deleteClient.setBackground(Color.red);
        deleteClient.setIcon(iconobtn7);
        deleteClient.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){   
                clientView.dispose();
                try {
                    deleteImage();
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        clientView.add(deleteClient);
        
        
        //Botón para cerrar sesión como cliente
        JButton logOutButton = new JButton("");
        ImageIcon iconobtn4 = new ImageIcon("imgUsadas/logout.png");
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
        
        
        
        
        
        
        
        
        
        //Label para mostrar los grafos y reportes
        JLabel graphLabel = new JLabel("");
        graphLabel.setLayout(null);
        graphLabel.setVisible(true);
        graphLabel.setForeground(Color.BLACK);
        graphLabel.setBounds(850,20,800,600);
        ImageIcon iconobtn5 = new ImageIcon("imgUsadas/iconologin.png");
        graphLabel.setIcon(iconobtn5);
        JScrollPane graphScroll= new JScrollPane();
        graphScroll.setBounds(850,20,700,600);
        graphScroll.setViewportView(graphLabel);
        clientView.add(graphScroll);
        
        
        
        
        JButton personalizePreOrderImage = new JButton("P");
        personalizePreOrderImage.setLayout(null);
        personalizePreOrderImage.setVisible(true);
        personalizePreOrderImage.setBounds(440, 530, 50, 60);
        personalizePreOrderImage.setBackground(Color.cyan);
        personalizePreOrderImage.setFont(font4);
        personalizePreOrderImage.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
         
                String route="Reportes Img/PersonalizePreOrder.png";
                ImageIcon layerIcon = new ImageIcon(route);
                layerIcon.getImage().flush();
                graphLabel.setIcon(layerIcon); 
            }
        }); 
        clientView.add(personalizePreOrderImage);
        
        JButton personalizeInOrderImage = new JButton("I");
        personalizeInOrderImage.setLayout(null);
        personalizeInOrderImage.setVisible(true);
        personalizeInOrderImage.setBounds(530, 530, 50, 60);
        personalizeInOrderImage.setBackground(Color.cyan);
        personalizeInOrderImage.setFont(font4);
        personalizeInOrderImage.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
         
                String route="Reportes Img/PersonalizeInOrder.png";
                ImageIcon layerIcon = new ImageIcon(route);
                layerIcon.getImage().flush();
                graphLabel.setIcon(layerIcon); 
            }
        }); 
        clientView.add(personalizeInOrderImage);
        
        
        JButton personalizePostOrderImage = new JButton("T");
        personalizePostOrderImage.setLayout(null);
        personalizePostOrderImage.setVisible(true);
        personalizePostOrderImage.setBounds(620, 530, 50, 60);
        personalizePostOrderImage.setBackground(Color.cyan);
        personalizePostOrderImage.setFont(font4);
        personalizePostOrderImage.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
         
                String route="Reportes Img/PersonalizePostOrder.png";
                ImageIcon layerIcon = new ImageIcon(route);
                layerIcon.getImage().flush();
                graphLabel.setIcon(layerIcon); 
            }
        }); 
        clientView.add(personalizePostOrderImage);
        
        
        
        JButton layerReportButton = new JButton("");
        ImageIcon iconobtn = new ImageIcon("imgUsadas/search.png");
        layerReportButton.setLayout(null);
        layerReportButton.setVisible(true);
        layerReportButton.setBounds(520, 140, 50, 60);
        layerReportButton.setBackground(Color.green);
        layerReportButton.setIcon(iconobtn);
        layerReportButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
         
                String route="Reportes Img/Capa"+idLayers.getSelectedItem().toString()+clientListHandler.nameByDpi(userLogged)+".png";
                ImageIcon layerIcon = new ImageIcon(route);
                layerIcon.getImage().flush();
                graphLabel.setIcon(layerIcon); 
            }
        }); 
        clientView.add(layerReportButton);
        
        JButton layerReportButton2 = new JButton("");
        ImageIcon iconobtn99 = new ImageIcon("imgUsadas/search.png");
        layerReportButton2.setLayout(null);
        layerReportButton2.setVisible(true);
        layerReportButton2.setBounds(600, 140, 50, 60);
        layerReportButton2.setBackground(Color.magenta);
        layerReportButton2.setIcon(iconobtn99);
        layerReportButton2.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
         
                String route="Reportes Img/Bonitacapa"+idLayers.getSelectedItem().toString()+clientListHandler.nameByDpi(userLogged)+".png";
                ImageIcon layerIcon = new ImageIcon(route);
                layerIcon.getImage().flush();
                graphLabel.setIcon(layerIcon); 
            }
        }); 
        clientView.add(layerReportButton2);
        
        JButton specificImage = new JButton("");
        ImageIcon iconobtn9 = new ImageIcon("imgUsadas/search.png");
        specificImage.setLayout(null);
        specificImage.setVisible(true);
        specificImage.setBounds(520, 280, 50, 60);
        specificImage.setBackground(Color.green);
        specificImage.setIcon(iconobtn9);
        specificImage.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
                String route="Reportes Img/imagen"+idImages.getSelectedItem().toString()+clientListHandler.nameByDpi(userLogged)+".png";
                ImageIcon imgIcon = new ImageIcon(route);
                imgIcon.getImage().flush();
                graphLabel.setIcon(imgIcon); 
            }
        }); 
        clientView.add(specificImage);
        
        JButton specificImage2 = new JButton("");
        ImageIcon iconobtn91 = new ImageIcon("imgUsadas/search.png");
        specificImage2.setLayout(null);
        specificImage2.setVisible(true);
        specificImage2.setBounds(600, 280, 50, 60);
        specificImage2.setBackground(Color.magenta);
        specificImage2.setIcon(iconobtn91);
        specificImage2.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
                String route="Reportes Img/BonitaImg"+idImages.getSelectedItem().toString()+clientListHandler.nameByDpi(userLogged)+".png";
                ImageIcon imgIcon = new ImageIcon(route);
                imgIcon.getImage().flush();
                graphLabel.setIcon(imgIcon); 
            }
        }); 
        clientView.add(specificImage2);
        
        
        JButton imageTree = new JButton("");
        imageTree.setLayout(null);
        imageTree.setVisible(true);
        imageTree.setBounds(520, 420, 50, 60);
        imageTree.setBackground(Color.green);
        imageTree.setIcon(iconobtn9);
        imageTree.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){ 
                String route="Reportes Img/ArbolImagen"+idImagesForTree.getSelectedItem().toString()+clientListHandler.nameByDpi(userLogged)+".png";
                ImageIcon imgIcon = new ImageIcon(route);
                imgIcon.getImage().flush();
                graphLabel.setIcon(imgIcon); 
            }
        }); 
        clientView.add(imageTree);
        
        
        //Botón para mostrar el grafo ABB de capas del cliente.
        JButton abbImgButton = new JButton("Capas");
        abbImgButton.setLayout(null);
        abbImgButton.setVisible(true);
        abbImgButton.setBounds(1115, 630, 150, 60);
        abbImgButton.setBackground(Color.white);
        abbImgButton.setFont(font3);
        abbImgButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                String abbRoute="";
                try { 
                    
                    abbRoute=clientListHandler.personalizeAbbRoute(userLogged);
                    System.out.println(abbRoute);
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                ImageIcon abbIcon = new ImageIcon(abbRoute);
                graphLabel.setIcon(abbIcon); 
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
                String albumsRoute="";
                try { 
                    
                    albumsRoute=clientListHandler.personalizeAlbumsListRoute(userLogged);
                    System.out.println(albumsRoute);
                    
                    
                 
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                ImageIcon albumsListIcon = new ImageIcon(albumsRoute);
                graphLabel.setIcon(albumsListIcon); 
                
            }
        }); 
        clientView.add(albumImgButton);
        
        //Botón para mostrar el grafo Avl de imágenes del cliente.
        JButton avlImgButton = new JButton("Img");
        avlImgButton.setLayout(null);
        avlImgButton.setVisible(true);
        avlImgButton.setBounds(940, 630, 150, 60);
        avlImgButton.setBackground(Color.white);
        avlImgButton.setFont(font3);
        avlImgButton.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent ecp){
                String avlRoute="";
                try { 
                    
                    avlRoute=clientListHandler.personalizeAvlRoute(userLogged);
                    System.out.println(avlRoute);
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                graphLabel.setIcon(new ImageIcon(avlRoute)); 
            }
        }); 
        clientView.add(avlImgButton);
        
        clientView.repaint();

            
        }
    */
}
