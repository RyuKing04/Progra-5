/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Mantenimiento;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Antonio Ramirez
 */
public class beanMantenimiento {

    String ced;
    String nombre;
    int edad;
    String estadoCivil;
    double salario;
    String provincia;
    String mensaje;

    LinkedList<SelectItem> listEstadoCivil = new LinkedList();
    LinkedList<SelectItem> listProvincia = new LinkedList();

    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public LinkedList<SelectItem> getListEstadoCivil() {
        Mantenimiento objMantenimiento = new Mantenimiento();
        return objMantenimiento.leerEstadoCivil();
    }

    public void setListEstadoCivil(LinkedList<SelectItem> listEstadoCivil) {
        this.listEstadoCivil = listEstadoCivil;
    }

    public LinkedList<SelectItem> getListProvincia() {
        Mantenimiento objMantenimiento = new Mantenimiento();
        return objMantenimiento.leerProvincia();
    }

    public void setListProvincia(LinkedList<SelectItem> listProvincia) {
        this.listProvincia = listProvincia;
    }

    public void imprimir() {
        double salarioNeto, ccss, asociacion = 0;
        String mayor;
        String error = "";
        if (ced.equals("")) {
            error = "Cedula invalida";
        }
        if (nombre.equals("")) {
            error = "Nombre invalido";
        }
        if (edad <= 0) {
            error = "Edad invalida";
        }
        if (edad > 18) {
            mayor = "Mayor de edad";
        } else {
            mayor = "menor de edad";
        }
        if (salario <= 0) {
            error = "Salario invalido";
        }
        ccss = (salario * 0.09);
        asociacion = (salario * 0.05);
        salarioNeto = salario - ccss - asociacion;

        if (!error.equals("")) {
            mensaje = error;
        } else {
            mensaje = "Cedula" + ced + "\n Nombre" + nombre + "\n Edad" + edad + "\n" + mayor
                    + "\n Estado Civil" + estadoCivil + "\n Salario" + salario
                    + "\n CCSS" + ccss + "\n Asociación" + asociacion
                    + "\n Salario Neto" + salarioNeto + "\n Provincia" + provincia;

        }
    }
   public void clear() {
        this.ced = ("");
        this.nombre = ("");
        this.edad = (0);
        this.salario = (0);
        this.mensaje = ("");

    }
}
