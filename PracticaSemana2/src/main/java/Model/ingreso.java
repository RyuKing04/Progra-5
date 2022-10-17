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
public class ingreso {
    public LinkedList<SelectItem> listaRol(){
        LinkedList ListaRoles= new LinkedList();
        ListaRoles.add(new SelectItem("Administrador"));
        ListaRoles.add(new SelectItem("Usuario"));
        ListaRoles.add(new SelectItem("Socio"));
        return ListaRoles;
    }
    public String validacion(String Usuario, String Contrasenna, String Rol){
        String usuario = "UTNUsuario", passUser = "U";
        String admin = "UTNAdministrador", passAdmin = "A";
        String socio = "UTNSocio", passSocio = "S";

        //Usuario
         if (Rol.equals("Usuario")) {
             if ((Usuario.equals(usuario)) && (Contrasenna.equals(passUser))) {
                 return "Datos correctos";
             }
         } 

         //Admin
         if (Rol.equals("Administrador")) {
             if ((Usuario.equals(admin)) && (Contrasenna.equals(passAdmin))) {
                 return "Datos correctos";
             }
         } 

         //Socio
         if (Rol.equals("Socio")) {
             if ((Usuario.equals(socio)) && (Contrasenna.equals(passSocio))) {
                 return "Datos correctos";
             }
         } 

         return "Datos incorrectos";
    }
    }

