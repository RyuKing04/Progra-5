/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.DatosCompra;
import Model.Detalle;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ramir
 */
public class DatosCompraDAO {
        private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public DatosCompraDAO(){
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    public DatosCompra traerDatos(int idOrden) throws SNMPExceptions{
         DatosCompra datosCompra = new DatosCompra();
         ArrayList<Detalle> listaDetalle = new ArrayList<>();
        String Query = "";
        ArrayList<DatosCompra> listaDatosCompra = new ArrayList();
         try {
            //Se obtienen los valores del objeto

            Query = "Select ORDEN_COMPRA.ORDEN_ID,ORDEN_COMPRA.TOTAL,ORDEN_COMPRA.CLIENTE_ID,DETALLE.PRODUCTO_ID,\n" +
"DETALLE.CANTIDAD,DETALLE.TOTAL[TotalDetalle], PRODUCTO.DESCRIPCION,CLIENTE.NOMBRE\n" +
"from ORDEN_COMPRA inner join DETALLE on ORDEN_COMPRA.ORDEN_ID = DETALLE.ORDEN_ID inner join\n" +
"PRODUCTO on PRODUCTO.PRODUCTO_ID = DETALLE.PRODUCTO_ID inner join CLIENTE ON CLIENTE.CLIENTE_ID =\n" +
"ORDEN_COMPRA.CLIENTE_ID WHERE ORDEN_COMPRA.ORDEN_ID = " + idOrden;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {
                datosCompra.setIdOrden(rsPA.getInt("ORDEN_ID"));
                datosCompra.setNombreCliente(rsPA.getString("NOMBRE"));
                datosCompra.setMontoOrden(rsPA.getDouble("TOTAL")); 
                Detalle detalle = new Detalle();
                detalle.setIdProd(rsPA.getInt("PRODUCTO_ID"));
                detalle.setNombreProd(rsPA.getString("DESCRIPCION"));
                detalle.setTotal(rsPA.getDouble("TotalDetalle"));
                detalle.setCant(rsPA.getInt("CANTIDAD"));
                listaDetalle.add(detalle);

            }
            datosCompra.setListaDetalle(listaDetalle);
          

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
         borradoLogico(idOrden);
        return datosCompra;
    }
    
    
    
    public void borradoLogico(int idOrden) throws SNMPExceptions{

        String Query = "";
        ArrayList<DatosCompra> listaDatosCompra = new ArrayList();
         try {
            //Se obtienen los valores del objeto

            Query = "update ORDEN_COMPRA SET BORRADO = 1 where ORDEN_ID = " + idOrden;

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(Query);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }

    }
    
      public String validar(int idOrden) throws SNMPExceptions{
         String mensaje = "";
        String Query = "";
        ArrayList<DatosCompra> listaDatosCompra = new ArrayList();
         try {
            //Se obtienen los valores del objeto

            Query = "select * from  ORDEN_COMPRA where ORDEN_ID = " + idOrden;

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(Query);

            while (rsPA.next()) {
               int borradoLogico = rsPA.getInt("BORRADO");
               if(borradoLogico == 1){
                   mensaje = "Orden borrada";
               }else{
                   if(borradoLogico == 0){
                    mensaje = "Existe";
                   }
               }
               

            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }

        return mensaje;
    }
    
}
