package test;

import com.ceep.tienda.negocio.*;
import com.ceep.tienda.dominio.Producto;
import java.util.Date;
import java.util.Scanner;

public class Tienda {

    public static void main(String[] args) {

        var nombreTienda = "Tienda.txt";
        ICatalogoProductos catalogo = new CatalogoProductosImpl();
        Scanner dato = new Scanner(System.in);
        String nombre = "", fecha = "";
        double precio = 0;
        int cantidad = 0;
        
        
        

        while (true) {
            System.out.println("\t*** TIENDA ***");
            System.out.println(" 1.- Iniciar Catálogo de tienda");
            System.out.println(" 2.- Agregar productos");
            System.out.println(" 3.- Listar tienda");
            System.out.println(" 4.- Buscar tienda");
            System.out.println(" 5.- Borrar producto");
            System.out.println(" 6.- Calcular total precio");
            System.out.println(" 7.- Mayor número de productos");
            System.out.println(" 0.- Salir");
            System.out.print("Indica la opción deseada: ");
            Scanner sn = new Scanner(System.in);
            int opcion = sn.nextInt();

            switch (opcion) {
                case 1:
                    catalogo.iniciarCatalogo(nombreTienda);
                    System.out.println("Catálogo iniciado");
                    break;
                case 2:
                    System.out.println("Introduce el nombre del Producto: ");                 
                    nombre = dato.nextLine();
                    System.out.print("Introduce el precio del Producto: ");
                    precio = dato.nextDouble();
                    System.out.print("Introduce la cantidad de productos: ");
                    cantidad = dato.nextInt();
                    System.out.print("Introduce la fecha de caducidad del producto: ");
                    fecha = dato.next();                    
                    Producto p = new Producto(nombre, precio, cantidad, fecha);
                    catalogo.agregarProducto(nombreTienda, p);

                    break;
                case 3:
                    catalogo.listarProducto(nombreTienda);
                    break;
                case 4:
                    System.out.println("Introduce el nombre del producto a buscar");
                    Scanner busqueda = new Scanner(System.in);
                    String buscar = busqueda.nextLine();
                    catalogo.buscarProducto(nombreTienda, buscar);
                    break;
                case 0:
                    System.out.println("Gracias!, hasta la próxima");
                    break;
                default:
                    System.out.println("Opción desconocida.");

            }
        }

    }

}
