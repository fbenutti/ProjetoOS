package br.edu.ifsp.fbenutti.projetoos.entidades;

/**
 * Created by pelps_000 on 19/05/2015.
 */
public class Permissoes {
    private int id_permissao;
    private String descricao;

    public Permissoes() {
    }

    public int getId_permissao() {
        return id_permissao;
    }

    public void setId_permissao(int id_permissao) {
        this.id_permissao = id_permissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
