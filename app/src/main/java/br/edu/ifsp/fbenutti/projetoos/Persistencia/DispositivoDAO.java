package br.edu.ifsp.fbenutti.projetoos.Persistencia;

/**
 * Created by pelps_000 on 28/05/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import br.edu.ifsp.fbenutti.projetoos.entidades.Dispositivo;
import br.edu.ifsp.fbenutti.projetoos.ws.DispositivoREST;

public class DispositivoDAO extends DAO<Dispositivo> {

    private Context context;
    private Cursor cursor;

    public DispositivoDAO(Context context) {
        super(context);
        this.context = context;
        campos = new String[] { "id_dispositivo", "android_genkey", "sn_ativo" };
        tableName = "dispositivos";
        database = getWritableDatabase();
    }

    @Override
    public boolean salvar(Dispositivo obj) {
        ContentValues values = serializeContentValues(obj);
        if (database.insert(tableName, null, values) > 0)
            return true;

        return false;
    }

    public boolean salvarWS(Dispositivo obj) {

        DispositivoREST dispositivoREST  = new DispositivoREST();
        String ret = new String();
        try {
            ret = dispositivoREST.inseriDispositivo(obj);

            if (ret.equalsIgnoreCase("Erro ao inserir ao dispositivo!")) {
                return false;
            }
            else{
                atualizarDispositivos(ret);
                return true;
            }

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    public boolean atualizarDispositivos(String dados)
    {
        Gson gson = new Gson();
        Dispositivo dispositivoAtual;
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(dados).getAsJsonArray();
        try {
            if (excluirAll()) {
                for (int i = 0; i < array.size(); i++) {
                    dispositivoAtual = gson.fromJson(array.get(i), Dispositivo.class);
                    salvar(dispositivoAtual);
                }
            } else {
                //Nao foi possivel deletar os dados da tabela
                //nao podem ser inseridos novos registros
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            close();
        }

        return true;
    }


    @Override
    public boolean atualizar(Dispositivo obj) {
        ContentValues values = serializeContentValues(obj);
        if (database.update(tableName, values, "id_dispositivo = ?",
                new String[] { String.valueOf(obj.getId_dispositivo()) }) > 0)
            return true;

        return false;
    }

    @Override
    public boolean excluir(Dispositivo obj) {
        if (database.delete(tableName, "id_dispositivo = ?",
                new String[]{String.valueOf(obj.getId_dispositivo())}) > 0)
            return true;

        return false;
    }

    public boolean excluirAll() {
        if (database.delete(tableName, null, null) > 0)
            return true;

        return false;
    }


    @Override
    protected ContentValues serializeContentValues(Dispositivo obj) {
        ContentValues values = new ContentValues();
        values.put("id_dispositivo", obj.getId_dispositivo());
        values.put("android_genkey", obj.getAndroid_genkey());
        values.put("sn_ativo", obj.getSn_ativo());

        return values;
    }

    @Override
    protected Dispositivo serializeByCursor(Cursor cursor) {

        Dispositivo dipositivo = new Dispositivo();
        dipositivo.setId_dispositivo(cursor.getInt(0));
        dipositivo.setAndroid_genkey(cursor.getString(1));
        dipositivo.setSn_ativo(cursor.getInt(2));


        return dipositivo;

    }

    @Override
    public void closeDB() {
        if(database.isOpen())
        {
            database.close();
        }

    }

}

