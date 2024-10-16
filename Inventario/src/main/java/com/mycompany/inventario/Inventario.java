/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inventario;

import java.util.Scanner;

//Paquetes del menu
//import gestion.GestionProductos;
import existencias.ControlExistencias;
import compras.PedidosCompra;
import informes.Informes;
/**
 *
 * @author abrah
 */
public class Inventario {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Bienvenido al inventario");
        
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
                GestionProductos.mostrarMenu();
                }
            
            case 2: {
                ControlExistencias existencias = new ControlExistencias();
                existencias.mostrarMenu();
            }
            case 3: {
                PedidosCompra pedidos = new PedidosCompra();
                pedidos.mostrarMenu();
            }
            case 4: {
                Informes informes = new Informes();
                informes.mostrarMenu();
            }
            
            case 5:
                Categorias categorias = new Categorias();
                categorias.mostrarMenu();
                
            case 6: 
                System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

