package br.edu.ifsp.fbenutti.projetoos.entidades;

/**
 * Created by pelps_000 on 20/05/2015.
 */
public class OsAcao {
    private int id_acao;
    private Os os;
    private TipoAcao tipoAcao;
    private Usuario usuarioOrigem;
    private Nivel nivelOrigem;
    private Usuario usuarioDestino;
    private Nivel nivelDestino;
    private String descricao;

    public OsAcao() {
    }

    public OsAcao(int id_acao, Os os, TipoAcao tipoAcao, Usuario usuarioOrigem, Nivel nivelOrigem,
                  Usuario usuarioDestino, Nivel nivelDestino, String descricao) {
        this.id_acao = id_acao;
        this.os = os;
        this.tipoAcao = tipoAcao;
        this.usuarioOrigem = usuarioOrigem;
        this.nivelOrigem = nivelOrigem;
        this.usuarioDestino = usuarioDestino;
        this.nivelDestino = nivelDestino;
        this.descricao = descricao;
    }

    public int getId_acao() {
        return id_acao;
    }

    public void setId_acao(int id_acao) {
        this.id_acao = id_acao;
    }

    public Os getOs() {
        return os;
    }

    public void setOs(Os os) {
        this.os = os;
    }

    public TipoAcao getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(TipoAcao tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public Usuario getUsuarioOrigem() {
        return usuarioOrigem;
    }

    public void setUsuarioOrigem(Usuario usuarioOrigem) {
        this.usuarioOrigem = usuarioOrigem;
    }

    public Nivel getNivelOrigem() {
        return nivelOrigem;
    }

    public void setNivelOrigem(Nivel nivelOrigem) {
        this.nivelOrigem = nivelOrigem;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public Nivel getNivelDestino() {
        return nivelDestino;
    }

    public void setNivelDestino(Nivel nivelDestino) {
        this.nivelDestino = nivelDestino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
