package br.edu.ifsp.fbenutti.projetoos.entidades;

/**
 * Created by pelps_000 on 19/05/2015.
 */
public class objBase {
    private Integer id_usuario_inc;
    private String dt_inc;
    private Integer id_usuario_alt;
    private String dt_alt;

    public objBase() {
    }

    public int getId_usuario_inc() {
        return id_usuario_inc;
    }

    public void setId_usuario_inc(int id_usuario_inc) {
        this.id_usuario_inc = id_usuario_inc;
    }

    public String getDt_inc() {
        return dt_inc;
    }

    public void setDt_inc(String dt_inc) {
        this.dt_inc = dt_inc;
    }

    public int getId_usuario_alt() {
        return id_usuario_alt;
    }

    public void setId_usuario_alt(int id_usuario_alt) {
        this.id_usuario_alt = id_usuario_alt;
    }

    public String getDt_alt() {
        return dt_alt;
    }

    public void setDt_alt(String dt_alt) {
        this.dt_alt = dt_alt;
    }
}
