package com.jeictechnology.conversormoneda.modelos;

import java.util.HashMap;

public class Moneda {

    private String pais;

    private String codigo;

    public Moneda(String pais, String codigo) {
        this.pais = pais;
        this.codigo = codigo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "pais='" + pais + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }

    public String convertir(){
        return "";
    }
}
