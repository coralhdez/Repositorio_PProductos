package com.ceep.tienda.datos;

import com.ceep.tienda.excepciones.*;
import com.ceep.tienda.dominio.Producto;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existeRecurso(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        return archivo.exists(); //devuelve boolean
    }

    @Override
    public void crearRecurso(String nombreArchivo) throws ExcepcionesAccesoDatos {
        File archivo = new File(nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo)); //se crea el archivo
            salida.close(); // se cierra el archivo
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new ExcepcionesAccesoDatos("Error intentando crear el archivo");
        }
    }

    @Override
    public List<Producto> listarRecurso(String nombreArchivo) throws ExcepcionesLectura {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); //para los tipo date
        //LEO TODAS LAS LINEAS DE MI FICHERO
        //EN CADA LINEA CREO UN OBJETO DE TIPO PRODUCTO Y LO METO EN UN LISTADO
        //Archivo
        File archivo = new File(nombreArchivo);
        Producto productoN = null; //objetos de producto
        String[] producto = new String[5]; //idProducto, nombre, precio, cantidad, fecha
        //Creo un arraylist con los productos
        List<Producto> productos = new ArrayList<>(); //almacena productos

        try {
            //Declaro variable para entrar al archivo
            BufferedReader entrada = new BufferedReader(new FileReader(archivo)); //Para que no se sobreescriba
            String lectura = null; // lectura = nombre;cantidad;precio;fecha

            while ((lectura = entrada.readLine()) != null) {
                producto = lectura.split(";"); // producto = {nombre, cantidad, precio, fecha}  lectura = nombre;cantidad;precio;fecha      
                //me tengo que crear en la clase Producto otro constructor con todos los atributos porque necesito visualizar el id
                productoN = new Producto(Integer.parseInt(producto[0]), producto[1], Double.parseDouble(producto[2]), Integer.parseInt(producto[3]), formatoFecha.parse(producto[4]));
                productos.add(productoN);
            }
            entrada.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new ExcepcionesLectura("Error de lectura listando los productos");
        }
        return productos;
    }

    @Override //AÑADIR PRODUCTOS AL FICHERO
    public void escribirProducto(Producto producto, String nombreArchivo, boolean anexar) throws ExcepcionesEscritura {
        File archivo = new File(nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo, true)); //añade una nueva linea con la info del objeto
            String productoTxt = producto.getIdProducto()
                    + "; " + producto.getNombreProducto()
                    + "; " + producto.getCantidad()
                    + "; " + producto.getPrecio()
                    + "; " + producto.getFechaCaducidad();

            salida.println(productoTxt);
            salida.close();
            /* salida.println(producto.getIdProducto() 
                    + ";" + producto.getNombreProducto()
                    + ";" + producto.getPrecio()
                    + ";" + producto.getCantidad()
                    + ";" + producto.getFechaCaducidad()
            );*/
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new ExcepcionesEscritura("Error de escritura al introducir el Producto");
        }

    }

    //hacer variante: buscar por todos los campos, no solo por nombre
    // otro: búsqueda especificando el campo por el que se quiere buscar
    // busqueda que contenga el término, búsqueda exacta que contenga el termino (contains)
    @Override
    public int buscarProductoSimple(String buscar, String nombreArchivo) throws ExcepcionesLectura {
        File archivo = new File(nombreArchivo);
        //String mensaje = "";
        int contador = 1;
        String[] productoTxt = new String[5];

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = null; // String  lectura = entrada.readLine();
            lectura = entrada.readLine();
            while (lectura != null) {
                productoTxt = lectura.split(";"); //me devuelve un array string

                if (buscar.equalsIgnoreCase(productoTxt[1])) { //el nombre
                    //mensaje = "El Producto " + buscar + " se encuentra en la línea " + contador + " del catálogo de productos";
                    break;
                }
                contador++;
            }
            lectura = entrada.readLine();
            /*if (lectura == null) {
                mensaje = "El producto " + buscar + " no se encuentra en el catálogo";
            }*/
            entrada.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new ExcepcionesLectura("Error en el listado de productos");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new ExcepcionesLectura("Error en el listado de productos0");
        }
        return contador; // mensaje

    }

    @Override
    public String borrarRecurso(String nombreArchivo) {
        String mensaje = "";
        File archivo = new File(nombreArchivo); //objeto de tipo archivo par trabajar con el archivo creado previamente
        if (existeRecurso(nombreArchivo)) {
            archivo.delete();
            mensaje = "Archivo borrado con éxito";

        } else {
            mensaje = "No se ha borrado porque no hay Archivo";
        }
        return mensaje;
    }

    @Override
    public void borrarProducto(String nombreArchivo, String nombreArticuloABorrar) throws ExcepcionesAccesoDatos {
        //creo otro objeto de archivo copia porque todos los productos que no voy a borrar los llevo alli
        //productos cuyo nombre sea distitno al nombre del producto que quiero borrar.
        File archivOriginal = new File(nombreArchivo);
        File archivoCopia = new File(nombreArchivo);
        String[] producto = new String[5];

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivOriginal)); //Para que no se sobreescriba
            PrintWriter salida = new PrintWriter(new PrintWriter(archivoCopia));
            String lectura = null;
            while ((lectura = entrada.readLine()) != null) {
                producto = lectura.split(";");
                if (producto[1] != nombreArchivo) { //nombreProducto
                    salida.println(producto);
                }
            }
            entrada.close();
            salida.close();

            //BORRAR EL ARCHIVO ORIGINAL Y EL QUE HEMOS CREADO DE COPIA QUE ES DONDE ESTAN LOS PRODUCTOS NO BORRADOS, LO RENOMBRAMOS 
            //Y LO CONVERTIMOS AL FICHERO ORIGINAL. SE RENUEVA CON EL NOMBRE DEL CATALOGO        
            if (existeRecurso(nombreArchivo)) {
                borrarRecurso(nombreArchivo);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }
