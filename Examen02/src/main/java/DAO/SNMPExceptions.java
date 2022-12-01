package DAO;

public class SNMPExceptions extends Exception {
    public static final int FILE_NOT_FOUND = 1;
      public static final int IO_EXCEPTION = 2;
      public static final int SQL_EXCEPTION = 3;
      public static final int CLASS_NOT_FOUND_EXCEPTION = 4;
      public static final int CREATE_EXCEPTION = 5;
      public static final int NAMING_EXCEPTION = 6;
      public static final int REMOTE_EXCEPTION = 7;
      public static final int GLOBAL_EXCEPTION = 8;
      public static final int PARSE_EXCEPTION = 9;
      public static final int OBJECT_NOT_FOUND_EXCEPTION = 10;
      public static final int HOME_fACTORY_EXCEPTION = 11;
      public static final int JR_EXCEPTION = 12;
      public static final int SAX_EXCEPTION = 13;
      public static final int PARSER_CONFIGURATION_EXCEPTION = 14;
      public static final int BIFF_EXCEPTION = 15;
      public static final int DATASOURCE_EXCEPTION = 16;
      public static final int MISE_EXCEPTION = 17;
      
      //Mensajes estandarizados para el usuario
      public static final String MSG_USR_FILE_NOT_FOUND = "DAO.exception.FILE_NOT_FOUND";
      public static final String MSG_USR_IO_EXCEPTION = "DAO.exception.IO_EXCEPTION";
      public static final String MSG_USR_CLASS_NOT_FOUND_EXCEPTION = "DAO.exception.CLASS_NOT_FOUND_EXCEPTION";
      public static final String MSG_USR_CREATE_EXCEPTION = "DAO.exception.CREATE_EXCEPTION";
      public static final String MSG_USR_NAMING_EXCEPTION = "DAO.exception.NAMING_EXCEPTION";
      public static final String MSG_USR_REMOTE_EXCEPTION = "DAO.exception.REMOTE_EXCEPTION";
      public static final String MSG_USR_GLOBAL_EXCEPTION = "DAO.exception.GLOBAL_EXCEPTION";
      public static final String MSG_USR_PARSE_EXCEPTION = "DAO.exception.PARSE_EXCEPTION";
      public static final String MSG_USR_OBJECT_NOT_FOUND_EXCEPTION = "DAO.exception.OBJECT_NOT_FOUND_EXCEPTION";
      public static final String MSG_USR_JR_EXCEPTION = "DAO.exception.JR_EXCEPTION";
      public static final String MSG_USR_SAX_EXCEPTION = "DAO.exception.SAX_EXCEPTION";
      public static final String MSG_USR_PARSER_CONFIGURATION_EXCEPTION = "DAO.exception.PARSER_CONFIGURATION_EXCEPTION";
      public static final String MSG_USR_BIFF_EXCEPTION = "DAO.exception.BIFF_EXCEPTION";
      public static final String MSG_USR_SQL_EXCEPTION = "DAO.exception.SQL_EXCEPTION";
      public static final String MSG_USR_SQL_EXCEPTION_LLAVE_DUPLICADA = "DAO.exception.SQL_EXCEPTION.llaveDuplicada";
      public static final String MSG_USR_SQL_EXCEPTION_NO_EXISTE = "DAO.exception.SQL_EXCEPTION.noExiste";
      public static final String MSG_USR_DEBE_SELECCIONAR_EXCEL = "DAO.exception.DEBE.SELECCIONAR_EXCEL";//OJO AQUI
      public static final String MSG_USR_DATASOURCE_EXCEPTION = "DAO.exception.ERROR.DATASOURCE";
      public static final String MSG_USR_MISE = "DAO.exception.MISE_EXCEPTION";//OJO AQUI
      public static final String MSG_USR_DEFAULT = "DAO.exception.ERROR.DEFAULT";
      
      //base de datos
      private static int BDSQL = 1;
      private static int BDORACLE = 2;
      @SuppressWarnings("compatibility:6700755726920661460")
      private static final long serialVersionUID = 1L;

