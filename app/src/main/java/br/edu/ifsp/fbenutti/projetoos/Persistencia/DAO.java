package br.edu.ifsp.fbenutti.projetoos.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.fbenutti.projetoos.entidades.Departamento;
import br.edu.ifsp.fbenutti.projetoos.entidades.Municipio;
import br.edu.ifsp.fbenutti.projetoos.entidades.Nivel;
import br.edu.ifsp.fbenutti.projetoos.entidades.Os;
import br.edu.ifsp.fbenutti.projetoos.entidades.Permissoes;
import br.edu.ifsp.fbenutti.projetoos.entidades.Prioridade;
import br.edu.ifsp.fbenutti.projetoos.entidades.Status;
import br.edu.ifsp.fbenutti.projetoos.entidades.Usuario;
import br.edu.ifsp.fbenutti.projetoos.util.CargaDadosBD;

/**
 * Created by pelps_000 on 20/05/2015.
 */
public abstract class DAO <T> extends SQLiteOpenHelper {

    //private static ResourceBundle config =
           // ResourceBundle.getBundle(Constantes.DB_CONFIG_PROPS, Locale.getDefault());
    protected Context context;
    protected SQLiteDatabase database;
    protected String[] campos;
    protected String tableName;

    //region Declaração NOMES das Tabelas do Banco de Dados SQLite
    //region Tabelas padronizadoras (codigo_)
    public static final String TABLE_DEPARTAMENTO = "codigo_departamento";
    public static final String TABLE_MUNICIPIOS = "codigo_municipios";
    public static final String TABLE_NIVEL = "codigo_nivel";
    public static final String TABLE_PERMISSOES = "codigo_permissoes";
    public static final String TABLE_PRIORIDADE = "codigo_prioridade";
    public static final String TABLE_STATUS = "codigo_status";
    public static final String TABLE_TIPO_ACAO = "codigo_tipo_acao";
    //endregion

    public static final String TABLE_DISPOSITIVOS = "dispositivos";
    public static final String TABLE_OS = "os";
    public static final String TABLE_OS_ACAO = "os_acao";
    public static final String TABLE_USUARIO = "usuario";
    public static final String TABLE_USUARIO_PERMISSOES = "usuario_permissoes";

    //endregion

    //region Create das Tabelas do Banco de Dados SQLite
    private StringBuilder CREATE_TABLE_DEPARTAMENTO = new StringBuilder().
            append("CREATE TABLE codigo_departamento (").
            append("id_departamento INTEGER PRIMARY KEY NOT NULL,").
            append("descricao TEXT DEFAULT NULL").
            append(");");

    private StringBuilder CREATE_TABLE_MUNICIPIOS = new StringBuilder().
            append("CREATE TABLE codigo_municipios (").
            append("id_municipio INTEGER PRIMARY KEY NOT NULL,").
            append("municipio TEXT  DEFAULT NULL,").
            append("uf char(2)  DEFAULT NULL").
            append(");");

    private StringBuilder CREATE_TABLE_NIVEL = new StringBuilder().
            append("CREATE TABLE codigo_nivel (").
            append("id_nivel INTEGER PRIMARY KEY NOT NULL,").
            append("descricao TEXT DEFAULT NULL").
            append(");");

    private StringBuilder CREATE_TABLE_PERMISSOES = new StringBuilder().
            append("CREATE TABLE codigo_permissoes (").
            append("id_permissao INTEGER PRIMARY KEY NOT NULL,").
            append("descricao TEXT DEFAULT NULL").
            append(");");

    private StringBuilder CREATE_TABLE_PRIORIDADE = new StringBuilder().
            append("CREATE TABLE codigo_prioridade (").
            append("id_prioridade INTEGER PRIMARY KEY NOT NULL,").
            append("descricao TEXT DEFAULT NULL").
            append(");");

    private StringBuilder CREATE_TABLE_STATUS = new StringBuilder().
            append("CREATE TABLE codigo_status (").
            append("id_status INTEGER PRIMARY KEY NOT NULL,").
            append("descricao TEXT DEFAULT NULL").
            append(");");

    private StringBuilder CREATE_TABLE_TIPO_ACAO = new StringBuilder().
            append("CREATE TABLE codigo_tipo_acao (").
            append("id_tipo_acao INTEGER PRIMARY KEY NOT NULL,").
            append("descricao TEXT DEFAULT NULL").
            append(");");