//mismos metodos de la interfaz
    // define el cuerpo de todos los métodos abstractos
    //instanciar objeto de tipo file para poder trabajar ocn el

//    @Override
//    public double CalcularPrecioTotal(String nombreArchivo) {    
//        //LEO TODAS LAS LINEAS DE MI FICHERO
//        //EN CADA LINEA CREO UN OBJETO DE TIPO PRODUCTO Y LO METO EN UN LISTADO
//        //Archivo
//        File archivo = new File(nombreArchivo); 
//        double total = 0.0;
//        String[] producto = new String[5]; //idProducto, nombre, precio, cantidad, fecha
//
//        try {
//            //Declaro variable para entrar al archivo
//            BufferedReader entrada = new BufferedReader(new FileReader(archivo)); //Para que no se sobreescriba
//            String lectura = null; // lectura = nombre;cantidad;precio;fecha
//
//            while ((lectura = entrada.readLine()) != null) {
//                producto = lectura.split(";"); // producto = {nombre, cantidad, precio, fecha}  lectura = nombre;cantidad;precio;fecha      
//                //en cada split que hago cojo el precio
//                //como el split me devuelve String, lo parseo a double
//                total += Double.parseDouble(producto[3]);
//            }
//            entrada.close();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//            try {
//                throw new ExcepcionesLectura("Error de lectura listando los productos");
//            } catch (ExcepcionesLectura ex) {
//            }
//        }
//        return total;
//    }  
//    @Override
//    public int contadorArticulos(String nombreArchivo) throws ExcepcionesLectura {
//        //LEO TODAS LAS LINEAS DE MI FICHERO
//        //EN CADA LINEA CREO UN OBJETO DE TIPO PRODUCTO Y LO METO EN UN LISTADO
//        //Archivo
//        File archivo = new File(nombreArchivo); 
//        int contador = 0;
//        String[] producto = new String[5]; //idProducto, nombre, precio, cantidad, fecha
//
//        try {
//            //Declaro variable para entrar al archivo
//            BufferedReader entrada = new BufferedReader(new FileReader(archivo)); //Para que no se sobreescriba
//            String lectura = null; // lectura = nombre;cantidad;precio;fecha
//
//            while ((lectura = entrada.readLine()) != null) {
//                producto = lectura.split(";"); // producto = {nombre, cantidad, precio, fecha}  lectura = nombre;cantidad;precio;fecha      
//
//                contador ++;
//            }
//            entrada.close();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//            try {
//                throw new ExcepcionesLectura("Error de lectura de los productos");
//            } catch (ExcepcionesLectura ex) {
//            }
//        }
//        return contador;
//    }
//
//    @Override
//    public double maxPrecioArticulo(String nombreArchivo) throws ExcepcionesLectura {         
//        //LEO TODAS LAS LINEAS DE MI FICHERO
//        //EN CADA LINEA CREO UN OBJETO DE TIPO PRODUCTO Y LO METO EN UN LISTADO
//        //Archivo
//        File archivo = new File(nombreArchivo); 
//        double max = 0;
//        String[] producto = new String[5]; //idProducto, nombre, precio, cantidad, fecha
//
//        try {
//            //Declaro variable para entrar al archivo
//            BufferedReader entrada = new BufferedReader(new FileReader(archivo)); //Para que no se sobreescriba
//            String lectura = null; // lectura = nombre;cantidad;precio;fecha
//
//            while ((lectura = entrada.readLine()) != null) {
//                producto = lectura.split(";"); // producto = {nombre, cantidad, precio, fecha}  lectura = nombre;cantidad;precio;fecha      
//                if(Double.parseDouble(producto[3]) > max) {
//                    max = Double.parseDouble(producto[3]);
//                }              
//            }
//            entrada.close();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//            try {
//                throw new ExcepcionesLectura("Error de lectura de los productos");
//            } catch (ExcepcionesLectura ex) {
//            }
//        }
//        return max;
//    }
//    
}
