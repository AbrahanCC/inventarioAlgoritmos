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

                            // crea un nuevo ID, se incrementa
                            while ((linea = br.readLine()) != null) {
                                id++;// mientras la linea no este vacia se cuenta y se incrementa 1
                            }
                            br.close();// cierra el lector que comprueba que existan lineas de datos
                        }

                        // abre el archivo para la escritura
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

                            // verifica si ya existe una categoria con el mismo nombre
                            while ((linea = br.readLine()) != null) {
                                String[] datos = linea.split("\\|");//separa los datos escritos para comparar
                                if (datos.length > 1 && datos[1].trim().equalsIgnoreCase(nombreCategoria)) {
                                    existeCategoria = true;
                                    break;
                                }
                            }
                            br.close();// cierra el lector de archivos despues de comprobar
                        }

                        // verifica si el nombre esta vacio o la categoria ya existe
                        if (nombreCategoria.isEmpty() || existeCategoria) {
                            System.out.println("El nombre no puede estar vacío o la categoría ya existe.");
                            return;// no agrega la categoria, pues ya existe
                        }

                        // escribe la nueva categoria en el archivo
                        bw.write(id + " | " + nombreCategoria + "\n");

                        // cierra el eescirtor despues de agregar la cateforia
                        
                        bw.close();

                        System.out.println("Categoria agregada correctamente");
                    } catch (IOException ex) {
                        Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            
                case 2 :
                    try{
                        System.out.println("Ingrese el ID de la categoria que quiere eliminar");
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
                        
                        // Recorre las categorias y copia solo las que no tienen el ID que se va a eliminar
                        while ((linea = br.readLine()) != null){
                            String[] datos = linea.split("\\|");
                            
                            if (!datos[0].equals(Eliminar)){
                                bw.write(linea+ "\n");
                            } else{
                                categoriaEliminada = true;
                            }
                        }
                            br.close();
                            bw.close();
                            
                            // sobreescribe el archivo original con la copia
                            Files.move(fcc.toPath(), c.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                            
                            if (categoriaEliminada) {
                                System.out.println("Categoria con ID " + Eliminar + " eliminado correctamente");
                            }
                            else{
                                System.out.println("No se encontro una categoria con ese ID");
                            }
                        
                    }
                    catch (IOException ex){
                        Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                    
                case 3: { 
                    try { 
                        //verificar si el archivo existe
                        if (!c.exists()){
                            System.out.println("No hay categorias por modificar");
                            break;
                        }
                        
                        //ingresar el ID de la categoria para modificar
                        System.out.println("Ingrese el ID de la categoria que desea modificar");
                        String modificar = scan.nextLine();
                        
                        // crear un archivo temporal para sobreescribir los productos que no se modificaran
                        FileReader fr = new FileReader(c);
                        BufferedReader br = new BufferedReader (fr);
                        
                        File fcc = new File("archivo_categorias_copia.txt");
                        FileWriter fw = new FileWriter(fcc);
                        BufferedWriter bw = new BufferedWriter(fw);
                        
                        String linea;
                        boolean categoriaModificada = false;
                        
                        while ((linea = br.readLine()) != null){
                            String [] datos = linea.split("\\|");
                            
                            // si el Id conincide, se puede modificar
                            if (datos[0].equals(modificar)){
                            System.out.println("Categoria encontrada");
                            System.out.println("Nombre " + datos[1]);
                            
                            //pedir nuevos datos
                            System.out.println("Ingrese el nuevo nombre");
                            String nuevoNombre = scan.nextLine();
                            scan.nextLine();// para saltar al siguiente scaner
                               
                            //Actualizar la linea con los nuevos datos
                            linea = datos[0] + "|" + nuevoNombre + "|";
                            categoriaModificada = true;
                            
                            //escribir la linea modificada en el archivo temporal
                            bw.write(linea + "\n");
                            }
                            
                            //cerrar los flujos de escritura
                            br.close();
                            bw.close();
                            
                            // si la categoria es modificada, reemplazar el archivo temporal por el original
                            if (categoriaModificada){
                                Files.move(fcc.toPath(), c.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                                System.out.println("Categoria con ID " + modificar + "modificado correctamente ");
                            } else{
                            // si no se encontro la categoria se eliminal el archivo temporal
                            fcc.delete();
                                System.out.println("Producto con ID " + modificar + "no se encontro" );
                            }}}
                             catch (IOException ex) {
                                    Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null , ex);
                                     }                        
                                break;
                            }
                        
                case 4 :{
                    try{
                        FileReader fr = new FileReader(c);
                        BufferedReader br = new BufferedReader(fr);
                        
                        String linea;
                        
                        System.out.println("\n CATEGORIAS AGREGADAS \n");
                        
                        while ((linea = br.readLine()) != null) {
                            System.out.println(linea + "\n");
                        }
                        
                        br.close();
                        
                        }
                        catch (IOException ex){
                          Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE,null, ex);
                        }       
                }
                
                case 5 :{
                    System.out.println("Saliendo del menu de categorias");
                    return;// sale del menu de categorias
                }
                
                default:
                    System.out.println("Opcion invalida ");
                    
            }
        }
    }
}