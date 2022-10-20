/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Antonio Ramirez
 */
public class Mantenimiento {
    

    public LinkedList<SelectItem> leerEstadoCivil() {
         LinkedList listEstadoCivil = new LinkedList();
        listEstadoCivil.add(new SelectItem("Soltero"));
        listEstadoCivil.add(new SelectItem("Casado"));
        listEstadoCivil.add(new SelectItem("Divorciado"));
        listEstadoCivil.add(new SelectItem("Viudo"));
        return listEstadoCivil;
    }
      public LinkedList<SelectItem> leerProvincia() {
         LinkedList listProvincia = new LinkedList();
        listProvincia.add(new SelectItem("Limón"));
        listProvincia.add(new SelectItem("San José"));
        listProvincia.add(new SelectItem("Alajuela"));
        listProvincia.add(new SelectItem("Heredia"));
        listProvincia.add(new SelectItem("Cartago"));
        listProvincia.add(new SelectItem("Guanacaste"));
        listProvincia.add(new SelectItem("Puntarenas"));

        return listProvincia;
    }
}
