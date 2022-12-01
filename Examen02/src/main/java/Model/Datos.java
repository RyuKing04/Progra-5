/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Estudiante
 */
public class Datos {
    private int idOrden;
    private String Cliente;
    private double montoOrden;
    ArrayList<Detalle> listDetalle = new ArrayList<>();

    public ArrayList<Detalle> getListDetalle() {
        return listDetalle;
    }

    public void setListDetalle(ArrayList<Detalle> listaDetalle) {
        this.listDetalle = listaDetalle;
    }


    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public double getMontoOrden() {
        return montoOrden;
    }

    public void setMontoOrden(double montoOrden) {
        this.montoOrden = montoOrden;
    }

}
