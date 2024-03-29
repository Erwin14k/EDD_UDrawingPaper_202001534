package udrawwingpaper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

/**
 *
 * @author Erwin14k
 */
public class HashTable {
    int filledKeys;
    int size;
    String graphCode;
    int max;
    int collisions;
    int collisionNewKey;
    HashTableNode first;
    public HashTable(){
        this.filledKeys=0;
        this.collisions=1;
        this.collisionNewKey=-1;
        this.size=37;
        this.max=28;
        this.first=null;
        this.graphCode="";
    }
    
    /*Se inicializa la tabla hash, con las posiciones creadas vacías esperando
    a ser llenadas.*/
    public void initialize(){
        for(int i=0;i<size;i++){
            HashTableNode node= new HashTableNode(i,null);
            if(first==null){
                first=node;
            }else{
                HashTableNode pointer= first;
                while(pointer.next!=null){
                    pointer=pointer.next;
                }
                pointer.next=node;
            }
        } 
    }
    //Calcula la posición en la que irá el nuevo elemento en la tabla hash.
    public int calculateKey(BigInteger dpi){
        int key=(int) (dpi.longValue()% this.size);
        return key;
    }
    
    /*Función que devuelve el siguiente número primo, para el aumento del tamaño
    de la tabla hash.*/
    public int findNextPrimeNumber(){
        int counter=this.size+1;
        while(! isPrimeNumber(counter)){
            counter++;
        }
        return counter;
    }
    
    //Función que verifica que si el número enviado como parámetro es primo.
    public boolean isPrimeNumber(int number) {
        if (number == 0 || number == 1 || number == 4) {
            return false;
        }
        for (int x = 2; x < number / 2; x++) {
                if (number % x == 0)
                    return false;
        }
        return true;
    }
    
    //Si se llega al máximo porcentaje de ocupación, se agranda la tabla hash.
    public void rehash(){
        int newSize=findNextPrimeNumber();
        int iterations=newSize-size;
        for(int i=0;i<iterations;i++){
            HashTableNode node= new HashTableNode(size+(i),null);
            System.out.println(node.key+"JJJJJJJJJJJJ");
            HashTableNode pointer= first;
            while(pointer.next!=null){
                pointer=pointer.next;
            }
            pointer.next=node;
        }
        size=newSize;
        max=(int) Math.round(size*0.75);
        System.out.println("Nuevo tamaño---"+size);
        System.out.println("Nuevo max---"+max);
    }
    //Verifica si el espacio de la llave está libre.
    public boolean isFree(int key){
        HashTableNode temp = first;
        do{
            if(temp.key==key){
                return temp.deliveryCourier == null;     
            }
            temp = temp.next;
        }while(temp != null);
        //System.out.println("No se encontró");
        return false; 
    }
    public void printData(){
        //System.out.println("Que empieceeeeeeeee");
        HashTableNode temp = first;
        do{
            if(temp.deliveryCourier!=null){
                System.out.println(temp.key+"----"+temp.deliveryCourier.getName());
            }else{
                System.out.println(temp.key+"----"+temp.deliveryCourier);
            }
            
            temp = temp.next;
        }while(temp != null);
    }
    
    public boolean  firstFree(DeliveryCourier deliveryCourier){
        HashTableNode temp = first;
        do{
            if(temp.deliveryCourier==null){
                temp.deliveryCourier=deliveryCourier;  
                return true;
            }
            temp = temp.next;
        }while(temp != null); 
        return false;
    }
    
    public boolean solveCollision(BigInteger dpi, int collision){
        int key=((int) ((dpi.longValue()% 7)+1))*collision;
        System.out.println("Nueva llave "+key);
        collisionNewKey=key;
        return isFree(key);
    }
    
    public void insert(DeliveryCourier deliveryCourier){
        int key=calculateKey(deliveryCourier.getDpi());
        System.out.println("La llave a ingresar es: "+key);
        if(isFree(key)){
            HashTableNode temp = first;
            do{
                if(temp.key==key){
                    temp.deliveryCourier=deliveryCourier; 
                    System.out.println("Asignamos a: "+temp.deliveryCourier.getName()+ "En llave "+key);
                    //break;
                }
                temp = temp.next;
            }while(temp != null);
            filledKeys++;
            if(filledKeys==max){
                System.out.println("toca rehash");
                rehash();
            }
        }else{
            while(! solveCollision(deliveryCourier.getDpi(), collisions)){
                collisions++;
                if(collisionNewKey>size){
                    firstFree(deliveryCourier);
                    System.out.println("Asignamos a: "+deliveryCourier.getName());
                    break;
                }
            }
            collisions=1;
            HashTableNode temp = first;
            System.out.println("hhhhhhhh"+collisionNewKey);
            do{
                if(temp.key==collisionNewKey){
                    System.out.println("holaaaa");
                    
                    temp.deliveryCourier=deliveryCourier;   
                    System.out.println("Asignamos a: "+temp.deliveryCourier.getName());
                }
                temp = temp.next;
            }while(temp != null);
            filledKeys++;
            if(filledKeys==max){
                rehash();
            }
            
        }
    }
    
    public String generateGraph() throws IOException{
        String route="Reportes Texto/Tabla Hash.txt";
        String graph="Reportes Img/Tabla Hash.png";
        String tParam = "-Tpng";
        String tOParam = "-o";
        String pathString = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        String finalText="digraph HashTable{\n node[shape = record,fillcolor=\"bisque1\" color=\"black\" style=\"filled\" label=\"{Posición|Mensajero}|";
        HashTableNode temp = first;
        while(temp != null){
            if(temp.deliveryCourier!=null){
                finalText+="{"+temp.key+"| DPI: "+temp.deliveryCourier.getDpi()+"\\n"+temp.deliveryCourier.getName()+" "+temp.deliveryCourier.getLastName()+"\\nLicencia Tipo: "+temp.deliveryCourier.getLicenseType()+"\\nDirección: "+temp.deliveryCourier.getDirection()+"}|";
            }else{
                finalText+="{"+temp.key+"|"+temp.deliveryCourier+"}|";
            }
            temp = temp.next;
        }
        finalText+="\"]Erwin;\n}";
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
        //System.out.println(finalText);
        return finalText;
    }
}