    private StringBuilder CREATE_TABLE_DISPOSITIVOS = new StringBuilder().
            append("CREATE TABLE dispositivos (").
            append("id_dispositivo INTEGER PRIMARY KEY NOT NULL,").
            append("android_genkey text,").
            append("sn_ativo INTEGER DEFAULT NULL").
            append(");");

    private StringBuilder CREATE_TABLE_OS = new StringBuilder().
            append("CREATE TABLE os (").
            append("id_os INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,").
            append("es_dispositivo INTEGER NOT NULL,").
            append("es_status INTEGER NOT NULL,").
            append("es_nivel INTEGER NOT NULL,").
            append("es_responsavel INTEGER DEFAULT NULL,").
            append("es_prioridade INTEGER NOT NULL,").
            append("titulo TEXT NOT NULL,").
            append("reclamacao TEXT DEFAULT NULL,").
            append("es_municipio INTEGER DEFAULT NULL,").
            append("nome_cliente TEXT DEFAULT NULL,").
            append("telefone TEXT DEFAULT NULL,").
            append("tempo_estimado decimal(3,2) DEFAULT NULL,").
            append("dt_abertura TIMESTAMP NOT NULL,").
            append("dt_resolucao TIMESTAMP DEFAULT NULL,").
            append("dt_retorno TIMESTAMP DEFAULT NULL,").
            append("dt_prazo TIMESTAMP DEFAULT NULL,").
            append("id_usuario_inc INTEGER DEFAULT NULL,").
            append("dt_inc TIMESTAMP DEFAULT NULL,").
            append("id_usuario_alt INTEGER DEFAULT NULL,").
            append("dt_alt TIMESTAMP DEFAULT NULL,").
            append("id_usuario_resolucao INTEGER DEFAULT NULL").
            append(");");

    private StringBuilder CREATE_TABLE_OS_ACAO = new StringBuilder().
            append("CREATE TABLE os_acao (").
            append("id_acao INTEGER PRIMARY KEY NOT NULL,").
            append("es_os INTEGER NOT NULL,").
            append("es_tipo_acao INTEGER NOT NULL,").
            append("es_usuario_origem INTEGER NOT NULL,").
            append("es_nivel_origem INTEGER NOT NULL,").
            append("id_usuario_destino INTEGER DEFAULT NULL,").
            append("id_nivel_destino INTEGER DEFAULT NULL,").
            append("descricao TEXT DEFAULT NULL,").
            append("id_usuario_inc INTEGER DEFAULT NULL,").
            append("dt_inc TIMESTAMP DEFAULT NULL,").
            append("id_usuario_alt INTEGER DEFAULT NULL,").
            append("dt_alt TIMESTAMP DEFAULT NULL,").
            append("FOREIGN KEY(es_os) REFERENCES os (id_os)").
            append(");");

    private StringBuilder CREATE_TABLE_USUARIO = new StringBuilder().
            append("CREATE TABLE usuario (").
            append("id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,").
            append("username TEXT NOT NULL,").
            append("password TEXT NOT NULL,").
            append("email TEXT DEFAULT NULL,").
            append("es_departamento INTEGER DEFAULT NULL,").
            append("dt_inc TIMESTAMP NOT NULL,").
            append("id_usuario_alt INTEGER DEFAULT NULL,").
            append("dt_alt TIMESTAMP DEFAULT NULL").
            append(");");

    private StringBuilder CREATE_TABLE_USUARIO_PERMISSOES = new StringBuilder().
            append("CREATE TABLE usuario_permissoes (").
            append("es_permissoes INTEGER PRIMARY KEY NOT NULL,").
            append("es_usuario INTEGER NOT NULL").
            append(");");

    //endregion

