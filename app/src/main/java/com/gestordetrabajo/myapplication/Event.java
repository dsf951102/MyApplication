package com.gestordetrabajo.myapplication;



public class Event {

    String name;
    String fecha;

    String hora;

    public Event( String fecha, String name, String hora) {

        this.name = name;
        this.fecha = fecha;
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

       public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        hora = hora;
    }
}
