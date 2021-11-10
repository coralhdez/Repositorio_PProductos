
package com.ceep.tienda.dominio;

import java.util.Date;


public class Producto {
    private int idProducto; //0
    String nombreProducto; //1
    int cantidad; //2
    double precio; //3
    Date fechaCaducidad; //4
    
    private static int contadorProductos = 0;

    public Producto() {
        this.idProducto = Producto.contadorProductos++; //cuando creo el producto con el constructor vacio los va contando.
    }
    //no necesito el setter del id porque el constructor ya se va encargando de asignarle su valor

    public Producto(String nombreProducto, double precio, int cantidad, Date fechaCaducidad) {
        this();
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Producto(int idProducto, String nombreProducto, double precio, int cantidad, Date fechaCaducidad) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
    }
    

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public static int getContadorProductos() {
        return contadorProductos;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", precio=" + precio + ", cantidad=" + cantidad + ", fechaCaducidad=" + fechaCaducidad + '}';
    }
 

    
    
    
    
}
