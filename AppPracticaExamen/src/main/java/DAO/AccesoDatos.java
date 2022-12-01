package DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.naming.NamingException;

/*
 * @(#)AccesoDatos.java   1.0 
 *
 * Copyright (c) 2018
 *
 * Este Software es cofidencial y contiene información de importancia para la
 * Universidad Tecnica Nacional. La  misma debe ser usada
 * con discreción y bajo permiso de la institución
 *
 */

/**
 * Clase que provee el Acceso a Datos para la Aplicación.
 */


public class AccesoDatos implements Serializable {
   
    /*Constante que identifica el motor de la base de datos*/
    static final int SQL_SERVER = 1;
    static final int ORACLE = 2;
    static final int MY_SQL = 3;
    
    
    /*Constantes que identifican el resultado de la operacion*/
    static final int OPERACION_EFECTUADA = 1;
    static final int ERROR_EJECUCION = 0;
    @SuppressWarnings("compatibility:-5753742077201878016")
    private static final long serialVersionUID = 1L;
    private transient Connection dbConn = null;
    private transient Statement stmt = null;

        
        /**
         * Ejecuta una instrucción SQL
         * @param pvcSQL
         * @return
         * @throws FileNotFoundException
         * @throws IOException
         * @throws SQLException
         * @throws ClassNotFoundException
         */
        
        public int ejecutaSQL(String pvcSQL)throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException  {
            //Crear el Driver para la conexion           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dbConn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DBExamen02", "sa", "123456");
            try{
                //Crear el statemente para la ejecucion de sentencias
                stmt = dbConn.createStatement();
                    
                System.out.println("EjecutaSQL: "+pvcSQL);
                stmt.execute(pvcSQL);
                return OPERACION_EFECTUADA;
                }
            catch(SQLException e){
                System.out.println("SQL ERROR: "+pvcSQL);
                System.out.println("SQL EXCEPTION: "+e.getMessage());     
                
                throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
                }
            finally{
                try{
                    stmt.close();
                    }
                catch(SQLException e){
                        System.out.println("SQL ERROR: "+pvcSQL);
                        System.out.println("SQL EXCEPTION: "+e.getMessage());     
                        throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
                    }
              
                
                
                }
            }
        
    /**
    * Ejecuta una instrucción SQL y retorna un ResultSet
    * @param pvcSQL
    * @return
    * @throws IOException
    * @throws SQLException
    * @throws ClassNotFoundException
    */
    public ResultSet ejecutaSQLRetornaRS(String pvcSQL) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        ResultSet rs = null;
     
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dbConn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DBExamen02", "sa", "123456");
        
