package cl.creative_it_spa.mascotas_2.Adaptadores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;
import cl.creative_it_spa.mascotas_2.R;

/**
 * Created by Rodrigo on 18-06-2017.
 */

public class AdaptMascotaSeleccionada extends RecyclerView.Adapter<AdaptMascotaSeleccionada.MascotaSelectViewHolder>{

    ArrayList<ListaMascotas> mascota_seleccionada;
    Activity activity;
    String id_mascota_seleccionada = "";

    TextView tv_mascota_seleccionada3;
    CircularImageView img_mascota_seleccionada;

    public AdaptMascotaSeleccionada(ArrayList<ListaMascotas> mascota_seleccionada, Activity activity, String id_mascota_seleccionada) {
        this.mascota_seleccionada = mascota_seleccionada;
        this.activity = activity;
        this.id_mascota_seleccionada = id_mascota_seleccionada;
        mostrar_mascota_seleccionada();

    }

    private void mostrar_mascota_seleccionada(){
        tv_mascota_seleccionada3=(TextView) activity.findViewById(R.id.tv_nombre_mascota_seleccionada);
        img_mascota_seleccionada=(CircularImageView) activity.findViewById(R.id.img_mascota_seleccionada3);


        if(id_mascota_seleccionada == "default"){
            ListaMascotas mascotaSeleccionada=mascota_seleccionada.get(0);
            tv_mascota_seleccionada3.setText(mascotaSeleccionada.getNombre_mascota());
            Picasso.with(activity)
                    .load(mascotaSeleccionada.getFoto_mascota())
                    .placeholder(R.drawable.default_dog)
                    .into(img_mascota_seleccionada);
        } else{
            for (int i = 0; i < mascota_seleccionada.size(); i++) {
                ListaMascotas objetox = mascota_seleccionada.get(i);
                if (objetox.getId().equals(id_mascota_seleccionada)){
                    //Toast.makeText(activity, objetox.getNombre_mascota(), Toast.LENGTH_SHORT).show();
                    tv_mascota_seleccionada3.setText(objetox.getNombre_mascota());
                    Picasso.with(activity)
                            .load(objetox.getFoto_mascota())
                            .placeholder(R.drawable.default_dog)
                            .into(img_mascota_seleccionada);

                    break;
                }
            }
        }


    }

    @Override
    public MascotaSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_mascota_seleccionada, parent, false);

        return new MascotaSelectViewHolder(v);
    }



    @Override
    public void onBindViewHolder(MascotaSelectViewHolder holder, int position) {
        final ListaMascotas mascota_sel= mascota_seleccionada.get(position);

        //holder.img_mascota_seleccionada_lib.setImageResource(mascota_sel.getFoto_mascota_mini_1());

        Picasso.with(activity)
                .load(mascota_sel.getFoto_mascota())
                .placeholder(R.drawable.default_dog)
                .into(holder.img_mascota_seleccionada_lib);

        holder.tv_mascota_seleccionada.setText(String.valueOf(mascota_sel.getPuntaje_mascota()));

    }

    @Override
    public int getItemCount() {
        return mascota_seleccionada.size();
        //return 1;
    }



    public static class MascotaSelectViewHolder extends RecyclerView.ViewHolder{
        ImageView img_mascota_seleccionada_lib, img_hueso_color_s;
        TextView tv_mascota_seleccionada;




        public MascotaSelectViewHolder(View itemView) {
            super(itemView);
            img_mascota_seleccionada_lib=(ImageView) itemView.findViewById(R.id.img_mascota_seleccionada_lib);
            img_hueso_color_s=(ImageView) itemView.findViewById(R.id.img_hueso_color_s);
            tv_mascota_seleccionada=(TextView) itemView.findViewById(R.id.tv_mascota_seleccionada);


        }
    }
}