    //region Insert padrão das Tabelas do Banco de Dados SQLite
    /*private StringBuilder INSERT_TABLE_DEPARTAMENTO = new StringBuilder().
        append("INSERT INTO codigo_departamento VALUES ").
        append("(1, 'GESTÃO DE CLIENTES'),").
        append("(2, 'SUPORTE'),").
        append("(3, 'DESENVOLVIMENTO'),").
        append("(4, 'INFRAESTRUTURA');");

    private StringBuilder INSERT_TABLE_NIVEL = new StringBuilder().
        append("INSERT INTO codigo_nivel VALUES ").
        append("(0, 'Todos'),").
        append("(1, 'Suporte'),").
        append("(2, 'Banco'),").
        append("(3, 'Desenvolvimento'),").
        append("(4, 'Atualização'),").
        append("(5, 'Bug'),").
        append("(6, 'Pré-análise'),").
        append("(7, 'Resolução'),").
        append("(8, 'Concluídas'),").
        append("(9, 'Teste'),").
        append("(10, 'Infraestrutura'),").
        append("(11, 'Gestao');");

    private StringBuilder INSERT_TABLE_PRIORIDADE = new StringBuilder().
        append("INSERT INTO codigo_prioridade VALUES ").
        append("(1, 'mínima'),").
        append("(2, 'média'),").
        append("(3, 'máxima'),").
        append("(4, 'urgente');");

    private StringBuilder INSERT_TABLE_STATUS = new StringBuilder().
        append("INSERT INTO codigo_status VALUES ").
        append("(1, 'pendente'),").
        append("(2, 'em andamento'),").
        append("(3, 'concluido'),").
        append("(4, 'não concluido');");

    private StringBuilder INSERT_TABLE_TIPO_ACAO = new StringBuilder().
        append("INSERT INTO codigo_tipo_acao  VALUES ").
        append("(1, 'Comentário'),").
        append("(2, 'Troca Responsável'),").
        append("(3, 'Troca Nível'),").
        append("(4, 'Víncular'),");

    private StringBuilder INSERT_TABLE_PERMISSOES = new StringBuilder().
        append("INSERT INTO codigo_permissoes VALUES ").
        append("(1, 'banco'),").
        append("(2, 'desenvolvimento'),").
        append("(3, 'pre_analise'),").
        append("(4, 'suporte'),").
        append("(5, 'prefeitura');");
    */
    //endregion

    public DAO(Context context) {
        super(context, "projetoos", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEPARTAMENTO.toString());
        db.execSQL(CREATE_TABLE_MUNICIPIOS.toString());
        db.execSQL(CREATE_TABLE_NIVEL.toString());
        db.execSQL(CREATE_TABLE_PERMISSOES.toString());
        db.execSQL(CREATE_TABLE_PRIORIDADE.toString());
        db.execSQL(CREATE_TABLE_STATUS.toString());
        db.execSQL(CREATE_TABLE_TIPO_ACAO.toString());
        db.execSQL(CREATE_TABLE_DISPOSITIVOS.toString());
        db.execSQL(CREATE_TABLE_OS.toString());
        db.execSQL(CREATE_TABLE_OS_ACAO.toString());
        db.execSQL(CREATE_TABLE_USUARIO.toString());
        db.execSQL(CREATE_TABLE_USUARIO_PERMISSOES.toString());

        CargaDadosBD.inserirDepartamento(context, db);
        CargaDadosBD.inserirNivel(context, db);
        CargaDadosBD.inserirPermissoes(context, db);
        CargaDadosBD.inserirPrioridade(context, db);
        CargaDadosBD.inserirStatus(context, db);
        CargaDadosBD.inserirTipoAcao(context, db);

        //db.execSQL(INSERT_TABLE_DEPARTAMENTO.toString());
        //db.execSQL(INSERT_TABLE_NIVEL.toString());
        //db.execSQL(INSERT_TABLE_PRIORIDADE.toString());
        //db.execSQL(INSERT_TABLE_STATUS.toString());
        //db.execSQL(INSERT_TABLE_TIPO_ACAO.toString());
        //db.execSQL(INSERT_TABLE_PERMISSOES.toString());
        //db.execSQL(Constantes.INSERT_TABLE_MUNICIPIOS.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISPOSITIVOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OS_ACAO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO_PERMISSOES);
        onCreate(db);
    }

    protected abstract ContentValues serializeContentValues(T obj);
    protected abstract T serializeByCursor(Cursor cursor);
    public abstract boolean salvar(T obj);
    public abstract boolean atualizar(T obj);
    public abstract boolean excluir(T obj);
    public abstract void closeDB();



    protected Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
    {
        return database.query(tableName,campos, selection, selectionArgs, null, null, orderBy);
    }

