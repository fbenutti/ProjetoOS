package br.edu.ifsp.fbenutti.projetoos.entidades;

/**
 * Created by pelps_000 on 19/05/2015.
 */
public class objBase {
    private Integer id_usuario_inc;
    private Integer dt_inc;
    private Integer id_usuario_alt;
    private Integer dt_alt;

    public objBase() {
    }

    public int getId_usuario_inc() {
        return id_usuario_inc;
    }

    public void setId_usuario_inc(int id_usuario_inc) {
        this.id_usuario_inc = id_usuario_inc;
    }

    public int getDt_inc() {
        return dt_inc;
    }

    public void setDt_inc(int dt_inc) {
        this.dt_inc = dt_inc;
    }

    public int getId_usuario_alt() {
        return id_usuario_alt;
    }

    public void setId_usuario_alt(int id_usuario_alt) {
        this.id_usuario_alt = id_usuario_alt;
    }

    public int getDt_alt() {
        return dt_alt;
    }

    public void setDt_alt(int dt_alt) {
        this.dt_alt = dt_alt;
    }
}
