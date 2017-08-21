package cl.creative_it_spa.mascotas_2.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.ListaUsuarios;
import cl.creative_it_spa.mascotas_2.restApi.JsonKeys;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.MascotaJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.UsuarioJson;

/**
 * Created by Rodrigo on 24-07-2017.
 */

public class UsuarioDeserializador implements JsonDeserializer<UsuarioJson> {
    @Override
    public UsuarioJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson=new Gson();
        UsuarioJson usuarioJson=gson.fromJson(json, UsuarioJson.class);
        JsonArray usuarioJsonData=json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        usuarioJson.setListaJsonUsuarios(deserializarUsuario(usuarioJsonData));
        return usuarioJson;
    }

    private ArrayList<ListaUsuarios> deserializarUsuario(JsonArray usuarioJsonData){
        ArrayList<ListaUsuarios> usuarioR=new ArrayList();
        for (int i = 0; i < usuarioJsonData.size(); i++) {


            JsonObject usuarioResponseDataObject = usuarioJsonData.get(i).getAsJsonObject();
            String id_usuario=usuarioResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String nombre_completo = usuarioResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();

            ListaUsuarios row = new ListaUsuarios();
            row.setId_usuario(id_usuario);
            row.setNombre_usuario(nombre_completo);


            usuarioR.add(row);
        }
        return usuarioR;
    }
}
