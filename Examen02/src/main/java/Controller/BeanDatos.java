/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DatosDAO;
import DAO.SNMPExceptions;
import Model.Datos;
import Model.Detalle;
import java.util.ArrayList;
/**
 *
 * @author Estudiante
 */
public class BeanDatos {
       private int idOrden;
    private String Cliente;
    private double montoOrden;
    ArrayList<Detalle> listDetalle = new ArrayList<>();
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

    public ArrayList<Detalle> getListDetalle() {
        return listDetalle;
    }

    public void setListDetalle(ArrayList<Detalle> listDetalle) {
        this.listDetalle = listDetalle;
    }
    
   
    
    public void traerDatos(int idOrden) throws SNMPExceptions {
        setMensaje("");
        DatosDAO datosDAO = new DatosDAO();
        String resultValidacion = datosDAO.validar(idOrden);
        if (resultValidacion.equals("Existe")) {
            Datos dc = datosDAO.traerDatos(idOrden);
            setListDetalle(dc.getListDetalle());
            setIdOrden(dc.getIdOrden());
            setMontoOrden(dc.getMontoOrden());
            setCliente(dc.getCliente());
            setMensaje("Borrado Exitoso");
            
        } else {
            if (resultValidacion.equals("Orden Eliminada")) {
                setMensaje(resultValidacion);
            } else {
                setMensaje("No esta en la Base de datos");
            }            
        }
    }
}
