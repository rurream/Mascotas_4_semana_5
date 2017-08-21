package cl.creative_it_spa.mascotas_2.POJOs;

import android.app.Activity;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Rodrigo on 21-07-2017.
 */

public class OrdenarMascotasTiempo {

    static ArrayList<ListaMascotas> mascotasLocal;
    //static  Activity activity;

    public OrdenarMascotasTiempo(ArrayList<ListaMascotas> mascotas) {
        this.mascotasLocal = mascotas;
        //this.activity=activity;
    }

    public static ArrayList<ListaMascotas> ordenar(){
        ArrayList<ListaMascotas> mascotasOrdenadas=new ArrayList<>();

        ArrayList mascota_ya_ordenada=new ArrayList() ;
        long fecha;
        int posición_de_la_lista=0;

        while (mascota_ya_ordenada.size() < mascotasLocal.size()){
            fecha=0;
            posición_de_la_lista=-1;
            for(int x = 0; x< mascotasLocal.size(); x++){
                if (!mascota_ya_ordenada.contains(x)) {
                    ListaMascotas mascota_x= mascotasLocal.get(x);
                    if (x == 0){
                        fecha=Long.valueOf(mascota_x.getFecha_creacion()).longValue();
                    }
                    if (Long.valueOf(mascota_x.getFecha_creacion()).longValue() < fecha){
                        posición_de_la_lista=x;
                        fecha=Long.valueOf(mascota_x.getFecha_creacion()).longValue();
                        //Toast.makeText(activity, "" + mascota_ya_ordenada.size() + " / " + fecha, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            if (posición_de_la_lista==-1){
                for(int x=0;x<mascota_ya_ordenada.size();x++){
                    if (!mascota_ya_ordenada.contains(x)) {
                        posición_de_la_lista=x;
                        break;
                    }
                }
            }

            ListaMascotas mascota_seleccionada= mascotasLocal.get(posición_de_la_lista);
            ListaMascotas row = new ListaMascotas();
            row.setId(mascota_seleccionada.getId());
            row.setNombre_mascota(mascota_seleccionada.getNombre_mascota());
            row.setFoto_mascota(mascota_seleccionada.getFoto_mascota());
            row.setPuntaje_mascota(mascota_seleccionada.getPuntaje_mascota());
            row.setFecha_creacion(mascota_seleccionada.getFecha_creacion());

            mascotasOrdenadas.add(row);


            mascota_ya_ordenada.add(posición_de_la_lista);

        }

        return mascotasOrdenadas;
    }
}
