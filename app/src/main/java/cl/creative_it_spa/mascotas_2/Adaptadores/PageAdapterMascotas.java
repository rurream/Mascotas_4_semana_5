package cl.creative_it_spa.mascotas_2.Adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Rodrigo on 14-06-2017.
 */

public class PageAdapterMascotas extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentos;

    public PageAdapterMascotas(FragmentManager fm, ArrayList<Fragment> fragmentos) {
        super(fm);
        this.fragmentos=fragmentos;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentos.get(position);
    }

    @Override
    public int getCount() {
        return fragmentos.size();
    }
}
