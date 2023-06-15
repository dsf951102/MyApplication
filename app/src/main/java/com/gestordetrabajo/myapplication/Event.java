package com.gestordetrabajo.myapplication;



public class Event {
    String id;
    String name;
    String fecha;

    String hora;

    public Event( String id, String name, String hora, String fecha) {
        this.id = id;
        this.name = name;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getId() {
        return id;
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

       public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        hora = hora;
    }
}