    //TODO
    //region Métodos pertencentes à cada classe - tirar depois
    public void insertOs(Os os){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("es_dispositivo", os.getDispositivo().getId_dispositivo());
        cv.put("es_status", os.getStatus().getId_status());
        cv.put("es_nivel", os.getNivel().getId_nivel());
        cv.put("es_responsavel", os.getUsuario().getId_usuario());
        cv.put("es_prioridade", os.getPrioridade().getId_prioridade());
        cv.put("titulo", os.getTitulo());
        cv.put("reclamacao", os.getReclamacao());
        cv.put("es_municipio", os.getMunicipio().getId_municipio());
        cv.put("nome_cliente", os.getNome_cliente());
        cv.put("telefone", os.getTelefone());
        cv.put("tempo_estimado", os.getTempo_estimado());
        cv.put("dt_abertura", os.getDt_abertura());
        cv.put("dt_resolucao", os.getDt_resolucao());
        cv.put("dt_retorno", os.getDt_retorno());
        cv.put("dt_prazo", os.getDt_prazo());
        cv.put("id_usuario_inc", os.getUsuario().getId_usuario());
        cv.put("dt_inc", os.getUsuario().getId_usuario());
        //cv.put("id_usuario_alt", os.getUsuario().getId_usuario());
        //cv.put("dt_alt", os.getUsuario().getId_usuario());
        //cv.put("id_usuario_resolucao", os.getUsuario().getId_usuario());

        db.insert("os", null, cv);

        db.close();
    }

    public void insertUsuario(Usuario usuario){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("username", usuario.getUsername());
        cv.put("password", usuario.getPassword());
        cv.put("email", usuario.getEmail());
        cv.put("es_departamento", usuario.getDepartamento().getId_departamento());
        cv.put("dt_inc", usuario.getDt_inc());
        cv.put("id_usuario_alt", usuario.getId_usuario_alt());
        cv.put("dt_alt", usuario.getDt_alt());

        db.insert("usuario", null, cv);

        db.close();
    }

    public List<Os> selectOsNivel(int nivel){

        List<Os> listOs = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectOsNivel = "SELECT " +
                "id_os," +
                "es_status," +
                "es_nivel," +
                "es_responsavel," +
                "es_prioridade," +
                "titulo," +
                "reclamacao," +
                "es_municipio," +
                "nome_cliente," +
                "telefone," +
                "tempo_estimado," +
                "dt_abertura," +
                "dt_resolucao," +
                "dt_retorno," +
                "dt_prazo," +
                "id_usuario_inc," +
                "dt_inc," +
                "id_usuario_alt," +
                "dt_alt," +
                "id_usuario_resolucao" +
                " FROM os";

        Cursor c;

        if (nivel == 0){
            c = db.rawQuery(sqlSelectOsNivel, null);
        }
        else{
            sqlSelectOsNivel += " WHERE id_nivel = ?";
            String[] params = new String[]{String.valueOf(nivel)};
            c = db.rawQuery(sqlSelectOsNivel, params);
        }


        if (c.moveToFirst()){
            do {
                Os os = new Os();
                os.setId_os(c.getInt(0));
                os.setStatus(selectStatus(c.getInt(1)));
                os.setNivel(selectNivel(c.getInt(2)));
                os.setUsuario(selectUsuario(c.getInt(3)));
                os.setPrioridade(selectPrioridade(c.getInt(4)));
                os.setTitulo(c.getString(5));
                os.setReclamacao(c.getString(6));
                os.setMunicipio(selectMunicipio(c.getInt(7)));
                os.setNome_cliente(c.getString(8));
                os.setTelefone(c.getString(9));
                os.setTempo_estimado(c.getDouble(10));
                os.setDt_abertura(c.getString(11));
                os.setDt_resolucao(c.getString(12));
                os.setDt_retorno(c.getString(13));
                os.setDt_prazo(c.getString(14));
                os.setId_usuario_inc(c.getInt(15));
                os.setDt_inc(c.getString(16));
                os.setId_usuario_alt(c.getInt(17));
                os.setDt_alt(c.getString(18));

                listOs.add(os);
            }while(c.moveToNext());
        }

        db.close();
        return listOs;

    }

