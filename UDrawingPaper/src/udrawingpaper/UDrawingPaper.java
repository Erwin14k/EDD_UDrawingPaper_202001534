
package udrawingpaper;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    //Instanciamos nuestras clases para poder usarlas
    static Queue quequeHandler= new Queue();
    static LinkedList linkedListHandler=new LinkedList();
    static ClientWindow clientWindowHandler=new ClientWindow();
    //Variable global que guarda el número de clientes.
    static int clientsCounter=0;
    //Variable que controla en que paso vamos
    static int stepsCounter=0;
    

    
    public static void main(String[] args) {
        mainMenu();
    }
    /*En este caso se crea la función menú, la cual servirá para controlar 
    el flujo de la aplicación*/
    public static void mainMenu(){
    
        
        /* Se instancia un Do-While, esto con el objetivo de mostrar el menú las veces que sea 
        necesario, hasta cumplir con alguna opción del mismo*/
        do {
            System.out.println("\n\n\n");
            System.out.println("==========Erwin14k UDrawing Paper========");
            System.out.println("| 1.Parámetros Iniciales                |");
            System.out.println("| 2.Ejectutar Paso                      |");
            System.out.println("| 3.Estado En Memoria De Las Estructuras|");
            System.out.println("| 4.Generar Reportes                    |");
            System.out.println("| 5.Información Del Estudiante          |");
            System.out.println("| 6.Salir                               |");
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
                masterMindAlgorithm();
                mainMenu();
            case 3:
                System.out.println("opcion 3 no está disponible por el momento");
                mainMenu();
            case 4:
                System.out.println("La generación de reportes no está disponible");
                mainMenu();
            case 5:
                System.out.println("\n\n\n");
                System.out.println("=========Datos Estudiante========");
                System.out.println("| Erwin Fernando Vásquez Peñate |");
                System.out.println("| 20 años                       |");
                System.out.println("| 202001534                     |");
                System.out.println("| Estructura De Datos Sección'C'|");
                System.out.println("| Primer Semestre 2021          |");
                System.out.println("=================================");
                mainMenu();
             case 6:
                System.exit(0);
                optionsScanner.close();
            }
    }
    public static void parametersMenu(){
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
                    System.out.println(newWindow.getId());
                }
                System.out.println("Las ventanillas disponibles son: "+ windowsNumber);
                linkedListHandler.travelList();
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
            Client newClient = new Client(id, name,colorImageCounter, bwImageCounter,0,-1,"recepción",totalImagesCounter,0);
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
        String rgxIDontWant="\"Cliente[0-9]*\":";
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
        stepsCounter++;
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
            clientWindowHandler.insert(updateClient);
            //Se elimina el cliente de la cola.
            quequeHandler.delete();
            //Se actualiza la ventanilla a estado ocupada
            linkedListHandler.uptadeBusyWindowState(availableWindow);
     
        //Si no hay ventanilla disponible, no hay movimiento en la cola
        }else{
            System.out.println("En este paso no hay ventanilla disponible, el cliente debe esperar");
        }
        
        /*Ya verificada la primera fase del movimiento de cola a ventanilla se
        procede a realizar el proceso de entrga de imágenes a ventanilla*/
        
        
        
    }

    
}