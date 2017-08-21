package cl.creative_it_spa.mascotas_2.restApi.deserializador;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import cl.creative_it_spa.mascotas_2.ConfigurarCuenta;
import cl.creative_it_spa.mascotas_2.POJOs.RegistroFireBase;
import cl.creative_it_spa.mascotas_2.restApi.JsonKeys;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.FireBaseJson;

/**
 * Created by Rodrigo on 08-08-2017.
 */

public class FindTokenDeserializador implements JsonDeserializer<FireBaseJson> {

    @Override
    public FireBaseJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson=new Gson();
        FireBaseJson fireBaseJson=gson.fromJson(json, FireBaseJson.class);

        JsonObject respuestaFB = json.getAsJsonObject();
        JsonObject resp = respuestaFB.getAsJsonObject(JsonKeys.MEDIA_RESPONSE_ARRAY_FIREBASE);

        ArrayList<RegistroFireBase> listarespuestaFB = new ArrayList();
        String id_usuario_instagram = "";
        String token = "";
        String id_firebase = "";

        JsonParser parser = new JsonParser();
        JsonObject jObj = (JsonObject) parser.parse(String.valueOf(resp));
        List<String> keys = new ArrayList<String>();
        for (Map.Entry<String, JsonElement> e : jObj.entrySet()) {
            keys.add(e.getKey());
        }

        for (int i = 0; i < keys.size(); i++) {
            id_firebase = keys.get(i).toString();
            JsonObject llave_FB = resp.getAsJsonObject(id_firebase);
            String id_usuario_Instagram_X = llave_FB.get(JsonKeys.KEY_id_usuario_instagram).getAsString();

            if (id_usuario_Instagram_X.matches(ConfigurarCuenta.id_usuario_cuenta)){

                RegistroFireBase row = new RegistroFireBase();

                row.setId_usuario_instagram(id_usuario_instagram);
                row.setToken(token);
                row.setId_firebase(id_firebase);

                listarespuestaFB.add(row);
            }
        }
        if (listarespuestaFB.size() == 0){
            RegistroFireBase row = new RegistroFireBase();
            row.setId_usuario_instagram("");
            row.setToken("");
            row.setId_firebase("");

            listarespuestaFB.add(row);
        }
        fireBaseJson.setRespuestaFB(listarespuestaFB);
        return fireBaseJson;
    }


}
