/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Model.logicaPractica_1;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Antonio Ramirez
 */
public class beanPractica {
    String codigoCentroSalud="";
    String centroDeSalud;
    String codigoDeTipoServicio="0";
    String tipoDeServicio;
    LinkedList<SelectItem> listaServicio= new LinkedList();
    String Especialidades;
    String codigoDeEspecialidades="0";
    LinkedList<SelectItem> listaEspecialidades= new LinkedList();

    
//Inicio de Centro de salud
    public String getCodigoCentroSalud() {
        return codigoCentroSalud;
    }

    public void setCodigoCentroSalud(String codigoCentroSalud) {
        this.codigoCentroSalud = codigoCentroSalud;
    }

    public String getCentroDeSalud() {
        return  centroDeSalud=(codigoCentroSalud.equals("2680")) ? "AREA DE SALUD TALAMANCA" : "CENTRO DE SALUD NO VALIDO";
    }

    public void setCentroDeSalud(String centroDeSalud) {
        this.centroDeSalud = centroDeSalud;
    }
   //public void validarCentro(){
     //  centroDeSalud=(codigoCentroSalud.equals("2680")) ? "AREA DE SALUD TALAMANCA" : "CENTRO DE SALUD NO VALIDO";
   //}
//Fin de centro de salud
   //Inicio de de tipo de servicio
    public String getCodigoDeTipoServicio() {
        return codigoDeTipoServicio;
    }

    public void setCodigoDeTipoServicio(String codigoDeTipoServicio) {
        this.codigoDeTipoServicio = codigoDeTipoServicio;
    }

    public String getTipoDeServicio() {
        return tipoDeServicio;
    }

    public void setTipoDeServicio(String tipoDeServicio) {
        this.tipoDeServicio = tipoDeServicio;
    }

    public LinkedList<SelectItem> getListaServicio() {
        LinkedList listService = new LinkedList();
        
      if (codigoDeTipoServicio.equals("1")) {
            logicaPractica_1 objListModel = new logicaPractica_1();
            return objListModel.listaServiciHospitalizacion();
        } else if (codigoDeTipoServicio.equals("2")) {
            logicaPractica_1 objListModel = new logicaPractica_1();
            return objListModel.listaServicioUrgencia();
        } else if (codigoDeTipoServicio.equals("3")) {
            logicaPractica_1 objListModel = new logicaPractica_1();
            return objListModel.listaServicioMedico();
        } else {
            listService.add(new SelectItem("Digita un numero (1-3) y da enter"));
            return listService;
    }   
    }

    public void setListaServicio(LinkedList<SelectItem> listaServicio) {
        this.listaServicio = listaServicio;
    }
   // public void validarCodigo(){
     //    codigoDeTipoServicio=(tipoDeServicio.equals("Servicios Medicos")) ? "1": (tipoDeServicio.equals("Servicios Urgencia")) ? "2" : "3";
    //}
   //fin de tipo de servicio

    public String getEspecialidades() {
        return Especialidades;
    }

    public void setEspecialidades(String Especialidades) {
        this.Especialidades = Especialidades;
    }

    public String getCodigoDeEspecialidades() {
        return codigoDeEspecialidades;
    }

    public void setCodigoDeEspecialidades(String codigoDeEspecialidades) {
        this.codigoDeEspecialidades = codigoDeEspecialidades;
    }

    public LinkedList<SelectItem> getListaEspecialidades() {
        LinkedList listSpecia = new LinkedList();

        if (codigoDeEspecialidades.equals("1")) {
            logicaPractica_1 objSpecialtiesModel = new logicaPractica_1();
            return objSpecialtiesModel.listaEspecialidadesCardioogia();
        } else if (codigoDeEspecialidades.equals("2")) {
            logicaPractica_1 objSpecialtiesModel = new logicaPractica_1();
            return objSpecialtiesModel.listaEspecialidadesGenera();
        } else if (codigoDeEspecialidades.equals("3")) {
            logicaPractica_1 objSpecialtiesModel = new logicaPractica_1();
            return objSpecialtiesModel.listaEspecialidadesPsicologia();
        } else {
            listSpecia.add(new SelectItem("Digita un numero (1-3) y da enter"));
            return listSpecia;
    }   
}

    public void setListaEspecialidades(LinkedList<SelectItem> listaEspecialidades) {
        this.listaEspecialidades = listaEspecialidades;
    }
    }
