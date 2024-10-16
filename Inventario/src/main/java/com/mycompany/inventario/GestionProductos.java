/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario;

import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;


/**
 *
 * @author abrah
 */
public class GestionProductos {

    public static void mostrarMenu() {
        Scanner scan = new Scanner(System.in);

        int opcion = 0;

        File f = new File("productos.txt");

        while (true) {

            System.out.println("1. Agregar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Mostrar productos");
            System.out.println("4. Modificar productos");
            System.out.println("5. Salir ");
            System.out.println("Seleccione una opcion ");
            opcion = scan.nextInt();
            scan.nextLine();//se hizo un salto de linea pues no deja leer el nuevo dato

            switch (opcion) {
                case 1:
                    try {
                        // Inicializar el ID en 1
                        int id = 1;

                        // Verificar si el archivo ya existe
                        if (f.exists()) {
                            // Leer el archivo para contar cuántos productos hay
                            FileReader fr = new FileReader(f);
                            BufferedReader br = new BufferedReader(fr);
                            String linea;
                            while ((linea = br.readLine()) != null) {
                                id++; // Incrementar el ID por cada línea encontrada
                            }
                            br.close(); // Cerrar el lector de archivos
                        }// finaliza la autonumeracion dle id

                        FileWriter fw = new FileWriter(f, true);// fw es un escritor de archivo si no se hace eso no funcionara
                        BufferedWriter bw = new BufferedWriter(fw); //bw es para leer lo que vas agregando 

                        System.out.println("Ingrese nombre del producto");
                        String nombre = scan.nextLine();

                        System.out.println("Ingrese descripcion del producto");
                        String descripcion = scan.nextLine();

                        System.out.println("Ingrese la cantidad de producto");
                        int cantidad = scan.nextInt();

                        System.out.println("Ingrese precio del producto compra");
                        double precio = scan.nextDouble();
                        
                        System.out.println("Ingrese precio del producto venta");
                        double precioV = scan.nextInt();

                        bw.write(id + " | " + nombre + " | " + descripcion + " | " + cantidad + " | " + " Q " + precio + " | " + precioV + "\n");
                        bw.close();
                        fw.close();

                        System.out.println("Producto agregado correctamente");
                    } catch (IOException ex) {
                        Logger.getLogger(GestionProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 2:
                    try {
                        
                        System.out.println("Ingrese el ID del producto que desea eliminar ");
                        String Eliminar = scan.nextLine();

                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);

                        File fc = new File("archivo_productos_copia.txt");// crea un archivo externo para verificar que archivo eliminar y despues de encontrarlo vuelve a llamarse del mismo nombre

                        FileWriter fw = new FileWriter(fc);
                        BufferedWriter bw = new BufferedWriter(fw);

                        String linea;
                        boolean productoEliminado = false;  // Bandera para verificar si el producto fue eliminado

                        // Leer el archivo original línea por línea
                        while ((linea = br.readLine()) != null) {
                            String[] datos = linea.split("\\|");  // Separar los campos de la línea usando '|' como delimitador

                            // Si el ID no coincide con el ingresado, escribir la línea en el archivo temporal
                            if (!datos[0].equals(Eliminar)) {
                                bw.write(linea + "\n");
                            } else {
                                productoEliminado = true;  // Se encontró y omitió el producto a eliminar
                            }
                        }

                        // Cerrar los flujos de lectura y escritura
                        br.close();
                        bw.close();

                        // Si el producto fue eliminado, reemplazar el archivo original por el archivo temporal
                        if (productoEliminado) {
                            // Reemplazar el archivo original con el archivo temporal
                            Files.move(fc.toPath(), f.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Producto con ID " + Eliminar + " eliminado correctamente.");
                        } else {
                            // El producto no se encontró, eliminar el archivo temporal
                            fc.delete();
                            System.out.println("Producto con ID " + Eliminar + " no encontrado.");
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(GestionProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 3: {
                    try {
                        // Leer el archivo y mostrar su contenido
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);

                        String linea;//se crea la cadena para mostrar el contenido

                        System.out.println(" \n Productos agregados \n ");

                        while ((linea = br.readLine()) != null) {
                            System.out.println(linea + "\n");
                        }
                        br.close();
                        fr.close();

                    } catch (IOException ex) {
                        Logger.getLogger(GestionProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

                case 4: {
                    try {
                        // Verificar si el archivo de productos existe
                        if (!f.exists()) {
                            System.out.println("No hay productos para modificar.");
                            break;
                        }

                        // Pedir al usuario el ID del producto que desea modificar
                        System.out.print("Ingrese el ID del producto que desea modificar: ");
                        String Modificar = scan.nextLine();  // Captura el ID como cadena

                        // Crear un archivo temporal para escribir los productos modificados
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);

                        File fc = new File("archivo_productos_copia.txt"); // Archivo temporal para productos restantes
                        FileWriter fw = new FileWriter(fc);
                        BufferedWriter bw = new BufferedWriter(fw);

                        String linea;
                        boolean productoModificado = false;  // Bandera para verificar si el producto fue modificado

                        // Leer el archivo original línea por línea
                        while ((linea = br.readLine()) != null) {
                            String[] datos = linea.split("\\|");  // Separar los campos de la línea usando '|' como delimitador

                            // Si el ID coincide con el ingresado, permitir modificar el producto
                            if (datos[0].equals(Modificar)) {
                                System.out.println("Producto encontrado: ");
                                System.out.println("Nombre: " + datos[1]);
                                System.out.println("Descripción: " + datos[2]);
                                System.out.println("Precio compra: " + datos[3]);
                                System.out.println("Precio venta: ");

                                // Pedir nuevos datos
                                System.out.print("Ingrese el nuevo nombre: ");
                                String nuevoNombre = scan.nextLine();

                                System.out.print("Ingrese la nueva descripcion: ");
                                String nuevaDescripcion = scan.nextLine();

                                System.out.print("Ingrese el nuevo precio compra: ");
                                double nuevoPrecio = scan.nextDouble();
                                
                                System.out.println("Ingrese el nuevo precio venta: ");
                                double nuevoPrecioV = scan.nextDouble();
                                scan.nextLine(); // limpiar el buffer

                                // Actualizar la línea con los nuevos datos
                                linea = datos[0] + "|" + nuevoNombre + "|" + nuevaDescripcion + "|" + nuevoPrecio + "|" + nuevoPrecioV;
                                productoModificado = true;
                            }

                            // Escribir la línea (modificada o no) en el archivo temporal
                            bw.write(linea + "\n");
                        }

                        // Cerrar los flujos de lectura y escritura
                        br.close();
                        bw.close();

                        // Si el producto fue modificado, reemplazar el archivo original por el archivo temporal
                        if (productoModificado) {
                            Files.move(fc.toPath(), f.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Producto con ID " + Modificar + " modificado correctamente.");
                        } else {
                            // Si no se encontró el producto, eliminar el archivo temporal
                            fc.delete();
                            System.out.println("Producto con ID " + Modificar + " no encontrado.");
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(GestionProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

                case 5:
                    // Opción para salir del sistema
                    System.out.println("Saliendo del menu de productos ");
                    return; // Finalizar la ejecución

                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        }
    }
}
