
package com.ceep.tienda.negocio;

import com.ceep.tienda.dominio.Producto;


public interface ICatalogoProductos {
    //Nombre recurso: nombreCatalogo
    //Pre-Condición: No existe el fichero
    //Post-Condición: Crear un nuevo recurso.
    double CalcularPrecioTotal(String nombreRecurso);

    int contadorArticulos(String nombreRecurso);

    double maxPrecioArticulo(String nombreRecurso);
    
    void agregarProducto(String nombreRecurso, Producto producto);
    
    String iniciarCatalogo(String nombreRecurso);
    
    String listarProducto(String nombreRecurso);   
    
    String mostrarPorductoID(String nombreRecurso, int id); 
    //buscar un producto en el catálogo
    String buscarProducto(String nombreRecurso, String buscar);
        
    String borrarCatalogo(String nombreRecurso);
    
    void borrarProducto(String nombreRecurso);
    
   
    
    
}
