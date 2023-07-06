package com.gestordetrabajo.myapplication;

import java.util.PrimitiveIterator;

public class RegistroGastos {

    private String Mes;
    private String Salario;
    private String Gastos;

    public RegistroGastos(String mes, String salario, String gastos) {
        Mes = mes;
        Salario = salario;
        Gastos = gastos;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String mes) {
        Mes = mes;
    }

    public String getSalario() {
        return Salario;
    }

    public void setSalario(String salario) {
        Salario = salario;
    }

    public String getGastos() {
        return Gastos;
    }

    public void setGastos(String gastos) {
        Gastos = gastos;
    }
}



