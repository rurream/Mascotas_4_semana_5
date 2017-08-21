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

import cl.creative_it_spa.mascotas_2.POJOs.LikeMascota;
import cl.creative_it_spa.mascotas_2.restApi.JsonKeys;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.DarLikeJson;

/**
 * Created by Rodrigo on 25-07-2017.
 */

public class LikeDeserializador implements JsonDeserializer<DarLikeJson> {
    @Override
    public DarLikeJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson=new Gson();
        DarLikeJson likeJson=gson.fromJson(json, DarLikeJson.class);
        JsonObject lJD = json.getAsJsonObject();

        ArrayList<LikeMascota> likesR = new ArrayList<>();
        JsonObject codigo = lJD.getAsJsonObject(JsonKeys.LLAVE_META);
        int codigo_respuesta= codigo.get(JsonKeys.CODIGO).getAsInt();
        LikeMascota row = new LikeMascota();
        row.setLikes_mascota(codigo_respuesta);
        likesR.add(row);


        likeJson.setLikes_mascota(likesR);
        return likeJson;
    }

}
