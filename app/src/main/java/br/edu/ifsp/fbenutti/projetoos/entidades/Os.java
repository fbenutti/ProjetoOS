package br.edu.ifsp.fbenutti.projetoos.entidades;

/**
 * Created by pelps_000 on 19/05/2015.
 */
public class Os extends objBase {
    private int id_os;
    private Dispositivo dispositivo;
    private Status status;
    private Nivel nivel;
    private Prioridade prioridade;
    private Usuario usuario;
    private String titulo;
    private String reclamacao;
    private Municipio municipio;
    private String nome_cliente;
    private String telefone;
    private double tempo_estimado;
    private String dt_abertura;
    private String dt_resolucao;
    private String dt_retorno;
    private String dt_prazo;

   

    public Os() {
        this.status = new Status();
        this.nivel = new Nivel();
        this.prioridade = new Prioridade();
        this.usuario = new Usuario();
    }

    public int getId_os() {
        return id_os;
    }

    public void setId_os(int id_os) {
        this.id_os = id_os;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getReclamacao() {
        return reclamacao;
    }

    public void setReclamacao(String reclamacao) {
        this.reclamacao = reclamacao;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getTempo_estimado() {
        return tempo_estimado;
    }

    public void setTempo_estimado(double tempo_estimado) {
        this.tempo_estimado = tempo_estimado;
    }

    public String getDt_abertura() {
        return dt_abertura;
    }

    public void setDt_abertura(String dt_abertura) {
        this.dt_abertura = dt_abertura;
    }

    public String getDt_resolucao() {
        return dt_resolucao;
    }

    public void setDt_resolucao(String dt_resolucao) {
        this.dt_resolucao = dt_resolucao;
    }

    public String getDt_retorno() {
        return dt_retorno;
    }

    public void setDt_retorno(String dt_retorno) {
        this.dt_retorno = dt_retorno;
    }

    public String getDt_prazo() {
        return dt_prazo;
    }

    public void setDt_prazo(String dt_prazo) {
        this.dt_prazo = dt_prazo;
    }
}
