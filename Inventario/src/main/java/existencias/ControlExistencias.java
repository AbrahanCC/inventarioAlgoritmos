/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package existencias;
/**
 *
 * @author abrah
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControlExistencias {

    public void mostrarMenu() {
        Scanner scan = new Scanner(System.in);
        
        File f = new File ("productos.txt");
        
        try {
            //verificar si el archivo existe
            if (!f.exists()){
                System.out.println("El archivo de productos no existe ");
            }
            
            //Lee el archivo
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        
        //mapa para alamacenar la cantidad acumulada de productos por nombre
        //String e integer son los datos que se utilizaran para sumar la cantidad 
        Map <String, Integer> productosMapa = new HashMap<>();
        String linea;
                       
            //se ejecuta siempre y cuando hayan datos en el archivo
            while ((linea = br.readLine()) != null){
                
                //Separa los datos
                String[] datos = linea.split("\\|");
                
                //Verifica si la longitud de la linea de datos tiene 5 o mas datos
                if (datos.length >=5){
                
                    String nombreProducto = datos[1].trim();//obtener el nombre del producto sin espacios, indice 1
                    int cantidadProducto = Integer.parseInt(datos[3].trim()); //obtener la cantidad del producto, como es una cadena, se debe convertir a entero 
                
                    //Sumar la cantidad en el mapa
                    //put agrega nuevos valores, es lo que comparara y sumara las cantidades
                    // si el nombre no esta  returna 0 de lo contrario suma la cantidad
                    productosMapa.put(nombreProducto, productosMapa.getOrDefault(nombreProducto, 0) + cantidadProducto);
                } 
            }
            br.close();
            
            //Mostrar los productos con sus cantidades acumuladas
            System.out.println("Nombre del producto y su disponibilidad \n");
            
            // returna los lo que se comparo, en este caso el String Nombre y el Int cantidad
            for (Map.Entry < String, Integer> entry : productosMapa.entrySet()){
                
                //entry.getKey regresa la clave que se comraro, en este caso el nombre indice[1]
                //entry.getValue si hay cantidades con el mismo nombre las suma de lo contrario las deja con el mismo valor, indice [3]
                System.out.println("Producto " + entry.getKey() + "|" + "Cantidad disponible " + entry.getValue() + "\n");
            }
  
            }
        //aca solo es catch(IOException, pues es por si el archivo no se puede leer
        catch (IOException ex){
                }
    }
}
