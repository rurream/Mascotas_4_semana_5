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

import cl.creative_it_spa.mascotas_2.restApi.JsonKeys;
import cl.creative_it_spa.mascotas_2.POJOs.ConsultaFollow;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.ConsultaFollowJson;

/**
 * Created by Rodrigo on 15-08-2017.
 */

public class ConsultaFollowDeserializador implements JsonDeserializer<ConsultaFollowJson> {
    @Override
    public ConsultaFollowJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson=new Gson();
        ConsultaFollowJson consultaFollowJson=gson.fromJson(json, ConsultaFollowJson.class);
        JsonObject follow = json.getAsJsonObject();

        ArrayList<ConsultaFollow> consultaFollows = new ArrayList<>();
        JsonObject respuesta = follow.getAsJsonObject(JsonKeys.LLAVE_DATA);
        String estado_follow= respuesta.get(JsonKeys.KEY_outgoing_status).getAsString();

        ConsultaFollow row = new ConsultaFollow();
        row.setEstado_follow(estado_follow);
        consultaFollows.add(row);

        consultaFollowJson.setConsultaFollows(consultaFollows);
        return consultaFollowJson;
    }
}
