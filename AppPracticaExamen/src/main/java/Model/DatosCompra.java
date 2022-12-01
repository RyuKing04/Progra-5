/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author ramir
 */
public class DatosCompra {
    private int idOrden;
    private String nombreCliente;
    private double montoOrden;
    ArrayList<Detalle> listaDetalle = new ArrayList<>();

    public ArrayList<Detalle> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(ArrayList<Detalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }


    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getMontoOrden() {
        return montoOrden;
    }

    public void setMontoOrden(double montoOrden) {
        this.montoOrden = montoOrden;
    }


    

}