      //Variables que se manejan en la exception
      private String mensajeParaDesarrollador;  //Mensaje para el desarrollador: Codigo del error-Tipo de Exception-Mensaje de la Exception_BD
      private String mensajeParaUsuario;  //Mensaje personalizado para el Usurio
      
      /**
       * Constructor que se encarga de la asignar el mensaje para el usuario
       * @param pvcMensajeParaUsuario
       */
      
       public SNMPExceptions(String pvcMensajeParaUsuario){
              this.setMensajeParaUsuario(pvcMensajeParaUsuario);
              }
          /**
           * Constructor que se encara de asignar el valor del tipo de exception, el mensaje para el usuario y el mensaje para imprimir
           * en consola para el desarrollador
           * @param pvnTipoException
           * @param pvcMensaje
           */
          
          public SNMPExceptions(int pvnTipoException, String pvcMensaje){
              mensajeParaDesarrollador = String.valueOf(pvnTipoException)+"-"+ pvcMensaje;
              mensajeParaUsuario = MSG_USR_DEFAULT;
              
              switch(pvnTipoException){
              case FILE_NOT_FOUND:
                  mensajeParaUsuario = MSG_USR_FILE_NOT_FOUND;
                  break;
              case IO_EXCEPTION:
                  mensajeParaUsuario = MSG_USR_IO_EXCEPTION;
                  break;
              case CLASS_NOT_FOUND_EXCEPTION:
                  mensajeParaUsuario = MSG_USR_CLASS_NOT_FOUND_EXCEPTION;
                  break;
              default:
                  mensajeParaUsuario = MSG_USR_DEFAULT;
                  break;
              };
              }
          
          /**
           * Constructor que ádemas recibe el código del error de la exception para inmprimirlo en la consola para el desarrollador
           * aplica para SQLException
           * @param pvnTipoException
           * @param pvcMensaje
           * @param pvnCodigoError
           */
          
          public SNMPExceptions(int pvnTipoException, String pvcMensaje, int pvnCodigoError) {
              mensajeParaDesarrollador = String.valueOf(pvnCodigoError)+"-"+String.valueOf(pvnTipoException)+"-"+pvcMensaje;
              mensajeParaUsuario = MSG_USR_DEFAULT;
              
              if(pvnTipoException == SQL_EXCEPTION){
                  mensajeParaUsuario = MSG_USR_SQL_EXCEPTION;
                  }
          }
          
          /**
           * Constructor que ádemas recibe la base de datos en la que se origino la exception para imprimir un mesaje personalizado
           * de acuerdo al código de error, aplica para SQLException
           * @param pvnTipoException
           * @param pvcMensaje
           * @param pvnCodigoError
           * @param pvnBD
           */
          public SNMPExceptions(int pvnTipoException, String pvcMensaje, int pvnCodigoError, int pvnBD) {
              mensajeParaDesarrollador = String.valueOf(pvnCodigoError)+"-"+String.valueOf(pvnTipoException)+"-"+pvcMensaje+"-"+pvnBD;
              mensajeParaUsuario = MSG_USR_DEFAULT;
              
              if(pvnTipoException == SQL_EXCEPTION){
                  if(pvnCodigoError == 2627 && pvnBD == BDSQL){
                      mensajeParaUsuario = MSG_USR_SQL_EXCEPTION_LLAVE_DUPLICADA;
                   }
                  else{
                      mensajeParaUsuario = MSG_USR_SQL_EXCEPTION;
                  }
                  }
                  }
          
          //Sets y Gets de las variables manejadas en la excetion

          public void setMensajeParaDesarrollador(String mensajeParaDesarrollador) {
              this.mensajeParaDesarrollador = mensajeParaDesarrollador;
          }

          public String getMensajeParaDesarrollador() {
              return mensajeParaDesarrollador;
          }

          public void setMensajeParaUsuario(String mensajeParaUsuario) {
              this.mensajeParaUsuario = mensajeParaUsuario;
          }

          public String getMensajeParaUsuario() {
              return mensajeParaUsuario;
          }
          
    public SNMPExceptions() {
        super();
    }
}


