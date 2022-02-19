
package udrawingpaper;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Erwin14k
 */
public class UDrawingPaper {
    //Variables Globales
    //Estas 2 variables almacenarán las opciones escogidas del menú
    static int chosenOption,parametersOption,windowsNumber;
    //Estas 2 variables servirán para la lectura del archivo Json
    static String route="",content="";
    //Estos scanners, recolectarán la información ingresada en consola
    static Scanner optionsScanner = new Scanner(System.in);
    static Scanner routeCollectorScanner = new Scanner(System.in);
    static Scanner stepsScanner=new Scanner(System.in);
    static Scanner reportsScanner=new Scanner(System.in);
    static Scanner clientScanner=new Scanner(System.in);
    //Instanciamos nuestras clases para poder usarlas
    static Queue quequeHandler= new Queue();
    static LinkedList linkedListHandler=new LinkedList();
    static ClientWindow clientWindowHandler=new ClientWindow();
    static AttendedList attendedListHandler = new AttendedList();
    static WaitingList waitingListHandler = new WaitingList();
    static PrinterQueue printerQueueHandler= new PrinterQueue();
    //Variable global que guarda el número de clientes.
    static int clientsCounter=0;
    //Variable que controla en que paso vamos
    static int stepsCounter=0;
    static PrinterQueue printerQueue1=new PrinterQueue();
    static PrinterQueue printerQueue2=new PrinterQueue();
    static Printer colorPrinter= new Printer(1, "lista", printerQueue1, 2);
    static Printer bwPrinter= new Printer(2, "lista", printerQueue2, 1);
    static int freeTime=0;
    static AttendedList bwDataList=new AttendedList();
    static AttendedList colorDataList=new AttendedList();
    static AttendedList stepsDataList=new AttendedList();
    static ArrayList randomNames = new ArrayList();
    static ArrayList randomLastNames = new ArrayList();
    
    //namesList.add("Iago","Elias","Brandon","Catalina","Ana","Belen","Adela","Elena","María","Erwin","Javier","Hector","Wilmer","Fernando","Lucía");
    //static String[] lastNames={"Zurita","Florez","Vergara","Castellanos","Sánchez","Rojo","Pinto","Jara","Suarez","Orozco","Gaviria","Robles","Herrera","Salazar","Montilla"};