        try{
            //Crear el statemente para la ejecucion de sentencias
            
            stmt= dbConn.createStatement();
                 
            /* Ejecuta la sentencia SQL */
            System.out.println("EjecutaSQLRetornaRS: " + pvcSQL);
            rs = stmt.executeQuery(pvcSQL);
            return rs;
            
          } catch (SQLException e) {
                System.out.println("SQL ERROR: " + pvcSQL);
                System.out.println("SQL EXCEPTION: " + e.getMessage());
                throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                  e.getMessage(), e.getErrorCode());
                }
            finally{
          
          }
    }

    /**
    * Ejecuta una matriz de sentencias SQL
    * @param pvcSQL
    * @return
    * @throws FileNotFoundException
    * @throws IOException
    * @throws SQLException
    * @throws ClassNotFoundException
    */
    public int ejecutaMatrizSQL(String pvcSQL, String pvcSQLBitacora) throws SNMPExceptions {
        String strSQL[] = null;
        int i = 0;
        //Crear el Driver para la conexion           

        try{
            //Crear el statemente para la ejecucion de sentencias
            stmt = dbConn.createStatement();

            //Actualizar el formato de la fecha en la base de datos
            String formatoFechaSQL = "Alter session set NLS_DATE_FORMAT = 'dd-mm--yy'";
            stmt.execute(formatoFechaSQL);       
       
           
           /* Se divide el string en varios substrings */
           strSQL = pvcSQL.split(";");
           /* Se establece el AutoCommit de la base de datos en falso
              para iniciar una transacción */
           iniciarTransaccion();
           for (i = 0; i < strSQL.length; i++) {
               /* Se ejecuta la sentencia SQL */
               if (!strSQL[i].toString().equals("")) {
                       if (!strSQL[i].toString().equals(" ")) {
                       stmt.execute(strSQL[i].replace(';', ' '));
                   }
               }
           }
           /* Se hace commit a la transacción para finalizar la ejecución de
              las sentencias SQL */
          cerrarTransaccion(true);

          stmt.execute(pvcSQLBitacora);            
           
          return OPERACION_EFECTUADA;
       } catch (SQLException e) {
           System.out.println("SQL ERROR: " + strSQL[i] + " BITACORA: " + pvcSQLBitacora);
           System.out.println("SQL EXCEPTION: " + e.getMessage());            
           cerrarTransaccion(false);
           throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                   e.getMessage(), e.getErrorCode());
       } finally {
           try {
               stmt.close();
           } catch (SQLException e) {
               System.out.println("SQL ERROR: " + strSQL[i] + " BITACORA: " + pvcSQLBitacora);
               System.out.println("SQL EXCEPTION: " + e.getMessage());                
               throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                       e.getMessage(), e.getErrorCode());
           }
       }
    }


    /**
    * Ejecuta una matriz de sentencias SQL
    * @param pvcSQL
    * @return
    * @throws FileNotFoundException
    * @throws IOException
    * @throws SQLException
    * @throws ClassNotFoundException
    */
    public int ejecutaMatrizSQL(String pvcSQL) throws SNMPExceptions {

       String strSQL[] = null;
       int i = 0;
       
       try {
           
           /* Obtiene la conexión hacia la base de datos */
           stmt = dbConn.createStatement();
           
           if(getDataSource().equals("jdbc/SNMPDataSourceDB")){
               String formatoFechaSQL = "Alter session set NLS_DATE_FORMAT = 'dd-mm-yy'";
               stmt.execute(formatoFechaSQL);
           }         

           /* Se divide el string en varios substrings */
           strSQL = pvcSQL.split(";");

           /* Se establece el AutoCommit de la base de datos en falso
              para iniciar una transacción */
           iniciarTransaccion();
           for (i = 0; i < strSQL.length; i++) {
               /* Se ejecuta la sentencia SQL */
               if (!strSQL[i].equals("")) {
                   stmt.execute(strSQL[i]);
               }
           }
           /* Se hace commit a la transacción para finalizar la ejecución de
              las sentencias SQL */
           cerrarTransaccion(true);
           return OPERACION_EFECTUADA;
           
       } catch (SQLException e) {
           System.out.println("SQL ERROR: " + strSQL[i]);
           System.out.println("SQL EXCEPTION: " + e.getMessage());            
           cerrarTransaccion(false);
           throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                   e.getMessage(), e.getErrorCode());
       } finally {
           try {
               stmt.close();
           } catch (SQLException e) {
               System.out.println("SQL ERROR: " + strSQL[i]);
               System.out.println("SQL EXCEPTION: " + e.getMessage());                
               throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                       e.getMessage(), e.getErrorCode());
           }
       }
    }

    /**
    * Ejecuta una instrucción SQL y retorna un ResultSet
    * @param pstSQL
    * @return
    * @throws IOException
    * @throws SQLException
    * @throws ClassNotFoundException
    */
    public ResultSet ejecutaSQLRetornaRS(PreparedStatement pstSQL) throws SNMPExceptions {
    ResultSet rs = null;
    
    try {
        stmt = dbConn.createStatement();

        if(getDataSource().equals("jdbc/SNMPDataSourceDB")){
           String formatoFechaSQL = "Alter session set NLS_DATE_FORMAT = 'dd-mm-yy' ";
           stmt.execute(formatoFechaSQL);
        }            
       
        /* Ejecuta la sentencia SQL */
        System.out.println("EjecutaSQLRetornaRS: " + pstSQL.toString());
        rs = pstSQL.executeQuery();
        return rs;
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + pstSQL.toString());
            System.out.println("SQL EXCEPTION: " + e.getMessage());
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                               e.getMessage(), e.getErrorCode());
        }
    }

    /**
    * Cierra un Statement
    * @throws SQLException
    */
    public void cerrarStatements() throws SNMPExceptions {
       try {
           stmt.close();
       } catch (SQLException e) {
           System.out.println("SQL EXCEPTION: " + e.getMessage());
           throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                   e.getMessage(), e.getErrorCode());
       }
    }

    /**
     * Cierra un ResultSet
     * @param resultado
     * @throws SNMPExceptions
     */
    public void closeResultSet(ResultSet resultado) throws SNMPExceptions {
       try {
           if (resultado != null) {
               resultado.close();
               resultado = null;
           }
       } catch (SQLException e) {
           System.out.println("SQL EXCEPTION: " + e.getMessage());
           throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                   e.getMessage(), e.getErrorCode());
       }
    }

    /**
    * Inicia una transacción en la base de datos.
    * @throws SQLException
    */
    public void iniciarTransaccion() throws SNMPExceptions {

       try {
           dbConn.setAutoCommit(false);
       } catch (SQLException e) {
           System.out.println("SQL EXCEPTION: " + e.getMessage());
           throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                   e.getMessage(), e.getErrorCode());
       }

    }

    /**
    * Cierra una transacción en la base de datos.
    * @throws SQLException
    */
    public void cerrarTransaccion(boolean blnExito) throws SNMPExceptions {
       try {
           if (blnExito == true) {
               dbConn.commit();
           } else {
               dbConn.rollback();
           }
       } catch (SQLException e) {
           System.out.println("SQL EXCEPTION: " + e.getMessage());
           throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                   e.getMessage(), e.getErrorCode());
       }
    }



    public void setTransaccionSerializable() throws SNMPExceptions
    {
       try
       {
           dbConn.setTransactionIsolation(dbConn.TRANSACTION_SERIALIZABLE);
       }
       catch(SQLException e)
       {
           System.out.println("SQL EXCEPTION: " + e.getMessage());
           throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
       }
    }

    public void setTransaccionNone() throws SNMPExceptions
    {
       try
       {
           dbConn.setTransactionIsolation(dbConn.TRANSACTION_NONE);
       }
       catch(SQLException e)
       {
           System.out.println("SQL EXCEPTION: " + e.getMessage());
           throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
       }
    }

    /**
    * Cierra la conexión con la base de datos.
    * @throws SQLException
    */
    public void cerrarConexion() throws SNMPExceptions {

       try {
           if(dbConn != null) {
               dbConn.close();
               dbConn = null;
           }
       } catch (SQLException e) {
           System.out.println("SQL EXCEPTION: " + e.getMessage());
           throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                   e.getMessage(), e.getErrorCode());
       }
    }

    /**
    * Deshace los cambios hechos dentro de una transacción.
    * @throws SQLException
    */
    public void rollbackTransaccion() throws SNMPExceptions {
       try {
           dbConn.rollback();
       } catch (SQLException e) {
           System.out.println("SQL EXCEPTION: " + e.getMessage());
           throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                   e.getMessage(), e.getErrorCode());
       }
    }

    public void setDbConn(Connection dbConn) {
       this.dbConn = dbConn;
    }

    public Connection getDbConn() {
       return dbConn;
    }
    
    
    public String getDataSource(){
             
       String ds = "";
       
       try {

           Properties properties = new Properties();
           String pathToConfigurationFile = "Configuracion.properties";
           
           //Carga del archivo de configuración
           FileInputStream input = new FileInputStream(pathToConfigurationFile);
           properties.load(input);
           
           ds = properties.getProperty("datasource");   
           System.out.println("Data Source: " + ds);
           //dataSource = (DataSource) context.lookup(ds);

       }
       catch (IOException e) {
           System.out.println("SQL EXCEPTION: " + e.getMessage());
           System.out.println("ERROR: "+ e.getMessage());
       }
       return ds;
       
    }


    public AccesoDatos() {
        super();
    }

}




