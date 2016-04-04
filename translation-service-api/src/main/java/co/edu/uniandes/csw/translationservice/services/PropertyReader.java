/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.translationservice.services;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jhony
 */
public class PropertyReader {
    
    private static Properties prop;
    
    private PropertyReader(){
    }
    
    public static void initializePropertyReader(){

        InputStream is = null;
            prop = new Properties();

            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties");
            if(is == null) {
                Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, "No se puedo cargar el archivo mail.properties");
            }
            else {
            try {
                prop.load(is);
            } catch (IOException ex) {
                Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }

    public static Set<Object> getAllKeys(){
        Set<Object> keys;
            keys= prop.keySet();
        return keys;
    }

    public static String getPropertyValue(String key){
        return prop.getProperty(key);
    }

    public static Properties getProperties() {
        return prop;
    }

}
