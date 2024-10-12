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

public class ControlExistencias {

    public void mostrarMenu() {
        Scanner scan = new Scanner(System.in);
        
        File f = new File ("productos.txt");
        
        try {
            //Lee el archivo
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        
        //crea una linea de texto para poder mostrar el contenido
            String linea;
            System.out.println("Descripcion del producto y su disponibilidad \n");
            
            //se ejecuta siempre y cuando hayan datos en el archivo
            while ((linea = br.readLine()) != null){
                //Separa los datos
                String[] datos = linea.split("\\|");
                
                //Verifica si la longitud de la linea de datos tiene 5 o mas datos
                if (datos.length >=5){
                
                    System.out.println("Descripcion " + " | " + datos[2] + " | " + " Cantidad disponible " + " | " + datos [4] +" | ");
                } 
            }
  
            } catch (IOException ex){
        }
    }
}
