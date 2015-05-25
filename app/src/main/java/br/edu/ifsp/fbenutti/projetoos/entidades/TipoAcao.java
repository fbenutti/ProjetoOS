package br.edu.ifsp.fbenutti.projetoos.entidades;

/**
 * Created by pelps_000 on 19/05/2015.
 */
public class TipoAcao {
    private int id_tipo_acao;
    private String descricao;

    public TipoAcao() {
    }

    public int getId_tipo_acao() {
        return id_tipo_acao;
    }

    public void setId_tipo_acao(int id_tipo_acao) {
        this.id_tipo_acao = id_tipo_acao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
