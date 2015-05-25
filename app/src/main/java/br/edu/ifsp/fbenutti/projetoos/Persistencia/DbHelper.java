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

/**
 * Created by pelps_000 on 20/05/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String NOME_BASE = "projetoos";
    private static final int VERSAO_BASE = 1;

    public DbHelper(Context context) {
        super(context, NOME_BASE, null, VERSAO_BASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTabelaOS = "CREATE TABLE os ("
                + "id_os int PRIMARY KEY AUTO_INCREMENT,"
                + "es_status int NOT NULL,"
                + "es_nv_escalonamento int NOT NULL,"
                + "es_responsavel int DEFAULT NULL,"
                + "es_prioridade int NOT NULL,"
                + "titulo varchar(50) NOT NULL,"
                + "reclamacao varchar(500) DEFAULT NULL,"
                + "es_municipio int DEFAULT NULL,"
                + "nome_cliente varchar(100) DEFAULT NULL,"
                + "telefone varchar(45) DEFAULT NULL,"
                + "tempo_estimado decimal(3,2) DEFAULT NULL,"
                + "dt_abertura int NOT NULL,"
                + "dt_resolucao int DEFAULT NULL,"
                + "dt_retorno int DEFAULT NULL,"
                + "dt_prazo int DEFAULT NULL,"
                + "id_usuario_inc int NOT NULL,"
                + "id_usuario_alt int DEFAULT NULL,"
                + "dt_alt int DEFAULT NULL,"
                + "id_usuario_resolucao int DEFAULT NULL)";
        db.execSQL(sqlCreateTabelaOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDropTabelaOs = "DROP TABLE os";
        db.execSQL(sqlDropTabelaOs);

        onCreate(db);
    }

    public void insertOs(Os os){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

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
        cv.put("id_usuario_resolucao", os.getUsuario().getId_usuario());

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

        List<Os> listOs = new ArrayList<Os>();

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
                os.setDt_abertura(c.getInt(11));
                os.setDt_resolucao(c.getInt(12));
                os.setDt_retorno(c.getInt(13));
                os.setDt_prazo(c.getInt(14));
                os.setId_usuario_inc(c.getInt(15));
                os.setDt_inc(c.getInt(16));
                os.setId_usuario_alt(c.getInt(17));
                os.setDt_alt(c.getInt(18));

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
            usuario.setDt_inc(c.getInt(5));
            usuario.setId_usuario_alt(c.getInt(6));
            usuario.setDt_alt(c.getInt(7));
        }

        db.close();

        return usuario;
    }

}

