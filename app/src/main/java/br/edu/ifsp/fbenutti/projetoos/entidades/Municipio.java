package br.edu.ifsp.fbenutti.projetoos.entidades;

/**
 * Created by pelps_000 on 21/05/2015.
 */
public class Municipio {
    private int id_municipio;
    private String municipio;
    private String uf;

    public Municipio() {
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
