/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ingreso;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Antonio Ramirez
 */
public class beanIngreso {
    String Usuario;
    String Contrasenna;
    String Mensaje; 
    String Rol;
     LinkedList<SelectItem> listaRol= new LinkedList();

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasenna() {
        return Contrasenna;
    }

    public void setContrasenna(String Contrasenna) {
        this.Contrasenna = Contrasenna;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }
public void ingresar(){
        ingreso objIngresoModel = new ingreso();
        this.Mensaje = objIngresoModel.validacion(Usuario, Contrasenna, Rol);
    }
    public LinkedList<SelectItem> getListaRol() {
        ingreso Listaingreso= new ingreso();
        
        return Listaingreso.listaRol();
    }

    public void setListaRol(LinkedList<SelectItem> listaRol) {
        this.listaRol = listaRol;
    }
     
}
