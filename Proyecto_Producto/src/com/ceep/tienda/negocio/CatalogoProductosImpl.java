package com.ceep.tienda.negocio;

import com.ceep.tienda.datos.AccesoDatosImpl;
import com.ceep.tienda.datos.IAccesoDatos;
import com.ceep.tienda.excepciones.ExcepcionesAccesoDatos;
import com.ceep.tienda.excepciones.ExcepcionesEscritura;
import com.ceep.tienda.excepciones.ExcepcionesLectura;
import com.ceep.tienda.dominio.Producto;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Tienda;

public class CatalogoProductosImpl implements ICatalogoProductos {

    //Objeto para acceder a las operaciones de la clase objeto a datos
    private final IAccesoDatos datos; //para usar los métodos de la interfdaz IAccesoDatos

    public CatalogoProductosImpl() {
        this.datos = new AccesoDatosImpl();
    }

    //Producto miObjeto = new Producto();
    @Override
    public void agregarProducto(String NombreCatalogo, Producto producto) {
        if (this.datos.existeRecurso(NombreCatalogo)) {
            try {
                this.datos.escribirProducto(producto, NombreCatalogo, true);
            } catch (ExcepcionesEscritura ex) {
                System.out.println("Error al escribir en el catálogo");
                ex.printStackTrace(System.out);
            }
        } else {
            System.out.println("Catálogo no inciializado");
        }
    }

    @Override
    public String listarProducto(String NombreCatalogo) {
        List<Producto> arrayProductos = new ArrayList<>();

        try {
            arrayProductos = this.datos.listarRecurso(NombreCatalogo);
//            arrayProductos.forEach(producto -> {
//                System.out.println("Producto: " + producto.getNombreProducto()
//                + producto.getPrecio()
//                + producto.getFechaCaducidad()
//                + producto.getFechaCaducidad());
//            });
            for (int i = 0; i < arrayProductos.size(); i++) {
                System.out.println("Producto: " + arrayProductos.get(i).getNombreProducto()
                        + arrayProductos.get(i).getPrecio()
                        + arrayProductos.get(i).getCantidad()
                        + arrayProductos.get(i).getFechaCaducidad());
            }
        } catch (ExcepcionesLectura ex) {
            System.out.println("Error leyendo el catálogo");
            ex.printStackTrace(System.out);
        }

        return "";
    }

    @Override
    public String iniciarCatalogo(String nombreCatalogo) {
        if (this.datos.existeRecurso(nombreCatalogo)) {
            this.datos.borrarRecurso(nombreCatalogo);
        } else {
            try {
                this.datos.crearRecurso(nombreCatalogo);
            } catch (ExcepcionesAccesoDatos ex) {
                ex.printStackTrace(System.out);
                System.out.println("Excepción intentando inicializar el catálogo");
            }
        }
        return "Catálogo de productos inicializado";
    }

    @Override
    public String buscarProducto(String nombreCatalogo, String buscar) {
        try {
            System.out.println(this.datos.buscarProductoSimple(buscar, nombreCatalogo));
        } catch (ExcepcionesLectura ex) {
            System.out.println("Error al buscar productos en el catálogo");
            ex.printStackTrace(System.out);
        }
        return "";

    }

    @Override
    public double CalcularPrecioTotal(String nombreArchivo) {
        List<Producto> productos = new ArrayList<>(); //porque el metodo listar devuelve un array
        double total = 0.0;

        try {
            //accedo al metodo listar de la capa interfaz acceso a datos
            productos = this.datos.listarRecurso(nombreArchivo); //genera una excepcion porque en el metodo listar hay un throws, se propaga la excepcion
            //Accedemos a los objetos que se han creado en Acceso datos
            for (int i = 0; i < productos.size(); i++) {
                total += productos.get(i).getPrecio();
            }

        } catch (ExcepcionesLectura ex) {
            ex.printStackTrace(System.out);
            System.out.println("Excepción listando el Recurso");
        }

        return total;

    }

    @Override
    public int contadorArticulos(String nombreArchivo) {
        List<Producto> productos = new ArrayList<>(); //porque el metodo listar devuelve un array
        //int contadorArticulos = 0;

        try {
            //accedo al metodo listar de la capa interfaz acceso a datos
            productos = this.datos.listarRecurso(nombreArchivo); //genera una excepcion porque en el metodo listar hay un throws, se propaga la excepcion
            //Accedemos a los objetos que se han creado en Acceso datos
            /*  for (int i = 0; i < productos.size(); i++) {
                contadorArticulos ++;
            }
             */
        } catch (ExcepcionesLectura ex) {
            ex.printStackTrace(System.out);
            System.out.println("Excepción listando el Recurso");
        }

        return productos.size(); //porque me devuelve la dimension del arraylist
    }

    @Override
    public double maxPrecioArticulo(String nombreArchivo) {
        List<Producto> productos = new ArrayList<>();
        double max = 0;

        try {
            productos = this.datos.listarRecurso(nombreArchivo);
            for (int i = 0; i < productos.size(); i++) {
                if (max < productos.get(i).getPrecio()) {
                    max = productos.get(i).getPrecio();
                }
            }
        } catch (ExcepcionesLectura ex) {
            ex.printStackTrace(System.out);
            System.out.println("Excepción listando el Recurso");
        }

        return max;
    }

    @Override
    public String borrarCatalogo(String nombreRecurso) {
        this.datos.borrarRecurso(nombreRecurso);
        return "Catálogo borrado";
    }

    @Override
    public void borrarProducto(String nombreRecurso) {
        try {
            this.datos.borrarProducto(nombreRecurso, nombreRecurso);
        } catch (ExcepcionesAccesoDatos ex) {
            ex.printStackTrace(System.out);
            System.out.println("Excepción a la hora de borrar un Producto");
        }

        System.out.println("Artículo borrado con éxito");
    }

}
