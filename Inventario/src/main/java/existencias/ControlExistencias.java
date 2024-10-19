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
            if (f.exists()){
                System.out.println("El archivo de productos no existe ");
            }
            
            //Lee el archivo
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        
        //mapa para alamacenar la cantidad acumulada de productos por nombre
        Map < String, Integer > productosMap = new HashMap<>();
        String linea;
                       
            //se ejecuta siempre y cuando hayan datos en el archivo
            while ((linea = br.readLine()) != null){
                
                //Separa los datos
                String[] datos = linea.split("\\|");
                
                //Verifica si la longitud de la linea de datos tiene 5 o mas datos
                if (datos.length >=5){
                
                    String nombreProducto = datos[1].trim();//obtener el nombre del producto sin espacios
                    int cantidadProducto = Integer.parseInt(datos[3].trim()); //obtener la cantidad del producto
                
                    //Sumar la cantidad en el mapa
                    productosMap.put(nombreProducto, productosMap.getOrDefault(nombreProducto, 0)+ cantidadProducto);
                } 
            }
            br.close();
            
            //Mostrar los productos con sus cantidades acumuladas
            System.out.println("Descripcion del producto y su disponibilidad \n");
            for (Map.Entry < String, Integer> entry : productosMap.entrySet()){
                System.out.println("Producto " + entry.getKey() + "|" + "Cantidad disponible " + entry.getValue() + "\n");
            }
  
            } catch (IOException ex){
                }
    }
}
