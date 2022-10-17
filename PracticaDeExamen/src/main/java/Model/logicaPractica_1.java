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
public class logicaPractica_1 {
         LinkedList listService = new LinkedList();
     LinkedList listSpecia = new LinkedList();
      public LinkedList<SelectItem> listaServicioMedico(){
        
        listService.add(new SelectItem("Servicio Medico"));
        return listService;
    }
       public LinkedList<SelectItem> listaServicioUrgencia(){
        
        listService.add(new SelectItem("Servicio Urgencia"));
        return listService;
    }
        public LinkedList<SelectItem> listaServiciHospitalizacion(){
        
        listService.add(new SelectItem("Servicio Hospitalizacion"));
        return listService;
    }
      //Especialidades
      public LinkedList<SelectItem> listaEspecialidadesCardioogia(){
           listSpecia.add(new SelectItem("Cardiología"));
        return listSpecia;
}
        public LinkedList<SelectItem> listaEspecialidadesGenera(){
           listSpecia.add(new SelectItem("General"));
        return listSpecia;
        }
        public LinkedList<SelectItem> listaEspecialidadesPsicologia(){
           listSpecia.add(new SelectItem("Psicologia"));
        return listSpecia;
        }
        //Fin de especialidades
        //Inicio de genero
        public LinkedList<SelectItem> listaGenero(){
        LinkedList ListaGenero= new LinkedList();
        ListaGenero.add(new SelectItem("Ambas"));
        ListaGenero.add(new SelectItem("Masculino"));
        ListaGenero.add(new SelectItem("Femenino"));
        return ListaGenero;
}

    }
