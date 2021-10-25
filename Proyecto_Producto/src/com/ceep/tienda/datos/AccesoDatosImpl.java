package com.ceep.tienda.datos;

import com.ceep.tienda.objetos.Producto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccesoDatosImpl implements IAccesoDatos {


    @Override
    public void borrar(String nombreArchivo) {

    }

    @Override
    public String buscar(Producto producto, String nombreProducto) {

        return "";
    }

    @Override
    public List<Producto> listar(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        Producto producto = null;
        List<Producto> arrayProductos = new ArrayList<>();

        try {
            var entrada = BufferedReader(new FileReader(archivo));
            var lectura = entrada.redLine();
            
            
        } catch (FileNotFoundException ex) {

        }

    }

}
