/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internalizacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Juanca Poner la etiqueta del idioma al lado de la bandera
 */
public class Internalizacion {
    
    private static int x, y, z;
    private static ArrayList<String> idiomas;
    private static ArrayList<Fruta> inventario;
    private static ArrayList<String> imagenes;
    private static String datos[][];
    
    public static void leerPalabras(String fich) throws FicheroNoEncontrado {
        if (!(new File(fich)).exists()) {
            throw new FicheroNoEncontrado("Fichero no encontrado \n");
        }
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        try {
            archivo = new File(fich);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String texto;
            texto = br.readLine();
            x = Integer.parseInt(texto);
            
            for(int i = 0; i < x * 2; ++i){
                texto = br.readLine();
                idiomas.add(texto);
            }
            
            texto = br.readLine();
            y = Integer.parseInt(texto);

            datos = new String[x][y];

            for (int i = 0; i < x; ++i) {
                for (int j = 0; j < y; ++j) {
                    texto = br.readLine();
                    datos[i][j] = texto;
                }
                z = Integer.parseInt(br.readLine());
            }
            int contador = 0;
            for (int i = 0; i < z * x; ++i) {
                if (contador == z) {
                    br.readLine();
                    contador = 0;
                }
                texto = br.readLine();
                imagenes.add(texto);
                ++contador;
            }
            
        } catch (IOException | NumberFormatException e) {
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
            }
        }
    }
    
    public static void cargarInventario(String fich) throws FicheroNoEncontrado {
        if (!(new File(fich)).exists()) {
            throw new FicheroNoEncontrado("Fichero no encontrado \n");
        }
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        try {
            archivo = new File(fich);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            boolean carga = true;
            String dato;
            dato = br.readLine();
            while(dato != null) {
                int id = Integer.parseInt(dato);
                String nombre = br.readLine();
                int cantidad = Integer.parseInt(br.readLine());
                float precio = Float.parseFloat(br.readLine());
                Fruta fruta = new Fruta(id, nombre, cantidad, precio);
                inventario.add(fruta);  
                dato = br.readLine();
            } 
            
        } catch (IOException | NumberFormatException e) {
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
            }
        }
    }

    /**
     * @param args the command line arguments
     * @throws internalizacion.FicheroNoEncontrado
     */
    public static void main(String[] args) throws FicheroNoEncontrado {
        // TODO code application logic here
        idiomas = new ArrayList<>();
        imagenes = new ArrayList<>();
        inventario = new ArrayList<>();
        leerPalabras("idiomas.txt");
        cargarInventario("inventario.txt");
        Ventana ventana = new Ventana(datos, inventario, idiomas, imagenes, 0);
        ventana.setVisible(true);
        
    }
    
}
