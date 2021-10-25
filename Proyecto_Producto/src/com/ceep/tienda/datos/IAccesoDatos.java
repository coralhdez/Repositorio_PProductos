
package com.ceep.tienda.datos;

import com.ceep.tienda.objetos.Producto;
import java.util.*;



public interface IAccesoDatos {
    /*  
        agregar
        buscar
        listar
        borrar
    */
    
    
    void borrar(String nombreArchivo);
    void agregar(String nombreArchivo);
    String buscar(Producto producto, String nombreProducto) throws AccesoDatosExcepciones;
    List<Producto> listar(String nombreArchivo);
    
    
}
