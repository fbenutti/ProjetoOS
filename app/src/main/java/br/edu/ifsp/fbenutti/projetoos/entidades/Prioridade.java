package br.edu.ifsp.fbenutti.projetoos.entidades;

/**
 * Created by pelps_000 on 19/05/2015.
 */
public class Prioridade {
    private int id_prioridade;
    private String descricao;

    public Prioridade() {
    }

    public int getId_prioridade() {
        return id_prioridade;
    }

    public void setId_prioridade(int id_prioridade) {
        this.id_prioridade = id_prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