    public Status selectStatus(int id){
        Status status = new Status();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectStatus = "SELECT id_status, descricao FROM codigo_status WHERE id_status = ?";

        String[] params = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(sqlSelectStatus, params);

        if (c.moveToFirst()){
            status.setId_status(c.getInt(0));
            status.setDescricao(c.getString(1));
        }
        
        db.close();

        return status;
    }

    public Nivel selectNivel(int id){
        Nivel nivel = new Nivel();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectNivel = "SELECT id_nivel, descricao FROM codigo_nivel WHERE id_nivel = ?";

        String[] params = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(sqlSelectNivel, params);

        if (c.moveToFirst()){
            nivel.setId_nivel(c.getInt(0));
            nivel.setDescricao(c.getString(1));
        }

        db.close();
        
        return nivel;
    }

    public Departamento selectDepartamento(int id){
        Departamento departamento = new Departamento();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectDepartamento = "SELECT id_departamento, descricao FROM codigo_departamento WHERE id_departamento = ?";

        String[] params = new String[]{String.valueOf(id)};

        Cursor c = db.rawQuery(sqlSelectDepartamento, params);

        if (c.moveToFirst()){
            departamento.setId_departamento(c.getInt(0));
            departamento.setDescricao(c.getString(1));
        }

        db.close();

        return departamento;
    }

    public Prioridade selectPrioridade(int id){
        Prioridade prioridade = new Prioridade();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectPrioridade = "SELECT id_prioridade, descricao FROM codigo_prioridade WHERE id_prioridade = ?";

        String[] params = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(sqlSelectPrioridade, params);

        if (c.moveToFirst()){
            prioridade.setId_prioridade(c.getInt(0));
            prioridade.setDescricao(c.getString(1));
        }

        db.close();

        return prioridade;
    }

    public Permissoes selectPermissoes(int id){
        Permissoes permissoes = new Permissoes();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectPermissoes = "SELECT id_permissao, descricao FROM codigo_permissoes WHERE id_permissoes = ?";

        String[] params = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(sqlSelectPermissoes, params);

        if (c.moveToFirst()){
            permissoes.setId_permissao(c.getInt(0));
            permissoes.setDescricao(c.getString(1));
        }

        db.close();

        return permissoes;
    }

    public List<Municipio> selectMunicipiosUF(String uf){
        List<Municipio> listMunicipios = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectMunicipio = "SELECT id_municipio, municipio, uf FROM codigo_municipio WHERE uf = ?";

        String[] params = new String[]{String.valueOf(uf)};
        Cursor c = db.rawQuery(sqlSelectMunicipio, params);

        if (c.moveToFirst()){
            do {
                Municipio municipio = new Municipio();
                municipio.setId_municipio(c.getInt(0));
                municipio.setMunicipio(c.getString(1));
                municipio.setUf(c.getString(2));

                listMunicipios.add(municipio);
            }while(c.moveToNext());
        }

        db.close();

        return listMunicipios;
    }

    public Municipio selectMunicipio(int id){
        Municipio municipio = new Municipio();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectMunicipio = "SELECT id_municipio, municipio, uf FROM codigo_municipio WHERE id_municipio = ?";

        String[] params = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(sqlSelectMunicipio, params);

        if (c.moveToFirst()){
            municipio.setId_municipio(c.getInt(0));
            municipio.setMunicipio(c.getString(1));
            municipio.setUf(c.getString(2));
        }

        db.close();

        return municipio;
    }
    
    public Usuario selectUsuario(int id){
        Usuario usuario = new Usuario();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectUsuario = "SELECT " +
                " id_usuario" +
                " ,username" +
                " ,password" +
                " ,email" +
                " ,es_departamento" +
                " ,dt_inc" +
                " ,id_usuario_alt" +
                " ,dt_alt" +
                " FROM usuario" +
                " WHERE id_usuario = ?";

        String[] params = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery(sqlSelectUsuario, params);

        if (c.moveToFirst()){
            usuario.setId_usuario(c.getInt(0));
            usuario.setUsername(c.getString(1));
            usuario.setPassword(c.getString(2));
            usuario.setEmail(c.getString(3));
            usuario.setDepartamento(selectDepartamento(c.getInt(4)));
            usuario.setDt_inc(c.getString(5));
            usuario.setId_usuario_alt(c.getInt(6));
            usuario.setDt_alt(c.getString(7));
        }

        db.close();

        return usuario;
    }
    //endregion
}



