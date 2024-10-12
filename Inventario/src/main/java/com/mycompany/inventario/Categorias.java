/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario;
/**
 *
 * @author abrah
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Categorias {
    public void mostrarMenu(){
        Scanner scan = new Scanner(System.in);
        
        int categoria = 0;
        File c = new File ("categorias.txt");
        
        while (true){ 
            System.out.println("1. Agregar categorias ");
            System.out.println("2. Eliminar categorias ");
            System.out.println("3. Modificar categorias ");
            System.out.println("4. Mostrar categorias ");
            System.out.println("5. Salir");
            categoria=scan.nextInt();
            scan.nextLine();
        
        
            switch (categoria) {

                case 1:
                    try {

                        int id = 1;

                        if (c.exists()) {//lee el archivo para seguir la autonumeracion
                            //comienza a contar cuantas linea de texto hay
                            FileReader fr = new FileReader(c);
                            BufferedReader br = new BufferedReader(fr);
                            String linea;

                            while ((linea = br.readLine()) != null) {
                                id++;// mientras la linea no este vacia se cuenta y se incrementa 1
                            }
                            br.close();
                        }

                        FileWriter fw = new FileWriter(c, true);// true es para agregar y no trncar el archiuvo de txt
                        BufferedWriter bw = new BufferedWriter(fw);//lee lo que va agregando

                        System.out.println("Nombre de la categoria");
                        String nombreCategoria = scan.nextLine();

                        //Bandera logica para verificar si la categorai existe
                        boolean existeCategoria = false;

                        if (c.exists()) {//leer el archivo para poder ver si ya tiene categorias
                            FileReader fr = new FileReader(c);
                            BufferedReader br = new BufferedReader(fr);
                            String linea;

                            while ((linea = br.readLine()) != null) {
                                String[] datos = linea.split("\\|");
                                if (datos[1].trim().equalsIgnoreCase(nombreCategoria.trim())){
                                    existeCategoria = true;
                                    break;
                                }
                            }
                        br.close();
                        }
                        
                    
                    if (nombreCategoria.isEmpty() || existeCategoria ) {
                        System.out.println("El nombre no puede estar vacío o la categoría ya existe.");
                        return;
                    }

                    bw.write(id + " | " + nombreCategoria + "\n");
                    fw.close();
                    bw.close();
                    
                    System.out.println("Categoria agregada correctamente");
                    
                    }
           
            catch (IOException e){
                                Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null,e );
                                    }
            break;
            
                case 2 :
                    try{
                        System.out.println("Ingrese el ID de la categoria ");
                        String Eliminar = scan.nextLine();
                        
                        FileReader fr = new FileReader(c);
                        BufferedReader br = new BufferedReader (fr);
                        
                        //archivo copia 
                        File fcc = new File("archivos_categorias_copia.txt");
                        
                        // crea el escritor del archivo copia
                        FileWriter fw = new FileWriter (fcc);
                        BufferedWriter bw = new BufferedWriter(fw);
                        
                        String linea;
                        boolean categoriaEliminada = false;
                        
                        while ((linea = br.readLine()) != null){
                            String[] datos = linea.split("\\|");
                            
                            if (!datos[0].equals(Eliminar)){
                                bw.write(linea+ "\n");
                            } else{
                                categoriaEliminada =true;
                            }
                        }
                            br.close();
                            bw.close();
                            
                            Files.move(fcc.toPath(), c.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Categoria con ID " + Eliminar + " eliminado correctamente");
                        
                    }
                    catch (IOException ex){
                        Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                    
                case 3: {
                
                }
                        
                           
                        
                        
                    
            }
        }
    }
}
