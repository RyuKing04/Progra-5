/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DatosCompraDAO;
import DAO.SNMPExceptions;
import Model.DatosCompra;
import Model.Detalle;
import java.util.ArrayList;

/**
 *
 * @author ramir
 */
public class BeanDatosCompra {
    
    private int idOrden;
    private String nombreCliente;
    private double montoOrden;
    ArrayList<Detalle> listaDetalle = new ArrayList<>();
    private String mensaje;
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
    
    public ArrayList<Detalle> getListaDetalle() {
        return listaDetalle;
    }
    
    public void setListaDetalle(ArrayList<Detalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }
    
    public void traerDatos(int idOrden) throws SNMPExceptions {
        setMensaje("");
        DatosCompraDAO datosCompraDAO = new DatosCompraDAO();
        String resultValidacion = datosCompraDAO.validar(idOrden);
        if (resultValidacion.equals("Existe")) {
            DatosCompra dc = datosCompraDAO.traerDatos(idOrden);
            setListaDetalle(dc.getListaDetalle());
            setIdOrden(dc.getIdOrden());
            setMontoOrden(dc.getMontoOrden());
            setNombreCliente(dc.getNombreCliente());
            setMensaje("Borrado Correctamente");
            
        } else {
            if (resultValidacion.equals("Orden borrada")) {
                setMensaje(resultValidacion);
            } else {
                setMensaje("No existe");
            }            
        }
    }
    
}
