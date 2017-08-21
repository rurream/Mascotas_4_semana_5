package cl.creative_it_spa.mascotas_2.POJOs;

/**
 * Created by Rodrigo on 08-08-2017.
 */

public class RegistroFireBase {

    private String id_usuario_instagram;
    private String token;
    private String id_firebase;

    public RegistroFireBase(String id_usuario_instagram, String token, String id_firebase) {
        this.id_usuario_instagram = id_usuario_instagram;
        this.token = token;
        this.id_firebase = id_firebase;
    }

    public RegistroFireBase() {
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId_firebase() {
        return id_firebase;
    }

    public void setId_firebase(String id_firebase) {
        this.id_firebase = id_firebase;
    }
}
