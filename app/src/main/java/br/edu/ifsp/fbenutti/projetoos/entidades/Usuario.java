package br.edu.ifsp.fbenutti.projetoos.entidades;

/**
 * Created by pelps_000 on 20/05/2015.
 */
public class Usuario extends objBase {
    private int id_usuario;
    private String username;
    private String password;
    private String email;
    private Departamento departamento;

    public Usuario() {
    }

    public Usuario(int id_usuario, String username, String password, String email, Departamento departamento) {
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
        this.email = email;
        this.departamento = departamento;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