    public UDrawingPaper() {
    }

    
    public static void main(String[] args) throws IOException {
        fillRandomNamesLists();
        mainMenu();
    }
    /*En este caso se crea la función menú, la cual servirá para controlar 
    el flujo de la aplicación*/
    public static void mainMenu() throws IOException{
    
        
        /* Se instancia un Do-While, esto con el objetivo de mostrar el menú las veces que sea 
        necesario, hasta cumplir con alguna opción del mismo*/
        do {
            System.out.println("\n\n\n");
            System.out.println("==========Erwin14k UDrawing Paper========");
            System.out.println("| 1.Parámetros Iniciales                |");
            System.out.println("| 2.Entrar Al Menú De Operaciones       |");
            System.out.println("| 3.Información Del Estudiante          |");
            System.out.println("| 4.Salir                               |");
            System.out.println("=========================================");;
            System.out.println();
            System.out.println("Teclee la opción requerida: ");
             //Variable que almacena el dígito de la opción seleccionada
            chosenOption = optionsScanner.nextInt();
            //Lo hará hasta que se cumpla la condición del while
        } while (chosenOption < 1 || chosenOption >6);
            /*Una vez cumplida la condición del Do-While, le sigue un switch, ya que para
            cada opción seleccionada hay una diferente acción que debe ser realizada*/
        switch (chosenOption) {
            case 1:
                parametersMenu();
            case 2:
                System.out.println("\n\n\n");
                stepMenu();
            case 3:
                System.out.println("\n\n\n");
                System.out.println("=========Datos Estudiante========");
                System.out.println("| Erwin Fernando Vásquez Peñate |");
                System.out.println("| 20 años                       |");
                System.out.println("| 202001534                     |");
                System.out.println("| Estructura De Datos Sección'C'|");
                System.out.println("| Primer Semestre 2022          |");
                System.out.println("=================================");
                mainMenu();
             case 4:
                System.exit(0);
                optionsScanner.close();
            }
    }
    public static void parametersMenu() throws IOException{
        do {
            System.out.println("\n\n\n");
            System.out.println("======Parámetros Iniciales=====");
            System.out.println("| 1.Carga Masiva De Clientes  |");
            System.out.println("| 2.Cantidad De Ventanillas   |");
            System.out.println("| 3.Volver al menu principal  |");
            System.out.println("===============================");
            System.out.println("Teclee la opción requerida: ");
            parametersOption = optionsScanner.nextInt();
        } while (parametersOption < 1 || parametersOption >3 );
        switch (parametersOption) {
             case 1:
                System.out.println("Ingrese la ruta del archivo Json: ");
                //La ruta del archivo Json el cual leeremos
                route = routeCollectorScanner.nextLine();
                //Mandamos a leer el Json y a meter los objetos a la cola de recepción.
                readJson(route, content);
                //Mostramos la cantidad de clientes ingresados en la carga masiva.
                System.out.println("========================================");
                System.out.println("El número de clientes ingresados es: "+quequeHandler.queueSize());
                //Se muestra un listado de los clientes con su nombre.
                quequeHandler.showQueue();
                System.out.println("========================================");
                
                parametersMenu();     
            case 2: 
                System.out.println("Ingrese La Cantidad De Ventanillas Iniciales: ");
                windowsNumber = optionsScanner.nextInt();
                for (int i = 0; i < windowsNumber; i++) {
                    StackList tempStack=new StackList();
                    Window newWindow = new Window(i+1,"libre",tempStack);
                    linkedListHandler.finalInsert(newWindow);
                    //System.out.println(newWindow.getId());
                }
                System.out.println("Las ventanillas disponibles son: "+ windowsNumber);
                //linkedListHandler.travelList();
                parametersMenu();
            case 3:
                mainMenu();
        }
        
    }
    
    
    public  static void readJson(String route,String content){
        /*Los parámetros son la ruta del archivo, y un string vacío llamado content
        para guardar el contenido del archivo json
        */
        
        //Inicializamos variables
        File archive = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        //Todo va dentro de un try para poder visualizar posibles errores.
        try{
            //Se inicia la lectura del archivo
            //Este archivo es extraido de la ruta que viene como parámetro.
            archive =new File(route);
            //Con estas 2 líneas leeremos el archivo.
            fr = new FileReader(archive);
            br = new BufferedReader(fr);
            //Variable temporal que irá guardando las líneas del documento Json.
            String line;
            //Ciclo While para leer línea por línea.
            while ((line = br.readLine()) != null) {
                //Solo agregamos el contenido de la línea leida.
                content += line;
            }
        }catch (Exception e) {
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
        //Variable la cuál guarda el Json ya personalizado por nuestro método.
        String jsonFormat= personalizeJson(content);
        //Empezamos el parseo
        JsonParser parser = new JsonParser();
        // JsonArray = arreglo de objetos Json, en este caso de tipo cliente.
        JsonArray clientsList = parser.parse(jsonFormat).getAsJsonArray();
        //Ya con el arreglo con objetos, para meterlos a la cola, hay que recorrer dicho arreglo.
        for (int i = 0; i < clientsList.size(); i++) {
            // JsonObject = Toma el Objeto del Json actual
            JsonObject object = clientsList.get(i).getAsJsonObject();                
            //Guardamos atributos del objeto en variables
            int id = object.get("id_cliente").getAsInt();
            String name = object.get("nombre_cliente").getAsString();
            int colorImageCounter = object.get("img_color").getAsInt();
            int bwImageCounter = object.get("img_bw").getAsInt();
            int totalImagesCounter=colorImageCounter+bwImageCounter; 
            
            // Se crea el objeto cliente
            ImageLinkedList tempImageList = new ImageLinkedList();
            Client newClient = new Client(id, name,colorImageCounter, bwImageCounter,0,-1,"recepción",totalImagesCounter,0,colorImageCounter,bwImageCounter,tempImageList,totalImagesCounter,false,"no",0);
            //El objeto cliente se agrega a la cola
            quequeHandler.insert(newClient);
            //Vaciamos nuestra variable que venía vacía inicialmente
            content ="";
            clientsCounter=clientsList.size();
        }
        
    }
    public static String personalizeJson(String content){
        /*Variable importantísima llamada rgxIDontWant, la cuál almacena una expresión 
        regular para eliminar del archivo json todas las coincidencias.*/
        String rgxIDontWant="\"Cliente[0-9]+\":";
        //Variable para almacenar content con las coincidencias borradas
        String contentWithoutRgx = content.replaceAll(rgxIDontWant, "");
        //Variable la cuál guarda un string reemplaza el primer caracter "{" por "["
        String firstSymbolDelete= contentWithoutRgx.replaceFirst("\\{", "[");
        //Variable la cuál guarda un string reemplaza el ultimo caracter "}" por "]" 
        String lastSymbolDelete = firstSymbolDelete.replaceFirst(".$", "]");
        //Ya con un string adecuado lo devolvemos para que lo pueda utilizar otro método.
        return lastSymbolDelete;
    }
    public static void masterMindAlgorithm(){
        createRandomClients();
        stepsCounter++;
        boolean clientCan=false;
        System.out.println("=============Paso Número: "+stepsCounter+"=============");
        /*Primera fase del algoritmo, verifica cual es el primer cliente de la cola,
        el primero de la lista avanza a ventanillas, si y solo sí alguna está libre*/
        
        // Se recolecta el id del primer cliente en la cola
        int idActualClient=quequeHandler.firstIdInQueue();
        
        //Se verifica si hay alguna ventanilla disponible.
        int availableWindow=linkedListHandler.isAWindowOpen();
        
        //Si hay ventanilla disponible traslada al cliente a la vetanilla
        //También verifica que haya alguien en la cola
        if (availableWindow != (-1) && idActualClient!= (-1)){
            quequeHandler.updateClientWindow(idActualClient,availableWindow);
            System.out.println("El cliente con el id: "+idActualClient+" Se traslada a la ventanilla "+availableWindow);
            //Se procede a recolectar los datos del cliente antes de eliminar de cola.
            Client updateClient= quequeHandler.firstInQueue();
            if (updateClient.getTotalImages()==0){
                System.out.println("El cliente con el id "+updateClient.getId()+" No tiene imágenes para imprimir, sale del sistema! ");
                updateClient.setSteps(stepsCounter-updateClient.getInitialStep());
                attendedListHandler.finalInsert(updateClient);
                bwDataList.bwDataSorting(updateClient);
                colorDataList.colorDataSorting(updateClient);
                stepsDataList.stepsDataSorting(updateClient);
                quequeHandler.delete();
            }
            else{
                
            
            clientWindowHandler.finalInsert(updateClient);
            //Se elimina el cliente de la cola.
            quequeHandler.delete();
            //Se actualiza la ventanilla a estado ocupada
            linkedListHandler.uptadeBusyWindowState(availableWindow);
            clientCan=false;
            }
        //Si no hay ventanilla disponible, no hay movimiento en la cola
        }/*else{
            System.out.println("En este paso no hay ventanilla disponible, el cliente debe esperar");
        }*/
        
        /*Ya verificada la primera fase del movimiento de cola a ventanilla se
        procede a realizar el proceso de entrga de imágenes a ventanilla.
        Se verifica que clientes están en ventanilla y hacen entrega de una
        imágen en caso aún tengan que entregar, si no pasan a la lista de espera
        de impresión.*/
        if(clientCan){
            //System.out.println("hola");
            clientWindowHandler.giveImageToTheWindow2();
            
        }else{
            clientWindowHandler.giveImageToTheWindow(idActualClient);
            
            
            
        }
        
        //Aquí se eliminan a todos los atendidos en este caso.
        int chekIntruders=-1;
        do{
            clientWindowHandler.deleteOfTheWindow();
            chekIntruders=clientWindowHandler.checkIntruders();
        }while(chekIntruders!=0);
        //Para verificar si hay alguna ventanilla libre luego de las entregas de las imágenes.
        /*if(linkedListHandler.isAWindowOpen()!=-1){
            int idActualClient2=quequeHandler.firstIdInQueue();
            int availableWindow2=linkedListHandler.isAWindowOpen();
            
            
        }*/
        
        /*La tercera fase del proceso, se maneja todo lo relacionado con las 
        impresiones. Estas líneas comentadas, solo es para ver el comportamiento
        de las pilas en ventanilla y colas de impresión.*/
        
        /*System.out.println("Pila ventanilla 1: ");
        linkedListHandler.travelListById(1);
        System.out.println("Pila Ventanilla 2:");
        linkedListHandler.travelListById(2);
        System.out.println("Cola de impresión de la impresora a color:");
        colorPrinter.getPrinterQueue().showQueue();
        System.out.println("Cola de impresión de la impresora a bw:");
        bwPrinter.getPrinterQueue().showQueue();*/ 
        colorPrinterStatus();
        bwPrinterStatus();
        waitingListHandler.validateClientsToPrint();
    }
    public static void colorPrinterStatus(){
        int validateId=colorPrinter.getPrinterQueue().idClientFirstImageInQueue();
        if(colorPrinter.getSecondsToBeFree()==2 && colorPrinter.getPrinterQueue().isEmpty() ){
            //System.out.println("aaaa");
            freeTime++;
        }else if (colorPrinter.getSecondsToBeFree()==2 && !colorPrinter.getPrinterQueue().isEmpty() && waitingListHandler.checkTheValidation(validateId)){
            //System.out.println("Vale aquí si entra");
            colorPrinter.setSecondsToBeFree(colorPrinter.getSecondsToBeFree()-1);
           
        }
        else if(colorPrinter.getSecondsToBeFree()==1 && !colorPrinter.getPrinterQueue().isEmpty()){
            colorPrinter.setSecondsToBeFree(colorPrinter.getSecondsToBeFree()-1);
            if(colorPrinter.getSecondsToBeFree()==0){
                //System.out.println("Debería eliminar");
                int idClient=colorPrinter.getPrinterQueue().idClientFirstImageInQueue();
                Image tempImage=colorPrinter.getPrinterQueue().FirstImageInQueue();
                System.out.println("Cliente No. "+idClient+" Recibe una imágen impresa a color");
                waitingListHandler.updateWaitingStatus(idClient, tempImage);
                colorPrinter.getPrinterQueue().delete();
                colorPrinter.setSecondsToBeFree(2);
            
            }   
        } 
    }    
    
    public static void bwPrinterStatus(){
        int validateId=bwPrinter.getPrinterQueue().idClientFirstImageInQueue();
        if(bwPrinter.getSecondsToBeFree()==1 && bwPrinter.getPrinterQueue().isEmpty() ){
            //System.out.println("aaaa");
            freeTime++;
        }else if (bwPrinter.getSecondsToBeFree()==1 && !bwPrinter.getPrinterQueue().isEmpty() && waitingListHandler.checkTheValidation(validateId)){
            //System.out.println("Vale aquí si entra");
            bwPrinter.setSecondsToBeFree(bwPrinter.getSecondsToBeFree()-1);
            int idClient=bwPrinter.getPrinterQueue().idClientFirstImageInQueue();
                Image tempImage=bwPrinter.getPrinterQueue().FirstImageInQueue();
                System.out.println("Cliente No. "+idClient+" Recibe una imágen impresa a blanco y negro");
                waitingListHandler.updateWaitingStatus(idClient, tempImage);
                bwPrinter.getPrinterQueue().delete();
                bwPrinter.setSecondsToBeFree(1);
           
        }
         
        }
   public static void stepMenu() throws IOException{
       int stepOption=0;
       do {
            System.out.println("\n\n\n");
            System.out.println("==========Erwin14k UDrawing Paper========");
            System.out.println("| 1.Ejectutar Paso                      |");
            System.out.println("| 2.Estado En Memoria De Las Estructuras|");
            System.out.println("| 3.Generar Reportes                    |");
            System.out.println("| 4.Información Cliente Específico      |");
            System.out.println("| 5.Volver al menú principal            |");
            System.out.println("=========================================");
            System.out.println();
            System.out.println("Teclee la opción requerida: ");
             //Variable que almacena el dígito de la opción seleccionada
            stepOption = stepsScanner.nextInt();
            //Lo hará hasta que se cumpla la condición del while
        } while (stepOption < 1 || stepOption >5);
       switch (stepOption) {
            case 1:
                System.out.println("\n\n\n");
                masterMindAlgorithm();
                stepMenu();
            case 2:
                //Se genera un grafo de la lista de pilas de las ventanillas
                linkedListHandler.graphvizGenerator();
                //Se genera un grafo de la cola de recepción
                quequeHandler.graphvizGenerator();
                //Se genera un grafo de la cola de impresión a color
                colorprinterGraphvizGenerator();
                //Se genera un grafo de la cola de impresión a blanco y negro
                bwprinterGraphvizGenerator();
                colorprinterGraphvizGenerator();
                //Se genera un grafo de la lista de atendidos
                attendedListHandler.graphvizGenerator();
                //waitingListHandler.updateStepsStatus();
                //Se genera un grafo de la lista de espera de clientes.
                waitingListHandler.graphvizGenerator();
                stepMenu();
            case 3:
                reportsMenu();
                
            case 4:
                System.out.println("Teclee el id del cliente buscado (Tiene que ser un cliente ya atendido): ");
                //Variable que almacena el dígito de la opción seleccionada
                int clientOption = clientScanner.nextInt();
                attendedListHandler.specificClientReport(clientOption);
                stepMenu();
            case 5:
                mainMenu();
            }
       
   }
   public static void reportsMenu() throws IOException{
       int reportsOption=0;
        do {
            System.out.println("\n\n\n");
            System.out.println("==========Erwin14k UDrawing Paper=========");
            System.out.println("| 1.Top clientes con menos imágenes bw   |");
            System.out.println("| 2.Top clientes con más imágenes a color|");
            System.out.println("| 3.Top clientes con más pasos en sistema|");
            System.out.println("| 4.Volver al menú de operaciones        |");
            System.out.println("==========================================");
            System.out.println();
            System.out.println("Teclee la opción requerida: ");
            //Variable que almacena el dígito de la opción seleccionada
            reportsOption = reportsScanner.nextInt();
            //Lo hará hasta que se cumpla la condición del while
        } while (reportsOption < 1 || reportsOption >4);
        switch (reportsOption) {
            case 1:
                System.out.println("\n\n\n");
                bwDataList.topBwReport();
                bwDataList.topBwReportByGraphviz();
                reportsMenu();
            case 2:
                System.out.println("\n\n\n");
                colorDataList.topColorReport();
                colorDataList.topColorReportByGraphviz();
                reportsMenu();
            case 3:
                System.out.println("\n\n\n");
                stepsDataList.topStepsReport();
                stepsDataList.topStepsReportByGraphviz();
                reportsMenu();
            case 4:
                stepMenu();
                }
       
   }
   public static String colorprinterGraphvizGenerator() throws IOException{
        String route="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Texto\\color.txt";
        String graph="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Img\\ColaImpresoraColor.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        String finalText="digraph G{\nnode [shape=box];\n";
        
        String rankSame="{rank=same; ";
        String conections="";
        String nodes="";
        String collectQueueImages="",collectConection="";
        if(!colorPrinter.getPrinterQueue().isEmpty()){
            collectQueueImages=colorPrinter.getPrinterQueue().collectPrinterQueue();
            collectConection=colorPrinter.getPrinterQueue().collectConections();
            nodes+=collectQueueImages;
            conections=collectConection;
            //finalText+="start"+" -> "+"N"+colorPrinter.getPrinterQueue().begin.hashCode()+";\n";
        }
        
        finalText+=nodes+"\n";
        finalText+="{rank= same;\n"+conections+"\n";
        
        finalText+="start [shape=Mdiamond label=\"Cola Impresora a color\"];";
        //finalText+=rankSame;
        finalText+="}\n}";
        FileWriter fw = new FileWriter(route);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(finalText);
        bw.close();
        
        String[] cmd = new String[5];
        cmd[0] = pathString;
        cmd[1] = tParam;
        cmd[2] = route;
        cmd[3] = tOParam;
        cmd[4] = graph;

        Runtime rt = Runtime.getRuntime();

        rt.exec( cmd );
        
        return finalText;
    }
   
    public static String bwprinterGraphvizGenerator() throws IOException{
        String route="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Texto\\bw.txt";
        String graph="C:\\Users\\Erwin14k\\Documents\\EDD_PROYECTO_FASE1_202001534\\Reportes Img\\ColaImpresoraBW.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        String finalText="digraph G{\nnode [shape=box];\n";
        
        
        String conections="";
        String nodes="";
        String collectQueueImages="",collectConection="";
        if(!bwPrinter.getPrinterQueue().isEmpty()){
            collectQueueImages=bwPrinter.getPrinterQueue().collectPrinterQueue();
            collectConection=bwPrinter.getPrinterQueue().collectConections();
            nodes+=collectQueueImages;
            conections=collectConection;
            //finalText+="start"+" -> "+"N"+bwPrinter.getPrinterQueue().begin.hashCode()+";\n";
        }
        
        finalText+=nodes+"\n";
        finalText+="{rank= same;\n"+conections+"\n";
        
        finalText+="start [shape=Mdiamond label=\"Cola Impresora a Blanco y negro\"];";
        finalText+="}\n}";
        FileWriter fw = new FileWriter(route);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(finalText);
        bw.close();
        
        String[] cmd = new String[5];
        cmd[0] = pathString;
        cmd[1] = tParam;
        cmd[2] = route;
        cmd[3] = tOParam;
        cmd[4] = graph;

        Runtime rt = Runtime.getRuntime();

        rt.exec( cmd );
        
        return finalText;
    }
   public static void createRandomClients(){
        Random r = new Random();
        int numberRandomClients = r.nextInt(3-0)+0;
        int bwRandom=r.nextInt(2-0)+0;
        int colorRandom=r.nextInt(2-0)+0;
        int totalImages=bwRandom+colorRandom;
        for (int i = 0; i < numberRandomClients; i++) {
            clientsCounter++;
            String tempName="";
            String tempLastName="";
            String randomName="";
            ImageLinkedList tempImageList = new ImageLinkedList();
            int a = (int) (Math.random() * 20);
            int b = (int) (Math.random() * 20);
            tempName=randomNames.get(a).toString();
            tempLastName=randomLastNames.get(b).toString();
            randomName=tempName+" "+tempLastName;
            
            Client newClient = new Client(clientsCounter, randomName,colorRandom, bwRandom,0,-1,"recepción",totalImages,0,colorRandom,bwRandom,tempImageList,totalImages,false,"no",0);
            quequeHandler.insert(newClient);
        }
   }
   public static void fillRandomNamesLists(){
       //Se meten nombres al arreglo de nombres para elegir aleatoriamente
       randomNames.add("Pepe");
       randomNames.add("Luuk");
       randomNames.add("Katherine");
       randomNames.add("Brandon");
       randomNames.add("Catalina");
       randomNames.add("Ana");
       randomNames.add("Belen");
       randomNames.add("Adela");
       randomNames.add("Elena");
       randomNames.add("Adriana");
       randomNames.add("Maria");
       randomNames.add("Erwin");
       randomNames.add("Javier");
       randomNames.add("Jorge");
       randomNames.add("Matias");
       randomNames.add("Hector");
       randomNames.add("Monica");
       randomNames.add("Lucia");
       randomNames.add("Fernanda");
       randomNames.add("Julian");
       //Se insertan apellidos a la lista de apellidos para escoger aleatoriamente
       randomLastNames.add("Rojo");
       randomLastNames.add("Pinto");
       randomLastNames.add("Vasquez");
       randomLastNames.add("Rosales");
       randomLastNames.add("Sanchez");
       randomLastNames.add("Orozco");
       randomLastNames.add("Luna");
       randomLastNames.add("Castro");
       randomLastNames.add("Ortiz");
       randomLastNames.add("Mejia");
       randomLastNames.add("Garcia");
       randomLastNames.add("Muñoz");
       randomLastNames.add("Pelaez");
       randomLastNames.add("Bermudez");
       randomLastNames.add("Osorio");
       randomLastNames.add("Orantes");
       randomLastNames.add("Santizo");
       randomLastNames.add("Marquez");
       randomLastNames.add("Gonzalez");
       randomLastNames.add("Marroquin");
       
       
       
   }
   
   
}   
    
    
    

