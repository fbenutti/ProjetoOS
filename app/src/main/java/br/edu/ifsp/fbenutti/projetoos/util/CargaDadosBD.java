package br.edu.ifsp.fbenutti.projetoos.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.edu.ifsp.fbenutti.projetoos.R;

/**
 * Created by Felipe on 30/05/2015.
 */
public class CargaDadosBD {


    public static void inserirDepartamento(Context c, SQLiteDatabase db)
    {
        String[] departamentos;
        departamentos = c.getResources().getStringArray(R.array.insertDepartamentoBD);
        for(String s:departamentos)
        {
            db.execSQL(s);
        }
    }

    public static void inserirNivel(Context c, SQLiteDatabase db)
    {
        String[] niveis;
        niveis = c.getResources().getStringArray(R.array.insertNivelBD);
        for(String s:niveis)
        {
            db.execSQL(s);
        }
    }

    public static void inserirPrioridade(Context c, SQLiteDatabase db)
    {
        String[] prioridade;
        prioridade = c.getResources().getStringArray(R.array.insertPrioridadeBD);
        for(String s:prioridade)
        {
            db.execSQL(s);
        }
    }

    public static void inserirStatus(Context c, SQLiteDatabase db)
    {
        String[] status;
        status = c.getResources().getStringArray(R.array.insertStatusBD);
        for(String s:status)
        {
            db.execSQL(s);
        }
    }

    public static void inserirTipoAcao(Context c, SQLiteDatabase db)
    {
        String[] tipoAcao;
        tipoAcao = c.getResources().getStringArray(R.array.insertTipoAcaoBD);
        for(String s:tipoAcao)
        {
            db.execSQL(s);
        }
    }

    public static void inserirPermissoes(Context c, SQLiteDatabase db)
    {
        String[] permissoes;
        permissoes = c.getResources().getStringArray(R.array.insertPermissoesBD);
        for(String s:permissoes)
        {
            db.execSQL(s);
        }
    }


}
