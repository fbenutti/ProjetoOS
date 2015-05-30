package br.edu.ifsp.fbenutti.projetoos.ws;

/**
 * Created by pelps_000 on 27/05/2015.
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import br.edu.ifsp.fbenutti.projetoos.entidades.Dispositivo;
import br.edu.ifsp.fbenutti.projetoos.util.Constantes;

public class DispositivoREST {

    private ResourceBundle config =
            ResourceBundle.getBundle(Constantes.WS_CONFIG_PROPS, Locale.getDefault());

    private StringBuilder URL_WS;

    public DispositivoREST() {
        URL_WS = new StringBuilder(config.getString(Constantes.WS_CONFIG_ENDERECO));
        this.URL_WS.append("dispositivo/");
    }

    // Busca Usuarios por ID
    public List<Dispositivo> getListaDispositivos() throws Exception {

        String[] resposta = new WebServiceCliente().get(URL_WS.toString()
                + "buscarTodosGSON");

        if (resposta[0].equals("200")) {
            Gson gson = new Gson();
            ArrayList<Dispositivo> listaDispositivos = new ArrayList<Dispositivo>();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

            for (int i = 0; i < array.size(); i++) {
                listaDispositivos.add(gson.fromJson(array.get(i), Dispositivo.class));
            }
            return listaDispositivos;
        } else {
            throw new Exception(resposta[1]);
        }
    }

    // Busca todos os usuarios
    public String getListaDispositivosGson() throws Exception {

        String[] resposta = new WebServiceCliente().get(URL_WS.toString()
                + "buscarTodosGSON");

        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }

    public String inseriDispositivo(Dispositivo dispositivo) throws Exception {

        Gson gson = new Gson();
        String usuarioJSON = gson.toJson(dispositivo);
        String[] resposta = new WebServiceCliente().post(URL_WS.toString()
                + "inserir", usuarioJSON);
        if (resposta[0].equals("200")) {
            return resposta[1];
        } else {
            throw new Exception(resposta[1]);
        }
    }

}

