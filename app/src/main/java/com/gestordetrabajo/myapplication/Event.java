package com.gestordetrabajo.myapplication;

import java.util.Date;

public class Event {

    String name;
    String fecha;
    String description;
    String sesion;
    String hora;

    public Event( String name, String description, String sesion, String hora) {

        this.name = name;
       // this.fecha = fecha;
        
        this.description = description;
        this.sesion = sesion;
        this.hora = hora;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        hora = hora;
    }
}
