package com.ceep.tienda.datos;

import com.ceep.tienda.excepciones.ExcepcionesAccesoDatos;
import com.ceep.tienda.excepciones.ExcepcionesEscritura;
import com.ceep.tienda.excepciones.ExcepcionesLectura;
import com.ceep.tienda.dominio.Producto;
import java.util.*;

public interface IAccesoDatos {

    /* 
    Métodos CRUD
     La interfaz es como un esqueleto que usamos para conectar la capa de datos con la capa superior (capa de negocio)
     porque la capa de negocio no puede acceder al fichero de datos directamente, entonces annece a esta capa.
     */
    //no se encarga e las excepciones se pone el throws por si hay expeciones que la gestione la clase que la implementa
    boolean existeRecurso(String nombreArchivo); //nombre de mi fuente de datos (si mi bbdd existe o no). Recurso = bbdd/fichero

    void crearRecurso(String nombreArchivo) throws ExcepcionesAccesoDatos; //nombreArchivo = nombreFichero

    List<Producto> listarRecurso(String nombreArchivo) throws ExcepcionesLectura; //método que me lee el archivo

    void escribirProducto(Producto producto, String nombreArchivo, boolean anexar) throws ExcepcionesEscritura; //añadir productos

    int buscarProductoSimple(String buscar, String nombreArchivo) throws ExcepcionesLectura;

    void borrarProducto(String nombreArchivo, String nombreArticuloABorrar) throws ExcepcionesAccesoDatos;

    String borrarRecurso(String nomreArchivo);

    //double CalcularPrecioTotal(String nombreArchivo) throws ExcepcionesLectura;

    //int contadorArticulos(String nombreArchivo)throws ExcepcionesLectura;

    //double maxPrecioArticulo(String nombreArchivo)throws ExcepcionesLectura;

}
