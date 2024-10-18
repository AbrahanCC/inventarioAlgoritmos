/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inventario;

import java.util.Scanner;

//paquete del control de existencias
import existencias.ControlExistencias;
/**
 *
 * @author abrah
 */
public class Inventario {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("~~~~~~Bienvenido al inventario~~~~~~");
        
        System.out.println("-----Elija una opcion-----");
        System.out.println("1) Gestion de productos");
        System.out.println("2) Control de existencias");
        System.out.println("3) Pedidos de compra");
        System.out.println("4) Informes y estadisticas");
        System.out.println("5) Categorias");
        System.out.println("6) Salir ");
        int opcion = scan.nextInt();
        
        switch (opcion) {
            case 1: {
                //ejecuta la clase gestion de productos el cual tiene el metodo de mostrarMenu
                GestionProductos.mostrarMenu();
                }
            
            case 2: {
                // en este caso creamos un nuevo obejto llamado existencias pues nuestra clase esta en otro folder y motrara el metodo mostrarMenu
                ControlExistencias existencias = new ControlExistencias();
                existencias.mostrarMenu();
            }
            case 3: {
                
            }
            case 4: {
                
            }
            
            case 5:
                //se crea un objeto categorias, para llamar a la clase Categorias y mostrar su menu
                Categorias categorias = new Categorias();
                categorias.mostrarMenu();
                
            case 6: 
                System.out.println("Saliendo del sistema");
                    break;
                    
                default:
                    // si no elije una opcion valida, termina aca
                    System.out.println("Opción no válida.");
            }
        }
    }

